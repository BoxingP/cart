package com.boxing.cart.unit.information;

import java.util.Calendar;

public class Discount extends Information {

    private Calendar discountCalendar;
    private double discountRate;
    private ItemType discountItemType;

    public Discount(Calendar discountCalendar, double discountRate, ItemType itemType) {
        this.discountCalendar = discountCalendar;
        this.discountRate = discountRate;
        this.discountItemType = itemType;
    }

    public Calendar getDiscountCalendar() {
        return discountCalendar;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public ItemType getDiscountItemType() {
        return discountItemType;
    }
}
