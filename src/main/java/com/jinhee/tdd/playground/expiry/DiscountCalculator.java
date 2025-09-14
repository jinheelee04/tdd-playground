package com.jinhee.tdd.playground.expiry;

import java.time.LocalTime;

public class DiscountCalculator {
    public int calculateDiscount(int price, Membership membership, LocalTime reservationTime){
        double discountRate = membership.getDiscountRate();
        if(reservationTime.isAfter(LocalTime.of(6, 0)) && reservationTime.isBefore(LocalTime.of(9, 0))){
            discountRate += 0.05;
        }
        return price - (int) Math.round(price * discountRate);
    }



}
