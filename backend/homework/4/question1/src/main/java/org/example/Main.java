package org.example;

import org.example.customexceptions.InvalidDataException;

import java.util.List;
import java.util.Scanner;

/**
 * The `Main` class provides the main entry point for student information retrieval and GPA calculations.
 */
public class Main {

    /**
     * Prints the student IDs in the provided list along with a success message.
     */
    public static void printStudentIdRange(List<Integer> studentIdList) {
        Logger loggerObj = Logger.getLoggerObject();
        for (int id : studentIdList) {
            loggerObj.debugLog(String.valueOf(id));
        }
        loggerObj.debugLog("Student IDs fetched successfully");
    }

    /**
     * Prints the student GPAs in the provided list along with a success message.
     */
    public static void printStudentGPA(List<Double> gpaList) {
        Logger loggerObj = Logger.getLoggerObject();
        for (double gpa : gpaList) {
            loggerObj.debugLog(String.valueOf(gpa));
        }
        loggerObj.debugLog("Student GPA fetched successfully");
    }

    /**
     * The main method for collecting user input, calculating GPAs, and retrieving student IDs within a GPA range.
     */
    public static void main(String[] args) {
        StudentUtility studentUtilityObject = new StudentUtility();
        UserInputHandler userInput = new UserInputHandler();
        Scanner reader = new Scanner(System.in);
        Logger loggerObj = Logger.getLoggerObject();

        loggerObj.userPrompt("Enter the number of students: ");
        int noOfStudents = reader.nextInt();

        int[] studentIdList = new int[noOfStudents];
        char[][] studentGrades = new char[noOfStudents][];

        // Collect student IDs and grades from user input
        for (int i = 0; i < noOfStudents; i++) {
            int id = userInput.readId(i + 1);
            studentIdList[i] = id;

            loggerObj.userPrompt("Enter the number of grades for student " + id + ": ");
            int noOfGrades = reader.nextInt();
            studentGrades[i] = new char[noOfGrades];

            for (int j = 0; j < noOfGrades; j++) {
                studentGrades[i][j] = userInput.readGrade(j + 1);
            }
        }



        try {
            // Calculate and print student GPAs
            List<Double> gpaList = studentUtilityObject.calculateGPA(studentIdList, studentGrades);
            printStudentGPA(gpaList);
            loggerObj.userPrompt("Enter the lower cap for GPA: ");
            double lowerCap = reader.nextDouble();
            loggerObj.userPrompt("Enter the higher cap for GPA: ");
            double higherCap = reader.nextDouble();

            // Validate GPA range
            if (lowerCap < 0 || higherCap < 0 || lowerCap > higherCap) {
                throw new IllegalArgumentException("Invalid GPA Range!");
            }

            printStudentIdRange(studentUtilityObject.getStudentsByGPA(lowerCap, higherCap, studentIdList, studentGrades));

        } catch (InvalidDataException e) {
            loggerObj.errorLog("Error calculating GPA : "+e.getMessage(),e);
        } catch (IllegalArgumentException e) {
            loggerObj.errorLog(e.getMessage());
        }
    }
}
