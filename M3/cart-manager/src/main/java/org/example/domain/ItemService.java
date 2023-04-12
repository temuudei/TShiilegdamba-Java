package org.example.domain;

import org.example.dal.DALException;
import org.example.models.Item;

import java.util.List;

public interface ItemService {
    List<Item> viewCart() throws DALException;
    ItemResult addItem(Item item) throws DALException;
    ItemResult removeItem(Item item);
    double checkOut();
    //ViewCart : List<Item>
    //AddItem : ItemResult
    //RemoveItem : ItemResult
    //Checkout : double
}
