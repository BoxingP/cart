package com.boxing.cart.system;

import com.boxing.cart.function.CouponCalculator;
import com.boxing.cart.function.InputParsing;
import com.boxing.cart.function.TotalPriceCalculator;
import com.boxing.cart.unit.Item;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class Cart {
    public String showTotalPrice(String input) throws ParseException {
        double totalPrice = 0d;

        InputParsing inputParsing = new InputParsing();
        Map<String, Object> inputInformation = inputParsing.abstractInformation(input);

        TotalPriceCalculator totalPriceCalculator = new TotalPriceCalculator();
        totalPrice += totalPriceCalculator.calculateTotalPrice((ArrayList<Item>) inputInformation.get("Item"));

        CouponCalculator couponCalculator = new CouponCalculator();
        totalPrice = couponCalculator.calculateDeal(totalPrice, (Calendar) inputInformation.get("Settlement"), (String[]) inputInformation.get("Coupon"));
        
        DecimalFormat totalPriceFormat = new DecimalFormat("0.00");
        return totalPriceFormat.format(totalPrice);
    }
}
