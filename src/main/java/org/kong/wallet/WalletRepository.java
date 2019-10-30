package org.kong.wallet;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.hyperledger.fabric.sdk.NetworkConfig;
import org.kong.ca.CaClient;
import org.kong.channel.FabricUser;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WalletRepository {
    private WalletConfig walletConfig;
    private CaClient caClient;
    private NetworkConfig.OrgInfo orgInfo;

    public WalletRepository(WalletConfig walletConfig, NetworkConfig.OrgInfo orgInfo) {
        this.walletConfig = walletConfig;
        this.caClient = new CaClient(orgInfo.getCertificateAuthorities().get(0));
        this.orgInfo = orgInfo;
    }

    public FabricUser registerUser() {
        try {
            FabricUser fabricAdmin = enrollAdmin();
            String userName = walletConfig.getName();
            Path basicPath = walletConfig.getStorePath();
            File cardfile = new File(basicPath + "/" + userName + "/" + userName + ".card");
            File certfile = new File(basicPath + "/" + userName + "/" + userName + ".crt");
            File keyfile = new File(basicPath + "/"+ userName + "/" + userName + ".pem");
            // TODO USE Wallet API
            if (!cardfile.exists()) {
                if (!cardfile.getParentFile().mkdirs()) {
                    System.out.println("Create Dir Failed");
                    return null;
                }
                FabricUser fabricUser = caClient.registerUser(fabricAdmin, walletConfig);
                FileWriter cardWriter = new FileWriter(cardfile);
                FileWriter certWriter = new FileWriter(certfile);
                FileWriter keyWriter = new FileWriter(keyfile);
                cardWriter.write(JSONObject.toJSONString(fabricUser));
                certWriter.write(fabricUser.getSignedCert());
                keyWriter.write(fabricUser.getPrivateKey());
                cardWriter.close();
                certWriter.close();
                keyWriter.close();
            }
            FabricUser fabricUser = unSerializeUser(cardfile);
            fabricUser.setMspid(orgInfo.getMspId());
            return fabricUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public FabricUser reEnrollUser() {
        try {
            Path basicPath = walletConfig.getStorePath();
            String username = walletConfig.getName();
            File file = new File(basicPath + "/" + username + "/" + username + ".card");
            FabricUser fabricUser = unSerializeUser(file);
            caClient.reEnrollUser(fabricUser);
            return fabricUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private FabricUser unSerializeUser(File file) throws IOException {
        FabricUser fabricUser = JSONObject.parseObject(IOUtils.toString(new FileInputStream(file), Charset.forName("utf-8")), FabricUser.class);

        fabricUser.setEnrollment(null);
        return fabricUser;
    }

    private FabricUser enrollAdmin() throws Exception {
        File file = new File(walletConfig.getStorePath() + "/admin.card");
        if (!file.exists()) {
            FabricUser fabricUser = caClient.enrollAdmin();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(JSONObject.toJSONString(fabricUser));
            fileWriter.close();
        }
        FabricUser fabricAdmin = unSerializeUser(file);
        fabricAdmin.setMspid(orgInfo.getMspId());
        return fabricAdmin;
    }
}
