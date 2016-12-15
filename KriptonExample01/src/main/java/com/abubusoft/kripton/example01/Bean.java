package com.abubusoft.kripton.example01;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType
public class Bean {

	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public String description;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public int id;
}
