package org.example;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calc = new Calculator();

        try {
            System.out.print("Введіть перше число: ");
            double a = Double.parseDouble(scanner.nextLine());

            System.out.print("Введіть друге число: ");
            double b = Double.parseDouble(scanner.nextLine());

            System.out.print("Введіть операцію (+, -, *, /, sqrt): ");
            String op = scanner.nextLine();

            double result;
            switch (op) {
                case "+":
                    result = calc.add(a, b);
                    break;
                case "-":
                    result = calc.subtract(a, b);
                    break;
                case "*":
                    result = calc.multiply(a, b);
                    break;
                case "/":
                    result = calc.divide(a, b);
                    break;
                case "sqrt":
                    result = calc.sqrt(a); // корінь з першого числа
                    break;
                default:
                    throw new InvalidInputException("Невідома операція: " + op);
            }
            System.out.println("Результат: " + result);

        } catch (NumberFormatException e) {
            System.out.println("Помилка: введіть число, а не текст!");
        } catch (ArithmeticException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("Некоректні дані: " + e.getMessage());
        } finally {
            System.out.println("Обробку запиту завершено.");
        }
    }
}

