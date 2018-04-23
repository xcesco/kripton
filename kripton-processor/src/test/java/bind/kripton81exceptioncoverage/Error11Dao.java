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
package bind.kripton81exceptioncoverage;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

// TODO: Auto-generated Javadoc
/**
 * The Interface Error11Dao.
 */
@BindDao(Error11Bean.class)
public interface Error11Dao {

	/**
	 * Select all.
	 *
	 * @param date the date
	 * @return the list
	 */
	@BindSqlSelect(fields = { "date1" }, where = "date=${date}")
	public List<Error11Bean> selectAll(Date date);
}
