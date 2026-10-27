package org.example;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        // самостійна робота
        LocalDate from = LocalDate.of(2024, 1, 1);
        LocalDate to = LocalDate.of(2024, 12, 31);
        TransactionReportGenerator.printExpenseExtremes(
                TransactionAnalyzer.findLargestExpense(transactions, from, to),
                TransactionAnalyzer.findSmallestExpense(transactions, from, to));
        TransactionReportGenerator.printStarReport("Витрати по категоріях:",
                TransactionAnalyzer.sumExpensesByCategory(transactions));
        TransactionReportGenerator.printStarReport("Витрати по місяцях:",
                TransactionAnalyzer.sumExpensesByMonth(transactions));
    }
}
