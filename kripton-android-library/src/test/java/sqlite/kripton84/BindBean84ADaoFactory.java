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
package sqlite.kripton84;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Represents dao factory interface for Bean84ADataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean84ADataSource
 * @see Bean84ADao
 * @see Bean84ADaoImpl
 * @see Bean84A
 */
public interface BindBean84ADaoFactory extends BindDaoFactory {
  
  /**
   * retrieve dao Bean84ADao.
   *
   * @return the bean 84 A dao
   */
  Bean84ADaoImpl getBean84ADao();
}
