package com.boxing.cart.function;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class InputParsingTest {
    InputParsing inputParsing;
    
    @Before
    public void initObject() {
        inputParsing = new InputParsing();
    }
    
    @Test
    public void shouldInputString_return_splitByEmptyLine() {
        String input = "\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01";
        String[] inputInformation = inputParsing.splitString(input);
        
        assertThat(inputInformation.length, is(3));
        assertThat(inputInformation[0], is(""));
        assertThat(inputInformation[1], is("3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20"));
        assertThat(inputInformation[2], is("2014.01.01"));
    }
}
