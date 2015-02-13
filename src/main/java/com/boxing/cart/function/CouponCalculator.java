package com.boxing.cart.function;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CouponCalculator {
    public double calculateDeal(double totalPrice, String couponInformation) throws ParseException {
        String[] coupon = couponInformation.split("\\n");

        if (isCouponEmpty(coupon)) return totalPrice;

        String[] couponElements = coupon[1].split(" ");
        String couponDay = couponElements[0];
        double totalOverPrice = Double.parseDouble(couponElements[1]);
        double couponPrice = Double.parseDouble(couponElements[2]);

        if (totalPrice >= totalOverPrice && isCouponValid(coupon[0], couponDay)) {
            totalPrice -= couponPrice;
        }
        return totalPrice;
    }

    private boolean isCouponValid(String settlementDay, String couponDay) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

        Date settlementDate = dateFormat.parse(settlementDay);
        Calendar settlementCalendar = Calendar.getInstance();
        settlementCalendar.setTime(settlementDate);

        Date couponDate = dateFormat.parse(couponDay);
        Calendar couponCalendar = Calendar.getInstance();
        couponCalendar.setTime(couponDate);

        return settlementCalendar.before(couponCalendar);
    }

    private boolean isCouponEmpty(String[] coupon) {
        return coupon.length == 1;
    }
}
