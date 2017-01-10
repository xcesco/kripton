package com.abubusoft.kriptonquickstart.model;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Address {

    public String street;
    public String suite;
    public String city;
    public String zipcode;
    public Geo geo;

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", suite='" + suite + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", geo=" + geo +
                '}';
    }
}