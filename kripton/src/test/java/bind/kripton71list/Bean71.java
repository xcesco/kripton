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
package bind.kripton71list;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;

import bind.kripton71list.Bean71;
import bind.kripton71list.Enum71;

// TODO: Auto-generated Javadoc
/**
 * The Class Bean71.
 */
@BindType
public class Bean71 {

	/** The value enum list. */
	public LinkedList<Enum71> valueEnumList;

	/** The value bean list. */
	@Bind(enabled = true)
	@BindXml(elementTag = "item")
	public LinkedList<Bean71> valueBeanList;

	/** The value big decimal list. */
	@Bind(enabled = true)
	public LinkedList<BigDecimal> valueBigDecimalList;

	/** The value big integer list. */
	@BindXml(elementTag = "item")
	public LinkedList<BigInteger> valueBigIntegerList;

	/** The value byte list. */
	@BindXml(elementTag = "item")
	public List<Byte> valueByteList;

	/** The value character list. */
	@BindXml(elementTag = "item")
	public ArrayList<Character> valueCharacterList;

	/** The value double list. */
	@BindXml(elementTag = "item")
	public List<Double> valueDoubleList;

	/** The value float list. */
	@BindXml(elementTag = "item")
	public List<Float> valueFloatList;

	/** The value int list. */
	@BindXml(elementTag = "item")
	private List<Integer> valueIntList;

	/** The value long list. */
	@BindXml(elementTag = "item")
	public List<Long> valueLongList;

	/** The value short list. */
	@BindXml(elementTag = "item")
	public List<Short> valueShortList;

	/** The value string list. */
	@BindXml(elementTag = "item")
	public List<String> valueStringList;

	/** The zalue string final. */
	@Bind(enabled = true)
	public String zalueStringFinal;

	/**
	 * Instantiates a new bean 71.
	 */
	public Bean71() {

	}

	/**
	 * Instantiates a new bean 71.
	 *
	 * @param name the name
	 */
	public Bean71(String name) {
		this.zalueStringFinal = name;
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
		Bean71 other = (Bean71) obj;
		if (valueBeanList == null) {
			if (other.valueBeanList != null)
				return false;
		} else if (!valueBeanList.equals(other.valueBeanList))
			return false;
		if (valueBigDecimalList == null) {
			if (other.valueBigDecimalList != null)
				return false;
		} else if (!valueBigDecimalList.equals(other.valueBigDecimalList))
			return false;
		if (valueBigIntegerList == null) {
			if (other.valueBigIntegerList != null)
				return false;
		} else if (!valueBigIntegerList.equals(other.valueBigIntegerList))
			return false;
		if (valueByteList == null) {
			if (other.valueByteList != null)
				return false;
		} else if (!valueByteList.equals(other.valueByteList))
			return false;
		if (valueCharacterList == null) {
			if (other.valueCharacterList != null)
				return false;
		} else if (!valueCharacterList.equals(other.valueCharacterList))
			return false;
		if (valueDoubleList == null) {
			if (other.valueDoubleList != null)
				return false;
		} else if (!valueDoubleList.equals(other.valueDoubleList))
			return false;
		if (valueEnumList == null) {
			if (other.valueEnumList != null)
				return false;
		} else if (!valueEnumList.equals(other.valueEnumList))
			return false;
		if (valueFloatList == null) {
			if (other.valueFloatList != null)
				return false;
		} else if (!valueFloatList.equals(other.valueFloatList))
			return false;
		if (valueIntList == null) {
			if (other.valueIntList != null)
				return false;
		} else if (!valueIntList.equals(other.valueIntList))
			return false;
		if (valueLongList == null) {
			if (other.valueLongList != null)
				return false;
		} else if (!valueLongList.equals(other.valueLongList))
			return false;
		if (valueShortList == null) {
			if (other.valueShortList != null)
				return false;
		} else if (!valueShortList.equals(other.valueShortList))
			return false;
		if (valueStringList == null) {
			if (other.valueStringList != null)
				return false;
		} else if (!valueStringList.equals(other.valueStringList))
			return false;
		if (zalueStringFinal == null) {
			if (other.zalueStringFinal != null)
				return false;
		} else if (!zalueStringFinal.equals(other.zalueStringFinal))
			return false;
		return true;
	}

	/**
	 * Gets the value int list.
	 *
	 * @return the value int list
	 */
	public List<Integer> getValueIntList() {
		return valueIntList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((valueBeanList == null) ? 0 : valueBeanList.hashCode());
		result = prime * result + ((valueBigDecimalList == null) ? 0 : valueBigDecimalList.hashCode());
		result = prime * result + ((valueBigIntegerList == null) ? 0 : valueBigIntegerList.hashCode());
		result = prime * result + ((valueByteList == null) ? 0 : valueByteList.hashCode());
		result = prime * result + ((valueCharacterList == null) ? 0 : valueCharacterList.hashCode());
		result = prime * result + ((valueDoubleList == null) ? 0 : valueDoubleList.hashCode());
		result = prime * result + ((valueEnumList == null) ? 0 : valueEnumList.hashCode());
		result = prime * result + ((valueFloatList == null) ? 0 : valueFloatList.hashCode());
		result = prime * result + ((valueIntList == null) ? 0 : valueIntList.hashCode());
		result = prime * result + ((valueLongList == null) ? 0 : valueLongList.hashCode());
		result = prime * result + ((valueShortList == null) ? 0 : valueShortList.hashCode());
		result = prime * result + ((valueStringList == null) ? 0 : valueStringList.hashCode());
		result = prime * result + ((zalueStringFinal == null) ? 0 : zalueStringFinal.hashCode());
		return result;
	}

	/**
	 * Sets the value int list.
	 *
	 * @param valueIntList the new value int list
	 */
	public void setValueIntList(List<Integer> valueIntList) {
		this.valueIntList = valueIntList;
	}

}
