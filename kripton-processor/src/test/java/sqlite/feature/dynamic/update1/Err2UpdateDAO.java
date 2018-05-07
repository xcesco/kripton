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
package sqlite.feature.dynamic.update1;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.feature.dynamic.Person;

// TODO: Auto-generated Javadoc
/**
 * The Interface Err2UpdateDAO.
 */
@BindDao(Person.class)
public interface Err2UpdateDAO {
	/*
	@BindSqlInsert
	void insertOne(String typeName, String surname, String birthCity, Date birthDay);

	@BindSqlSelect(where="typeName like ${nameTemp} || '%' ")
	List<Person> selectOne(@BindSqlParam("nameTemp") String nameValue);
	*/
	
	/**
	 * Update.
	 *
	 * @param bean the bean
	 */
	@BindSqlUpdate(excludedFields={"id", "typeName", "surname", "birthCity" ,"birthDay"})
	void update(Person bean);
	
//	@BindSqlSelect(orderBy="typeName")
//	void selectCursorListener(OnReadCursorListener cursorListener);
}