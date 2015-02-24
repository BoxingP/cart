package com.boxing.cart.function;

import com.boxing.cart.unit.information.Coupon;
import com.boxing.cart.unit.information.Discount;
import com.boxing.cart.unit.information.Item;
import com.boxing.cart.unit.information.ItemType;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
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

        assertThat(inputInformation.getDiscountList(), instanceOf(List.class));
        assertThat(inputInformation.getItemList(), instanceOf(List.class));
        assertThat(inputInformation.getSettlementCalendar(), instanceOf(Calendar.class));
        assertThat(inputInformation.getCouponList(), instanceOf(List.class));
    }

    @Test
    public void shouldInputStringStandard_return_ItemListSettlementCalendar() throws ParseException {
        String input = "\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01\n";
        InputInformation inputInformation = InputInformation.convertStringToInputInformation(input);

        assertThat(inputInformation.getDiscountList().isEmpty(), is(true));

        assertThat(inputInformation.getItemList().size(), is(2));
        assertItemListIsExpected(inputInformation.getItemList());

        assertThat(inputInformation.getSettlementCalendar(), is(calendar2014Jan1));
        assertThat(inputInformation.getCouponList().isEmpty(), is(true));
    }

    @Test
    public void shouldInputStringWithDiscount_return_ItemListSettlementCalendarDiscount() throws ParseException {
        String input = "2014.01.01 | 0.7 | 食品\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01\n";
        InputInformation inputInformation = InputInformation.convertStringToInputInformation(input);

        assertThat(inputInformation.getDiscountList().size(), is(1));
        assertDiscountListIsExpected(inputInformation.getDiscountList());

        assertThat(inputInformation.getItemList().size(), is(2));
        assertItemListIsExpected(inputInformation.getItemList());

        assertThat(inputInformation.getSettlementCalendar(), is(calendar2014Jan1));
        assertThat(inputInformation.getCouponList().isEmpty(), is(true));
    }


    @Test
    public void shouldInputStringWithDiscountCoupon_return_ItemListSettlementCalendarDiscountCoupon() throws ParseException {
        String input = "2014.01.01 | 0.7 | 食品\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01\n2014.3.2 40 5";
        InputInformation inputInformation = InputInformation.convertStringToInputInformation(input);

        assertThat(inputInformation.getDiscountList().size(), is(1));
        assertDiscountListIsExpected(inputInformation.getDiscountList());

        assertThat(inputInformation.getItemList().size(), is(2));
        assertItemListIsExpected(inputInformation.getItemList());

        assertThat(inputInformation.getSettlementCalendar(), is(calendar2014Jan1));

        assertThat(inputInformation.getCouponList().size(), is(1));
        assertCouponListIsExpected(inputInformation.getCouponList());
    }

    @Test
    public void shouldSetInputInformationItemList_return_expectedItemList() throws ParseException {
        List<Item> expectedItemList = new ArrayList<Item>();
        expectedItemList.add(new Item(3, "蔬菜", 5.98));
        expectedItemList.add(new Item(8, "餐巾纸", 3.20));

        InputInformation inputInformation = new InputInformation(new ArrayList<Discount>(), new ArrayList<Item>(), null, new ArrayList<Coupon>());
        inputInformation.setItemList(expectedItemList);

        assertThat(inputInformation.getItemList().size(), is(2));
        assertItemListIsExpected(inputInformation.getItemList());
    }

    @Test
    public void shouldSetInputInformationDiscountList_return_expectedDiscountList() throws ParseException {
        List<Discount> expectedDiscountList = new ArrayList<Discount>();
        expectedDiscountList.add(new Discount(calendar2014Jan1, 0.7, ItemType.FOOD));

        InputInformation inputInformation = new InputInformation(new ArrayList<Discount>(), new ArrayList<Item>(), null, new ArrayList<Coupon>());
        inputInformation.setDiscountList(expectedDiscountList);

        assertThat(inputInformation.getDiscountList().size(), is(1));
        assertDiscountListIsExpected(inputInformation.getDiscountList());
    }

    @Test
    public void shouldSetInputInformationCouponList_return_expectedCouponList() throws ParseException {
        List<Coupon> expectedCouponList = new ArrayList<Coupon>();
        expectedCouponList.add(new Coupon(calendar2014Mar2, 40d, 5d));

        InputInformation inputInformation = new InputInformation(new ArrayList<Discount>(), new ArrayList<Item>(), null, new ArrayList<Coupon>());
        inputInformation.setCouponList(expectedCouponList);

        assertThat(inputInformation.getCouponList().size(), is(1));
        assertCouponListIsExpected(inputInformation.getCouponList());
    }

    @Test
    public void shouldSetInputInformationSettlementCalendar_return_expectedCalendar() throws ParseException {
        InputInformation inputInformation = new InputInformation(new ArrayList<Discount>(), new ArrayList<Item>(), null, new ArrayList<Coupon>());
        inputInformation.setSettlementCalendar(calendar2014Jan1);

        assertThat(inputInformation.getSettlementCalendar(), is(calendar2014Jan1));
    }

    private void assertItemListIsExpected(List<Item> itemList) {
        assertThat(itemList.get(0).getItemAmount(), is(3));
        assertThat(itemList.get(0).getItemName(), is("蔬菜"));
        assertThat(itemList.get(0).getItemType(), is(ItemType.FOOD));
        assertThat(itemList.get(0).getItemUnitPrice(), is(5.98));
        assertThat(itemList.get(1).getItemAmount(), is(8));
        assertThat(itemList.get(1).getItemName(), is("餐巾纸"));
        assertThat(itemList.get(1).getItemType(), is(ItemType.DAILY_NECESSITIES));
        assertThat(itemList.get(1).getItemUnitPrice(), is(3.20));
    }

    private void assertDiscountListIsExpected(List<Discount> discountList) {
        assertThat(discountList.get(0).getDiscountCalendar(), is(calendar2014Jan1));
        assertThat(discountList.get(0).getDiscountRate(), is(0.7));
        assertThat(discountList.get(0).getDiscountItemType(), is(ItemType.FOOD));
    }

    private void assertCouponListIsExpected(List<Coupon> couponList) {
        assertThat(couponList.get(0).getCouponCalendar(), is(calendar2014Mar2));
        assertThat(couponList.get(0).getValidTotalPrice(), is(40d));
        assertThat(couponList.get(0).getCouponPrice(), is(5d));
    }
}
