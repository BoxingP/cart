package com.boxing.cart.unit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Coupon {
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

    public static Coupon abstractInformation(String input) throws ParseException {
        String[] couponInformation = input.split(" ");

        Calendar couponCalendar = abstractCalendar(couponInformation[0]);
        double validTotalPrice = Double.parseDouble(couponInformation[1]);
        double couponPrice = Double.parseDouble(couponInformation[2]);

        return new Coupon(couponCalendar, validTotalPrice, couponPrice);
    }

    private static Calendar abstractCalendar(String input) throws ParseException {
        Date settlementDate = new SimpleDateFormat("yyyy.MM.dd").parse(input);
        Calendar settlementCalendar = Calendar.getInstance();
        settlementCalendar.setTime(settlementDate);

        return settlementCalendar;
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
