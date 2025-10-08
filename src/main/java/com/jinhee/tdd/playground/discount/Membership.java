package com.jinhee.tdd.playground.discount;

public enum Membership {
    BASIC(0.0), SILVER(0.1), GOLD(0.2);

    private double discountRate;
    Membership(double discountRate){
        this.discountRate = discountRate;
    }
    public double getDiscountRate(){
        return discountRate;
    }
}
