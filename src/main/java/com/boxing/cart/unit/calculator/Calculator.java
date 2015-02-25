package com.boxing.cart.unit.calculator;

import com.boxing.cart.function.InputInformation;

public interface Calculator {
    public abstract double calculate(double totalPrice, InputInformation inputInformation);
}
