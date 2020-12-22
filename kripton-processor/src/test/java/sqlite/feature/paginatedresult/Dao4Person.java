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
package sqlite.feature.paginatedresult;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlPageSize;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.PagedResultImpl;

import sqlite.feature.paginatedresult.model.Person;


/**
 * The Interface Dao4Person.
 */
@BindDao(Person.class)
public interface Dao4Person {

	/**
	 * Select.
	 *
	 * @param value the value
	 * @param pageSize the page size
	 * @return the paginated result
	 */
	@BindSqlSelect(where = "id>${value}", orderBy = "name")
	PagedResultImpl<Person> select(long value, @BindSqlPageSize int pageSize);

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
	 * Select all.
	 *
	 * @return the list
	 */
	@BindSqlSelect(orderBy = "name")
	List<Person> selectAll();

	/**
	 * Delete all.
	 *
	 * @return the int
	 */
	@BindSqlDelete
	int deleteAll();
}