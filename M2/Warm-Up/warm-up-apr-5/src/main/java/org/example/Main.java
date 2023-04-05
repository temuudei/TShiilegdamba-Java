package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println(getScambler("Hello"));
    }

    public static char[] getScambler(String input) {
        char[] charArray = input.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            for (int j = i + 2; j < charArray.length; j++) {
                char temp = ' ';
                temp = charArray[i];
                charArray[i] = charArray[j];
                charArray[j] = temp;
            }
        }
        return charArray;
    }
}