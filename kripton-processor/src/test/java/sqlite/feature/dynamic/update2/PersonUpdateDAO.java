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
package sqlite.feature.dynamic.update2;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.feature.dynamic.Person;

/**
 * The Interface PersonUpdateDAO.
 */
@BindDao(Person.class)
public interface PersonUpdateDAO {
/*	
	@BindSqlSelect(where="name like ${nameTemp} || '%' ")
	List<Person> selectOne(@BindSqlParam("nameTemp") String nameValue);
	*/
	/*
	@BindSqlUpdate(jql="UPDATE person SET name=${name} WHERE id = ${id} #{DYNAMIC_WHERE}")
	void updateRaw(String name, String nameValue, long id, @BindSqlDynamicWhere String where);
	*/
	@BindSqlUpdate(jql="UPDATE person SET name=${bean.name}, surname=${bean.surname}, birthCity=${bean.birthCity}, birthDay=${bean.birthDay} WHERE id = ${bean.id} #{DYNAMIC_WHERE}")
	void updateBean(Person bean, @BindSqlDynamicWhere String where);
	/*
	
	@BindSqlSelect
	List<Person> selecAll();
	
	@BindSqlInsert(conflictAlgorithm=ConflictAlgorithmType.IGNORE)
	void insertOne(String name, String surname, String birthCity, Date birthDay);
*/
}