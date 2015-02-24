package com.boxing.cart.unit;

import com.boxing.cart.function.InputInformation;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountCalculator extends Calculator {

    @Override
    public double calculate(double totalPrice, InputInformation inputInformation) {

        List<Discount> discountList = inputInformation.getDiscountList();
        List<Item> itemList = inputInformation.getItemList();
        Calendar settlementCalendar = inputInformation.getSettlementCalendar();

        Map<ItemType, Double> discountMap = new HashMap<ItemType, Double>();

        if (isListValid(discountList)) {
            abstractDiscountInformation(discountList, settlementCalendar, discountMap);
        }

        for (Item item : itemList) {
            double itemDiscountRate = discountMap.get(item.getItemType()) != null ? discountMap.get(item.getItemType()) : 1d;
            totalPrice += item.getItemUnitPrice() * item.getItemAmount() * itemDiscountRate;
        }

        DecimalFormat totalPriceFormat = new DecimalFormat("0.00");
        totalPriceFormat.format(totalPrice);
        return Double.parseDouble(totalPriceFormat.format(totalPrice));
    }

    private boolean isListValid(List<Discount> discountList) {
        return discountList != null && !discountList.isEmpty();
    }

    private void abstractDiscountInformation(List<Discount> discountList, Calendar settlementCalendar, Map<ItemType, Double> discountMap) {
        for (Discount discount : discountList) {
            if (isSameDay(discount.getDiscountCalendar(), settlementCalendar)) {
                discountMap.put(discount.getDiscountItemType(), discount.getDiscountRate());
            }
        }
    }

    private boolean isSameDay(Calendar discountCalendar, Calendar settlementCalendar) {
        return discountCalendar.get(Calendar.YEAR) == settlementCalendar.get(Calendar.YEAR) && discountCalendar.get(Calendar.DAY_OF_YEAR) == settlementCalendar.get(Calendar.DAY_OF_YEAR);
    }
}
