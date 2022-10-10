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
package shared.feature.encrypt.case1;

import com.abubusoft.kripton.android.annotation.BindSharedPreferences;

import java.util.List;

/**
 * The Class AppPreferences.
 */
@BindSharedPreferences(value="named_preferences" )
public class SimpleNamedAppPreferences {

	/** The name. */
	public String name="ciao"; 
	
	/** The description. */
	protected String description;
		
	/** The value float. */
	public float valueFloat=5.0f;
	
	/** The value boolean. */
	public boolean valueBoolean;
	
	//public AppType appType;
	
	/** The string array. */
	protected String[] stringArray;
	
	/** The string list. */
	public List<String> stringList;
	
	
	/**
	 * Gets the string array.
	 *
	 * @return the string array
	 */
	public String[] getStringArray() {
		return stringArray;
	}

	/**
	 * Sets the string array.
	 *
	 * @param stringArray the new string array
	 */
	public void setStringArray(String[] stringArray) {
		this.stringArray = stringArray;
	}

	/** The value int. */
	public int valueInt;
	
	/** The value long. */
	public Long valueLong;

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
