public class Invoice {
    private String item;
    private int quantity;
    private double price;
    private float taxRate;

    public Invoice(String item, int quantity, double price, float taxRate) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
        this.taxRate = taxRate;
    }

    public double calculateTotal() {
        return price * quantity;
    }
    public double calculateTaxRate() {
        return taxRate;
    }
    public double calculateSubtotal() {
        return calculateTotal() + (calculateTotal() * calculateTaxRate());
    }
}
