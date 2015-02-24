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
import static org.junit.Assert.assertThat;

public class CouponStringParserTest {
    CouponStringParser couponStringParser;
    InputInformation inputInformation;
    Calendar calendar2014Jan1;

    @Before
    public void initObject() throws ParseException {
        couponStringParser = new CouponStringParser();
        inputInformation = new InputInformation(new ArrayList<Discount>(), new ArrayList<Item>(), null, new ArrayList<Coupon>());

        Date date2014Jan1 = new SimpleDateFormat("yyyy.MM.dd").parse("2014.01.01");
        calendar2014Jan1 = Calendar.getInstance();
        calendar2014Jan1.setTime(date2014Jan1);
    }

    @Test
    public void shouldInputCouponString_return_CouponList() throws ParseException {
        String input = "2014.1.1 1000 200";
        InputInformation inputInformation = couponStringParser.parseInput(input, this.inputInformation);

        assertThat(inputInformation.getCouponList().size(), is(1));

        Coupon actualCoupon = inputInformation.getCouponList().get(0);

        assertThat(actualCoupon.getCouponCalendar(), is(calendar2014Jan1));
        assertThat(actualCoupon.getValidTotalPrice(), is(1000d));
        assertThat(actualCoupon.getCouponPrice(), is(200d));
    }

    @Test
    public void shouldInputNotCouponString_return_emptyCouponList() throws ParseException {
        String input = "2014.11.11";
        InputInformation inputInformation = couponStringParser.parseInput(input, this.inputInformation);

        assertThat(inputInformation.getCouponList().size(), is(0));
    }
}
