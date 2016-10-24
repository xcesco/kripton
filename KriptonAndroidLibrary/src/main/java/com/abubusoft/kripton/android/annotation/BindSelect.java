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

/**
 * @author xcesco
 *
 *
 * @since 05/mag/2016
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BindSelect {
	

	/**
	 * if true, set distinct clause
	 * 
	 * @return
	 * 		distinct clause
	 */
	boolean distinct() default false;
	
	/**
	 * properties to include into SELECT command
	 * 
	 * @return
	 * 		property's names to include
	 */
	String[] value() default {};
	
	/**
	 * properties to include into UPDATE command
	 * @return
	 * 		property's names to exclude
	 */
	String[] excludedFields() default {};
	
	/**
	 * where condition
	 * 
	 * @return
	 * 		where condition
	 */
	String where() default "1=1";
	
	/**
	 * having statement
	 * 
	 * @return
	 * 		having statement
	 */
	String having() default "";
	
	/**
	 * having statement
	 * 
	 * @return
	 * 		groupBy statement
	 * 		
	 */
	String groupBy() default "";
	
	/**
	 * order statement
	 * 
	 * @return
	 * 		order statement
	 */
	String orderBy() default "";		

}
