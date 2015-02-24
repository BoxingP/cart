package com.boxing.cart.unit;

import com.boxing.cart.function.InputInformation;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiscountStringParser extends Parser {
    @Override
    public InputInformation parseInput(String input, InputInformation inputInformation) throws ParseException {
        List<Discount> discountList = inputInformation.getDiscountList();

        if (isDiscountString(input)) {
            String[] discountInformationElements = input.split(" \\| ");

            Calendar discountCalendar = CalendarStringParser.extractCalendar(discountInformationElements[0]);
            double discountRate = Double.parseDouble(discountInformationElements[1]);
            ItemType discountItemType = ItemType.abstractItemType(discountInformationElements[2]);

            discountList.add(new Discount(discountCalendar, discountRate, discountItemType));
        }

        return new InputInformation(discountList, inputInformation.getItemList(), inputInformation.getSettlementCalendar(), inputInformation.getCouponList());
    }

    private boolean isDiscountString(String input) {
        String discountPatternString = "(\\d+)(\\.)(\\d+)(\\.)(\\d+)( \\| )(.+)( \\| )(.+)";
        Pattern discountPattern = Pattern.compile(discountPatternString);
        Matcher matcher = discountPattern.matcher(input);
        return matcher.find();
    }
}
