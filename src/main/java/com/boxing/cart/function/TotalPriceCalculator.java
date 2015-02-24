package com.boxing.cart.function;

import com.boxing.cart.unit.calculator.Calculator;
import com.boxing.cart.unit.calculator.CouponCalculator;
import com.boxing.cart.unit.calculator.DiscountCalculator;

import java.text.DecimalFormat;

public class TotalPriceCalculator {
    public double calculateTotalPrice(InputInformation inputInformation) {
        double totalPrice = 0d;

        Calculator discountCalculator = new DiscountCalculator();
        totalPrice = discountCalculator.calculate(totalPrice, inputInformation);

        Calculator couponCalculator = new CouponCalculator();
        totalPrice = couponCalculator.calculate(totalPrice, inputInformation);

        DecimalFormat totalPriceFormat = new DecimalFormat("0.00");
        totalPriceFormat.format(totalPrice);
        return Double.parseDouble(totalPriceFormat.format(totalPrice));
    }
}
