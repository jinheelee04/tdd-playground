package com.jinhee.tdd.playground.expiry;

import java.time.LocalTime;

public class DiscountCalculator {
    public int calculateDiscount(int price, Membership membership, LocalTime reservationTime){
        double discountRate = membership.getDiscountRate();
        if(!reservationTime.isBefore(LocalTime.of(6, 0)) && reservationTime.isBefore(LocalTime.of(9, 0))){
            discountRate += 0.05;
        }

        if(!reservationTime.isBefore(LocalTime.of(21, 0)) && reservationTime.isBefore(LocalTime.of(23, 0))){
            discountRate += 0.05;
        }
        return price - (int) Math.round(price * discountRate);
    }



}
