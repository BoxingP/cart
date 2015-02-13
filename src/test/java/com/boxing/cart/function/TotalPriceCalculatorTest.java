package com.boxing.cart.function;

import com.boxing.cart.unit.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TotalPriceCalculatorTest {
    TotalPriceCalculator totalPriceCalculator;
    
    @Before
    public void initObject() {
        totalPriceCalculator = new TotalPriceCalculator();
    }
    
    @Test
    public void shouldItemList_return_correctTotalPrice() {
        Item mockItemA = mock(Item.class);
        when(mockItemA.getItemAmount()).thenReturn(3);
        when(mockItemA.getItemUnitPrice()).thenReturn(5.98);

        Item mockItemB = mock(Item.class);
        when(mockItemB.getItemAmount()).thenReturn(8);
        when(mockItemB.getItemUnitPrice()).thenReturn(3.20);

        ArrayList<Item> mockItemList = new ArrayList<Item>();
        mockItemList.add(mockItemA);
        mockItemList.add(mockItemB);

        assertThat(totalPriceCalculator.calculateTotalPrice(mockItemList), is("43.54"));
    }
}
