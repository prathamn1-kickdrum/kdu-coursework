package org.example.question1;

import org.example.logging.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void printStudentIdRange(ArrayList<Integer> studentIdList,Logger logger) {

        for(int id : studentIdList) {
            System.out.print(id+" ");
        }
        logger.debugLog("Student Ids fetched Successfully");

    }

    public  static void printStudentGPA(double[] gpaList,Logger logger) {
        for(double gpa : gpaList) {
            System.out.print(gpa+" ");
        }
        logger.debugLog("Student GPA fetched Successfully");
    }

    public static void main(String[] args) {

        StudentUtility studentUtilityObject = new StudentUtility();
        UserInputHandler userInput = new UserInputHandler();
        Scanner reader =new Scanner(System.in);
        Logger logger = new Logger();


        logger.userPrompt("Enter no. of students : ");
        int noOfStudents = reader.nextInt();
        int[] studentIdList = new int[noOfStudents];
        char[][] studentGrades = new char[noOfStudents][];
        for(int i=0;i<noOfStudents;i++) {
            int id = userInput.readId(i+1);
            logger.userPrompt("Enter no. of grades : ");
            int noOfGrades = reader.nextInt();
            studentGrades[i] = new char[noOfGrades];
            for(int j=0;j<noOfGrades;j++) {
                studentGrades[i][j] = userInput.readGrade(j+1);
            }
        }
        printStudentGPA(StudentUtility.calculateGPA(studentIdList,studentGrades),logger);
        try {
            logger.userPrompt("Enter the lower cap for GPA: ");
            double lowerCap = reader.nextDouble();
            logger.userPrompt("Enter the lower cap for GPA: ");
            double higherCap = reader.nextDouble();

            if(lowerCap<0 || higherCap<0 || lowerCap>higherCap) {
                throw new IllegalArgumentException("Invalid Ranges!");
            }

            printStudentIdRange(studentUtilityObject.getStudentsByGPA(lowerCap,higherCap,studentIdList,studentGrades),logger);

        }catch (IllegalArgumentException e) {
            logger.errorLog(e.getMessage());
        }





    }
}
