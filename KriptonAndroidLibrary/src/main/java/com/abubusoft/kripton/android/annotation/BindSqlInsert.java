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
 * Allow to insert a bean into database. You can use bean as input parameter or method parameters like bean property, but you can not use mixed case.
 * 
 * <p>
 * For example suppose we want to persist bean <code>Person</code> defined as follow:
 * 
 * <pre>
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
 * It's possible define a INSERT query with annotation {@link BindSqlInsert}. It is possibile to define query parameter simply using method parameter with same name of the bean property.
 * </p>
 * 
 * <pre>
 * &#064;BindDao(Person.class)
 * public interface PersonDAO {
 * 
 *  &#064;BindInsert
 *  void insertOne(String name, String surname, String birthCity, Date birthDay);
 * 
 *  &#064;BindInsert
 *  long insertTwo(String name, String surname, String birthCity, Date birthDay);
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
 *  &#064;BindInsert(excludedFields={"name", "surname"}
 *  void insert(Person bean);
 * }
 * </pre>
 * 
 * <p>
 * You can use attribute <b>value</b> to define which property insert into query or you can use attribute <b>excludedFields</b> to avoid to insert some fields,
 * <b>but you can not use both attributes in the same method definition</b>. Values of this attribute will be used like bean property name. At the end of the insert, bean will have id value set to last row id used to insert bean into
 * table. If you specify a return type for methods, it has to be of type <code>int, long, Integer, Long</code> and it will contains same value like row id.
 * 
 * 
 * @author xcesco
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BindSqlInsert {

	/**
	 * <p>
	 * bean properties to include into INSERT command. <b>To use only if method have only one parameter and its type is the same of supported bean</b>.
	 * 
	 * @return property's names to include
	 */
	String[] value() default {};

	/**
	 * properties to exclude into INSERT command. <b>To use only if method have only one parameter and its type is the same of supported bean</b>.
	 * 
	 * @return property's names to exclude
	 */
	String[] excludedFields() default {};

}
