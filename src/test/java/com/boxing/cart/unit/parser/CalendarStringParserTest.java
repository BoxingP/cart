package com.boxing.cart.unit.parser;

import com.boxing.cart.function.InputInformation;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalendarStringParserTest {
    CalendarStringParser calendarStringParser;
    InputInformation mockInputInformation;
    Calendar calendar2014Jan1;

    @Before
    public void initObject() throws ParseException {
        calendarStringParser = new CalendarStringParser();
        mockInputInformation = mock(InputInformation.class);
        when(mockInputInformation.getSettlementCalendar()).thenReturn(null);

        Date date2014Jan1 = new SimpleDateFormat("yyyy.MM.dd").parse("2014.01.01");
        calendar2014Jan1 = Calendar.getInstance();
        calendar2014Jan1.setTime(date2014Jan1);
    }

    @Test
    public void shouldInputCalendarString_return_Calendar() throws ParseException {
        String input = "2014.1.1";
        InputInformation inputInformation = calendarStringParser.parseInput(input, mockInputInformation);

        assertThat(inputInformation.getSettlementCalendar(), is(calendar2014Jan1));
    }

    @Test
    public void shouldInputNotCalendarString_return_Null() throws ParseException {
        String input = "2014.1.1 1000 200";
        InputInformation inputInformation = calendarStringParser.parseInput(input, mockInputInformation);

        assertThat(inputInformation.getSettlementCalendar(), is(nullValue()));
    }
}
