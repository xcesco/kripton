/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.kripton63;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;


/**
 * The Interface BeanDao2.
 */
@BindDao(Bean632.class)
public interface BeanDao2 {
	
	/**
	 * Select one.
	 *
	 * @return the bean 632
	 */
	@BindSqlSelect()
	Bean632 selectOne();	

	/**
	 * Select one.
	 *
	 * @param id the id
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "id = ${id}")
	void selectOne(int id, OnReadBeanListener<Bean632> listener);

	/**
	 * Select one.
	 *
	 * @param id the id
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "id = ${id}")
	void selectOne(long id, OnReadCursorListener listener);

	/**
	 * Select list.
	 *
	 * @param id the id
	 * @return the list
	 */
	@BindSqlSelect(where = "id = ${id}")
	List<Bean632> selectList(long id);
	
	/**
	 * Update one.
	 *
	 * @param value the value
	 * @return the long
	 */
	@BindSqlUpdate(where = "id=${value.id}")
	long updateOne(Bean632 value);
	
	/**
	 * Insert.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlInsert()
	long insert(Bean632 bean);
	
	/**
	 * Insert.
	 *
	 * @param valueBigDecimalSet the value big decimal set
	 * @return the long
	 */
	// BigDecimal
	@BindSqlInsert()
	long insert(HashSet<BigDecimal> valueBigDecimalSet);
	
	/**
	 * Select one.
	 *
	 * @param valueBigDecimalSet the value big decimal set
	 * @return the bean 632
	 */
	@BindSqlSelect(where = "value=${valueBigDecimalSet}")
	Bean632 selectOne(HashSet<BigDecimal> valueBigDecimalSet);
	
	/**
	 * Delete.
	 *
	 * @param valueBigDecimalSet the value big decimal set
	 * @return the long
	 */
	@BindSqlDelete(where = "value=${valueBigDecimalSet}")
	long delete(HashSet<BigDecimal> valueBigDecimalSet);
	
	/**
	 * Update one.
	 *
	 * @param valueBigDecimalSet the value big decimal set
	 * @return the long
	 */
	@BindSqlUpdate(where = "value=${valueBigDecimalSet}")
	long updateOne(HashSet<BigDecimal> valueBigDecimalSet);
	
	
	
}