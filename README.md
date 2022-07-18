# grpc-demo
This is a Demo application for grpc connectivity.

# Steps to compile the application
1. Download the git repository
2. Compile the module 'grpc-common' using mvn clean install
3. Compile the module 'grpc-server' using mvn clean install
4. Compile the module 'grpc-client' using mvn clean install

# Steps to run the demo
1. Run the grpc-server by triggering the main method in GrpcApplication.java
2. Verify the line "INFO: Server started, listening on 50052" in the Standard output console
3. Run the grpc-client by triggering the main method in ClientCode.java

# Output
The gRPC call is made to the server on localhost 50052 and the same can be verified in the Client as well as server logs
