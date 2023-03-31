package org.example.inheritanceexercise;

public class GiftCard extends Payment {
    private String accountNumber;
    private double balance;

    public int getLotaltyPoints() {
        return lotaltyPoints;
    }

    public void setLotaltyPoints(int lotaltyPoints) {
        this.lotaltyPoints = lotaltyPoints;
    }

    private int lotaltyPoints;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getLoyaltyPointsAccumlated() {
        return loyaltyPointsAccumlated;
    }

    public void setLoyaltyPointsAccumlated(int loyaltyPointsAccumlated) {
        this.loyaltyPointsAccumlated = loyaltyPointsAccumlated;
    }

    private int loyaltyPointsAccumlated;

    public GiftCard(int id, double amount, String accountNumber, double balance, int loyaltyPointsAccumlated) {
        super(id, amount);
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.loyaltyPointsAccumlated = loyaltyPointsAccumlated;
    }

    @Override
    public boolean processPayment() {
        System.out.println("Processing gifts card and adding loyalty points..");
        if (balance >= super.getAmount()) {
            balance -= super.getAmount();
            setBalance(balance);
            lotaltyPoints = 100 * (int) super.getAmount();
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return "Payment: " + super.getId() + " Amount: " + super.getAmount() + " Type: Gift card Balance: " + getBalance() + " Loyalty points: " + getLoyaltyPointsAccumlated();
    }
}
