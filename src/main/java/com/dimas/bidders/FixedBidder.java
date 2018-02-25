package com.dimas.bidders;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FixedBidder extends AbstractBidder implements Bidder {

    private int fixedBid;

    public FixedBidder(String name, int fixedBid) {
        super(name);
        this.fixedBid = fixedBid;
    }

    @Override
    public int placeBid() {
        int nextBid = fixedBid;
        if (!availableCash(nextBid)) {
            nextBid = 0;
        }
        return nextBid;
    }

}

