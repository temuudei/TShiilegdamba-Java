import java.text.DecimalFormat;

public class BMI {
    public static void main(String[] args) {
        final DecimalFormat df = new DecimalFormat("0.00");
        String firstName, lastName;
        double height, weight;
        int age;

        firstName = "Temuudei";
        lastName = "Shiilegdamba";
        height = 1.75f;
        weight = 75.43f;
        age = 25;

        System.out.println("Hi " + firstName + " " + lastName);
        System.out.println("Your age is " + age);
        double bmi = (weight / (Math.pow(height, 2)));
        System.out.println("Your BMI is " + df.format(bmi));

        if (bmi >= 18.5 && bmi <= 24.9) {
            System.out.println("Great job");
        }
        else if (bmi < 18.5) {
            System.out.println("Try to eat more please");
        }
        else if (bmi >= 25.0 && bmi <= 29.9) {
            System.out.println("Need extra work please");
        }
        else if (bmi >= 30.0) {
            System.out.println("Need even more extra work please");
        }
    }
}