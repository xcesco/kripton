/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
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
package sqlite.kripton58.list;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;

// TODO: Auto-generated Javadoc
/**
 * The Interface IntegerDao.
 */
@BindDao(IntegerBean.class)
public interface IntegerDao {
	
	/**
	 * Select one.
	 *
	 * @return the integer bean
	 */
	@BindSqlSelect()
	IntegerBean selectOne();	
	
	/**
	 * Select one.
	 *
	 * @param value the value
	 * @return the integer bean
	 */
	@BindSqlSelect(where = "value=${value}")
	IntegerBean selectOne(List<Integer> value);

	/**
	 * Select one.
	 *
	 * @param value the value
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "value=${value}")
	void selectOne(List<Integer> value, OnReadBeanListener<IntegerBean> listener);
	
	/**
	 * Select one.
	 *
	 * @param value the value
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "value=${value}")
	void selectOne(List<Integer> value, OnReadCursorListener listener);
	
	/**
	 * Select list.
	 *
	 * @param value the value
	 * @return the list
	 */
	@BindSqlSelect(where = "value=${value}")
	List<IntegerBean> selectList(List<Integer> value);
	
	/**
	 * Update one.
	 *
	 * @param value the value
	 * @param id the id
	 * @param paramValue the param value
	 * @return the long
	 */
	@BindSqlUpdate(where = "id=${id} and value=${paramValue}")
	long updateOne(List<Integer> value, long id, List<Integer> paramValue);
	
	/**
	 * Insert.
	 *
	 * @param id the id
	 * @param value the value
	 * @return the long
	 */
	@BindSqlInsert()
	long insert(long id, List<Integer> value);
	
	/**
	 * Insert.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlInsert()
	long insert(IntegerBean bean);
	
	/**
	 * Delete.
	 *
	 * @param paramValue the param value
	 * @return the long
	 */
	@BindSqlDelete(where="value=${paramValue}")
	long delete(List<Integer> paramValue);
	
}