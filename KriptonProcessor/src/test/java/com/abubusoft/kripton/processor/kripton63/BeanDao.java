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
 ******************************************************************************/
package com.abubusoft.kripton.processor.kripton63;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.ReadBeanListener;
import com.abubusoft.kripton.android.sqlite.ReadCursorListener;

@BindDao(Bean63.class)
public interface BeanDao {
	
	@BindSqlSelect()
	Bean63 selectOne();	

	@BindSqlSelect(where = "id = ${id}")
	void selectOne(int id, ReadBeanListener<Bean63> listener);

	@BindSqlSelect(where = "id = ${id}")
	void selectOne(long id, ReadCursorListener listener);

	@BindSqlSelect(where = "id = ${id}")
	List<Bean63> selectList(long id);
	
	@BindSqlUpdate(where = "id=${value.id}")
	long updateOne(Bean63 value);
	
	@BindSqlInsert()
	long insert(Bean63 bean);
	
	// -- map
	@BindSqlInsert()
	long insert(Map<String, Byte> valueMapStringByte);
	
	@BindSqlSelect(where = "value=${valueMapStringByte}")
	Bean63 selectOne(Map<String, Byte> valueMapStringByte);
	
	@BindSqlDelete(where = "value=${valueMapStringByte}")
	long delete(Map<String, Byte> valueMapStringByte);
	
	@BindSqlUpdate(where = "value=${valueMapStringByte}")
	long updateOne(Map<String, Byte> valueMapStringByte);
	
	//---
	@BindSqlInsert()
	long insert(HashMap<EnumType, Byte> valueMapEnumByte);
	
	@BindSqlSelect(where = "value=${valueMapEnumByte}")
	Bean63 selectOne(HashMap<EnumType, Byte> valueMapEnumByte);
	
	@BindSqlSelect(where = "value=${valueMapEnumByte}")
	Cursor selectCursorOne(HashMap<EnumType, Byte> valueMapEnumByte);
	
	@BindSqlSelect(where = "value=${valueMapEnumByte}")
	void selectListenerOne(HashMap<EnumType, Byte> valueMapEnumByte, ReadBeanListener<Bean63> listener);
	
	@BindSqlSelect(where = "value=${valueMapEnumByte}")
	void selectCursorListenerOne(HashMap<EnumType, Byte> valueMapEnumByte, ReadCursorListener listener);
	
	@BindSqlDelete(where = "value=${valueMapEnumByte}")
	long delete(HashMap<EnumType, Byte> valueMapEnumByte);
	
	@BindSqlUpdate(where = "value=${valueMapEnumByte}")
	long updateOne(HashMap<EnumType, Byte> valueMapEnumByte);
	
	@BindSqlSelect(value = "valueMapEnumByte")
	List<Bean63> selectMapEnumByteOne();
	
	@BindSqlSelect(value = "valueMapEnumByte")
	List<String> selectMapEnumByteOneString();
	
	
	
}