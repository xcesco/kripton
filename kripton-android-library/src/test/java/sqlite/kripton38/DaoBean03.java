/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.kripton38;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

// TODO: Auto-generated Javadoc
/**
 * The Interface DaoBean03.
 */
@BindDao(Bean03.class)
public interface DaoBean03{

	/**
	 * Select one.
	 *
	 * @param id the id
	 * @return the bean 03
	 */
	@BindSqlSelect(where="id=${id}")
	Bean03 selectOne(long id);
	
	/**
	 * Delete one.
	 *
	 * @param id the id
	 * @return the long
	 */
	@BindSqlDelete(where="id=${id}")
	long deleteOne(long id);
	
}
