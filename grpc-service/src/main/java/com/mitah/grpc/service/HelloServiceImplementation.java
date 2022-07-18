package com.mitah.grpc.service;

import com.mitah.grpc.HelloRequest;
import com.mitah.grpc.HelloResponse;
import com.mitah.grpc.helloGrpc;
import io.grpc.stub.StreamObserver;

public class HelloServiceImplementation extends helloGrpc.helloImplBase {

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver){
        String firstName = request.getFirstName();
        System.out.println("Received Message: " + firstName);


        HelloResponse greetingResponse = HelloResponse.newBuilder()
                .setGreetings("Hello " + firstName)
                .build();

        responseObserver.onNext(greetingResponse);
        responseObserver.onCompleted();
    }
}
