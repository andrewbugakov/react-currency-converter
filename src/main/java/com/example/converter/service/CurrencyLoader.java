package com.example.converter.service;

import com.example.converter.model.Currency;
import com.example.converter.model.CurrencyRepository;
import com.example.converter.model.ValCurs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.List;

import static com.example.converter.service.Map.currencies;

@Component
public class CurrencyLoader {
    private static final Logger log = LoggerFactory.getLogger(CurrencyLoader.class);

    private final CurrencyRepository repository;

    public CurrencyLoader(CurrencyRepository repository) {
        this.repository = repository;
    }

    public void load() throws JAXBException, MalformedURLException {
        log.info("StartApplication...");
        ValCurs valCurs = ReadXmlFromRemote.readFromUrl();
        List<Currency> currencies = currencies(valCurs);
        repository.saveAll(currencies);
        log.info("Saved all currencies {}", currencies.size());
    }

    public void load(LocalDate date) throws JAXBException, MalformedURLException {
        log.info("StartApplication...");
        ValCurs valCurs = ReadXmlFromRemote.readFromUrl(date);
        List<Currency> currencies = currencies(valCurs, date);
        repository.saveAll(currencies);
        log.info("Saved all currencies {}", currencies.size());
    }
}
