package org.example.userutility;

import org.example.logging.Logger;

import java.util.Scanner;

public class UserUtilityMenu {
    private static final Scanner reader = new Scanner(System.in);
    private static final Logger loggerObj = Logger.getLoggerObject();

    private UserUtilityMenu() {

    }
    private static final UserUtilityMenu menuObject = new UserUtilityMenu();

    public static UserUtilityMenu getUserUtilityMenuObject() {
        return menuObject;
    }
    public int operationMenu() {
        try {
            loggerObj.userPrompt("Enter Option no." +
                    "\n1 -> Coin Operations" +
                    "\n2 -> Trader Operations" +
                    "\n-1 -> Exit\n: ");
            int op = Integer.parseInt(reader.nextLine());
            return op;
        } catch (NumberFormatException e) {
            loggerObj.errorLog("Invalid Operation No. \n", e);
        }
        return -1;
    }

    public int coinMenu() {
        try {
            loggerObj.userPrompt("Enter Option no." +
                    "\n1 -> Details by Name" +
                    "\n2 ->Details by code" +
                    "\n3 -> Top N coins\n" +
                    "-1 -> Exit\n: ");
            int op = Integer.parseInt(reader.nextLine());
            return op;
        } catch (NumberFormatException e) {
            loggerObj.errorLog("Invalid Operation No. \n",e);
        }
        return -1;
    }

    public int traderMenu() {
        try {
            loggerObj.userPrompt("Enter Option no.\n1 -> Trader portfolio\n2 ->Trader profit/loss\n3 -> Top/Bottom 5 traders\n-1 -> Exit\n: ");
            int op = Integer.parseInt(reader.nextLine());
            return op;
        } catch (NumberFormatException e) {
            loggerObj.errorLog("Invalid Operation No. \n",e);
        }
        return -1;
    }
}