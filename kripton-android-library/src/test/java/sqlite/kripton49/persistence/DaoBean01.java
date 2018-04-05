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
package sqlite.kripton49.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.kripton49.entities.Bean01Entity;

// TODO: Auto-generated Javadoc
/**
 * The Interface DaoBean01.
 */
@BindDao(Bean01Entity.class)
public interface DaoBean01 {
	
	/**
	 * Select one.
	 *
	 * @param id the id
	 * @return the bean 01 entity
	 */
	@BindSqlSelect(where="id=${id}")
	Bean01Entity selectOne(Long id);
	
	/**
	 * Select by id.
	 *
	 * @param id the id
	 * @return the list
	 */
	@BindSqlSelect(where="id=${id}")
	List<Bean01Entity> selectById(Long id);
	
	/**
	 * Update one.
	 *
	 * @param text the text
	 * @param id the id
	 * @return the long
	 */
	@BindSqlUpdate(where="id=${id}")
	long updateOne(String text, Long id);
	
	/**
	 * Delete one.
	 *
	 * @param id the id
	 * @return the long
	 */
	@BindSqlDelete(where="id=${id}")
	long deleteOne(Long id);
	
	/**
	 * Insert one.
	 *
	 * @param id the id
	 * @return the long
	 */
	@BindSqlInsert()
	long insertOne(Long id);
	
	/**
	 * Insert one.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlInsert
	long insertOne(Bean01Entity bean);
	
}
