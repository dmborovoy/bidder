package com.dimas;

import com.dimas.bidders.AbstractBidder;
import com.dimas.bidders.Bidder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Forexxx {
    private Bidder bidder1;
    private Bidder bidder2;
    private int money1 = 0;
    private int money2 = 0;
    private int product1 = 0;
    private int product2 = 0;
    private int maxRounds = 100;

    public Forexxx(Bidder bidder1, Bidder bidder2) {
        this.bidder1 = bidder1;
        this.bidder2 = bidder2;
    }

    public void init(int quantity, int cash, int maxRounds) {
        this.bidder1.init(quantity, cash);
        this.bidder2.init(quantity, cash);
        this.money1 = cash;//don't trust bidder to calculate available balance to play
        this.money2 = cash;
        this.maxRounds = maxRounds;
    }

    public Bidder run() {
        int rounds = 0;
        while (rounds < maxRounds || !availableBalances()) {
            int bid1 = bidder1.placeBid();
            int bid2 = bidder2.placeBid();
            log.info("round# {}", rounds);
            log.info("bid1: {}, bid2: {}", bid1, bid2);
            money1 -= bid1;
            money2 -= bid2;
            if (bid1 > bid2) {
                product1 += Const.WIN_QUANTITY;
            } else if (bid1 < bid2) {
                product2 += Const.WIN_QUANTITY;
            } else {
                product1 += Const.DRAW_QUANTITY;
                product2 += Const.DRAW_QUANTITY;
            }
            bidder1.bids(bid1, bid2);
            bidder2.bids(bid2, bid1);
            rounds++;
        }
        return getWinner();
    }

    private boolean availableBalances() {
        return (money1) > 0 && (money2) > 0;
    }

    public void printScores() {
        log.info("THE END");
        log.info("The winner is: {}", getWinner().getName());
        log.info("Score: product1={}, product2={}", product1, product2);
        log.info("Balance: money1={}, money2={}", money1, money2);
    }

    private AbstractBidder getWinner() {
        return product1 > product2 ? (AbstractBidder) bidder1 : (AbstractBidder) bidder2;
    }

}


