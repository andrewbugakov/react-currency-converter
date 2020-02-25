package com.example.converter.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ConvertionDto {
    private String fromCurrencyId;
    private String toCurrencyId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfIssue;
    private Double incomingCount;

    public String getFromCurrencyId() {
        return fromCurrencyId;
    }

    public void setFromCurrencyId(String fromCurrencyId) {
        this.fromCurrencyId = fromCurrencyId;
    }

    public String getToCurrencyId() {
        return toCurrencyId;
    }

    public void setToCurrencyId(String toCurrencyId) {
        this.toCurrencyId = toCurrencyId;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Double getIncomingCount() {
        return incomingCount;
    }

    public void setIncomingCount(Double incomingCount) {
        this.incomingCount = incomingCount;
    }
}
