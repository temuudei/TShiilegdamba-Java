package org.example.dal;

import org.example.models.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public class ItemListRepository implements ItemRepository {
    List<Item> items;

    public ItemListRepository() {
        items = new ArrayList<>();
    }

    @Override
    public Item create(Item item) throws DALException {
        int nextid = 0;
        for (Item i: items) {
            if(i.getId() > nextid){
                nextid = i.getId();
            }
        }
        nextid++;
        item.setId(nextid);
        items.add(item);
        return item;
    }

    @Override
    public List<Item> readAll() throws DALException {
        return items;
    }

    @Override
    public Item readById(int id) throws DALException {
        for (Item item:items) {
            if(item.getId() == id){
                return item;
            }
        }
        return null;
    }

    @Override
    public void update(int id, Item item) throws DALException {
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getId() == id){
                items.set(i, item);
                break;
            }
        }

    }

    @Override
    public void delete(int id) throws DALException {
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getId() == id){
                items.remove(i);
                break;
            }
        }
    }
}
