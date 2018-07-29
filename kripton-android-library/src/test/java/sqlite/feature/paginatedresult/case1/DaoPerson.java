/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
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
package sqlite.feature.paginatedresult.case1;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.PaginatedResult;

// TODO: Auto-generated Javadoc
/**
 * The Interface Dao1Person.
 */
@BindDao(Person.class)
public interface DaoPerson {
	
	/**
	 * Select.
	 *
	 * @return the paginated result
	 */
	/*@BindSqlSelect(orderBy="name", pageSize=10)
	PaginatedResult<Person> select();*/

	@BindSqlSelect(orderBy="name", pageSize=10)
	List<Person> selectAll();
		
}