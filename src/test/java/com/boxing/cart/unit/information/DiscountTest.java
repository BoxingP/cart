package com.boxing.cart.unit.information;

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
        Date date2013Nov11 = new SimpleDateFormat("yyyy.MM.dd").parse("2013.11.11");
        Calendar calendar2013Nov11 = Calendar.getInstance();
        calendar2013Nov11.setTime(date2013Nov11);

        Discount discount = new Discount(calendar2013Nov11, 0.7, ItemType.ELECTRONICS);

        assertThat(discount.getDiscountCalendar(), is(calendar2013Nov11));
        assertThat(discount.getDiscountRate(), is(0.7));
        assertThat(discount.getDiscountItemType(), is(ItemType.ELECTRONICS));
    }
}