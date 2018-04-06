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
package sqlite.feature.many2many.err3;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Represents dao factory interface for PersonCirtyErr3DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PersonCirtyErr3DataSource
 * @see PersonErr3Dao
 * @see PersonErr3DaoImpl
 * @see Person
 * @see CityErr3Dao
 * @see CityErr3DaoImpl
 * @see City
 * @see PersonCityErr1Dao
 * @see PersonCityErr1DaoImpl
 * @see PersonCityErr3
 */
public interface BindPersonCirtyErr3DaoFactory extends BindDaoFactory {
  
  /**
   * retrieve dao PersonErr3Dao.
   *
   * @return the person err 3 dao
   */
  PersonErr3DaoImpl getPersonErr3Dao();

  /**
   * retrieve dao CityErr3Dao.
   *
   * @return the city err 3 dao
   */
  CityErr3DaoImpl getCityErr3Dao();

  /**
   * retrieve dao PersonCityErr1Dao.
   *
   * @return the person city err 1 dao
   */
  PersonCityErr1DaoImpl getPersonCityErr1Dao();
}
