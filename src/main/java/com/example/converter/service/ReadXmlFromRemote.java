package com.example.converter.service;

import com.example.converter.model.ValCurs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReadXmlFromRemote {
    public static ValCurs readFromUrl() throws JAXBException, MalformedURLException {
        JAXBContext jaxbContext;
        jaxbContext = JAXBContext.newInstance(ValCurs.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        String spec = "https://cbr.ru/scripts/xml_daily.asp";
        return (ValCurs) jaxbUnmarshaller.unmarshal(new URL(spec));
    }

    public static ValCurs readFromUrl(LocalDate date) throws JAXBException, MalformedURLException {
        JAXBContext jaxbContext;
        jaxbContext = JAXBContext.newInstance(ValCurs.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        String spec = "https://cbr.ru/scripts/xml_daily.asp?date_req=";
        URL url = new URL(spec + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return (ValCurs) jaxbUnmarshaller.unmarshal(url);
    }

}
