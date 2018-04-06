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
 * The Interface LongDao.
 */
@BindDao(LongBean.class)
public interface LongDao {
	
	/**
	 * Select one.
	 *
	 * @return the long bean
	 */
	@BindSqlSelect()
	LongBean selectOne();

	/**
	 * Select one.
	 *
	 * @param value the value
	 * @return the long bean
	 */
	@BindSqlSelect(where = "CAST(value AS TEXT)=${value}")
	LongBean selectOne(List<Long> value);

	/**
	 * Select one.
	 *
	 * @param value the value
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "CAST(value AS TEXT)=${value}")
	void selectOne(List<Long> value, OnReadBeanListener<LongBean> listener);

	/**
	 * Select one.
	 *
	 * @param value the value
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "CAST(value AS TEXT)=${value}")
	void selectOne(List<Long> value, OnReadCursorListener listener);

	/**
	 * Select list.
	 *
	 * @param value the value
	 * @return the list
	 */
	@BindSqlSelect(where = "CAST(value AS TEXT)=${value}")
	List<LongBean> selectList(List<Long> value);

	/**
	 * Update one.
	 *
	 * @param value the value
	 * @param id the id
	 * @param paramValue the param value
	 * @return the long
	 */
	@BindSqlUpdate(where = "id=${id} and CAST(value AS TEXT)=${paramValue}")
	long updateOne(List<Long> value, long id, List<Long> paramValue);

	/**
	 * Insert.
	 *
	 * @param id the id
	 * @param value the value
	 * @return the long
	 */
	@BindSqlInsert()
	long insert(long id, List<Long> value);

	/**
	 * Insert.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlInsert()
	long insert(LongBean bean);

	/**
	 * Delete.
	 *
	 * @param paramValue the param value
	 * @return the long
	 */
	@BindSqlDelete(where = "CAST(value AS TEXT)=${paramValue}")
	long delete(List<Long> paramValue);

}