package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Категорії
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        // 2. Каталог товарів
        List<Product> catalog = new ArrayList<>();
        catalog.add(new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics));
        catalog.add(new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones));
        catalog.add(new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories));

        // 3. Кошик та історія замовлень
        Cart cart = new Cart();
        List<Order> orderHistory = new ArrayList<>();

        // 4. Головне меню
        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Переглянути кошик");
            System.out.println("4 - Видалити товар з кошика");
            System.out.println("5 - Зробити замовлення");
            System.out.println("6 - Історія замовлень");
            System.out.println("7 - Пошук товарів");
            System.out.println("0 - Вийти");

            int choice = readInt(scanner, "Ваш вибір: ");

            switch (choice) {
                case 1:
                    printProducts(catalog);
                    break;

                case 2: {
                    int id = readInt(scanner, "Введіть ID товару для додавання до кошика: ");
                    Product product = findById(catalog, id);
                    if (product == null) {
                        System.out.println("Товар з таким ID не знайдено");
                    } else {
                        cart.addProduct(product);
                        System.out.println("Додано: " + product.getName());
                    }
                    break;
                }

                case 3:
                    System.out.println(cart);
                    break;

                case 4: {
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній, видаляти нічого.");
                        break;
                    }
                    System.out.println(cart);
                    int id = readInt(scanner, "Введіть ID товару для видалення з кошика: ");
                    Product product = findById(cart.getProducts(), id);
                    if (product == null) {
                        System.out.println("Такого товару немає в кошику");
                    } else {
                        cart.removeProduct(product);
                        System.out.println("Видалено: " + product.getName());
                    }
                    break;
                }

                case 5:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        System.out.println("Замовлення оформлено:");
                        System.out.println(order);
                        orderHistory.add(order);
                        cart.clear();
                    }
                    break;

                case 6:
                    if (orderHistory.isEmpty()) {
                        System.out.println("Історія замовлень порожня.");
                    } else {
                        System.out.println("Історія замовлень:");
                        for (int i = 0; i < orderHistory.size(); i++) {
                            System.out.println("--- Замовлення №" + (i + 1) + " ---");
                            System.out.println(orderHistory.get(i));
                        }
                    }
                    break;

                case 7: {
                    System.out.print("Введіть назву товару або категорії: ");
                    String query = scanner.nextLine().trim().toLowerCase();
                    boolean found = false;
                    for (Product product : catalog) {
                        if (product.getName().toLowerCase().contains(query)
                                || product.getCategory().getName().toLowerCase().contains(query)) {
                            System.out.println(product);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Нічого не знайдено.");
                    }
                    break;
                }

                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;

                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }

    // Безпечне зчитування числа: програма не падає, якщо ввести літери
    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Потрібно ввести число.");
            }
        }
    }

    private static void printProducts(List<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static Product findById(List<Product> products, int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}