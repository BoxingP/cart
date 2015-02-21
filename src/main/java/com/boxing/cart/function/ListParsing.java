package com.boxing.cart.function;

import com.boxing.cart.unit.Coupon;
import com.boxing.cart.unit.Discount;
import com.boxing.cart.unit.Item;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ListParsing {

    public List<Item> abstractItemList(String input) throws ParseException {
        if (input.equals("")) {
            return null;
        }
        
        List<Item> list = new ArrayList<Item>();

        String[] listInformation = input.split("[\\r\\n]+");
        for (String elementInformation : listInformation) {
            Item element = Item.abstractInformation(elementInformation);
            list.add(element);
        }

        return list;
    }

    public List<Discount> abstractDiscountList(String input) throws ParseException {
        if (input.equals("")) {
            return null;
        }

        List<Discount> list = new ArrayList<Discount>();

        String[] listInformation = input.split("[\\r\\n]+");
        for (String elementInformation : listInformation) {
            Discount element = Discount.abstractInformation(elementInformation);
            list.add(element);
        }

        return list;
    }

    public List<Coupon> abstractCouponList(String input) throws ParseException {
        if (input.equals("")) {
            return null;
        }

        List<Coupon> list = new ArrayList<Coupon>();

        String[] listInformation = input.split("[\\r\\n]+");
        for (String elementInformation : listInformation) {
            Coupon element = Coupon.abstractInformation(elementInformation);
            list.add(element);
        }

        return list;
    }
}