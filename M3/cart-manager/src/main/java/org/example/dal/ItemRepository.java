package org.example.dal;

import org.example.models.Item;

import java.util.List;

public interface ItemRepository {
    Item create(Item item) throws DALException;
    List<Item> readAll() throws DALException;
    Item readById(int id) throws DALException;
    void update(int id, Item item) throws DALException;
    void delete(int id) throws DALException;
}
