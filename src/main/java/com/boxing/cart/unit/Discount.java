package com.boxing.cart.unit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Discount {

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

    public static Discount abstractInformation(String discountInformation) throws ParseException {
        if (discountInformation.equals("")) return null;

        String[] discountInformationElements = discountInformation.split(" \\| ");
        if (discountInformationElements.length < 3) return null;

        Calendar discountCalendar = abstractDiscountCalendar(discountInformationElements[0]);
        double discountRate = abstractDiscountRate(discountInformationElements[1]);
        ItemType discountItemType = ItemType.abstractItemType(discountInformationElements[2]);

        return new Discount(discountCalendar, discountRate, discountItemType);
    }

    private static Calendar abstractDiscountCalendar(String discountInformation) throws ParseException {
        Date date = new SimpleDateFormat("yyyy.MM.dd").parse(discountInformation);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }

    private static double abstractDiscountRate(String input) {
        return Double.parseDouble(input);
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
