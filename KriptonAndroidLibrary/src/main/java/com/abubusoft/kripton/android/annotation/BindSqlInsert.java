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

import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;

/**
 * Allow to insert a bean into database. You can use bean as input parameter or
 * method parameters like bean property, but you can not use mixed case.
 * 
 * <p>
 * For example suppose we want to persist bean <code>Person</code> defined as
 * follow:
 * 
 * <pre>
 * &#064;BindType
 * public class Person {
 * 	public long id;
 * 
 * 	public String name;
 * 
 * 	public String surname;
 * 
 * 	public String birthCity;
 * 
 * 	public Date birthDay;
 * }
 * </pre>
 * 
 * <p>
 * The associated DAO interface is
 * </p>
 * 
 * <pre>
 * &#064;BindDao(Person.class)
 * public interface PersonDAO {
 * }
 * </pre>
 * 
 * <h2>Case 1 - Method use a bean type parameter</h2>
 * 
 * <p>
 * It's possible define a INSERT query with annotation {@link BindSqlInsert}. It
 * is possibile to define query parameter simply using method parameter with
 * same name of the bean property.
 * </p>
 * 
 * <pre>
 * &#064;BindDao(Person.class)
 * public interface PersonDAO {
 * 
 * 	&#064;BindInsert
 * 	void insertOne(String name, String surname, String birthCity, Date birthDay);
 * 
 * 	&#064;BindInsert
 * 	long insertTwo(String name, String surname, String birthCity, Date birthDay);
 * }
 * </pre>
 * 
 * <p>
 * Each method parameter will be use like input parameter for query. The name of
 * parameters will be used to map field bean and then the column name of the
 * associated table. If you specify a return type for methods (like method
 * <code>insertTwo</code>), it has to be of type
 * <code>int, long, Integer, Long</code>. In this case, the return value will be
 * the id value of just inserted row.
 * 
 * <h2>Case 2 - method use its parameters like bean properties</h2>
 * 
 * <p>
 * The other way to define an INSERT query is using a bean as input parameter:
 * 
 * <pre>
 * &#064;BindDao(Person.class)
 * public interface PersonDAO
 * {
 * 
 *  &#064;BindInsert(excludedFields={"name", "surname"})
 *  void insertThree(Person bean);
 * }
 * </pre>
 * 
 * <p>
 * You can use attribute <b>value</b> to define which property insert into query
 * or you can use attribute <b>excludedFields</b> to avoid to insert some
 * fields, <b>but you can not use both attributes in the same method
 * definition</b>. Values of this attribute will be used like bean property
 * name. At the end of the insert, bean will have id value set to last row id
 * used to insert bean into table. If you specify a return type for methods, it
 * has to be of type <code>int, long, Integer, Long</code> and it will contains
 * same value like row id.
 * 
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BindSqlInsert {

	/**
	 * <p>
	 * Type of INSERT. Default value is NONE. See
	 * <a href="https://www.sqlite.org/lang_conflict.html">here</a> for more
	 * info.
	 * </p>
	 * 
	 * @return type of insert.
	 */
	ConflictAlgorithmType conflictAlgorithm() default ConflictAlgorithmType.NONE;

	/**
	 * <p>
	 * bean properties to include into INSERT command. <b>To use only if method
	 * have only one parameter and its type is the same of DAO's supported bean</b>.
	 * </p>
	 * 
	 * @return property's names to include
	 */
	String[] fields() default {};

	/**
	 * <p>
	 * Allow to include primary key into INSERT command. <b>To use only if
	 * method have only one parameter and its type is the same of DAO's supported
	 * bean</b>.
	 * </p>
	 * 
	 * @return true if you need to include primary key in INSERT COMMAND
	 */
	boolean includePrimaryKey() default false;

	/**
	 * <p>
	 * properties to exclude into INSERT command. <b>To use only if method have
	 * only one parameter and its type is the same of DAO's supported bean</b>.
	 * </p>
	 * 
	 * @return property's names to exclude
	 */
	String[] excludedFields() default {};
	
	/**
	 * 
	 * <p>
	 * JQL value. With this attribute, it is possibile to specify directly the JQL code. JQL means that you can write SQL using field's names and class name indeed
	 * of column and table names. Moreover, it is possibile to specify where to use the dynamic parts of query through dynamic statements like DYNAMIC_WHERE, DYNAMIC_ORDER_BY, DYNAMIC_PAGE_SIZE, DYNAMIC_PAGE_OFFSET, encapsulated
	 * in <code>#{ <dynamic-part-name> }</code>
	 * </p>
	 * 
	 * <p>For example, for a <code>select</code> statement, you can write:</p>
	 * 
	 * <pre>
	 * SELECT * FROM media WHERE mediaId IN (SELECT mediaId FROM fav WHERE #{DYNAMIC_WHERE}) ORDER BY indx DESC LIMIT 0, 100 
	 * </pre>
	 * 
	 * <strong>If you use this attribute, no other attributes can be defined for the annotation</strong>.
	 * 
	 * @return
	 * 	JQL code specified by user
	 */
	String jql() default "";

}
