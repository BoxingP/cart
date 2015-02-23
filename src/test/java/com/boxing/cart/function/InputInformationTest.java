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
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class InputInformationTest {
    Calendar calendar2014Jan1;
    Calendar calendar2014Mar2;
    
    @Before
    public void initObject() throws ParseException {
        Date date2014Jan1 = new SimpleDateFormat("yyyy.MM.dd").parse("2014.01.01");
        calendar2014Jan1 = Calendar.getInstance();
        calendar2014Jan1.setTime(date2014Jan1);

        Date date2014Mar2 = new SimpleDateFormat("yyyy.MM.dd").parse("2014.3.2");
        calendar2014Mar2 = Calendar.getInstance();
        calendar2014Mar2.setTime(date2014Mar2);
    }

    @Test
    public void shouldInputString_return_4ObjectsContainInformation() throws ParseException {
        String input = "\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01\n";
        InputInformation inputInformation = InputInformation.convertStringToInputInformation(input);

        assertThat(inputInformation.getDiscountList(), is(nullValue()));
        assertThat(inputInformation.getItemList(), instanceOf(List.class));
        assertThat(inputInformation.getSettlementCalendar(), instanceOf(Calendar.class));
        assertThat(inputInformation.getCouponList(), is(nullValue()));
    }

    @Test
    public void shouldInputStringStandard_return_ItemListSettlementCalendar() throws ParseException {
        String input = "\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01\n";
        InputInformation inputInformation = InputInformation.convertStringToInputInformation(input);

        Object[] expectItemList = new Object[2];
        expectItemList[0] = new Item(3, "蔬菜", 5.98);
        expectItemList[1] = new Item(8, "餐巾纸", 3.20);

        assertThat(inputInformation.getDiscountList(), is(nullValue()));
        assertArrayEquals(inputInformation.getItemList().toArray(), expectItemList);
        assertThat(inputInformation.getSettlementCalendar(), is(calendar2014Jan1));
        assertThat(inputInformation.getCouponList(), is(nullValue()));
    }

    @Test
    public void shouldInputStringWithDiscount_return_ItemListSettlementCalendarDiscount() throws ParseException {
        String input = "2014.01.01 | 0.7 | 食品\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01\n";
        InputInformation inputInformation = InputInformation.convertStringToInputInformation(input);

        Object[] expectItemList = new Object[2];
        expectItemList[0] = new Item(3, "蔬菜", 5.98);
        expectItemList[1] = new Item(8, "餐巾纸", 3.20);

        Object[] expectDiscountList = new Object[1];
        expectDiscountList[0] = new Discount(calendar2014Jan1, 0.7, ItemType.FOOD);

        assertArrayEquals(inputInformation.getDiscountList().toArray(), expectDiscountList);
        assertArrayEquals(inputInformation.getItemList().toArray(), expectItemList);
        assertThat(inputInformation.getSettlementCalendar(), is(calendar2014Jan1));
        assertThat(inputInformation.getCouponList(), is(nullValue()));
    }
    
    @Test
    public void shouldInputStringWithDiscountCoupon_return_ItemListSettlementCalendarDiscountCoupon() throws ParseException {
        String input = "2014.01.01 | 0.7 | 食品\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01\n2014.3.2 40 5";
        InputInformation inputInformation = InputInformation.convertStringToInputInformation(input);

        Object[] expectItemList = new Object[2];
        expectItemList[0] = new Item(3, "蔬菜", 5.98);
        expectItemList[1] = new Item(8, "餐巾纸", 3.20);

        Object[] expectDiscountList = new Object[1];
        expectDiscountList[0] = new Discount(calendar2014Jan1, 0.7, ItemType.FOOD);

        Object[] expectCouponList = new Object[1];
        expectCouponList[0] = new Coupon(calendar2014Mar2, 40d, 5d);

        assertArrayEquals(inputInformation.getDiscountList().toArray(), expectDiscountList);
        assertArrayEquals(inputInformation.getItemList().toArray(), expectItemList);
        assertThat(inputInformation.getSettlementCalendar(), is(calendar2014Jan1));
        assertArrayEquals(inputInformation.getCouponList().toArray(), expectCouponList);
    }
}
