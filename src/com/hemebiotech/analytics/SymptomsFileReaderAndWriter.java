package com.hemebiotech.analytics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SymptomsFileReaderAndWriter {

    public List<String> readSymptomsFromFile (String filepath) {

        ReadSymptomDataFromFile readSymptomDataFromFile = new ReadSymptomDataFromFile(filepath);
        List<String> symptoms = readSymptomDataFromFile.GetSymptoms();
        Collections.sort(symptoms);
        return symptoms;
    }

    public void writeSymptomsInResultsFile (Map<String, Integer> mapOfSymptoms, String filepath) throws IOException {

        File resultsFile = new File(filepath);
        resultsFile.exists();
        if (resultsFile.exists()) {
            resultsFile.delete();
        }

        FileWriter writer = new FileWriter(filepath);
        for (Map.Entry<String, Integer> entry : mapOfSymptoms.entrySet()) {
            writer.write(entry.getKey() + " : " + entry.getValue() + " \n ");
        }

        writer.close();

    }
}
