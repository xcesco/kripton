package com.abubusoft.benchmark.model;

import java.util.List;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class User {

    @Bind("_id")
    public String id;


    public int index;


    public String guid;
  
    public boolean isActive;

    public String balance;

    @Bind("picture")
    public String pictureUrl;

    public int age;

    public Name name;

    public String company;

    public String email;

    public String address;

    public String about;

    public String registered;

    public double latitude;

    public double longitude;

    public List<String> tags;

    public List<Integer> range;

    public List<Friend> friends;

    public List<Image> images;

    public String greeting;

    public String favoriteFruit;

    public String eyeColor;

    public String phone;
}