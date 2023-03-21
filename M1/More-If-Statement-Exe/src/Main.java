public class Main {
    public static void main(String[] args) {
        float miles = 56.78f;
        float km = miles * 1.6f;
        int goalKm = 90;

        System.out.println("Converting " + miles + " miles to km: " + km);
        System.out.println("You have run " + km + " km");
        System.out.println("Your goal is to run " + goalKm + " km");
        boolean isGoalReached = km >= goalKm;

        if (isGoalReached) {
            System.out.println("You have reached ur goal to run more than 90 km");
        }
        else {
            System.out.println("You have not reached ur goal to run more than 90 km");
        }
    }
}