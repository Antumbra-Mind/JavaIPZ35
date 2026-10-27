package org.example;
import java.util.List;
import java.util.Map;


public class TransactionReportGenerator {

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    // самостійна робота
    public static void printExpenseExtremes(Transaction largest, Transaction smallest) {
        System.out.println("Найбільша витрата: " + largest);
        System.out.println("Найменша витрата: " + smallest);
    }

    // самостійна робота
    public static void printStarReport(String title, Map<String, Double> data) {
        System.out.println(title);
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            int stars = (int) (entry.getValue() / 1000);
            System.out.println(entry.getKey() + ": " + "*".repeat(stars) + " (" + entry.getValue() + ")");
        }
    }
}

