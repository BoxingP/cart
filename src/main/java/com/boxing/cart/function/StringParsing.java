package com.boxing.cart.function;

import com.boxing.cart.unit.Coupon;
import com.boxing.cart.unit.Discount;
import com.boxing.cart.unit.Item;
import com.boxing.cart.unit.ItemType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringParsing {

    public static Item extractItem(String input) {
        String[] itemInformation = input.split(" \\* | : ");

        int itemAmount = Integer.parseInt(itemInformation[0]);
        String itemName = itemInformation[1];
        double itemUnitPrice = Double.parseDouble(itemInformation[2]);

        return new Item(itemAmount, itemName, itemUnitPrice);
    }

    public static Discount extractDiscount(String input) throws ParseException {
        String[] discountInformationElements = input.split(" \\| ");

        Calendar discountCalendar = extractCalendar(discountInformationElements[0]);
        double discountRate = Double.parseDouble(discountInformationElements[1]);
        ItemType discountItemType = ItemType.abstractItemType(discountInformationElements[2]);

        return new Discount(discountCalendar, discountRate, discountItemType);
    }

    public static Coupon extractCoupon(String input) throws ParseException {
        String[] couponInformation = input.split(" ");

        Calendar couponCalendar = extractCalendar(couponInformation[0]);
        double validTotalPrice = Double.parseDouble(couponInformation[1]);
        double couponPrice = Double.parseDouble(couponInformation[2]);

        return new Coupon(couponCalendar, validTotalPrice, couponPrice);
    }

    public static Calendar extractCalendar(String input) throws ParseException {
        Date date = new SimpleDateFormat("yyyy.MM.dd").parse(input);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }
}
