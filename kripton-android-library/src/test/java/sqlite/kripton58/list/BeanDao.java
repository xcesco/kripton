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

import sqlite.kripton58.BeanInner;

// TODO: Auto-generated Javadoc
/**
 * The Interface BeanDao.
 */
@BindDao(BeanBean.class)
public interface BeanDao {
	
	/**
	 * Select one.
	 *
	 * @return the bean bean
	 */
	@BindSqlSelect()
	BeanBean selectOne();	
	
	/**
	 * Select one.
	 *
	 * @param value the value
	 * @return the bean bean
	 */
	@BindSqlSelect(where = "value=${value}")
	BeanBean selectOne(List<BeanBean> value);

	/**
	 * Select one.
	 *
	 * @param value the value
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "value=${value}")
	void selectOne(List<BeanBean> value, OnReadBeanListener<BeanBean> listener);
	
	/**
	 * Select one.
	 *
	 * @param value the value
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "value=${value}")
	void selectOne(List<BeanBean> value, OnReadCursorListener listener);
	
	/**
	 * Select list.
	 *
	 * @param value the value
	 * @return the list
	 */
	@BindSqlSelect(where = "value=${value}")
	List<BeanBean> selectList(List<BeanInner> value);
	
	/**
	 * Update one.
	 *
	 * @param value the value
	 * @param id the id
	 * @param paramValue the param value
	 * @return true, if successful
	 */
	@BindSqlUpdate(where = "id=${id} and value=${paramValue}")
	boolean updateOne(List<BeanInner> value, long id, List<BeanInner> paramValue);
	
	/**
	 * Insert.
	 *
	 * @param id the id
	 * @param value the value
	 * @return the long
	 */
	@BindSqlInsert()
	long insert(long id, List<BeanInner> value);
	
	/**
	 * Insert.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlInsert()
	long insert(BeanBean bean);
	
	/**
	 * Delete.
	 *
	 * @param paramValue the param value
	 * @return the long
	 */
	@BindSqlDelete(where="value=${paramValue}")
	long delete(List<BeanInner> paramValue);
	
}