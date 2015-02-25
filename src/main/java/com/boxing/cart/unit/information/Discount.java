package com.boxing.cart.unit.information;

import java.math.BigDecimal;
import java.util.Calendar;

public class Discount implements Information {

    private Calendar discountCalendar;
    private BigDecimal discountRate;
    private ItemType discountItemType;

    public Discount(Calendar discountCalendar, BigDecimal discountRate, ItemType itemType) {
        this.discountCalendar = discountCalendar;
        this.discountRate = discountRate;
        this.discountItemType = itemType;
    }

    public Calendar getDiscountCalendar() {
        return discountCalendar;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public ItemType getDiscountItemType() {
        return discountItemType;
    }
}
