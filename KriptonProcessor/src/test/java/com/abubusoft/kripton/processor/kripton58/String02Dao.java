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
package com.abubusoft.kripton.processor.kripton58;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;

@BindDao(String02Bean.class)
public interface String02Dao {
	
	@BindSelect()
	String02Bean selectOne();	
	
	@BindSelect(where = "value=${value}")
	String02Bean selectOne(List<String> value);
	
	@BindSelect(where = "value=${value}")
	List<String02Bean> selectList(List<String> value);
	
	@BindUpdate(where = "id=${id} and value=${paramValue}")
	long updateOne(List<String> value, long id, List<String> paramValue);
	
	@BindInsert()
	long insert(long id, List<String> value);
	
	@BindInsert()
	long insert(String02Bean bean);
	
	@BindDelete(where="value=${paramValue}")
	long delete(List<String> paramValue);
	
}