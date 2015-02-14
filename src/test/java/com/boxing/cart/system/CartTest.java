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
    public void shouldInputStringVegetablePaperNapkinWithCoupon_return_38point54() throws ParseException {
        String input = "\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01\n2014.3.2 40 5";

        assertThat(cart.showTotalPrice(input), is("38.54"));
    }

    @Test
    public void shouldInputStringVegetablePaperNapkinWithDiscount_return_38point16() throws ParseException {
        String input = "2014.01.01 | 0.7 | 食品\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01\n";

        assertThat(cart.showTotalPrice(input), is("38.16"));
    }

    @Test
    public void shouldInputStringVegetablePaperNapkinWithDiscountInvalid_return_43point54() throws ParseException {
        String input = "2014.01.02 | 0.7 | 食品\n\n3 * 蔬菜 : 5.98\n8 * 餐巾纸 : 3.20\n\n2014.01.01\n";

        assertThat(cart.showTotalPrice(input), is("43.54"));
    }

    @Test
    public void shouldInputStringElectronicsFoodWithDiscountCoupon_return_3083point60() throws ParseException {
        String input = "2013.11.11 | 0.7 | 电子\n\n1 * ipad : 2399.00\n1 * 显示器 : 1799.00\n12 * 啤酒 : 25.00\n5 * 面 包 : 9.00\n\n2013.11.11\n2014.3.2 1000 200";

        assertThat(cart.showTotalPrice(input), is("3083.60"));
    }
}
