package com.dimas.bidders;

import com.dimas.Const;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractBidder implements Bidder {

    protected int quantity;
    protected int cash;
    protected String name;

    public AbstractBidder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void init(int quantity, int cash) {
        this.quantity = quantity;
        this.cash = cash;
    }

    protected boolean availableCash(int nextBid) {
        return (cash - nextBid) > 0;
    }

    protected void calculateBalance(int bid, int q) {
        quantity += q;
        cash -= bid;
    }

    protected void win(int own, int other) {
        if (own > other) {
            calculateBalance(own, Const.WIN_QUANTITY);
        } else if (own < other) {
            calculateBalance(own, Const.lOOS_QUANTITY);
        } else {
            calculateBalance(own, Const.DRAW_QUANTITY);
        }
    }

    @Override
    public void bids(int own, int other) {
        win(own, other);
    }
}

