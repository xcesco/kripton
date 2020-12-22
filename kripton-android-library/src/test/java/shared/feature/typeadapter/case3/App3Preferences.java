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
package shared.feature.typeadapter.case3;

import com.abubusoft.kripton.android.annotation.BindPreference;
import com.abubusoft.kripton.android.annotation.BindPreferenceAdapter;
import com.abubusoft.kripton.android.annotation.BindSharedPreferences;


/**
 * The Class App3Preferences.
 */
@BindSharedPreferences
public class App3Preferences {
	
	/** The field string public. */
	@BindPreferenceAdapter(adapter=StringSetTypeAdapter.class)
	@BindPreference
	public String fieldStringPublic;
	
	/** The field string private. */
	@BindPreferenceAdapter(adapter=StringSetTypeAdapter.class)
	@BindPreference
	private String fieldStringPrivate;

	/**
	 * Gets the field string private.
	 *
	 * @return the field string private
	 */
	public String getFieldStringPrivate() {
		return fieldStringPrivate;
	}

	/**
	 * Sets the field string private.
	 *
	 * @param fieldStringPrivate the new field string private
	 */
	public void setFieldStringPrivate(String fieldStringPrivate) {
		this.fieldStringPrivate = fieldStringPrivate;
	}
		
}
