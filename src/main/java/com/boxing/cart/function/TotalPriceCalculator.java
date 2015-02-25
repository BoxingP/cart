package com.boxing.cart.function;

import com.boxing.cart.unit.calculator.Calculator;
import com.boxing.cart.unit.calculator.CouponCalculator;
import com.boxing.cart.unit.calculator.DiscountCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TotalPriceCalculator {
    public BigDecimal calculateTotalPrice(InputInformation inputInformation) {
        BigDecimal totalPrice = new BigDecimal("0");

        List<Calculator> calculators = new ArrayList<Calculator>();
        calculators.add(new DiscountCalculator());
        calculators.add(new CouponCalculator());
        
        for (Calculator calculator: calculators) {
            totalPrice = calculator.calculate(totalPrice, inputInformation);
        }
        
        return totalPrice;
    }
}
