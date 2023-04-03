package org.example;

public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        Teacher teacher = new Teacher();
        ConsoleIO.display("Welcome to Better School Tracker!");

        student.setFirstName(ConsoleIO.promptString("Enter Student First Name"));
        student.setLastName(ConsoleIO.promptString("Enter Student Last Name"));
        student.setGPA(ConsoleIO.promptDouble("Enter Student's GPA"));
        teacher.setHomeroomTeacherFirstName(ConsoleIO.promptString("Enter Homeroom teacher's first Name"));
        teacher.setHomeroomTeacherLastName(ConsoleIO.promptString("Enter Homeroom teacher's last name"));
        student.setTeacher(teacher);

        System.out.println("Student: " + student.getFirstName() + " " + student.getLastName());
        System.out.println("GPA: " + student.getGPA());
        System.out.println("Homeroom Teacher: " + student.getTeacher().getHomeroomTeacherFirstName() + " " + student.getTeacher().getHomeroomTeacherLastName());

    }
}