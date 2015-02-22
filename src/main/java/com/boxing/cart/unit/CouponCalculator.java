package com.boxing.cart.unit;

import com.boxing.cart.function.InputInformationConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CouponCalculator extends Calculator {
//    public double calculateDeal(double totalPrice, Calendar settlementCalendar, String[] couponInformation) throws ParseException {
//
//        if (couponInformation != null && isCouponValid(totalPrice, settlementCalendar, couponInformation)) {
//            totalPrice -= Double.parseDouble(couponInformation[2]);
//        }
//
//        return totalPrice;
//    }
//
//    private boolean isCouponValid(double totalPrice, Calendar settlementCalendar, String[] couponInformation) throws ParseException {
//        Calendar couponCalendar = abstractCalendar(couponInformation[0]);
//        return (!settlementCalendar.after(couponCalendar)) && (totalPrice >= Double.parseDouble(couponInformation[1]));
//    }
//
//    private Calendar abstractCalendar(String input) throws ParseException {
//        Date settlementDate = new SimpleDateFormat("yyyy.MM.dd").parse(input);
//        Calendar settlementCalendar = Calendar.getInstance();
//        settlementCalendar.setTime(settlementDate);
//
//        return settlementCalendar;
//    }

    @Override
    public double calculate(double totalPrice, InputInformationConverter inputInformationConverter) {

        List<Coupon> couponList = inputInformationConverter.getCoupon();
        Calendar settlementCalendar = inputInformationConverter.getSettlementCalendar();

        if (couponList != null) {
            totalPrice = subtractCouponPrice(totalPrice, couponList, settlementCalendar);
        }

        return totalPrice;
    }

    private double subtractCouponPrice(double totalPrice, List<Coupon> couponList, Calendar settlementCalendar) {
        for (Coupon coupon : couponList) {
            if (isCouponValid(totalPrice, coupon, settlementCalendar)) {
                totalPrice -= coupon.getCouponPrice();
            }
        }
        return totalPrice;
    }

    private boolean isCouponValid(double totalPrice, Coupon coupon, Calendar settlementCalendar) {
        return (!settlementCalendar.after(coupon.getCouponCalendar())) && (totalPrice >= coupon.getValidTotalPrice());
    }
}
