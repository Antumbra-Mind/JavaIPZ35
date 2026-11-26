package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class TransactionCSVParser {

        public static List<Transaction> parse(List<String> lines) {
            List<Transaction> result = new ArrayList<>();
            for (String line : lines) {
                if (line.isBlank()) continue;
                String[] v = line.split(",");
                result.add(new Transaction(v[0], Double.parseDouble(v[1]), v[2]));
            }
            return result;
        }
    }
