package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.example.logging.Logger;
import org.example.operations.CoinOperation;
import org.example.operations.TraderOperation;
import org.example.userutility.UserInputHandler;
import org.example.userutility.UserUtilityMenu;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final Logger loggerObj = Logger.getLoggerObject();
    private static final Path TRANSACTION_JSON_FILE_PATH = Path.of(
            "src/test/resources/test_transaction_1.json");

    public static ArrayList<String[]> parseCSV(Path fileCsvPath) {
        Logger loggerObj = Logger.getLoggerObject();
        ArrayList<String[]> csvList = new ArrayList<>();
        try {
            Reader csvReader = new FileReader(String.valueOf(fileCsvPath));
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder().build();
            Iterable<CSVRecord> csvRecords = csvFormat.parse(csvReader);
            boolean skipHeader = true;
            for (CSVRecord rec : csvRecords) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                csvList.add(rec.values());
            }
        } catch (IOException e) {
            loggerObj.errorLog(e.getMessage(), e);
        }
        return csvList;
    }

    public static JsonNode parseJsonFile(String fileStringPath) {
        JsonNode jsonTransactions = null;
        Logger loggerObj = Logger.getLoggerObject();
        try {
            File jsonFile = new File(fileStringPath);
            ObjectMapper mapper = new ObjectMapper();
            jsonTransactions = mapper.readValue(jsonFile, JsonNode.class);
        } catch (IOException e) {
            loggerObj.errorLog(e.getMessage(), e);
        }
        return jsonTransactions;
    }

    public static void executeTransactions(JsonNode jsonTransactions, CountDownLatch latch) {
        if(jsonTransactions==null) {
            throw new NullPointerException();
        }
        CoinOperation.loadCoinsData();
        TraderOperation.loadTradersData();

        int numOfThreads = jsonTransactions.size();
        ExecutorService executorService = Executors.newFixedThreadPool(numOfThreads);

        for (JsonNode trRecord : jsonTransactions) {
            executorService.submit(new ExecuteTransaction(trRecord, latch));
            latch.countDown();
        }
        executorService.shutdown();
    }

    private static void coinUserInteraction() {
        UserUtilityMenu userMenu = UserUtilityMenu.getUserUtilityMenuObject();
        UserInputHandler inputHandler = UserInputHandler.getUserInputHandlerObject();
        CoinOperation coinOpObj = new CoinOperation();
        while(true) {
            int operationOp = userMenu.coinMenu();
            if(operationOp==-1) {
                break;
            }
            switch (operationOp) {
                case 1:
                    String coinName = inputHandler.readName("Enter Coin Name");
                    coinOpObj.printCoinDetailsByName(coinName);
                    break;
                case 2:
                    String coinCode = inputHandler.readName("Enter Coin Code (All caps");
                    coinOpObj.printCoinDetailsByCode(coinCode);
                    break;
                case 3:
                    int topN = inputHandler.readNum("Enter N : ");
                    coinOpObj.displayTopCoinsByPrice(topN);
                    break;
                default:
                    loggerObj.errorLog("Wrong Choice. Try Again");
                    break;
            }
        }
    }

    public static void traderUserInteraction() {
        UserInputHandler inputHandler = UserInputHandler.getUserInputHandlerObject();
        UserUtilityMenu userMenu = UserUtilityMenu.getUserUtilityMenuObject();
        TraderOperation traderOpObj = new TraderOperation();
        while(true) {
            int operationOp = userMenu.traderMenu();
            if(operationOp==-1) {
                break;
            }
            String wallet;
            switch (operationOp) {
                case 1:
                    wallet = inputHandler.readName("Enter wallet Address");
                    traderOpObj.displayTraderPortfolio(wallet);
                    break;
                case 2:
                    wallet = inputHandler.readName("Enter wallet Address");
                    traderOpObj.displayProfitOrLoss(wallet);
                    break;
                case 3:
                    traderOpObj.displayTradersTopBottom(5);
                    break;
                default:
                    loggerObj.errorLog("Enter valid operation number");
                    break;
            }
        }

    }

    public static void userInteraction() {
        UserUtilityMenu userMenu = UserUtilityMenu.getUserUtilityMenuObject();
        while(true) {
            int operationOp = userMenu.operationMenu();
            if(operationOp==-1) {
                break;
            }
            switch (operationOp) {
                case 1:
                    coinUserInteraction();
                    break;
                case 2:
                    traderUserInteraction();
                    break;
                default:
                    loggerObj.errorLog("Enter valid operation number");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        try {
            JsonNode jsonTransactions = parseJsonFile(String.valueOf(TRANSACTION_JSON_FILE_PATH));
            int latchCount=0;
            if(jsonTransactions!=null) {
                latchCount = jsonTransactions.size();
            }
            CountDownLatch latch = new CountDownLatch(latchCount);

            executeTransactions(jsonTransactions, latch);
            userInteraction();
            latch.await();
        }catch (NullPointerException e) {
            loggerObj.errorLog("Error in parsing json file",e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            loggerObj.errorLog(e.getMessage(), e);
        }
    }

}