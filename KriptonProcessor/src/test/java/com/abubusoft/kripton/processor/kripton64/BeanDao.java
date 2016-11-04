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
package com.abubusoft.kripton.processor.kripton64;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.android.sqlite.ReadBeanListener;
import com.abubusoft.kripton.android.sqlite.ReadCursorListener;

@BindDao(Bean63.class)
public interface BeanDao {
	
	@BindSelect()
	Bean63 selectOne();	

	@BindSelect(where = "id = ${id}")
	void selectOne(int id, ReadBeanListener<Bean63> listener);

	@BindSelect(where = "id = ${id}")
	void selectOne(long id, ReadCursorListener listener);

	@BindSelect(where = "id = ${id}")
	List<Bean63> selectList(long id);
	
	@BindUpdate(where = "id=${value.id}")
	long updateOne(Bean63 value);
	
	@BindInsert()
	long insert(Bean63 bean);
	
	// -- map
	@BindInsert()
	long insert(Map<String, Byte> valueMapStringByte);
	
	@BindSelect(where = "value=${valueMapStringByte}")
	Bean63 selectOne(Map<String, Byte> valueMapStringByte);
	
	@BindDelete(where = "value=${valueMapStringByte}")
	long delete(Map<String, Byte> valueMapStringByte);
	
	@BindUpdate(where = "value=${valueMapStringByte}")
	long updateOne(Map<String, Byte> valueMapStringByte);
	
	//---
	@BindInsert()
	long insert(HashMap<EnumType, Byte> valueMapEnumByte);
	
	@BindSelect(where = "value=${valueMapEnumByte}")
	Bean63 selectOne(HashMap<EnumType, Byte> valueMapEnumByte);
	
	@BindSelect(where = "value=${valueMapEnumByte}")
	Cursor selectCursorOne(HashMap<EnumType, Byte> valueMapEnumByte);
	
	@BindSelect(where = "value=${valueMapEnumByte}")
	void selectListenerOne(HashMap<EnumType, Byte> valueMapEnumByte, ReadBeanListener<Bean63> listener);
	
	@BindSelect(where = "value=${valueMapEnumByte}")
	void selectCursorListenerOne(HashMap<EnumType, Byte> valueMapEnumByte, ReadCursorListener listener);
	
	@BindDelete(where = "value=${valueMapEnumByte}")
	long delete(HashMap<EnumType, Byte> valueMapEnumByte);
	
	@BindUpdate(where = "value=${valueMapEnumByte}")
	long updateOne(HashMap<EnumType, Byte> valueMapEnumByte);
	
	@BindSelect(value = "valueMapEnumByte")
	List<Bean63> selectMapEnumByteOne();
	
	@BindSelect(value = "valueMapEnumByte")
	List<String> selectMapEnumByteOneString();
	
	
	
}