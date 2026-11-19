import org.example.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.example.TransactionCSVReader;

public class TransactionCSVReaderTest {
    @TempDir
    Path tempDir;

    @Test
    public void testReadTransactions() throws IOException {
        // Створюємо тимчасовий CSV-файл
        Path file = tempDir.resolve("test.csv");

        String csvData = "01-01-2024,1000.0,Зарплата\n" + "02-01-2024,-500.0,Продукти\n" +  "03-01-2024,-200.0,Аптека";
        Files.writeString(file, csvData);

        // Передаємо шлях до CSV-файлу
        String filePath = file.toUri().toURL().toString();

        // Викликаємо метод читання
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        // Перевіряємо кількість транзакцій
        Assertions.assertEquals(3, transactions.size());

        // Перевіряємо першу транзакцію
        Assertions.assertEquals("01-01-2024", transactions.get(0).getDate());
        Assertions.assertEquals(1000.0,transactions.get(0).getAmount());
        Assertions.assertEquals("Зарплата",transactions.get(0).getDescription());

        // Перевіряємо другу транзакцію
        Assertions.assertEquals(-500.0, transactions.get(1).getAmount());

        // Перевіряємо третю транзакцію
        Assertions.assertEquals("Аптека", transactions.get(2).getDescription());

    }
}
