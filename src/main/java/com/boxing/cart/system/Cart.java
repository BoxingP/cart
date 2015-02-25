package com.boxing.cart.system;

import com.boxing.cart.function.InputInformation;
import com.boxing.cart.function.TotalPriceCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        BigDecimal totalPrice = new BigDecimal("0");

        if (isInputValid(input)) {
            InputInformation inputInformation = InputInformation.convertStringToInputInformation(input);

            TotalPriceCalculator totalPriceCalculator = new TotalPriceCalculator();
            totalPrice = totalPrice.add(totalPriceCalculator.calculateTotalPrice(inputInformation));
        }

        return totalPrice.setScale(2, RoundingMode.HALF_UP).toString();
    }

    private boolean isInputValid(String input) {
        return input != null && !input.equals("");
    }

}
