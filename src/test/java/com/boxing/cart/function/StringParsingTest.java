package com.boxing.cart.function;

import com.boxing.cart.unit.Coupon;
import com.boxing.cart.unit.Discount;
import com.boxing.cart.unit.Item;
import com.boxing.cart.unit.ItemType;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class StringParsingTest {
    Calendar calendar13Nov11;
    
    @Before
    public void initObject() throws ParseException {
        Date date13Nov11 = new SimpleDateFormat("yyyy.MM.dd").parse("2013.11.11");
        calendar13Nov11 = Calendar.getInstance();
        calendar13Nov11.setTime(date13Nov11);
    }
    
    @Test
    public void shouldInputItemString_return_itemObject() throws ParseException {
        String itemString = "3 * 蔬菜 : 5.98";
        Item information = StringParsing.extractItem(itemString);

        assertThat(information, instanceOf(Item.class));
        assertThat(information, is(new Item(3, "蔬菜", 5.98)));
    }

    @Test
    public void shouldInputDiscountString_return_discountObject() throws ParseException {
        String discountString = "2013.11.11 | 0.7 | 电子";
        Discount information = StringParsing.extractDiscount(discountString);

        assertThat(information, instanceOf(Discount.class));
        assertThat(information, is(new Discount(calendar13Nov11, 0.7, ItemType.ELECTRONICS)));
    }

    @Test
    public void shouldInputSettlementCalendarString_return_calendarObject() throws ParseException {
        String settlementCalendarString = "2013.11.11";
        Calendar information = StringParsing.extractCalendar(settlementCalendarString);

        assertThat(information, instanceOf(Calendar.class));
        assertThat(information, is(calendar13Nov11));
    }

    @Test
    public void shouldInputCouponString_return_couponObject() throws ParseException {
        String couponString = "2013.11.11 1000 200";
        Coupon information = StringParsing.extractCoupon(couponString);

        assertThat(information, instanceOf(Coupon.class));
        assertThat(information, is(new Coupon(calendar13Nov11, 1000d, 200d)));
    }
}
