package com.boxing.cart.system;

import com.boxing.cart.function.*;
import com.boxing.cart.unit.Item;

import java.util.ArrayList;

public class Cart {
    public String showTotalPrice(String input) {
        String totalPrice = "";
        
        InputParsing inputParsing = new InputParsing();
        String[] inputInformation = inputParsing.splitString(input);

        ItemListParsing itemListParsing = new ItemListParsing();
        ArrayList<Item> itemList = itemListParsing.abstractItem(inputInformation[1]);

        TotalPriceCalculator totalPriceCalculator = new TotalPriceCalculator();
        totalPrice += totalPriceCalculator.calculateTotalPrice(itemList);
        
        return totalPrice;
    }
}
