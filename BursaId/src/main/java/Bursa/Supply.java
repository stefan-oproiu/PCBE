package Bursa;

public class Supply {

    private double price;
    private volatile int count;
    private Seller owner;
    private volatile boolean inTransaction = false;

    public Supply(double price, int count, Seller owner) {
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

    public Seller getOwner() {
        return this.owner;
    }

    public boolean getInTransaction() {
        return this.inTransaction;
    }

    public void setInTransaction(boolean newValue) {
        this.inTransaction = newValue;
    }

    public void consume(int count) {
        this.count -= count;
        this.owner.onSupplySold(count * this.getPrice());
    }

    public void tryBuy(Demand demand) {
        if (this.getInTransaction() || demand.getInTransaction()) {
            return;
        }

        synchronized (this) {
            this.setInTransaction(true);
            demand.setInTransaction(true);

            if (demand.getPrice() != this.getPrice() || this.getCount() == 0 || demand.getCount() == 0) {
                return;
            }

            int min = Math.min(this.getCount(), demand.getCount());

            this.consume(min);
            demand.consume(min);
        }

        this.setInTransaction(false);
        demand.setInTransaction(false);

    }
}
