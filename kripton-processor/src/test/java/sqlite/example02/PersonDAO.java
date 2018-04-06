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
package sqlite.example02;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;

// TODO: Auto-generated Javadoc
/**
 * The Interface PersonDAO.
 */
@BindDao(Person.class)
public interface PersonDAO {
	
	/**
	 * Update one.
	 *
	 * @param name the name
	 * @param surname the surname
	 * @param birthCity the birth city
	 * @param birthDay the birth day
	 */
	@BindSqlUpdate(where="typeName=${name}")
	void updateOne(String name, String surname, String birthCity, Date birthDay);

	/**
	 * Update two.
	 *
	 * @param name the name
	 * @param surname the surname
	 * @param birthCity the birth city
	 * @param date the date
	 * @return the long
	 */
	@BindSqlUpdate
	long updateTwo(String name, String surname, String birthCity, @BindSqlParam("birthDay") Date date);

	/**
	 * Update three exclude.
	 *
	 * @param bean the bean
	 */
	@BindSqlUpdate(excludedFields={"surname"},where="id=${bean.id} and typeName=${bean.name}")
	void updateThreeExclude(Person bean);
	
	/**
	 * Update three include.
	 *
	 * @param bean the bean
	 */
	@BindSqlUpdate(where="id=${bean.id} and typeName=${bean.name}")
	void updateThreeInclude(Person bean);
	
	/**
	 * Update three include OK.
	 *
	 * @param bean the bean
	 */
	@BindSqlUpdate(fields={"surname"},where="id=${bean.id} and typeName=${bean.name}")
	void updateThreeIncludeOK(Person bean);
	
	/**
	 * Select three include ERR.
	 *
	 * @param bean the bean
	 * @return the list
	 */
	@BindSqlSelect(fields={"typeName"},where="id=${bean.id} and typeName=${bean.name}")
	List<Person> selectThreeIncludeERR(Person bean);
	
	/**
	 * Update three include ERR.
	 *
	 * @param bean the bean
	 */
	@BindSqlUpdate(fields={"typeName"},where="id=${bean.id} and typeName=${bean.name}")
	void updateThreeIncludeERR(Person bean);
	
	/**
	 * Update three include ERR.
	 *
	 * @param name the name
	 * @param surname the surname
	 * @param nameValue the name value
	 */
	@BindSqlUpdate(where="surname=${surname} and typeName=${nameValue}")
	void updateThreeIncludeERR(String name, String surname, String nameValue);
	
	
	/**
	 * Insert one.
	 *
	 * @param name the name
	 * @param surname the surname
	 * @param birthCity the birth city
	 * @param birthDay the birth day
	 */
	@BindSqlInsert
	void insertOne(String name, String surname, String birthCity, Date birthDay);

	/**
	 * Insert two.
	 *
	 * @param name the name
	 * @param surname the surname
	 * @param birthCity the birth city
	 * @param date the date
	 * @return the long
	 */
	@BindSqlInsert
	long insertTwo(String name, String surname, String birthCity, @BindSqlParam("birthDay") Date date);

	/**
	 * Insert three.
	 *
	 * @param bean the bean
	 */
	@BindSqlInsert(excludedFields = { "typeName", "surname" })
	void insertThree(Person bean);

	/**
	 * Delete one.
	 *
	 * @param name the name
	 * @param temp the temp
	 */
	@BindSqlDelete(where = "typeName=${typeName} and surname=${surname}")
	void deleteOne(String name, @BindSqlParam("surname") String temp);

	/**
	 * Delete two.
	 *
	 * @param name the name
	 * @param temp the temp
	 * @return the long
	 */
	@BindSqlDelete(where = "typeName=${typeName} and surname=${surname}")
	long deleteTwo(String name, @BindSqlParam("surname") String temp);

	/**
	 * Delete three.
	 *
	 * @param bean the bean
	 */
	@BindSqlDelete(where = " id = ${bean.id} ")
	void deleteThree(Person bean);
	
	/**
	 * Select all.
	 *
	 * @return the list
	 */
	@BindSqlSelect(orderBy="typeName")
	List<Person> selectAll();
	
	/**
	 * Select all.
	 *
	 * @param name the name
	 * @return the sets the
	 */
	@BindSqlSelect(where="typeName like ${typeName} || '%' ", orderBy="typeName")
	Set<Person> selectAll(String name);
	
	/**
	 * Gets the students.
	 *
	 * @param typeName the type name
	 * @return the students
	 */
	// select a list of students with extended JQL
	@BindSqlSelect(jql="select * from person where typeName like ${typeName} || '%' ")
	List<Person> getStudents(String typeName);
	
	/**
	 * Select bean listener.
	 *
	 * @param beanListener the bean listener
	 */
	@BindSqlSelect(orderBy="typeName")
	void selectBeanListener(OnReadBeanListener<Person> beanListener);
	
	/**
	 * Select cursor listener.
	 *
	 * @param cursorListener the cursor listener
	 */
	@BindSqlSelect(orderBy="typeName")
	void selectCursorListener(OnReadCursorListener cursorListener);
	
}