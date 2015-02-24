package com.boxing.cart.unit;

import com.boxing.cart.function.InputInformation;

import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemStringParser extends Parser {
    
    @Override
    public InputInformation parseInput(String input, InputInformation inputInformation) throws ParseException {
        List<Item> itemList = inputInformation.getItemList();
        if (isItemString(input)) {
            String[] itemInformation = input.split(" \\* | : ");

            int itemAmount = Integer.parseInt(itemInformation[0]);
            String itemName = itemInformation[1];
            double itemUnitPrice = Double.parseDouble(itemInformation[2]);

            itemList.add(new Item(itemAmount, itemName, itemUnitPrice));
        }

        return new InputInformation(inputInformation.getDiscountList(), itemList, inputInformation.getSettlementCalendar(), inputInformation.getCouponList());
    }

    private boolean isItemString(String input) {
        String itemPatternString = "(\\d+)( \\* )(.+)( : )(\\d+)";
        Pattern itemPattern = Pattern.compile(itemPatternString);
        Matcher matcher = itemPattern.matcher(input);
        return matcher.find();
    }
}
