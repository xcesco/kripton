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
package sqlite.feature.datasourceoptions.livedata;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteQuery;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating PersonCursor objects.
 */
public class PersonCursorFactory implements CursorFactory {

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteDatabase.CursorFactory#newCursor(android.database.sqlite.SQLiteDatabase, android.database.sqlite.SQLiteCursorDriver, java.lang.String, android.database.sqlite.SQLiteQuery)
	 */
	@Override
	public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {
		// TODO Auto-generated method stub
		return null;
	}
 
}
