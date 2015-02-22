package com.boxing.cart.system;

import com.boxing.cart.function.InputInformationConverter;
import com.boxing.cart.function.TotalPriceCalculator;

import java.text.DecimalFormat;
import java.text.ParseException;

public class Cart {

    public static void main(String[] args) throws ParseException {
        String inputInformation = args[0];
        inputInformation=inputInformation.replaceAll("\\\\n", "\n");
        System.out.println(inputInformation);
        
        Cart cart = new Cart();
        String totalPrice = cart.showTotalPrice(inputInformation);

        System.out.println("Total Price: " + totalPrice);
    }

    public String showTotalPrice(String input) throws ParseException {
        double totalPrice = 0d;

        if (input.equals("")) {
            DecimalFormat totalPriceFormat = new DecimalFormat("0.00");
            return totalPriceFormat.format(totalPrice);
        }
        
        InputInformationConverter inputInformationConverter = new InputInformationConverter(input);

        TotalPriceCalculator totalPriceCalculator = new TotalPriceCalculator();
        totalPrice += totalPriceCalculator.calculateTotalPrice(inputInformationConverter);

        DecimalFormat totalPriceFormat = new DecimalFormat("0.00");
        return totalPriceFormat.format(totalPrice);
    }
}
