package com.rrj09832.rjob;

import com.rrj09832.rjob.Order;

import java.lang.Double;

import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.List;
import java.util.LinkedList;

public class Market
{
    // Maps use List as value to hold multiple values with same hash
    private Map<Double, List<Order>> bidMap = null;
    private Map<Double, List<Order>> offerMap = null;

    private Queue<Double> bidList = null;
    private Queue<Double> offerList = null;

    // Initializes marketplace
    public Market()
    {
        bidMap = new HashMap<Double, List<Order>>();
        offerMap = new HashMap<Double, List<Order>>();

        bidList = new PriorityQueue<Double>();
        offerList = new PriorityQueue<Double>();
    }

    /*  Adds bid to map by hashing the price, then
     *  adding bid to list located in that hash bucket
     */
    public void addBid(Double price, int quantity)
    {
        List<Order> bucket = getBucket(bidMap, price);
        Order newBid = new Order(price, quantity);
        bucket.add(newBid);
        bidMap.put(newBid.getPrice(), bucket);
        bidList.add(price);
//        matchOrders();
    }

    /*  Adds offer to map by hashing the price, then
     *  adding offer to list located in that hash bucket
     */
    public void addOffer(Double price, int quantity)
    {
        List<Order> bucket = getBucket(offerMap, price);
        Order newOffer = new Order(price, quantity);
        bucket.add(newOffer);
        offerMap.put(newOffer.getPrice(), bucket);
        offerList.add(price);
//        matchOrders();
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

    // matches offers and bids based on pricetime priority
    public void matchOrders()
    {
        boolean finished = false;

        while(!finished)
        {
            /*  get copy of lowestOffer and highestBid

                If lowestOffer.price > highestBid.price || lO == null || hB == null
                    finished = true
                else
                    if highestBid.quantity > lowestOffer.quantity
                        print "trade of lowO.quantity for lowO.price per share"
                        highBid.quantity -= lowO.quantity
                        remove lowestOffer
                    else if lowestOff.quantity > highestBig.quantity
                        print "trade of highBid.quantity for lowO.price per share"
                        lowOffer.quantity -= highBid.quantity
                        remove highestBid
                    else
                        print "Trade of lowO.quantity for lowO.price per share"
                        remove lowestOffer
                        remove highestBid
             */
        }
    }

    public void openMarket()
    {
        System.out.println("The market is open for trading");
    }

    public void printBids()
    {
        System.out.println("Max bid is: " + bidList.remove());
        System.out.println("\nValues of map after iterating over it : ");

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
}