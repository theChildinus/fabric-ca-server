package org.kong;

import org.apache.log4j.BasicConfigurator;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.impl.GatewayImpl;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Issue {
    public static void main(String[] args) throws  Exception{
        BasicConfigurator.configure();
        String basicNetworkPath = "/home/kong/goworks/src/github.com/hyperledger/fabric-samples/basic-network";
        Path networkConfigPath = Paths.get(basicNetworkPath,"connection.json");
        Path credPath = Paths.get(basicNetworkPath, "/crypto-config/peerOrganizations/org1.example.com/users/User1@org1.example.com");
        FileReader cert = new FileReader(credPath + "/msp/signcerts/User1@org1.example.com-cert.pem");
        FileReader key = new FileReader(credPath + "/msp/keystore/c75bd6911aca808941c3557ee7c97e90f3952e379497dc55eb903f31b50abc83_sk");
        Path walletPath = Paths.get("wallet");
        Wallet wallet = Wallet.createFileSystemWallet(walletPath);
        wallet.put("User1@org1.example.com", Wallet.Identity.createIdentity("Org1.MSP", cert, key));

        Gateway.Builder builder = Gateway.createBuilder();
        builder.identity(wallet, "User1@org1.example.com").networkConfig(networkConfigPath);

        try (Gateway gateway = builder.connect()) {
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("papercontract");
            byte[] result = contract.submitTransaction("issue", "MagnetoCorp", "00001", "2020-05-31", "2020-11-30", "5000000");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
