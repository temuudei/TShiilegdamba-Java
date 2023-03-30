public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee();
        Book book = new Book();
        Circle circle = new Circle(4.12);
        System.out.println(circle.calculateArea());
        Triangle triangle = new Triangle(2.42,56.3);
        Address address = new Address();
        Invoice invoice = new Invoice("Watch", 4, 5.12, 0.10f);

    }
}