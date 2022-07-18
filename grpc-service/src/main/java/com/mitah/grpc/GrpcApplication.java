package com.mitah.grpc;

import com.mitah.grpc.service.HelloServiceImplementation;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@SpringBootApplication
public class GrpcApplication {
    private static final Logger logger = Logger.getLogger(GrpcApplication.class.getName());

    private Server server;
    public static void main(String args[]) {
        //SpringApplication.run(GrpcApplication.class, args);
        final GrpcApplication grpcApplication = new GrpcApplication();
        try {
            grpcApplication.start();
            grpcApplication.server.awaitTermination();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void start() throws IOException {
        int port = 50052;
        server = ServerBuilder.forPort(port).addService(new HelloServiceImplementation()).build().start();

        logger.info("Server started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("Shutting down gRPC server");
                try {
                    server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }
}
