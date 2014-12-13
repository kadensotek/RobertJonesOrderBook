package com.rrj09832.rjob;

import com.rrj09832.rjob.Market;

import java.lang.StringBuilder;

public class RobertJonesOrderBook
{
    public static void main(String[] args)
    {
        printInput();
        Market myMarket = new Market();
        myMarket.openMarket();

        myMarket.addOffer(20.31, 100);
        myMarket.addOffer(20.25, 100);
        myMarket.addOffer(20.31, 200);
        myMarket.addBid(20.15, 100);
        myMarket.addBid(20.22, 200);
        myMarket.addBid(20.15, 200);
        myMarket.addBid(20.35, 250);
        myMarket.addOffer(20.09, 150);

        myMarket.closeMarket();

    }

    public static void printInput()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Order of input orders:\n");
        sb.append("Offer: 100 shares at $20.31 per share.\n");
        sb.append("Offer: 100 shares at $20.25 per share.\n");
        sb.append("Offer: 200 shares at $20.31 per share.\n");
        sb.append("Bid: 100 shares at $20.15 per share.\n");
        sb.append("Bid: 200 shares at $20.22 per share.\n");
        sb.append("Bid: 200 shares at $20.35 per share.\n");
        sb.append("Offer: 150 shares at $20.09 per share.\n");
        System.out.println(sb.toString());
    }
}