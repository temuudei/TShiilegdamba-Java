import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        final DecimalFormat df = new DecimalFormat("0.00");
        float fahreneheit = 66.8f;
        float celsius = ((fahreneheit - 32) * 5) / 9;
        System.out.println(df.format(celsius) + " celsius");
    }
}