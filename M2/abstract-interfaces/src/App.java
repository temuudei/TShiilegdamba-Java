public class App {
    public static void main(String[] args) {
        Token token1 = new Player();
        Token token2 = new Goblin();
        Player p = new Player();


        Token[] tokens = new Token[3];
        tokens[0] = token1;
        tokens[1] = token2;

        p.setName("Sally");
        p.setMarker("P");
        tokens[2] = p;

        for (int i = 0; i < tokens.length; i++) {
            Token token = tokens[i];
            System.out.printf("Token %s is at {%s, %s}", token.getMarker(), token.getX(), token.getY());
            if (token instanceof Player) {
                Player player = (Player) token;
                System.out.println("Player's name is " + player.getName());
            }
            else if (token instanceof Goblin) {
                Goblin goblin = (Goblin) token;
                System.out.println("GOblin has this loot: " + goblin.getLoot());
            }
        }
    }
}