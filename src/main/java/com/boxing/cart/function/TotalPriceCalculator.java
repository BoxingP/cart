package com.boxing.cart.function;

import com.boxing.cart.unit.Calculator;
import com.boxing.cart.unit.CouponCalculator;
import com.boxing.cart.unit.DiscountCalculator;

import java.text.DecimalFormat;

public class TotalPriceCalculator {
    public double calculateTotalPrice(InputInformationConverter inputInformationConverter) {
        double totalPrice = 0d;
        
        Calculator discountCalculator = new DiscountCalculator();
        totalPrice = discountCalculator.calculate(totalPrice, inputInformationConverter);
        
        Calculator couponCalculator = new CouponCalculator();
        totalPrice = couponCalculator.calculate(totalPrice, inputInformationConverter);

        DecimalFormat totalPriceFormat = new DecimalFormat("0.00");
        totalPriceFormat.format(totalPrice);
        return Double.parseDouble(totalPriceFormat.format(totalPrice));
    }
}
