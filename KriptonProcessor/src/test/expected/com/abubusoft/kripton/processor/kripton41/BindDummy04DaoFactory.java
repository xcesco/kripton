/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.kripton41;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Dummy04DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy04DataSource
 * @see DaoBeanInsertOK
 * @see BindDaoBeanInsertOK
 * @see Bean01
 */
public interface BindDummy04DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoBeanInsertOK
   */
  BindDaoBeanInsertOK getDaoBeanInsertOK();
}
