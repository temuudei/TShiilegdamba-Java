package org.example;

public class Main {
    public static void main(String[] args) {
        String str = "AAABBBIII";
        System.out.println(replaceString(str));
    }

    public static String replaceString(String str) {
        if (str.contains("A") || str.contains("a")) {
            str = str.replace("A", "4");
        }
        if (str.contains("B") || str.contains("b")) {
            str = str.replace("B", "I3");
        }
        if (str.contains("C") || str.contains("c")) {
            str = str.replace("C", "[");
        }
        if (str.contains("D") || str.contains("d")) {
            str = str.replace("D", ")");
        }
        if (str.contains("E") || str.contains("e")) {
            str = str.replace("E", "3");
        }
        if (str.contains("F") || str.contains("e")) {
            str = str.replace("F", "!=");
        }
        if (str.contains("G") || str.contains("g")) {
            str = str.replace("G", "&");
        }
        if (str.contains("H") || str.contains("h")) {
            str = str.replace("H", "*");
        }
        if (str.contains("I") || str.contains("i")) {
            str = str.replace("I", "1");
        }
        if (str.contains("J") || str.contains("j")) {
            str = str.replace("J", ",_|");
        }
        if (str.contains("K") || str.contains("k")) {
            str = str.replace("K", ">|");
        }
        if (str.contains("L") || str.contains("l")) {
            str = str.replace("L", "1");
        }
        
        return str;
    }
}