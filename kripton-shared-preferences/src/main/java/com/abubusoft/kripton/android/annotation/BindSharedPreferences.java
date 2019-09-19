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
 * Bind bean to shared preferences. An annotated class name must finish with
 * prefix "SharedPreferences" or "Preferences".
 * </p>
 * <h3>Usage</h3>
 * <p>See <a href="https://github.com/xcesco/kripton/wiki/Persist-with-SharedPreferences">SharedPreferences</a> to see how to use it.</p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindSharedPreferences {

	/**
	 * File name for generated shared preferences. If it's no defined, class
	 * name will be used as filename too.
	 * 
	 * @return filename for shared preference.
	 */
	String value() default "";

	/**
	 * <p>
	 * Enable rx generation for marked property. Set to <code>true</code> this
	 * attribute will generate rx support for each properties.
	 * </p>
	 * 
	 * @return
	 */
	boolean rx() default false;

	/**
	 * <p>Enable live data for marked property. Set to <code>true</code> this
	 * attribute will generate livedata support for each properties.</p>
	 * 
	 * @return
	 */
	boolean liveData() default false;

}
