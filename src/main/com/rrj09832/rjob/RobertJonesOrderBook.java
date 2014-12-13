package com.rrj09832.rjob;

import com.rrj09832.rjob.Market;

public class RobertJonesOrderBook
{
    public static void main(String[] args)
    {
        Market myMarket = new Market();
        myMarket.openMarket();
        myMarket.addBid(12.35, 50);
        myMarket.addBid(12.35, 60);
        myMarket.addBid(12.36, 45);
        myMarket.addBid(13.0,50);
        myMarket.addBid(12.35, 12);
        myMarket.addBid(12.34, 60);
        myMarket.printBids();

    }

}


/*
    Open market

    Add selling info

    Add buying orders

    Match and close trades
        Pricetime priority
        Report successful trades as they happen

    Close market
        Report cancelled trades
 */