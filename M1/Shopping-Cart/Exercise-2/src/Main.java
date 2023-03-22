public class Main {
    public static void main(String[] args) {
        System.out.println("Order status:");
        OrderStatus[] orderValues = OrderStatus.values();
        System.out.println("1: " + orderValues[0]);
        System.out.println("2: " + orderValues[1]);
        System.out.println("3: " + orderValues[2]);
        System.out.println("4: " + orderValues[3]);
        System.out.println("5: " + orderValues[4]);
        System.out.println("6: " + orderValues[5]);

        System.out.println("\nTrack status:");
        TrackStatus[] trackValues = TrackStatus.values();
        System.out.println("1: " + trackValues[0]);
        System.out.println("2: " + trackValues[1]);
        System.out.println("3: " + trackValues[2]);
        System.out.println("4: " + trackValues[3]);
    }
}