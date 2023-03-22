public class Main {
    public static void main(String[] args) {
        System.out.println("Order status:");
        OrderStatus[] orderValues = OrderStatus.values();
        for (int i = 0; i < orderValues.length; i++) {
            System.out.println((i + 1) + ": " + orderValues[i]);
        }

        System.out.println("\nTrack status:");
        TrackStatus[] trackValues = TrackStatus.values();
        for (int i = 0; i < trackValues.length; i++) {
            System.out.println((i + 1) + ": " + trackValues[i]);
        }
    }
}