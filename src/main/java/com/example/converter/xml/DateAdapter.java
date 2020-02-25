package com.example.converter.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateAdapter extends XmlAdapter<String, LocalDate> {

    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public String marshal(LocalDate v) throws Exception {
        synchronized (dateFormat) {
            return dateFormat.format(v);
        }
    }

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        synchronized (dateFormat) {
            return LocalDate.parse(v, dateFormat);
        }
    }

}