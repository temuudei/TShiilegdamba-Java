public class ShopKeeper {

    private String name;
    private int gold;
    private double distanceTraveled;
    private boolean hasBodyGuard;
    private double distanceToCity;

    private int citiesVisited;

    public ShopKeeper(String name) {
        this.name = name;
        this.gold = 0;
        this.distanceTraveled = 0;
        this.hasBodyGuard = false;
        this.distanceToCity = 200;
        this.citiesVisited = 0;
    }

    public void playRound(Robber robber) {
        System.out.println("Let's play!");
        System.out.println("Round " + citiesVisited);
        System.out.println("Your available gold: " + gold);
        if (hasBodyGuard == true) {
            System.out.println("You have a guard");
        } else {
            System.out.println("You have no guard");
        }

        moveDistance(robber);

        robber.robShopKeeper(this);
    }

    public void moveDistance(Robber robber) {
        distanceTraveled += 50;
        distanceToCity -= 50;
        if (distanceToCity == 0) {
            System.out.println("You've reached a city!");
            citiesVisited += 1;
            distanceToCity = 200;
            robber.increaseProbability();
        } else {
            System.out.printf("You have another %d miles to the next city\n", distanceToCity);
        }
    }

    public void useGold(int amount) {
        gold -= amount;
    }

    public void getBodyGuard(int amount) {
        hasBodyGuard = true;
        useGold(amount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(double distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public boolean isHasBodyGuard() {
        return hasBodyGuard;
    }

    public void setHasBodyGuard(boolean hasBodyGuard) {
        this.hasBodyGuard = hasBodyGuard;
    }

    public double getDistanceToCity() {
        return distanceToCity;
    }

    public void setDistanceToCity(double distanceToCity) {
        this.distanceToCity = distanceToCity;
    }
}