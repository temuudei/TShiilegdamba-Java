import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] oldArray = new String[2];
        Scanner sc = new Scanner(System.in);
        String input = "";

        for (int i = 0; i < oldArray.length; i++) {
            System.out.print("Please enter a string: ");
            input = sc.nextLine();
            oldArray[i] = input;
            if ((i + 1) >= oldArray.length) {
                String[] newArray = new String[oldArray.length * 2];
                for (int j = 0; j <= newArray.length; j++) {
                    if (j >= newArray.length) {
                        System.out.println("Ran out of memory, sorry!");
                        break;
                    }
                    else {
                        System.out.print("Please enter a string: ");
                        input = sc.nextLine();
                        newArray[j] = input;
                        System.out.println(newArray[j]);
                    }
                }
            }
            else if (input.equals("")) {
                System.out.println("Bye");
                break;
            }
        }
        for (int i = 0; i < oldArray.length; i++) {
            System.out.println(oldArray[i] + " ");
        }
    }
}