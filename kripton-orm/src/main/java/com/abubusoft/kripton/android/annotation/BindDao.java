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

// TODO: Auto-generated Javadoc
/**
 * <p>
 * This annotation mark an interface for DAO (Data Access Object) associated to a specific bean associated with {@link #value()} attribute. 
 * For each defined methods in the associated interface will be generated an method which use SQL statement. The kind of SQL used in methods will
 * depends from annotation used to mark method.
 * </p>
 * 
 * <p>
 * Supported queries are:
 * <ul>
 * 	<li><code>INSERT</code>: with {@link BindSqlInsert} annotation</li>
 *  <li><code>UPDATE</code>: with {@link BindSqlUpdate} annotation</li>
 *  <li><code>SELECT</code>: with {@link BindSqlSelect} annotation</li>
 *  <li><code>DELETE</code>: with {@link BindSqlDelete} annotation</li>
 * </ul> 
 * 
 * <p>Referred {@link #value()} bean must be annotated with {@link BindTable} annotation.</p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindDao {

	/**
	 * <p>Bean class to associate with this dao definition.</p>
	 * 
	 * <p>Referred {@link #value()} bean must be annotated with {@link BindTable} annotation.</p>
	 * 
	 * @return
	 * 		class of assocaited bean
	 */
	Class<?> value();

}
