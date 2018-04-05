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
package sqlite.feature.foreignKey;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Represents dao factory interface for Dummy2DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy2DataSource
 * @see DaoBeanA_3
 * @see DaoBeanA_3Impl
 * @see BeanA_3
 * @see DaoBeanA_4
 * @see DaoBeanA_4Impl
 * @see BeanA_4
 */
public interface BindDummy2DaoFactory extends BindDaoFactory {
  
  /**
   * retrieve dao DaoBeanA_3.
   *
   * @return the dao bean A 3
   */
  DaoBeanA_3Impl getDaoBeanA_3();

  /**
   * retrieve dao DaoBeanA_4.
   *
   * @return the dao bean A 4
   */
  DaoBeanA_4Impl getDaoBeanA_4();
}
