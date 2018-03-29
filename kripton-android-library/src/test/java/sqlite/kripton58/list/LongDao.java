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

@BindDao(LongBean.class)
public interface LongDao {
	@BindSqlSelect()
	LongBean selectOne();

	@BindSqlSelect(where = "CAST(value AS TEXT)=${value}")
	LongBean selectOne(List<Long> value);

	@BindSqlSelect(where = "CAST(value AS TEXT)=${value}")
	void selectOne(List<Long> value, OnReadBeanListener<LongBean> listener);

	@BindSqlSelect(where = "CAST(value AS TEXT)=${value}")
	void selectOne(List<Long> value, OnReadCursorListener listener);

	@BindSqlSelect(where = "CAST(value AS TEXT)=${value}")
	List<LongBean> selectList(List<Long> value);

	@BindSqlUpdate(where = "id=${id} and CAST(value AS TEXT)=${paramValue}")
	long updateOne(List<Long> value, long id, List<Long> paramValue);

	@BindSqlInsert()
	long insert(long id, List<Long> value);

	@BindSqlInsert()
	long insert(LongBean bean);

	@BindSqlDelete(where = "CAST(value AS TEXT)=${paramValue}")
	long delete(List<Long> paramValue);

}