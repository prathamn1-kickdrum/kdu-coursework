package org.example;

import org.example.customexceptions.InvalidDataException;
import org.example.customexceptions.MissingGradeException;

import java.util.ArrayList;
import java.util.List;

/**
 * The `StudentUtility` class provides utility methods for calculating GPAs and retrieving students based on GPA range.
 */
public class StudentUtility {

    // Mapping of grades to corresponding grade points
    private static final Grades gradeObj = new Grades();
    Logger loggerObj = Logger.getLoggerObject();

    /**
     * Calculates the GPA for a set of grades.
     */
    public double getGpa(int id,char[] grades) throws MissingGradeException {
        double totalMarks = 0;
        for (char grade : grades) {
            if(grade == '.') {
                throw new MissingGradeException(id);
            }
            totalMarks += gradeObj.getGradeValue(grade);
        }
        return grades.length==0 ? 0 : totalMarks / grades.length;
    }

    /**
     * Calculates the GPAs for a list of students.
     */
    public List<Double> calculateGPA(int[] studentIdList, char[][] studentsGrades) {
        List<Double> studentGpa = new ArrayList<>();
        try {
            //Task 1 : validating that all students have received grades or not

            if (studentIdList.length != studentsGrades.length) {
                throw new IllegalArgumentException("studentIdList & studentsGrades are out-of-sync. studentIdList.length: " +
                        studentIdList.length + ", studentsGrades.length: " + studentsGrades.length);
            }
            int noOfStudents = studentIdList.length;

            for (int i = 0; i < noOfStudents; i++) {
                studentGpa.add(getGpa(studentIdList[i],studentsGrades[i]));
            }
            return studentGpa;
        }catch (IllegalArgumentException e) {
            loggerObj.errorLog(e.getMessage());
        }catch (MissingGradeException e) {
            throw new InvalidDataException("Invalid Data : Missing grade for student id : "+e.getStudentId());
        }
        return studentGpa;
    }

    /**
     * Retrieves the list of student IDs within the specified GPA range.
     */
    List<Integer> getStudentsByGPA(double lower, double higher, int[] studentListId, char[][] studentsGrades) {
        List<Integer> validStudentIdList = new ArrayList<>();
        try {
            List<Double> studentGpaArr = calculateGPA(studentListId, studentsGrades);
            for (int i = 0; i < studentListId.length; i++) {
                if (studentGpaArr.get(i) >= lower && studentGpaArr.get(i) <= higher) {
                    validStudentIdList.add(studentListId[i]);
                }
            }
        }catch (InvalidDataException e) {
            loggerObj.errorLog("Error calculating GPA : "+e.getMessage(),e);
        }
        return validStudentIdList;
    }
}
