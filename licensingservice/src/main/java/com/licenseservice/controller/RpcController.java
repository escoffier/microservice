package com.licenseservice.controller;

import com.licenseservice.service.GrpcClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import routeguide.Feature;
import routeguide.Point;
import routeguide.Rectangle;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/v1/rpc")
public class RpcController {
    private static final Logger logger = LoggerFactory.getLogger(RpcController.class);

    @Autowired
    private GrpcClientService grpcClientService;

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        String message = grpcClientService.sayHello(name);
        logger.info(message);
        return message;
    }

    @GetMapping("/features")
    public List<Feature> listFeatures() {
        //Rectangle rec = Rectangle.newBuilder().
        int lowLat=400000000,  lowLon = -750000000,  hiLat=420000000,   hiLon = -730000000;
        Rectangle rectangle =
                Rectangle.newBuilder()
                        .setLo(Point.newBuilder().setLatitude(lowLat).setLongitude(lowLon).build())
                        .setHi(Point.newBuilder().setLatitude(hiLat).setLongitude(hiLon).build()).build();
        return grpcClientService.listFeaturesSync(rectangle);
    }

    @GetMapping("/feature")
    public Feature getFeature() {
        Feature feature = grpcClientService.getFeature();
        logger.info(feature.toString());
        return feature;
    }
}
