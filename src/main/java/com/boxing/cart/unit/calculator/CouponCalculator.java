package com.boxing.cart.unit.calculator;

import com.boxing.cart.function.InputInformation;
import com.boxing.cart.unit.information.Coupon;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

public class CouponCalculator implements Calculator {

    @Override
    public BigDecimal calculate(BigDecimal totalPrice, InputInformation inputInformation) {

        List<Coupon> couponList = inputInformation.getCouponList();
        Calendar settlementCalendar = inputInformation.getSettlementCalendar();

        if (isListValid(couponList)) {
            totalPrice = subtractCouponPrice(totalPrice, couponList, settlementCalendar);
        }

        return totalPrice;
    }

    private boolean isListValid(List<Coupon> couponList) {
        return !couponList.isEmpty();
    }

    private BigDecimal subtractCouponPrice(BigDecimal totalPrice, List<Coupon> couponList, Calendar settlementCalendar) {

        for (Coupon coupon : couponList) {
            if (isCouponValid(totalPrice, coupon, settlementCalendar)) {
                totalPrice = totalPrice.subtract(coupon.getCouponPrice());
            }
        }

        return totalPrice;
    }

    private boolean isCouponValid(BigDecimal totalPrice, Coupon coupon, Calendar settlementCalendar) {
        return (!settlementCalendar.after(coupon.getCouponCalendar())) && (totalPrice.compareTo(coupon.getValidTotalPrice()) >= 0);
    }
}
