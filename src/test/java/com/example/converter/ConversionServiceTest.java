package com.example.converter;

import com.example.converter.model.Currency;
import com.example.converter.model.CurrencyRepository;
import com.example.converter.service.ConvertionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class ConversionServiceTest {
    ConvertionService convertionService = new ConvertionService(mock(CurrencyRepository.class));
    @Test
    void testConversion() {
        Currency currency = new Currency();
        currency.setValue(30);
        currency.setNominal(1);
        Currency currency1=new Currency();
        currency1.setNominal(1);
        currency1.setValue(30);
        double convert = convertionService.convert(currency, currency1, 1);
        Assertions.assertEquals(1, convert);
    }
}
