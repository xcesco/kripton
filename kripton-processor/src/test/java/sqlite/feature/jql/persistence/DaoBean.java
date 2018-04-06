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
package sqlite.feature.jql.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

// TODO: Auto-generated Javadoc
/**
 * The Interface DaoBean.
 *
 * @param <E> the element type
 */
public interface DaoBean<E> {

	/**
	 * Select all.
	 *
	 * @return the list
	 */
	@BindSqlSelect
	public List<E> selectAll();

	/**
	 * Insert bean.
	 *
	 * @param bean the bean
	 * @return the e
	 */
	@BindSqlInsert
	public E insertBean(E bean);

}
