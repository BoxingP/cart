package com.boxing.cart.system;

import com.boxing.cart.function.*;
import com.boxing.cart.unit.Item;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

public class Cart {
    public String showTotalPrice(String input) throws ParseException {
        double totalPrice = 0d;

        InputParsing inputParsing = new InputParsing();
        String[] inputInformation = inputParsing.splitString(input);

        ItemListParsing itemListParsing = new ItemListParsing();
        ArrayList<Item> itemList = itemListParsing.abstractItem(inputInformation[1]);

        TotalPriceCalculator totalPriceCalculator = new TotalPriceCalculator();
        totalPrice += totalPriceCalculator.calculateTotalPrice(itemList);

        CouponCalculator couponCalculator = new CouponCalculator();
        totalPrice = couponCalculator.calculateDeal(totalPrice, inputInformation[2]);

        DecimalFormat totalPriceFormat = new DecimalFormat("#.##");
        return totalPriceFormat.format(totalPrice);
    }
}
