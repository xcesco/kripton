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
package com.abubusoft.kripton.processor.kripton62;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Time;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindPreference;
import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindSharedPreferences
public class Bean2 {
	
	long id;
	
	Set<Byte> valueByteSet;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Byte> getValueByteSet() {
		return valueByteSet;
	}

	public void setValueByteSet(Set<Byte> valueByteSet) {
		this.valueByteSet = valueByteSet;
	}

	public HashSet<Short> getValueShortSet() {
		return valueShortSet;
	}

	public void setValueShortSet(HashSet<Short> valueShortSet) {
		this.valueShortSet = valueShortSet;
	}

	public LinkedHashSet<Integer> getValueIntegerSet() {
		return valueIntegerSet;
	}

	public void setValueIntegerSet(LinkedHashSet<Integer> valueIntegerSet) {
		this.valueIntegerSet = valueIntegerSet;
	}

	public HashSet<String> getValueStringSet() {
		return valueStringSet;
	}

	public void setValueStringSet(HashSet<String> valueStringSet) {
		this.valueStringSet = valueStringSet;
	}

	public Set<Character> getValueCharacterSet() {
		return valueCharacterSet;
	}

	public void setValueCharacterSet(Set<Character> valueCharacterSet) {
		this.valueCharacterSet = valueCharacterSet;
	}

	public HashSet<Float> getValueFloatSet() {
		return valueFloatSet;
	}

	public void setValueFloatSet(HashSet<Float> valueFloatSet) {
		this.valueFloatSet = valueFloatSet;
	}

	public HashSet<Double> getValueDoubleSet() {
		return valueDoubleSet;
	}

	public void setValueDoubleSet(HashSet<Double> valueDoubleSet) {
		this.valueDoubleSet = valueDoubleSet;
	}

	public HashSet<BigDecimal> getValueBigDecimalSet() {
		return valueBigDecimalSet;
	}

	public void setValueBigDecimalSet(HashSet<BigDecimal> valueBigDecimalSet) {
		this.valueBigDecimalSet = valueBigDecimalSet;
	}

	public LinkedHashSet<Bean> getValueBeanSet() {
		return valueBeanSet;
	}

	public void setValueBeanSet(LinkedHashSet<Bean> valueBeanSet) {
		this.valueBeanSet = valueBeanSet;
	}

	public HashSet<EnumType> getValueEnumTypeSet() {
		return valueEnumTypeSet;
	}

	public void setValueEnumTypeSet(HashSet<EnumType> valueEnumTypeSet) {
		this.valueEnumTypeSet = valueEnumTypeSet;
	}

	HashSet<Short> valueShortSet;
	
	LinkedHashSet<Integer> valueIntegerSet;
	
	HashSet<String> valueStringSet;
	
	Set<Character> valueCharacterSet;
	
	HashSet<Float> valueFloatSet;
	
	HashSet<Double> valueDoubleSet;
	
	HashSet<BigDecimal> valueBigDecimalSet;
	
	LinkedHashSet<Bean> valueBeanSet;
	
	HashSet<EnumType> valueEnumTypeSet;
}
