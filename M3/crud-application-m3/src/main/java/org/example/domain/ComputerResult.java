package org.example.domain;

import org.example.dal.DALException;
import org.example.models.Computer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class ComputerResult {
    private List<String> messages;
    private Computer computer;

    public ComputerResult() {
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

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer item) {
        this.computer = item;
    }

}
