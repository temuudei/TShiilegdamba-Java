public class Main {
    public static void main(String[] args) {
        System.out.println(firstCharacter("hi"));
    }

    public static int getSum(int a, int b) {
        return a + b;
    }

    public static String getConcaternation(String a, String b) {
        return a + b;
    }

    public static int getStringLength(String a) {
        return a.length();
    }

    public static int getNumberSquare(int a) {
        return a * a;
    }

    public static String stringAndIntConcat(String str, int numb) {
        return str + numb;
    }

    public static String getAllUppercase(String str) {
        return str.toUpperCase();
    }

    public static String getLexicographically(String strOne, String strTwo) {
        if (strOne.compareTo(strTwo) < 1) {
            return strOne;
        }
        else {
            return strTwo;
        }
    }

    public static String stringRepeated(String str, int numb) {
        String newStr = "";
        for (int i = 0; i < numb; i++) {
            newStr += " " + str;
        }
        return newStr;
    }

    public static int arraySum(int[] numb) {
        int sum = 0;
        for (int i = 0; i < numb.length; i++) {
            sum += numb[i];
        }
        return sum;
    }

    public static String longestString(String[] str) {
        String longestStr = "";
        for (int i = 0; i < str.length; i++) {
            for (int j = 1; j < str.length; j++) {
                if (str[i].length() > str[j].length()) {
                    longestStr = str[i];
                }
                else {
                    longestStr = str[j];
                }
            }
        }
        return longestStr;
    }
    public static String numberChecker(int numb) {
        String check = "";
        if (numb > 0) {
            check = "Positive";
        }
        else if (numb < 0) {
            check = "Negative";
        }
        else {
            check = "Zero";
        }
        return check;
    }

    public static Character firstCharacter(String str) {
        return str.charAt(0);
    }

    public static int absoluteNumber(int numb) {
        return Math.abs(numb);
    }

    public static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static int numberOfTimesChar(String str, char c) {
        for (int i = 0; i < str.length(); i++) {
            
        }
    }
}