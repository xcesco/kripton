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
package sqlite.contentprovider.kripton35.persistence;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereArgs;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;

import java.util.Date;

import sqlite.contentprovider.kripton35.entities.Person;

@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface PersonDAO {

	@BindContentProviderEntry
	@BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.CONFLICT_FAIL, excludedFields="parentId")
	void insertOne(Person bean);
	
	@BindContentProviderEntry(path="${parentId}/children")
	@BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.CONFLICT_ABORT)
	void insertChild(Person bean);
//	
//	@BindContentProviderEntry(path="test1")
//	@BindSqlInsert
//	void insertTwo(String name, String surname, String birthCity, Date birthDay);
//	
	@BindContentProviderEntry(path="${id}")
	@BindSqlDelete(where="id = ${id}")
	void delete(long id, @BindSqlDynamicWhere String runtimeWhere, @BindSqlDynamicWhereArgs String[] args);
	
	@BindContentProviderEntry(path="level1/${id}/${parentId}")
	@BindSqlDelete(where="id = ${id} and birthDay=${parentId}")
	void delete(long id, long parentId);
	
//
//	@BindContentProviderEntry(path = "#")
//	@BindSqlUpdate(where = "id=${id}")
//	int updateWhereStaticAndDynamic(@BindSqlParam("birthCity") String dummy, long id, @BindSqlWhere String where);
//
//	@BindContentProviderEntry
//	@BindSqlSelect(where = "name like ${nameTemp} || '%'", groupBy = "id", having = "id=2", orderBy = "id")
//	List<Person> selectOne(@BindSqlParam("nameTemp") String nameValue, @BindSqlPageSize int pageSize, @BindSqlWhere(prepend = PrependType.OR) String where, @BindSqlOrderBy String orderBy);
//
//	@BindSqlSelect(orderBy = "name")
//	void selectBeanListener(OnReadBeanListener<Person> beanListener, @BindSqlOrderBy String orderBy);
//
//	@BindContentProviderEntry(path = "#")
//	@BindSqlDelete(where = "id = ${bean.id}")
//	void deleteBean(Person bean, @BindSqlWhere String where);

	// @BindSqlSelect(orderBy="typeName")
	// void selectCursorListener(OnReadCursorListener cursorListener);
}