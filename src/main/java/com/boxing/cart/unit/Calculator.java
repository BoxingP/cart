package com.boxing.cart.unit;

import com.boxing.cart.function.InputInformationConverter;

public abstract class Calculator {
    public abstract double calculate(double totalPrice, InputInformationConverter inputInformationConverter);
}
