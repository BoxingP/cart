package com.boxing.cart.unit;

import com.boxing.cart.function.InputInformation;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemStringParserTest {
    ItemStringParser itemStringParser;
    InputInformation mockInputInformation;
    
    @Before
    public void initObject() {
        itemStringParser = new ItemStringParser();
        mockInputInformation = mock(InputInformation.class);
        when(mockInputInformation.getItemList()).thenReturn(new ArrayList<Item>());
    }
    
    @Test
    public void shouldInputItemString_return_ItemList() throws ParseException {
        String input="3 * 蔬菜 : 5.98";
        InputInformation inputInformation = itemStringParser.parseInput(input, mockInputInformation);

        assertThat(inputInformation.getItemList().size(), is(1));
        
        Item actualItem = inputInformation.getItemList().get(0);
        
        assertThat(actualItem.getItemAmount(), is(3));
        assertThat(actualItem.getItemName(), is("蔬菜"));
        assertThat(actualItem.getItemUnitPrice(), is(5.98));
        assertThat(actualItem.getItemType(), is(ItemType.FOOD));
    }

    @Test
    public void shouldInputNotItemString_return_emptyItemList() throws ParseException {
        String input="2014.11.11";
        InputInformation inputInformation = itemStringParser.parseInput(input, mockInputInformation);

        assertThat(inputInformation.getItemList().size(), is(0));
    }
}
