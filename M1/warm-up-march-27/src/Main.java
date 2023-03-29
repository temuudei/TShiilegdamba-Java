public class Main {
    public static void main(String[] args) {
        int[] numbers = {3, 6, 9,};
        System.out.println(sumArray(numbers));
    }

    public static int sumArray(int[] numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 3 == 0) {
                sum += numbers[i];
            }
        }
        return sum;
    }
}