package com.jinhee.tdd.playground.expiry;

import java.time.LocalTime;
import java.util.Arrays;

public enum TimePolicy {
    MORNING(LocalTime.of(6, 0), LocalTime.of(9, 0), 0.05),
    NIGHT(LocalTime.of(21, 0), LocalTime.of(23, 0), 0.05)
    ;

    private final LocalTime startTime;
    private final LocalTime endTime;
    private final double discountRate;

    TimePolicy(LocalTime startTime, LocalTime endTime, double discountRate){
        this.startTime = startTime;
        this.endTime = endTime;
        this.discountRate = discountRate;
    }
    public double getDiscountRate() {
        return discountRate;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }

    public boolean isWithinTimeRange(LocalTime reservationTime){
        return !reservationTime.isBefore(startTime) && reservationTime.isBefore(endTime);
    }

    public static double getDiscountRate(LocalTime reservationTime){
        return Arrays.stream(values())
                .filter(policy->policy.isWithinTimeRange(reservationTime))
                .mapToDouble(TimePolicy::getDiscountRate)
                .sum();
    }
}
