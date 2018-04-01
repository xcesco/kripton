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

import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BindSqlUpdate {
	
	/**
	 * <p>
	 * Type of UPDATE. Default value is NONE. See
	 * <a href="https://www.sqlite.org/lang_conflict.html">here</a> for more
	 * info.
	 * </p>
	 * 
	 * @return type of insert.
	 */
	ConflictAlgorithmType conflictAlgorithm() default ConflictAlgorithmType.NONE;

	/**
	 * properties to include into UPDATE command
	 * @return
	 * 		property's names to include
	 */
	String[] fields() default {};
	
	/**
	 * properties to exclude from UPDATE command
	 * @return
	 * 		property's names to exclude
	 */
	String[] excludedFields() default {};
	
	/**
	 * where conditions
	 * 
	 * @return
	 * 		where conditions
	 */
	String where() default "";
	
	/**
	 * 
	 * <p>
	 * JQL value. With this attribute, it is possibile to specify directly the JQL code. JQL means that you can write SQL using field's names and class name indeed
	 * of column and table names. Moreover, it is possibile to specify where to use the dynamic parts of query through dynamic statements like DYNAMIC_WHERE, DYNAMIC_ORDER_BY, DYNAMIC_PAGE_SIZE, DYNAMIC_PAGE_OFFSET, encapsulated
	 * in <code>#{ <dynamic-part-name> }</code>
	 * </p>
	 * 
	 * <p>For example, for a <code>select</code> statement, you can write:</p>
	 * 
	 * <pre>
	 * SELECT * FROM media WHERE mediaId IN (SELECT mediaId FROM fav WHERE #{DYNAMIC_WHERE}) ORDER BY indx DESC LIMIT 0, 100 
	 * </pre>
	 * 
	 * <strong>If you use this attribute, no other attributes can be defined for the annotation</strong>.
	 * 
	 * @return
	 * 	JQL code specified by user
	 */
	String jql() default "";
}
