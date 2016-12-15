package com.abubusoft.kripton.example02;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Prestazione {

	@Bind("codicePrestazione")
	public String code;
	
	public String info;
	
	@Bind("descrizione")
	public String description;
	
	public String[] tags;
	
	@Bind("prenotabilita")
	public Prenotabilita prenotationType;
}
