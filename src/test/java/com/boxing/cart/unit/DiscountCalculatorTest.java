package com.boxing.cart.unit;

import com.boxing.cart.function.InputInformationConverter;
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


public class DiscountCalculatorTest {
    DiscountCalculator discountCalculator;
    Calendar calendar14Jan1;
    Calendar calendar14Jan2;
    InputInformationConverter mockInputInformationConverter;
    
    @Before
    public void initObject() throws ParseException {
        discountCalculator = new DiscountCalculator();
        
        Date date14Jan1 = new SimpleDateFormat("yyyy.MM.dd").parse("2014.01.01");
        calendar14Jan1 = Calendar.getInstance();
        calendar14Jan1.setTime(date14Jan1);
        
        Date date14Jan2 = new SimpleDateFormat("yyyy.MM.dd").parse("2014.01.02");
        calendar14Jan2 = Calendar.getInstance();
        calendar14Jan2.setTime(date14Jan2);
        
        mockInputInformationConverter = mock(InputInformationConverter.class);
    }

    @Test
    public void shouldDiscountValid_return_totalPriceAfterDiscounted() {
        List<Item> mockItemList = new ArrayList<Item>();
        mockItemList.add(new Item(3, "蔬菜", 5.98));

        List<Discount> mockDiscountList = new ArrayList<Discount>();
        mockDiscountList.add(new Discount(calendar14Jan1, 0.7, ItemType.FOOD));

        when(mockInputInformationConverter.getDiscount()).thenReturn(mockDiscountList);
        when(mockInputInformationConverter.getItemList()).thenReturn(mockItemList);
        when(mockInputInformationConverter.getSettlementCalendar()).thenReturn(calendar14Jan1);

        assertThat(discountCalculator.calculate(0d, mockInputInformationConverter), is(12.56));
    }

    @Test
    public void shouldDiscountEmpty_return_totalPriceWithoutDiscounted() throws ParseException {
        List<Item> mockItemList = new ArrayList<Item>();
        mockItemList.add(new Item(3, "蔬菜", 5.98));

        when(mockInputInformationConverter.getDiscount()).thenReturn(null);
        when(mockInputInformationConverter.getItemList()).thenReturn(mockItemList);
        when(mockInputInformationConverter.getSettlementCalendar()).thenReturn(calendar14Jan1);

        assertThat(discountCalculator.calculate(0d, mockInputInformationConverter), is(17.94));
    }

    @Test
    public void shouldDiscountIsNotToday_return_totalPriceWithoutDiscounted() throws ParseException {
        List<Item> mockItemList = new ArrayList<Item>();
        mockItemList.add(new Item(3, "蔬菜", 5.98));

        List<Discount> mockDiscountList = new ArrayList<Discount>();
        mockDiscountList.add(new Discount(calendar14Jan2, 0.7, ItemType.FOOD));

        when(mockInputInformationConverter.getDiscount()).thenReturn(mockDiscountList);
        when(mockInputInformationConverter.getItemList()).thenReturn(mockItemList);
        when(mockInputInformationConverter.getSettlementCalendar()).thenReturn(calendar14Jan1);

        assertThat(discountCalculator.calculate(0d, mockInputInformationConverter), is(17.94));
    }

    @Test
    public void shouldDiscountIsOtherItemType_return_totalPriceWithoutDiscounted() throws ParseException {
        List<Item> mockItemList = new ArrayList<Item>();
        mockItemList.add(new Item(3, "蔬菜", 5.98));

        List<Discount> mockDiscountList = new ArrayList<Discount>();
        mockDiscountList.add(new Discount(calendar14Jan1, 0.7, ItemType.ELECTRONICS));

        when(mockInputInformationConverter.getDiscount()).thenReturn(mockDiscountList);
        when(mockInputInformationConverter.getItemList()).thenReturn(mockItemList);
        when(mockInputInformationConverter.getSettlementCalendar()).thenReturn(calendar14Jan1);

        assertThat(discountCalculator.calculate(0d, mockInputInformationConverter), is(17.94));
    }

    @Test
    public void shouldDiscountIsOneItemType_return_totalPriceWithoutDiscounted() throws ParseException {
        List<Item> mockItemList = new ArrayList<Item>();
        mockItemList.add(new Item(3, "蔬菜", 5.98));
        mockItemList.add(new Item(8, "餐巾纸", 3.20));

        List<Discount> mockDiscountList = new ArrayList<Discount>();
        mockDiscountList.add(new Discount(calendar14Jan1, 0.7, ItemType.FOOD));

        when(mockInputInformationConverter.getDiscount()).thenReturn(mockDiscountList);
        when(mockInputInformationConverter.getItemList()).thenReturn(mockItemList);
        when(mockInputInformationConverter.getSettlementCalendar()).thenReturn(calendar14Jan1);

        assertThat(discountCalculator.calculate(0d, mockInputInformationConverter), is(38.16));
    }
}