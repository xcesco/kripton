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
package bind.kripton72;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import com.abubusoft.kripton.annotation.BindType;


/**
 * All field are collection without external tag.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@BindType
public class Bean72A {
	
	/**
	 * Instantiates a new bean 72 A.
	 */
	public Bean72A()
	{
		
	}
	
	/**
	 * Instantiates a new bean 72 A.
	 *
	 * @param name the name
	 */
	public Bean72A(String name)
	{
		this.name=name;
	}
	
	/** The name. */
	public String name;
	
	/** The value bean set. */
	public HashSet<Bean72A> valueBeanSet;
	
	/** The value big decimal set. */
	public HashSet<BigDecimal> valueBigDecimalSet;
	
	/** The value big integer set. */
	public HashSet<BigInteger> valueBigIntegerSet;
	
	/** The value byte set. */
	public Set<Byte> valueByteSet;
	
	/** The value character set. */
	public HashSet<Character> valueCharacterSet;
	
	/** The value double set. */
	public Set<Double> valueDoubleSet;
	
	/** The value enum set. */
	public HashSet<Enum72> valueEnumSet;
	
	/** The value float set. */
	public Set<Float> valueFloatSet;
	
	/** The value int set. */
	private Set<Integer> valueIntSet;
	
	/**
	 * Gets the value int set.
	 *
	 * @return the value int set
	 */
	public Set<Integer> getValueIntSet() {
		return valueIntSet;
	}

	/**
	 * Sets the value int set.
	 *
	 * @param valueIntSet the new value int set
	 */
	public void setValueIntSet(Set<Integer> valueIntSet) {
		this.valueIntSet = valueIntSet;
	}

	/** The value long set. */
	public Set<Long> valueLongSet;

	/** The value short set. */
	public Set<Short> valueShortSet;
	
	/** The value string set. */
	public Set<String> valueStringSet;
	
	/** The zalue string final. */
	public String zalueStringFinal;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((valueBeanSet == null) ? 0 : valueBeanSet.hashCode());
		result = prime * result + ((valueBigDecimalSet == null) ? 0 : valueBigDecimalSet.hashCode());
		result = prime * result + ((valueBigIntegerSet == null) ? 0 : valueBigIntegerSet.hashCode());
		result = prime * result + ((valueByteSet == null) ? 0 : valueByteSet.hashCode());
		result = prime * result + ((valueCharacterSet == null) ? 0 : valueCharacterSet.hashCode());
		result = prime * result + ((valueDoubleSet == null) ? 0 : valueDoubleSet.hashCode());
		result = prime * result + ((valueEnumSet == null) ? 0 : valueEnumSet.hashCode());
		result = prime * result + ((valueFloatSet == null) ? 0 : valueFloatSet.hashCode());
		result = prime * result + ((valueIntSet == null) ? 0 : valueIntSet.hashCode());
		result = prime * result + ((valueLongSet == null) ? 0 : valueLongSet.hashCode());
		result = prime * result + ((valueShortSet == null) ? 0 : valueShortSet.hashCode());
		result = prime * result + ((valueStringSet == null) ? 0 : valueStringSet.hashCode());
		result = prime * result + ((zalueStringFinal == null) ? 0 : zalueStringFinal.hashCode());
		return result;
	}

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
		Bean72A other = (Bean72A) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (valueBeanSet == null) {
			if (other.valueBeanSet != null)
				return false;
		} else if (!valueBeanSet.equals(other.valueBeanSet))
			return false;
		if (valueBigDecimalSet == null) {
			if (other.valueBigDecimalSet != null)
				return false;
		} else if (!valueBigDecimalSet.equals(other.valueBigDecimalSet))
			return false;
		if (valueBigIntegerSet == null) {
			if (other.valueBigIntegerSet != null)
				return false;
		} else if (!valueBigIntegerSet.equals(other.valueBigIntegerSet))
			return false;
		if (valueByteSet == null) {
			if (other.valueByteSet != null)
				return false;
		} else if (!valueByteSet.equals(other.valueByteSet))
			return false;
		if (valueCharacterSet == null) {
			if (other.valueCharacterSet != null)
				return false;
		} else if (!valueCharacterSet.equals(other.valueCharacterSet))
			return false;
		if (valueDoubleSet == null) {
			if (other.valueDoubleSet != null)
				return false;
		} else if (!valueDoubleSet.equals(other.valueDoubleSet))
			return false;
		if (valueEnumSet == null) {
			if (other.valueEnumSet != null)
				return false;
		} else if (!valueEnumSet.equals(other.valueEnumSet))
			return false;
		if (valueFloatSet == null) {
			if (other.valueFloatSet != null)
				return false;
		} else if (!valueFloatSet.equals(other.valueFloatSet))
			return false;
		if (valueIntSet == null) {
			if (other.valueIntSet != null)
				return false;
		} else if (!valueIntSet.equals(other.valueIntSet))
			return false;
		if (valueLongSet == null) {
			if (other.valueLongSet != null)
				return false;
		} else if (!valueLongSet.equals(other.valueLongSet))
			return false;
		if (valueShortSet == null) {
			if (other.valueShortSet != null)
				return false;
		} else if (!valueShortSet.equals(other.valueShortSet))
			return false;
		if (valueStringSet == null) {
			if (other.valueStringSet != null)
				return false;
		} else if (!valueStringSet.equals(other.valueStringSet))
			return false;
		if (zalueStringFinal == null) {
			if (other.zalueStringFinal != null)
				return false;
		} else if (!zalueStringFinal.equals(other.zalueStringFinal))
			return false;
		return true;
	}
	
	
	
}
