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
package sqlite.feature.many2many.case6.persistence;

import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;

// TODO: Auto-generated Javadoc
/**
 * The Interface AbstractDao.
 *
 * @param <E> the element type
 */
public interface AbstractDao<E> {

    /**
     * Insert.
     *
     * @param bean the bean
     * @return the int
     */
    @BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.REPLACE)
    int insert(E bean);

    /**
     * Select by id.
     *
     * @param id the id
     * @return the e
     */
    @BindSqlSelect(where = "id = ${id}")
    E selectById(long id);

    /**
     * Delete by id.
     *
     * @param id the id
     * @return true, if successful
     */
    @BindSqlDelete(where ="id = ${id}")
    boolean deleteById(long id);
    
    /**
     * Update by id.
     *
     * @param bean the bean
     * @return true, if successful
     */
    @BindSqlDelete(where ="id = ${bean.id}")
    boolean updateById(E bean);


}
