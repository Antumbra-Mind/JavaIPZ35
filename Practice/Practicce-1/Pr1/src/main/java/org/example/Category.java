package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor


public class Category {
    private int id; // Унікальний ідентифікатор категорії
    private String name; // Назва категорії
    // Конструктор класу(Видаленно заради Гетера/Сетера/Нового конструктора)
    // Геттери та сеттери(Видаленно заради Гетера/Сетера/Нового конструктора)
    //Цікаво звичайно як це все скоротило код.
    @Override
    public String toString() {
        return "Категорія{" +
                "id=" + id +
                ", назва='" + name + '\'' +
                '}';
    }
}
