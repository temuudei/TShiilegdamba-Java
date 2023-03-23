import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter a word: ");
        String userInput = sc.nextLine();

        char[] charWords = userInput.toCharArray();

        for (int i = 0; i < charWords.length; i++) {
            if (charWords[i] == 'z') {
                charWords[i] = 'a';
            }
            else {
                charWords[i] = (char)(userInput.charAt(i) + 1);
            }
        }
        for (int i = 0; i < charWords.length; i++) {
            System.out.print(charWords[i] + " ");
        }
    }
}