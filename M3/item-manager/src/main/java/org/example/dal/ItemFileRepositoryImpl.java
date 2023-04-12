package org.example.dal;

import org.example.models.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemFileRepositoryImpl implements ItemRepository {
    ArrayList<Item> items;

    public ItemFileRepositoryImpl() {
        items = new ArrayList<>();
    }

    @Override
    public void create(Item item) {
        items.add(item);
    }

    @Override
    public List<Item> ReadAll() {
        return items;
    }

    @Override
    public Item ReadById(int id) {
        for (Item item: items) {
            if(item.getId() == id){
                return item;
            }
        }
        return null;
    }

    @Override
    public void Update(int id, Item item) {
        if(items.contains(item)){
            items.set(items.indexOf(item), item);
        }
    }

    @Override
    public void Delete(int id) {
        items.removeIf( p -> p.getId() == id);
    }
}
