package com.boxing.cart.function;

import com.boxing.cart.unit.DiscountParsing;
import com.boxing.cart.unit.ItemType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InputParsing {
    private Map<String, Object> inputInformation;
    private Calendar settlementCalendar;

    public Map<String, Object> abstractInformation(String input) throws ParseException {
        inputInformation = new HashMap<>();
        String[] information = input.split("\\n\\n");

        Map<ItemType, Map<Calendar, Double>> discountMap = new DiscountParsing().abstractDiscount(information[0]);

        inputInformation.put("Coupon", abstractCoupon(information[2]));
        inputInformation.put("Item", new ItemListParsing().abstractItem(information[1], discountMap, settlementCalendar));

        return inputInformation;
    }

    private String[] abstractCoupon(String input) throws ParseException {

        String[] coupon = input.split("\\n");

        settlementCalendar = abstractCalendar(coupon[0]);
        inputInformation.put("Settlement", settlementCalendar);

        if (isCouponEmpty(coupon)) {
            return null;
        }

        return coupon[1].split(" ");
    }

    private boolean isCouponEmpty(String[] coupon) {
        return coupon.length == 1;
    }

    private Calendar abstractCalendar(String input) throws ParseException {
        Date settlementDate = new SimpleDateFormat("yyyy.MM.dd").parse(input);
        Calendar settlementCalendar = Calendar.getInstance();
        settlementCalendar.setTime(settlementDate);

        return settlementCalendar;
    }
}
