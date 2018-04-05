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
 * The Interface StringDao.
 */
@BindDao(StringBean.class)
public interface StringDao {
	
	/**
	 * Select one.
	 *
	 * @return the string bean
	 */
	@BindSqlSelect()
	StringBean selectOne();	
	
	/**
	 * Select one.
	 *
	 * @param value the value
	 * @return the string bean
	 */
	@BindSqlSelect(where = "value=${value}")
	StringBean selectOne(List<String> value);

	/**
	 * Select one.
	 *
	 * @param value the value
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "value=${value}")
	void selectOne(List<String> value, OnReadBeanListener<StringBean> listener);
	
	/**
	 * Select one.
	 *
	 * @param value the value
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "value=${value}")
	void selectOne(List<String> value, OnReadCursorListener listener);
	
	/**
	 * Select list.
	 *
	 * @param value the value
	 * @return the list
	 */
	@BindSqlSelect(where = "value=${value}")
	List<StringBean> selectList(List<String> value);
	
	/**
	 * Update one.
	 *
	 * @param value the value
	 * @param id the id
	 * @param paramValue the param value
	 * @return true, if successful
	 */
	@BindSqlUpdate(where = "id=${id} and value=${paramValue}")
	boolean updateOne(List<String> value, long id, List<String> paramValue);
	
	/**
	 * Insert.
	 *
	 * @param id the id
	 * @param value the value
	 * @return the long
	 */
	@BindSqlInsert()
	long insert(long id, List<String> value);
	
	/**
	 * Insert.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlInsert()
	long insert(StringBean bean);
	
	/**
	 * Delete.
	 *
	 * @param paramValue the param value
	 * @return the long
	 */
	@BindSqlDelete(where="value=${paramValue}")
	long delete(List<String> paramValue);
	
}