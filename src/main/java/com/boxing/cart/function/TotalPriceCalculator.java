package com.boxing.cart.function;

import com.boxing.cart.unit.calculator.Calculator;
import com.boxing.cart.unit.calculator.CouponCalculator;
import com.boxing.cart.unit.calculator.DiscountCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TotalPriceCalculator {
    private static final List<Calculator> calculators = generateCalculators();
    
    private static List<Calculator> generateCalculators() {
        List<Calculator> calculators = new ArrayList<Calculator>();
        calculators.add(new DiscountCalculator());
        calculators.add(new CouponCalculator());
        return calculators;
    }

    public BigDecimal calculateTotalPrice(InputInformation inputInformation) {
        BigDecimal totalPrice = new BigDecimal("0");

        for (Calculator calculator: calculators) {
            totalPrice = calculator.calculate(totalPrice, inputInformation);
        }
        
        return totalPrice;
    }
}
