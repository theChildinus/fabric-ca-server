package org.kong.wallet;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.hyperledger.fabric.sdk.NetworkConfig;
import org.kong.ca.CaClient;
import org.kong.channel.FabricUser;

import javax.json.JsonObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

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
            File file = new File(walletConfig.getStorePath() + "/" + walletConfig.getName() + ".card");
            // TODO USE Wallet API
            if (!file.exists()) {
                FabricUser fabricUser = caClient.registerUser(fabricAdmin, walletConfig);
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(JSONObject.toJSONString(fabricUser));
                fileWriter.close();
            }
            FabricUser fabricUser = unSerializeUser(file);
            fabricUser.setMspid(orgInfo.getMspId());
            return fabricUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public FabricUser reEnrollUser() {
        try {
            File file = new File(walletConfig.getStorePath() + "/" + walletConfig.getName() + ".card");

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
