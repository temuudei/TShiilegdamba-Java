import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean[] isVisit = {false, false, false, false, false};
        String[] locations = {"Haunted House", "Maze", "Basement", "UFO House", "Ghost House", "exit the game"};

        System.out.println("Welcome to the game!");
        int locationIndex = 0;
        do {
            displayChoices(locations);
            locationIndex = promptUserForInt("Location?");

            if (locationIndex == 1) {
                System.out.println("\nHaunted House: ");
                if (isVisit[0] == false) {
                    System.out.println("Description A: There is a ghost standing behind you");
                    isVisit[0] = true;
                } else {
                    System.out.println("Description B: There is a clown standing in front of you");
                }
            }
            else if (locationIndex == 2) {
                System.out.println("\nMaze: ");
                if (isVisit[1] == false) {
                    System.out.println("Description A: There are aliens");
                    isVisit[1] = true;
                } else {
                    System.out.println("Description B: There are lions");
                }
            }
            else if (locationIndex == 3) {
                System.out.println("\nBasement: ");
                if (isVisit[2] == false) {
                    System.out.println("Description A: There is a bad person in front of you");
                    isVisit[2] = true;
                } else {
                    System.out.println("Description B: There is Anabelle staring at you on a chair, run!!!");
                }
            }
            else if (locationIndex == 4) {
                System.out.println("\nUFO House: ");
                if (isVisit[3] == false) {
                    System.out.println("Description A: There is a flying saucer on your left");
                    isVisit[3] = true;
                } else {
                    System.out.println("Description B: There are aliens talking to each other");
                }
            }
            else if (locationIndex == 5){
                if ((isVisit[0] && isVisit[1] && isVisit[2] && isVisit[3]) == true) {
                    System.out.println("\nGhose House: ");
                    if (isVisit[4] == false) {
                        System.out.println("Description A: There is an angry ghost yelling next to you");
                        isVisit[4] = true;
                    } else {
                        System.out.println("Description B: Boss ghost is there, RUN RUN RUN!!!");
                        if ((isVisit[0] && isVisit[1] && isVisit[2] && isVisit[3] && isVisit[4]) == true) {
                            System.out.println("You have visited all the places. Goodbye!");
                            break;
                        }
                    }
                }
                else if (isVisit[0] == false || isVisit[1] == false || isVisit[2] == false || isVisit[3] == false) {
                    System.out.println("You have to visit both Haunted House and Maze first in order to have the access to this location.");
                }
            }
            else if (locationIndex == 6) {
                System.out.println("You have ended the game. Goodbye!");
                break;
            }
            else {
                System.out.println("Wrong choice. Please only choose from the above 6");
            }

        } while (locationIndex < 1 || locationIndex > 6 || locationIndex != 6);
    }
    private static int promptUserForInt(String prompt) {
        java.util.Scanner console = new java.util.Scanner(System.in);
        boolean isValid = false;
        int result = -1;

        while (!isValid) {
            System.out.println(prompt);
            try {
                result = Integer.parseInt(console.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
        return result;
    }
    private static void displayChoices(String[] choices) {
        for (int i = 0; i < choices.length; i++) {
            System.out.println(i + 1 + ": " + choices[i]);
        }
    }
}