package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(5);
        list.add(6);
        consecutive(list);
        System.out.println(list);
    }

    public static int consecutive(ArrayList<Integer> arrayList) {
        Collections.sort(arrayList);
    }
}