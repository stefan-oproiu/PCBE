package Bursa;

public class Buyer {

    private StockMarket market;
    private int sharesCount = 0;
    private static int currentId = 1;
    private int id;

    public Buyer(StockMarket market) {
        this.market = market;
        this.id = currentId++;
    }

    public void addDemand(double price, int count) {
        Demand demand = new Demand(price, count);
        this.market.addDemand(demand);
    }

    public void onDemandConsumed(int count) {
        this.sharesCount += count;
        System.out.println(
                "Buyer " + id + " has just bought " + count + " shares and now has " + sharesCount + " shares.");
    }

}
