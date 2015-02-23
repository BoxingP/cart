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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coupon coupon = (Coupon) o;

        if (Double.compare(coupon.couponPrice, couponPrice) != 0) return false;
        if (Double.compare(coupon.validTotalPrice, validTotalPrice) != 0) return false;
        if (couponCalendar != null ? !couponCalendar.equals(coupon.couponCalendar) : coupon.couponCalendar != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
