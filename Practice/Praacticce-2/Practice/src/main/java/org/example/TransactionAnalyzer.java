package org.example;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.TreeMap;

public abstract class TransactionAnalyzer {


    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        int count = 0;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }
    private static boolean isInPeriod(Transaction transaction, LocalDate start, LocalDate end) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
        return !date.isBefore(start) && !date.isAfter(end);
    }

    public static Transaction findMaxExpenseInPeriod(List<Transaction> transactions, String startDate, String endDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate start = LocalDate.parse(startDate, dateFormatter);
        LocalDate end = LocalDate.parse(endDate, dateFormatter);

        Transaction max = null;
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0 && isInPeriod(transaction, start, end)) {
                // витрати від'ємні, тому найбільша витрата - це найменше число
                if (max == null || transaction.getAmount() < max.getAmount()) {
                    max = transaction;
                }
            }
        }
        return max;
    }

    public static Transaction findMinExpenseInPeriod(List<Transaction> transactions, String startDate, String endDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate start = LocalDate.parse(startDate, dateFormatter);
        LocalDate end = LocalDate.parse(endDate, dateFormatter);

        Transaction min = null;
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0 && isInPeriod(transaction, start, end)) {
                if (min == null || transaction.getAmount() > min.getAmount()) {
                    min = transaction;
                }
            }
        }
        return min;
    }

    public static Map<String, Double> calculateExpensesByCategory(List<Transaction> transactions) {
        Map<String, Double> result = new TreeMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                String category = transaction.getDescription();
                double sum = result.getOrDefault(category, 0.0);
                result.put(category, sum - transaction.getAmount());
            }
        }
        return result;
    }

    public static Map<String, Double> calculateExpensesByMonth(List<Transaction> transactions) {
        Map<String, Double> result = new TreeMap<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
                String month = date.format(DateTimeFormatter.ofPattern("yyyy-MM"));
                double sum = result.getOrDefault(month, 0.0);
                result.put(month, sum - transaction.getAmount());
            }
        }
        return result;
    }
}