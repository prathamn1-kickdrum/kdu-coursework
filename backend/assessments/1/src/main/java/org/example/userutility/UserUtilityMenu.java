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
            loggerObj.userPrompt("""
                Enter Option no.
                    1 -> Team Operations
                    2 -> Season Operations
                    -1 -> Exit : """);
            int op = Integer.parseInt(reader.nextLine());
            return op;
        } catch (NumberFormatException e) {
            loggerObj.errorLog("Invalid Operation No. \n", e);
        }
        return -1;
    }

    public int teamMenu() {
        try {
            loggerObj.userPrompt("""
                Enter Option no.
                    1 -> Bowlers with 40 wickets
                    2 -> Highest wicket taker & run scorer
                    -1 -> Exit: """);
            int op = Integer.parseInt(reader.nextLine());
            return op;
        } catch (NumberFormatException e) {
            loggerObj.errorLog("Invalid Operation No. \n",e);
        }
        return -1;
    }

    public int seasonMenu() {
        try {
            loggerObj.userPrompt("""
                Enter Option no.
                    1 -> Top 3 wicket takers
                    2 -> Top 3 run scorer
                    3 -> Create Fixtures
                    -1 -> Exit: """);
            int op = Integer.parseInt(reader.nextLine());
            return op;
        } catch (NumberFormatException e) {
            loggerObj.errorLog("Invalid Operation No. \n",e);
        }
        return -1;
    }
}
