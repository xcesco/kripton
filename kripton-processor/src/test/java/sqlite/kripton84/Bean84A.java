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
package sqlite.kripton84;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.abubusoft.kripton.annotation.BindType;

// TODO: Auto-generated Javadoc
/**
 * The Class Bean84A.
 */
@BindType
public class Bean84A {
	
	/** The id. */
	public long id;
	
	/** The param 1. */
	public String param1;
	
	/** The param 2. */
	public String param2;
	
	/** The param 3. */
	public String param3;
	
	/** The param 4. */
	public String param4;
	
	/** The column list string. */
	public List<String> columnListString;
	
	/** The column map integer string. */
	public Map<Integer, String> columnMapIntegerString;
	
	/** The column array char. */
	public Character[] columnArrayChar;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(columnArrayByteType);
		result = prime * result + Arrays.hashCode(columnArrayChar);
		result = prime * result + Arrays.hashCode(columnArrayCharType);
		result = prime * result + ((columnBean == null) ? 0 : columnBean.hashCode());
		result = prime * result + ((columnListString == null) ? 0 : columnListString.hashCode());
		result = prime * result + ((columnMapIntegerString == null) ? 0 : columnMapIntegerString.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Bean84A other = (Bean84A) obj;
		if (!Arrays.equals(columnArrayByteType, other.columnArrayByteType))
			return false;
		if (!Arrays.equals(columnArrayChar, other.columnArrayChar))
			return false;
		if (!Arrays.equals(columnArrayCharType, other.columnArrayCharType))
			return false;
		if (columnBean == null) {
			if (other.columnBean != null)
				return false;
		} else if (!columnBean.equals(other.columnBean))
			return false;
		if (columnListString == null) {
			if (other.columnListString != null)
				return false;
		} else if (!columnListString.equals(other.columnListString))
			return false;
		if (columnMapIntegerString == null) {
			if (other.columnMapIntegerString != null)
				return false;
		} else if (!columnMapIntegerString.equals(other.columnMapIntegerString))
			return false;
		if (id != other.id)
			return false;
		if (valueString == null) {
			if (other.valueString != null)
				return false;
		} else if (!valueString.equals(other.valueString))
			return false;
		return true;
	}

	/** The column array char type. */
	public char[] columnArrayCharType;
	
	/** The column bean. */
	public Bean84A columnBean;
	
	/** The column array byte type. */
	public byte[] columnArrayByteType;
	
	/** The value string. */
	public String valueString;
}
