/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.feature.contentprovider.kripton35.nolog;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereParams;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;

import sqlite.feature.contentprovider.kripton35.entities.Person;

// TODO: Auto-generated Javadoc
/**
 * The Interface Person2DAO.
 */
@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface Person2DAO {

	/**
	 * Insert bean.
	 *
	 * @param bean the bean
	 */
	@BindContentProviderEntry
	@BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.FAIL, excludedFields = "parentId")
	void insertBean(Person bean);

	/**
	 * Insert name.
	 *
	 * @param tempName the temp name
	 */
	@BindContentProviderEntry(path = "${name}")
	@BindSqlInsert
	void insertName(@BindSqlParam("name") String tempName);

	/**
	 * Delete raw.
	 *
	 * @param id the id
	 * @return the int
	 */
	@BindContentProviderEntry(path = "${id}")
	@BindSqlDelete(where = "id = ${id}")
	int deleteRaw(long id);

	/**
	 * Delete raw.
	 *
	 * @param id the id
	 * @param where the where
	 * @param args the args
	 * @return true, if successful
	 */
	@BindContentProviderEntry(path = "test0/${id}")
	@BindSqlDelete(where = "id = ${id}")
	boolean deleteRaw(long id, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String[] args);

	/**
	 * Delete bean.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindContentProviderEntry(path = "test1/${bean.id}")
	@BindSqlDelete(where = "id = ${bean.id}")
	long deleteBean(Person bean);

	/**
	 * Update raw.
	 *
	 * @param name the name
	 * @param id the id
	 * @return the int
	 */
	@BindContentProviderEntry(path = "${id}")
	@BindSqlUpdate(where = "id=${id}")
	int updateRaw(String name, long id);

	/**
	 * Update raw.
	 *
	 * @param name the name
	 * @param id the id
	 * @param where the where
	 * @return the int
	 */
	@BindContentProviderEntry(path = "test1/${id}")
	@BindSqlUpdate(where = "id=${id}")
	int updateRaw(String name, long id, @BindSqlDynamicWhere String where);

	/**
	 * Update raw.
	 *
	 * @param name the name
	 * @param id the id
	 * @param where the where
	 * @param args the args
	 * @return the int
	 */
	@BindContentProviderEntry(path = "test2/${id}")
	@BindSqlUpdate(where = "id=${id}")
	int updateRaw(String name, long id, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String[] args);

	/**
	 * Update bean.
	 *
	 * @param person the person
	 * @return the int
	 */
	@BindContentProviderEntry(path = "test3/${person.id}")
	@BindSqlUpdate(where = "id=${person.id}")
	int updateBean(Person person);

	/**
	 * Select one.
	 *
	 * @param nameValue the name value
	 * @param orderBy the order by
	 * @return the list
	 */
	@BindContentProviderEntry(path = "${nameTemp}/test0")
	@BindSqlSelect(where = "name like ${nameTemp} || '%'", groupBy = "id", having = "id=2", orderBy = "id")
	List<Person> selectOne(@BindSqlParam("nameTemp") String nameValue, @BindSqlDynamicOrderBy String orderBy);

	/**
	 * Select all.
	 *
	 * @param where the where
	 * @param args the args
	 * @param order the order
	 * @return the list
	 */
	@BindContentProviderEntry
	@BindSqlSelect(orderBy = "name asc")
	List<Person> selectAll(@BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String[] args, @BindSqlDynamicOrderBy String order);

	/**
	 * Select one.
	 *
	 * @param bean the bean
	 * @param orderBy the order by
	 * @return the list
	 */
	@BindContentProviderEntry(path = "${data.name}/test1")
	@BindSqlSelect(where = "name like ${data.name} || '%'")
	List<Person> selectOne(@BindSqlParam("data") Person bean, @BindSqlDynamicOrderBy String orderBy);

	/**
	 * Select bean.
	 *
	 * @return the list
	 */
	@BindContentProviderEntry(path = "test3")
	@BindSqlSelect
	List<Person> selectBean();

	/**
	 * Select cursor listener.
	 *
	 * @param cursorListener the cursor listener
	 * @param where the where
	 */
	@BindSqlSelect(orderBy = "name")
	void selectCursorListener(OnReadCursorListener cursorListener, @BindSqlDynamicWhere String where);
}
