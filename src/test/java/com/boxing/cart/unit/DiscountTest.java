package com.boxing.cart.unit;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DiscountTest {

    @Test
    public void shouldStringDiscount_return_DiscountObject() throws ParseException {
        String discountInformation = "2013.11.11 | 0.7 | 电子";
        Discount discount = Discount.abstractInformation(discountInformation);

        Date discountDate = new SimpleDateFormat("yyyy.MM.dd").parse("2013.11.11");
        Calendar discountCalendar = Calendar.getInstance();
        discountCalendar.setTime(discountDate);

        assertThat(discount.getDiscountCalendar(), is(discountCalendar));
        assertThat(discount.getDiscountRate(), is(0.7));
        assertThat(discount.getDiscountItemType(), is(ItemType.ELECTRONICS));
    }

}
