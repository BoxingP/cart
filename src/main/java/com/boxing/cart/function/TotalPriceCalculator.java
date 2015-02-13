package com.boxing.cart.function;

import com.boxing.cart.unit.Item;
import com.boxing.cart.unit.ItemPriceCalculator;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TotalPriceCalculator {
    public String calculateTotalPrice(ArrayList<Item> itemList) {
        double totalPrice = 0d;
        ItemPriceCalculator itemPriceCalculator = new ItemPriceCalculator();
        for (Item item : itemList) {
            totalPrice += itemPriceCalculator.calculate(item);
        }
        
        return new DecimalFormat("###.##").format(totalPrice);
    }
}
