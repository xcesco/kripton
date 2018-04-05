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
package sqlite.kripton84;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

// TODO: Auto-generated Javadoc
/**
 * The Interface Bean84BDao.
 */
@BindDao(Bean84B.class)
public interface Bean84BDao {

	/**
	 * Select by id.
	 *
	 * @param param1 the param 1
	 * @return the bean 84 B
	 */
	@BindSqlSelect(where="id = ${id}")
	Bean84B selectById(@BindSqlParam("id") long param1);
	
	/**
	 * Select by bean.
	 *
	 * @param param1 the param 1
	 * @return the bean 84 B
	 */
	@BindSqlSelect(where="cast(columnBean as TEXT) = ${param1}")
	Bean84B selectByBean(Bean84B2 param1);
	
	/**
	 * Insert.
	 *
	 * @param bean the bean
	 * @return true, if successful
	 */
	@BindSqlInsert
	boolean insert(Bean84B bean);
	
	/**
	 * Update all.
	 *
	 * @param bean the bean
	 * @return true, if successful
	 */
	@BindSqlUpdate
	boolean updateAll(Bean84B bean);
	
	/**
	 * Delete all.
	 *
	 * @param bean the bean
	 * @return true, if successful
	 */
	@BindSqlDelete
	boolean deleteAll(Bean84B bean);
}
