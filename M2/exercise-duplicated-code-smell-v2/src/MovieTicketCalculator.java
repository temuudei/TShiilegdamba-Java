import java.util.ArrayList;
import java.util.List;

public class MovieTicketCalculator {

    public static void main(String[] args) {
        System.out.println(calculateTicketPrice(25, "Monday"));
        System.out.println(calculateTicketPrice(15, "Saturday"));
    }

    public static double calculateTicketPrice(int age, String day) {
        double price;
        List<String> listOfWeekOne = new ArrayList<String>();
        listOfWeekOne.add("monday");
        listOfWeekOne.add("tueday");
        listOfWeekOne.add("wednesday");

        List<String> listOfWeekTwo = new ArrayList<String>();
        listOfWeekTwo.add("thursday");
        listOfWeekTwo.add("friday");
        listOfWeekTwo.add("saturday");
        listOfWeekTwo.add("sunday");

        if (isPartOfWeek(day, listOfWeekOne)) {
            price = getPrice(age, 6.0, 8.0, 6.5);
        } else if (isPartOfWeek(day, listOfWeekTwo)) {
            price = getPrice(age, 8.0, 10.0, 8.5);
        } else {
            throw new IllegalArgumentException("Invalid day: " + day);
        }
        return price;
    }
    public static double getPrice(int age, double priceForChild, double priceForAdult, double priceForElderly) {
        if (age < 18) {
            return priceForChild;
        } else if (age >= 18 && age < 65) {
            return priceForAdult;
        } else {
            return priceForElderly;
        }
    }
    public static boolean isPartOfWeek(String day, List<String> daysOfWeek) {
        /*
        foreach(String dayOfWeek : daysOfWeek) {
            if(day.equalsIgnoreUppercase(dayOfWeek)) return true;
        }
        return false;
         */
        return daysOfWeek.contains(day.toLowerCase());
    }
}