package com.boxing.cart.unit;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class ItemTypeTest {
    @Test
    public void shouldInputDianZi_return_ELECTRONICS() {
        assertThat(ItemType.abstractItemType("电子"), is(ItemType.ELECTRONICS));
    }

    @Test
    public void shouldInputShiPin_return_FOOD() {
        assertThat(ItemType.abstractItemType("食品"), is(ItemType.FOOD));
    }

    @Test
    public void shouldInputRiYongPin_return_DAILY_NECESSITIES() {
        assertThat(ItemType.abstractItemType("日用品"), is(ItemType.DAILY_NECESSITIES));
    }

    @Test
    public void shouldInputJiuLei_return_ALCOHOL() {
        assertThat(ItemType.abstractItemType("酒类"), is(ItemType.ALCOHOL));
    }

    @Test
    public void shouldInputJiaJu_return_null() {
        assertThat(ItemType.abstractItemType("家具"), is(nullValue()));
    }
}
