/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package kripton08;

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
	
	@BindXml(xmlType=XmlType.VALUE)
	BeanType fieldEnum;
	
	@BindXml(xmlType=XmlType.VALUE)
	Locale fieldLocale;
	
	@BindXml(xmlType=XmlType.VALUE)
	QName fieldQName;
	
	@BindXml(xmlType=XmlType.VALUE)
	Calendar fieldCalendar;
	
	@BindXml(xmlType=XmlType.VALUE)
	Character fieldChar;
	
	@BindXml(xmlType=XmlType.VALUE)
	Date fieldDate;
	
	@BindXml(xmlType=XmlType.VALUE)
	BigDecimal fieldBigDecimal;
	
	@BindXml(xmlType=XmlType.VALUE)
	URL fieldUrl;
	
	@BindXml(xmlType=XmlType.VALUE)
	Boolean fieldBoolean;
	
	@BindXml(xmlType=XmlType.VALUE)
	Byte fieldByte;
	
	@BindXml(xmlType=XmlType.VALUE)
	Currency fieldCurrency;
	
	@BindXml(xmlType=XmlType.VALUE)
	Double fieldDouble;
	
	@BindXml(xmlType=XmlType.VALUE)
	Float fieldFloat;
	
	@BindXml(xmlType=XmlType.VALUE)
	Long fieldLong;
	
	@BindXml(xmlType=XmlType.VALUE)
	Short fieldShort;
	
	@BindXml(xmlType=XmlType.VALUE)
	Time fieldTime;
	
	@BindXml(xmlType=XmlType.VALUE)
	TimeZone fieldTimeZone;
	
	@BindXml(xmlType=XmlType.VALUE)
	Integer fieldInteger;
	
	@BindXml(xmlType=XmlType.VALUE)
	String fieldString;

	@BindXml(xmlType=XmlType.VALUE)
	public BigInteger fieldBigInteger;
}
