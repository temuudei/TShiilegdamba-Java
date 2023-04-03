public class Main {
    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {
            System.out.println(getNumberDivisible(i));
        }
    }

    public static String getNumberDivisible(int number) {
        String str = "";
        if (number % 3 == 0) {
            if (number % 5 == 0) {
                str = "Fizz Buzz";
            }
            else {
                str = "Fizz";
            }
        }
        else if (number % 5 == 0) {
            str = "Buzz";
        }
        else {
            str = Integer.toString(number);
        }
        return str;
    }
}

