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
 ******************************************************************************/
package com.abubusoft.kripton.processor.kripton62;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.android.sqlite.ReadBeanListener;
import com.abubusoft.kripton.android.sqlite.ReadCursorListener;

@BindDao(Bean2.class)
public interface BeanDao2 {
	
	@BindSelect()
	Bean2 selectOne();	

	@BindSelect(where = "id = ${id}")
	void selectOne(int id, ReadBeanListener<Bean2> listener);

	@BindSelect(where = "id = ${id}")
	void selectOne(long id, ReadCursorListener listener);

	@BindSelect(where = "id = ${id}")
	List<Bean2> selectList(long id);
	
	@BindUpdate(where = "id=${value.id}")
	long updateOne(Bean2 value);
	
	@BindInsert()
	long insert(Bean2 bean);
	
	// BigDecimal
	@BindInsert()
	long insert(HashSet<BigDecimal> valueBigDecimalSet);
	
	@BindSelect(where = "value=${valueBigDecimalSet}")
	Bean2 selectOne(HashSet<BigDecimal> valueBigDecimalSet);
	
	@BindDelete(where = "value=${valueBigDecimalSet}")
	long delete(HashSet<BigDecimal> valueBigDecimalSet);
	
	@BindUpdate(where = "value=${valueBigDecimalSet}")
	long updateOne(HashSet<BigDecimal> valueBigDecimalSet);
	
	
	
}