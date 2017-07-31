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
 * <p>
 * When a class is converted to a table, the name of table is obtained from Java
 * class name with a upper camel to Lower under score convertion. For example
 * SayHello class name will be transformed in say_hello table name.
 * </p>
 * 
 * <p>
 * This annotation allow to specify the name of the associated table associated
 * to a Java class. Note that specified name will be transformed like a Java
 * class name.
 * </p>
 * 
 * <p>
 * Take this class
 * </p>
 * 
 * <pre>
 * &#064;BindType
 * &#064;BindTable("WsBean")
 * public class Bean05 {
 * 
 * 	&#064;BindColumn(columnType = ColumnType.PRIMARY_KEY)
 * 	protected long pk;
 * 
 * 	protected long number;
 * 
 * 	protected String text;
 * 
 * 	protected byte[] content;
 * 
 * 	protected Date creationTime;
 * }
 * </pre>
 * 
 * <p>
 * Its associated table name is <code>ws_bean</code>.
 * </p>
 * 
 * @author xcesco
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindTable {
	/**
	 * Name of the table, in SQLite style (with words underline separator). It will be converted during creation of
	 * table. If null, the name of the table will be the transformed class name.
	 * 
	 * @return defined name of the table in java style
	 */
	String name() default "";

	/**
	 * <p>
	 * Allow to crete (multicolumn or single column) indexes for this table. 
	 * </p>
	 * 
	 * @return indexes definition
	 */
	String[] indexes() default {};
	
	/**
	 * <p>
	 * Allow to crete unique (multicolumn or single column) indexes for this table. 
	 * </p>
	 * 
	 * @return indexes definition
	 */
	String[] uniqueIndexes() default {};
	
}
