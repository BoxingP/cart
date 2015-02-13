package com.boxing.cart.function;

import com.boxing.cart.unit.Item;
import com.boxing.cart.unit.ParseItemInformation;

import java.util.ArrayList;

public class ItemListParsing {
    public ArrayList<Item> abstractItem(String listInformation) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        ParseItemInformation parseItemInformation = new ParseItemInformation();

        String[] items = listInformation.split("[\\r\\n]+");
        for (String element : items) {
            Item item = parseItemInformation.abstractInformation(element);
            itemList.add(item);
        }

        return itemList;
    }
}