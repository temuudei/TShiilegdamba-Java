public class App {
    public static void main(String[] args) {
        Fish myFish;
        AquariumFish myAquariumFish = new AquariumFish();
        myAquariumFish.setScientificName("Anableps anableps");
        myAquariumFish.setName("Four-eyed fish");
        myAquariumFish.setNickname("Benny");

        System.out.println(myAquariumFish.toString());

    }
}