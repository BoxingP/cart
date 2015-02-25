package com.boxing.cart.unit.calculator;

import com.boxing.cart.function.InputInformation;
import com.boxing.cart.unit.information.Discount;
import com.boxing.cart.unit.information.Item;
import com.boxing.cart.unit.information.ItemType;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountCalculator implements Calculator {

    @Override
    public BigDecimal calculate(BigDecimal totalPrice, InputInformation inputInformation) {

        List<Discount> discountList = inputInformation.getDiscountList();
        List<Item> itemList = inputInformation.getItemList();
        Calendar settlementCalendar = inputInformation.getSettlementCalendar();

        Map<ItemType, BigDecimal> discountMap = new HashMap<ItemType, BigDecimal>();

        if (isListValid(discountList)) {
            abstractDiscountInformation(discountList, settlementCalendar, discountMap);
        }

        for (Item item : itemList) {
            BigDecimal itemDiscountRate = discountMap.get(item.getItemType()) != null ? discountMap.get(item.getItemType()) : new BigDecimal("1");

            BigDecimal itemAmount = new BigDecimal(item.getItemAmount());
            BigDecimal itemPrice = item.getItemUnitPrice().multiply(itemAmount).multiply(itemDiscountRate);

            totalPrice = totalPrice.add(itemPrice);
        }

        return totalPrice;
    }

    private boolean isListValid(List<Discount> discountList) {
        return discountList != null && !discountList.isEmpty();
    }

    private void abstractDiscountInformation(List<Discount> discountList, Calendar settlementCalendar, Map<ItemType, BigDecimal> discountMap) {
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
