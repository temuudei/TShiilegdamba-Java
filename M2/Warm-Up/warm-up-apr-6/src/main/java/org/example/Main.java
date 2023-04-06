package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(4);
        arrayList.add(6);
        arrayList.add(9);
        arrayList.add(10);
        System.out.println(getEvenNumSum(arrayList));
    }

    public static int getEvenNumSum(List<Integer> arrayList) {
        int sum = 0;
        for (int i : arrayList) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}