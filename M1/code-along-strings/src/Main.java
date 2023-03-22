public class Main {
    public static void main(String[] args) {
        String example = "This is a string";
        String fname = "Temuudei";
        String lname = "Shiilegdamba";
        // got to this using lightbulb from clicking on plus sign during normal
        // concatenation
        String fullname = fname + " " + lname;
        System.out.println(fullname);
        String fullsentence = "jack and jill " + "went up the hill";
        System.out.println(fullsentence);

        String example2 = "Hello" + ' ';
        String exampl3 = "Goodbye";
        String example4 = example2 + exampl3;

        System.out.println(fullsentence.length());
        fullsentence.isBlank();
        fullsentence.isEmpty();

        // indexOf
        String search = "The quick brown fox jumped over the lazy dog";
        System.out.println(search);
        int index = search.indexOf('e');
        System.out.println("The index of e is: " + index);
        int nextIndex = search.indexOf('e', 3);
        System.out.println("The second index of e is: " + nextIndex);
        int indexQuick = search.indexOf("quick");
        System.out.println("This is the index of the word 'quick': " + indexQuick);
        int indexFoot = search.indexOf("football");
        System.out.println(indexFoot);

        // substrings
        String dateTime = "20220103 05:10:32";
        String year = dateTime.substring(0, 4);
        String month = dateTime.substring(4, 6);
        String day = dateTime.substring(6, 8);
        String hours = dateTime.substring(9, 11);
        String minutes = dateTime.substring(12, 14);
        String seconds = dateTime.substring(15, 17);

        System.out.println("year: " + year + " month: " + month + " day: " + day
                           + " hours: " + hours + " minutes: " + minutes + " seconds: " + seconds);

        // Splitting a string
        String[] dateAndTime = dateTime.split(" ");
        String[] timeFields = dateAndTime[1].split(":");
        System.out.println("hours: " + timeFields[0]);

        // using constants for readability
        final int HOURS = 0;
        System.out.println("hours: " + timeFields[HOURS]);

        // charAt
        String data = "001somethingy12345";
        System.out.println("This is the character at the 12th index: " + data.charAt(12));

        // replace method
        String _version = "this_text_needs_spaces_in_it";
        System.out.println(_version);
        System.out.println(_version.replace('_', ' ').replace("needs", "has"));
    }
}