package com.abubusoft.kriptonquickstart.model;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Geo {

    public String lat;
    public String lng;

    @Override
    public String toString() {
        return "Geo{" +
                "lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}