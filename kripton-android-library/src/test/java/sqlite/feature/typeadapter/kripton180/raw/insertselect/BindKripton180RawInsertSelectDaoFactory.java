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
package sqlite.feature.typeadapter.kripton180.raw.insertselect;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Represents dao factory interface for Kripton180RawInsertSelectDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Kripton180RawInsertSelectDataSource
 * @see EmployeeRawInsertSelectDao
 * @see EmployeeRawInsertSelectDaoImpl
 * @see Employee
 */
public interface BindKripton180RawInsertSelectDaoFactory extends BindDaoFactory {
  
  /**
   * retrieve dao EmployeeRawInsertSelectDao.
   *
   * @return the employee raw insert select dao
   */
  EmployeeRawInsertSelectDaoImpl getEmployeeRawInsertSelectDao();
}
