package org.example;


import java.util.Scanner;

/**
 * The `UserInputHandler` class handles user input for student-related information.
 */
public class UserInputHandler {

    // Logger instance for logging messages
    Logger loggerObj = Logger.getLoggerObject();

    // Scanner instance for reading user input
    Scanner reader = new Scanner(System.in);

    /**
     * Reads and validates the input for student ID.
     */
    public int readNumber() {
        int num = -1;
        while (num == -1) {
            try {
                // Prompt user for student ID
                loggerObj.userPrompt("Enter a positive number : ");
                num = reader.nextInt();

                // Validate that the num is a positive integer
                if (num <= 0) {
                    throw new IllegalArgumentException("num cannot be negative");
                }
            } catch (NumberFormatException e) {
                loggerObj.errorLog("Invalid Number", e);
                num = -1;
            } catch (IllegalArgumentException e) {
                loggerObj.errorLog(e.getMessage());
                num = -1;
            }
        }
        return num;
    }
}