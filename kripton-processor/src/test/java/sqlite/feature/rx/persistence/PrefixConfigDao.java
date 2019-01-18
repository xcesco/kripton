/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
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
package sqlite.feature.rx.persistence;


import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.feature.rx.model.PrefixConfig;

// TODO: Auto-generated Javadoc
/**
 * Created by xcesco on 18/02/2017.
 */
@BindDao(PrefixConfig.class)
public interface PrefixConfigDao extends AbstractDao<PrefixConfig> {

    /**
     * Select one.
     *
     * @return the prefix config
     */
    @BindSqlSelect
    PrefixConfig selectOne();

    /**
     * Update.
     *
     * @param bean the bean
     * @return the int
     */
    @BindSqlUpdate(where ="id = ${bean.id} ")
    int update(PrefixConfig bean);


}
