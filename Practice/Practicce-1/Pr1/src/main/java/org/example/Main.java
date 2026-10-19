package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Створення категорій
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        // 2. Створення об'єктів класу Product з вказівкою категорії
        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном...", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);

        // 3. Створення кошика
        Cart cart = new Cart();

        // 4. Додавання товарів до кошика
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.addProduct(product3);

        // 5. Виведення інформації про товари в кошику
        System.out.println(cart);

        // 6. Видалення навушників з кошика
        cart.removeProduct(product3);

        // 7. Виведення оновленої інформації про товари в кошику
        System.out.println("\nПісля видалення товару:");
        System.out.println(cart);

        // 8. Оформлення замовлення
        List<Order> orderHistory = new ArrayList<>();

        if (cart.getProducts().isEmpty()) {
            System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
        } else {
            Order order = new Order(cart);
            System.out.println("\nЗамовлення оформлено:");
            System.out.println(order);

            orderHistory.add(order); // Зберігаємо замовлення в історію
            cart.clear(); // Очищення кошика після оформлення замовлення
        }

        // 9. Виведення історії замовлень
        System.out.println("\nІсторія замовлень:");
        for (Order histOrder : orderHistory) {
            System.out.println(histOrder);
        }

        // 10. Пошук товарів за назвою або категорією (ваш новий код)
        List<Product> allProducts = new ArrayList<>();
        allProducts.add(product1);
        allProducts.add(product2);
        allProducts.add(product3);

        String search = "Ноутбук"; // Назва або категорія для пошуку

        System.out.println("\nРезультати пошуку (" + search + "):");
        for (Product product : allProducts) {
            // Перевіряємо чи збігається назва товару АБО назва категорії
            if (product.getName().equals(search) || product.getCategory().getName().equals(search)) {
                System.out.println(product);
            }
        }
    }
}