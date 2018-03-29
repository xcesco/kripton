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
package sqlite.kripton58.list;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.orm.OnReadBeanListener;
import com.abubusoft.kripton.android.orm.OnReadCursorListener;

@BindDao(CharBean.class)
public interface CharDao {
	
	@BindSqlSelect()
	CharBean selectOne();	
	
	@BindSqlSelect(where = "value=${value}")
	CharBean selectOne(List<Character> value);

	@BindSqlSelect(where = "value=${value}")
	void selectOne(List<Short> value, OnReadBeanListener<CharBean> listener);
	
	@BindSqlSelect(where = "value=${value}")
	void selectOne(List<Character> value, OnReadCursorListener listener);
	
	@BindSqlSelect(where = "value=${value}")
	List<CharBean> selectList(List<Character> value);
	
	@BindSqlUpdate(where = "id=${id} and value=${paramValue}")
	long updateOne(List<Character> value, long id, List<Character> paramValue);
	
	@BindSqlInsert()
	long insert(long id, List<Character> value);
	
	@BindSqlInsert()
	long insert(CharBean bean);
	
	@BindSqlDelete(where="value=${paramValue}")
	long delete(List<Character> paramValue);
	
}