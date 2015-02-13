package com.boxing.cart.unit;

public class Item {
    private int itemAmount;
    private String itemName;
    private double itemUnitPrice;

    public int getItemAmount() {
        return itemAmount;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemUnitPrice() {
        return itemUnitPrice;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemUnitPrice(double itemUnitPrice) {
        this.itemUnitPrice = itemUnitPrice;
    }
}
