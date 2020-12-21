/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
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
package sqlite.feature.typeadapter.kripton180.adapters;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.android.SqlTypeAdapter;

import sqlite.feature.typeadapter.kripton180.Address;


/**
 * The Class TypeAdapterAddress.
 */
public class TypeAdapterAddress implements SqlTypeAdapter<Address, String> {

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.TypeAdapter#toJava(java.lang.Object)
	 */
	@Override
	public Address toJava(String dataValue) {
		if (dataValue!=null) {
			return KriptonBinder.jsonBind().parse(dataValue, Address.class);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.TypeAdapter#toData(java.lang.Object)
	 */
	@Override
	public String toData(Address javaValue) {
		if (javaValue!=null) {
			return KriptonBinder.jsonBind().serialize(javaValue);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.SqlTypeAdapter#toString(java.lang.Object)
	 */
	@Override
	public String toString(Address javaValue) {
		if (javaValue!=null) {
			return KriptonBinder.jsonBind().serialize(javaValue);
		}
		return null;
	}

}
