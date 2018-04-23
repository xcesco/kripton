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
package bind.kripton80contextcollection;

import com.abubusoft.kripton.annotation.BindType;

import bind.kripton80contextcollection.Bean80A;

// TODO: Auto-generated Javadoc
/**
 * The Class Bean80A.
 */
@BindType
public class Bean80A {
	
	/** The id. */
	protected long id;
	
	/** The value bean. */
	public Bean80A valueBean;

//	public BigDecimal valueBigDecimal;
//	
//	public BigInteger valueBigInteger;
//
//	public Boolean valueBool;
//
//	public boolean valueBoolType;
//	
//	public Byte valueByte;
//	
//	public byte valueByteType;	
//	
//	public Calendar valueCalendar;
//	
//	public Character valueChar;
//	
//	public char valueCharType;
//	
//	@BindXml(xmlType=XmlType.VALUE_CDATA)
//	public Integer valueContentBoolType;
//	
//	public Currency valueCurrency;
//	
//	public Date valueDate;
//	
//	public Double valueDouble;
//	
//	public double valueDoubleType;
//	
//	public BeanEnum valueEnum;
//	
//	public Float valueFloat;
//	
//	public float valueFloatType;
//	
//	public Integer valueInt;
//	
//	public int valueIntType;
//	
//	public Locale valueLocale;
//	
//	public Long valueLong;
//	
//	public long valueLongType;
//	
//	public Short valueShort;
//	
//	public short valueShortType;
	
	/** The value string. */
public String valueString;
	
//	public Time valueTime;
//	
//	public TimeZone valueTimeZone;
//	
//	public URL valueUrl;
	
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
		Bean80A other = (Bean80A) obj;
		if (id != other.id)
			return false;
		if (valueBean == null) {
			if (other.valueBean != null)
				return false;
		} else if (!valueBean.equals(other.valueBean))
			return false;
		if (valueString == null) {
			if (other.valueString != null)
				return false;
		} else if (!valueString.equals(other.valueString))
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
		result = prime * result + ((valueString == null) ? 0 : valueString.hashCode());
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
