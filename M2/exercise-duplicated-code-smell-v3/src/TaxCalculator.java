public class TaxCalculator {
    public static void main(String[] args) {
        printItemTax("Electronics", 1000);
        printItemTax("Clothing", 200);
        printItemTax("Groceries", 50);
    }
    private static void printItemTax(String itemType, double price) {
        System.out.printf("%S over $%.2f have a tax of $%.2f\n",itemType, price, calculateTax(itemType, price));
    }
    public static double calculateTax(String itemType, double price) {
        double tax;
        if (itemType.equalsIgnoreCase("Electronics")) {
            tax = getTotalTax(1000, 0.15, 0.05, price);
        } else if (itemType.equalsIgnoreCase("Clothing")) {
            tax = getTotalTax(500, 0.07, 0.03, price);
        } else if (itemType.equalsIgnoreCase("Groceries")) {
            tax = getTotalTax(200, 0.05,0.02,price);
        } else {
            throw new IllegalArgumentException("Invalid item type: " + itemType);
        }
        return tax;
    }
    private static double getTotalTax(int greaterThan, double taxRate, double luxuryRate ,double price) {
        double tax;
        double baseTax = getBaseTax(taxRate, price);
        double luxuryTax = getLuxuryTax(greaterThan, luxuryRate, price);
        tax = baseTax + luxuryTax;
        return tax;
    }
    private static double getLuxuryTax(int greaterThan,double luxuryRate ,double price) {
        return price > greaterThan ? luxuryRate * price : 0.0;
    }
    private static double getBaseTax(double taxRate,double price) {
        return taxRate * price;
    }
}