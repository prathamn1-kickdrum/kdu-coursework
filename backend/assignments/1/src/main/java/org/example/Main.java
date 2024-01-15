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
        CoinOperation.loadCoinsData();
        TraderOperation.loadTradersData();

        int NUM_OF_THREADS = jsonTransactions.size();
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);

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
            switch (operationOp) {
                case 1:
                    String coinName = inputHandler.readName("Enter Coin Name");
                    coinOpObj.printCoinDetailsByName(coinName);
                case 2:
                    String coinCode = inputHandler.readName("Enter Coin Code (All caps");
                    coinOpObj.printCoinDetailsByCode(coinCode);
                case 3:
                    int topN = inputHandler.readNum("Enter N : ");
                    coinOpObj.displayTopCoinsByPrice(topN);
                case -1:
                    break;
                default:
                    loggerObj.errorLog("Enter valid operation number");
            }
        }
    }

    public static void traderUserInteraction() {
        UserInputHandler inputHandler = UserInputHandler.getUserInputHandlerObject();
        UserUtilityMenu userMenu = UserUtilityMenu.getUserUtilityMenuObject();
        TraderOperation traderOpObj = new TraderOperation();
        while(true) {
            int operationOp = userMenu.traderMenu();
            String wallet;
            switch (operationOp) {
                case 1:
                    wallet = inputHandler.readName("Enter wallet Address");
                    traderOpObj.displayTraderPortfolio(wallet);
                case 2:
                    wallet = inputHandler.readName("Enter wallet Address");
                    traderOpObj.displayProfitOrLoss(wallet);
                case 3:
                    traderOpObj.displayTradersTopBottom(5);
                case -1:
                    break;
                default:
                    loggerObj.errorLog("Enter valid operation number");
            }
        }

    }

    public static void userInteraction() {
        UserUtilityMenu userMenu = UserUtilityMenu.getUserUtilityMenuObject();
        while(true) {
            int operationOp = userMenu.operationMenu();
            switch (operationOp) {
                case 1:
                    coinUserInteraction();
                case 2:
                    traderUserInteraction();
                case -1:
                    break;
                default:
                    loggerObj.errorLog("Enter valid operation number");
            }
        }
    }

    public static void main(String[] args) {
        try {
            JsonNode jsonTransactions = parseJsonFile(String.valueOf(TRANSACTION_JSON_FILE_PATH));
            int LATCH_COUNT = jsonTransactions.size();
            CountDownLatch latch = new CountDownLatch(LATCH_COUNT);

            executeTransactions(jsonTransactions, latch);
            userInteraction();
            latch.await();
        } catch (InterruptedException e) {
            loggerObj.errorLog(e.getMessage(), e);
        }
    }

}