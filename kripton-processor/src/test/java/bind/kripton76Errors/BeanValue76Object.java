/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package bind.kripton76Errors;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Time;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType(value="root", allFields=false)
public class BeanValue76Object {
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.VALUE)
	protected long id;
	
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.VALUE)
	public BeanValue76Object valueBean;

	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BigDecimal valueBigDecimal;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BigInteger valueBigInteger;

	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Boolean valueBool;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public boolean valueBoolType;	
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Byte valueByte;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public byte valueByteType;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Calendar valueCalendar;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Character valueChar;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public char valueCharType;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Integer valueContentBoolType;

	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Currency valueCurrency;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Date valueDate;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Double valueDouble;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public double valueDoubleType;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BeanEnum valueEnum;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Float valueFloat;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public float valueFloatType;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Integer valueInt;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public int valueIntType;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Locale valueLocale;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Long valueLong;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public long valueLongType;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Short valueShort;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public short valueShortType;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public String valueString;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Time valueTime;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public TimeZone valueTimeZone;
	
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public URL valueUrl;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	
}
