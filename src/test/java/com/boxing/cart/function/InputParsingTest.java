package com.boxing.cart.function;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

        Date date14Jan1 = new SimpleDateFormat("yyyy.MM.dd").parse("2014.01.01");
        Calendar calendar14Jan1 = Calendar.getInstance();
        calendar14Jan1.setTime(date14Jan1);

        assertThat(inputInformation.size(), is(4));
        assertThat(inputInformation.get("Discount"), is(nullValue()));
        assertThat(inputInformation.get("Item"), instanceOf(ArrayList.class));
        assertThat(inputInformation.get("Settlement"), instanceOf(Calendar.class));
        assertThat(inputInformation.get("Coupon"), is(nullValue()));
    }
}
