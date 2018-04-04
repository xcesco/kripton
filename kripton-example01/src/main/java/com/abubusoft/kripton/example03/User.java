package com.abubusoft.kripton.example03;

import java.util.Map;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class User {

	public String name;
	
	public String surname;
	
	public Map<RelationShipType, User> map;
}
