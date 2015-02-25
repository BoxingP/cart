package com.boxing.cart.unit.calculator;

import com.boxing.cart.function.InputInformation;

import java.math.BigDecimal;

public interface Calculator {
    public abstract BigDecimal calculate(BigDecimal totalPrice, InputInformation inputInformation);
}
