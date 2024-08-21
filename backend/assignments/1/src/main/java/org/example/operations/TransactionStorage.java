package org.example.operations;

import org.example.model.Transaction;

import java.util.HashMap;
import java.util.Map;

public class TransactionStorage
{
    private static final Map<String,Transaction> transactionMap = new HashMap<>();

    private TransactionStorage() {}
    public static void storeTransaction(String type, String coinCode ,String walletAddress,String hash) {
        transactionMap.put(hash,new Transaction(type,coinCode,walletAddress,hash));
    }

    public static Transaction getTransactionHistory(String hash) {
        return transactionMap.get(hash);
    }
}
