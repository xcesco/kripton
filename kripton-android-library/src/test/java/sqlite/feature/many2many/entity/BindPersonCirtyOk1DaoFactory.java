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
package sqlite.feature.many2many.entity;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Represents dao factory interface for PersonCirtyOk1DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PersonCirtyOk1DataSource
 * @see PersonOk1Dao
 * @see PersonOk1DaoImpl
 * @see Person
 * @see CityOk1Dao
 * @see CityOk1DaoImpl
 * @see City
 * @see PersonCityOk1Dao
 * @see PersonCityOk1DaoImpl
 * @see PersonCityOk1
 */
public interface BindPersonCirtyOk1DaoFactory extends BindDaoFactory {
  
  /**
   * retrieve dao PersonOk1Dao.
   *
   * @return the person ok 1 dao
   */
  PersonOk1DaoImpl getPersonOk1Dao();

  /**
   * retrieve dao CityOk1Dao.
   *
   * @return the city ok 1 dao
   */
  CityOk1DaoImpl getCityOk1Dao();

  /**
   * retrieve dao PersonCityOk1Dao.
   *
   * @return the person city ok 1 dao
   */
  PersonCityOk1DaoImpl getPersonCityOk1Dao();
}
