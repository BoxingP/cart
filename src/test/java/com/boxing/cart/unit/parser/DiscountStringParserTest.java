package com.boxing.cart.unit.parser;

import com.boxing.cart.function.InputInformation;
import com.boxing.cart.unit.information.Discount;
import com.boxing.cart.unit.information.ItemType;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DiscountStringParserTest {
    DiscountStringParser discountStringParser;
    InputInformation mockInputInformation;
    Calendar calendar2014Jan1;

    @Before
    public void initObject() throws ParseException {
        discountStringParser = new DiscountStringParser();
        mockInputInformation = mock(InputInformation.class);
        when(mockInputInformation.getDiscountList()).thenReturn(new ArrayList<Discount>());

        Date date2014Jan1 = new SimpleDateFormat("yyyy.MM.dd").parse("2014.01.01");
        calendar2014Jan1 = Calendar.getInstance();
        calendar2014Jan1.setTime(date2014Jan1);
    }

    @Test
    public void shouldInputDiscountString_return_DiscountList() throws ParseException {
        String input = "2014.01.01 | 0.7 | 食品";
        InputInformation inputInformation = discountStringParser.parseInput(input, mockInputInformation);

        assertThat(inputInformation.getDiscountList().size(), is(1));

        Discount actualDiscount = inputInformation.getDiscountList().get(0);

        assertThat(actualDiscount.getDiscountCalendar(), is(calendar2014Jan1));
        assertThat(actualDiscount.getDiscountRate(), is(0.7));
        assertThat(actualDiscount.getDiscountItemType(), is(ItemType.FOOD));
    }

    @Test
    public void shouldInputNotDiscountString_return_emptyDiscountList() throws ParseException {
        String input = "2014.11.11";
        InputInformation inputInformation = discountStringParser.parseInput(input, mockInputInformation);

        assertThat(inputInformation.getDiscountList().size(), is(0));
    }
}
