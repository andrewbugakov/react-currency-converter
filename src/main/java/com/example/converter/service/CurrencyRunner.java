package com.example.converter.service;

import com.example.converter.model.CurrencyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;

@Component
public class CurrencyRunner implements CommandLineRunner {
    private final CurrencyLoader currencyLoader;

    public CurrencyRunner(CurrencyLoader currencyLoader, CurrencyRepository repository) {
        this.currencyLoader = currencyLoader;
    }

    @Override
    public void run(String... args) throws JAXBException, MalformedURLException {
        currencyLoader.load();
    }
}
