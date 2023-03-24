public class Main {
    public static void main(String[] args) {
        System.out.println("Arrays Demo - Classroom Manager");

        // start with 5 students
        String s1 = "Jerry";
        String s2 = "Elaine";
        String s3 = "George";
        String s4 = "Kramer";
        String s5 = "Newman";

        // do stuff with the students
        // print welcome to each
        System.out.println("Hello, "+s1);
        System.out.println("Hello, "+s2);
        System.out.println("Hello, "+s3);
        System.out.println("Hello, "+s4);
        System.out.println("Hello, "+s5);

        // This would be better in an array!


        // creating an array
        String[] students = new String[5];
        // 2 lines, 1 line

        // initial size, assign values later
        students[0] = "Jerry";
        students[1] = "Elaine";
        students[2] = "George";
        students[3] = "Kramer";
        students[4] = "Newman";

        // initialize literal values
        // - Strings
        String[] names = {"Jerry", "Elaine"};
        // - double
        double[] grades = {88.5, 97.8, 92.2, 78.7, 80.1};

        // accessing arrays
        System.out.println("Third student equals: " + students[2]);
        System.out.println("The last student equals: " + students[students.length - 1]);
        // looping over arrays
        for (int i = 0; i < students.length ; i++) {
            System.out.println("Name: " + students[i]);
            System.out.println("Grade: " + grades[i]);
        }

        // average grade?
        double sum = 0.0;
        for (int i = 0; i < grades.length; i++) {
            sum += grades[i];
        }
        System.out.println("The average grade equals: " + (sum / grades.length));
    }
}