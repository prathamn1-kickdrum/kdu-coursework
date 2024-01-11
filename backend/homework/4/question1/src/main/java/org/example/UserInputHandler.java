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
    public int readId(int index) {
        int id = -1;
        while (id == -1) {
            try {
                // Prompt user for student ID
                loggerObj.userPrompt("Enter Student ID for student " + index + ": ");
                id = reader.nextInt();

                // Validate that the ID is a positive integer
                if (id <= 0) {
                    throw new IllegalArgumentException("ID cannot be negative");
                }
            } catch (NumberFormatException e) {
                loggerObj.errorLog("Invalid Student ID", e);
                id = -1;
            } catch (IllegalArgumentException e) {
                loggerObj.errorLog(e.getMessage());
                id = -1;
            }
        }
        return id;
    }

    /**
     * Reads and validates the input for student grade.
     */
    public char readGrade(int index) {
        char grade = ' ';
        while (grade == ' ') {
            try {
                // Prompt user for student grade
                loggerObj.userPrompt("Enter grade " + index + ": ");
                grade = reader.next().charAt(0);

                // Validate that the grade is 'A', 'B', or 'C'
                if ((grade < 'A' || grade > 'C') && grade != '.') {
                    throw new IllegalArgumentException("Grade must be 'A', 'B', or 'C'");
                }
            } catch (NumberFormatException e) {
                loggerObj.errorLog("Invalid Grade", e);
                grade = ' ';
            } catch (IllegalArgumentException e) {
                loggerObj.errorLog(e.getMessage());
                grade = ' ';
            }
        }
        return grade;
    }
}
