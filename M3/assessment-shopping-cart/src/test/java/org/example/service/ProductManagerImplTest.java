package org.example.service;

import org.example.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerImplTest {
    private ProductManager productManager;
    private Product productOne;
    private Product productTwo;

    @BeforeEach
    void setUp() {
        productManager = new ProductManagerImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void readAllWhenOnlyOneItemIsAdded() {
        //ARRANGE
        productOne = new Product();
        productOne.setItemQuantity(5);
        productOne.setItemName("PS5");
        productOne.setItemID(1);
        //ACT
        productManager.createProduct(1, productOne, 5);
        int expected = 1;
        //ASSERT
        assertEquals(expected, productManager.readAll().size());
    }

    @Test
    void readAllWhenTwoItemsAreAdded() {
        //ARRANGE
        productOne = new Product();
        productOne.setItemQuantity(5);
        productOne.setItemName("PS5");
        productOne.setItemID(1);

        productTwo = new Product();
        productTwo.setItemQuantity(9);
        productTwo.setItemName("PS4");
        productTwo.setItemID(3);
        //ACT
        productManager.createProduct(1, productOne, 5);
        productManager.createProduct(3, productTwo, 9);
        int expected = 2;
        //ASSERT
        assertEquals(expected, productManager.readAll().size());
    }

    @Test
    void readByIndexWhenOneItemIsAdded() {
        //ARRANGE
        productOne = new Product();
        productOne.setItemQuantity(5);
        productOne.setItemName("PS5");
        productOne.setItemID(1);
        //ACT
        productManager.createProduct(1, productOne, 5);
        int expected = 5;
        //ASSERT
        assertEquals(expected, productManager.readByIndex(1).getItemQuantity());
    }

    @Test
    void deleteProductWhenOnlyOneItemIsInTheMap() {
        //ARRANGE
        productOne = new Product();
        productOne.setItemQuantity(5);
        productOne.setItemName("PS5");
        productOne.setItemID(1);
        //ACT
        productManager.createProduct(1, productOne, 5);
        int expected = 5;
        //ASSERT
        assertEquals(expected, productManager.deleteProduct(1).getItemQuantity());
    }

    @Test
    void createOnlyOneItemAndAddItToTheMap() {
        //ARRANGE
        productOne = new Product();
        productOne.setItemQuantity(5);
        productOne.setItemName("PS5");
        productOne.setItemID(1);
        //ACT
        //ASSERT
        int expected = 5;
        assertEquals(expected, productManager.createProduct(1, productOne, 5).getItemQuantity());
    }
}