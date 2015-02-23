package com.boxing.cart.unit;

import java.util.Arrays;

public class Item extends Information {
    private int itemAmount;
    private String itemName;
    private double itemUnitPrice;
    private ItemType itemType;

    public Item(int itemAmount, String itemName, double itemUnitPrice) {
        this.itemAmount = itemAmount;
        this.itemName = itemName;
        this.itemUnitPrice = itemUnitPrice;
        setItemType(this.itemName);
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemUnitPrice() {
        return itemUnitPrice;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (itemAmount != item.itemAmount) return false;
        if (Double.compare(item.itemUnitPrice, itemUnitPrice) != 0) return false;
        if (itemName != null ? !itemName.equals(item.itemName) : item.itemName != null) return false;
        if (itemType != item.itemType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
