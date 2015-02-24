package com.boxing.cart.unit.parser;

import com.boxing.cart.function.InputInformation;

import java.text.ParseException;

public abstract class Parser {
    public abstract void parseInput(String input, InputInformation inputInformation) throws ParseException;
}
