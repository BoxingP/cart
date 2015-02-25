package com.boxing.cart.unit.information;

import java.math.BigDecimal;
import java.util.Calendar;

public class Coupon implements Information {
    private Calendar couponCalendar;
    private BigDecimal validTotalPrice;
    private BigDecimal couponPrice;

    public Coupon(Calendar couponCalendar, BigDecimal validTotalPrice, BigDecimal couponPrice) {
        this.couponCalendar = couponCalendar;
        this.validTotalPrice = validTotalPrice;
        this.couponPrice = couponPrice;
    }

    public Calendar getCouponCalendar() {
        return couponCalendar;
    }

    public BigDecimal getValidTotalPrice() {
        return validTotalPrice;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }
}
