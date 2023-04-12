package org.example.domain;

import org.example.models.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemResult {
    List<String> messages;
    private Item item;
    public ItemResult() {
        messages = new ArrayList<>();
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public boolean isSuccessful() {
        return messages.size() == 0;
    }

    public List<String> getMessages() {
        return messages;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
