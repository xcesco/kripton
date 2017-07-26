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
package sqlite.feat.paginatedResult;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlPageSize;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.PaginatedResult;

import sqlite.feat.paginatedResult.Person;

@BindDao(Person.class)
public interface PersonDAO {
	
	@BindSqlSelect(orderBy="name", pageSize=20)
	PaginatedResult<Person> selectPagedStatic1();
	
	@BindSqlSelect(orderBy="name")
	PaginatedResult<Person> selectPagedStatic2(@BindSqlPageSize int pageSize);
	
	@BindSqlInsert
	void insertOne(String name, String surname, String birthCity, Date birthDay);

	@BindSqlSelect(orderBy="name")
	List<Person> selectAll();
	
	@BindSqlSelect(where="name like ${nameTemp} || '%'")
	List<Person> selectOne(@BindSqlParam("nameTemp") String nameValue, @BindSqlDynamicWhere String where, @BindSqlDynamicOrderBy String orderBy);
	
	@BindSqlSelect(orderBy="name")
	void selectBeanListener(OnReadBeanListener<Person> beanListener, @BindSqlDynamicOrderBy String orderBy);
	
}