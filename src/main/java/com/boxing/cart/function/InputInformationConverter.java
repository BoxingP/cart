package com.boxing.cart.function;

import com.boxing.cart.unit.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class InputInformationConverter {

    private List<Discount> discount;
    private List<Item> itemList;
    private Calendar settlementCalendar;
    private List<Coupon> coupon;

    public InputInformationConverter(String input) throws ParseException {
        abstractInformation(input);
    }

    private void abstractInformation(String input) throws ParseException {
        ListParsing listParsing = new ListParsing();
        
        String[] information = input.split("\\n\\n");

        this.discount = information[0] == null ? null : listParsing.abstractDiscountList(information[0]);
        this.itemList = listParsing.abstractItemList(information[1]);
        
        String[] couponInformation = information[2].split("\\n", 2);
        this.settlementCalendar = abstractCalendar(couponInformation[0]);
        this.coupon = isCouponEmpty(couponInformation) ? null : listParsing.abstractCouponList(couponInformation[1]);

        
    }

    private Calendar abstractCalendar(String input) throws ParseException {
        Date settlementDate = new SimpleDateFormat("yyyy.MM.dd").parse(input);
        Calendar settlementCalendar = Calendar.getInstance();
        settlementCalendar.setTime(settlementDate);

        return settlementCalendar;
    }

    private boolean isCouponEmpty(String[] coupon) {
        return coupon.length == 1;
    }

    public List<Discount> getDiscount() {
        return discount;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public Calendar getSettlementCalendar() {
        return settlementCalendar;
    }

    public List<Coupon> getCoupon() {
        return coupon;
    }
}
