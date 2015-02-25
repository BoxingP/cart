package com.boxing.cart.unit.parser;

import com.boxing.cart.function.InputInformation;
import com.boxing.cart.unit.information.Coupon;
import com.boxing.cart.unit.information.Discount;
import com.boxing.cart.unit.information.Item;
import com.boxing.cart.unit.information.ItemType;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemStringParserTest {
    ItemStringParser itemStringParser;

    @Before
    public void initObject() throws ParseException {
        itemStringParser = new ItemStringParser();
    }

    @Test
    public void shouldInputItemString_return_ItemList() throws ParseException {
        String input = "3 * 蔬菜 : 5.98";
        InputInformation inputInformation = new InputInformation(new ArrayList<Discount>(), new ArrayList<Item>(), null, new ArrayList<Coupon>());
        itemStringParser.parseInput(input, inputInformation);

        assertThat(inputInformation.getItemList().size(), is(1));

        Item actualItem = inputInformation.getItemList().get(0);

        assertThat(actualItem.getItemAmount(), is(3));
        assertThat(actualItem.getItemName(), is("蔬菜"));
        assertThat(actualItem.getItemUnitPrice(), is(new BigDecimal("5.98")));
        assertThat(actualItem.getItemType(), is(ItemType.FOOD));
    }

    @Test
    public void shouldInputNotItemString_return_emptyItemList() throws ParseException {
        String input = "2014.11.11";
        InputInformation inputInformation = new InputInformation(new ArrayList<Discount>(), new ArrayList<Item>(), null, new ArrayList<Coupon>());
        itemStringParser.parseInput(input, inputInformation);

        assertThat(inputInformation.getItemList().size(), is(0));
    }
}
