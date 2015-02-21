package com.boxing.cart.function;

import com.boxing.cart.unit.Information;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ListParsing {

    public <T> List<T> abstractList(String input, Information<T> information) throws ParseException {
        if (input.equals("")) {
            return null;
        }

        List<T> list = new ArrayList<T>();

        String[] listInformation = input.split("[\\r\\n]+");
        for (String elementInformation : listInformation) {
            T element = information.abstractInformation(elementInformation);
            list.add(element);
        }

        return list;
    }
}