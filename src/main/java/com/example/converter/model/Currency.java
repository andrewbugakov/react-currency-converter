package com.example.converter.model;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "CURRENCY", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"DATE_OF_REQUEST", "CURRENCY_ID", "NUM_CODE", "CHAR_CODE"})
})
@Entity
public class Currency {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "DATE_OF_REQUEST")
    private LocalDate dateOfRequest;
    @Column(name = "CURRENCY_ID")
    private String currencyId;
    @Column(name = "NUM_CODE")
    private double numCode;
    @Column(name = "CHAR_CODE")
    private String charCode;
    @Column(name = "NOMINAL")
    private int nominal;
    @Column(name = "NAME")
    private String name;
    @Column(name = "VALUE")
    private double value;

    public Currency() {
    }

    public Currency(LocalDate dateOfRequest, String currencyId, double numCode,
                    String charCode, int nominal, String name, double value) {
        this.dateOfRequest = dateOfRequest;
        this.currencyId = currencyId;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(LocalDate dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public double getNumCode() {
        return numCode;
    }

    public void setNumCode(double numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
