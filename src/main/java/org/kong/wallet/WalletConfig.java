package org.kong.wallet;

import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric_ca.sdk.Attribute;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WalletConfig {
    private String name;
    private Path storePath;
    private Wallet wallet;
    private Collection<Attribute> attributes;

    public WalletConfig(String name, Path storePath, boolean isFileWallet) {
        this(name, storePath, isFileWallet, new ArrayList<Attribute>());
    }

    public WalletConfig(String name, Path storePath, boolean isFileWallet, Collection<Attribute> attributes) {
        this.name = name;
        this.storePath = storePath;
        try {
            if (isFileWallet) {
                wallet = Wallet.createFileSystemWallet(storePath);
            } else {
                wallet = Wallet.createInMemoryWallet();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Path getStorePath() {
        return storePath;
    }

    public void setStorePath(Path storePath) {
        this.storePath = storePath;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Collection<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Collection<Attribute> attributes) {
        this.attributes = attributes;
    }
}
