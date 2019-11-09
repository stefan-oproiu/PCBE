package Bursa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StockMarket {

    private volatile List<Supply> supplies;
    private volatile HashMap<Demand, Thread> demands;

    public StockMarket() {
        this.supplies = new ArrayList<>();
        this.demands = new HashMap<>();
    }

    public synchronized void addSupply(Supply supply) {
        List<Supply> newState = new ArrayList<Supply>(this.supplies);
        newState.add(supply);
        this.supplies = newState;
    }

    public synchronized void removeSupply(Supply supply) {
        ArrayList<Supply> newState = new ArrayList<Supply>(this.supplies);
        newState.remove(supply);
        this.supplies = newState;
    }

    public synchronized void addDemand(Demand demand) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (Supply supply : supplies) {
                        supply.tryBuy(demand);
                        if (supply.getCount() == 0) {
                            removeSupply(supply);
                        }
                        if (demand.getCount() == 0) {
                            removeDemand(demand);
                        }
                    }
                }
            }
        });
        thread.start();
        this.demands.put(demand, thread);
    }

    public synchronized void removeDemand(Demand demand) {
        Thread thread = this.demands.get(demand);
        if(thread == null) {
            return;
        }

        thread.interrupt();
        this.demands.remove(demand);
    }
}
