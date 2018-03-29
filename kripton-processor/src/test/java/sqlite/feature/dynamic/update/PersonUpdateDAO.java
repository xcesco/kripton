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
package sqlite.feature.dynamic.update;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;

import sqlite.feature.dynamic.Person;

@BindDao(Person.class)
public interface PersonUpdateDAO {
	@BindSqlSelect(where="name like ${nameTemp} || '%' ")
	List<Person> selectOne(@BindSqlParam("nameTemp") String nameValue);
	
	@BindSqlDelete(where="id = ${nameValue}")
	void deleteRaw(String nameValue, @BindSqlDynamicWhere String where);
	
	@BindSqlDelete(where="id = ${bean.id}")
	void deleteBean(Person bean, @BindSqlDynamicWhere String where);	
	
	@BindSqlUpdate(where="id = ${nameValue}")
	void updateRaw(String name, String nameValue, @BindSqlDynamicWhere String where);
	
	@BindSqlUpdate(where="id = ${bean.id}")
	void updateBean(Person bean, @BindSqlDynamicWhere String where);
	
	
	@BindSqlSelect
	List<Person> selecAll();
	
	@BindSqlInsert(conflictAlgorithm=ConflictAlgorithmType.IGNORE)
	void insertOne(String name, String surname, String birthCity, Date birthDay);

}