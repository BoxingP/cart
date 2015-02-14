package com.boxing.cart.system;

import com.boxing.cart.function.*;
import com.boxing.cart.unit.DiscountParsing;
import com.boxing.cart.unit.Item;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class Cart {
    public String showTotalPrice(String input) throws ParseException {
        double totalPrice = 0d;

        InputParsing inputParsing = new InputParsing();
        String[] inputInformation = inputParsing.splitString(input);

        DiscountParsing discountParsing = new DiscountParsing();
        Map discountMap = discountParsing.abstractDiscount(inputInformation[0]);
        
        String[] settlementString = inputInformation[2].split("\\n");
        Date settlementDate = new SimpleDateFormat("yyyy.MM.dd").parse(settlementString[0]);
        Calendar settlementCalendar = Calendar.getInstance();
        settlementCalendar.setTime(settlementDate);
        
        ItemListParsing itemListParsing = new ItemListParsing();
        ArrayList<Item> itemList = itemListParsing.abstractItem(inputInformation[1], discountMap, settlementCalendar);

        TotalPriceCalculator totalPriceCalculator = new TotalPriceCalculator();
        totalPrice += totalPriceCalculator.calculateTotalPrice(itemList);

        CouponCalculator couponCalculator = new CouponCalculator();
        totalPrice = couponCalculator.calculateDeal(totalPrice, inputInformation[2]);

        DecimalFormat totalPriceFormat = new DecimalFormat("0.00");
        return totalPriceFormat.format(totalPrice);
    }
}
