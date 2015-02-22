package com.boxing.cart.unit;

import com.boxing.cart.function.InputInformationConverter;
import org.junit.Before;
import org.junit.Test;

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
    InputInformationConverter mockInputInformationConverter;
    
    @Before
    public void initObject() throws ParseException {
        couponCalculator = new CouponCalculator();

        Date date14Mar1 = new SimpleDateFormat("yyyy.MM.dd").parse("2014.3.1");
        calendar14Mar1 = Calendar.getInstance();
        calendar14Mar1.setTime(date14Mar1);
        
        Date date14Mar2 = new SimpleDateFormat("yyyy.MM.dd").parse("2014.3.2");
        calendar14Mar2 = Calendar.getInstance();
        calendar14Mar2.setTime(date14Mar2);

        mockInputInformationConverter = mock(InputInformationConverter.class);
    }
    
    @Test
    public void shouldTotalPrice1000CouponValid_return_800() throws ParseException {
        List<Coupon> mockCouponList = new ArrayList<Coupon>();
        mockCouponList.add(new Coupon(calendar14Mar2, 1000d, 200d));
        
        when(mockInputInformationConverter.getSettlementCalendar()).thenReturn(calendar14Mar2);
        when(mockInputInformationConverter.getCoupon()).thenReturn(mockCouponList);
        
        assertThat(couponCalculator.calculate(1000, mockInputInformationConverter), is(800.d));
    }

    @Test
    public void shouldTotalPrice1000CouponEmpty_return_1000() throws ParseException {

        when(mockInputInformationConverter.getSettlementCalendar()).thenReturn(calendar14Mar2);
        when(mockInputInformationConverter.getCoupon()).thenReturn(null);

        assertThat(couponCalculator.calculate(1000, mockInputInformationConverter), is(1000.d));
    }

    @Test
    public void shouldTotalPrice1000CouponPassed_return_1000() throws ParseException {
        List<Coupon> mockCouponList = new ArrayList<Coupon>();
        mockCouponList.add(new Coupon(calendar14Mar1, 1000d, 200d));

        when(mockInputInformationConverter.getSettlementCalendar()).thenReturn(calendar14Mar2);
        when(mockInputInformationConverter.getCoupon()).thenReturn(mockCouponList);

        assertThat(couponCalculator.calculate(1000, mockInputInformationConverter), is(1000.d));
    }

    @Test
    public void shouldTotalPrice900LessThan1000CouponValid_return_900() throws ParseException {
        List<Coupon> mockCouponList = new ArrayList<Coupon>();
        mockCouponList.add(new Coupon(calendar14Mar2, 1000d, 200d));

        when(mockInputInformationConverter.getSettlementCalendar()).thenReturn(calendar14Mar2);
        when(mockInputInformationConverter.getCoupon()).thenReturn(mockCouponList);

        assertThat(couponCalculator.calculate(900, mockInputInformationConverter), is(900.d));
    }
}
