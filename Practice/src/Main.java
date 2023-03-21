public class Main {
    public static void main(String[] args) {
        System.out.println("the assignment operator");
        System.out.println("int x = 0");
        int x = 0;

        System.out.println("This is the value of x");
        System.out.println(x);

        x += 5; // x = x + 5
        System.out.println("Added 5 to x");
        System.out.println(x);

        x -= 2; // x = x - 2
        System.out.println("Subtracted 2 from x");
        System.out.println(x);

        x *= 4;
        System.out.println("Multiplied it by 4");
        System.out.println(x);

        x /= 3;
        System.out.println("Divided by 3");
        System.out.println(x);

        x = 0;
        System.out.println("Reset x to 0");
        System.out.println(x);

        x++; // x = x + 1
        System.out.println("Incrementing it by 1");
        System.out.println(x);

        x--; // x = x - 1
        System.out.println("Decrementing it by 1");
        System.out.println(x);

        int t = 10 / 3;
        System.out.println("Value is truncated");
        System.out.println(t);

        int wholeNumber, remainder;
        wholeNumber = 10 / 3;
        remainder = 10 % 3;
        System.out.println("The whole number value is " + wholeNumber);
        System.out.println("The remainder is " + remainder);

        boolean outcome; // false
        outcome = 3 < 4;
        System.out.println("The outcome of 3 less than 4 is " + outcome);

        outcome = 3 > 4;
        System.out.println("The outcome of 3 > 4");
        System.out.println(outcome);

        outcome = 3 <= 4;
        System.out.println("The outcome of 3 <= 4");
        System.out.println(outcome);

        outcome = 3 == 3;
        System.out.println("The outcome of 3 == 3");
        System.out.println(outcome);

        outcome = 3 == 4;
        System.out.println("The outcome of 3 == 4");
        System.out.println(outcome);

        outcome = true && true;
        System.out.println("The outcome of true && true");
        System.out.println(outcome);

        outcome = true && false;
        System.out.println("The outcome of true && false");
        System.out.println(outcome);

        outcome = true || true;
        System.out.println("Outcome of true || false");
        System.out.println(outcome);

        outcome = false || true;
        System.out.println("Outcome of false || true");
        System.out.println(outcome);

        outcome = !false;
        System.out.println("Outcome of !false");
        System.out.println(outcome);
    }
}