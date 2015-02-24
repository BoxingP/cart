package com.boxing.cart.unit;

import com.boxing.cart.function.InputInformation;

import java.util.*;

public class CouponCalculator extends Calculator {

    @Override
    public double calculate(double totalPrice, InputInformation inputInformation) {

        List<Coupon> couponList = inputInformation.getCouponList();
        Calendar settlementCalendar = inputInformation.getSettlementCalendar();

        if (isListValid(couponList)) {
            totalPrice = subtractCouponPrice(totalPrice, couponList, settlementCalendar);
        }

        return totalPrice;
    }

    private boolean isListValid(List<Coupon> couponList) {
        return couponList != null && !couponList.isEmpty();
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
