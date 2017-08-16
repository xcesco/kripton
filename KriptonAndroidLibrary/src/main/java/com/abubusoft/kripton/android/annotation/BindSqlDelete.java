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
 * Allow to delete a bean from a database. You can use bean as input parameter or method parameters like bean property, but you can not use mixed case.
 * 
 * <p>
 * For example suppose we persist bean <code>Person</code> defined as follow:
 * 
 * <pre>
 * &#064;BindType
 * public class Person {
 *  public long id;
 *  
 *  public String name;
 *  
 *  public String surname;
 *  
 *  public String birthCity;
 *  
 *  public Date birthDay;
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
 * It's possible define a DELETE query with annotation {@link BindSqlDelete}. It is possibile to define query parameter simply using method parameter with same name of the bean property.
 * </p>
 * 
 * <pre>
 * &#064;BindDao(Person.class)
 * public interface PersonDAO {
 * 
 *  &#064;BindDelete(where="name=${name} and surname=${surname}")
 *  void deleteOne(String name, &#064;BindSqlParam("surname") temp);
 * 
 *  &#064;BindDelete(where="name=${name} and surname=${surname}")
 *  long deleteTwo(String name, &#064;BindSqlParam("surname") temp);
 * }
 * </pre>
 * 
 * <p>
 * Each method parameter will be use like input parameter for query. The name of parameters will be used to map field bean and then the column name of the associated table. If you specify a return type for methods (like method
 * <code>insertTwo</code>), it has to be of type <code>int, long, Integer, Long</code>. In this case, the return value will be the id value of just inserted row.
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
 *  &#064;BindDelete(where=" id = ${bean.id} ")
 *  void deleteThree(Person bean);
 * }
 * </pre>
 * 
 * <p>
 * You can use attribute <b>value</b> to define which property insert into query or you can use attribute <b>excludedFields</b> to avoid to insert some fields,
 * <b>but you can not use both attributes in the same method definition</b>. Values of this attribute will be used like bean property name. At the end of the insert, bean will have id value set to last row id used to insert bean into
 * table. If you specify a return type for methods, it has to be of type <code>int, long, Integer, Long</code> and it will contains same value like row id.
 * 
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BindSqlDelete {

	String where() default "1=1";
	
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
