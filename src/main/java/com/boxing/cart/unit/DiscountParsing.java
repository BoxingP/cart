package com.boxing.cart.unit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DiscountParsing {
    private Map<ItemType, Map<Calendar, Double>> discountMap;
    private Map<Calendar, Double> electronicsDiscountMap;
    private Map<Calendar, Double> foodDiscountMap;
    private Map<Calendar, Double> dailyNecessitiesDiscountMap;
    private Map<Calendar, Double> alcoholDiscountMap;

    public DiscountParsing() {
        this.discountMap = new HashMap<ItemType, Map<Calendar, Double>>();
        this.electronicsDiscountMap=new HashMap<Calendar, Double>();
        this.foodDiscountMap=new HashMap<Calendar, Double>();
        this.dailyNecessitiesDiscountMap=new HashMap<Calendar, Double>();
        this.alcoholDiscountMap=new HashMap<Calendar, Double>();

        discountMap.put(ItemType.ELECTRONICS, electronicsDiscountMap);
        discountMap.put(ItemType.FOOD, foodDiscountMap);
        discountMap.put(ItemType.DAILY_NECESSITIES, dailyNecessitiesDiscountMap);
        discountMap.put(ItemType.ALCOHOL, alcoholDiscountMap);
    }

    public Map<ItemType, Map<Calendar, Double>> abstractDiscount(String discount) throws ParseException {
        if (discount.equals("")) {
            return null;
        }
        
        String[] discountList = discount.split("\\n");
        for (String discountElement : discountList) {
            discountMap = parseDiscount(discountElement);
        }
        return discountMap;
    }

    private Map<ItemType, Map<Calendar, Double>> parseDiscount(String discountElement) throws ParseException {
        if (discountElement.equals("")) return discountMap;
        
        String[] discountInformation = discountElement.split(" \\| ");
        if (discountInformation.length<3) return discountMap;
        
        if (discountInformation[2].equals("电子")) {
            discountMap.put(ItemType.ELECTRONICS, storeCalendarDiscount(electronicsDiscountMap, discountInformation));

        } else if (discountInformation[2].equals("食品")) {
            discountMap.put(ItemType.FOOD, storeCalendarDiscount(foodDiscountMap, discountInformation));

        } else if (discountInformation[2].equals("日用品")) {
            discountMap.put(ItemType.DAILY_NECESSITIES, storeCalendarDiscount(dailyNecessitiesDiscountMap, discountInformation));

        } else if (discountInformation[2].equals("酒类")) {
            discountMap.put(ItemType.ALCOHOL, storeCalendarDiscount(alcoholDiscountMap, discountInformation));

        }

        return discountMap;
    }

    private Map<Calendar, Double> storeCalendarDiscount(Map<Calendar, Double> calendarDiscountMap, String[] discountInformation) throws ParseException {
        Date date = new SimpleDateFormat("yyyy.MM.dd").parse(discountInformation[0]);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendarDiscountMap.put(calendar, Double.parseDouble(discountInformation[1]));
        return calendarDiscountMap;
    }
}
