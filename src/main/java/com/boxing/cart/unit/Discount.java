package com.boxing.cart.unit;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount1 = (Discount) o;

        if (Double.compare(discount1.discountRate, discountRate) != 0) return false;
        if (discountCalendar != null ? !discountCalendar.equals(discount1.discountCalendar) : discount1.discountCalendar != null)
            return false;
        if (discountItemType != discount1.discountItemType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return discountItemType.ordinal();
    }
}
