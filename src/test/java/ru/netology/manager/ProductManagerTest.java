package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductManagerTest {
    @Mock
    private ProductRepository repository;
    @InjectMocks
    private ProductManager manager;

    private Book first = new Book(1,"Война и мир",500,"Лев Толстой");
    private Smartphone second = new Smartphone(2,"Iphone 11",50000, "Apple");
    private Book third = new Book(3,"Преступление и наканание",500,"Федор Достоевский");
    private Smartphone fourth = new Smartphone(4,"Samsung A30",30000, "Samsung");
    private Book fifth = new Book(5,"Идиот",500,"Федор Достоевский");
    private Smartphone sixth = new Smartphone(6,"Samsung Galaxy S5",60000, "Samsung");
    private Product seventh = new Product(7,"Huawei P20",15000);


    @BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(sixth);
        manager.add(seventh);
    }

    @Test
    public void shouldFindBookOfName() {

        Product[] returned = new Product[]{first, second, third, fourth,fifth, sixth,seventh};
        doReturn(returned).when(repository).findAll();

        Product[] expected = new Product[]{first};
        Product[] actual = manager.searchBy("Война и мир");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindBookOfName() {

        Product[] returned = new Product[]{first, second, third, fourth,fifth, sixth, seventh};
        doReturn(returned).when(repository).findAll();

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Мастер и Маргарита");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNullFindBookOfName() {

        Product[] returned = new Product[]{first, second, third, fourth,fifth, sixth, seventh};
        doReturn(returned).when(repository).findAll();

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindBookOfAuthor() {

        Product[] returned = new Product[]{first, second, third, fourth,fifth, sixth, seventh};
        doReturn(returned).when(repository).findAll();

        Product[] expected = new Product[]{first};
        Product[] actual = manager.searchBy("Лев Толстой");

        assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldFindTwoBookOfAuthor() {

        Product[] returned = new Product[]{first, second, third, fourth,fifth, sixth, seventh};
        doReturn(returned).when(repository).findAll();

        Product[] expected = new Product[]{third,fifth};
        Product[] actual = manager.searchBy("Федор Достоевский");

        assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldFindSmartphoneOfName() {

        Product[] returned = new Product[]{first, second, third, fourth,fifth, sixth, seventh};
        doReturn(returned).when(repository).findAll();

        Product[] expected = new Product[]{second};
        Product[] actual = manager.searchBy("Iphone 11");

        assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldFindSmartphoneOfManufacturer() {

        Product[] returned = new Product[]{first, second, third, fourth,fifth, sixth, seventh};
        doReturn(returned).when(repository).findAll();

        Product[] expected = new Product[]{second};
        Product[] actual = manager.searchBy("Apple");

        assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldFindTwoSmartphoneOfManufacturer() {

        Product[] returned = new Product[]{first, second, third, fourth,fifth, sixth, seventh};
        doReturn(returned).when(repository).findAll();

        Product[] expected = new Product[]{fourth, sixth};
        Product[] actual = manager.searchBy("Samsung");

        assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldFindProduct() {

        Product[] returned = new Product[]{first, second, third, fourth,fifth, sixth, seventh};
        doReturn(returned).when(repository).findAll();

        Product[] expected = new Product[]{seventh};
        Product[] actual = manager.searchBy("Huawei P20");

        assertArrayEquals(expected, actual);
    }

}