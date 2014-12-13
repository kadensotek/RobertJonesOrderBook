package com.rrj09832.rjob;

import com.rrj09832.rjob.Order;

import java.lang.Double;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Market
{
    // Maps use List as value to hold multiple values with same hash
    private Map<Double, List<Order>> bidMap = null;
    private Map<Double, List<Order>> offerMap = null;

    private Queue<Double> bidMaxPriceList = null;
    private Queue<Double> offerMinPriceList = null;

    // Initializes marketplace
    public Market()
    {
        bidMap = new HashMap<Double, List<Order>>();
        offerMap = new HashMap<Double, List<Order>>();

        bidMaxPriceList = new PriorityQueue<Double>(java.util.Collections.reverseOrder()); // top is max bid price
        offerMinPriceList = new PriorityQueue<Double>();  // top is min offer price
    }

    /*  Adds bid to map by hashing the price, then
     *  adding bid to list located in that hash bucket
     */
    public void addBid(double price, int quantity)
    {
        List<Order> bucket = getBucket(bidMap, price);
        Order newBid = new Order(price, quantity);
        bucket.add(newBid);
        bidMap.put(newBid.getPrice(), bucket);
        bidMaxPriceList.add(price);
        matchOrders();
    }

    /*  Adds offer to map by hashing the price, then
     *  adding offer to list located in that hash bucket
     */
    public void addOffer(double price, int quantity)
    {
        List<Order> bucket = getBucket(offerMap, price);
        Order newOffer = new Order(price, quantity);
        bucket.add(newOffer);
        offerMap.put(newOffer.getPrice(), bucket);
        offerMinPriceList.add(price);
        matchOrders();
    }

    // Returns bucket list if price match, otherwise returns new list
    private List<Order> getBucket(Map<Double, List<Order>> hashmap, Double price)
    {
        List<Order> bucket;
        if(hashmap.containsKey(price))
        {
            bucket = hashmap.get(price);
        }
        else
        {
            bucket = new LinkedList<Order>();
        }
        return bucket;
    }

    // Matches offers and bids based on pricetime priority
    public void matchOrders()
    {
        List<Order> bidBucket = null;
        List<Order> offerBucket = null;
        Double lowestOffer = null;
        Double highestBid = null;
        boolean finished = false;

        while(!finished)
        {
            highestBid = bidMaxPriceList.peek();
            lowestOffer = offerMinPriceList.peek();

            if(lowestOffer == null || highestBid == null || lowestOffer > highestBid)
            {
                // No possible trade if either list is empty or no bid higher than an offer
                finished = true;
            }
            else
            {
                // gets buckets for both maps
                bidBucket = bidMap.get(bidMaxPriceList.peek());
                offerBucket = offerMap.get(offerMinPriceList.peek());

                // Gets first element from each bucket since that's the oldest
                int bidQuantity = bidBucket.get(0).getQuantity();
                int offerQuantity = offerBucket.get(0).getQuantity();

                if(bidQuantity > offerQuantity)
                {
                    System.out.println(offerQuantity + " shares traded for " + lowestOffer + " dollars per share.");

                    // Decrement quantity in bid and close previous offer
                    bidQuantity -= offerQuantity;
                    bidBucket.get(0).setQuantity(bidQuantity);
                    offerBucket.remove(0);
                    offerMinPriceList.remove();
                }
                else if(offerQuantity > bidQuantity)
                {
                    System.out.println(bidQuantity + " shares traded for " + lowestOffer + " dollars per share.");

                    // Decrement quantity in offer and close previous bid
                    offerQuantity -= bidQuantity;
                    bidBucket.remove(0);
                    bidMaxPriceList.remove();
                    offerBucket.get(0).setQuantity(offerQuantity);
                }
                else
                {
                    System.out.println(bidQuantity + " shares traded for " + lowestOffer + " dollars per share.");

                    // Remove bid and offer because they're both closed
                    bidBucket.remove(0);
                    bidMaxPriceList.remove();
                    offerBucket.remove(0);
                    offerMinPriceList.remove();
                }
            }
        }
    }

    public void openMarket()
    {
        System.out.println("The market is open for trading");
    }

    public void printBids()
    {
        System.out.println("Values of map after iterating over it : ");

        for (Double key : bidMap.keySet())
        {

            List<Order> bucket = bidMap.get(key);

            for(Order o : bucket)
            {
                System.out.println(o.toString());
            }
            //bids.remove(key);
        }
    }

    public void printOffers()
    {
        System.out.println("Values of map after iterating over it : ");

        for (Double key : offerMap.keySet())
        {

            List<Order> bucket = offerMap.get(key);

            for(Order o : bucket)
            {
                System.out.println(o.toString());
            }
            //bids.remove(key);
        }
    }
}