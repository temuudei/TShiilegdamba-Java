public class Player extends Token {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void takeTurn() {
        System.out.println(this.getMarker() + " " + this.getX());
    }
}
