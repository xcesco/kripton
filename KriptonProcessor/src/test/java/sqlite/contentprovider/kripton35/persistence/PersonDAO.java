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
import com.abubusoft.kripton.android.annotation.BindSqlDynamicOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere.PrependType;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereArgs;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlPageSize;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;

import java.util.Date;
import java.util.List;

import sqlite.contentprovider.kripton35.entities.Person;

@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface PersonDAO {

	@BindContentProviderEntry
	@BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.CONFLICT_FAIL)
	void insertBean(Person bean);
	
	@BindContentProviderEntry(path="${parentId}/children")
	@BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.CONFLICT_ABORT)
	void insertBeanChild(Person bean);
	
//	@BindContentProviderEntry(path="test1")
//	@BindSqlInsert
//	void insertTwo(String name, String surname, String birthCity, Date birthDay);
//
//	@BindContentProviderEntry(path="${id}")
//	@BindSqlDelete(where="id = ${id}")
//	void delete(long id, @BindSqlDynamicWhere String runtimeWhere, @BindSqlDynamicWhereArgs String[] args);
//	
	@BindContentProviderEntry(path="${id}")
	@BindSqlDelete(where="id = ${id}")
	void delete(long id);
	
//	@BindContentProviderEntry(path="${id}")
//	@BindSqlDelete(where="id = ${id}")
//	void update(Person bean);

	@BindContentProviderEntry(path = "${id}")
	@BindSqlUpdate(where = "id=${id}")
	int updateName(String name, long id, @BindSqlDynamicWhere String where);

//	@BindContentProviderEntry(path="${nameTemp}/test")
//	@BindSqlSelect(where = "name like ${nameTemp} || '%'", groupBy = "id", having = "id=2", orderBy = "id")
//	List<Person> selectOne(@BindSqlParam("nameTemp") String nameValue, @BindSqlPageSize int pageSize, @BindSqlDynamicOrderBy String orderBy);
//
	@BindContentProviderEntry
	@BindSqlSelect(orderBy="name asc")
	List<Person> selectAll(@BindSqlDynamicWhere String where, @BindSqlDynamicWhereArgs String[] args, @BindSqlDynamicOrderBy String order);
//
//	@BindContentProviderEntry(path = "#")
//	@BindSqlDelete(where = "id = ${bean.id}")
//	void deleteBean(Person bean, @BindSqlWhere String where);

	// @BindSqlSelect(orderBy="typeName")
	// void selectCursorListener(OnReadCursorListener cursorListener);
}