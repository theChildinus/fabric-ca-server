package org.kong.channel;

import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.User;
import org.hyperledger.fabric_ca.sdk.Attribute;
import org.kong.utils.PemUtils;

import java.io.*;
import java.security.PrivateKey;
import java.util.Collection;
import java.util.Set;

public class FabricUser implements User, Serializable {

    private static final long serialVersionUID = 8077132186383604355L;
    private String name;
    private Set<String> roles;
    private String mspid;
    private String account;
    private String affiliation;
    private String organization;
    private String signedCert;
    private String privateKey;
    private Enrollment enrollment;
    private Collection<Attribute> attributes;

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public String getMspid() {
        return mspid;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getSignedCert() {
        return signedCert;
    }

    public void setSignedCert(String signedCert) {
        this.signedCert = signedCert;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setMspid(String mspid) {
        this.mspid = mspid;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<String> getRoles() {
        return roles;
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public String getAffiliation() {
        return affiliation;
    }

    @Override
    public Enrollment getEnrollment() {
        if (this.enrollment == null) {
            try {
                PrivateKey privateKey = null;
                if (this.privateKey != null && !this.privateKey.isEmpty()) {
                    privateKey = PemUtils.getPrivateKeyFromPEMString(this.privateKey);
                }
                this.enrollment = new FabricEnrollment(privateKey, signedCert);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return this.enrollment;
    }

    @Override
    public String getMspId() {
        return mspid;
    }

    public Collection<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Collection<Attribute> attributes) {
        this.attributes = attributes;
    }
}
