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
package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.abubusoft.kripton.BindTypeAdapter;

/**
 * <p>Defines TypeAdapter to use to the field.</p>
 * 
 * <p>This kind of adapter is applied from java to data format (JSON/XML/etc) and viceversa. <strong>It is not used in 
 * SharedPreference and SQLite generation.</strong></p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindAdapter {

	/**
	 * TypeAdapter used to convert data
	 * 
	 * @return
	 * 		instance of class converter
	 */
	public Class<? extends BindTypeAdapter<?,?>> adapter();
		
	/**
	 * Type of data stored in json/xml/etc.
	 * @return
	 */
	public Class<?> dataType();
}
