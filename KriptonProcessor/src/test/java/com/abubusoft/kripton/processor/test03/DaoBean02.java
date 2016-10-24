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
package com.abubusoft.kripton.processor.test03;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindUpdate;

@BindDao(Bean01.class)
public interface DaoBean02
{
/*
	@BindSelect(where="value > ${startDate}")
	public List<Bean01> selecList(long startDate);
	
	@BindSelect(where="value > ${startDate}")
	public Cursor selectCursor(long startDate);
	
	@BindSelect(where="value > ${startDate}")
	public Bean01 selectOne(long startDate);
	
	@BindSelect(where="value > ${startDate}")
	public void select(long startDate, ReadCursorListener listener);
	
	@BindSelect(where="value > ${startDate}")
	public void select(long startDate, ReadBeanListener<Bean01> listener);
	
	@BindSelect(value="content", where="value > ${startDate}")
	public byte[] selectOneRaw(long startDate);
	
	@BindSelect(value="count(content)", where="value > ${startDate}", having=" value = ${startDate}")
	public List<byte[]> selectRaw(long startDate);
	*/
	
	@BindInsert
	public long insert(Bean01 bean);
	
	@BindInsert
	public long insert(long value, long messageDate);
	
	@BindDelete(where="id=${id}")
	public long delete(long id);
	
	@BindDelete(where = "id=${bean.id}")
	public long delete(Bean01 bean);
	
	@BindUpdate(where = "id>${id}")
	public long update(long value, long id);
	
	@BindUpdate(where = "value=${bean.value}")
	public long update(Bean01 bean);
}