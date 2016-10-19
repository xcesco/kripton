/**
 * 
 */
package com.abubusoft.kripton.examples.example02;

import java.util.Date;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindTypeXml;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;



/**
 * @author xcesco
 *
 */
@BindType
@BindTypeXml
public class Employee {

	@Bind
	@BindXml(XmlType.ATTRIBUTE)
	public Date birthday;
	
	@Bind
	@BindXml(XmlType.ATTRIBUTE)
	public String name;

	@Bind
	@BindXml(XmlType.VALUE_CDATA)
	public String surname;
	
	public Date getBirthday() {
		return birthday;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
}
