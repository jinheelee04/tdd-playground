package com.jinhee.tdd.playground.expiry;

import java.time.LocalTime;

public class DiscountCalculator {
    public int calculateDiscount(int price, Membership membership, LocalTime reservationTime){
        int discounted = price;
        if(membership == Membership.SILVER){
            discounted = (int) (price * 0.9);
        }else if(membership == Membership.GOLD){
            discounted = (int) (price * 0.8);
        }
        return discounted;
    }

}
