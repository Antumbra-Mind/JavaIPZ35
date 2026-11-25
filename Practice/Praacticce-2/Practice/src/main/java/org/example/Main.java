package org.example;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions,monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        String startDate = "01-12-2023";
        String endDate = "31-12-2023";
        Transaction maxExpense = TransactionAnalyzer.findMaxExpenseInPeriod(transactions, startDate, endDate);
        Transaction minExpense = TransactionAnalyzer.findMinExpenseInPeriod(transactions, startDate, endDate);
        TransactionReportGenerator.printMinMaxExpenseReport(startDate, endDate, maxExpense, minExpense);

        Map<String, Double> byCategory = TransactionAnalyzer.calculateExpensesByCategory(transactions);
        TransactionReportGenerator.printExpensesChart("Витрати по категоріях", byCategory);

        Map<String, Double> byMonth = TransactionAnalyzer.calculateExpensesByMonth(transactions);
        TransactionReportGenerator.printExpensesChart("Витрати по місяцях", byMonth);
    }
}
