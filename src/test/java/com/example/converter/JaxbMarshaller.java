package com.example.converter;

import com.example.converter.model.ValCurs;
import com.example.converter.model.Valute;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.converter.service.ReadXmlFromRemote.readFromUrl;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JaxbMarshaller {

    @Test
    void readTest() throws JAXBException, MalformedURLException {
        ValCurs read = readFromUrl();
        assertNotNull(read);
    }

    private void write() {
        File xmlFile = new File("valute2.xml");
        JAXBContext jaxbContext;
        ValCurs valCurs = new ValCurs();
        valCurs.setDate(LocalDate.now());
        valCurs.setName("name");
        ArrayList<Valute> valutes = new ArrayList<>();
        Valute valute = new Valute();
        valute.setName("name");
        valute.setCharCode("USD");
        valute.setId("CURR01");
        valute.setNominal(1);
        valute.setNumCode(1.2);
        valute.setValue(22.33);
        valutes.add(valute);
        valCurs.getValutes().add(valute);
        try {
            jaxbContext = JAXBContext.newInstance(ValCurs.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(valCurs, xmlFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
