package com.boxing.cart.unit;

import java.util.Calendar;
import java.util.Map;

public class ItemInformationParsing {
    public Item abstractInformation(String itemInformation, Map<ItemType, Map<Calendar, Double>> discountMap, Calendar settlementCalendar) {
        Item item;

        String[] informationContainsAll = itemInformation.split(" \\* ");
        String[] informationContainsNameUnitPrice = informationContainsAll[1].split(" : ");

        int itemAmount = Integer.parseInt(informationContainsAll[0]);
        String itemName = informationContainsNameUnitPrice[0];
        double itemUnitPrice = Double.parseDouble(informationContainsNameUnitPrice[1]);
        item = new Item(itemAmount, itemName, itemUnitPrice);

        double itemDiscount = isDiscountValid(item, discountMap, settlementCalendar) ? getItemDiscount(item, discountMap, settlementCalendar) : 1d;

        item.setDiscount(itemDiscount);
        return item;
    }

    private double getItemDiscount(Item item, Map<ItemType, Map<Calendar, Double>> discountMap, Calendar settlementCalendar) {
        return discountMap.get(item.getItemType()).get(settlementCalendar);
    }

    private boolean isDiscountValid(Item item, Map<ItemType, Map<Calendar, Double>> discountMap, Calendar settlementCalendar) {
        return discountMap != null && discountMap.get(item.getItemType()) != null && discountMap.get(item.getItemType()).get(settlementCalendar) != null;
    }

}