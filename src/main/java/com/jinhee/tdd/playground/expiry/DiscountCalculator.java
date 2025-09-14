package com.jinhee.tdd.playground.expiry;

import java.time.LocalTime;

public class DiscountCalculator {

    public int calculateDiscount(int price, Membership membership, LocalTime reservationTime){
        double discountRate = membership.getDiscountRate()
                            + TimePolicy.getDiscountRate(reservationTime);
        return price - (int) Math.round(price * discountRate);
    }


}
