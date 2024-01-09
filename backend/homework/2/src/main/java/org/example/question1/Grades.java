package org.example.question1;

import org.example.logging.Logger;

import java.util.HashMap;
import java.util.Map;

public class Grades {
    private static final Map<Character,Integer> gradeMap= new HashMap<>();
    Logger loggerObj = new Logger();

    public int getGradeValue(char grade) {
        if(isGradePresent(grade)) {
            loggerObj.errorLog("Grade " + grade + " not present.");
        }
        return gradeMap.get(grade);
    }

    private boolean isGradePresent(char grade) {
        return !gradeMap.containsKey(grade);
    }
    public Grades() {
        gradeMap.put('A',4);
        gradeMap.put('B',3);
        gradeMap.put('C',2);
    }

    public void setGradeValue(char grade,int value) {
        if(isGradePresent(grade)) {
            gradeMap.put(grade,value);
        }else {
            gradeMap.replace(grade,value);
        }

    }

    public void removeGrade(char grade) {
        if(!isGradePresent(grade)) {
            loggerObj.errorLog("grade "+grade+" not present. Couldn't remove it.");
        }
        gradeMap.remove(grade);
    }

}
