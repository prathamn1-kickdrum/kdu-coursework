package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * The Grades class manages a mapping of grades to their corresponding values.
 * It provides methods to retrieve, update, and remove grade values.
 */
public class Grades {

    // A map to store the mapping of grades to their values
    private static final Map<Character, Integer> gradeMap = new HashMap<>();

    // Logger instance for logging error messages
    private final Logger loggerObj = Logger.getLoggerObject();

    /**
     * Constructor for the Grades class. Initializes the gradeMap with default values.
     */
    public Grades() {
        gradeMap.put('A', 4);
        gradeMap.put('B', 3);
        gradeMap.put('C', 2);
    }

    /**
     * Retrieves the value associated with a given grade.
     * If the grade is not present, logs an error and returns 0.
     */
    public int getGradeValue(char grade) {
        if (isGradePresent(grade)) {
            loggerObj.errorLog("Grade " + grade + " not present.");
            return 0;
        }
        return gradeMap.get(grade);
    }

    /**
     * Updates or adds a grade and its corresponding value in the gradeMap.
     */
    public void setGradeValue(char grade, int value) {
        if (isGradePresent(grade)) {
            gradeMap.put(grade, value);
        } else {
            gradeMap.replace(grade, value);
        }
    }

    /**
     * Removes a grade from the gradeMap.
     * If the grade is not present, logs an error.
     */
    public void removeGrade(char grade) {
        if (!isGradePresent(grade)) {
            loggerObj.errorLog("Grade " + grade + " not present. Couldn't remove it.");
        }
        gradeMap.remove(grade);
    }

    /**
     * Checks if a grade is present in the gradeMap.
     * Returns true if the grade is present, false otherwise.
     */
    private boolean isGradePresent(char grade) {
        return !gradeMap.containsKey(grade);
    }
}
