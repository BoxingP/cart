package com.boxing.cart.function;

import com.boxing.cart.unit.Coupon;
import com.boxing.cart.unit.Discount;
import com.boxing.cart.unit.Item;
import com.boxing.cart.unit.ItemType;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TotalPriceCalculatorTest {
    TotalPriceCalculator totalPriceCalculator;

    @Before
    public void initObject() {
        totalPriceCalculator = new TotalPriceCalculator();
    }

    @Test
    public void shouldInputInformationConverter_return_correctTotalPrice() throws ParseException {
        InputInformation mockInputInformation = mock(InputInformation.class);

        List<Item> mockItemList = new ArrayList<Item>();
        mockItemList.add(new Item(3, "蔬菜", 5.98));
        mockItemList.add(new Item(8, "餐巾纸", 3.20));

        Date mockSettlementDate = new SimpleDateFormat("yyyy.MM.dd").parse("2014.01.01");
        Calendar mockSettlementCalendar = Calendar.getInstance();
        mockSettlementCalendar.setTime(mockSettlementDate);

        List<Discount> mockDiscountList = new ArrayList<Discount>();
        mockDiscountList.add(new Discount(mockSettlementCalendar, 0.7, ItemType.FOOD));


        List<Coupon> mockCouponList = new ArrayList<Coupon>();
        mockCouponList.add(new Coupon(mockSettlementCalendar, 40d, 5d));

        when(mockInputInformation.getDiscountList()).thenReturn(mockDiscountList);
        when(mockInputInformation.getItemList()).thenReturn(mockItemList);
        when(mockInputInformation.getSettlementCalendar()).thenReturn(mockSettlementCalendar);
        when(mockInputInformation.getCouponList()).thenReturn(mockCouponList);

        assertThat(totalPriceCalculator.calculateTotalPrice(mockInputInformation), is(38.16));
    }
}
