package com.organizationservice.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import routeguide.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.lang.Math.*;
import static java.lang.Math.sqrt;

@GrpcService
public class RouteGuideService extends RouteGuideGrpc.RouteGuideImplBase {
    private static final Logger logger = LoggerFactory.getLogger(RouteGuideService.class);

    private List<Feature> featureList;

    private final ConcurrentMap<Point, List<RouteNote>> routeNotes = new ConcurrentHashMap<>();

    public RouteGuideService() {
        URL url = RouteGuideUtil.getDefaultFeaturesFile();
        try {
            this.featureList = RouteGuideUtil.parseFeatures(url);
        } catch (IOException ex) {
            logger.warn(ex.getMessage());
        }
    }

    @Override
    public void getFeature(Point request, StreamObserver<Feature> responseObserver) {
        //super.getFeature(request, responseObserver);
        responseObserver.onNext(checkFeature(request));
        responseObserver.onCompleted();
    }

    // A server-to-client streaming RPC.
    //
    // Obtains the Features available within the given Rectangle.  Results are
    // streamed rather than returned at once (e.g. in a response message with a
    // repeated field), as the rectangle may cover a large area and contain a
    // huge number of features.
    // rpc ListFeatures(Rectangle) returns (stream Feature) {}
    @Override
    public void listFeatures(Rectangle request, StreamObserver<Feature> responseObserver) {
        //super.listFeatures(request, responseObserver);
        int left = min(request.getLo().getLongitude(), request.getHi().getLongitude());
        int right = max(request.getLo().getLongitude(), request.getHi().getLongitude());
        int top = max(request.getLo().getLatitude(), request.getHi().getLatitude());
        int bottom = min(request.getLo().getLatitude(), request.getHi().getLatitude());

        for(Feature feature : featureList) {
            if (!RouteGuideUtil.exists(feature)) {
                continue;
            }

            int lat = feature.getLocation().getLatitude();
            int lon = feature.getLocation().getLongitude();
            if (lon >= left && lon <= right && lat >= bottom && lat <= top) {
                responseObserver.onNext(feature);
            }
        }
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<Point> recordRoute(StreamObserver<RouteSummary> responseObserver) {
        return super.recordRoute(responseObserver);
    }

    @Override
    public StreamObserver<RouteNote> routeChat(StreamObserver<RouteNote> responseObserver) {
        return super.routeChat(responseObserver);
    }

    /**
     * Get the notes list for the given location. If missing, create it.
     */
    private List<RouteNote> getOrCreateNotes(Point location) {
        List<RouteNote> notes = Collections.synchronizedList(new ArrayList<RouteNote>());
        List<RouteNote> prevNotes = routeNotes.putIfAbsent(location, notes);
        return prevNotes != null ? prevNotes : notes;
    }

    /**
     * Gets the feature at the given point.
     *
     * @param location the location to check.
     * @return The feature object at the point. Note that an empty name indicates no feature.
     */
    private Feature checkFeature(Point location) {
        for (Feature feature : featureList) {
            if (feature.getLocation().getLatitude() == location.getLatitude()
                    && feature.getLocation().getLongitude() == location.getLongitude()) {
                return feature;
            }
        }

        // No feature was found, return an unnamed feature.
        return Feature.newBuilder().setName("").setLocation(location).build();
    }

    /**
     * Calculate the distance between two points using the "haversine" formula.
     * The formula is based on http://mathforum.org/library/drmath/view/51879.html.
     *
     * @param start The starting point
     * @param end   The end point
     * @return The distance between the points in meters
     */
    private static int calcDistance(Point start, Point end) {
        int r = 6371000; // earth radius in meters
        double lat1 = toRadians(RouteGuideUtil.getLatitude(start));
        double lat2 = toRadians(RouteGuideUtil.getLatitude(end));
        double lon1 = toRadians(RouteGuideUtil.getLongitude(start));
        double lon2 = toRadians(RouteGuideUtil.getLongitude(end));
        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        double a = sin(deltaLat / 2) * sin(deltaLat / 2)
                + cos(lat1) * cos(lat2) * sin(deltaLon / 2) * sin(deltaLon / 2);
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));

        return (int) (r * c);
    }
}
