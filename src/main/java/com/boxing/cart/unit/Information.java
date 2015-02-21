package com.boxing.cart.unit;

import java.text.ParseException;

public abstract class Information<T> {
    public abstract T abstractInformation(String input) throws ParseException;
}
