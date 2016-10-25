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
package com.abubusoft.kripton.processor.kripton52;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;

@BindDao(Bean01.class)
public interface DaoBean01 /*extends BaseDao<Bean01>*/  {
	/*
	@BindSelect(where="value=${value} and longValue=${longValue}")
	Bean01 selectOne(byte[] value, long[] longValue);
	
	@BindUpdate(where="id=${uid}")
	long updateOne(byte[] value, long[] longValue, long uid);
	
	@BindSelect(value="value", where="id=${uid}")
	byte[] selectSingle(long uid);
	*/
	@BindSelect(value="count(*)", where="id=${uid} and longValue=${longValue}")
	long selectLongSingle(long uid, long[] longValue);
	
	@BindUpdate(where="id=${uid}")
	long update(long uid, long[] longValue);
	
}