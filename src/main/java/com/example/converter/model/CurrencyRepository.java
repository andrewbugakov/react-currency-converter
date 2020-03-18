package com.example.converter.model;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    List<Currency> findByDateOfRequest(LocalDate dateOfRequest);

    Currency findByDateOfRequestAndCurrencyId(
            LocalDate dateOfRequest,
            String currencyId
    );

    Currency findByDateOfRequestAndCharCode(
            LocalDate dateOfRequest,
            String currencyId
    );
}
