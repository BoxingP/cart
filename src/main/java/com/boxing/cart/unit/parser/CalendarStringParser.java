package com.boxing.cart.unit.parser;

import com.boxing.cart.function.InputInformation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalendarStringParser extends Parser {
    @Override
    public InputInformation parseInput(String input, InputInformation inputInformation) throws ParseException {
        Calendar settlementCalendar = inputInformation.getSettlementCalendar();

        if (isSettlementString(input)) {
            settlementCalendar = extractCalendar(input);
        }

        inputInformation.setSettlementCalendar(settlementCalendar);
        return inputInformation;
    }

    private boolean isSettlementString(String input) {
        String settlementCalendarPatternString = "(\\d+)(\\.)(\\d+)(\\.)(\\d+)$";
        Pattern settlementCalendarPattern = Pattern.compile(settlementCalendarPatternString);
        Matcher matcher = settlementCalendarPattern.matcher(input);
        return matcher.find();
    }

    public static Calendar extractCalendar(String input) throws ParseException {
        Date date = new SimpleDateFormat("yyyy.MM.dd").parse(input);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }
}
