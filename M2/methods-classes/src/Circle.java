public class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return 3.14 * (radius * radius);
    }

    public double calculateCircumference() {
        return 2 * 3.14 * radius;
    }
}
