package main.java;

public class App {
    public static void main(String[] args) {
        Exhibit[] myExhibits = new Exhibit[3];

        myExhibits[0] = new Pond("Front main.java.Pond", 27.0, "Catfish", true);
        myExhibits[1] = new Aquarium("100 Gallon Tank", 23.0, "Oscar", "Floating Water Moss");
        myExhibits[2] = new Terrarium("Little Turtle Tank", 27.0, 22.0, "Ornate Box Turtle");
        Lake lake = new Lake("Front main.java.Pond", 27.0, "Catfish", true);
        lake.name = "hello";


        System.out.println("Welcome to the main.java.Aquarium Manager!");
        System.out.println("Here are the current exhibits: ");

        for (int i = 0; i < myExhibits.length; i++) {
            Exhibit e = myExhibits[i];
            System.out.println(e.getDescription());
        }

        for (Exhibit e: myExhibits) {
            System.out.println(e.getDescription());
            if (e.isOpen()) {
                System.out.println("The exhibit is open.");
            }
        }
        Pond myPond = new Pond("Front main.java.Pond", 27.0, "Catfish", true);
        myPond.printAppleType();
        /*System.out.println("The " + myPond.getName() +
                            " houses the "  + myPond.getFish());
        System.out.println("The " + myAquarium.getName() +
                            " houses the " +
                            myAquarium.getFish() + " and " +
                            myAquarium.getPlant());
        System.out.println("The " + myTerrarium.getName() +
                            " houses the " + myTerrarium.getTurtle());*/
    }
}
