package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println(chooseCatering(300, 9));
    }
    public static int chooseCatering(int budget, int people) {
        int firstCaterer = people * 15;
        int secondCaterer = people * 20;
        int thirdCaterer = people * 30;

        if (firstCaterer <= budget) {
            return 1;
        }
        else if (budget > firstCaterer && budget <= secondCaterer) {
            return 2;
        }
        else if (budget > secondCaterer && budget <= thirdCaterer){
            return 3;
        }
        else if (people == 0 || budget < firstCaterer) {
            return -1;
        }
        return -1;
    }
}