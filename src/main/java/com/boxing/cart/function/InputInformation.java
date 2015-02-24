package com.boxing.cart.function;

import com.boxing.cart.unit.information.Coupon;
import com.boxing.cart.unit.information.Discount;
import com.boxing.cart.unit.information.Item;
import com.boxing.cart.unit.parser.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InputInformation {

    private List<Discount> discountList;
    private List<Item> itemList;
    private Calendar settlementCalendar;
    private List<Coupon> couponList;

    public InputInformation(List<Discount> discountList, List<Item> itemList, Calendar settlementCalendar, List<Coupon> couponList) throws ParseException {
        this.discountList = discountList;
        this.itemList = itemList;
        this.settlementCalendar = settlementCalendar;
        this.couponList = couponList;
    }

    public List<Discount> getDiscountList() {
        return discountList;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public Calendar getSettlementCalendar() {
        return settlementCalendar;
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public static InputInformation convertStringToInputInformation(String input) throws ParseException {
        InputInformation inputInformation = new InputInformation(new ArrayList<Discount>(), new ArrayList<Item>(), null, new ArrayList<Coupon>());

        List<Parser> parsers = new ArrayList<Parser>();
        parsers.add(new DiscountStringParser());
        parsers.add(new ItemStringParser());
        parsers.add(new CalendarStringParser());
        parsers.add(new CouponStringParser());

        String[] information = input.split("\\n");
        for (String string : information) {
            if (isStringValid(string)) {
                for (Parser parser : parsers) {
                    inputInformation = parser.parseInput(string, inputInformation);
                }
            }
        }

        return inputInformation;
    }

    private static boolean isStringValid(String string) {
        return string != null && !string.equals("");
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void setDiscountList(List<Discount> discountList) {
        this.discountList = discountList;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.couponList = couponList;
    }

    public void setSettlementCalendar(Calendar settlementCalendar) {
        this.settlementCalendar = settlementCalendar;
    }
}
