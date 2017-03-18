package com.abubusoft.kripton.isocodes.input;

import java.util.List;

import com.abubusoft.kripton.annotation.BindType;

@BindType("country")
public class InputCountry {
	public Name name;
	public List<String> callingCode;
	public String region;
	public Translations translations;	
	public Long area;
	public String cca2;

}
