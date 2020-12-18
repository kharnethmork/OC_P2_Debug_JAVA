package com.hemebiotech.analytics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AnalyticsCounter {
/**
 *
 *The filepath of the files.
 */
    public static final String SYMPTOMS_TXT = "src/ressources/symptoms.txt";
    public static final String RESULTATS_TXT = "src/results/resultats.out";

    /**
     *
     *To start the program.
     */

    public static void main(String args[]) throws Exception {

        SymptomsFileReaderAndWriter symptomsFileReaderAndWriter = new SymptomsFileReaderAndWriter();
        AnalyseSymptoms analyseSymptoms = new AnalyseSymptoms();

       List<String> listOfSymptoms = symptomsFileReaderAndWriter.readSymptomsFromFile(SYMPTOMS_TXT);

       Map<String, Integer> mapOfSymptoms = analyseSymptoms.getMapOfSymptoms(listOfSymptoms);

       symptomsFileReaderAndWriter.writeSymptomsInResultsFile(mapOfSymptoms, RESULTATS_TXT);
    }

}