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

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindDaoMany2Many {
	/**
	 * Allows to specify primary key name.
	 * 
	 * @return
	 */
	String idName() default "id";

	/**
	 * Name of the table, in SQLite style (with words underline separator). It
	 * will be converted during creation of table. If null, the name of the
	 * table will be the transformed class name.
	 * 
	 * <pre>
	 * person_city
	 * </pre>
	 * 
	 * @return defined name of the table in java style
	 */
	String tableName() default "";

	/**
	 * referred entity 1
	 * @return
	 */
	Class<?> entity1();

	/**
	 * referred entity 2
	 * @return
	 */
	Class<?> entity2();

	/**
	 * if true, it will generate standard methods for the dao:
	 * <ul>
	 * <li>Insert bean</li>
	 * <li>Select bean by foreign key 1</li>
	 * <li>Select bean by foreign key 2</li>
	 * <li>Select bean by id</li>
	 * <li>Delete bean by foreign key 1</li>
	 * <li>Delete bean by foreign key 2</li>
	 * 
	 * </ul>
	 * 
	 * @return
	 */
	boolean methods() default true;

}
