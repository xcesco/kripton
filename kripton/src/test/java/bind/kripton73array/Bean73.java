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
package bind.kripton73array;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;

import bind.kripton73array.Bean73;
import bind.kripton73array.Enum73;

// TODO: Auto-generated Javadoc
/**
 * The Class Bean73.
 */
@BindType
public class Bean73 {
	
	/**
	 * Instantiates a new bean 73.
	 */
	public Bean73()
	{
		
	}
	
	/**
	 * Instantiates a new bean 73.
	 *
	 * @param name the name
	 */
	public Bean73(String name)
	{
		this.name=name;
	}
	
	/** The name. */
	public String name;
	
	/** The value bean array. */
	@BindXml(elementTag="item")
	public Bean73[] valueBeanArray;
	
	/** The value big decimal array. */
	@Bind(enabled=true)
	public BigDecimal[] valueBigDecimalArray;
	
	/** The value big integer array. */
	@Bind(enabled=true)
	@BindXml(elementTag="item")
	public BigInteger[] valueBigIntegerArray;
	
	/** The value byte array. */
	@BindXml(elementTag="item")
	public Byte[] valueByteArray;
	
	/** The value character array. */
	@BindXml(elementTag="item")
	public Character[] valueCharacterArray;
	
	/** The value double array. */
	@BindXml(elementTag="item")
	public Double[] valueDoubleArray;
	
	/** The value enum array. */
	public Enum73[] valueEnumArray;
	
	/** The value float array. */
	@BindXml(elementTag="item")
	public Float[] valueFloatArray;
	
	/** The value int array. */
	@BindXml(elementTag="item")
	private Integer[] valueIntArray;
	
	/**
	 * Gets the value int array.
	 *
	 * @return the value int array
	 */
	public Integer[] getValueIntArray() {
		return valueIntArray;
	}

	/**
	 * Sets the value int array.
	 *
	 * @param valueIntArray the new value int array
	 */
	public void setValueIntArray(Integer[] valueIntArray) {
		this.valueIntArray = valueIntArray;
	}

	/** The value long array. */
	@BindXml(elementTag="item")
	public Long[] valueLongArray;

	/** The value short array. */
	@BindXml(elementTag="item")
	public Short[] valueShortArray;
	
	/** The value string array. */
	@BindXml(elementTag="item")
	public String[] valueStringArray;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(valueBeanArray);
		result = prime * result + Arrays.hashCode(valueBigDecimalArray);
		result = prime * result + Arrays.hashCode(valueBigIntegerArray);
		result = prime * result + Arrays.hashCode(valueByteArray);
		result = prime * result + Arrays.hashCode(valueCharacterArray);
		result = prime * result + Arrays.hashCode(valueDoubleArray);
		result = prime * result + Arrays.hashCode(valueEnumArray);
		result = prime * result + Arrays.hashCode(valueFloatArray);
		result = prime * result + Arrays.hashCode(valueIntArray);
		result = prime * result + Arrays.hashCode(valueLongArray);
		result = prime * result + Arrays.hashCode(valueShortArray);
		result = prime * result + Arrays.hashCode(valueStringArray);
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
		Bean73 other = (Bean73) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (!Arrays.equals(valueBeanArray, other.valueBeanArray))
			return false;
		if (!Arrays.equals(valueBigDecimalArray, other.valueBigDecimalArray))
			return false;
		if (!Arrays.equals(valueBigIntegerArray, other.valueBigIntegerArray))
			return false;
		if (!Arrays.equals(valueByteArray, other.valueByteArray))
			return false;
		if (!Arrays.equals(valueCharacterArray, other.valueCharacterArray))
			return false;
		if (!Arrays.equals(valueDoubleArray, other.valueDoubleArray))
			return false;
		if (!Arrays.equals(valueEnumArray, other.valueEnumArray))
			return false;
		if (!Arrays.equals(valueFloatArray, other.valueFloatArray))
			return false;
		if (!Arrays.equals(valueIntArray, other.valueIntArray))
			return false;
		if (!Arrays.equals(valueLongArray, other.valueLongArray))
			return false;
		if (!Arrays.equals(valueShortArray, other.valueShortArray))
			return false;
		if (!Arrays.equals(valueStringArray, other.valueStringArray))
			return false;
		return true;
	}
		
	
	
}
