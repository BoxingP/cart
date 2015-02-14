package com.boxing.cart.function;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CouponCalculator {
    public double calculateDeal(double totalPrice, Calendar settlementCalendar, String[] couponInformation) throws ParseException {

        if (couponInformation != null && isCouponValid(totalPrice, settlementCalendar, couponInformation)) {
            totalPrice -= Double.parseDouble(couponInformation[2]);
        }

        return totalPrice;
    }

    private boolean isCouponValid(double totalPrice, Calendar settlementCalendar, String[] couponInformation) throws ParseException {
        Calendar couponCalendar = abstractCalendar(couponInformation[0]);
        return (!settlementCalendar.after(couponCalendar)) && (totalPrice >= Double.parseDouble(couponInformation[1]));
    }

    private Calendar abstractCalendar(String input) throws ParseException {
        Date settlementDate = new SimpleDateFormat("yyyy.MM.dd").parse(input);
        Calendar settlementCalendar = Calendar.getInstance();
        settlementCalendar.setTime(settlementDate);

        return settlementCalendar;
    }
}
