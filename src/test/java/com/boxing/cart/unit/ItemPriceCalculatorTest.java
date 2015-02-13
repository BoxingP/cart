package com.boxing.cart.unit;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemPriceCalculatorTest {
    ItemPriceCalculator itemPriceCalculator;
    
    @Before
    public void initObject() {
        itemPriceCalculator = new ItemPriceCalculator();
    }
    
    @Test
    public void shouldItemAmount3UnitPrice5point98_return_17point94() {
        Item mockItem = mock(Item.class);
        when(mockItem.getItemAmount()).thenReturn(3);
        when(mockItem.getItemUnitPrice()).thenReturn(5.98);
        assertThat(itemPriceCalculator.calculate(mockItem), is(17.94));
    }
}
