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
package sqlite.feature.many2many.case4.persistence;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Represents dao factory interface for XenoDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see XenoDataSource
 * @see PhoneDao
 * @see PhoneDaoImpl
 * @see PhoneNumber
 * @see PrefixConfigDao
 * @see PrefixConfigDaoImpl
 * @see PrefixConfig
 * @see CountryDao
 * @see CountryDaoImpl
 * @see Country
 * @see Person2PhoneDao
 * @see Person2PhoneDaoImpl
 * @see PersonPhoneNumber
 * @see PersonDao
 * @see PersonDaoImpl
 * @see Person
 */
public interface BindXenoDaoFactory extends BindDaoFactory {
  
  /**
   * retrieve dao PhoneDao.
   *
   * @return the phone dao
   */
  PhoneDaoImpl getPhoneDao();

  /**
   * retrieve dao PrefixConfigDao.
   *
   * @return the prefix config dao
   */
  PrefixConfigDaoImpl getPrefixConfigDao();

  /**
   * retrieve dao CountryDao.
   *
   * @return the country dao
   */
  CountryDaoImpl getCountryDao();

  /**
   * retrieve dao Person2PhoneDao.
   *
   * @return the person 2 phone dao
   */
  Person2PhoneDaoImpl getPerson2PhoneDao();

  /**
   * retrieve dao PersonDao.
   *
   * @return the person dao
   */
  PersonDaoImpl getPersonDao();
}
