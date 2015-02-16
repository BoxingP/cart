package com.boxing.cart.function;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class InputParsingTest {
    InputParsing inputParsing;
    
    @Before
    public void initObject() {
        inputParsing = new InputParsing();
    }
    
    @Test
    public void shouldInputString_return_splitByEmptyLine() throws ParseException {
        String input = "\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01\n";
        Map<String, Object> inputInformation = inputParsing.abstractInformation(input);

        assertThat(inputInformation.size(), is(3));
        assertThat(inputInformation.get("Item"), instanceOf(ArrayList.class));
        assertThat(inputInformation.get("Settlement"), instanceOf(Calendar.class));
        assertThat(inputInformation.get("Coupon"), is(nullValue()));
    }

    @Test
    public void shouldInputStringWithCouponDiscount_return_splitByEmptyLine() throws ParseException {
        String input = "2014.01.01 | 0.7 | 食品\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01\n2014.3.2 40 5";
        Map<String, Object> inputInformation = inputParsing.abstractInformation(input);

        assertThat(inputInformation.size(), is(3));
        assertThat(inputInformation.get("Item"), instanceOf(ArrayList.class));
        assertThat(inputInformation.get("Settlement"), instanceOf(Calendar.class));
        assertThat(inputInformation.get("Coupon"), instanceOf(String[].class));
    }
}
