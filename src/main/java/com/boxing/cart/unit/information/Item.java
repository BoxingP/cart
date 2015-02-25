package com.boxing.cart.unit.information;

import java.math.BigDecimal;
import java.util.Arrays;

public class Item implements Information {
    private int itemAmount;
    private String itemName;
    private BigDecimal itemUnitPrice;
    private ItemType itemType;

    public Item(int itemAmount, String itemName, BigDecimal itemUnitPrice) {
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

    public BigDecimal getItemUnitPrice() {
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
}
