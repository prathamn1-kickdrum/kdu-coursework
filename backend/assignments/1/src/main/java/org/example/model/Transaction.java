package org.example.model;

public class Transaction {
    private String type;
    private String coinCode;
    private String walletAddress;
    private String blockHash;

    // Constructor
    public Transaction(String type, String coinCode, String walletAddress,String hash) {
        this.type = type;
        this.coinCode = coinCode;
        this.walletAddress = walletAddress;
        this.blockHash=hash;
    }

    // Getters
    public String getType() {
        return type;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public String getBlockHash() {
        return blockHash;
    }

    // Setters
    public void setType(String type) {
        this.type = type;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    // toString method
    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", coinCode='" + coinCode + '\'' +
                ", walletAddress='" + walletAddress + '\'' +
                ", blockHash='" + blockHash + '\'' +
                '}';
    }
}

