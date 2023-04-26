package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {4,5,1,2,10};
        System.out.println(arrayDifference(numbers));
    }

    public static int arrayDifference(int[] numbers) {
        Arrays.sort(numbers);
        return numbers[numbers.length - 1] - numbers[0];
    }
}