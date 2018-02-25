package com.dimas.bidders;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class RandomBidder extends AbstractBidder implements Bidder {
    private int rangeBid;
    private Random random = new Random();

    public RandomBidder(String name, int rangeBid) {
        super(name);
        this.rangeBid = rangeBid;
    }

    @Override
    public int placeBid() {
        int nextBid = random.nextInt(rangeBid);
        if (!availableCash(nextBid)) {
            nextBid = 0;
        }
        return nextBid;
    }


}

