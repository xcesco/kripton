package com.abubusoft.kripton.example01;

import java.util.Date;

import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType
public class Bean {

	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public String description;

	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public int id;

	@BindAdapter(adapter = DateAdapter.class, dataType = Long.class)
	public Date date;
}
