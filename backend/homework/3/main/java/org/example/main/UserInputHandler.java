package org.example.main;

import org.example.logging.Logger;

import java.util.Scanner;

/**
 * The `UserInputHandler` class handles user input for student-related information.
 */
public class UserInputHandler {

    // Logger instance for logging messages
    Logger loggerObj = new Logger();

    // Scanner instance for reading user input
    Scanner reader = new Scanner(System.in);

    /**
     * Reads and valageates the input for student ID.
     */
    public int readAge() {
        int age = -1;
        while (age == -1) {
            try {
                // Prompt user for student ID
                loggerObj.userPrompt("Enter age : ");
                age = reader.nextInt();

                if (age <= 0) {
                    throw new IllegalArgumentException("age cannot be negative");
                }
            } catch (NumberFormatException e) {
                loggerObj.errorLog("Invalid age", e);
                age = -1;
            } catch (IllegalArgumentException e) {
                loggerObj.errorLog(e.getMessage());
                age = -1;
            }
        }
        return age;
    }

    public double readSalary() {
        double salary = -1;
        while (salary == -1) {
            try {
                // Prompt user for student ID
                loggerObj.userPrompt("Enter your salary : ");
                salary = reader.nextDouble();

                if (salary <= 0) {
                    throw new IllegalArgumentException("Salary cannot be negative");
                }
            } catch (NumberFormatException e) {
                loggerObj.errorLog("Invalid Salary", e);
                salary = -1;
            } catch (IllegalArgumentException e) {
                loggerObj.errorLog(e.getMessage());
                salary = -1;
            }
        }
        return salary;
    }


}