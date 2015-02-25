package com.boxing.cart.unit.parser;

import com.boxing.cart.function.InputInformation;
import com.boxing.cart.unit.information.Coupon;
import com.boxing.cart.unit.information.Discount;
import com.boxing.cart.unit.information.Item;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CouponStringParserTest {
    CouponStringParser couponStringParser;
    Calendar calendar2014Jan1;

    @Before
    public void initObject() throws ParseException {
        couponStringParser = new CouponStringParser();

        Date date2014Jan1 = new SimpleDateFormat("yyyy.MM.dd").parse("2014.01.01");
        calendar2014Jan1 = Calendar.getInstance();
        calendar2014Jan1.setTime(date2014Jan1);
    }

    @Test
    public void shouldInputCouponString_return_CouponList() throws ParseException {
        String input = "2014.1.1 1000 200";
        InputInformation inputInformation = new InputInformation(new ArrayList<Discount>(), new ArrayList<Item>(), null, new ArrayList<Coupon>());
        couponStringParser.parseInput(input, inputInformation);

        assertThat(inputInformation.getCouponList().size(), is(1));

        Coupon actualCoupon = inputInformation.getCouponList().get(0);

        assertThat(actualCoupon.getCouponCalendar(), is(calendar2014Jan1));
        assertThat(actualCoupon.getValidTotalPrice(), is(new BigDecimal("1000")));
        assertThat(actualCoupon.getCouponPrice(), is(new BigDecimal("200")));
    }

    @Test
    public void shouldInputNotCouponString_return_emptyCouponList() throws ParseException {
        String input = "2014.11.11";
        InputInformation inputInformation = new InputInformation(new ArrayList<Discount>(), new ArrayList<Item>(), null, new ArrayList<Coupon>());
        couponStringParser.parseInput(input, inputInformation);

        assertThat(inputInformation.getCouponList().size(), is(0));
    }
}
