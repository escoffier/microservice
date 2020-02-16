package com.licenseservice.service;

import com.google.common.collect.Lists;
import helloworld.GreeterGrpc;
import helloworld.HelloReply;
import helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import routeguide.Feature;
import routeguide.Point;
import routeguide.RouteGuideGrpc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class GrpcClientService {

    private static Logger logger = LoggerFactory.getLogger(GrpcClientService.class);

    @GrpcClient("organization-service")
    private  GreeterGrpc.GreeterBlockingStub blockingStub;

    @GrpcClient("organization-service")
    private RouteGuideGrpc.RouteGuideStub routeGuideStub;

    @GrpcClient("organization-service")
    RouteGuideGrpc.RouteGuideBlockingStub routeGuideBlockingStub;
    //RouteGuideGrpc.RouteGuideFutureStub routeGuideFutureStub;

    public String sayHello(String name) {
        HelloReply reply = blockingStub.sayHello(HelloRequest.newBuilder().setName(name).build());
        return reply.getMessage();
    }

    public Feature getFeature() {
        int lat=100, lon=200;

        Point request = Point.newBuilder().setLatitude(lat).setLongitude(lon).build();
        return routeGuideBlockingStub.getFeature(request);
    }
    // A server-to-client streaming RPC.
    //
    // Obtains the Features available within the given Rectangle.  Results are
    // streamed rather than returned at once (e.g. in a response message with a
    // repeated field), as the rectangle may cover a large area and contain a
    // huge number of features.
    // rpc ListFeatures(Rectangle) returns (stream Feature) {}
    public void listFeatures(routeguide.Rectangle request) {
        StreamObserver<Feature> responseObserver = new StreamObserver<Feature>() {
            @Override
            public void onNext(Feature feature) {
                logger.info(feature.toString());
            }

            @Override
            public void onError(Throwable throwable) {
                logger.error(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
            }
        };
        routeGuideStub.listFeatures(request, responseObserver);
    }

    public List<Feature> listFeaturesSync(routeguide.Rectangle request) {
        Iterator<Feature> features = routeGuideBlockingStub.listFeatures(request);
        List<Feature> featureList = Lists.newArrayList(features);


//        while (features.hasNext()) {
//            Feature feature = features.next();
//            JsonFo
//            feature.t
//            logger.info(feature.toString());
//        }
        return featureList;
    }
}
