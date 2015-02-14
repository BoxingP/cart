package com.boxing.cart.unit;

import java.util.HashMap;
import java.util.Map;

public class DiscountParsing {
    private Map<ItemType, String[]> discountMap;

    public DiscountParsing() {
        this.discountMap = new HashMap<>();
    }

    public Map<ItemType, String[]> abstractDiscount(String discount) {
        String[] discountInformation = discount.split(" \\| ");
        switch (discountInformation[2]) {
            case "电子":
                discountMap.put(ItemType.ELECTRONICS, new String[]{discountInformation[0], discountInformation[1]});
                break;
            case "食品":
                discountMap.put(ItemType.FOOD, new String[]{discountInformation[0], discountInformation[1]});
                break;
            case "日用品":
                discountMap.put(ItemType.DAILY_NECESSITIES, new String[]{discountInformation[0], discountInformation[1]});
                break;
            case "酒类":
                discountMap.put(ItemType.ALCOHOL, new String[]{discountInformation[0], discountInformation[1]});
                break;
            default:
                break;
        }

        return discountMap;
    }
}
