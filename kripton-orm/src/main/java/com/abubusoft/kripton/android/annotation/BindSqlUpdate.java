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

import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;

/**
 * <p>
 * This annotation allows to update a bean into database. You can use bean as
 * the unique method parameter or method parameters like bean property, but you
 * can not use mixed case.
 * </p>
 * 
 * <h2>Attributes</h2>
 * <ul>
 * <li><strong>value</strong>: bean properties to include into UPDATE command.
 * To use only if method have only one parameter and its type is the same of
 * supported bean.</li>
 * <li><strong>excludedFields</strong>: properties to exclude into UPDATE
 * command. To use only if method have only one parameter and its type is the
 * same of supported bean.</li>
 * <li><strong>where</strong>: where condition. It is possible to specify a
 * parameter binded to method's parameter with the syntax ${<parameter
 * name>}.</li>
 * <li><strong>jql</strong>: allows specifying the entire query with JQL.</li>
 * </ul>
 * 
 * <h2>Usage</h2>
 * <p>
 * Suppose we want to persist bean Person defined as follow:
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
 * The associated DAO interface is
 * </p>
 * 
 * <pre>
 * &#64;BindDao(Person.class)
 * public interface PersonDAO {
 * 
 * 	&#64;BindSqlUpdate(where = "name=${name}")
 * 	void updateOne(String name, String surname, String birthCity, Date birthDay);
 * 
 * 	&#64;BindSqlUpdate
 * 	long updateTwo(String name, String surname, String birthCity, @BindSqlParam("birthDay") Date date);
 * 
 * 	&#64;BindSqlUpdate(where = "id=${bean.id}")
 * 	void updateThree(Person bean);
 * }
 * </pre>
 * <p>
 * There are two kind of UPDATE:
 * </p>
 * <ul>
 * <li><strong>Raw update</strong>: method parameters are directly used like
 * table column or query parameter: method's parameter that are not used as
 * query parameter are used to update column name with same name or alias
 * (with @BindSqlParam). If you specify a return type for methods (like method
 * updateTwo), it has to be of type int, long, Integer, Long. In this case, the
 * return value will be the updated rows count. @BindSqlParam annotation can be
 * used to specify a different column name associated to a specific method's
 * parameter.</li>
 * <li><strong>Managed bean update</strong>: method has only one managed bean as
 * parameter: method accepts only one managed bean class type parameter. It is
 * possible to use excludedFields attribute to avoid to update some fields, or
 * you can use includeFields attribute to include only specific fields. If you
 * specify a return type for methods, it has to be of type int, long, Integer,
 * Long and it will contains updated rows count.</li>
 * <p>
 * For interface PersonDAO, Kripton annotation processor will generate the
 * following Person DAO implementation:
 * </p>
 * 
 * <pre>
 * public class PersonDAOImpl extends AbstractDao implements PersonDAO {
 * 	public PersonDAOImpl(BindPersonDataSource dataSet) {
 * 		super(dataSet);
 * 	}
 * 
 * 	&#64;Override
 * 	public void updateOne(String name, String surname, String birthCity, Date birthDay) {
 * 		ContentValues contentValues = contentValues();
 * 		contentValues.clear();
 * 		if (surname != null) {
 * 			contentValues.put("surname", surname);
 * 		} else {
 * 			contentValues.putNull("surname");
 * 		}
 * 		if (birthCity != null) {
 * 			contentValues.put("birth_city", birthCity);
 * 		} else {
 * 			contentValues.putNull("birth_city");
 * 		}
 * 		if (birthDay != null) {
 * 			contentValues.put("birth_day", DateUtils.write(birthDay));
 * 		} else {
 * 			contentValues.putNull("birth_day");
 * 		}
 * 
 * 		String[] whereConditions = { (name == null ? null : name) };
 * 
 * 		Logger.info(StringUtils.formatSQL("UPDATE person SET surname='" + StringUtils.checkSize(contentValues.get("surname")) + "', birthCity='"
 * 				+ StringUtils.checkSize(contentValues.get("birth_city")) + "', birthDay='" + StringUtils.checkSize(contentValues.get("birth_day")) + "' WHERE name=%s"), (Object[]) whereConditions);
 * 		int result = database().update("person", contentValues, "name=?", whereConditions);
 * 	}
 * 
 * 	&#64;Override
 * 	public long updateTwo(String name, String surname, String birthCity, Date date) {
 * 		ContentValues contentValues = contentValues();
 * 		contentValues.clear();
 * 		if (name != null) {
 * 			contentValues.put("name", name);
 * 		} else {
 * 			contentValues.putNull("name");
 * 		}
 * 		if (surname != null) {
 * 			contentValues.put("surname", surname);
 * 		} else {
 * 			contentValues.putNull("surname");
 * 		}
 * 		if (birthCity != null) {
 * 			contentValues.put("birth_city", birthCity);
 * 		} else {
 * 			contentValues.putNull("birth_city");
 * 		}
 * 		if (date != null) {
 * 			contentValues.put("birth_day", DateUtils.write(date));
 * 		} else {
 * 			contentValues.putNull("birth_day");
 * 		}
 * 
 * 		String[] whereConditions = {};
 * 
 * 		Logger.info(
 * 				StringUtils.formatSQL("UPDATE person SET name='" + StringUtils.checkSize(contentValues.get("name")) + "', surname='" + StringUtils.checkSize(contentValues.get("surname"))
 * 						+ "', birthCity='" + StringUtils.checkSize(contentValues.get("birth_city")) + "', birthDay='" + StringUtils.checkSize(contentValues.get("birth_day")) + "' WHERE 1=1"),
 * 				(Object[]) whereConditions);
 * 		int result = database().update("person", contentValues, "1=1", whereConditions);
 * 		return result;
 * 	}
 * 
 * 	&#64;Override
 * 	public void updateThree(Person bean) {
 * 		ContentValues contentValues = contentValues();
 * 		contentValues.clear();
 * 
 * 		if (bean.name != null) {
 * 			contentValues.put("name", bean.name);
 * 		} else {
 * 			contentValues.putNull("name");
 * 		}
 * 
 * 		if (bean.surname != null) {
 * 			contentValues.put("surname", bean.surname);
 * 		} else {
 * 			contentValues.putNull("surname");
 * 		}
 * 
 * 		if (bean.birthCity != null) {
 * 			contentValues.put("birth_city", bean.birthCity);
 * 		} else {
 * 			contentValues.putNull("birth_city");
 * 		}
 * 
 * 		if (bean.birthDay != null) {
 * 			contentValues.put("birth_day", DateUtils.write(bean.birthDay));
 * 		} else {
 * 			contentValues.putNull("birth_day");
 * 		}
 * 
 * 		String[] whereConditions = { String.valueOf(bean.id) };
 * 
 * 		Logger.info(
 * 				StringUtils.formatSQL("UPDATE person SET name='" + StringUtils.checkSize(contentValues.get("name")) + "', surname='" + StringUtils.checkSize(contentValues.get("surname"))
 * 						+ "', birth_city='" + StringUtils.checkSize(contentValues.get("birth_city")) + "', birth_day='" + StringUtils.checkSize(contentValues.get("birth_day")) + "' WHERE id='%s'"),
 * 				(Object[]) whereConditions);
 * 		int result = database().update("person", contentValues, "id=?", whereConditions);
 * 	}
 * }
 * </pre>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BindSqlUpdate {

	/**
	 * <p>
	 * Type of UPDATE. Default value is NONE. See
	 * <a href="https://www.sqlite.org/lang_conflict.html">here</a> for more
	 * info.
	 * </p>
	 * 
	 * @return type of insert.
	 */
	ConflictAlgorithmType conflictAlgorithm() default ConflictAlgorithmType.NONE;

	/**
	 * properties to include into UPDATE command.
	 *
	 * @return property's names to include
	 */
	String[] fields() default {};

	/**
	 * properties to exclude from UPDATE command.
	 *
	 * @return property's names to exclude
	 */
	String[] excludedFields() default {};

	/**
	 * where conditions.
	 *
	 * @return where conditions
	 */
	String where() default "";

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
}
