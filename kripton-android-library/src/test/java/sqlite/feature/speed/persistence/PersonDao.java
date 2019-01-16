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
package sqlite.feature.speed.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.feature.speed.model.Person;

// TODO: Auto-generated Javadoc
/**
 * The Interface PersonDao.
 */
@BindDao(Person.class)
public interface PersonDao {

	/**
	 * Select all.
	 *
	 * @return the list
	 */
	@BindSqlSelect
	List<Person> selectAll();
	
	/**
	 * Select by id.
	 *
	 * @param id the id
	 * @return the person
	 */
	@BindSqlSelect(where="id=${id}")
	Person selectById(long id);
	
	/**
	 * Insert.
	 *
	 * @param bean the bean
	 * @return the person
	 */
	@BindSqlInsert
	Person insert(Person bean);
	
	/**
	 * Update.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlUpdate(where="id=${bean.id}")
	long update(Person bean);
	
	/**
	 * Delete.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlDelete(where="id=${bean.id}")
	long delete(Person bean);
}
