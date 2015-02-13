package com.boxing.cart.system;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CartTest {
    Cart cart;
    
    @Before
    public void initObject() {
        cart = new Cart();
    }
    
    @Test
    public void shouldInputStringVegetablePaperNapkin_return_43point54() {
        String input = "\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01";

        assertThat(cart.showTotalPrice(input), is("43.54"));
    }
}
