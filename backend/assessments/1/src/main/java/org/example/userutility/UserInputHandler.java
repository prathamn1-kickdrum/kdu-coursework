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
}
