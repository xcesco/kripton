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
package sqlite.feature.contentprovider.staticmethod;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;

/**
 * The Interface PersonDAO.
 */
@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface PersonDAO {
		
	@BindContentProviderEntry(path = "${name}")
	@BindSqlSelect(where = "name = ${name}")
	static List<Person> customSelect(String name) {
		return null;
	}

	/**
	 * Insert name.
	 *
	 * @param tempName the temp name
	 */
	@BindContentProviderEntry(path = "${name}")
	@BindSqlInsert
	void insertName(@BindSqlParam("name") String tempName);

	/**
	 * Delete raw.
	 *
	 * @param id the id
	 * @return the int
	 */
	@BindContentProviderEntry(path = "${id}")
	@BindSqlDelete(where = "id = ${id}")
	int deleteRaw(long id);

	/**
	 * Update raw.
	 *
	 * @param name the name
	 * @param id the id
	 * @return the int
	 */
	@BindContentProviderEntry(path = "${id}")
	@BindSqlUpdate(where = "id=${id}")
	int updateRaw(String name, long id);

	/**
	 * Select bean.
	 *
	 * @return the list
	 */
	@BindContentProviderEntry(path = "test3")
	@BindSqlSelect
	List<Person> selectBean();

	/**
	 * Select cursor listener.
	 *
	 * @param cursorListener the cursor listener
	 * @param where the where
	 */
	@BindSqlSelect(orderBy = "name")
	void selectCursorListener(OnReadCursorListener cursorListener, @BindSqlDynamicWhere String where);
}