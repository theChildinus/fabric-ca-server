package org.kong;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.openssl.PEMParser;
import org.kong.channel.FabricUser;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class ParseCert {
    public static void main(String[] args) throws Exception {

        String basic = System.getProperty("user.dir");
        String fileName = basic + "/card/test2.card";
        System.out.println(fileName);
        FabricUser fabricUser = JSONObject.parseObject(IOUtils.toString(new FileInputStream(fileName), Charset.forName("utf-8")), FabricUser.class);
        StringReader stringReader = new StringReader(fabricUser.getSignedCert());
        PEMParser pp = new PEMParser(stringReader);
        X509CertificateHolder certificateHolder = (X509CertificateHolder)pp.readObject();
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        InputStream inputStream = new ByteArrayInputStream(certificateHolder.getEncoded());
        X509Certificate cert = (X509Certificate) certFactory.generateCertificate(inputStream);
        Files.write(Paths.get(basic, "/card/test2.cer"), fabricUser.getSignedCert().getBytes());
        Files.write(Paths.get(basic, "/card/text2.pem"), fabricUser.getPrivateKey().getBytes());
        String extensionValue = new String(cert.getExtensionValue("1.2.3.4.5.6.7.8.1"));
        System.out.println(extensionValue);
    }
}
