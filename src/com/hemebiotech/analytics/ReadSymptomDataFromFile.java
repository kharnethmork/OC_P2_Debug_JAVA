package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple brute force implementation.
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

    private final String filePath;

    /**
     * Constructor with filepath parameter.
     * @param filePath a full or partial path to file with symptom strings in it, one per line.
     */
    public ReadSymptomDataFromFile(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<String> getSymptoms() {
        List<String> result = new ArrayList<>();

        if (filePath != null) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                String line = reader.readLine();

                while (line != null) {
                    result.add(line);
                    line = reader.readLine();
                }
                reader.close();
            } catch (FileNotFoundException f) {
                System.out.println("File not found with this specified path : " + filePath);
                System.out.println(f.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

}
