package com.abubusoft.kripton.processor.kripton42;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType
public class Restaurant {

	@BindXml(XmlType.ATTRIBUTE)
	public long id;
			
	@BindXml(XmlType.ATTRIBUTE)
	public String name;
	
	@BindXml(XmlType.VALUE_CDATA)
	public String address;
		
	@BindXml(XmlType.ATTRIBUTE)
	public Double longitude;
	
	@BindXml(XmlType.ATTRIBUTE)
	public Double latitude;
}
