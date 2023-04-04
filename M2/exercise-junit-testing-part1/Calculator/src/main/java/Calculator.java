import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//1, /n, 3 -- fail
// 1/n,2 -- fails
// 1/n2,3--pass
// /n1,2 -- fail

public class Calculator {

    public static int Add(String input) {

        String numbersToAdd = input;
        String splitter = ",";
        if(input.startsWith("//") && input.substring(3,4).equals("\n")){
            splitter = input.substring(2,3);
            numbersToAdd = input.substring(4);
        }




        String[] inputs = numbersToAdd.split(splitter+"|(\n)");
        if(input.contains("\n,") || input.contains(",\n")) {
            System.out.println("Invalid input");
            return -1;
        }
        List<Integer> numbers = new ArrayList<Integer>();

        for (String numString : inputs) {
            int number;
            try {
                if(numString.equals("")) number = 0;
                else number = Integer.parseInt(numString);

            } catch (NumberFormatException e) {

                System.out.println("Invalid input");
                return -1;
            }

            numbers.add(number);
        }

      

        int addition = 0;
         for (int number : numbers) {
             addition+=number;
         }
        return addition;
    }

    /*
    public static int Add(String input) {

        string splitter = ","
        if input starts with // and the 4-5th char is \n
        3rd char is splitter
        //;\n




        String[] inputs = input.split(","); // [ "1\n2", "3"] \\1,2,3\n
        List<Integer> numbers = new ArrayList<Integer>();
        for (String numString : inputs) {
            int number;
            try {
                if(numString.equals("")) number = 0;
                else number = Integer.parseInt(numString);
                numbers.add(number);
            } catch (NumberFormatException e) {
                if(numString.contains("\n")){ //1\n2       1\n
                    String[] numbersBetweenWhitespace = numString.split("\n"); //["1","2"]   ["1",""]
                    if(numbersBetweenWhitespace.length<1) {
                        System.out.println("Invalid input");
                        return -1;
                    }
                    for (String numString2 : numbersBetweenWhitespace) {
                        try {
                            number = Integer.parseInt(numString2);
                        } catch (NumberFormatException ex) {
                            System.out.println("Invalid input");
                            return -1;
                        }
                        numbers.add(number);
                    }
                }else {
                    System.out.println("Invalid input");
                    return -1;
                }
            }
        }

        int addition = 0;
        for (int number : numbers) {
            addition+=number;
        }
        return addition;
    }
    */
}
