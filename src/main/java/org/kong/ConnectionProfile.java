package org.kong;

import org.hyperledger.fabric.sdk.NetworkConfig;
import org.kong.channel.FabricUser;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ConnectionProfile {
    private NetworkConfig networkConfig;
    private JsonObject jsonObject;

    public ConnectionProfile(InputStream inputStream) throws Exception {
        JsonReader reader = Json.createReader(inputStream);
        this.jsonObject = (JsonObject) reader.read();
        this.networkConfig = NetworkConfig.fromJsonObject(jsonObject);
    }

    public ConnectionProfile(File file) throws Exception {
        this(new FileInputStream(file));
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }


    public NetworkConfig getNetworkConfig() {
        return this.networkConfig;
    }

    public FabricUser getPeerAdmin() {
        try {
            NetworkConfig.UserInfo userInfo = networkConfig.getPeerAdmin();
            FabricUser fabricPeerAdmin = new FabricUser();
            fabricPeerAdmin.setMspid(userInfo.getMspid());
            fabricPeerAdmin.setName(userInfo.getName());
            fabricPeerAdmin.setEnrollment(userInfo.getEnrollment());
            return fabricPeerAdmin;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

    }
}
