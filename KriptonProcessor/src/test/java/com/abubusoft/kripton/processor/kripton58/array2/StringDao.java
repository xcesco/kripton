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
package com.abubusoft.kripton.processor.kripton58.array2;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.android.sqlite.ReadBeanListener;
import com.abubusoft.kripton.android.sqlite.ReadCursorListener;

@BindDao(StringBean.class)
public interface StringDao {
	
	@BindSelect()
	StringBean selectOne();	
	
	@BindSelect(where = "value=${value} and value2=${value2}")
	StringBean selectOne(String[] value, String[] value2);

	@BindSelect(where = "value=${value} and value2=${value2}")
	void selectOne(String[] value, String[] value2, ReadBeanListener<StringBean> listener);	

	@BindSelect(where = "value=${value} and value2=${value2}")
	void selectOne(String[] value, String[] value2, ReadCursorListener listener);
	
	@BindSelect(where = "value=${value} and value2=${value2}")
	List<StringBean> selectList(String[] value, String[] value2);
	
	@BindUpdate(where = "id=${id} and value=${value} and value2=${value2}")
	long updateOne(long id, String[] value, String[] value2);
	
	@BindInsert()
	long insert(long id, String[] value, String[] value2);
	
	@BindInsert()
	long insert(StringBean bean);
	
	@BindDelete(where = "value=${value} and value2=${value2}")
	long delete(String[] value, String[] value2);
	
}