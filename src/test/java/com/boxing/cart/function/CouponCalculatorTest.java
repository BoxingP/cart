package com.boxing.cart.function;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CouponCalculatorTest {
    CouponCalculator couponCalculator;
    
    @Before
    public void initObject() {
        couponCalculator = new CouponCalculator();
    }
    
    @Test
    public void shouldTotalPrice1000CouponValid_return_800() throws ParseException {
        String couponInformation="2013.11.11\n2014.3.2 1000 200";
        assertThat(couponCalculator.calculateDeal(1000, couponInformation), is(800.d));
    }

    @Test
    public void shouldTotalPrice1000CouponEmpty_return_1000() throws ParseException {
        String couponInformation="2013.11.11\n";
        assertThat(couponCalculator.calculateDeal(1000, couponInformation), is(1000.d));
    }

    @Test
    public void shouldTotalPrice1000CouponPassed_return_1000() throws ParseException {
        String couponInformation="2013.11.11\n2013.11.10 1000 200";
        assertThat(couponCalculator.calculateDeal(1000, couponInformation), is(1000.d));
    }

    @Test
    public void shouldTotalPrice900CouponValid_return_900() throws ParseException {
        String couponInformation="2013.11.11\n2014.3.2 1000 200";
        assertThat(couponCalculator.calculateDeal(900, couponInformation), is(900.d));
    }

    @Test
    public void shouldTotalPrice1000CouponValidCouponPrice100_return_900() throws ParseException {
        String couponInformation="2013.11.11\n2014.3.2 1000 100";
        assertThat(couponCalculator.calculateDeal(1000, couponInformation), is(900.d));
    }
}
