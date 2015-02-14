package com.boxing.cart.function;

import com.boxing.cart.unit.Item;
import com.boxing.cart.unit.ItemInformationParsing;
import com.boxing.cart.unit.ItemType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class ItemListParsing {
    public ArrayList<Item> abstractItem(String listInformation, Map<ItemType, Map<Calendar, Double>> discountMap, Calendar settlementCalendar) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        ItemInformationParsing itemInformationParsing = new ItemInformationParsing();

        String[] items = listInformation.split("[\\r\\n]+");
        for (String element : items) {
            Item item = itemInformationParsing.abstractInformation(element, discountMap, settlementCalendar);
            itemList.add(item);
        }

        return itemList;
    }
}