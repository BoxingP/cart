package com.boxing.cart.unit;

import com.boxing.cart.function.InputInformation;
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

public class CouponStringParserTest {
    CouponStringParser couponStringParser;
    InputInformation mockInputInformation;
    Calendar calendar2014Jan1;

    @Before
    public void initObject() throws ParseException {
        couponStringParser = new CouponStringParser();
        mockInputInformation = mock(InputInformation.class);
        when(mockInputInformation.getCouponList()).thenReturn(new ArrayList<Coupon>());

        Date date2014Jan1 = new SimpleDateFormat("yyyy.MM.dd").parse("2014.01.01");
        calendar2014Jan1 = Calendar.getInstance();
        calendar2014Jan1.setTime(date2014Jan1);
    }

    @Test
    public void shouldInputCouponString_return_CouponList() throws ParseException {
        String input="2014.1.1 1000 200";
        InputInformation inputInformation = couponStringParser.parseInput(input, mockInputInformation);

        assertThat(inputInformation.getCouponList().size(), is(1));

        Coupon actualCoupon = inputInformation.getCouponList().get(0);

        assertThat(actualCoupon.getCouponCalendar(), is(calendar2014Jan1));
        assertThat(actualCoupon.getValidTotalPrice(), is(1000d));
        assertThat(actualCoupon.getCouponPrice(), is(200d));
    }

    @Test
    public void shouldInputNotCouponString_return_emptyCouponList() throws ParseException {
        String input="2014.11.11";
        InputInformation inputInformation = couponStringParser.parseInput(input, mockInputInformation);

        assertThat(inputInformation.getCouponList().size(), is(0));
    }
}
