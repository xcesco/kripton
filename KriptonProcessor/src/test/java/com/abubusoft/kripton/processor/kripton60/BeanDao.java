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
package com.abubusoft.kripton.processor.kripton60;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.android.sqlite.ReadBeanListener;
import com.abubusoft.kripton.android.sqlite.ReadCursorListener;

@BindDao(Bean.class)
public interface BeanDao {
	
	@BindSelect()
	Bean selectOne();	

	@BindSelect(where = "valueBigDecimal=${valueBigDecimal} and valueBigInteger=${valueBigInteger} and valueBoolType=${valueBoolType}")
	Bean selectOne(BigDecimal valueBigDecimal, BigInteger valueBigInteger, boolean valueBoolType);

	@BindSelect(where = "valueBigDecimal=${valueBigDecimal} and valueBigInteger=${valueBigInteger} and valueBoolType=${valueBoolType}")
	void selectOne(BigDecimal valueBigDecimal, BigInteger valueBigInteger, boolean valueBoolType, ReadBeanListener<Bean> listener);

	@BindSelect(where = "valueBigDecimal=${valueBigDecimal} and valueBigInteger=${valueBigInteger} and valueBoolType=${valueBoolType}")
	void selectOne(BigDecimal valueBigDecimal, BigInteger valueBigInteger, boolean valueBoolType, ReadCursorListener listener);

	@BindSelect(where = "valueBigDecimal=${valueBigDecimal} and valueBigInteger=${valueBigInteger} and valueBoolType=${valueBoolType}")
	List<Bean> selectList(BigDecimal valueBigDecimal, BigInteger valueBigInteger, boolean valueBoolType);
	
	@BindUpdate(where = "valueBigDecimal=${valueBigDecimal} and valueBigInteger=${valueBigInteger} and valueBoolType=${valueBoolType}")
	long updateOne(BigDecimal valueBigDecimal, BigInteger valueBigInteger, boolean valueBoolType);
	
	@BindInsert()
	long insert(BigDecimal valueBigDecimal, BigInteger valueBigInteger, boolean valueBoolType);
	
	@BindInsert()
	long insert(Bean bean);
	
	@BindDelete(where = "valueBigDecimal=${valueBigDecimal} and valueBigInteger=${valueBigInteger} and valueBoolType=${valueBoolType}")
	long delete(BigDecimal valueBigDecimal, BigInteger valueBigInteger, boolean valueBoolType);
	
}