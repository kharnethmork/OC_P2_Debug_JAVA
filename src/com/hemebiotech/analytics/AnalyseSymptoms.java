package com.hemebiotech.analytics;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AnalyseSymptoms {

    /**
     *
     * To analyse and count the symptoms.
     */
    public Map<String, Integer> getMapOfSymptoms (List<String> listOfSymptoms) {

        Collections.sort(listOfSymptoms);

        Map<String, Integer> mapOfSymptoms = new LinkedHashMap<>();

        for (String symptom: listOfSymptoms) {
            if (!mapOfSymptoms.containsKey(symptom)) {
                mapOfSymptoms.put(symptom, 0);
            }
        }
        for (String symptom: listOfSymptoms) {
            mapOfSymptoms.replace(symptom, mapOfSymptoms.get(symptom) + 1);
        }
        return mapOfSymptoms;
    }
}
