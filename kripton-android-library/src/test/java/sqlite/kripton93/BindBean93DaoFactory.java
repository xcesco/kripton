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
package sqlite.kripton93;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Represents dao factory interface for Bean93DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean93DataSource
 * @see Bean93Dao
 * @see Bean93DaoImpl
 * @see Bean93
 */
public interface BindBean93DaoFactory extends BindDaoFactory {
  
  /**
   * retrieve dao Bean93Dao.
   *
   * @return the bean 93 dao
   */
  Bean93DaoImpl getBean93Dao();
}
