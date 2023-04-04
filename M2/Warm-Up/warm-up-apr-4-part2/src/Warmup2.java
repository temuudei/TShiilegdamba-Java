public class Warmup2 {
    public static void main(String[] args) {
        double radius = 5.0;
        double area = calculateCircleArea(radius);
        System.out.print("Area of the circle: " + area);
    }
    public static double calculateCircleArea(double radius) {
        double area;
        area = 3.14 * radius * radius;
        return area;
    }
}