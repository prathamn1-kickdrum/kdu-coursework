package org.example.model;

import org.example.logging.Logger;
import org.example.operations.CoinOperation;

import java.util.HashMap;
import java.util.Map;

public class Traders {
    private String firstName;
    private double netMoney;
    private String lastName;
    private String phone;
    private String walletAddress;
    private final Map<String, Long> portfolio;


    public Traders(String firstName, String lastName, String phone, String walletAddress) {
        portfolio = new HashMap<>();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.walletAddress = walletAddress;
        this.netMoney = 0;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public double getNetMoney() {
        return this.netMoney;
    }

    public long getCoinVolume(String code) {
        if(!portfolio.containsKey(code)) return 0L;
        return portfolio.get(code);
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public void updateNetMoney(double diff) {
        this.netMoney +=diff;
    }
    public void updatePortfolioCoinVolume(String code, long qty) {
        long org = portfolio.get(code);
        portfolio.replace(code,qty+org);
    }

    public double getNetIncome() {
        double marketHolding = portfolio.entrySet().stream()
                .mapToDouble(entry -> {
                    String coinSymbol = entry.getKey();
                    long volume = entry.getValue();
                    Coins coin = CoinOperation.getCoinByCode(coinSymbol);
                    double currentPrice = coin.getCoinPrice();
                    return volume * currentPrice;
                })
                .sum();
        return marketHolding + netMoney;
    }

    public void printPortfolio() {
        Logger loggerObj = Logger.getLoggerObject();
        for(Map.Entry<String,Long> mp : portfolio.entrySet()) {
            loggerObj.infoLog(mp.getKey() + " : " + mp.getValue());
        }
    }

    // toString method
    @Override
    public String toString() {
        return " firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", walletAddress='" + walletAddress + '\'';
    }
}

