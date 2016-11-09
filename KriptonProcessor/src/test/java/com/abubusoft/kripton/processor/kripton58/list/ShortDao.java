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
package com.abubusoft.kripton.processor.kripton58.list;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;

@BindDao(ShortBean.class)
public interface ShortDao {
	
	@BindSqlSelect()
	ShortBean selectOne();	
	
	@BindSqlSelect(where = "value=${value}")
	ShortBean selectOne(List<Short> value);

	@BindSqlSelect(where = "value=${value}")
	void selectOne(List<Short> value, OnReadBeanListener<ShortBean> listener);
	
	@BindSqlSelect(where = "value=${value}")
	void selectOne(List<Short> value, OnReadCursorListener listener);
	
	@BindSqlSelect(where = "value=${value}")
	List<ShortBean> selectList(List<Short> value);
	
	@BindSqlUpdate(where = "id=${id} and value=${paramValue}")
	long updateOne(List<Short> value, long id, List<Short> paramValue);
	
	@BindSqlInsert()
	long insert(long id, List<Short> value);
	
	@BindSqlInsert()
	long insert(ShortBean bean);
	
	@BindSqlDelete(where="value=${paramValue}")
	long delete(List<Short> paramValue);
	
}