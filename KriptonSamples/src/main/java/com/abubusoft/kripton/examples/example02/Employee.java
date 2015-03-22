/**
 * 
 */
package com.abubusoft.kripton.examples.example02;

import java.util.Date;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindTypeXml;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.annotation.XmlType;



/**
 * @author xcesco
 *
 */
@BindType
@BindTypeXml
public class Employee {

	@Bind
	@BindXml(XmlType.ATTRIBUTE)
	private Date birthday;
	
	@Bind
	@BindXml(XmlType.ATTRIBUTE)
	private String name;

	@Bind
	@BindXml(XmlType.VALUE_CDATA)
	private String surname;
	
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
