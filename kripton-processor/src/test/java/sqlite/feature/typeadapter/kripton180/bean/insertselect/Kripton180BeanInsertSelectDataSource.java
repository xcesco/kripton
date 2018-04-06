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
package sqlite.feature.typeadapter.kripton180.bean.insertselect;

import com.abubusoft.kripton.android.annotation.BindDataSource;

import sqlite.feature.typeadapter.kripton180.bean.insertselect.EmployeeBeanInsertSelectDao;

/**
 * The Interface Kripton180BeanInsertSelectDataSource.
 */
@BindDataSource(daoSet={EmployeeBeanInsertSelectDao.class}, fileName="kripton180.db", rx=true)
public interface Kripton180BeanInsertSelectDataSource {

}
