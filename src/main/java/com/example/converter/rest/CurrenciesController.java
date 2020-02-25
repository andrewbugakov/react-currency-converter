package com.example.converter.rest;

import com.example.converter.service.ConvertionService;
import com.example.converter.service.CurrencyLoader;
import com.example.converter.model.ConvertionDto;
import com.example.converter.model.Currency;
import com.example.converter.model.CurrencyRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class CurrenciesController {
    private final ConvertionService convertionService;
    private final CurrencyRepository currencyRepository;
    private final CurrencyLoader currencyLoader;

    public CurrenciesController(ConvertionService convertionService, CurrencyRepository currencyRepository, CurrencyLoader currencyLoader) {
        this.convertionService = convertionService;
        this.currencyRepository = currencyRepository;
        this.currencyLoader = currencyLoader;
    }

    @PostMapping(value = "/currencies/convert")
    public Double currencyConvert(@RequestBody ConvertionDto convertionDto) throws JAXBException, MalformedURLException {
        List<Currency> currencies = getCurrencies(convertionDto.getDateOfIssue());
        return convertionService.getConvertedCurrency(
                convertionDto.getFromCurrencyId(),
                convertionDto.getToCurrencyId(),
                convertionDto.getDateOfIssue(),
                convertionDto.getIncomingCount()
        );
    }

    @GetMapping(value = "/api/currencies/search/findByDateOfRequest")
    public List<Currency> getCurrencies(@RequestParam(name = "dateOfRequest") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfRequest) throws JAXBException, MalformedURLException {
        List<Currency> byDateOfRequest = currencyRepository.findByDateOfRequest(dateOfRequest);
        if (byDateOfRequest.isEmpty()) {
            currencyLoader.load(dateOfRequest);
        } else {
            return byDateOfRequest;
        }
        return currencyRepository.findByDateOfRequest(dateOfRequest);
    }
}
