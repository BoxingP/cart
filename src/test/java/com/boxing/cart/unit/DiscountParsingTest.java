package com.boxing.cart.unit;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertThat;

public class DiscountParsingTest {
    DiscountParsing discountParsing;
    Calendar calendar13Nov11;

    @Before
    public void initObject() throws ParseException {
        discountParsing = new DiscountParsing();

        Date date13Nov11 = new SimpleDateFormat("yyyy.MM.dd").parse("2013.11.11");
        calendar13Nov11 = Calendar.getInstance();
        calendar13Nov11.setTime(date13Nov11);
    }

    @Test
    public void shouldDiscountString_return_discountMap() throws ParseException {
        String discount = "2013.11.11 | 0.7 | 电子";
        Map<ItemType, Map<Calendar, Double>> discountMap = discountParsing.abstractDiscount(discount);

        assertThat(discountMap.get(ItemType.ELECTRONICS).get(calendar13Nov11), Is.<Object>is(0.7));
    }

    @Test
    public void shouldDiscountStringContainsSeveral_return_discountMap() throws ParseException {
        String discount = "2013.11.11 | 0.7 | 电子\n2013.11.11 | 0.5 | 食品";
        Map<ItemType, Map<Calendar, Double>> discountMap = discountParsing.abstractDiscount(discount);

        assertThat(discountMap.get(ItemType.ELECTRONICS).get(calendar13Nov11), Is.<Object>is(0.7));
        assertThat(discountMap.get(ItemType.FOOD).get(calendar13Nov11), Is.<Object>is(0.5));
    }

    @Test
    public void shouldDiscountStringSameProperty_return_discountMap() throws ParseException {
        String discount = "2013.11.11 | 0.7 | 电子\n2013.11.12 | 0.7 | 电子";
        Map<ItemType, Map<Calendar, Double>> discountMap = discountParsing.abstractDiscount(discount);

        Date date13Nov12 = new SimpleDateFormat("yyyy.MM.dd").parse("2013.11.12");
        Calendar calendar13Nov12 = Calendar.getInstance();
        calendar13Nov12.setTime(date13Nov12);

        assertThat(discountMap.get(ItemType.ELECTRONICS).get(calendar13Nov11), Is.<Object>is(0.7));
        assertThat(discountMap.get(ItemType.ELECTRONICS).get(calendar13Nov12), Is.<Object>is(0.7));
    }

    @Test
    public void shouldDiscountStringSamePropertySameCalendar_return_discountMap() throws ParseException {
        String discount = "2013.11.11 | 0.7 | 电子\n2013.11.11 | 0.5 | 电子";
        Map<ItemType, Map<Calendar, Double>> discountMap = discountParsing.abstractDiscount(discount);

        assertThat(discountMap.get(ItemType.ELECTRONICS).get(calendar13Nov11), Is.<Object>is(0.5));
    }
}
