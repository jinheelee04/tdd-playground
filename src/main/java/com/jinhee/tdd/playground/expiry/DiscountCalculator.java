package com.jinhee.tdd.playground.expiry;

import java.time.LocalTime;

public class DiscountCalculator {
    public int calculateDiscount(int price, Membership membership, LocalTime reservationTime){
        double discountRate = membership.getDiscountRate();
        if(LocalTime.of(6, 30).equals(reservationTime)){
            discountRate += 0.05;
        }
        return price - (int) Math.round(price * discountRate);
    }



}
