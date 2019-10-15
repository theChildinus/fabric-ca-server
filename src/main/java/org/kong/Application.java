package org.kong;

import org.apache.log4j.BasicConfigurator;
import org.kong.channel.FabricUser;
import org.kong.wallet.WalletConfig;
import org.kong.wallet.WalletRepository;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();
        String basic = "/home/kong/goworks/src/github.com/hyperledger/fabric-samples/basic-network/";
        Path connectionFilePath = Paths.get("./", "connection.json");
        ConnectionProfile connectionProfile = new ConnectionProfile(connectionFilePath.toFile());
        WalletConfig walletConfig = new WalletConfig("test", Paths.get("./card"), true);
        WalletRepository walletRepository = new WalletRepository(
                walletConfig, connectionProfile.getNetworkConfig().getClientOrganization());

        FabricUser fabricUser = walletRepository.registerUser();
        System.out.println("registerUser: " + fabricUser.getName());

        FabricUser fabricUser2 = walletRepository.reEnrollUser();
        System.out.println("reEnroll: " + fabricUser2.getName());
    }
}
