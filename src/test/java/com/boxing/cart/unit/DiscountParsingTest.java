package com.boxing.cart.unit;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DiscountParsingTest {
    DiscountParsing discountParsing;

    @Before
    public void initObject() {
        discountParsing = new DiscountParsing();
    }

    @Test
    public void shouldDiscountString_return_discountMap() {
        String discount = "2013.11.11 | 0.7 | 电子";
        Map<ItemType, String[]> discountMap = discountParsing.abstractDiscount(discount);

        assertThat(discountMap.get(ItemType.ELECTRONICS), is(new String[]{"2013.11.11", "0.7"}));
    }

    @Test
    public void shouldDiscountStringContainsSeveral_return_discountMap() {
        String discount = "2013.11.11 | 0.7 | 电子\n2013.11.11 | 0.5 | 食品";
        Map<ItemType, String[]> discountMap = discountParsing.abstractDiscount(discount);

        assertThat(discountMap.get(ItemType.ELECTRONICS), is(new String[]{"2013.11.11", "0.7"}));
        assertThat(discountMap.get(ItemType.FOOD), is(new String[]{"2013.11.11", "0.5"}));
    }
}
