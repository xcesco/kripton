/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.kripton62;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.annotation.BindType;

// TODO: Auto-generated Javadoc
/**
 * The Class Bean2.
 */
@BindType
@BindSharedPreferences
public class Bean2 {
	
	/** The id. */
	public long id;
	
	/** The value. */
	public String value;
	
	/** The value byte set. */
	Set<Byte> valueByteSet;
	

	/**
	 * Gets the value byte set.
	 *
	 * @return the value byte set
	 */
	public Set<Byte> getValueByteSet() {
		return valueByteSet;
	}

	/**
	 * Sets the value byte set.
	 *
	 * @param valueByteSet the new value byte set
	 */
	public void setValueByteSet(Set<Byte> valueByteSet) {
		this.valueByteSet = valueByteSet;
	}

	/**
	 * Gets the value short set.
	 *
	 * @return the value short set
	 */
	public HashSet<Short> getValueShortSet() {
		return valueShortSet;
	}

	/**
	 * Sets the value short set.
	 *
	 * @param valueShortSet the new value short set
	 */
	public void setValueShortSet(HashSet<Short> valueShortSet) {
		this.valueShortSet = valueShortSet;
	}

	/**
	 * Gets the value integer set.
	 *
	 * @return the value integer set
	 */
	public LinkedHashSet<Integer> getValueIntegerSet() {
		return valueIntegerSet;
	}

	/**
	 * Sets the value integer set.
	 *
	 * @param valueIntegerSet the new value integer set
	 */
	public void setValueIntegerSet(LinkedHashSet<Integer> valueIntegerSet) {
		this.valueIntegerSet = valueIntegerSet;
	}

	/**
	 * Gets the value string set.
	 *
	 * @return the value string set
	 */
	public HashSet<String> getValueStringSet() {
		return valueStringSet;
	}

	/**
	 * Sets the value string set.
	 *
	 * @param valueStringSet the new value string set
	 */
	public void setValueStringSet(HashSet<String> valueStringSet) {
		this.valueStringSet = valueStringSet;
	}

	/**
	 * Gets the value character set.
	 *
	 * @return the value character set
	 */
	public Set<Character> getValueCharacterSet() {
		return valueCharacterSet;
	}

	/**
	 * Sets the value character set.
	 *
	 * @param valueCharacterSet the new value character set
	 */
	public void setValueCharacterSet(Set<Character> valueCharacterSet) {
		this.valueCharacterSet = valueCharacterSet;
	}

	/**
	 * Gets the value float set.
	 *
	 * @return the value float set
	 */
	public HashSet<Float> getValueFloatSet() {
		return valueFloatSet;
	}

	/**
	 * Sets the value float set.
	 *
	 * @param valueFloatSet the new value float set
	 */
	public void setValueFloatSet(HashSet<Float> valueFloatSet) {
		this.valueFloatSet = valueFloatSet;
	}

	/**
	 * Gets the value double set.
	 *
	 * @return the value double set
	 */
	public HashSet<Double> getValueDoubleSet() {
		return valueDoubleSet;
	}

	/**
	 * Sets the value double set.
	 *
	 * @param valueDoubleSet the new value double set
	 */
	public void setValueDoubleSet(HashSet<Double> valueDoubleSet) {
		this.valueDoubleSet = valueDoubleSet;
	}

	/**
	 * Gets the value big decimal set.
	 *
	 * @return the value big decimal set
	 */
	public HashSet<BigDecimal> getValueBigDecimalSet() {
		return valueBigDecimalSet;
	}

	/**
	 * Sets the value big decimal set.
	 *
	 * @param valueBigDecimalSet the new value big decimal set
	 */
	public void setValueBigDecimalSet(HashSet<BigDecimal> valueBigDecimalSet) {
		this.valueBigDecimalSet = valueBigDecimalSet;
	}

	/**
	 * Gets the value bean set.
	 *
	 * @return the value bean set
	 */
	public LinkedHashSet<Bean> getValueBeanSet() {
		return valueBeanSet;
	}

	/**
	 * Sets the value bean set.
	 *
	 * @param valueBeanSet the new value bean set
	 */
	public void setValueBeanSet(LinkedHashSet<Bean> valueBeanSet) {
		this.valueBeanSet = valueBeanSet;
	}

	/**
	 * Gets the value enum type set.
	 *
	 * @return the value enum type set
	 */
	public HashSet<EnumType> getValueEnumTypeSet() {
		return valueEnumTypeSet;
	}

	/**
	 * Sets the value enum type set.
	 *
	 * @param valueEnumTypeSet the new value enum type set
	 */
	public void setValueEnumTypeSet(HashSet<EnumType> valueEnumTypeSet) {
		this.valueEnumTypeSet = valueEnumTypeSet;
	}

	/** The value short set. */
	HashSet<Short> valueShortSet;
	
	/** The value integer set. */
	LinkedHashSet<Integer> valueIntegerSet;
	
	/** The value string set. */
	HashSet<String> valueStringSet=new HashSet<>(new HashSet<String>());
	
	/** The value character set. */
	Set<Character> valueCharacterSet;
	
	/** The value float set. */
	HashSet<Float> valueFloatSet;
	
	/** The value double set. */
	HashSet<Double> valueDoubleSet;
	
	/** The value big decimal set. */
	HashSet<BigDecimal> valueBigDecimalSet;
	
	/** The value bean set. */
	LinkedHashSet<Bean> valueBeanSet;
	
	/** The value enum type set. */
	HashSet<EnumType> valueEnumTypeSet;
}
