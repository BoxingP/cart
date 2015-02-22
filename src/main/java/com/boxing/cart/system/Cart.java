package com.boxing.cart.system;

import com.boxing.cart.function.InputInformationConverter;
import com.boxing.cart.function.TotalPriceCalculator;

import java.text.DecimalFormat;
import java.text.ParseException;

public class Cart {
    public String showTotalPrice(String input) throws ParseException {
        double totalPrice = 0d;

        InputInformationConverter inputInformationConverter = new InputInformationConverter(input);

        TotalPriceCalculator totalPriceCalculator = new TotalPriceCalculator();
        totalPrice += totalPriceCalculator.calculateTotalPrice(inputInformationConverter);

        DecimalFormat totalPriceFormat = new DecimalFormat("0.00");
        return totalPriceFormat.format(totalPrice);
    }
}
