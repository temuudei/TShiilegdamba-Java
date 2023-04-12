package org.example.domain;

import org.example.dal.ItemRepository;
import org.example.models.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemResult addItem(Item item){
        // validate the item
        ItemResult result = new ItemResult();
        if("apple".equals(item.getName())){
            result.addMessage("Apples are not allowed here");
        }
        result.setItem(item);

        if(result.isSuccessful()){
            itemRepository.create(item);
        }
        // if it passes the validation add it to the repo
        return result;
    }
}
