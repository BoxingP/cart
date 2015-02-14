package com.boxing.cart.function;

import com.boxing.cart.unit.Item;
import com.boxing.cart.unit.ItemType;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemListParsingTest {
    ItemListParsing itemListParsing;

    @Before
    public void initObject() {
        itemListParsing = new ItemListParsing();
    }

    @Test
    public void shouldStringContains2Items_return_arrayListLengthIs2() throws ParseException {
        String listInformation = "3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20";

        Map<ItemType, Map<Calendar, Double>> discountMap = new HashMap<>();

        Date date13Nov11 = new SimpleDateFormat("yyyy.MM.dd").parse("2013.11.11");
        Calendar calendar13Nov11 = Calendar.getInstance();
        calendar13Nov11.setTime(date13Nov11);

        ArrayList<Item> itemList = itemListParsing.abstractItem(listInformation, discountMap, calendar13Nov11);
        assertThat(itemList.size(), is(2));
    }

    @Test
    public void shouldStringContains1Item_return_arrayListLengthIs1() throws ParseException {
        String listInformation = "3 * 蔬菜 : 5.98";

        Map<ItemType, Map<Calendar, Double>> discountMap = new HashMap<>();

        Date date13Nov11 = new SimpleDateFormat("yyyy.MM.dd").parse("2013.11.11");
        Calendar calendar13Nov11 = Calendar.getInstance();
        calendar13Nov11.setTime(date13Nov11);

        ArrayList<Item> itemList = itemListParsing.abstractItem(listInformation, discountMap, calendar13Nov11);
        assertThat(itemList.size(), is(1));
    }
}
