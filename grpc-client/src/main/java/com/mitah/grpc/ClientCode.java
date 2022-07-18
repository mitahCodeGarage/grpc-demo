package com.mitah.grpc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class ClientCode {
    private static final Logger logger = Logger.getLogger(ClientCode.class.getName());

    private final helloGrpc.helloBlockingStub blockingStub;

    public ClientCode(Channel channel) {
        blockingStub = helloGrpc.newBlockingStub(channel);
    }

    public static void main(String[] args) throws Exception {
        String firstName = "Hatim";
        String lastName = "Motorwala";
        String serverAddress = "localhost:50052";
        ManagedChannel channel = ManagedChannelBuilder.forTarget(serverAddress)
                .usePlaintext()
                .build();
        try {
            ClientCode clientCode = new ClientCode(channel);
            clientCode.createGreeting(firstName, lastName);
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }

    public void createGreeting(String firstName, String lastName) {
        logger.info("Sending greeting to server for name: " + firstName + " " + lastName);
        HelloRequest request = HelloRequest.newBuilder().setFirstName(firstName).setLastName(lastName).build();
        logger.info("Sending to server: " + request);

        HelloResponse response;
        try {
            response = blockingStub.hello(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        logger.info("Got following from the server: " + response.getGreetings());

    }
}
