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

import com.abubusoft.kripton.android.sqlite.NoPopulator;
import com.abubusoft.kripton.android.sqlite.SQLitePopulator;

/**
 * <p>
 * Decorate an interface to define a database schema. This definition is needed
 * to generate a {@link com.abubusoft.kripton.android.sqlite.AbstractDataSource}
 * instance.
 * </p>
 * 
 * <p>
 * For every managed entity referred in {{@link #dao()} attribute, will be used
 * the associated {@link BindDao} definition.
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindDataSource {

	/**
	 * DAOs to include in the database schema
	 * 
	 * @return class to include in the database schema
	 */
	Class<?>[] daoSet();

	/**
	 * Name of database file
	 * 
	 * @return database name
	 */
	String fileName();

	/**
	 * Database version
	 * 
	 * @return database version
	 */
	int version() default 1;

	/**
	 * if true, generate log info
	 * 
	 * @return true if you want to produce log code
	 */
	boolean log() default true;

	/**
	 * <p>
	 * if true, generate schema sql file, in <code>schema/</code> folder.
	 * </p>
	 * 
	 * <p>
	 * The name of file will be:
	 * </p>
	 * 
	 * <pre>
	 * {name of datasource}.schema.{version}.sql
	 * </pre>
	 * 
	 * Example:
	 * 
	 * <pre>
	 * xeno.schema.1.sql
	 * </pre>
	 * 
	 * @return
	 */
	boolean schema() default false;

	/**
	 * if true, generate async task
	 * 
	 * @return true if you want to generate async task class
	 */
	boolean asyncTask() default false;

	/**
	 * if true, generate cursor wrapper
	 * 
	 * @return true if you want to generate cursor wrapper
	 */
	boolean cursorWrapper() default false;

	/**
	 * if true, generate rx support
	 * 
	 * @return true if you want to generate rx support
	 */
	boolean rx() default false;

	/**
	 * if true, generate database in memory
	 * 
	 * @return true if you want to generate database in memory
	 */
	boolean inMemory() default false;

	/**
	 * When a datasource instance is created, these are the executed tasks.
	 * 
	 * @return
	 */
	BindDataSourceUpdateTask[] updateTasks() default {};

	/**
	 * {@link SQLitePopulator#execute()} is executed after database creation.
	 * 
	 * @return
	 */
	Class<? extends SQLitePopulator> populator() default NoPopulator.class;

}
