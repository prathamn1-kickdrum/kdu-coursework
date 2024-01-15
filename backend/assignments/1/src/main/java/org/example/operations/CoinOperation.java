package org.example.operations;

import org.example.Main;
import org.example.logging.Logger;
import org.example.model.Coins;

import java.nio.file.Path;
import java.util.*;

public class CoinOperation {

    static Logger loggerObj = Logger.getLoggerObject();
    private static final Path COIN_CSV_FILE_PATH = Path.of(
            "src/main/resources/coins.csv");
    private static List<Coins> coinsList;
    private static Map<String,Integer> coinNameMap;
    private static Map<String,Integer> coinCodeMap;

    public static void loadCoinsData() {
        coinsList = new ArrayList<>();
        coinCodeMap = new HashMap<>();
        coinNameMap = new HashMap<>();

        ArrayList<String[]> coinsArr = Main.parseCSV(COIN_CSV_FILE_PATH);
        int index = 0;

        for(String[] arr : coinsArr) {
            int rank = Integer.parseInt(arr[1]);
            String name=arr[2];
            String symbol=arr[3];
            double price = Double.parseDouble(arr[4]);
            long volume = Long.parseLong(arr[5]);
            coinsList.add(new Coins(rank,name,symbol,price,volume));
            coinNameMap.put(name,index);
            coinCodeMap.put(symbol,index);
            index++;
        }
    }

    public void printCoinDetailsByCode(String code) {
        loggerObj.infoLog("Coin Details", getCoinByCode(code));
    }

    public void printCoinDetailsByName(String name) {
        if(coinNameMap.get(name)==null) {
            loggerObj.debugLog("No coin present with Name : "+name);
            return;
        }
        Coins myCoin = coinsList.get(coinNameMap.get(name));
        loggerObj.infoLog("Coin Details : ",myCoin);
    }

    public static Coins getCoinByCode(String code) {
        if(coinCodeMap.get(code)==null) {
            loggerObj.debugLog("No coin present with code : "+code);
            return null;
        }
        return coinsList.get(coinCodeMap.get(code));
    }

    public void displayTopCoinsByPrice(int N) {
        List<Coins> topCoins = coinsList.stream()
                .sorted(Comparator.comparingDouble(Coins::getCoinPrice).reversed())
                .toList();

        int counter = 1;
        for(Coins myCoin : topCoins) {
            loggerObj.infoLog(counter + myCoin.toString());
            if(counter==N) {
                break;
            }
            counter++;
        }
    }

}
