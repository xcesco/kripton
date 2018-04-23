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
package sqlite.feature.generichierarchy;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

// TODO: Auto-generated Javadoc
/**
 * The Interface BaseDAO.
 *
 * @param <E> the element type
 */
public interface BaseDAO<E> {	
	
	/**
	 * Select by id.
	 *
	 * @param bean the bean
	 * @return the list
	 */
	@BindSqlSelect(where = "id=${work.id}")
	List<E> selectById(@BindSqlParam("work") E bean);

	/**
	 * Insert thread 1.
	 *
	 * @param bean the bean
	 */
	@BindSqlInsert
	public void insertThread1(E bean);

	/**
	 * Update.
	 *
	 * @param bean the bean
	 * @return true, if successful
	 */
	@BindSqlUpdate(where = "id=${work.id}")
	public boolean update(@BindSqlParam("work") E bean);

	/**
	 * Delete.
	 *
	 * @param bean the bean
	 * @return true, if successful
	 */
	@BindSqlDelete(where = "id=${work.id}")
	public boolean delete(@BindSqlParam("work") E bean);

}
