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
package sqlite.kripton84;

import java.util.List;
import java.util.Map;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

@BindDao(Bean84.class)
public interface Bean84Dao {

	@BindSqlSelect
	List<Bean84> selectAll();
	
	@BindSqlSelect(where="columnListString=${param1} and columnMapIntegerString=${param2} and columnArrayChar=${param3}  and columnArrayCharType=${param4}")
	List<Bean84> selectWhere(List<String> param1, Map<Integer, String> param2, Character[] param3, char[] param4);
	
	@BindSqlInsert
	boolean insertAll(Bean84 bean);
	
	@BindSqlInsert
	boolean insert(@BindSqlParam("columnListString") List<String> param1);
	
	@BindSqlUpdate
	boolean updateAll(Bean84 bean);
	
	@BindSqlDelete
	boolean deleteAll(Bean84 bean);
}