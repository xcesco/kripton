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
package sqlite.feature.contentprovider.kripton213.case1;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.SQLitePopulator;

import android.database.sqlite.SQLiteDatabase;

/**
 * The Class SamplePopulator.
 */
public class SamplePopulator implements SQLitePopulator {

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.sqlite.SQLitePopulator#execute()
	 */
	@Override
	public void execute(SQLiteDatabase database) {
		Logger.info("execute populator");
		
	}

}

