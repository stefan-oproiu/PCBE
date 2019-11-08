package Bursa;

public class Seller {

    private StockMarket market;
    private double balance;
    private static int currentId = 1;
    private int id;

    public Seller(StockMarket market) {
        this.market = market;
        this.balance = 0;
        this.id = currentId++;
    }

    public void putOnSale(double price, int count) {
        Supply supply = new Supply(price, count, this);
        this.market.addSupply(supply);
    }

    public void onSupplySold(double cost) {
        this.balance += cost;
        System.out.println(
                "Seller " + id + " has just became " + cost + " dollars richer and now has " + balance + " dollars.");
    }

}
