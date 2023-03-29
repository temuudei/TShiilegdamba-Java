import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean[] isVisit = {false, false, false};
        String[] locations = {"Haunted House", "Maze", "Basement", "exit the game"};
        String locationInput = "";
        int locationInputInt = 0;

        System.out.println("Welcome to the game!");
        do {
            for (int i = 0; i < locations.length; i++) {
                System.out.println(i+1 + ". " + locations[i]);
            }
            System.out.print("Location? ");
            locationInput = sc.nextLine();
            locationInputInt = Integer.parseInt(locationInput);
            if (locationInputInt == 1) {
                System.out.println("\nHaunted House: ");
                if (isVisit[0] == false) {
                    System.out.println("Description A: There is a ghost standing behind you");
                    isVisit[0] = true;
                } else {
                    System.out.println("Description B: There is a clown standing in front of you");
                }
            }
            else if (locationInputInt == 2) {
                System.out.println("\nMaze: ");
                if (isVisit[1] == false) {
                    System.out.println("Description A: There are aliens");
                    isVisit[1] = true;
                } else {
                    System.out.println("Description B: There are lions");
                }
            }
            else if (locationInputInt == 3){
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
            else if (locationInputInt == 4) {
                System.out.println("You have ended the game. Goodbye!");
                break;
            } else {
                System.out.println("Invalid option. Please make a choice from those 4 options");
            }
        } while (locationInputInt < 1 || locationInputInt > 4 || locationInputInt != 4);
    }
}