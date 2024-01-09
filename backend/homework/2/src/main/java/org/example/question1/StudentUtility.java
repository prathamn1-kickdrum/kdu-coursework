package org.example.question1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class StudentUtility {

    static HashMap<Character,Integer> gradeMap;

    StudentUtility() {
        gradeMap = new HashMap<>();
        gradeMap.put('A',4);
        gradeMap.put('B',3);
        gradeMap.put('C',2);
    }

    public static double getGpa(char[] grades) {
        double totalMarks=0;
        for (char grade : grades) {
            totalMarks += gradeMap.get(grade);
        }
        System.out.println(totalMarks);
        return totalMarks/grades.length;
    }
    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) {
        int noOfStudents = studentIdList.length;
        double[] studentGpa = new double[noOfStudents];
        for(int i=0;i<noOfStudents;i++) {
            studentGpa[i] = getGpa(studentsGrades[i]);
        }
        return studentGpa;
    }

    ArrayList<Integer> getStudentsByGPA(double lower, double higher, int[] studentListId, char[][] studentsGrades) {
        double[] studentGpas = calculateGPA(studentListId, studentsGrades);
        ArrayList<Integer> validStudentIdList = new ArrayList<>();
        for(int i=0;i<studentListId.length;i++) {
            if(studentGpas[i]>=lower && studentGpas[i]<=higher) {
                validStudentIdList.add(studentListId[i]);
            }
        }
        return validStudentIdList;
    }
}
