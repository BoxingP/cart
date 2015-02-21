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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class ListParsingTest {
    ListParsing listParsing;
    Calendar calendar13Nov11;

    @Before
    public void initObject() throws ParseException {
        listParsing = new ListParsing();

        Date date13Nov11 = new SimpleDateFormat("yyyy.MM.dd").parse("2013.11.11");
        calendar13Nov11 = Calendar.getInstance();
        calendar13Nov11.setTime(date13Nov11);
    }

    @Test
    public void shouldString1Item_return_itemListLength1() throws ParseException {
        String listInformation = "3 * 蔬菜 : 5.98";
        List<Item> itemList = listParsing.abstractList(listInformation, new Item());

        Object[] expectItemList = new Object[1];
        expectItemList[0] = new Item(3, "蔬菜", 5.98);

        assertThat(itemList.size(), is(1));
        assertArrayEquals(itemList.toArray(), expectItemList);
    }

    @Test
    public void shouldString2Items_return_itemListLength2() throws ParseException {
        String listInformation = "3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20";
        List<Item> itemList = listParsing.abstractList(listInformation, new Item());

        Object[] expectItemList = new Object[2];
        expectItemList[0] = new Item(3, "蔬菜", 5.98);
        expectItemList[1] = new Item(8, "餐巾纸", 3.20);

        assertThat(itemList.size(), is(2));
        assertArrayEquals(itemList.toArray(), expectItemList);
    }

    @Test
    public void shouldString1Discount_return_discountListLength1() throws ParseException {
        String listInformation = "2013.11.11 | 0.7 | 食品";
        List<Discount> discountList = listParsing.abstractList(listInformation, new Discount());

        Object[] expectDiscountList = new Object[1];
        expectDiscountList[0] = new Discount(calendar13Nov11, 0.7, ItemType.FOOD);

        assertThat(discountList.size(), is(1));
        assertArrayEquals(discountList.toArray(), expectDiscountList);
    }

    @Test
    public void shouldString2Discounts_return_discountListLength2() throws ParseException {
        String listInformation = "2013.11.11 | 0.7 | 食品\n2013.11.11 | 0.5 | 电子";
        List<Discount> discountList = listParsing.abstractList(listInformation, new Discount());

        Object[] expectDiscountList = new Object[2];
        expectDiscountList[0] = new Discount(calendar13Nov11, 0.7, ItemType.FOOD);
        expectDiscountList[1] = new Discount(calendar13Nov11, 0.5, ItemType.ELECTRONICS);

        assertThat(discountList.size(), is(2));
        assertArrayEquals(discountList.toArray(), expectDiscountList);

    }

    @Test
    public void shouldString1Coupon_return_couponListLength1() throws ParseException {
        String listInformation = "2013.11.11 1000 200";
        List<Coupon> couponList = listParsing.abstractList(listInformation, new Coupon());

        Object[] expectCouponList = new Object[1];
        expectCouponList[0] = new Coupon(calendar13Nov11, 1000d, 200d);

        assertThat(couponList.size(), is(1));
        assertArrayEquals(couponList.toArray(), expectCouponList);
    }

    @Test
    public void shouldString2Coupons_return_couponListLength2() throws ParseException {
        String listInformation = "2013.11.11 1000 200\n2013.11.11 800 100";
        List<Coupon> couponList = listParsing.abstractList(listInformation, new Coupon());

        Object[] expectCouponList = new Object[2];
        expectCouponList[0] = new Coupon(calendar13Nov11, 1000d, 200d);
        expectCouponList[1] = new Coupon(calendar13Nov11, 800d, 100d);

        assertThat(couponList.size(), is(2));
        assertArrayEquals(couponList.toArray(), expectCouponList);
    }
}
