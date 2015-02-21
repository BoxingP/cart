package com.boxing.cart.function;

import com.boxing.cart.unit.Coupon;
import com.boxing.cart.unit.Discount;
import com.boxing.cart.unit.Item;
import com.boxing.cart.unit.ItemType;
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

public class InputInformationConverterTest {

    @Test
    public void shouldInputString_return_4ObjectsContainInformation() throws ParseException {
        String input = "\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01\n";
        InputInformationConverter inputInformationConverter = new InputInformationConverter(input);

        assertThat(inputInformationConverter.getDiscount(), is(nullValue()));
        assertThat(inputInformationConverter.getItemList(), instanceOf(List.class));
        assertThat(inputInformationConverter.getSettlementCalendar(), instanceOf(Calendar.class));
        assertThat(inputInformationConverter.getCoupon(), is(nullValue()));
    }

    @Test
    public void shouldInputStringStandard_return_ItemListSettlementCalendar() throws ParseException {
        String input = "\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01\n";
        InputInformationConverter inputInformationConverter = new InputInformationConverter(input);

        Date settlementDate = new SimpleDateFormat("yyyy.MM.dd").parse("2014.01.01");
        Calendar expectSettlementCalendar = Calendar.getInstance();
        expectSettlementCalendar.setTime(settlementDate);

        Object[] expectItemList = new Object[2];
        expectItemList[0] = new Item(3, "蔬菜", 5.98);
        expectItemList[1] = new Item(8, "餐巾纸", 3.20);

        assertThat(inputInformationConverter.getDiscount(), is(nullValue()));
        assertArrayEquals(inputInformationConverter.getItemList().toArray(), expectItemList);
        assertThat(inputInformationConverter.getSettlementCalendar(), is(expectSettlementCalendar));
        assertThat(inputInformationConverter.getCoupon(), is(nullValue()));
    }

    @Test
    public void shouldInputStringWithDiscount_return_ItemListSettlementCalendarDiscount() throws ParseException {
        String input = "2014.01.01 | 0.7 | 食品\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01\n";
        InputInformationConverter inputInformationConverter = new InputInformationConverter(input);

        Date settlementDate = new SimpleDateFormat("yyyy.MM.dd").parse("2014.01.01");
        Calendar expectSettlementCalendar = Calendar.getInstance();
        expectSettlementCalendar.setTime(settlementDate);

        Object[] expectItemList = new Object[2];
        expectItemList[0] = new Item(3, "蔬菜", 5.98);
        expectItemList[1] = new Item(8, "餐巾纸", 3.20);

        Object[] expectDiscountList = new Object[1];
        expectDiscountList[0] = new Discount(expectSettlementCalendar, 0.7, ItemType.FOOD);

        assertArrayEquals(inputInformationConverter.getDiscount().toArray(), expectDiscountList);
        assertArrayEquals(inputInformationConverter.getItemList().toArray(), expectItemList);
        assertThat(inputInformationConverter.getSettlementCalendar(), is(expectSettlementCalendar));
        assertThat(inputInformationConverter.getCoupon(), is(nullValue()));
    }
    
    @Test
    public void shouldInputStringWithDiscountCoupon_return_ItemListSettlementCalendarDiscountCoupon() throws ParseException {
        String input = "2014.01.01 | 0.7 | 食品\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01\n2014.3.2 40 5";
        InputInformationConverter inputInformationConverter = new InputInformationConverter(input);

        Date settlementDate = new SimpleDateFormat("yyyy.MM.dd").parse("2014.01.01");
        Calendar expectSettlementCalendar = Calendar.getInstance();
        expectSettlementCalendar.setTime(settlementDate);

        Object[] expectItemList = new Object[2];
        expectItemList[0] = new Item(3, "蔬菜", 5.98);
        expectItemList[1] = new Item(8, "餐巾纸", 3.20);

        Object[] expectDiscountList = new Object[1];
        expectDiscountList[0] = new Discount(expectSettlementCalendar, 0.7, ItemType.FOOD);

        Date couponDate = new SimpleDateFormat("yyyy.MM.dd").parse("2014.3.2");
        Calendar couponCalendar = Calendar.getInstance();
        couponCalendar.setTime(couponDate);
        Object[] expectCouponList = new Object[1];
        expectCouponList[0] = new Coupon(couponCalendar, 40d, 5d);

        assertArrayEquals(inputInformationConverter.getDiscount().toArray(), expectDiscountList);
        assertArrayEquals(inputInformationConverter.getItemList().toArray(), expectItemList);
        assertThat(inputInformationConverter.getSettlementCalendar(), is(expectSettlementCalendar));
        assertArrayEquals(inputInformationConverter.getCoupon().toArray(), expectCouponList);
    }
}
