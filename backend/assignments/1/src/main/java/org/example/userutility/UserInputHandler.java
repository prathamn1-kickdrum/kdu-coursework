package org.example.userutility;


import org.example.logging.Logger;

import java.util.Scanner;

/**
 * The `UserInputHandler` class handles user input for getting statistical information.
 */
public class UserInputHandler {

    private UserInputHandler() {

    }
    private static final UserInputHandler inputHandlerObject = new UserInputHandler();

    public static UserInputHandler getUserInputHandlerObject() {
        return inputHandlerObject;
    }

    // Logger instance for logging messages
    Logger loggerObj = Logger.getLoggerObject();

    // Scanner instance for reading user input
    Scanner reader = new Scanner(System.in);

    public String readName(String msg) {
        String name = "";
        while (name.isEmpty()) {
            loggerObj.userPrompt(msg);
            name = reader.nextLine();
        }
        return name;
    }

    public int readNum(String msg) {
        int num = -1;
        while (num == -1) {
            try {
                // Prompt user for student ID
                loggerObj.userPrompt(msg);
                num = reader.nextInt();

                if (num <= 0) {
                    throw new IllegalArgumentException("num cannot be negative");
                }
            } catch (NumberFormatException e) {
                loggerObj.errorLog("Invalid number format", e);
                reader.nextLine();
                num = -1;
            } catch (IllegalArgumentException e) {
                loggerObj.errorLog(e.getMessage());
                reader.nextLine();
                num = -1;
            }
        }
        return num;
    }


}
