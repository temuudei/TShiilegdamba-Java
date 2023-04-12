package org.example.domain;

import org.example.dal.DALException;
import org.example.dal.ItemListRepository;
import org.example.dal.ItemRepository;
import org.example.models.Item;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> viewCart() throws DALException {
        return itemRepository.readAll();
    }

    @Override
    public ItemResult addItem(Item item) throws DALException {
        // Validate Item data aka Business logic!
        ItemResult result = new ItemResult();
        if(item == null){
            result.addMessage("An item is required");
            return result;
        }
        if(item.getName().isBlank()){
            result.addMessage("Item name is required");
        }
        if(item.getPrice() <= 0){
            result.addMessage("Items must have a price greater than zero");
        }
        if(item.getQty() <= 0){
            result.addMessage("Items must have a qty greater than zero");
        }

        if(result.isSuccessful()){
            item = itemRepository.create(item);
            result.setItem(item);
        }
        return result;
    }

    @Override
    public ItemResult removeItem(Item item) {
        return null;
    }

    @Override
    public double checkOut() {
        return 0;
    }
}
