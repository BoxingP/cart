package com.boxing.cart.unit;

public class ItemInformationParsing {
    public Item abstractInformation(String itemInformation) {
        Item item = new Item();
        
        String[] informationContainsAll = itemInformation.split(" \\* ");
        String[] informationContainsNameUnitPrice = informationContainsAll[1].split(" : ");
        
        int itemAmount = Integer.parseInt(informationContainsAll[0]);
        String itemName = informationContainsNameUnitPrice[0];
        double itemUnitPrice = Double.parseDouble(informationContainsNameUnitPrice[1]);
        
        item.setItemAmount(itemAmount);
        item.setItemName(itemName);
        item.setItemUnitPrice(itemUnitPrice);
        
        return item;
    }
}
