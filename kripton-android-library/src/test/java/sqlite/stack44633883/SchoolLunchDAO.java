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
package sqlite.stack44633883;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

// TODO: Auto-generated Javadoc
/**
 * The Interface SchoolLunchDAO.
 */
@BindDao(value=SchoolLunch.class)
public interface SchoolLunchDAO {
	
	/**
	 * Gets the 1.
	 *
	 * @return the 1
	 */
	@BindSqlSelect(jql="SELECT * FROM SchoolLunch ORDER BY fruits COLLATE LOCALIZED")
    List<SchoolLunch> get1();


    /**
     * Gets the all.
     *
     * @return the all
     */
    @BindSqlSelect
    List<SchoolLunch> getAll();

    /**
     * Insert all.
     *
     * @param schoolLunches the school lunches
     */
    @BindSqlInsert
    void insertAll(SchoolLunch schoolLunches);

    /**
     * Delete all.
     */
    @BindSqlDelete
    void deleteAll();
}
