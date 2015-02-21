package com.boxing.cart.unit;

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
        String couponInformation = "2014.3.2 1000 200";
        Coupon coupon = new Coupon().abstractInformation(couponInformation);

        Date couponDate = new SimpleDateFormat("yyyy.MM.dd").parse("2014.3.2");
        Calendar couponCalendar = Calendar.getInstance();
        couponCalendar.setTime(couponDate);

        assertThat(coupon.getCouponCalendar(), is(couponCalendar));
        assertThat(coupon.getValidTotalPrice(), is(1000d));
        assertThat(coupon.getCouponPrice(), is(200d));
    }

}
