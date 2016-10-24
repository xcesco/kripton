/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.android.adapter;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Base64;

/**
 * Transformer between a base64 encoded string and a byte[]
 * 
 * @author bulldog
 *
 */
public class Base64Adapter implements SqliteAdapter<byte[]> {

	@Override
	public byte[] readCursor(Cursor cursor, int columnIndex) throws Exception {
		byte[] buffer=cursor.getBlob(columnIndex);		
		return buffer==null?null:Base64.decode(buffer,0);
	}

	@Override
	public void writeValue(byte[] value, ContentValues content, String columnKey) throws Exception {
		if (value==null) return;
		content.put(columnKey, Base64.encode(value, 0));
		
	}



}
