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
 * <p>Decorate an database schema definition interface. This definition is needed to generate a {@link com.abubusoft.kripton.android.sqlite.AbstractDataSource} instance.</p>
 * 
 * <p>For every managed entity referred in {{@link #value()} attribute, will be used the associated {@link BindDao} definition.
 * 
 * @author xcesco
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindDataSource {
	
	/**
	 * DAOs to include in the database schema
	 * 
	 * @return
	 * 		class to include in the database schema
	 */
	Class<?>[] value();
	
	/**
	 * Name of database file
	 * 
	 * @return
	 * 		database name
	 */
	String fileName();
	
	/**
	 * Database version
	 * 
	 * @return
	 * 		database version
	 */
	int version() default 1;
	
	/**
	 * if true, generate log info
	 * 
	 * @return
	 * 		true if you want to produce log code
	 */
	boolean log() default true;
	
	/**
	 * TODO
	 * options.tablePrefix("TD_");
	 * @return
	 * 		prefix table
	 */
	String tableNamePrefix() default "";
	
	/**
	 * if true, generate async task
	 * 
	 * @return
	 * 		true if you want to generate async task class
	 */
	boolean asyncTask() default true;

	/**
	 * if true, generate cursor wrapper
	 * 
	 * @return
	 * 		true if you want to generate cursor wrapper
	 */
	boolean cursor() default true;

}
