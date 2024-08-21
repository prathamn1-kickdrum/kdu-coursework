package org.example.operations;

import org.example.Main;
import org.example.logging.Logger;
import org.example.model.Traders;

import java.nio.file.Path;
import java.util.*;

public class TraderOperation {
    private static final Logger loggerObj = Logger.getLoggerObject();
    private static Map<String, Integer> tradersMap;
    private static ArrayList<Traders> tradersList;
    private static final Path TRADER_CSV_FILE_PATH = Path.of(
            "src/main/resources/traders.csv");

    public static void loadTradersData() {
        tradersMap = new HashMap<>();
        tradersList = new ArrayList<>();
        ArrayList<String[]> tradersArr = Main.parseCSV(TRADER_CSV_FILE_PATH);
        int index = 0;
        for(String[] arr : tradersArr) {
            String firstName = arr[1];
            String lastName = arr[2];
            String phone = arr[3];
            String walletAddress = arr[4];
            tradersList.add(new Traders(firstName,lastName,phone,walletAddress));
            tradersMap.put(walletAddress,index);
            index++;
        }
    }
    public static Traders getTraderByWallet(String walletAddress) {
        if(!tradersMap.containsKey(walletAddress)) {
            loggerObj.debugLog("No trader is present with wallet address : "+walletAddress);
            return null;
        }
        return tradersList.get(tradersMap.get(walletAddress));
    }

    public void displayTraderPortfolio(String walletAddress) {
        Traders trader = getTraderByWallet(walletAddress);
        if(trader!=null) trader.printPortfolio();
    }

    public void displayProfitOrLoss(String walletAddress) {
        Traders trader = getTraderByWallet(walletAddress);
        if(trader==null) return;
        double netIncome = trader.getNetIncome();
        if(netIncome<0) {
            loggerObj.infoLog("Loss : " + Math.abs(netIncome));
        }else {
            loggerObj.infoLog("Profit : " + Math.abs(netIncome));
        }
    }

    public void printTraderList(List<Traders> tradLi) {
        int counter =1;
        for(Traders item : tradLi) {
            loggerObj.infoLog(counter+item.toString());
        }
    }
    public void displayTradersTopBottom(int n) {
        List<Traders> topNTraders = findTopNTraders(tradersList, n, Comparator.comparingDouble(Traders::getNetIncome));
        loggerObj.infoLog("Top "+n+" traders : ");
        printTraderList(topNTraders);
        List<Traders> bottomNTraders = findTopNTraders(tradersList, n, Comparator.comparingDouble(Traders::getNetIncome).reversed());
        loggerObj.infoLog("Bottom "+n+" traders : ");
        printTraderList(bottomNTraders);
    }

    private List<Traders> findTopNTraders(List<Traders> traders, int n, Comparator<Traders> comparator) {
        PriorityQueue<Traders> priorityQueue = new PriorityQueue<>(comparator);
        for (Traders trader : traders) {
            priorityQueue.offer(trader);
            if (priorityQueue.size() > n) {
                priorityQueue.poll();
            }
        }
        return new ArrayList<>(priorityQueue);
    }

}
