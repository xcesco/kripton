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
package sqlite.feature.many2many.case4.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.feature.many2many.case4.model.PhoneNumber;
import sqlite.feature.many2many.case4.persistence.AbstractDao;



/**
 * The Interface PhoneDao.
 */
@BindDao(PhoneNumber.class)
public interface PhoneDao extends AbstractDao<PhoneNumber> {

    /**
     * Select by number.
     *
     * @param number the number
     * @return the phone number
     */
    @BindSqlSelect(where = " number = ${number}")
    PhoneNumber selectByNumber(String number);

    /**
     * Select all.
     *
     * @return the list
     */
    @BindSqlSelect(orderBy = "contactName, number")
    List<PhoneNumber> selectAll();
}
