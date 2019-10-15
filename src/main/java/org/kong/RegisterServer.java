package org.kong;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.kong.proto.RegisterGrpc;
import org.kong.proto.RegisterReq;
import org.kong.proto.RegisterResp;

import java.io.IOException;
import java.util.logging.Logger;

public class RegisterServer {
    private static final Logger logger = Logger.getLogger(RegisterServer.class.getName());
    private Server server;

    private void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new RegisterImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                RegisterServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final RegisterServer server = new RegisterServer();
        server.start();
        server.blockUntilShutdown();
    }

    static class RegisterImpl extends RegisterGrpc.RegisterImplBase {
        @Override
        public void register(RegisterReq req, StreamObserver<RegisterResp> responseObserver) {
            System.out.println("Received UserName:" + req.getUsername());
            RegisterResp resp = RegisterResp.newBuilder().setCode(111).build();
            responseObserver.onNext(resp);
            responseObserver.onCompleted();
        }
    }
}
