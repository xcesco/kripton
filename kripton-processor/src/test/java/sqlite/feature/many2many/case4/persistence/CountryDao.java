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


import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.feature.many2many.case4.model.Country;
import sqlite.feature.many2many.case4.persistence.AbstractDao;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * Created by xcesco on 26/02/2017.
 */
@BindDao(Country.class)
public interface CountryDao extends AbstractDao<Country> {

    /**
     * Select all.
     *
     * @return the list
     */
    @BindSqlSelect(orderBy = "name asc")
    List<Country> selectAll();

    /**
     * Select by calling code.
     *
     * @param callingCode the calling code
     * @return the country
     */
    @BindSqlSelect(where ="callingCode = ${callingCode}")
    Country selectByCallingCode(String callingCode);

    /**
     * Select by country.
     *
     * @param code the code
     * @return the country
     */
    @BindSqlSelect(where ="code = ${code}")
    Country selectByCountry(String code);
}
