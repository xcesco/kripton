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
