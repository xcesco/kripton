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

import javax.xml.namespace.QName;

import android.content.ContentValues;
import android.database.Cursor;

import com.abubusoft.kripton.common.StringUtil;


public class QNameAdapter implements SqliteAdapter<QName> {
	@Override
	public QName readCursor(Cursor cursor, int columnIndex) throws Exception {
		String[] parts = cursor.getString(columnIndex).split(":");
		if (parts.length == 2) {
			return new QName(null, parts[1], parts[0]);
		} else if (parts.length == 1) {
			return new QName(parts[0]);
		}
		return null;
	}

	@Override
	public void writeValue(QName value, ContentValues content, String columnKey) throws Exception {
		String localName = value.getLocalPart();
		String prefix = value.getPrefix();
		if (!StringUtil.isEmpty(localName)) {
			if (!StringUtil.isEmpty(prefix)) {
				content.put(columnKey, prefix + ":" + localName);
			} else {
				content.put(columnKey,localName);
			}
		} else {
			//content.put(columnKey,null);
		}
		
	}

}
