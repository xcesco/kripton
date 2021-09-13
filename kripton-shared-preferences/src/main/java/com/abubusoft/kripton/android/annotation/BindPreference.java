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
package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * This annotation allow to specify behaviour for specific field when it is
 * persisted to a SharedPreference with <code>@BindSharedPreferences</code>
 * annotation.
 * </p>
 * 
 * <pre>
 * &#64;BindSharedPreferences(liveData = true)
 * public class AppPreferences {
 * 
 * 	public String name = "ciao";
 * 
 * 	protected String description;
 * 
 * 	public float valueFloat = 5.0f;
 * 
 * 	public boolean valueBoolean;
 * 
 * 	protected String[] stringArray;
 * 
 * 	public List&lt;String&gt; stringList;
 * 
 * 	public String[] getStringArray() {
 * 		return stringArray;
 * 	}
 * 
 * 	public void setStringArray(String[] stringArray) {
 * 		this.stringArray = stringArray;
 * 	}
 * 
 * 	public int valueInt;
 * 
 * 	public Long valueLong;
 * 
 * 	public String getDescription() {
 * 		return description;
 * 	}
 * 
 * 	public void setDescription(String description) {
 * 		this.description = description;
 * 	}
 * }
 * </pre>
 * 
 * <p>
 * With <code>liveData=true</code> Kripton will generate the
 * <code>LiveData</code> support code. This feature allows to manage Shared
 * Preferences with Live Data.
 * </p>
 * 
 * <pre>
 * BindAppPreferences sp = BindAppPreferences.getInstance();
 * sp.getDescriptionAsLiveData().observeForever(new Observer&lt;String&gt;() {
 * 
 * 	&#64;Override
 * 	public void onChanged(String t) {
 * 		Logger.info("getDescriptionAsLiveData  " + t);
 * 
 * 	}
 * });
 * </pre>
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindPreference {

	/**
	 * Enabled.
	 *
	 * @return if true, means field must bind persisted on shared preferences
	 */
	boolean enabled() default true;

	/**
	 * Value.
	 *
	 * @return Preference name. Default name is attribute name.
	 */
	String value() default "";

	/**
	 * enable rx generation for marked property
	 * 
	 * @return
	 */
	boolean rx() default true;

	/**
	 * enable live data for marked property
	 * 
	 * @return
	 */
	boolean liveData() default true;

}
