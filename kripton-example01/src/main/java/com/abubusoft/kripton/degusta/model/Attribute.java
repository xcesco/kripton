package com.abubusoft.kripton.degusta.model;

import java.util.ArrayList;
import java.util.List;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Attribute {
	public Attribute(String ita, String fre, String eng) {
		name=new Translation(ita, fre, eng);
	}
	
	public Attribute(String code, String ita, String fre, String eng) {
		name=new Translation(ita, fre, eng);
		this.singleValue=true;
		this.code=code;
	}
	
	public Attribute(String code, String ita, String fre, String eng, boolean singleValue) {
		name=new Translation(ita, fre, eng);
		this.singleValue=singleValue;
		this.code=code;
	}
	
	public Attribute(String ita, String fre, String eng, boolean singleValue) {
		name=new Translation(ita, fre, eng);
		this.singleValue=singleValue;
		this.code=ita;
	}
	
	public Attribute() {
	}
	
	@Bind(order=0)
	public String code;

	@Bind(order=1)
	public Translation name;
	
	@Bind(order=2)
	public boolean singleValue=true;
	
	@Bind(order=3)
	public List<Translation> values=new ArrayList<>();
	
	
}
