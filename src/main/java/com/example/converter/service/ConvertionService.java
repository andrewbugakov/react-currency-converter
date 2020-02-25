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
        Currency fromCurrency = getByDateOfRequestAndCurrencyId(fromCurrencyId, dateOfIssue);
        Currency toCurrency = getByDateOfRequestAndCurrencyId(toCurrencyId, dateOfIssue);
        return convert(fromCurrency, toCurrency, 1);
    }

    public double getConvertedCurrency(
            String fromCurrencyId,
            String toCurrencyId,
            LocalDate dateOfIssue,
            Double incomingCount
    ) {
        Currency fromCurrency = getByDateOfRequestAndCurrencyId(fromCurrencyId, dateOfIssue);
        Currency toCurrency = getByDateOfRequestAndCurrencyId(toCurrencyId, dateOfIssue);
        return convert(fromCurrency, toCurrency, incomingCount);
    }

    private Currency getByDateOfRequestAndCurrencyId(String fromCurrencyId, LocalDate dateOfIssue) {
        return fromCurrencyId.equals("RUB") ?
                getRub():
                currencyRepository.findByDateOfRequestAndCurrencyId(dateOfIssue, fromCurrencyId);
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
