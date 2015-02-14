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
        this.discountMap = new HashMap<>();
        this.electronicsDiscountMap=new HashMap<>();
        this.foodDiscountMap=new HashMap<>();
        this.dailyNecessitiesDiscountMap=new HashMap<>();
        this.alcoholDiscountMap=new HashMap<>();

        discountMap.put(ItemType.ELECTRONICS, electronicsDiscountMap);
        discountMap.put(ItemType.FOOD, foodDiscountMap);
        discountMap.put(ItemType.DAILY_NECESSITIES, dailyNecessitiesDiscountMap);
        discountMap.put(ItemType.ALCOHOL, alcoholDiscountMap);
    }

    public Map<ItemType, Map<Calendar, Double>> abstractDiscount(String discount) throws ParseException {
        if (discount.equals("")) {
            return discountMap;
        }
        
        String[] discountList = discount.split("\\n");
        for (String discountElement : discountList) {
            discountMap = parseDiscount(discountElement);
        }
        return discountMap;
    }

    private Map<ItemType, Map<Calendar, Double>> parseDiscount(String discountElement) throws ParseException {
        String[] discountInformation = discountElement.split(" \\| ");
        switch (discountInformation[2]) {
            case "电子":
                discountMap.put(ItemType.ELECTRONICS, storeCalendarDiscount(electronicsDiscountMap, discountInformation));
                break;
            case "食品":
                discountMap.put(ItemType.FOOD, storeCalendarDiscount(foodDiscountMap, discountInformation));
                break;
            case "日用品":
                discountMap.put(ItemType.DAILY_NECESSITIES, storeCalendarDiscount(dailyNecessitiesDiscountMap, discountInformation));
                break;
            case "酒类":
                discountMap.put(ItemType.ALCOHOL, storeCalendarDiscount(alcoholDiscountMap, discountInformation));
                break;
            default:
                break;
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
