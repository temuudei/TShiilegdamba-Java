import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random rng = new Random();
        final int upperBound = 100;
        final int lowerBound = 0;
        int rndNumber = 0;

        int gold = 40;
        float distanceTravelled = 0.0f;
        boolean isBandit = false;
        int numberOfRounds = 1;
        boolean hasGuard = false;
        int cityDistance = 200;
        int round = 0;
        int percentage = 5;
        Scanner sc = new Scanner(System.in);
        boolean isWantsToPlay = false;
        String input;

        while (gold >= 0 ) {
            rndNumber = rng.nextInt(lowerBound, upperBound)+1;
            System.out.println("Let's play!");
            System.out.println("Round " + numberOfRounds);
            System.out.println("Your available gold: " + gold);
            if (hasGuard == true) {
                System.out.println("You have a guard");
            }
            else {
                System.out.println("You have no guard");
            }

            System.out.println("You have travelled " + distanceTravelled + " mile(s)");
            System.out.println("Percentage of running into bandit is " + percentage + "%");
            if (hasGuard == false){
                System.out.println("Would you like to purchase a guard for 20 gold? (y/n)");
                input = sc.nextLine();
                if (input.equalsIgnoreCase("y")) {
                    gold -= 20;
                    hasGuard = true;
                    System.out.println("Now you have " + gold + " many golds available");
                    System.out.println("You have purchased a guard.");
                }
            }

            System.out.println("You are about to travel to the next round. Hopefully you don't run into a bandit. Good luck.");
            if (hasGuard == true) {
                if (rndNumber <= percentage) {
                    System.out.println("Unfortunately, you have run into a bandit. But luckily you have a guard! Your guard saved you, but now you are guardless ");
                    hasGuard = false;
                    numberOfRounds++;
                    distanceTravelled+=50;
                    if (distanceTravelled % 200 == 0) {
                        hasGuard = false;
                        if (percentage <= 55){
                            percentage+=5;
                        }else{
                            percentage=60;
                        }
                        System.out.println("Congrats! You travelled 200 miles to your next city");
                        System.out.println("Would you like to purchase a guard for 20 gold? (y/n)");
                        input = sc.nextLine();
                        if (input.equalsIgnoreCase("y")) {
                            gold -= 20;
                            hasGuard = true;
                            System.out.println("Now you have " + gold + " many golds available");
                            System.out.println("You have purchased a guard.");
                        }
                    }
                    gold+=15;


                }else{
                    System.out.println("Congrats! You made it this round with no bandits.");
                    numberOfRounds++;
                    distanceTravelled+=50;
                    if (distanceTravelled % 200 == 0) {
                        if (percentage <= 55){
                            percentage+=5;
                        }else{
                            percentage=60;
                        }
                        hasGuard = false;
                        System.out.println("Congrats! You travelled 200 miles to your next city");
                        System.out.println("Would you like to purchase a guard for 20 gold? (y/n)");
                        input = sc.nextLine();
                        if (input.equalsIgnoreCase("y")) {
                            gold -= 20;
                            hasGuard = true;
                            System.out.println("Now you have " + gold + " many golds available");
                            System.out.println("You have purchased a guard.");
                        }
                    }
                    gold+=15;

                }
            }else{
                if (rndNumber <= percentage) {
                    System.out.println("Unfortunately, you have run into a bandit. You do not have a guard and need to pay the bandit 10 gold. ");
                    gold-=10;
                    numberOfRounds++;
                    distanceTravelled+=50;
                    if (distanceTravelled % 200 == 0) {
                        if (percentage <= 55){
                            percentage+=5;
                        }else{
                            percentage=60;
                        }
                        hasGuard = false;
                        System.out.println("Congrats! You travelled 200 miles to your next city");
                        System.out.println("Would you like to purchase a guard for 20 gold? (y/n)");
                        input = sc.nextLine();
                        if (input.equalsIgnoreCase("y")) {
                            gold -= 20;
                            hasGuard = true;
                            System.out.println("Now you have " + gold + " many golds available");
                            System.out.println("You have purchased a guard.");
                        }
                    }
                    gold+=15;


                }else{
                    System.out.println("Congrats! You made it this round with no bandits.");
                    numberOfRounds++;
                    distanceTravelled+=50;
                    if (distanceTravelled % 200 == 0) {
                        hasGuard = false;
                        if (percentage <= 55){
                            percentage+=5;
                        }else{
                            percentage=60;
                        }
                        System.out.println("Congrats! You travelled 200 miles to your next city");
                        System.out.println("Would you like to purchase a guard for 20 gold? (y/n)");
                        input = sc.nextLine();
                        if (input.equalsIgnoreCase("y")) {
                            gold -= 20;
                            hasGuard = true;
                            System.out.println("Now you have " + gold + " many golds available");
                            System.out.println("You have purchased a guard.");
                        }
                    }
                    gold+=15;

                }

            }
            System.out.println("Are you ready for the next round? y/n");
            String readyForNextRound = sc.nextLine();
            if (readyForNextRound.equalsIgnoreCase("y")){
                continue;
            }else{
                break;
            }

        }
        System.out.println("Do you want to play again?Y/N");
    }
}