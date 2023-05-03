package org.example.domains;

import java.util.ArrayList;
import java.util.List;


public class Result<Obj> {
    List<String> messages;
    Obj obj;

    public Result() {
        messages = new ArrayList<>();
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }

    public boolean isSuccessful() {
        return messages.size()==0;
    }

    public Obj getObj() {
        return obj;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }
}
