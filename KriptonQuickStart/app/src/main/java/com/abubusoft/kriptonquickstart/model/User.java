package com.abubusoft.kriptonquickstart.model;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class User {

    public long id;
    public String name;
    public String username;
    public String email;
    public Address address;
    public String phone;
    public String website;
    public Company company;

}