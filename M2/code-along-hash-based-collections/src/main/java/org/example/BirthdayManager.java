package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class BirthdayManager {
    HashMap<Friend, List<Gift>> myFriendsGifts;

    public BirthdayManager() {
        this.myFriendsGifts = new HashMap<>();
    }

    public List<Friend> getFriends() {
        return myFriendsGifts.keySet().stream().toList();
    }

    public void addFriend(Friend newFriend) {
        myFriendsGifts.put(newFriend, new ArrayList<Gift>());
    }

    public void addGift(Friend friend, Gift gift) {
        if (!myFriendsGifts.containsKey(friend)) {
            addFriend(friend);
        }
        myFriendsGifts.get(friend).add(gift);
    }

    public void removeFriend(Friend exFriend) {
        myFriendsGifts.remove(exFriend);
    }
}
