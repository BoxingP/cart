package com.boxing.cart.function;

import com.boxing.cart.unit.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemListParsingTest {
    ItemListParsing itemListParsing;
    
    @Before
    public void initObject() {
        itemListParsing = new ItemListParsing();
    }
    
    @Test
    public void shouldStringContains2Items_return_arrayListLengthIs2() {
        String listInformation = "3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20";
        ArrayList<Item> itemList = itemListParsing.abstractItem(listInformation);
        assertThat(itemList.size(), is(2));
    }

    @Test
    public void shouldStringContains1Item_return_arrayListLengthIs1() {
        String listInformation = "3 * 蔬菜 : 5.98";
        ArrayList<Item> itemList = itemListParsing.abstractItem(listInformation);
        assertThat(itemList.size(), is(1));
    }
}
