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
package sqlite.feat.includeFields;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.feat.includeFields.Person;

// TODO: Auto-generated Javadoc
/**
 * The Interface PersonDAO.
 */
@BindDao(Person.class)
public interface PersonDAO {
	
	/**
	 * Select include one.
	 *
	 * @param bean the bean
	 * @return the list
	 */
	@BindSqlSelect(fields={"name", "id"}, where="typeName=${bean.name}",orderBy="name")
	List<Person> selectIncludeOne(Person bean);
	
	/**
	 * Select exclude one.
	 *
	 * @return the list
	 */
	@BindSqlSelect(excludedFields={"name", "id"}, orderBy="name")
	List<Person> selectExcludeOne();
	
	/**
	 * Insert include one.
	 *
	 * @param bean the bean
	 */
	@BindSqlInsert(fields={"name", "id"})
	void insertIncludeOne(Person bean);
	
	/**
	 * Insert exclude one.
	 *
	 * @param bean the bean
	 */
	@BindSqlInsert(excludedFields={"name", "id"})
	void insertExcludeOne(Person bean);	
	
	/**
	 * Update include one.
	 *
	 * @param bean the bean
	 */
	@BindSqlUpdate(fields={"name", "id"})
	void updateIncludeOne(Person bean);
	
	/**
	 * Update exclude one.
	 *
	 * @param bean the bean
	 */
	@BindSqlUpdate(excludedFields={"name", "id"})
	void updateExcludeOne(Person bean);
	
	/**
	 * Delete include one.
	 *
	 * @param bean the bean
	 */
	@BindSqlDelete
	void deleteIncludeOne(Person bean);
	
	/**
	 * Delete exclude one.
	 *
	 * @param bean the bean
	 */
	@BindSqlDelete
	void deleteExcludeOne(Person bean);
	
	

}