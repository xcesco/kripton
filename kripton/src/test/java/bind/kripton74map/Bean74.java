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
/**
 * 
 */
package bind.kripton74map;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;

import bind.kripton74map.Bean74;
import bind.kripton74map.Enum74;




/**
 * The Class Bean74.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@BindType(allFields=true)
public class Bean74 implements Serializable {

	/**
	 * Instantiates a new bean 74.
	 */
	public Bean74()
	{	
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3113613163524431347L;

	/** The value map string integer. */
	@Bind(mapKeyName="k", mapValueName="v")
	@BindXml(elementTag="rutto")
	public Map<String, Integer> valueMapStringInteger;
	
	/** The value map enum bean. */
	@Bind(mapKeyName="k", mapValueName="v")
	@BindXml(elementTag="item")
	public Map<Enum74, Bean74> valueMapEnumBean;
	
	/** The value map int byte array. */
	@Bind(mapKeyName="k", mapValueName="v")
	public Map<Integer, byte[]> valueMapIntByteArray;
	
	/** The value map bean locale. */
	@Bind(mapKeyName="k", mapValueName="v")
	public Map<Bean74, Locale> valueMapBeanLocale;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((valueMapBeanLocale == null) ? 0 : valueMapBeanLocale.hashCode());
		result = prime * result + ((valueMapEnumBean == null) ? 0 : valueMapEnumBean.hashCode());
		result = prime * result + ((valueMapIntByteArray == null) ? 0 : valueMapIntByteArray.hashCode());
		result = prime * result + ((valueMapStringInteger == null) ? 0 : valueMapStringInteger.hashCode());
		result = prime * result + ((valueString == null) ? 0 : valueString.hashCode());
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
		Bean74 other = (Bean74) obj;
		if (valueMapBeanLocale == null) {
			if (other.valueMapBeanLocale != null)
				return false;
		} else if (!valueMapBeanLocale.equals(other.valueMapBeanLocale))
			return false;
		if (valueMapEnumBean == null) {
			if (other.valueMapEnumBean != null)
				return false;
		} else if (!valueMapEnumBean.equals(other.valueMapEnumBean))
			return false;
		if (valueMapIntByteArray == null) {
			if (other.valueMapIntByteArray != null)
				return false;
		} else if (!valueMapIntByteArray.equals(other.valueMapIntByteArray))
			return false;
		if (valueMapStringInteger == null) {
			if (other.valueMapStringInteger != null)
				return false;
		} else if (!valueMapStringInteger.equals(other.valueMapStringInteger))
			return false;
		if (valueString == null) {
			if (other.valueString != null)
				return false;
		} else if (!valueString.equals(other.valueString))
			return false;
		return true;
	}

	/** The value string. */
	public String valueString;

}
