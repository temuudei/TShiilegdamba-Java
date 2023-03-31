package org.example.inheritanceexercise;

public class CreditCard extends Payment {
    private long accountNumber;
    private String cardVendor;

    public CreditCard(int id, double amount, long accountNumber, String cardVendor) {
        super(id, amount);
        this.accountNumber = accountNumber;
        this.cardVendor = cardVendor;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCardVendor() {
        return cardVendor;
    }

    public void setCardVendor(String cardVendor) {
        this.cardVendor = cardVendor;
    }

    @Override
    public boolean processPayment() {
        System.out.println("Processing via: " + getCardVendor() + " for $" + super.getAmount());
        return true;
    }
    @Override
    public String toString() {
        return "Payment: " + super.getId() + " Amount: " + super.getAmount() + " Type: Credit Card" + " Credit card vendor: " + getCardVendor();
    }
}
