public class ShopKeeper {
    private String name;
    private int gold;
    private double distanceTravelled;
    private boolean hasBodyGuard;
    private double distanceToCity;
    private boolean citiesVisited;


    public ShopKeeper(String name) {
        this.name = name;
        this.gold = 0;
        this.hasBodyGuard = false;
        this.distanceTravelled = 200;
        this.distanceToCity = 0;
        this.citiesVisited = false;

    }

    public void playRound() {
        System.out.println("Let's play!");
        System.out.println("Round " + citiesVisited);
        System.out.println("Your available gold: " + gold);
        if (hasBodyGuard == true) {
            System.out.println("You have a guard");
        }
        else {
            System.out.println("You have no guard");
        }
    }

}
