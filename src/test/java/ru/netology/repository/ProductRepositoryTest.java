package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exception.NotFoundException;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();

    private Book first = new Book(1,"Война и мир",500,"Лев Толстой");
    private Smartphone second = new Smartphone(2,"Iphone 11",50000, "Apple");
    private Book third = new Book(3,"Преступление и наканание",500,"Федор Достоевский");
    private Smartphone fourth = new Smartphone(4,"Samsung A30",30000, "Samsung");
    private Book fifth = new Book(5,"Идиот",500,"Федор Достоевский");

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
    }

    @Test
    public void shouldRemoveById() {

        repository.removeById(3);

        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{first,second,fourth,fifth};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFoundException() {

        int idToRemove = 50;

        NotFoundException ex = new NotFoundException("Element with id: " + idToRemove + " not found");

        assertThrows(NotFoundException.class, () -> repository.removeById(idToRemove));

        ex.printStackTrace();

        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{first,second,third,fourth,fifth};

        assertArrayEquals(expected, actual);
    }
}