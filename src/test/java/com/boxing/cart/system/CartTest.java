package com.boxing.cart.system;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CartTest {
    Cart cart;

    @Before
    public void initObject() {
        cart = new Cart();
    }

    @Test
    public void shouldInputStringVegetablePaperNapkin_return_43point54() throws ParseException {
        String input = "\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01\n";

        assertThat(cart.showTotalPrice(input), is("43.54"));
    }

    @Test
    public void shouldInputStringVegetablePaperNapkinWithCoupon_return_35point54() throws ParseException {
        String input = "\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01\n2014.3.2 40 5";

        assertThat(cart.showTotalPrice(input), is("38.54"));
    }
}
