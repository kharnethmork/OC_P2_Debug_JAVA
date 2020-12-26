package com.hemebiotech.analytics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AnalyticsCounter {

    /**
     * The filepath of the files.
     */
    public static final String SYMPTOMS_TXT = "src/ressources/symptoms.txt";
    public static final String RESULTATS_TXT = "src/results/resultats.out";

    /**
     * Read and sort the symptom's list
     */
    public static class SymptomsFileReaderAndWriter {

        public List<String> readSymptomsFromFile(String filepath) {

            ReadSymptomDataFromFile readSymptomDataFromFile = new ReadSymptomDataFromFile(filepath);
            List<String> symptoms = readSymptomDataFromFile.GetSymptoms();
            Collections.sort(symptoms);
            return symptoms;
        }

        /**
         * Delete the results file if exists and write a new one.
         *
         * @param mapOfSymptoms
         * @param filepath
         * @throws IOException
         */
        public void writeSymptomsInResultsFile(Map<String, Integer> mapOfSymptoms, String filepath) throws IOException {

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

    public static class AnalyseSymptoms {

        /**
         * To analyse and count the symptoms.
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
    }

    /**
     * To start the program.
     */
    public static void main(String args[]) throws Exception {

       SymptomsFileReaderAndWriter symptomsFileReaderAndWriter = new SymptomsFileReaderAndWriter();
       AnalyseSymptoms analyseSymptoms = new AnalyseSymptoms();

        List<String> listOfSymptoms = symptomsFileReaderAndWriter.readSymptomsFromFile(SYMPTOMS_TXT);

        Map<String, Integer> mapOfSymptoms = analyseSymptoms.getMapOfSymptoms(listOfSymptoms);

        symptomsFileReaderAndWriter.writeSymptomsInResultsFile(mapOfSymptoms, RESULTATS_TXT);
    }

}