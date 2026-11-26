package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


    public abstract class TransactionCSVReader {

        public static List<String> readLines(String source) {
            List<String> lines = new ArrayList<>();
            try {
                URL url = new URL(source);
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(url.openStream(), "UTF-8"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        lines.add(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return lines;
        }

        public static List<Transaction> readTransactions(String filePath) {
            return TransactionCSVParser.parse(readLines(filePath));
        }
    }
