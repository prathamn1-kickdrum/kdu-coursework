package org.example.question1;

import java.util.ArrayList;
import java.util.List;

/**
 * The `StudentUtility` class provides utility methods for calculating GPAs and retrieving students based on GPA range.
 */
public class StudentUtility {

    // Mapping of grades to corresponding grade points
    private static final Grades gradeObj = new Grades();

    /**
     * Constructor to initialize the gradeMap.
     */

    /**
     * Calculates the GPA for a set of grades.
     */
    public static double getGpa(char[] grades) {
        double totalMarks = 0;
        for (char grade : grades) {
            totalMarks += gradeObj.getGradeValue(grade);
        }
        return totalMarks / grades.length;
    }

    /**
     * Calculates the GPAs for a list of students.
     */
    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) {
        int noOfStudents = studentIdList.length;
        double[] studentGpa = new double[noOfStudents];
        for (int i = 0; i < noOfStudents; i++) {
            studentGpa[i] = getGpa(studentsGrades[i]);
        }
        return studentGpa;
    }

    /**
     * Retrieves the list of student IDs within the specified GPA range.
     */
    List<Integer> getStudentsByGPA(double lower, double higher, int[] studentListId, char[][] studentsGrades) {
        double[] studentGpaArr = calculateGPA(studentListId, studentsGrades);
        List<Integer> validStudentIdList = new ArrayList<>();
        for (int i = 0; i < studentListId.length; i++) {
            if (studentGpaArr[i] >= lower && studentGpaArr[i] <= higher) {
                validStudentIdList.add(studentListId[i]);
            }
        }
        return validStudentIdList;
    }
}
