package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.logging.Logger;
import org.example.model.Coins;
import org.example.model.Traders;
import org.example.operations.CoinOperation;
import org.example.operations.TraderOperation;
import org.example.operations.TransactionStorage;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ExecuteTransaction implements Runnable{
    private final Random rnd = new Random();
    private CountDownLatch latch;
    private String type;
    private Coins coin;
    private String coinCode;
    private long quantity;
    private double price;
    private long volume;
    private String walletAddress;
    private Traders trader;
    private final Object monitor = new Object();
    Logger loggerObj = Logger.getLoggerObject();

    public ExecuteTransaction(JsonNode transactionJson, CountDownLatch latch) {
        this.type = transactionJson.get("type").asText();
        this.latch = latch;
        this.coin = CoinOperation.getCoinByCode(transactionJson.get("data").get("coin").asText());
        assert coin != null;
        this.coinCode = coin.getCoinSymbol();

        if(transactionJson.get("data").get("wallet_address")!=null) {
            this.walletAddress = transactionJson.get("data").get("wallet_address").asText();
            this.trader = TraderOperation.getTraderByWallet(walletAddress);
        }

        if(transactionJson.get("data").get("quantity")!=null) {
            this.quantity = transactionJson.get("data").get("quantity").asLong();
        }

        if(transactionJson.get("data").get("price")!=null) {
            this.price = transactionJson.get("data").get("price").asDouble();
        }

        if(transactionJson.get("data").get("volume")!=null) {
            this.volume = transactionJson.get("data").get("volume").asLong();
        }

    }

    public ExecuteTransaction() {

    }
    private String getBlockHash() {
        String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder transactionHash = new StringBuilder();
        for (int i = 0; i < 199999999; i++) {
            if(i==199999998) break;
        }
        while (transactionHash.length() < 128) {
            int index = rnd.nextInt() * saltChars.length();
            transactionHash.append(saltChars.charAt(index));
        }
        String hashCode = transactionHash.toString();
        return "0x" + hashCode.toLowerCase();
    }


    private synchronized void buyTransaction() {
        while (true) {
            if (coin.getCoinVolume() >= quantity) {
                coin.updateCoinVolume(-quantity);
                trader.updatePortfolioCoinVolume(coinCode, quantity);
                trader.updateNetMoney(-quantity * coin.getCoinPrice());
                break;
            }

            try {
                // Release the lock and wait for a notification or a timeout
                monitor.wait(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                loggerObj.errorLog(e.getMessage(), e);
            }
        }
    }

    private synchronized void sellTransaction() {
        while (true) {
            if (trader.getCoinVolume(coinCode) >= quantity) {
                coin.updateCoinVolume(quantity);
                trader.updatePortfolioCoinVolume(coinCode, -quantity);
                trader.updateNetMoney(quantity * coin.getCoinPrice());
                break;
            }
            try {
                // Release the lock and wait for a notification or a timeout
                monitor.wait(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                loggerObj.errorLog(e.getMessage(), e);
            }
        }
    }

    private synchronized void updatePriceTransaction() {
        coin.setCoinPrice(price);
    }

    private synchronized void addVolumeTransaction() {
        coin.updateCoinVolume(volume);
    }

    private void startTransaction() {
        switch (type) {
            case "BUY" -> buyTransaction();
            case "SELL" -> sellTransaction();
            case "ADD_VOLUME" -> addVolumeTransaction();
            case "UPDATE_PRICE" -> updatePriceTransaction();
            default -> loggerObj.debugLog("Invalid Transaction Type");
        }
    }

    @Override
    public void run() {
        startTransaction();
        TransactionStorage.storeTransaction(type,coinCode,walletAddress,getBlockHash());
        latch.countDown();
    }
}
