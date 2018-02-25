package com.dimas;

import com.dimas.bidders.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ForexxxIntegrationTest {

    private Forexxx system;
    private AbstractBidder bidder1;
    private AbstractBidder bidder2;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private void fire(Bidder bidder1, Bidder bidder2) {
        system = new Forexxx(bidder1, bidder2);
        system.init(100, 1000, 100);
        Bidder winner = system.run();
        system.printScores();
        assertNotNull(winner);
        assertEquals(winner, bidder2);
    }

    @Test
    public void fixedBidderWithHigherRateShouldWinFixedBidderWithLowerRate() {
        bidder1 = new FixedBidder("Fixed1", 4);
        bidder2 = new FixedBidder("Fixed2", 5);
        fire(bidder1, bidder2);
    }

    @Test
    public void statBidderShouldWinFixedBidderWithLowRate() {
        bidder1 = new FixedBidder("Fixed1", 4);
        bidder2 = new StatBidder("Stat2", 10, 20);
        fire(bidder1, bidder2);
    }

    @Test
    public void statBidderShouldWinFixedBidderWithHighRate() {
        bidder1 = new FixedBidder("Fixed1", 15);
        bidder2 = new StatBidder("Stat2", 10, 20);
        fire(bidder1, bidder2);
    }

    @Test
    public void statBidderShouldWinRandomBidder() {
        bidder1 = new RandomBidder("Random1", 10);
        bidder2 = new StatBidder("Stat2", 10, 20);
        fire(bidder1, bidder2);
    }

    @Test
    public void statWeighedBidderShouldWinRandomBidder() {
        bidder1 = new RandomBidder("Random1", 10);
        bidder2 = new StatWeighetBidder("StatW2", 10, 20);
        fire(bidder1, bidder2);
    }

    @Test
    public void statWeighedBidderVsStatBidder() {
        bidder1 = new StatBidder("Stat1", 10, 20);
        bidder2 = new StatWeighetBidder("StatW2", 10, 20);
        fire(bidder1, bidder2);
    }
}