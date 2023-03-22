import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int correctNumber = 8;
        Scanner sc = new Scanner(System.in);
        int counter = 0;

        while (counter < 3) {
            System.out.println("Please enter your guess:");
            String input = sc.nextLine();
            int intInput = Integer.parseInt(input);

            if (intInput == correctNumber) {
                System.out.println("Good job, you guessed it right. Took you " + (counter + 1) + " times to guess it right.");
                System.out.println("Would you like to play again? 'yes' or 'no'");
                input = sc.nextLine();
                if (input.equals("yes")) {
                    counter = 0;
                }
                else {
                    break;
                }
            }
            else if (intInput < correctNumber) {
                System.out.println("Your answer is too low. Try again.");
            }
            else if (intInput > correctNumber) {
                System.out.println("Your answer is too high. Try again.");
            }
            else if (intInput < 0) {
                System.out.println("It cannot be a negative number. Try again.");
            }
            else if (intInput > 20) {
                System.out.println("It cannot be larger than 20");
            }
            counter++;
            System.out.println("Your lost. Bye!");
        }
    }
}