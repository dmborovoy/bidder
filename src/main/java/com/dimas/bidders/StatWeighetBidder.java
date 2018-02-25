package com.dimas.bidders;

import com.dimas.Const;
import com.dimas.Util;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
public class StatWeighetBidder extends AbstractBidder implements Bidder {

    private int counter = 0;
    private int warmUpRounds = Const.DEFAULT_WARMUP_ROUNDS;
    private int rangeBid;
    private Random random = new Random();
    private HashMap<Integer, Integer> statistics = new HashMap<>();

    public StatWeighetBidder(String name, int rangeBid, int warmUpRounds) {
        super(name);
        this.rangeBid = rangeBid;
        this.warmUpRounds = warmUpRounds;
    }

    @Override
    public int placeBid() {
        int nextBid = random.nextInt(rangeBid);
        if (counter > warmUpRounds) {
            nextBid = Util.maxValue(statistics) + 1;
            log.debug("{}", statistics);
        }
        if (!availableCash(nextBid)) {
            nextBid = 0;
        }
        ++counter;
        return nextBid;
    }

    @Override
    public void bids(int own, int other) {
        int weight = other / 2;
        Util.updateStatistics(statistics, other, weight);
        win(own, other);
    }

}

