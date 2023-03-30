import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String userInput = console.nextLine();

        //Converting string array to char array first by using the toCharArray() method.
        //Time complexity: O(n), space complexity: O(n)
        char[] charArray = userInput.toCharArray();
        for (int i = charArray.length - 1; i >= 0 ; i--) {
            System.out.print(charArray[i] + " ");
        }
    }
}