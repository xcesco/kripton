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
 * Allows to query a database table. When you define the query through
 * interface's method you can define query parameter by a DAO's associated bean
 * instance, or directly with fields.
 * </p>
 * 
 * <h2>Query parameters</h2>
 * <p>
 * Almost all parameters used in method can be used as query parameter.
 * </p>
 * 
 * <pre>
 * &#064;BindSqlSelect(where = "name=${name} and surname=${surname}")
 * Person selectOne(String name, BindSqlParam("surname") String temp);
 * </pre>
 * 
 * <p>
 * Parameters of <code>where</code> condition are linked to method parameters
 * with the syntax ${&lt;name of parameter&gt;}
 * </p>
 * 
 * <h2>Return query result</h2>
 * 
 * <p>
 * There are many return type allowed for method which define a query:
 * </p>
 * <ul>
 * <li>a DAO's associated bean instance</li>
 * <li>list of associated bean</li>
 * <li>set of associated bean</li>
 * <li>Cursor: it is possible to wrap cursor with the cursor wrapper generated
 * for bean associated to DAO. For example, given a <code>Person</code> and
 * <code>PersonDAO</code>, will be generated <code>BindPersonCursor</code></li>
 * <li>It is possible to set return type as Void and define a
 * <code>OnReadBeanListener</code> which a method
 * <code>void onRead(E bean, int row, int rowCount)</code> allow to manage each
 * row of result with only one bean (reused) instance.</li>
 * <li>It is possible to set return type as Void and define a
 * <code>OnReadCursorListener</code> which a method
 * <code>void onRead(Cursor cursor)</code> allows to manage resultset iteration
 * with a cursor.</li>
 * </ul>
 * 
 * <h2>Attributes</h2>
 * <ul>
 * <li><strong>distinct</strong>: if true insert distinct clause in SQL
 * statement.</li>
 * <li><strong>excludedFields</strong>: properties to exclude from the SELECT
 * statement.</li>
 * <li><strong>fields</strong>: properties to include into the SELECT
 * statement.</li>
 * <li><strong>groupBy</strong>: GROUP BY statement. It is not necessary to
 * include GROUP BY words in statements, they are added automatically.</li>
 * <li><strong>having</strong>: HAVING statement. It is not necessary to include
 * HAVING words in statements, they are added automatically.</li>
 * <li><strong>jql</strong>: allows specifying the entire query with JQL.</li>
 * <li><strong>orderBy</strong>: ORDER BY statement. It is not necessary to
 * include ORDER BY words in statements, they are added automatically.</li>
 * <li><strong>pageSize</strong>: If the method returns a paginated result, this
 * attribute allows to specify the size of the page.</li>
 * <li><strong>where</strong>: WHERE statement. It is not necessary to include
 * WHERE words in statements, they have added automatically.</li>
 * </ul>
 * 
 * <h2>Query parameters</h2>
 * <p>
 * Almost all parameters used in method can be used as query parameter.
 * </p>
 * 
 * <pre>
 * &#64;BindSqlSelect(where = "name=${name} and surname=${surname}")
 * Person selectOne(String name, @BindSqlParam("surname") String temp);
 * </pre>
 * <p>
 * Parameters of where condition are linked to method parameters with the syntax
 * ${name of parameter}
 * </p>
 * 
 * <h2>Return query result</h2>
 * <p>
 * There are many return type allowed for method which define a query:
 * </p>
 * 
 * <ul>
 * <li>a DAO's associated bean instance</li>
 * <li>list of associated bean</li>
 * <li>set of associated bean
 * <li>Cursor: it is possible to wrap cursor with the cursor wrapper generated
 * for bean associated to DAO. For example, given a Person and PersonDAO, will
 * be generated BindPersonCursor.
 * <li>It is possible to set return type as Void and define a OnReadBeanListener
 * which a method void onRead(E bean, int row, int rowCount) allow to manage
 * each row of result with only one bean (reused) instance.
 * <li>It is possible to set return type as Void and define a
 * OnReadCursorListener which a method void onRead(Cursor cursor) allows to
 * manage resultset iteration with a cursor.
 * <li>LiveData</li>
 * <li>Paginated result</li>
 * </ul>
 * 
 * <h2>Example</h2>
 * <p>
 * Given a Java class definition:
 * </p>
 * 
 * <pre>
 * &#64;BindType
 * public class Person {
 * 	public long id;
 * 	public String name;
 * 	public String surname;
 * 	public String birthCity;
 * 	public Date birthDay;
 * }
 * </pre>
 * <p>
 * And a associated DAO definition:
 * </p>
 * 
 * <pre>
 * &#64;BindDao(Person.class)
 * public interface PersonDAO {
 * 	&#64;BindSqlSelect(orderBy = "name")
 * 	List&lt;Person&gt; selectAll();
 * 
 * 	&#64;BindSqlSelect(where = "name like ${name} || '%%' ", orderBy = "name")
 * 	Set&lt;Person&gt; selectAll(String name);
 * 
 * 	&#64;BindSqlSelect(orderBy = "name")
 * 	void selectBeanListener(OnReadBeanListener&lt;Person&gt; beanListener);
 * 
 * 	&#64;BindSqlSelect(orderBy = "name")
 * 	void selectCursorListener(OnReadCursorListener cursorListener);
 * }
 * </pre>
 * <p>
 * When Kripton annotation processor examine <code>BindDao</code> annotation, it
 * generates the following DAO implementations:
 * </p>
 * 
 * <pre>
 * public class PersonDAOImpl extends AbstractDao implements PersonDAO {
 * 	public PersonDAOImpl(BindPersonDataSource dataSet) {
 * 		super(dataSet);
 * 	}
 * 
 * 	&#64;Override
 * 	public List&lt;Person&gt; selectAll() {
 * 		// build where condition
 * 		String[] args = {};
 * 
 * 		Logger.info(
 * 				StringUtils.formatSQL(
 * 						"SELECT id, name, surname, birth_city, birth_day FROM person WHERE 1=1 ORDER BY name"),
 * 				(Object[]) args);
 * 		Cursor cursor = database().rawQuery(
 * 				"SELECT id, name, surname, birth_city, birth_day FROM person WHERE 1=1 ORDER BY name", args);
 * 		Logger.info("Rows found: %s", cursor.getCount());
 * 
 * 		LinkedList&lt;Person&gt; resultList = new LinkedList&lt;Person&gt;();
 * 		Person resultBean = null;
 * 
 * 		if (cursor.moveToFirst()) {
 * 
 * 			int index0 = cursor.getColumnIndex("id");
 * 			int index1 = cursor.getColumnIndex("name");
 * 			int index2 = cursor.getColumnIndex("surname");
 * 			int index3 = cursor.getColumnIndex("birth_city");
 * 			int index4 = cursor.getColumnIndex("birth_day");
 * 
 * 			do {
 * 				resultBean = new Person();
 * 
 * 				if (!cursor.isNull(index0)) {
 * 					resultBean.id = cursor.getLong(index0);
 * 				}
 * 				if (!cursor.isNull(index1)) {
 * 					resultBean.name = cursor.getString(index1);
 * 				}
 * 				if (!cursor.isNull(index2)) {
 * 					resultBean.surname = cursor.getString(index2);
 * 				}
 * 				if (!cursor.isNull(index3)) {
 * 					resultBean.birthCity = cursor.getString(index3);
 * 				}
 * 				if (!cursor.isNull(index4)) {
 * 					resultBean.birthDay = DateUtils.read(cursor.getString(index4));
 * 				}
 * 
 * 				resultList.add(resultBean);
 * 			} while (cursor.moveToNext());
 * 		}
 * 		cursor.close();
 * 
 * 		return resultList;
 * 	}
 * 
 * 	&#64;Override
 * 	public Set&lt;Person&gt; selectAll(String name) {
 * 		// build where condition
 * 		String[] args = { (name == null ? null : name) };
 * 
 * 		Logger.info(
 * 				StringUtils.formatSQL(
 * 						"SELECT id, name, surname, birth_city, birth_day FROM person WHERE name like '%s' || \'%%\' ORDER BY name"),
 * 				(Object[]) args);
 * 		Cursor cursor = database().rawQuery(
 * 				"SELECT id, name, surname, birth_city, birth_day FROM person WHERE name like ? || \'%%\' ORDER BY name",
 * 				args);
 * 		Logger.info("Rows found: %s", cursor.getCount());
 * 
 * 		HashSet&lt;Person&gt; resultList = new HashSet&lt;Person&gt;();
 * 		Person resultBean = null;
 * 
 * 		if (cursor.moveToFirst()) {
 * 
 * 			int index0 = cursor.getColumnIndex("id");
 * 			int index1 = cursor.getColumnIndex("name");
 * 			int index2 = cursor.getColumnIndex("surname");
 * 			int index3 = cursor.getColumnIndex("birth_city");
 * 			int index4 = cursor.getColumnIndex("birth_day");
 * 
 * 			do {
 * 				resultBean = new Person();
 * 
 * 				if (!cursor.isNull(index0)) {
 * 					resultBean.id = cursor.getLong(index0);
 * 				}
 * 				if (!cursor.isNull(index1)) {
 * 					resultBean.name = cursor.getString(index1);
 * 				}
 * 				if (!cursor.isNull(index2)) {
 * 					resultBean.surname = cursor.getString(index2);
 * 				}
 * 				if (!cursor.isNull(index3)) {
 * 					resultBean.birthCity = cursor.getString(index3);
 * 				}
 * 				if (!cursor.isNull(index4)) {
 * 					resultBean.birthDay = DateUtils.read(cursor.getString(index4));
 * 				}
 * 
 * 				resultList.add(resultBean);
 * 			} while (cursor.moveToNext());
 * 		}
 * 		cursor.close();
 * 
 * 		return resultList;
 * 	}
 * 
 * 	&#64;Override
 * 	public void selectBeanListener(OnReadBeanListener&lt;Person&gt; beanListener) {
 * 		// build where condition
 * 		String[] args = {};
 * 
 * 		Logger.info(
 * 				StringUtils.formatSQL(
 * 						"SELECT id, name, surname, birth_city, birth_day FROM person WHERE 1=1 ORDER BY name"),
 * 				(Object[]) args);
 * 		Cursor cursor = database().rawQuery(
 * 				"SELECT id, name, surname, birth_city, birth_day FROM person WHERE 1=1 ORDER BY name", args);
 * 		Logger.info("Rows found: %s", cursor.getCount());
 * 		Person resultBean = new Person();
 * 		try {
 * 			if (cursor.moveToFirst()) {
 * 
 * 				int index0 = cursor.getColumnIndex("id");
 * 				int index1 = cursor.getColumnIndex("name");
 * 				int index2 = cursor.getColumnIndex("surname");
 * 				int index3 = cursor.getColumnIndex("birth_city");
 * 				int index4 = cursor.getColumnIndex("birth_day");
 * 
 * 				int rowCount = cursor.getCount();
 * 				do {
 * 					// reset mapping
 * 					resultBean.id = 0L;
 * 					resultBean.name = null;
 * 					resultBean.surname = null;
 * 					resultBean.birthCity = null;
 * 					resultBean.birthDay = null;
 * 
 * 					// generate mapping
 * 					if (!cursor.isNull(index0)) {
 * 						resultBean.id = cursor.getLong(index0);
 * 					}
 * 					if (!cursor.isNull(index1)) {
 * 						resultBean.name = cursor.getString(index1);
 * 					}
 * 					if (!cursor.isNull(index2)) {
 * 						resultBean.surname = cursor.getString(index2);
 * 					}
 * 					if (!cursor.isNull(index3)) {
 * 						resultBean.birthCity = cursor.getString(index3);
 * 					}
 * 					if (!cursor.isNull(index4)) {
 * 						resultBean.birthDay = DateUtils.read(cursor.getString(index4));
 * 					}
 * 
 * 					beanListener.onRead(resultBean, cursor.getPosition(), rowCount);
 * 				} while (cursor.moveToNext());
 * 			}
 * 		} finally {
 * 			if (!cursor.isClosed()) {
 * 				cursor.close();
 * 			}
 * 		}
 * 	}
 * 
 * 	&#64;Override
 * 	public void selectCursorListener(OnReadCursorListener cursorListener) {
 * 		// build where condition
 * 		String[] args = {};
 * 
 * 		Logger.info(
 * 				StringUtils.formatSQL(
 * 						"SELECT id, name, surname, birth_city, birth_day FROM person WHERE 1=1 ORDER BY name"),
 * 				(Object[]) args);
 * 		Cursor cursor = database().rawQuery(
 * 				"SELECT id, name, surname, birth_city, birth_day FROM person WHERE 1=1 ORDER BY name", args);
 * 		Logger.info("Rows found: %s", cursor.getCount());
 * 
 * 		try {
 * 			if (cursor.moveToFirst()) {
 * 
 * 				do {
 * 					cursorListener.onRead(cursor);
 * 				} while (cursor.moveToNext());
 * 			}
 * 		} finally {
 * 			if (!cursor.isClosed()) {
 * 				cursor.close();
 * 			}
 * 		}
 * 	}
 * }
 * </pre>
 * <p>
 * So, the code to execute query selection:
 * </p>
 * 
 * <pre>
 * // open database
 * instance.openReadOnlyDatabase();
 * 
 * // select 1
 * Set&lt;Person&gt; list = instance.getPersonDAO().selectAll("name");
 * 
 * // select 2
 * instance.getPersonDAO().selectBeanListener(new OnReadBeanListener&lt;Person&gt;() {
 * 
 * 	&#64;Override
 * 	public void onRead(Person bean, int row, int rowCount) {
 * 		// work with
 * 
 * 	}
 * });
 * 
 * // select 3
 * instance.getPersonDAO().selectCursorListener(new OnReadCursorListener() {
 * 
 * 	&#64;Override
 * 	public void onRead(Cursor cursor) {
 * 		// work directly with cursor
 * 
 * 	}
 * });
 * 
 * // close database
 * instance.close();
 * </pre>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 * @since 05/mag/2016
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BindSqlSelect {

	/**
	 * if true, set distinct clause.
	 *
	 * @return distinct clause
	 */
	boolean distinct() default false;

	/**
	 * properties to include into SELECT command.
	 *
	 * @return property's names to include
	 */
	String[] fields() default {};

	/**
	 * properties to exclude from SELECT statement.
	 * 
	 * @return property's names to exclude
	 */
	String[] excludedFields() default {};

	/**
	 * where condition.
	 *
	 * @return where condition
	 */
	String where() default "";

	/**
	 * having statement.
	 *
	 * @return having statement
	 */
	String having() default "";

	/**
	 * having statement.
	 *
	 * @return groupBy statement
	 */
	String groupBy() default "";

	/**
	 * order statement.
	 *
	 * @return order statement
	 */
	String orderBy() default "";

	/**
	 * <p>
	 * Allow to define limit for query result. Default no limit is defined.
	 * </p>
	 *
	 * @return the int
	 */
	int pageSize() default 0;

	/**
	 * 
	 * <p>
	 * JQL value. With this attribute, it is possibile to specify directly the
	 * JQL code. JQL means that you can write SQL using field's names and class
	 * name indeed of column and table names. Moreover, it is possibile to
	 * specify where to use the dynamic parts of query through dynamic
	 * statements like DYNAMIC_WHERE, DYNAMIC_ORDER_BY, DYNAMIC_PAGE_SIZE,
	 * DYNAMIC_PAGE_OFFSET, encapsulated in <code>#{ dynamic-part-name }</code>
	 * </p>
	 * 
	 * <p>
	 * For example, for a <code>select</code> statement, you can write:
	 * </p>
	 * 
	 * <pre>
	 * SELECT * FROM media WHERE mediaId IN (SELECT mediaId FROM fav WHERE #{DYNAMIC_WHERE}) ORDER BY indx DESC LIMIT 0, 100
	 * </pre>
	 * 
	 * <strong>If you use this attribute, no other attributes can be defined for
	 * the annotation</strong>.
	 * 
	 * @return JQL code specified by user
	 */
	String jql() default "";

	/**
	 * <p>
	 * Used to specify which queries need to be invoked to fill fields that represent relation
	 * with other entities.
	 * </p>
	 * 
	 * @return set of selects used to fill fields defined as relation
	 * 
	 */
	BindSqlChildSelect[] childrenSelects() default {};

}
