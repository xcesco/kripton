package com.abubusoft.kripton.degusta.model;

import java.util.ArrayList;
import java.util.List;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Section {
	

	public Section(String code, String ita, String fra, String eng) {
		name=new Translation(ita, fra, eng);
		this.code=code;
	}
	
	public Section() {		
	}
	
	@Bind(order=0)
	public String code;

	@Bind(order=1)
	public Translation name;
	
	@Bind(order=2)
	public List<Attribute> attributes=new ArrayList<>();
}
