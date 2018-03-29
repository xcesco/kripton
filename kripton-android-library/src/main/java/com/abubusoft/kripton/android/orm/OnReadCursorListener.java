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
/**
 * 
 */
package com.abubusoft.kripton.android.orm;

import android.database.Cursor;

/**
 * <p>Listener for query with raw result.</p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 *
 * @since 17/mag/2016
 */
public interface OnReadCursorListener {

	/**
	 * This event is fired for each row of resultset.
	 * 
	 * @param cursor
	 * 		cursor from database
	 */
	void onRead(Cursor cursor);
}
