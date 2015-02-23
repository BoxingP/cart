package com.boxing.cart.function;

import com.boxing.cart.unit.Coupon;
import com.boxing.cart.unit.Discount;
import com.boxing.cart.unit.Item;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        List<Discount> discountList = new ArrayList<Discount>();
        List<Item> itemList = new ArrayList<Item>();
        Calendar settlementCalendar = null;
        List<Coupon> couponList = new ArrayList<Coupon>();

        String[] information = input.split("\\n");
        for (String string : information) {
            if (isStringValid(string)) {
                if (isItemString(string)) {
                    itemList.add(StringParsing.extractItem(string));
                    continue;
                }
                if (isDiscountString(string)) {
                    discountList.add(StringParsing.extractDiscount(string));
                    continue;
                }
                if (isCouponString(string)) {
                    couponList.add(StringParsing.extractCoupon(string));
                    continue;
                }
                if (isSettlementString(string)) {
                    settlementCalendar = StringParsing.extractCalendar(string);
                }
            }
        }

        if (discountList.isEmpty()) discountList = null;
        if (couponList.isEmpty()) couponList = null;

        return new InputInformation(discountList, itemList, settlementCalendar, couponList);
    }

    private static boolean isStringValid(String string) {
        return string != null && !string.equals("");
    }

    private static boolean isItemString(String input) {
        String itemPatternString = "(\\d+)( \\* )(.+)( : )(\\d+)";
        Pattern itemPattern = Pattern.compile(itemPatternString);
        Matcher matcher = itemPattern.matcher(input);
        return matcher.find();
    }

    private static boolean isDiscountString(String input) {
        String discountPatternString = "(\\d+)(\\.)(\\d+)(\\.)(\\d+)( \\| )(.+)( \\| )(.+)";
        Pattern discountPattern = Pattern.compile(discountPatternString);
        Matcher matcher = discountPattern.matcher(input);
        return matcher.find();
    }

    private static boolean isCouponString(String input) {
        String couponPatternString = "(\\d+)(\\.)(\\d+)(\\.)(\\d+)( )(\\d+)( )(\\d+)";
        Pattern couponPattern = Pattern.compile(couponPatternString);
        Matcher matcher = couponPattern.matcher(input);
        return matcher.find();
    }

    private static boolean isSettlementString(String input) {
        String settlementCalendarPatternString = "(\\d+)(\\.)(\\d+)(\\.)(\\d+)";
        Pattern settlementCalendarPattern = Pattern.compile(settlementCalendarPatternString);
        Matcher matcher = settlementCalendarPattern.matcher(input);
        return matcher.find();
    }
}
