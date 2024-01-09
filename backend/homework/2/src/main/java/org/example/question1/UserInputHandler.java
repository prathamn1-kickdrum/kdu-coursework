package org.example.question1;

import org.example.logging.Logger;

import java.util.Scanner;

public class UserInputHandler {

    Logger logger = new Logger();
    Scanner reader = new Scanner(System.in);
    public int readId(int index) {
        int id = -1;
        while (id == -1) {
            try {
                logger.userPrompt("Enter Student Id for " + index + " student : ");
                id = reader.nextInt();
                if (id <= 0) {
                    throw new IllegalArgumentException("Id can't be negative");
                }
            } catch (NumberFormatException e) {
                logger.errorLog("Invalid Student Id", e);
                id=-1;
            } catch (IllegalArgumentException e) {
                logger.errorLog(e.getMessage());
                id=-1;
            }
        }
        return id;
    }

    public char readGrade(int index) {
        char grade = ' ';
        while (grade == ' ') {
            try {
                logger.userPrompt("Enter grade " + index + " : ");
                grade = reader.next().charAt(0);
                if (grade<'A' || grade>'C') {
                    throw new IllegalArgumentException("grade must be A or B or C");
                }
            } catch (NumberFormatException e) {
                logger.errorLog("Invalid Grade", e);
                grade=' ';
            } catch (IllegalArgumentException e) {
                logger.errorLog(e.getMessage());
                grade=' ';
            }
        }
        return grade;
    }
}
