package Bursa;

public class Demand {

    private double price;
    private int count;
    private Buyer owner;
    private volatile boolean inTransaction = false;

    public Demand(double price, int count, Buyer owner) {
        this.price = price;
        this.count = count;
        this.owner = owner;
    }

    public double getPrice() {
        return this.price;
    }

    public int getCount() {
        return this.count;
    }

    public boolean getInTransaction() {
        return this.inTransaction;
    }

    public void setInTransaction(boolean newValue) {
        this.inTransaction = newValue;
    }

    public void consume(int count) {
        if (count > this.count) {
            throw new IllegalArgumentException();
        }
        this.count -= count;
        this.owner.onDemandConsumed(count);
    }

}
