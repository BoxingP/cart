package com.boxing.cart.unit.information;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CouponTest {

    @Test
    public void shouldStringCoupon_return_CouponObject() throws ParseException {
        Date date2014Mar2 = new SimpleDateFormat("yyyy.MM.dd").parse("2014.3.2");
        Calendar calendar2014Mar2 = Calendar.getInstance();
        calendar2014Mar2.setTime(date2014Mar2);

        Coupon coupon = new Coupon(calendar2014Mar2, 1000d, 200d);

        assertThat(coupon.getCouponCalendar(), is(calendar2014Mar2));
        assertThat(coupon.getValidTotalPrice(), is(1000d));
        assertThat(coupon.getCouponPrice(), is(200d));
    }
}