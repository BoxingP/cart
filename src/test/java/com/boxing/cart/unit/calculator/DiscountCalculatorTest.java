package com.boxing.cart.unit.calculator;

import com.boxing.cart.function.InputInformation;
import com.boxing.cart.unit.information.Discount;
import com.boxing.cart.unit.information.Item;
import com.boxing.cart.unit.information.ItemType;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
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


public class DiscountCalculatorTest {
    DiscountCalculator discountCalculator;
    Calendar calendar14Jan1;
    Calendar calendar14Jan2;
    InputInformation mockInputInformation;
    List<Item> mockItemList;
    List<Discount> mockDiscountList;

    @Before
    public void initObject() throws ParseException {
        discountCalculator = new DiscountCalculator();

        Date date14Jan1 = new SimpleDateFormat("yyyy.MM.dd").parse("2014.01.01");
        calendar14Jan1 = Calendar.getInstance();
        calendar14Jan1.setTime(date14Jan1);

        Date date14Jan2 = new SimpleDateFormat("yyyy.MM.dd").parse("2014.01.02");
        calendar14Jan2 = Calendar.getInstance();
        calendar14Jan2.setTime(date14Jan2);

        mockInputInformation = mock(InputInformation.class);

        mockItemList = new ArrayList<Item>();
        mockItemList.add(new Item(3, "蔬菜", new BigDecimal("5.98")));

        mockDiscountList = new ArrayList<Discount>();
        mockDiscountList.add(new Discount(calendar14Jan1, new BigDecimal("0.7"), ItemType.FOOD));
    }

    @Test
    public void shouldDiscountValid_return_totalPriceAfterDiscounted() {

        when(mockInputInformation.getDiscountList()).thenReturn(this.mockDiscountList);
        when(mockInputInformation.getItemList()).thenReturn(this.mockItemList);
        when(mockInputInformation.getSettlementCalendar()).thenReturn(calendar14Jan1);

        assertThat(discountCalculator.calculate(new BigDecimal("0"), mockInputInformation), is(new BigDecimal("12.558")));
    }

    @Test
    public void shouldDiscountEmpty_return_totalPriceWithoutDiscounted() throws ParseException {

        when(mockInputInformation.getDiscountList()).thenReturn(null);
        when(mockInputInformation.getItemList()).thenReturn(this.mockItemList);
        when(mockInputInformation.getSettlementCalendar()).thenReturn(calendar14Jan1);

        assertThat(discountCalculator.calculate(new BigDecimal("0"), mockInputInformation), is(new BigDecimal("17.94")));
    }

    @Test
    public void shouldDiscountIsNotToday_return_totalPriceWithoutDiscounted() throws ParseException {

        List<Discount> mockDiscountList = new ArrayList<Discount>();
        mockDiscountList.add(new Discount(calendar14Jan2, new BigDecimal("0.7"), ItemType.FOOD));

        when(mockInputInformation.getDiscountList()).thenReturn(mockDiscountList);
        when(mockInputInformation.getItemList()).thenReturn(this.mockItemList);
        when(mockInputInformation.getSettlementCalendar()).thenReturn(calendar14Jan1);

        assertThat(discountCalculator.calculate(new BigDecimal("0"), mockInputInformation), is(new BigDecimal("17.94")));
    }

    @Test
    public void shouldDiscountIsOtherItemType_return_totalPriceWithoutDiscounted() throws ParseException {
        List<Discount> mockDiscountList = new ArrayList<Discount>();
        mockDiscountList.add(new Discount(calendar14Jan1, new BigDecimal("0.7"), ItemType.ELECTRONICS));

        when(mockInputInformation.getDiscountList()).thenReturn(mockDiscountList);
        when(mockInputInformation.getItemList()).thenReturn(this.mockItemList);
        when(mockInputInformation.getSettlementCalendar()).thenReturn(calendar14Jan1);

        assertThat(discountCalculator.calculate(new BigDecimal("0"), mockInputInformation), is(new BigDecimal("17.94")));
    }

    @Test
    public void shouldDiscountIsOneItemType_return_totalPriceWithoutDiscounted() throws ParseException {
        List<Item> mockItemList = new ArrayList<Item>();
        mockItemList.add(new Item(3, "蔬菜", new BigDecimal("5.98")));
        mockItemList.add(new Item(8, "餐巾纸", new BigDecimal("3.20")));

        when(mockInputInformation.getDiscountList()).thenReturn(this.mockDiscountList);
        when(mockInputInformation.getItemList()).thenReturn(mockItemList);
        when(mockInputInformation.getSettlementCalendar()).thenReturn(calendar14Jan1);

        assertThat(discountCalculator.calculate(new BigDecimal("0"), mockInputInformation), is(new BigDecimal("38.158")));
    }
}