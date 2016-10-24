package com.abubusoft.kripton.android.kripton08;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Time;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.namespace.QName;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType
public class Bean1 extends Bean0 {

	public enum BeanType 
	{
		TEST_1,
		TEST_2;
	}
	
	@BindXml(value=XmlType.VALUE)
	BeanType fieldEnum;
	
	@BindXml(value=XmlType.VALUE)
	Locale fieldLocale;
	
	@BindXml(value=XmlType.VALUE)
	QName fieldQName;
	
	@BindXml(value=XmlType.VALUE)
	Calendar fieldCalendar;
	
	@BindXml(value=XmlType.VALUE)
	Character fieldChar;
	
	@BindXml(value=XmlType.VALUE)
	Date fieldDate;
	
	@BindXml(value=XmlType.VALUE)
	BigDecimal fieldBigDecimal;
	
	@BindXml(value=XmlType.VALUE)
	URL fieldUrl;
	
	@BindXml(value=XmlType.VALUE)
	Boolean fieldBoolean;
	
	@BindXml(value=XmlType.VALUE)
	Byte fieldByte;
	
	@BindXml(value=XmlType.VALUE)
	Currency fieldCurrency;
	
	@BindXml(value=XmlType.VALUE)
	Double fieldDouble;
	
	@BindXml(value=XmlType.VALUE)
	Float fieldFloat;
	
	@BindXml(value=XmlType.VALUE)
	Long fieldLong;
	
	@BindXml(value=XmlType.VALUE)
	Short fieldShort;
	
	@BindXml(value=XmlType.VALUE)
	Time fieldTime;
	
	@BindXml(value=XmlType.VALUE)
	TimeZone fieldTimeZone;
	
	@BindXml(value=XmlType.VALUE)
	Integer fieldInteger;
	
	@BindXml(value=XmlType.VALUE)
	String fieldString;

	@BindXml(value=XmlType.VALUE)
	public BigInteger fieldBigInteger;
}
