import java.util.Random;

public class Robber {

    Random rng = new Random();
    private double robberProbability;

    public void increaseProbability() {
        robberProbability += 0.05;
    }

    public void robShopKeeper(ShopKeeper user) {
        boolean robShopKeeper = rng.nextDouble(0, 1) <= robberProbability;
        if (robShopKeeper && !user.isHasBodyGuard()) {
            System.out.println("You've been robbed! lost 10 gold");
            user.useGold(10);
        } //else if
    }

}