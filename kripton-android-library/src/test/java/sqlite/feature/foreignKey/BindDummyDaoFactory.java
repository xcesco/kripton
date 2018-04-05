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
 * Represents dao factory interface for DummyDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see DummyDataSource
 * @see DaoBeanA_1
 * @see DaoBeanA_1Impl
 * @see BeanA_1
 * @see DaoBeanA_2
 * @see DaoBeanA_2Impl
 * @see BeanA_2
 */
public interface BindDummyDaoFactory extends BindDaoFactory {
  
  /**
   * retrieve dao DaoBeanA_1.
   *
   * @return the dao bean A 1
   */
  DaoBeanA_1Impl getDaoBeanA_1();

  /**
   * retrieve dao DaoBeanA_2.
   *
   * @return the dao bean A 2
   */
  DaoBeanA_2Impl getDaoBeanA_2();
}
