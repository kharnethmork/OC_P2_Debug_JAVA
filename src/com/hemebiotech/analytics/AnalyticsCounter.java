package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * To read, analyse the list of symptoms  and count them.
 */
public class AnalyticsCounter {

    public static final String SYMPTOMS_TXT = "src/ressources/symptoms.txt";
    public static final String RESULTATS_TXT = "src/results/resultats.out";

    /**
     * Read and sort the symptom's list.
     *
     * @param filepath The path of the file symptoms.
     * @return Return value symptoms and finish the method.
     */
    public List<String> readSymptomsFromFile(String filepath) {

        ReadSymptomDataFromFile readSymptomDataFromFile = new ReadSymptomDataFromFile(filepath);
        List<String> symptoms = readSymptomDataFromFile.GetSymptoms();
        Collections.sort(symptoms);
        return symptoms;
    }

    /**
     * To analyse and count the symptoms.
     *
     * @param listOfSymptoms List of symptoms sort by alphabetic order.
     * @return Return value mapOfSymptoms and finish the method.
     */
    public Map<String, Integer> getMapOfSymptoms(List<String> listOfSymptoms) {

        Collections.sort(listOfSymptoms);

        Map<String, Integer> mapOfSymptoms = new LinkedHashMap<>();

        for (String symptom : listOfSymptoms) {
            if (!mapOfSymptoms.containsKey(symptom)) {
                mapOfSymptoms.put(symptom, 0);
            }
        }
        for (String symptom : listOfSymptoms) {
            mapOfSymptoms.replace(symptom, mapOfSymptoms.get(symptom) + 1);
        }
        return mapOfSymptoms;
    }

    /**
     * Delete the results file if exists and write a new one.
     *
     * @param mapOfSymptoms List of symptoms under map format.
     * @param filePath      Path to output file.
     */
    public void writeSymptomsInResultsFile(Map<String, Integer> mapOfSymptoms, String filePath) {

        FileWriter writer;
        try {
            writer = new FileWriter(filePath);
            for (Map.Entry<String, Integer> entry : mapOfSymptoms.entrySet()) {
                writer.write(entry.getKey() + " : " + entry.getValue() + " \n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("A problem occurred when you attempt to write output file : " + filePath);
            System.out.println(e.getMessage());
        }
    }

    /**
     * To start the program.
     * @param args The argument is a String board.
     */
    public static void main(String[] args) {

        AnalyticsCounter analyticsCounter = new AnalyticsCounter();

        analyticsCounter.writeSymptomsInResultsFile(
                analyticsCounter.getMapOfSymptoms(
                        analyticsCounter.readSymptomsFromFile(SYMPTOMS_TXT)
                ), RESULTATS_TXT);
    }
}