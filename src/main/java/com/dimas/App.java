package com.dimas;

import com.dimas.bidders.Bidder;
import com.dimas.bidders.FixedBidder;
import com.dimas.bidders.RandomBidder;
import com.dimas.bidders.StatBidder;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Bidder bidder1 = new FixedBidder("F1", 1);
        Bidder bidder2 = new FixedBidder("F2", 5);
        Bidder bidder3 = new RandomBidder("Random", 10);
        Bidder bidder4 = new StatBidder("Stat", 10, 20);
//        Forexxx system = new Forexxx(bidder1, bidder2);
        Forexxx system = new Forexxx(bidder2, bidder4);

        system.init(100, 1000, 100);
        system.run();
        system.printScores();
    }
}
