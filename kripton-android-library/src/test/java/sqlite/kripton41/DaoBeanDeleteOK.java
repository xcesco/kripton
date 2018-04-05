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
package sqlite.kripton41;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;

// TODO: Auto-generated Javadoc
/**
 * The Interface DaoBeanDeleteOK.
 */
@BindDao(Bean01.class)
public interface DaoBeanDeleteOK  {
	
	/*@BindSelect(value="count(*)>1", where="id=${id} and value=${value}")
	Boolean selectDistance(long id, double value);
	
	
	@BindInsert
	boolean insertDistance(long id, double value);
	*/
	/*
	@BindUpdate(where="id=${test}")
	boolean updateDistance(long id, Double value, long test);*/
	
	/**
	 * Delete distance.
	 *
	 * @param value the value
	 * @return true, if successful
	 */
	@BindSqlDelete(where="id=${value}")
	boolean deleteDistance(double value);
	
	
}
