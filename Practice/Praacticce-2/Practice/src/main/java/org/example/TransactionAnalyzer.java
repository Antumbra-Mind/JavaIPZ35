package org.example;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        int count = 0;
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

    // самостійна робота
    public static Transaction findLargestExpense(List<Transaction> transactions, LocalDate from, LocalDate to) {
        Transaction result = null;
        for (Transaction t : transactions) {
            LocalDate date = LocalDate.parse(t.getDate(), dateFormatter);
            if (t.getAmount() < 0 && !date.isBefore(from) && !date.isAfter(to)) {
                if (result == null || t.getAmount() < result.getAmount()) {
                    result = t;
                }
            }
        }
        return result;
    }

    // самостійна робота
    public static Transaction findSmallestExpense(List<Transaction> transactions, LocalDate from, LocalDate to) {
        Transaction result = null;
        for (Transaction t : transactions) {
            LocalDate date = LocalDate.parse(t.getDate(), dateFormatter);
            if (t.getAmount() < 0 && !date.isBefore(from) && !date.isAfter(to)) {
                if (result == null || t.getAmount() > result.getAmount()) {
                    result = t;
                }
            }
        }
        return result;
    }

    // самостійна робота
    public static Map<String, Double> sumExpensesByCategory(List<Transaction> transactions) {
        Map<String, Double> result = new HashMap<>();
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                result.merge(t.getDescription(), -t.getAmount(), Double::sum);
            }
        }
        return result;
    }

    // самостійна робота
    public static Map<String, Double> sumExpensesByMonth(List<Transaction> transactions) {
        Map<String, Double> result = new TreeMap<>();
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                LocalDate date = LocalDate.parse(t.getDate(), dateFormatter);
                String key = date.format(DateTimeFormatter.ofPattern("yyyy-MM"));
                result.merge(key, -t.getAmount(), Double::sum);
            }
        }
        return result;
    }
}
