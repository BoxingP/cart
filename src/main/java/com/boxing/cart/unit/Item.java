package com.boxing.cart.unit;

import java.util.Arrays;

public class Item {
    private int itemAmount;
    private String itemName;
    private double itemUnitPrice;
    private ItemType itemType;
    private double discount;

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
        setItemType(this.itemName);
    }

    public void setItemUnitPrice(double itemUnitPrice) {
        this.itemUnitPrice = itemUnitPrice;
    }

    public ItemType getItemType() {
        return itemType;
    }

    private void setItemType(String itemName) {
        for (ItemType itemType : ItemType.values()) {
            if (Arrays.asList(itemType.getItems()).contains(itemName)) {
                this.itemType = itemType;
                break;
            }
        }
    }

    public void setItemDiscount(double discount) {
        this.discount=discount;
    }

    public double getItemDiscount() {
        return discount;
    }
}
