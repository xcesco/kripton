package com.abubusoft.kripton.degusta.model;

import java.util.ArrayList;
import java.util.List;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Scheda {
	@Bind(order=0)
	public Translation name;
	
	@Bind(order=1)
	public int version;
	
	@Bind(order=2)
	public List<Section> sections=new ArrayList<>();
	
}
