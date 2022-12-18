package app.entities;

public class Payment {
    private int id;
    private int subscriptionId;
    private String date;
    private double totalAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Payment(int id, int subscriptionId, String date, double totalAmount) {
        this.id = id;
        this.subscriptionId = subscriptionId;
        this.date = date;
        this.totalAmount = totalAmount;
    }

    public Payment() {
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
