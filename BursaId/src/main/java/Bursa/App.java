package Bursa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class App {
    public static void main(String[] args) {
        StockMarket market = new StockMarket();

        List<Seller> sellers = new ArrayList<>();
        sellers.add(new Seller(market));
        sellers.add(new Seller(market));
        sellers.add(new Seller(market));
        sellers.add(new Seller(market));

        List<Buyer> buyers = new ArrayList<>();
        buyers.add(new Buyer(market));
        buyers.add(new Buyer(market));
        buyers.add(new Buyer(market));
        buyers.add(new Buyer(market));

        Random rnd = new Random();

        for(Seller s: sellers) {
            new Thread(new Runnable(){
                @Override
                public void run() {
                    while(true) {
                        try {
                            Thread.sleep(1000);
                            s.putOnSale(rnd.nextInt(2) + 1, rnd.nextInt(4) + 1);
                        }
                        catch(InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        for(Buyer b: buyers) {
            new Thread(new Runnable(){
                @Override
                public void run() {
                    while(true) {
                        try {
                            Thread.sleep(1000);
                            b.addDemand(rnd.nextInt(2) + 1, rnd.nextInt(4) + 1);
                        }
                        catch(InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        try{
            System.in.read();
        }
        catch(Exception ex) {}
    }
}
