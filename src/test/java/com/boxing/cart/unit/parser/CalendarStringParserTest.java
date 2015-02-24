package com.boxing.cart.unit.parser;

import com.boxing.cart.function.InputInformation;
import com.boxing.cart.unit.information.Coupon;
import com.boxing.cart.unit.information.Discount;
import com.boxing.cart.unit.information.Item;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class CalendarStringParserTest {
    CalendarStringParser calendarStringParser;
    InputInformation inputInformation;
    Calendar calendar2014Jan1;

    @Before
    public void initObject() throws ParseException {
        calendarStringParser = new CalendarStringParser();
        inputInformation = new InputInformation(new ArrayList<Discount>(), new ArrayList<Item>(), null, new ArrayList<Coupon>());

        Date date2014Jan1 = new SimpleDateFormat("yyyy.MM.dd").parse("2014.01.01");
        calendar2014Jan1 = Calendar.getInstance();
        calendar2014Jan1.setTime(date2014Jan1);
    }

    @Test
    public void shouldInputCalendarString_return_Calendar() throws ParseException {
        String input = "2014.1.1";
        InputInformation inputInformation = calendarStringParser.parseInput(input, this.inputInformation);

        assertThat(inputInformation.getSettlementCalendar(), is(calendar2014Jan1));
    }

    @Test
    public void shouldInputNotCalendarString_return_Null() throws ParseException {
        String input = "2014.1.1 1000 200";
        InputInformation inputInformation = calendarStringParser.parseInput(input, this.inputInformation);

        assertThat(inputInformation.getSettlementCalendar(), is(nullValue()));
    }
}
