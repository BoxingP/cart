package com.boxing.cart.unit;

public class ItemPriceCalculator {
    public double calculate(Item item) {
        return item.getItemUnitPrice()*item.getItemAmount();
    }
}
