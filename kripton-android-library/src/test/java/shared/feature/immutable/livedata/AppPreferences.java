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
package shared.feature.immutable.livedata;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindSharedPreferences;

/**
 * The Class AppPreferences.
 */
@BindSharedPreferences(liveData=true)
public class AppPreferences {

	/** The name. */
	private String name="ciao"; 
	


	/**
	 * @param name
	 * @param description
	 * @param valueFloat
	 * @param valueBoolean
	 * @param stringArray
	 * @param stringList
	 */
	public AppPreferences(String name, String description, float valueFloat, boolean valueBoolean, String[] stringArray,
			List<String> stringList) {
		super();
		this.name = name;
		this.description = description;
		this.valueFloat = valueFloat;
		this.valueBoolean = valueBoolean;
		this.stringArray = stringArray;
		this.stringList = stringList;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public float getValueFloat() {
		return valueFloat;
	}

	public boolean isValueBoolean() {
		return valueBoolean;
	}

	public String[] getStringArray() {
		return stringArray;
	}

	public List<String> getStringList() {
		return stringList;
	}

	/** The description. */
	private String description;
		
	/** The value float. */
	private float valueFloat=5.0f;
	
	/** The value boolean. */
	private boolean valueBoolean;
	
	//public AppType appType;
	
	/** The string array. */
	private String[] stringArray;
	
	/** The string list. */
	private List<String> stringList;
	
	
	
}
