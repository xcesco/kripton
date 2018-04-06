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
package sqlite.quickstart.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.quickstart.model.Todo;

// TODO: Auto-generated Javadoc
/**
 * Created by xcesco on 04/01/2017.
 */
@BindDao(Todo.class)
public interface TodoDao {
    
    /**
     * Insert.
     *
     * @param bean the bean
     */
    @BindSqlInsert(includePrimaryKey = true)
    void insert(Todo bean);

    /**
     * Select by user id.
     *
     * @param userId the user id
     * @return the list
     */
    @BindSqlSelect(where="userId = ${value}")
    List<Todo> selectByUserId(@BindSqlParam("value") long userId);

    /**
     * Select one by user id.
     *
     * @param userId the user id
     * @return the todo
     */
    @BindSqlSelect(where="id = ${value}")
    Todo selectOneByUserId(@BindSqlParam("value") long userId);

}
