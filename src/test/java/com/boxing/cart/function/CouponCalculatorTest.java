package com.boxing.cart.function;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CouponCalculatorTest {
    CouponCalculator couponCalculator;
    Calendar calendar14Mar2;
    
    @Before
    public void initObject() throws ParseException {
        couponCalculator = new CouponCalculator();

        Date date14Mar2 = new SimpleDateFormat("yyyy.MM.dd").parse("2014.3.2");
        calendar14Mar2 = Calendar.getInstance();
        calendar14Mar2.setTime(date14Mar2);
    }
    
    @Test
    public void shouldTotalPrice1000CouponValid_return_800() throws ParseException {
        String[] couponInformation=new String[]{"2014.3.2", "1000", "200"};
        assertThat(couponCalculator.calculateDeal(1000, calendar14Mar2, couponInformation), is(800.d));
    }

    @Test
    public void shouldTotalPrice1000CouponValidCouponPrice100_return_900() throws ParseException {
        String[] couponInformation=new String[]{"2014.3.2", "1000", "100"};
        assertThat(couponCalculator.calculateDeal(1000, calendar14Mar2, couponInformation), is(900.d));
    }
    
    @Test
    public void shouldTotalPrice1000CouponEmpty_return_1000() throws ParseException {
        assertThat(couponCalculator.calculateDeal(1000, calendar14Mar2, null), is(1000.d));
    }

    @Test
    public void shouldTotalPrice1000CouponPassed_return_1000() throws ParseException {
        String[] couponInformation=new String[]{"2014.3.1", "1000", "200"};
        
        assertThat(couponCalculator.calculateDeal(1000, calendar14Mar2, couponInformation), is(1000.d));
    }

    @Test
    public void shouldTotalPrice900CouponValid_return_900() throws ParseException {
        String[] couponInformation=new String[]{"2014.3.2", "1000", "200"};
        assertThat(couponCalculator.calculateDeal(900, calendar14Mar2, couponInformation), is(900.d));
    }
}
