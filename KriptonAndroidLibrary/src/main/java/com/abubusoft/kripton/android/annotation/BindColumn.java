/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.sqlite.FieldType;

/**
 * 
 * F
 * 
 * @author xcesco
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindColumn {

	static boolean NULLABLE_DEFAULT = true;
	
	/**
	 * 
	 * @return
	 * 		if true, means field must bind persist model
	 */
	boolean enabled() default true;

	/**
	 * Name of the column. If not present, the column name is same of field. It must be specified in java style naming conventions.
	 * 
	 * @return name of the column
	 */
	public String name() default "";

	/**
	 * Type of column
	 * 
	 * @return type of the column
	 */
	public ColumnType value() default ColumnType.STANDARD;

	/**
	 * if true, column can be set to null
	 * 
	 * @return if true, column can be set to null
	 */
	public boolean nullable() default NULLABLE_DEFAULT;
	
	/**
	 * Transformation to apply
	 * 
	 * @return
	 * 		transformation
	 */
	public FieldType fieldType() default FieldType.NONE;

}
