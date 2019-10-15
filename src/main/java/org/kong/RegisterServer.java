package org.kong;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.apache.log4j.BasicConfigurator;
import org.kong.channel.FabricUser;
import org.kong.proto.RegisterGrpc;
import org.kong.proto.RegisterReq;
import org.kong.proto.RegisterResp;
import org.kong.wallet.WalletConfig;
import org.kong.wallet.WalletRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            logger.info("Received UserName:" + req.getUsername());
            RegisterResp resp;
            try {
                registerUser(req.getUsername());
                resp = RegisterResp.newBuilder().setCode(0).build();

            } catch (Exception e) {
                e.printStackTrace();
                resp = RegisterResp.newBuilder().setCode(-1).build();
            }
            responseObserver.onNext(resp);
            responseObserver.onCompleted();
        }

        public void registerUser(String username) throws Exception {
            BasicConfigurator.configure();
            // String basic = "/home/kong/goworks/src/github.com/hyperledger/fabric-samples/basic-network/";
            Path connectionFilePath = Paths.get("./", "connection.json");
            ConnectionProfile connectionProfile = new ConnectionProfile(connectionFilePath.toFile());
            WalletConfig walletConfig = new WalletConfig(username, Paths.get("./card"), true);
            WalletRepository walletRepository = new WalletRepository(
                    walletConfig, connectionProfile.getNetworkConfig().getClientOrganization());

            FabricUser fabricUser = walletRepository.registerUser();
            logger.info("registerUser: " + fabricUser.getName());
        }
    }
}
