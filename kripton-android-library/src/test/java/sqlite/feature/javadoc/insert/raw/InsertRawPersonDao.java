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
package sqlite.feature.javadoc.insert.raw;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;

import sqlite.feature.javadoc.Person;

// TODO: Auto-generated Javadoc
/**
 * The Interface InsertRawPersonDao.
 */
@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface InsertRawPersonDao {

	/**
	 * insert BEAN with parameter.
	 *
	 * @param name the name
	 * @param personSurname the person surname
	 * @return the int
	 */
	@BindContentProviderEntry
	@BindSqlInsert()
	int insertOneRaw(@BindSqlParam("personName") String name, String personSurname);

	/**
	 * insert BEAN with parameter.
	 *
	 * @param name the name
	 * @return the int
	 */
	@BindContentProviderEntry(path = "name")
	@BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.REPLACE)
	int insertOneRawFieldName(@BindSqlParam("personName") String name);

	/**
	 * insert RAW.
	 *
	 * @param surnname the surnname
	 * @param name the name
	 * @return the int
	 */
	@BindContentProviderEntry(path = "surname")
	@BindSqlInsert(jql = "INSERT OR REPLACE INTO Person (personName, personSurname) VALUES (${personName}, ${personSurname})")
	int insertOne2RawFieldName(@BindSqlParam("personSurname") String surnname, @BindSqlParam("personName") String name);

	/**
	 * insert RAW with parameter. INSERT-FROM-SELECT is not allowed
	 *
	 * @param name the name
	 */
	@BindSqlInsert(jql = "INSERT OR REPLACE INTO Person (personName) SELECT personName FROM Person WHERE personName=${name}")
	void insertRawFromSelect(String name);

}
