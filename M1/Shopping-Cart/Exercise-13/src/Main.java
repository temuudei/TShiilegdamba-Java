import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean[] isVisit = {false, false, false};
        String[] locations = {"Haunted House", "Maze", "Basement", "UFO House", "Ghost House", "exit the game"};
        String locationInput = "";
        int locationInputInt = 0;

        System.out.println("Welcome to the game!");
        do {
            String location = null;
            int locationIndex = 0;

            while (location == null) {
                displayChoices(locations);
                locationIndex = promptUserForInt("Location?");
                location = locations[locationIndex];
            }

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
                System.out.println("\nMaze: ");
                if (isVisit[1] == false) {
                    System.out.println("Description A: There are aliens");
                    isVisit[1] = true;
                } else {
                    System.out.println("Description B: There are lions");
                }
            }
            else if (locationIndex == 4) {
                System.out.println("\nMaze: ");
                if (isVisit[1] == false) {
                    System.out.println("Description A: There are aliens");
                    isVisit[1] = true;
                } else {
                    System.out.println("Description B: There are lions");
                }
            }
            else if (locationIndex == 5){
                if ((isVisit[0] && isVisit[1]) == true) {
                    System.out.println("\nBasement: ");
                    if (isVisit[2] == false) {
                        System.out.println("Description A: There is a bad person in front of you");
                        isVisit[2] = true;
                    } else  {
                        System.out.println("Description B: There is Anabelle staring at you on a chair, run!!!");
                        if ((isVisit[0] && isVisit[1] && isVisit[2]) == true) {
                            System.out.println("You have visited all the places. Goodbye!");
                            break;
                        }
                    }
                }
                else if (isVisit[0] == false || isVisit[1] == false) {
                    System.out.println("You have to visit both Haunted House and Maze first in order to have the access to this location.");
                }
            }
            else if (loca == 6) {
                System.out.println("You have ended the game. Goodbye!");
                break;
            } else {
                System.out.println("Invalid option. Please make a choice from those 4 options");
            }
        } while (locationInputInt < 1 || locationInputInt > 6 || locationInputInt != 6);
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
    private static String promptUserForString(String prompt) {
        java.util.Scanner console = new java.util.Scanner(System.in);
        System.out.println(prompt);
        return console.nextLine();
    }
    // Method for prompt user for string
    private static String promptUserForString(String prompt, String[] values) {
        boolean isValid = false;
        String input = "";
        do {
            input = promptUserForString(prompt);
            for (int i = 0; i < values.length; i++) {
                if (input.equalsIgnoreCase(values[i])) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                System.out.println("Hey, invalid entry. Please try again.");
            }
        } while (!isValid);
        return input;
    }

    private static void displayChoices(String[] choices) {
        for (int i = 0; i < choices.length; i++) {
            System.out.println(i + 1 + ": " + choices[i]);
        }
    }
}