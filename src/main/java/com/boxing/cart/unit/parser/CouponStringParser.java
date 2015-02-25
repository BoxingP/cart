package com.boxing.cart.unit.parser;

import com.boxing.cart.function.InputInformation;
import com.boxing.cart.unit.information.Coupon;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CouponStringParser implements Parser {
    @Override
    public void parseInput(String input, InputInformation inputInformation) throws ParseException {
        if (isCouponString(input)) {
            List<Coupon> couponList = inputInformation.getCouponList();
            String[] couponInformation = input.split(" ");

            Calendar couponCalendar = CalendarStringParser.extractCalendar(couponInformation[0]);
            double validTotalPrice = Double.parseDouble(couponInformation[1]);
            double couponPrice = Double.parseDouble(couponInformation[2]);

            couponList.add(new Coupon(couponCalendar, validTotalPrice, couponPrice));
            inputInformation.setCouponList(couponList);
        }
    }

    private boolean isCouponString(String input) {
        String couponPatternString = "(\\d+)(\\.)(\\d+)(\\.)(\\d+)( )(\\d+)( )(\\d+)";
        Pattern couponPattern = Pattern.compile(couponPatternString);
        Matcher matcher = couponPattern.matcher(input);
        return matcher.find();
    }
}
