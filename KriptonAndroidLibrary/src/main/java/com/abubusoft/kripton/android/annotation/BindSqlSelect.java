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
 * Allow to query a database table. When you define the query through interface's method you can define query parameter by a DAO's associated bean instance, or directly with fields.
 * </p>
 * 
 * <h2>Query parameters</h2>
 * <p>Almost all parameters used in method can be used as query parameter.</p>
 * 
 * <pre>
 * &#064;BindSqlSelect(where = "name=${name} and surname=${surname}")
 * Person selectOne(String name, @BindSqlParam("surname") String temp);
 * </pre>
 * 
 * <p>Parameters of <code>where</code> condition are linked to method parameters with the syntax ${&lt;name of parameter&gt;}</p>
 * 
 * <h2>Return query result</h2>
 * 
 * <p>
 * There are many return type allowed for method which define a query:
 * </p>
 * <ul>
 * 		<li>a DAO's associated bean instance</li>
 * 		<li>list of associated bean</li>
 * 		<li>set of associated bean</li>
 * 		<li>Cursor: it is possible to wrap cursor with the cursor wrapper generated for bean associated to DAO. 
 * 				For example, given a <code>Person</code> and <code>PersonDAO</code>, will be generated <code>BindPersonCursor</code></li>
 *      <li>It is possible to set return type as Void and define a <code>OnReadBeanListener</code> which a method <code>void onRead(E bean, int row, int rowCount)</code> 
 *      allow to manage each row of result with only one bean (reused) instance.</li>
 *      <li>It is possible to set return type as Void and define a <code>OnReadCursorListener</code> which a method <code>void onRead(Cursor cursor)</code> 
 *      allows to manage resultset iteration with a cursor.</li>
 * </ul>
 * 
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 * @since 05/mag/2016
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BindSqlSelect {

	/**
	 * if true, set distinct clause
	 * 
	 * @return distinct clause
	 */
	boolean distinct() default false;

	/**
	 * properties to include into SELECT command
	 * 
	 * @return property's names to include
	 */
	String[] value() default {};

	/**
	 * properties to exclude from SELECT statement.
	 * 
	 * @return property's names to exclude
	 */
	String[] excludedFields() default {};

	/**
	 * where condition
	 * 
	 * @return where condition
	 */
	String where() default "1=1";

	/**
	 * having statement
	 * 
	 * @return having statement
	 */
	String having() default "";

	/**
	 * having statement
	 * 
	 * @return groupBy statement
	 * 
	 */
	String groupBy() default "";

	/**
	 * order statement
	 * 
	 * @return order statement
	 */
	String orderBy() default "";

}
