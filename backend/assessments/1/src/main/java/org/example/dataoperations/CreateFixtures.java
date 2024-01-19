package org.example.dataoperations;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;


import org.example.model.Team;
import org.example.logging.*;;

public class CreateFixtures {

    static final String TIME_1 = " 6:30 PM";
    static final String TIME_2 = " 9:30 PM";

    private CreateFixtures() {}

    public static void generateMatches() {
        Logger loggerObj = Logger.getLoggerObject();
        try (FileWriter writer = new FileWriter("src/main/resources/fixtures.csv")) {
            LocalDate currDate = LocalDate.of(2024, 1, 18);
            int matchNo=0;
            int ct=1;
            for(Team tm1 : Team.values()) {
                for(Team tm2 : Team.values()) {
                    String time="";
                    if(ct==1) {
                        time = TIME_1;
                        ct=2;
                    }else if(ct==2) {
                        time=TIME_2;
                        currDate = currDate.plusDays(1);
                        ct=1;
                    }
                    writer.append(String.format("%s,%d,%s,%s,%s %n",
                        currDate+time, matchNo, tm1, tm2, tm2+"_home"));
                }
            } 
            loggerObj.debugLog("Fixtures generated successfully. Saved to Resources Folder");
        } catch (IOException e) {
            loggerObj.errorLog(e.getMessage());
        }   
    }
}
