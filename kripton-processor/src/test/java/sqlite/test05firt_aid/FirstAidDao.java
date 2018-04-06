/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.test05firt_aid;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

// TODO: Auto-generated Javadoc
/**
 * Created by xcesco on 09/06/2016.
 */
@BindDao(value = FirstAid.class)
public interface FirstAidDao {

    /**
     * Select all.
     *
     * @return the list
     */
    @BindSqlSelect(orderBy = "description")
    List<FirstAid> selectAll();

    /**
     * Delete all.
     *
     * @return the int
     */
    @BindSqlDelete(where="1=1")
    int deleteAll();

    /**
     * Insert.
     *
     * @param bean the bean
     * @return the int
     */
    @BindSqlInsert
    int insert(FirstAid bean);

}
