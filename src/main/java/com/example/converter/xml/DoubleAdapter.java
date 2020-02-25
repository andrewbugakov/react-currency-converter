package com.example.converter.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoubleAdapter extends XmlAdapter<String, Double> {

    @Override
    public String marshal(Double v) throws Exception {
            return String.valueOf(v);
    }

    @Override
    public Double unmarshal(String v) throws Exception {
        return Double.valueOf(v.replace(",","."));
    }

}