package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AnalyticsCounter {

    public static final String SYMPTOMS_TXT = "symptoms.txt";

    public static void main(String args[]) throws Exception {
        AnalyticsCounter analyticsCounter = new AnalyticsCounter();
        analyticsCounter.analyseAndWriteResults();
    }

    private void analyseAndWriteResults() throws IOException {
        List<String> listSymptoms = readSymptomsFile();

        Iterator<String> iterator = listSymptoms.iterator();
        String actualSymptoms;
        String countSymptoms = listSymptoms.get(0);
        int repetition = 0;
        FileWriter writer = new FileWriter("resultats.out");

        while (iterator.hasNext()) {
            actualSymptoms = iterator.next();
            if (actualSymptoms.equals(countSymptoms)) {
                repetition++;
            } else {
                writer.write(countSymptoms + " : " + repetition + "\n");
                countSymptoms = actualSymptoms;
                repetition = 1;
            }
        }
        writer.close();
    }

    private List<String> readSymptomsFile() {
        ReadSymptomDataFromFile readSymptomDataFromFile = new ReadSymptomDataFromFile(SYMPTOMS_TXT);
        List<String> symptoms = readSymptomDataFromFile.GetSymptoms();
        Collections.sort(symptoms);
        return symptoms;
    }
}