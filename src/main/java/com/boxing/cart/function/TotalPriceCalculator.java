package com.boxing.cart.function;

import com.boxing.cart.unit.calculator.Calculator;
import com.boxing.cart.unit.calculator.CouponCalculator;
import com.boxing.cart.unit.calculator.DiscountCalculator;

import java.math.BigDecimal;

public class TotalPriceCalculator {
    public BigDecimal calculateTotalPrice(InputInformation inputInformation) {
        BigDecimal totalPrice = new BigDecimal("0");

        Calculator discountCalculator = new DiscountCalculator();
        totalPrice = discountCalculator.calculate(totalPrice, inputInformation);

        Calculator couponCalculator = new CouponCalculator();
        totalPrice = couponCalculator.calculate(totalPrice, inputInformation);

        return totalPrice;
    }
}
