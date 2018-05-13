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
package com.abubusoft.kripton.android.sqlite;

import android.database.sqlite.SQLiteDatabase;

/**
 * This simple interface is needed to implements populators executed after
 * database creation. Database used as parameter is already opened and it will
 * automatically closed by Kripton, after populator ends.
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public interface SQLitePopulator {

	/**
	 * Execute
	 * 
	 * @param database
	 */
	void execute(SQLiteDatabase database);
}
