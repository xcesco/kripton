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
package sqlite.foreignKey;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class BeanA_5 {

	public long id;

	@BindColumn(foreignKey = BeanA_6.class, nullable = false)
	public Long beanA2Id;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beanA2Id == null) ? 0 : beanA2Id.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((valueString == null) ? 0 : valueString.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BeanA_5 other = (BeanA_5) obj;
		if (beanA2Id == null) {
			if (other.beanA2Id != null)
				return false;
		} else if (!beanA2Id.equals(other.beanA2Id))
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

	public String valueString;
}
