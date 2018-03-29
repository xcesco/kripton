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
import com.abubusoft.kripton.android.orm.OnReadBeanListener;
import com.abubusoft.kripton.android.orm.OnReadCursorListener;

@BindDao(Person.class)
public interface PersonDAO {
	
	@BindSqlUpdate(where="typeName=${name}")
	void updateOne(String name, String surname, String birthCity, Date birthDay);

	@BindSqlUpdate
	long updateTwo(String name, String surname, String birthCity, @BindSqlParam("birthDay") Date date);

	@BindSqlUpdate(excludedFields={"surname"},where="id=${bean.id} and typeName=${bean.name}")
	void updateThreeExclude(Person bean);
	
	@BindSqlUpdate(where="id=${bean.id} and typeName=${bean.name}")
	void updateThreeInclude(Person bean);
	
	@BindSqlUpdate(fields={"surname"},where="id=${bean.id} and typeName=${bean.name}")
	void updateThreeIncludeOK(Person bean);
	
	@BindSqlSelect(fields={"typeName"},where="id=${bean.id} and typeName=${bean.name}")
	List<Person> selectThreeIncludeERR(Person bean);
	
	@BindSqlUpdate(fields={"typeName"},where="id=${bean.id} and typeName=${bean.name}")
	void updateThreeIncludeERR(Person bean);
	
	@BindSqlUpdate(where="surname=${surname} and typeName=${nameValue}")
	void updateThreeIncludeERR(String name, String surname, String nameValue);
	
	
	@BindSqlInsert
	void insertOne(String name, String surname, String birthCity, Date birthDay);

	@BindSqlInsert
	long insertTwo(String name, String surname, String birthCity, @BindSqlParam("birthDay") Date date);

	@BindSqlInsert(excludedFields = { "typeName", "surname" })
	void insertThree(Person bean);

	@BindSqlDelete(where = "typeName=${typeName} and surname=${surname}")
	void deleteOne(String name, @BindSqlParam("surname") String temp);

	@BindSqlDelete(where = "typeName=${typeName} and surname=${surname}")
	long deleteTwo(String name, @BindSqlParam("surname") String temp);

	@BindSqlDelete(where = " id = ${bean.id} ")
	void deleteThree(Person bean);
	
	@BindSqlSelect(orderBy="typeName")
	List<Person> selectAll();
	
	@BindSqlSelect(where="typeName like ${typeName} || '%' ", orderBy="typeName")
	Set<Person> selectAll(String name);
	
	// select a list of students with extended JQL
	@BindSqlSelect(jql="select * from person where typeName like ${typeName} || '%' ")
	List<Person> getStudents(String typeName);
	
	@BindSqlSelect(orderBy="typeName")
	void selectBeanListener(OnReadBeanListener<Person> beanListener);
	
	@BindSqlSelect(orderBy="typeName")
	void selectCursorListener(OnReadCursorListener cursorListener);
	
}