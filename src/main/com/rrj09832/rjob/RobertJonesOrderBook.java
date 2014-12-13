package com.rrj09832.rjob;

import com.rrj09832.rjob.Market;

public class RobertJonesOrderBook
{
    public static void main(String[] args)
    {
        Market myMarket = new Market();
        myMarket.openMarket();
        myMarket.addBid(21.35, 25);
        myMarket.addOffer(20.0, 30);
        myMarket.addBid(12.35, 12);
        myMarket.addBid(12.35, 15);
        myMarket.addBid(12.36, 45);
        myMarket.addBid(13.0, 11);
        myMarket.addBid(12.35, 12);
        myMarket.addBid(12.34, 40);
        myMarket.printBids();

        myMarket.addOffer(20.0, 30);
        myMarket.addOffer(20.0, 60);
        myMarket.addOffer(11.0, 30);
        myMarket.addOffer(25.0, 18);
        myMarket.addOffer(2.0, 20);
        myMarket.addOffer(2.0, 15);
        myMarket.addOffer(15.0, 17);
        myMarket.printOffers();

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