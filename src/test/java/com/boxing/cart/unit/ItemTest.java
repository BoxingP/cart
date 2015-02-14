package com.boxing.cart.unit;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemTest {
    Item item;
    
    @Before
    public void initObject() {
        item = new Item();
    }
    
    @Test
    public void shouldSetItemAmount1_return_itemAmount1() {
        item.setItemAmount(1);
        assertThat(item.getItemAmount(), is(1));
    }

    @Test
    public void shouldSetItemNameA_return_itemNameA() {
        item.setItemName("a");
        assertThat(item.getItemName(), is("a"));
    }

    @Test
    public void shouldSetItemUnitPrice1_return_itemUnitPrice1() {
        item.setItemUnitPrice(1.00);
        assertThat(item.getItemUnitPrice(), is(1.00));
    }
    
    @Test
    public void shouldSetItemNameVegetable_return_itemTypeFood() {
        item.setItemName("蔬菜");
        assertThat(item.getItemType(), is(ItemType.FOOD));
    }

    @Test
    public void shouldSetItemDiscount_return_itemDiscount() {
        item.setItemDiscount(0.7);
        assertThat(item.getItemDiscount(), is(0.7));
    }
}
