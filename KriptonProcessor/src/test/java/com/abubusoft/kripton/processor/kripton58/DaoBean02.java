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
import com.abubusoft.kripton.android.annotation.BindSelect;

@BindDao(Bean02.class)
public interface DaoBean02 /* extends BaseDao<Bean01> */{
	
	@BindSelect()
	Bean02 selectOne();
	

	@BindSelect(where = "stringValue=${stringValue}")
	Bean01 selectList(List<String> stringValue);

	/*
	@BindUpdate(where = "id=${uid}")
	long updateOne(byte[] value, long[] longValue, long uid);

	@BindSelect(value = "value", where = "id=${uid}")
	byte[] selectSingle(long uid);

	@BindSelect(value = "count(*)", where = "id=${id} and longValue=${longValue}")
	long selectLongSingle(long id, long[] longValue);

	@BindSelect(where = "id=${bean.id} and longValue=${bean.longValue} and integerValue=${bean.integerValue}")
	List<Bean01> selectBeanSingle(Bean01 bean);

	@BindSelect(value = "count(*)", where = "id=${id} and longValue=${longValue} or byteValue=${byteValue}")
	long selectRawSingle(long id, long[] longValue, Byte[] byteValue);

	@BindSelect(where = "id=${id} and longValue=${longValue}")
	Bean01 selectRawSingle(long id, Long[] longValue);

	@BindInsert()
	long insert(long id, long[] longValue);

	@BindInsert()
	long update(Bean01 bean);

	@BindDelete(where = "longValue=${bean.longValue}")
	long delete(Bean01 bean);

	@BindUpdate(where = "id=${uid}")
	long update(long uid, long[] longValue, Byte[] byteValue);
*/
}