package org.kong;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.kong.proto.*;
import org.apache.log4j.BasicConfigurator;
import org.kong.channel.FabricUser;
import org.kong.wallet.WalletConfig;
import org.kong.wallet.WalletRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Logger;

public class FabricService {
    private static final Logger logger = Logger.getLogger(FabricService.class.getName());
    private Server server;

    private void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new FabricServiceImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                FabricService.this.stop();
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
        final FabricService server = new FabricService();
        server.start();
        server.blockUntilShutdown();
    }

    static class FabricServiceImpl extends FabricServiceGrpc.FabricServiceImplBase {
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

        @Override
        public void download(DownloadReq req, StreamObserver<DownloadResp> responseObserver) {
            logger.info("Received UserName:" + req.getUsername());
            String userName = req.getUsername();
            String encoding = "UTF-8";
            File file = new File("./card/" + userName + "/" + userName + ".crt");
            Long filelength = file.length();
            byte[] filecontent = new byte[filelength.intValue()];
            try {
                FileInputStream in = new FileInputStream(file);
                in.read(filecontent);
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            DownloadResp resp;
            try {
                resp = DownloadResp.newBuilder().setCert(new String(filecontent, encoding)).build();
            } catch (Exception e) {
                e.printStackTrace();
                resp = DownloadResp.newBuilder().setCert("").build();
            }
            responseObserver.onNext(resp);
            responseObserver.onCompleted();
        }

        @Override
        public void login(LoginReq req, StreamObserver<LoginResp> responseObserver) {
            logger.info("Received UserName:" + req.getUsername());
            LoginResp resp;
            try {
                boolean res = loginUserVerify(req.getUsername(), Base64.decode(req.getUsersign()), String.valueOf(req.getUserrand()));
                if (res) {
                    resp = LoginResp.newBuilder().setCode(0).build();
                } else {
                    resp = LoginResp.newBuilder().setCode(-1).build();
                }

            } catch (Exception e) {
                e.printStackTrace();
                resp = LoginResp.newBuilder().setCode(-1).build();
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
            if (fabricUser != null) {
                logger.info("registerUser " + fabricUser.getName() + " Success");
            } else {
                logger.info("registerUser " + username + " Failed");
            }

        }

        public boolean loginUserVerify(String username, byte[] signed, String source) throws Exception {
            System.out.println(HexBin.encode(signed));
            String certPath = "./card/" + username + "/" + username + ".crt";
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate)cf.generateCertificate(new FileInputStream(certPath));
            PublicKey ecPublicKey = cert.getPublicKey();
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(ecPublicKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            PublicKey newPublicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Signature signature = Signature.getInstance("SHA1withECDSA");
            signature.initVerify(newPublicKey);
            signature.update(source.getBytes());
            boolean bool = signature.verify(signed);
            return bool;
        }
    }
}
