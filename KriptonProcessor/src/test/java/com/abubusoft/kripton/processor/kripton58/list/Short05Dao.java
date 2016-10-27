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
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.android.sqlite.ReadBeanListener;
import com.abubusoft.kripton.android.sqlite.ReadCursorListener;

@BindDao(Short05Bean.class)
public interface Short05Dao {
	/*
	@BindSelect()
	Short05Bean selectOne();	
	
	@BindSelect(where = "value=${value}")
	Short05Bean selectOne(List<Short> value);
	
*/
	@BindSelect(where = "value=${value}")
	void selectOne(List<Short> value, ReadBeanListener<Short05Bean> listener);
	
/*
	@BindSelect(where = "value=${value}")
	void selectOne(List<Short> value, ReadCursorListener listener);
	
	@BindSelect(where = "value=${value}")
	List<Short05Bean> selectList(List<Short> value);
	
	@BindUpdate(where = "id=${id} and value=${paramValue}")
	long updateOne(List<Short> value, long id, List<Short> paramValue);
	
	@BindInsert()
	long insert(long id, List<Short> value);
	
	@BindInsert()
	long insert(Short05Bean bean);
	
	@BindDelete(where="value=${paramValue}")
	long delete(List<Short> paramValue);*/
	
}