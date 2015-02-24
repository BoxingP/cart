package com.boxing.cart.unit;

import com.boxing.cart.function.InputInformation;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CouponStringParser extends Parser{
    @Override
    public InputInformation parseInput(String input, InputInformation inputInformation) throws ParseException {
        List<Coupon> couponList = inputInformation.getCouponList();

        if (isCouponString(input)) {
            String[] couponInformation = input.split(" ");

            Calendar couponCalendar = CalendarStringParser.extractCalendar(couponInformation[0]);
            double validTotalPrice = Double.parseDouble(couponInformation[1]);
            double couponPrice = Double.parseDouble(couponInformation[2]);

            couponList.add(new Coupon(couponCalendar, validTotalPrice, couponPrice));
        }

        return new InputInformation(inputInformation.getDiscountList(), inputInformation.getItemList(), inputInformation.getSettlementCalendar(), couponList);
    }

    private boolean isCouponString(String input) {
        String couponPatternString = "(\\d+)(\\.)(\\d+)(\\.)(\\d+)( )(\\d+)( )(\\d+)";
        Pattern couponPattern = Pattern.compile(couponPatternString);
        Matcher matcher = couponPattern.matcher(input);
        return matcher.find();
    }
}
