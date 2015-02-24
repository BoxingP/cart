package com.boxing.cart.system;

import com.boxing.cart.function.InputInformation;
import com.boxing.cart.function.TotalPriceCalculator;

import java.text.DecimalFormat;
import java.text.ParseException;

public class Cart {

    public static void main(String[] args) throws ParseException {
        String inputString = args[0];
        String separator = System.getProperty("line.separator");
        inputString = inputString.replaceAll(separator, "\n");
        inputString = inputString.replaceAll("\\\\n", "\n");
        System.out.println(inputString);

        Cart cart = new Cart();
        String totalPrice = cart.showTotalPrice(inputString);

        System.out.println("Total Price: " + totalPrice);
    }

    public String showTotalPrice(String input) throws ParseException {
        double totalPrice = 0d;

        if (input.equals("")) {
            DecimalFormat totalPriceFormat = new DecimalFormat("0.00");
            return totalPriceFormat.format(totalPrice);
        }

        InputInformation inputInformation = InputInformation.convertStringToInputInformation(input);

        TotalPriceCalculator totalPriceCalculator = new TotalPriceCalculator();
        totalPrice += totalPriceCalculator.calculateTotalPrice(inputInformation);

        DecimalFormat totalPriceFormat = new DecimalFormat("0.00");
        return totalPriceFormat.format(totalPrice);
    }
}
