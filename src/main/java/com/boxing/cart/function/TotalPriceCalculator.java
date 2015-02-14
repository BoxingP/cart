package com.boxing.cart.function;

import com.boxing.cart.unit.Item;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TotalPriceCalculator {
    public double calculateTotalPrice(ArrayList<Item> itemList) {
        double totalPrice = 0d;
        for (Item item : itemList) {
            totalPrice += calculate(item);
        }

        DecimalFormat totalPriceFormat = new DecimalFormat("#.##");
        totalPrice = Double.valueOf(totalPriceFormat.format(totalPrice));
        return totalPrice;
    }

    private double calculate(Item item) {
        return item.getItemUnitPrice()*item.getItemAmount();
    }
}
