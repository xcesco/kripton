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
package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Define a dynamic where condition. It is appended to <code>where</code> statement defined with attribute <code>where</code> in <code>BindSqlSelect</code> annotation.
 * 
 * <ul>
 *    <li>on SELECT, DELETE, UPDATE query type</li>
 *    <li>only one annotation for method</li>
 * </ul>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface BindSqlDynamicWhere {
	
	/**
	 * Conjunction to prepend to dynamic where statement.
	 */
	public enum PrependType {
		
		/** The and. */
		AND,
		
		/** The or. */
		OR
	}
	
	/**
	 * Conjunction to prepend to dynamic where. Default value is AND
	 *
	 * @return the prepend type
	 */
	PrependType prepend() default PrependType.AND;

}
