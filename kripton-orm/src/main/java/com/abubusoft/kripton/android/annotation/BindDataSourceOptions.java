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

import com.abubusoft.kripton.android.sqlite.DatabaseLifecycleHandler;
import com.abubusoft.kripton.android.sqlite.NoCursorFactory;
import com.abubusoft.kripton.android.sqlite.NoDatabaseErrorHandler;
import com.abubusoft.kripton.android.sqlite.NoDatabaseLifecycleHandler;
import com.abubusoft.kripton.android.sqlite.NoPopulator;
import com.abubusoft.kripton.android.sqlite.SQLitePopulator;

import android.app.Application;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * <p>
 * Used to define datasource options with annotation. It's usefull when you need
 * to define a custom configuration without write code in
 * {@link Application#onCreate()}. A typical example is when you need to
 * generate a content provider and you want to modifiy datasource default
 * configuration.
 * </p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindDataSourceOptions {

	public Class<? extends CursorFactory> cursorFactory() default NoCursorFactory.class;

	public Class<? extends DatabaseErrorHandler> databaseErrorHandler() default NoDatabaseErrorHandler.class;

	public Class<? extends DatabaseLifecycleHandler> databaseLifecycleHandler() default NoDatabaseLifecycleHandler.class;

	/**
	 * if true, generate database in memory
	 * 
	 * @return true if you want to generate database in memory
	 */
	boolean inMemory() default false;

	/**
	 * @return true to show operations log
	 * 
	 */
	boolean logEnabled() default true;

	/**
	 * {@link SQLitePopulator#execute()} is executed after database creation.
	 * 
	 * @return
	 */
	Class<? extends SQLitePopulator> populator() default NoPopulator.class;

	/**
	 * When a datasource instance is created, these are the executed tasks.
	 * 
	 * @return
	 */
	BindDataSourceUpdateTask[] updateTasks() default {};

}
