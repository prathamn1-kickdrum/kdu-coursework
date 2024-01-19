package org.example.dataoperations;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.example.logging.Logger;

public class LoadData {

    private LoadData() {}

    public static ArrayList<String[]> parseCSV(Path fileCsvPath) {
        Logger loggerObj = Logger.getLoggerObject();
        ArrayList<String[]> csvList = new ArrayList<>();
        try {
            Reader csvReader = new FileReader(String.valueOf(fileCsvPath));
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder().build();
            Iterable<CSVRecord> csvRecords = csvFormat.parse(csvReader);
            boolean skipHeader = true;
            for (CSVRecord rec : csvRecords) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                csvList.add(rec.values());
            }
        } catch (IOException e) {
            loggerObj.errorLog(e.getMessage(), e);
        }
        return csvList;
    }
}
