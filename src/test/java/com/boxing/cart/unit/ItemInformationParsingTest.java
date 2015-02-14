package com.boxing.cart.unit;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemInformationParsingTest {
    ItemInformationParsing itemInformationParsing;
    
    @Before
    public void initObject() {
        itemInformationParsing = new ItemInformationParsing();
    }
    
    @Test
    public void shouldString3Vegetable_return_correctItemInformation() throws ParseException {
        String itemInformation = "3 * 蔬菜 : 5.98";
        Map<ItemType, Map<Calendar, Double>> discountMap = new HashMap<>();
        Map<Calendar, Double> discountCalendarMap = new HashMap<>();

        Date date13Nov11 = new SimpleDateFormat("yyyy.MM.dd").parse("2013.11.11");
        Calendar calendar13Nov11 = Calendar.getInstance();
        calendar13Nov11.setTime(date13Nov11);

        discountCalendarMap.put(calendar13Nov11,1d);
        discountMap.put(ItemType.FOOD, discountCalendarMap);
        Item item = itemInformationParsing.abstractInformation(itemInformation, discountMap, calendar13Nov11);
        assertThat(item.getItemAmount(), is(3));
        assertThat(item.getItemName(), is("蔬菜"));
        assertThat(item.getItemUnitPrice(), is(5.98));
    }

    @Test
    public void shouldString3VegetableWithDiscount_return_correctItemInformation() throws ParseException {
        String itemInformation = "3 * 蔬菜 : 5.98";
        Map<ItemType, Map<Calendar, Double>> discountMap = new HashMap<>();
        Map<Calendar, Double> discountCalendarMap = new HashMap<>();
        
        Date date13Nov11 = new SimpleDateFormat("yyyy.MM.dd").parse("2013.11.11");
        Calendar calendar13Nov11 = Calendar.getInstance();
        calendar13Nov11.setTime(date13Nov11);
        
        discountCalendarMap.put(calendar13Nov11,0.7);
        discountMap.put(ItemType.FOOD, discountCalendarMap);
        
        Item item = itemInformationParsing.abstractInformation(itemInformation, discountMap, calendar13Nov11);
        
        assertThat(item.getItemAmount(), is(3));
        assertThat(item.getItemName(), is("蔬菜"));
        assertThat(item.getItemUnitPrice(), is(5.98));
        assertThat(item.getItemDiscount(), is(0.7));
    }
}
