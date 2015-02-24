package com.boxing.cart.unit;

import java.util.Calendar;

public class Coupon extends Information {
    private Calendar couponCalendar;
    private double validTotalPrice;
    private double couponPrice;

    public Coupon(Calendar couponCalendar, double validTotalPrice, double couponPrice) {
        this.couponCalendar = couponCalendar;
        this.validTotalPrice = validTotalPrice;
        this.couponPrice = couponPrice;
    }

    public Calendar getCouponCalendar() {
        return couponCalendar;
    }

    public double getValidTotalPrice() {
        return validTotalPrice;
    }

    public double getCouponPrice() {
        return couponPrice;
    }
}
