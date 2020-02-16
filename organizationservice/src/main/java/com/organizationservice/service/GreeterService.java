package com.organizationservice.service;

import helloworld.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import routeguide.Feature;
import routeguide.RouteGuideUtil;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@GrpcService
public class GreeterService extends GreeterGrpc.GreeterImplBase {
    private static final Logger logger = LoggerFactory.getLogger(GreeterService.class);

    public GreeterService() {
    }

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {

        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        logger.info(reply.getMessage());
        //super.sayHello(request, responseObserver);
    }

    @Override
    public void getName(IdMessage request, StreamObserver<NameMessage> responseObserver) {
        //super.getName(request, responseObserver);
        NameMessage nameMessage = NameMessage.newBuilder().setName("escoffier").build();
        responseObserver.onNext(nameMessage);
        responseObserver.onCompleted();
    }
}
