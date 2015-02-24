package com.boxing.cart.unit.parser;

import com.boxing.cart.function.InputInformation;
import com.boxing.cart.unit.information.Discount;
import com.boxing.cart.unit.information.ItemType;

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

        inputInformation.setDiscountList(discountList);
        return inputInformation;
    }

    private boolean isDiscountString(String input) {
        String discountPatternString = "(\\d+)(\\.)(\\d+)(\\.)(\\d+)( \\| )(.+)( \\| )(.+)";
        Pattern discountPattern = Pattern.compile(discountPatternString);
        Matcher matcher = discountPattern.matcher(input);
        return matcher.find();
    }
}
