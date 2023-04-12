package org.example.domain;

import org.example.models.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemResult {
    private ArrayList<String> messages;
    private Item item;
    public ItemResult() {
        messages = new ArrayList<>();
    }

    public boolean isSuccessful(){
        return messages.size() == 0;
    }

    public Item getItem() {
        return item;
    }

    public void addMessage(String message){
        messages.add(message);
    }
    public void setItem(Item item) {
        this.item = item;
    }

    public List<String> getMessages() {
        return messages;
    }
}
