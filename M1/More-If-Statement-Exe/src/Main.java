public class Main {
    public static void main(String[] args) {
        float miles = 56.78f;
        float convertedValue = miles * 1.6f;
        int goalKm = 90;

        System.out.println("Converting " + miles + " miles to convertedValue: " + convertedValue);
        System.out.println("You have run " + convertedValue + " convertedValue");
        System.out.println("Your goal is to run " + goalKm + " convertedValue");
        boolean isGoalReached = convertedValue >= goalKm;

        if (isGoalReached) {
            System.out.println("You have reached ur goal to run more than 90 convertedValue");
        }
        else {
            System.out.println("You have not reached ur goal to run more than 90 convertedValue");
        }
    }
}