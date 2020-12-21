/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
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
package bind.kripton80contextcollection;

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

import bind.kripton80contextcollection.Bean80;
import bind.kripton80contextcollection.BeanEnum;


/**
 * The Class Bean80.
 */
@BindType("root")
public class Bean80 {
	
	/** The id. */
	@Bind("typeName")
	@BindXml(xmlType=XmlType.TAG)
	protected long id;
	
	/** The value bean. */
	public Bean80 valueBean;

	/** The value big decimal. */
	public BigDecimal valueBigDecimal;
	
	/** The value big integer. */
	public BigInteger valueBigInteger;

	/** The value bool. */
	public Boolean valueBool;

	/** The value bool type. */
	public boolean valueBoolType;
	
	/** The value byte. */
	public Byte valueByte;
	
	/** The value byte type. */
	public byte valueByteType;	
	
	/** The value calendar. */
	public Calendar valueCalendar;
	
	/** The value char. */
	public Character valueChar;
	
	/** The value char type. */
	public char valueCharType;
	
	/** The value content bool type. */
	@BindXml(xmlType=XmlType.VALUE_CDATA)
	public Integer valueContentBoolType;
	
	/** The value currency. */
	public Currency valueCurrency;
	
	/** The value date. */
	public Date valueDate;
	
	/** The value double. */
	public Double valueDouble;
	
	/** The value double type. */
	public double valueDoubleType;
	
	/** The value enum. */
	public BeanEnum valueEnum;
	
	/** The value float. */
	public Float valueFloat;
	
	/** The value float type. */
	public float valueFloatType;
	
	/** The value int. */
	public Integer valueInt;
	
	/** The value int type. */
	public int valueIntType;
	
	/** The value locale. */
	public Locale valueLocale;
	
	/** The value long. */
	public Long valueLong;
	
	/** The value long type. */
	public long valueLongType;
	
	/** The value short. */
	public Short valueShort;
	
	/** The value short type. */
	public short valueShortType;
	
	/** The value string. */
	public String valueString;
	
	/** The value time. */
	public Time valueTime;
	
	/** The value time zone. */
	public TimeZone valueTimeZone;
	
	/** The value url. */
	public URL valueUrl;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bean80 other = (Bean80) obj;
		if (id != other.id)
			return false;
		if (valueBean == null) {
			if (other.valueBean != null)
				return false;
		} else if (!valueBean.equals(other.valueBean))
			return false;
		if (valueBigDecimal == null) {
			if (other.valueBigDecimal != null)
				return false;
		} else if (!valueBigDecimal.equals(other.valueBigDecimal))
			return false;
		if (valueBigInteger == null) {
			if (other.valueBigInteger != null)
				return false;
		} else if (!valueBigInteger.equals(other.valueBigInteger))
			return false;
		if (valueBool == null) {
			if (other.valueBool != null)
				return false;
		} else if (!valueBool.equals(other.valueBool))
			return false;
		if (valueBoolType != other.valueBoolType)
			return false;
		if (valueByte == null) {
			if (other.valueByte != null)
				return false;
		} else if (!valueByte.equals(other.valueByte))
			return false;
		if (valueByteType != other.valueByteType)
			return false;
		if (valueCalendar == null) {
			if (other.valueCalendar != null)
				return false;
		} else if (!valueCalendar.equals(other.valueCalendar))
			return false;
		if (valueChar == null) {
			if (other.valueChar != null)
				return false;
		} else if (!valueChar.equals(other.valueChar))
			return false;
		if (valueCharType != other.valueCharType)
			return false;
		if (valueContentBoolType == null) {
			if (other.valueContentBoolType != null)
				return false;
		} else if (!valueContentBoolType.equals(other.valueContentBoolType))
			return false;
		if (valueCurrency == null) {
			if (other.valueCurrency != null)
				return false;
		} else if (!valueCurrency.equals(other.valueCurrency))
			return false;
		if (valueDate == null) {
			if (other.valueDate != null)
				return false;
		} else if (!valueDate.equals(other.valueDate))
			return false;
		if (valueDouble == null) {
			if (other.valueDouble != null)
				return false;
		} else if (!valueDouble.equals(other.valueDouble))
			return false;
		if (Double.doubleToLongBits(valueDoubleType) != Double.doubleToLongBits(other.valueDoubleType))
			return false;
		if (valueEnum != other.valueEnum)
			return false;
		if (valueFloat == null) {
			if (other.valueFloat != null)
				return false;
		} else if (!valueFloat.equals(other.valueFloat))
			return false;
		if (Float.floatToIntBits(valueFloatType) != Float.floatToIntBits(other.valueFloatType))
			return false;
		if (valueInt == null) {
			if (other.valueInt != null)
				return false;
		} else if (!valueInt.equals(other.valueInt))
			return false;
		if (valueIntType != other.valueIntType)
			return false;
		if (valueLocale == null) {
			if (other.valueLocale != null)
				return false;
		} else if (!valueLocale.equals(other.valueLocale))
			return false;
		if (valueLong == null) {
			if (other.valueLong != null)
				return false;
		} else if (!valueLong.equals(other.valueLong))
			return false;
		if (valueLongType != other.valueLongType)
			return false;
		if (valueShort == null) {
			if (other.valueShort != null)
				return false;
		} else if (!valueShort.equals(other.valueShort))
			return false;
		if (valueShortType != other.valueShortType)
			return false;
		if (valueString == null) {
			if (other.valueString != null)
				return false;
		} else if (!valueString.equals(other.valueString))
			return false;
		if (valueTime == null) {
			if (other.valueTime != null)
				return false;
		} else if (!valueTime.equals(other.valueTime))
			return false;
		if (valueTimeZone == null) {
			if (other.valueTimeZone != null)
				return false;
		} else if (!valueTimeZone.equals(other.valueTimeZone))
			return false;
		if (valueUrl == null) {
			if (other.valueUrl != null)
				return false;
		} else if (!valueUrl.equals(other.valueUrl))
			return false;
		return true;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((valueBean == null) ? 0 : valueBean.hashCode());
		result = prime * result + ((valueBigDecimal == null) ? 0 : valueBigDecimal.hashCode());
		result = prime * result + ((valueBigInteger == null) ? 0 : valueBigInteger.hashCode());
		result = prime * result + ((valueBool == null) ? 0 : valueBool.hashCode());
		result = prime * result + (valueBoolType ? 1231 : 1237);
		result = prime * result + ((valueByte == null) ? 0 : valueByte.hashCode());
		result = prime * result + valueByteType;
		result = prime * result + ((valueCalendar == null) ? 0 : valueCalendar.hashCode());
		result = prime * result + ((valueChar == null) ? 0 : valueChar.hashCode());
		result = prime * result + valueCharType;
		result = prime * result + ((valueContentBoolType == null) ? 0 : valueContentBoolType.hashCode());
		result = prime * result + ((valueCurrency == null) ? 0 : valueCurrency.hashCode());
		result = prime * result + ((valueDate == null) ? 0 : valueDate.hashCode());
		result = prime * result + ((valueDouble == null) ? 0 : valueDouble.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valueDoubleType);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((valueEnum == null) ? 0 : valueEnum.hashCode());
		result = prime * result + ((valueFloat == null) ? 0 : valueFloat.hashCode());
		result = prime * result + Float.floatToIntBits(valueFloatType);
		result = prime * result + ((valueInt == null) ? 0 : valueInt.hashCode());
		result = prime * result + valueIntType;
		result = prime * result + ((valueLocale == null) ? 0 : valueLocale.hashCode());
		result = prime * result + ((valueLong == null) ? 0 : valueLong.hashCode());
		result = prime * result + (int) (valueLongType ^ (valueLongType >>> 32));
		result = prime * result + ((valueShort == null) ? 0 : valueShort.hashCode());
		result = prime * result + valueShortType;
		result = prime * result + ((valueString == null) ? 0 : valueString.hashCode());
		result = prime * result + ((valueTime == null) ? 0 : valueTime.hashCode());
		result = prime * result + ((valueTimeZone == null) ? 0 : valueTimeZone.hashCode());
		result = prime * result + ((valueUrl == null) ? 0 : valueUrl.hashCode());
		return result;
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
