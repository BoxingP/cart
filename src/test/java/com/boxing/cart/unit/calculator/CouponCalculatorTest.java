package com.boxing.cart.unit.calculator;

import com.boxing.cart.function.InputInformation;
import com.boxing.cart.unit.information.Coupon;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CouponCalculatorTest {
    CouponCalculator couponCalculator;
    Calendar calendar14Mar1;
    Calendar calendar14Mar2;
    InputInformation mockInputInformation;
    List<Coupon> mockCouponList;

    @Before
    public void initObject() throws ParseException {
        couponCalculator = new CouponCalculator();

        Date date14Mar1 = new SimpleDateFormat("yyyy.MM.dd").parse("2014.3.1");
        calendar14Mar1 = Calendar.getInstance();
        calendar14Mar1.setTime(date14Mar1);

        Date date14Mar2 = new SimpleDateFormat("yyyy.MM.dd").parse("2014.3.2");
        calendar14Mar2 = Calendar.getInstance();
        calendar14Mar2.setTime(date14Mar2);

        mockInputInformation = mock(InputInformation.class);

        mockCouponList = new ArrayList<Coupon>();
        mockCouponList.add(new Coupon(calendar14Mar2, new BigDecimal("1000"), new BigDecimal("200")));
    }

    @Test
    public void shouldTotalPrice1000CouponValid_return_800() throws ParseException {

        when(mockInputInformation.getSettlementCalendar()).thenReturn(calendar14Mar2);
        when(mockInputInformation.getCouponList()).thenReturn(this.mockCouponList);

        assertThat(couponCalculator.calculate(new BigDecimal("1000"), mockInputInformation), is(new BigDecimal("800")));
    }

    @Test
    public void shouldTotalPrice1000CouponEmpty_return_1000() throws ParseException {

        when(mockInputInformation.getSettlementCalendar()).thenReturn(calendar14Mar2);
        when(mockInputInformation.getCouponList()).thenReturn(new ArrayList<Coupon>());

        assertThat(couponCalculator.calculate(new BigDecimal("1000"), mockInputInformation), is(new BigDecimal("1000")));
    }

    @Test
    public void shouldTotalPrice1000CouponPassed_return_1000() throws ParseException {
        List<Coupon> mockCouponList = new ArrayList<Coupon>();
        mockCouponList.add(new Coupon(calendar14Mar1, new BigDecimal("1000"), new BigDecimal("200")));

        when(mockInputInformation.getSettlementCalendar()).thenReturn(calendar14Mar2);
        when(mockInputInformation.getCouponList()).thenReturn(mockCouponList);

        assertThat(couponCalculator.calculate(new BigDecimal("1000"), mockInputInformation), is(new BigDecimal("1000")));
    }

    @Test
    public void shouldTotalPrice900LessThan1000CouponValid_return_900() throws ParseException {

        when(mockInputInformation.getSettlementCalendar()).thenReturn(calendar14Mar2);
        when(mockInputInformation.getCouponList()).thenReturn(this.mockCouponList);

        assertThat(couponCalculator.calculate(new BigDecimal("900"), mockInputInformation), is(new BigDecimal("900")));
    }
}
