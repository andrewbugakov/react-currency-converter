package com.example.converter.service;

import com.example.converter.model.ValCurs;
import com.example.converter.model.Currency;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Map {
    public static List<Currency> currencies(ValCurs curs) {
        return currencies(curs, curs.getDate());
    }

    public static List<Currency> currencies(ValCurs curs, LocalDate date) {

        return curs.getValutes()
                .stream()
                .map(e -> {
                    Currency currency = new Currency();
                    currency.setCharCode(e.getCharCode());
                    currency.setDateOfRequest(date);
                    currency.setName(e.getName());
                    currency.setNumCode(e.getNumCode());
                    currency.setNominal(e.getNominal());
                    currency.setValue(e.getValue());
                    currency.setCurrencyId(e.getId());
                    return currency;
                })
                .collect(Collectors.toList());
    }
}
