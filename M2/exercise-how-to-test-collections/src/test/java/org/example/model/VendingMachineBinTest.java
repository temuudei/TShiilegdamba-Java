package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class VendingMachineBinTest {
    private VendingMachineBin vendingMachineBin;
    @BeforeEach
    void setUp() {
        vendingMachineBin = new VendingMachineBin();
    }

    @Test
    void vendProductWhenIndexOutOfBoundTest() {
        //Arrange
        Product apple = new Product();
        apple.setItemName("Apple");
        apple.setPrice(.25);
        //Act
        vendingMachineBin.loadProduct(apple);
        vendingMachineBin.loadProduct(Product.clone(apple));
        vendingMachineBin.loadProduct(Product.clone(apple));
        vendingMachineBin.vendProduct();
        vendingMachineBin.vendProduct();
        //Assert
        String expected = "Apple";
        assertEquals(expected, vendingMachineBin.getProducts().get(1).getItemName());
    }

    @Test
    void vendProductWhenIndexNotOutOfBoundTest() {
        //Arrange
        Product apple = new Product();
        apple.setItemName("Apple");
        apple.setPrice(.25);
        //Act
        vendingMachineBin.loadProduct(apple);
        vendingMachineBin.loadProduct(Product.clone(apple));
        vendingMachineBin.loadProduct(Product.clone(apple));
        vendingMachineBin.vendProduct();
        vendingMachineBin.vendProduct();
        //Assert
        String expected = "Apple";
        assertEquals(expected, vendingMachineBin.getProducts().get(0).getItemName());
    }
}