package org.example.dal;

import org.example.models.Item;

import java.util.List;

public interface ItemRepository {
    //Create
    //Read
    //Update
    //Delete
    void create(Item item);
    List<Item> ReadAll();
    Item ReadById(int id);
    void Update(int id, Item item);
    void Delete(int id);
}
