package org.example;

import java.util.List;
import java.util.Map;

public abstract class TransactionReportGenerator {

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
    public static void printMinMaxExpenseReport(String startDate, String endDate, Transaction max, Transaction min) {
        System.out.println("Витрати за період " + startDate + " - " + endDate + ":");
        if (max == null || min == null) {
            System.out.println("Витрат за цей період немає");
            return;
        }
        System.out.println("Найбільша: " + max.getDescription() + ": " + max.getAmount());
        System.out.println("Найменша: " + min.getDescription() + ": " + min.getAmount());
    }

    public static void printExpensesChart(String title, Map<String, Double> expenses) {
        System.out.println(title + " (1 * = 1000 грн):");
        for (Map.Entry<String, Double> entry : expenses.entrySet()) {
            int starsCount = (int) Math.round(entry.getValue() / 1000);
            String stars = "";
            for (int i = 0; i < starsCount; i++) {
                stars += "*";
            }
            System.out.println(entry.getKey() + " | " + stars + " " + entry.getValue());
        }
    }
}