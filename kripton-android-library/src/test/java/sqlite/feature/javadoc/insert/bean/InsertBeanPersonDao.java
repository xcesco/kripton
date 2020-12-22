/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
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
package sqlite.feature.javadoc.insert.bean;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;

import sqlite.feature.javadoc.Person;


/**
 * The Interface InsertBeanPersonDao.
 */
@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface InsertBeanPersonDao {

	/**
	 * insert BEAN with parameter.
	 *
	 * @param bean the bean
	 * @return the int
	 */
	@BindContentProviderEntry
	@BindSqlInsert
	int insertOneBean(Person bean);

	/**
	 * insert BEAN with parameter.
	 *
	 * @param bean the bean
	 * @return the int
	 */
	@BindContentProviderEntry(path = "name")
	@BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.REPLACE, fields = "personName")
	int insertOneBeanFieldName(Person bean);

	/**
	 * insert BEAN with parameter.
	 *
	 * @param bean the bean
	 * @return the int
	 */
	@BindContentProviderEntry(path = "surname")
	@BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.REPLACE, excludedFields = "personName")
	int insertOneBeanFieldSurname(Person bean);

	/**
	 * insert BEAN with INSERT-FROM-SELECT is no allowed.
	 *
	 * @param bean the bean
	 */
	@BindSqlInsert(jql = "INSERT OR REPLACE INTO Person (personName) SELECT personName FROM Person WHERE personName=${bean.personName}")
	void insertBeanFromSelect(Person bean);

}
