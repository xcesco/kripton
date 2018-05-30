/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
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
 * The Interface BindPreference.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindPreference {
	
	/**
	 * Enabled.
	 *
	 * @return 		if true, means field must bind persisted on shared preferences
	 */
	boolean enabled() default true;

	/**
	 * Value.
	 *
	 * @return 		Preference name. Default name is attribute name.
	 */
	String value() default "";
	
	/**
	 * enable rx generation for marked property 
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
