package org.example.domain;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private ArrayList<String> messages = new ArrayList<>();
    private ArrayList<String> messagesTwo = new ArrayList<>();

    public boolean isSuccess() {
        return messages.size() == 0;
    }

    public boolean isSuccessTwo() {
        return messagesTwo.size() == 0;
    }

    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }

    public void addErrorMessage(String message) {
        messages.add(message);
    }
    public void addMessage(String message) {messagesTwo.add(message);}
    public List<String> getAddMessage() { return new ArrayList<>(messagesTwo);}
}
