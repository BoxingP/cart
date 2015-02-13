package com.boxing.cart.function;

import com.boxing.cart.unit.Item;
import com.boxing.cart.unit.ItemPriceCalculator;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TotalPriceCalculator {
    public double calculateTotalPrice(ArrayList<Item> itemList) {
        double totalPrice = 0d;
        ItemPriceCalculator itemPriceCalculator = new ItemPriceCalculator();
        for (Item item : itemList) {
            totalPrice += itemPriceCalculator.calculate(item);
        }

        DecimalFormat totalPriceFormat = new DecimalFormat("#.##");
        totalPrice = Double.valueOf(totalPriceFormat.format(totalPrice));
        return totalPrice;
    }
}
