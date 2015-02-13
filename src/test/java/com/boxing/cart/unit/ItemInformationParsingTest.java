package com.boxing.cart.unit;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemInformationParsingTest {
    ItemInformationParsing itemInformationParsing;
    
    @Before
    public void initObject() {
        itemInformationParsing = new ItemInformationParsing();
    }
    
    @Test
    public void shouldString3Vegetable_return_correctItemInformation() {
        String itemInformation = "3 * 蔬菜 : 5.98";
        Item item = itemInformationParsing.abstractInformation(itemInformation);
        assertThat(item.getItemAmount(), is(3));
        assertThat(item.getItemName(), is("蔬菜"));
        assertThat(item.getItemUnitPrice(), is(5.98));
    }
    
}
