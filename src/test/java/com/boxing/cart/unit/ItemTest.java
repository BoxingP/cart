package com.boxing.cart.unit;

import org.junit.Test;

import java.text.ParseException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemTest {

    @Test
    public void shouldStringVegetable_return_ItemObject() throws ParseException {
        Item item = new Item(3, "蔬菜", 5.98);

        assertThat(item.getItemAmount(), is(3));
        assertThat(item.getItemName(), is("蔬菜"));
        assertThat(item.getItemUnitPrice(), is(5.98));
        assertThat(item.getItemType(), is(ItemType.FOOD));
    }
}