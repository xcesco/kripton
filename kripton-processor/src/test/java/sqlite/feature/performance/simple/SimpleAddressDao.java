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
package sqlite.feature.performance.simple;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * Created by xcesco on 23/09/2017.
 */
@BindDao(SimpleAddressItem.class)
public interface SimpleAddressDao {

    /**
     * Select by id.
     *
     * @param id the id
     * @return the simple address item
     */
    @BindSqlSelect(where="id=${id}")
    SimpleAddressItem selectById(long id);

    /**
     * Delete all.
     */
    @BindSqlDelete
    void deleteAll();

    /**
     * Select all.
     *
     * @return the array list
     */
    @BindSqlSelect
    ArrayList<SimpleAddressItem> selectAll();

    /**
     * Insert.
     *
     * @param bean the bean
     */
    @BindSqlInsert
    void insert(SimpleAddressItem bean);
}
