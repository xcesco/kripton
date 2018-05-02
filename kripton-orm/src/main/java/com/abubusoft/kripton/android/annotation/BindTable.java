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
 * <p>
 * This annotation allows to manage the class as data model and will instruct
 * Kripton to generate an associated table in the data source that contains it.
 * This annotation allows to specify the table name and the
 * 
 * When a class is converted to a table, the name of the table is obtained from
 * Java class name with an upper camel to Lower underscore conversion. For
 * example `SayHello` class name will be transformed into `say_hello` table
 * name.
 * 
 * This annotation allows specifying the name of the associated table associated
 * with a Java class. Note that specified name will be transformed like a Java
 * class name.
 * </p>
 * 
 * <h3>Attributes</h3>
 * <ul>
 * <li><strong>indexes</strong>: allows defining indexes on the table. See
 * {@link BindIndex} for more informations.</li>
 * <li><strong>name</strong>: name of the table.</li>
 * </ul>
 * 
 * <h3>Usage</h3>
 * 
 * <pre>
&#64;BindTable(
  name="ws_bean",
  indexes= {
    &#64;BindIndex({"birthCity", "birthDay desc"}),
    &#64;BindIndex({"surname"}),
    &#64;BindIndex(value={"name","surname", "date desc"}, unique=true )
  }
)
public class Person {
  ...
}
 * </pre>
 * 
 * Its associated table name is `ws_bean` and it will generate the following
 * table definition:
 * 
 * <pre>
CREATE TABLE ws_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, alias_name TEXT UNIQUE, date TEXT, name TEXT, surname TEXT, birth_city TEXT, birth_day TEXT); 
CREATE INDEX idx_person_name ON person(name); CREATE INDEX idx_person_surname ON person(surname); 
CREATE UNIQUE INDEX idx_person_0 on person (name, surname, date desc); 
CREATE INDEX idx_person_0 on person (birth_city, birth_day desc); 
CREATE INDEX idx_person_1 on person (surname);
 * </pre>
 * 
 * 
 * @author xcesco
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindTable {
	/**
	 * Name of the table in SQL world. If null, the name of the
	 * table will be the transformed class name.
	 * 
	 * @return defined name of the table in java style
	 */
	String name() default "";

	/**
	 * <p>
	 * Allow to cretae (multicolumn or single column) indexes for this table.
	 * </p>
	 * 
	 * @return indexes definition
	 */
	BindIndex[] indexes() default {};

}
