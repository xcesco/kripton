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

import com.abubusoft.kripton.android.sharedprefs.PreferenceTypeAdapter;

/**
 * <p>
 * This annotation decorates a field to use a particular Shared-Preference Type
 * Adapter to customize persistence on the Shared Preference mechanism. A type
 * adapter must implement the PreferenceTypeAdapter interface. It has two
 * parameter type: the first is the field type, the second is the type that we
 * want to use as replacement and that will be used to store data into the
 * Shared Preference. It implements two methods:
 * </p>
 * <ul>
 * <li>toJava: converts data retrieved from a Shared Preference element.</li>
 * <li>toData: converts a field into data to store into a Shared Preference
 * element.</li>
 * </ul>
 * 
 * <h3>Usage</h3>
 * 
 * <pre>
 * &#64;BindSharedPreferences
 * public class App1Preferences {
 * 
 * 	&#64;BindPreference
 * 	public HashSet&lt;String&gt; valueSet;
 * 
 * 	&#64;BindPreferenceAdapter(adapter = IntTypeAdapter.class)
 * 	&#64;BindPreference
 * 	public int right;
 * }
 * </pre>
 * <p>
 * And the associated SQL type adapter:
 * </p>
 * 
 * <pre>
public class IntTypeAdapter implements PreferenceTypeAdapte&lt;<Integer, String&gt; {

  &#64;Override
  public IntegertoJava(byte[] dataValue) {
   ...
  }

  &#64;Override
  public String toData(Integer javaValue) {
    ...
  }
}
 * </pre>
<p>This annotation is very useful when you need to persist a class that Kripton does not support directly for persistence.</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindPreferenceAdapter {

	/**
	 * Adapter class used to convert bean attribute into column value and viceversa.
	 *
	 * @return the adapter
	 */
	Class<? extends PreferenceTypeAdapter<?, ?>> adapter();
}
