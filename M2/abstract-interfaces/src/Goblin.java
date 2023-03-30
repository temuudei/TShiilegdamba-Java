public class Goblin extends Token {
    private String loot;

    public String getLoot() {
        return loot;
    }

    public void setLoot(String loot) {
        this.loot = loot;
    }

    @Override
    public void takeTurn() {
        System.out.printf("%s swings their loot of %s", this.getMarker(), this.getLoot());
    }
}
