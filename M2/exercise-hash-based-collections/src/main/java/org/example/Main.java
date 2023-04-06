package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "January");
        map.put(2, "February");
        map.put(3, "March");
        map.put(4, "April");
        map.put(5, "May");
        map.put(6, "June");
        map.put(7, "July");
        map.put(8, "August");
        map.put(9, "September");
        map.put(10, "October");
        map.put(11, "November");
        map.put(12, "December");

        for (int key : map.keySet()) {
            System.out.println(key + "-" + map.get(key));
        }

        Map<String, List<String>> mapTwo = new HashMap<>();
        mapTwo.put("Hearts", Arrays.asList("Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"));
        mapTwo.put("Diamonds", Arrays.asList("Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"));
        mapTwo.put("Clubs", Arrays.asList("Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"));
        mapTwo.put("Spades", Arrays.asList("Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"));

        for (String suit : mapTwo.keySet()) {
            for (String value : mapTwo.get(suit)) {
                System.out.println(value + " of " + suit);
            }
        }
    }
}