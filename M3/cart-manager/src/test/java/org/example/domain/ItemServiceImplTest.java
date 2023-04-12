package org.example.domain;

import org.example.dal.DALException;
import org.example.dal.ItemListRepository;
import org.example.dal.ItemRepository;
import org.example.models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceImplTest {
    private ItemRepository itemRepository;
    private ItemService itemService;

    @BeforeEach
    void setup(){
        itemRepository = new ItemMockRepository();
        itemService = new ItemServiceImpl(itemRepository);
    }

    @Test
    void addItem() {
        // Arrange
        Item item = new Item();
        item.setQty(1);
        item.setPrice(1.43);
        item.setName("iPad");
        ItemResult expectedResult = new ItemResult();
        expectedResult.setItem(item);
        // Act
        ItemResult actualResult = null;
        try {
             actualResult = itemService.addItem(item);
        } catch (DALException e) {
            fail("Should not have thrown an exception");
        }
        // Assert
        assertNotNull(actualResult);
        assertTrue(actualResult.isSuccessful());
        assertEquals(item, actualResult.getItem());

    }
}