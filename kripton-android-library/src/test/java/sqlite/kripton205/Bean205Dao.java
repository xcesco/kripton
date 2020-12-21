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
package sqlite.kripton205;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;


/**
 * The Interface Bean205Dao.
 */
@BindDao(Bean205.class)
public interface Bean205Dao {

	/**
	 * Select by bean.
	 *
	 * @param name the name
	 * @return the bean 205
	 */
	@BindSqlSelect(where = "name like ${name} || '%'")
	Bean205 selectByBean(String name);

	/**
	 * Insert.
	 *
	 * @param bean the bean
	 * @return true, if successful
	 */
	@BindSqlInsert
	boolean insert(Bean205 bean);
}