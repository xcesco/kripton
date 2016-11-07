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
package com.abubusoft.kripton.processor.kripton58.array;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.ReadBeanListener;
import com.abubusoft.kripton.android.sqlite.ReadCursorListener;

@BindDao(ShortBean.class)
public interface ShortDao {
	
	@BindSqlSelect()
	ShortBean selectOne();	
	
	@BindSqlSelect(where = "value=${value} and value2=${value2}")
	ShortBean selectOne(short[] value, Short[] value2);

	@BindSqlSelect(where = "value=${value} and value2=${value2}")
	void selectOne(short[] value, Short[] value2, ReadBeanListener<ShortBean> listener);	

	@BindSqlSelect(where = "value=${value} and value2=${value2}")
	void selectOne(short[] value, Short[] value2, ReadCursorListener listener);
	
	@BindSqlSelect(where = "value=${value} and value2=${value2}")
	List<ShortBean> selectList(short[] value, Short[] value2);
	
	@BindSqlUpdate(where = "id=${id} and value=${value} and value2=${value2}")
	long updateOne(long id, short[] value, Short[] value2);
	
	@BindSqlInsert()
	long insert(long id, short[] value, Short[] value2);
	
	@BindSqlInsert()
	long insert(ShortBean bean);
	
	@BindSqlDelete(where = "value=${value} and value2=${value2}")
	long delete(short[] value, Short[] value2);
	
}