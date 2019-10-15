package org.kong.ca;

import org.bouncycastle.eac.EACCertificateBuilder;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.NetworkConfig;
import org.hyperledger.fabric.sdk.User;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.Attribute;
import org.hyperledger.fabric_ca.sdk.EnrollmentRequest;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.RegistrationRequest;
import org.kong.channel.FabricUser;
import org.kong.utils.PemUtils;
import org.kong.wallet.WalletConfig;

import java.util.ArrayList;

public class CaClient {
    //fabric 内置管理员用户信息
    private static final String adminUsername = "admin";
    private static final String adminPassword = "adminpw";

    private NetworkConfig.CAInfo caInfo;

    public CaClient(NetworkConfig.CAInfo caInfo) {
        this.caInfo = caInfo;
    }

    /**
     * 从CA取得admin授权证书
     *
     * @return 授权用户
     * @throws Exception
     */
    public FabricUser enrollAdmin() throws Exception {
        FabricUser fabricUser = new FabricUser();
        HFCAClient caClient = HFCAClient.createNewInstance(caInfo);
        caClient.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());

        Enrollment enrollment = caClient.enroll(adminUsername, adminPassword);
        fabricUser.setName(adminUsername);
        fabricUser.setPrivateKey(PemUtils.getPEMString(enrollment.getKey()));
        fabricUser.setSignedCert(enrollment.getCert());
        return fabricUser;
    }

    /**
     * 根据admin授权，新注册用户并授权
     *
     * @param adminUser 管理员用户
     * @param walletConfig 钱包配置
     * @return 授权用户
     * @throws Exception
     */
    public FabricUser registerUser(FabricUser adminUser, WalletConfig walletConfig) throws Exception {
        boolean attributesExist = !walletConfig.getAttributes().isEmpty();

        HFCAClient caClient = HFCAClient.createNewInstance(caInfo);
        caClient.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());

        FabricUser newUser = new FabricUser();
        newUser.setName(walletConfig.getName());
        RegistrationRequest rr = new RegistrationRequest(newUser.getName());
        if (attributesExist) {
            ArrayList<Attribute> attList = (ArrayList<Attribute>) walletConfig.getAttributes();
            for (Attribute attribute : attList) {
                rr.addAttribute(attribute);
            }
        }
        String enrollmentSecret = caClient.register(rr, adminUser);

        EnrollmentRequest req = new EnrollmentRequest();
        ArrayList<Attribute> attList = (ArrayList<Attribute>) walletConfig.getAttributes();
        for (Attribute attribute : attList) {
            req.addAttrReq(attribute.getName()).setOptional(true);
        }

        caClient = HFCAClient.createNewInstance(caInfo);
        caClient.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());
        Enrollment enrollment = attributesExist ?
                caClient.enroll(newUser.getName(), enrollmentSecret, req) :
                caClient.enroll(newUser.getName(), enrollmentSecret);

        String signedCert = enrollment.getCert();
        newUser.setPrivateKey(PemUtils.getPEMString(enrollment.getKey()));
        newUser.setSignedCert(signedCert);
        if (attributesExist) {
            newUser.setAttributes(attList);
        }
        return newUser;
    }
    public void reEnrollUser(FabricUser user) throws Exception {
        HFCAClient caClient = HFCAClient.createNewInstance(caInfo);

        caClient.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());
        Enrollment enrollment = caClient.reenroll(user);

        user.setEnrollment(enrollment);
        String signedCert = enrollment.getCert();
        user.setPrivateKey(PemUtils.getPEMString(enrollment.getKey()));
        user.setSignedCert(signedCert);
    }
}
