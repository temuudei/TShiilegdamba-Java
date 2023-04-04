public class Warmup1 {
    public static void main(String[] args) {
        String input = "Hello, world!";
        String reversed = reverseString(input);
        System.out.print("Reversed string: " + reversed);
    }
    public static String reverseString(String str) {
        String reversed = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }
        return reversed;
    }
}