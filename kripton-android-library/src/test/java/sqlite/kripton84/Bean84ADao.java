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
package sqlite.kripton84;

import java.util.List;
import java.util.Map;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

@BindDao(Bean84A.class)
public interface Bean84ADao {

	@BindSqlSelect
	List<Bean84A> selectAll();
	
	@BindSqlSelect(where="id=${id}")
	List<Bean84A> selectById(@BindSqlParam("id") long uid);
	
	@BindSqlSelect(where="columnListString=${param1} and columnMapIntegerString=${param2} and columnArrayChar=${param3}  and columnArrayCharType=${param4}")
	List<Bean84A> selectWhere(List<String> param1, Map<Integer, String> param2, Character[] param3, char[] param4);
	
	@BindSqlInsert
	boolean insertAll(Bean84A bean);
	
	@BindSqlInsert
	boolean insert(@BindSqlParam("columnListString") List<String> param1);
	
	@BindSqlUpdate
	boolean updateAll(Bean84A bean);
	
	@BindSqlDelete
	boolean deleteAll(Bean84A bean);
}