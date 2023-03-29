package aquarium;

import java.util.Scanner;

public class AquariumTracker {
    public AquariumTracker() {
    }

    public static void main(String[] args) {
        AquariumFish aquariumFish = new AquariumFish();
        Scanner console = new Scanner(System.in);
        aquariumFish.setValidInput(false);
        System.out.println("Enter the information for your fish.");
        System.out.print("Species Name: ");
        aquariumFish.setSpecies(console.nextLine());
        System.out.print("Common Name: ");
        aquariumFish.setCommonName(console.nextLine());

        String temp;
        do {
            System.out.print("Maximum temperature: ");
            temp = console.nextLine();

            try {
                aquariumFish.setMaxTemp(Double.valueOf(temp));
                aquariumFish.setValidInput(true);
            } catch (Exception var6) {
                System.out.println("Enter a valid temperature");
                aquariumFish.setValidInput(false);
            }
        } while(!aquariumFish.isValidInput());

        do {
            System.out.print("Minimum temperature: ");
            temp = console.nextLine();

            try {
                aquariumFish.setMinTemp(Double.valueOf(temp));
                aquariumFish.setValidInput(true);
            } catch (Exception var5) {
                System.out.println("Enter a valid temperature");
                aquariumFish.setValidInput(false);
            }
        } while(!aquariumFish.isValidInput());

        System.out.println("Diet: ");
        aquariumFish.setDiet(console.nextLine());
        System.out.println("Thank you for the input, here is the summary.");
        System.out.println("Species name: " + aquariumFish.getSpecies());
        System.out.println("Common name: " + aquariumFish.getCommonName());
        System.out.println("Average Temperature: " + aquariumFish.getAverageTemp());
    }
}