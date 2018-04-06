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

// TODO: Auto-generated Javadoc
/**
 * The Class BeanAttribute76.
 */
@BindType(value="root", allFields=true)
public class BeanAttribute76 {
	
	/** The id. */
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	protected long id;
	
	/** The value bean. */
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BeanAttribute76 valueBean;

	/** The value big decimal. */
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BigDecimal valueBigDecimal;
	
	/** The value big integer. */
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BigInteger valueBigInteger;

	/** The value bool. */
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Boolean valueBool;
	
	/** The value bool type. */
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public boolean valueBoolType;	
	
	/** The value byte. */
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Byte valueByte;
	
	/** The value byte type. */
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public byte valueByteType;
	
	/** The value calendar. */
	//@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Calendar valueCalendar;
	
	/** The value char. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Character valueChar;
	
	/** The value char type. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public char valueCharType;
	
	/** The value content bool type. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Integer valueContentBoolType;

	/** The value currency. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Currency valueCurrency;
	
	/** The value date. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Date valueDate;
	
	/** The value double. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Double valueDouble;
	
	/** The value double type. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public double valueDoubleType;
	
	/** The value enum. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BeanEnum valueEnum;
	
	/** The value float. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Float valueFloat;
	
	/** The value float type. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public float valueFloatType;
	
	/** The value int. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Integer valueInt;
	
	/** The value int type. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public int valueIntType;
	
	/** The value locale. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Locale valueLocale;
	
	/** The value long. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Long valueLong;
	
	/** The value long type. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public long valueLongType;
	
	/** The value short. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Short valueShort;
	
	/** The value short type. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public short valueShortType;
	
	/** The value string. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public String valueString;
	
	/** The value time. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Time valueTime;
	
	/** The value time zone. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public TimeZone valueTimeZone;
	
	/** The value url. */
	@Bind(enabled=true)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public URL valueUrl;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	
}
