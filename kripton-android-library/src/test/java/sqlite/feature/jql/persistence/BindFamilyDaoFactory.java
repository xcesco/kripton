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

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Represents dao factory interface for FamilyDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see FamilyDataSource
 * @see DaoChild
 * @see DaoChildImpl
 * @see Child
 * @see DaoPerson
 * @see DaoPersonImpl
 * @see Person
 */
public interface BindFamilyDaoFactory extends BindDaoFactory {
  
  /**
   * retrieve dao DaoChild.
   *
   * @return the dao child
   */
  DaoChildImpl getDaoChild();

  /**
   * retrieve dao DaoPerson.
   *
   * @return the dao person
   */
  DaoPersonImpl getDaoPerson();
}
