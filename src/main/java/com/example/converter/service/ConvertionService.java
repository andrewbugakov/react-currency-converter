package com.example.converter.service;

import com.example.converter.model.Currency;
import com.example.converter.model.CurrencyRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ConvertionService {
    private final CurrencyRepository currencyRepository;

    public ConvertionService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public double getHistoryOfCurrency(String fromCurrencyId, String toCurrencyId, LocalDate dateOfIssue) {
        Currency fromCurrency = getByDateOfRequestAndCharCode(fromCurrencyId, dateOfIssue);
        Currency toCurrency = getByDateOfRequestAndCharCode(toCurrencyId, dateOfIssue);
        return convert(fromCurrency, toCurrency, 1);
    }

    public double getConvertedCurrency(
            String fromCurrencyId,
            String toCurrencyId,
            LocalDate dateOfIssue,
            Double incomingCount
    ) {
        Currency fromCurrency = getByDateOfRequestAndCharCode(fromCurrencyId, dateOfIssue);
        Currency toCurrency = getByDateOfRequestAndCharCode(toCurrencyId, dateOfIssue);
        return convert(fromCurrency, toCurrency, incomingCount);
    }

    private Currency getByDateOfRequestAndCharCode(String charCode, LocalDate dateOfIssue) {
        return charCode.equals("RUB") ?
                getRub():
                currencyRepository.findByDateOfRequestAndCharCode(dateOfIssue, charCode);
    }

    private Currency getRub() {
        Currency rub = new Currency();
        rub.setValue(1);
        rub.setNominal(1);
        return rub;
    }

    public double convert(Currency incoming, Currency outcoming, double incomingCount) {
        double valueInRubIncoming = incoming.getValue() * (incomingCount / incoming.getNominal());
        return (valueInRubIncoming / outcoming.getValue()) * outcoming.getNominal();
    }
}
