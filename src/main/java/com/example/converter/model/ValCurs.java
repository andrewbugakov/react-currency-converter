package com.example.converter.model;

import com.example.converter.xml.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ValCurs")
public class ValCurs {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "Date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate date;
    @XmlElements(
            value = {
                    @XmlElement(name = "Valute", type = Valute.class)
            }
    )
    private List<Valute> valutes = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Valute> getValutes() {
        return valutes;
    }

    public void setValutes(List<Valute> valutes) {
        this.valutes = valutes;
    }
}
