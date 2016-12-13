/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
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

@BindDao(BeanBean.class)
public interface BeanDao {
	
	@BindSqlSelect()
	BeanBean selectOne();	
	
	@BindSqlSelect(where = "value=${value}")
	BeanBean selectOne(List<BeanBean> value);

	@BindSqlSelect(where = "value=${value}")
	void selectOne(List<BeanBean> value, OnReadBeanListener<BeanBean> listener);
	
	@BindSqlSelect(where = "value=${value}")
	void selectOne(List<BeanBean> value, OnReadCursorListener listener);
	
	@BindSqlSelect(where = "value=${value}")
	List<BeanBean> selectList(List<BeanInner> value);
	
	@BindSqlUpdate(where = "id=${id} and value=${paramValue}")
	boolean updateOne(List<BeanInner> value, long id, List<BeanInner> paramValue);
	
	@BindSqlInsert()
	long insert(long id, List<BeanInner> value);
	
	@BindSqlInsert()
	long insert(BeanBean bean);
	
	@BindSqlDelete(where="value=${paramValue}")
	long delete(List<BeanInner> paramValue);
	
}