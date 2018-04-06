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
package sqlite.kripton63;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;

// TODO: Auto-generated Javadoc
/**
 * The Interface BeanDao.
 */
@BindDao(Bean63.class)
public interface BeanDao {
	
	/**
	 * Select one.
	 *
	 * @return the bean 63
	 */
	@BindSqlSelect()
	Bean63 selectOne();	

	/**
	 * Select one.
	 *
	 * @param id the id
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "id = ${id}")
	void selectOne(int id, OnReadBeanListener<Bean63> listener);

	/**
	 * Select one.
	 *
	 * @param id the id
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "id = ${id}")
	void selectOne(long id, OnReadCursorListener listener);

	/**
	 * Select list.
	 *
	 * @param id the id
	 * @return the list
	 */
	@BindSqlSelect(where = "id = ${id}")
	List<Bean63> selectList(long id);
	
	/**
	 * Update one.
	 *
	 * @param value the value
	 * @return the long
	 */
	@BindSqlUpdate(where = "id=${value.id}")
	long updateOne(Bean63 value);
	
	/**
	 * Insert.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlInsert()
	long insert(Bean63 bean);
	
	/**
	 * Insert.
	 *
	 * @param valueMapStringByte the value map string byte
	 * @return the long
	 */
	// -- map
	@BindSqlInsert()
	long insert(Map<String, Byte> valueMapStringByte);
	
	/**
	 * Select one.
	 *
	 * @param valueMapStringByte the value map string byte
	 * @return the bean 63
	 */
	@BindSqlSelect(where = "value=${valueMapStringByte}")
	Bean63 selectOne(Map<String, Byte> valueMapStringByte);
	
	/**
	 * Delete.
	 *
	 * @param valueMapStringByte the value map string byte
	 * @return the long
	 */
	@BindSqlDelete(where = "value=${valueMapStringByte}")
	long delete(Map<String, Byte> valueMapStringByte);
	
	/**
	 * Update one.
	 *
	 * @param id the id
	 * @param valueMapStringByte the value map string byte
	 * @return the long
	 */
	@BindSqlUpdate(where = "value=${valueMapStringByte}")
	long updateOne(long id, Map<String, Byte> valueMapStringByte);
	
	/**
	 * Insert.
	 *
	 * @param valueMapEnumByte the value map enum byte
	 * @return the long
	 */
	//---
	@BindSqlInsert()
	long insert(HashMap<EnumType, Byte> valueMapEnumByte);
	
	/**
	 * Select one.
	 *
	 * @param valueMapEnumByte the value map enum byte
	 * @return the bean 63
	 */
	@BindSqlSelect(where = "value=${valueMapEnumByte}")
	Bean63 selectOne(HashMap<EnumType, Byte> valueMapEnumByte);
	
	/**
	 * Select cursor one.
	 *
	 * @param valueMapEnumByte the value map enum byte
	 * @return the cursor
	 */
	@BindSqlSelect(where = "value=${valueMapEnumByte}")
	Cursor selectCursorOne(HashMap<EnumType, Byte> valueMapEnumByte);
	
	/**
	 * Select listener one.
	 *
	 * @param valueMapEnumByte the value map enum byte
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "value=${valueMapEnumByte}")
	void selectListenerOne(HashMap<EnumType, Byte> valueMapEnumByte, OnReadBeanListener<Bean63> listener);
	
	/**
	 * Select cursor listener one.
	 *
	 * @param valueMapEnumByte the value map enum byte
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "value=${valueMapEnumByte}")
	void selectCursorListenerOne(HashMap<EnumType, Byte> valueMapEnumByte, OnReadCursorListener listener);
	
	/**
	 * Delete.
	 *
	 * @param valueMapEnumByte the value map enum byte
	 * @return the long
	 */
	@BindSqlDelete(where = "value=${valueMapEnumByte}")
	long delete(HashMap<EnumType, Byte> valueMapEnumByte);
	
	/**
	 * Update one.
	 *
	 * @param id the id
	 * @param valueMapEnumByte the value map enum byte
	 * @return the long
	 */
	@BindSqlUpdate(where = "value=${valueMapEnumByte}")
	long updateOne(long id, HashMap<EnumType, Byte> valueMapEnumByte);
	
	/**
	 * Select map enum byte one.
	 *
	 * @return the list
	 */
	@BindSqlSelect(fields = "valueMapEnumByte")
	List<Bean63> selectMapEnumByteOne();
	
	/**
	 * Select map enum byte one string.
	 *
	 * @return the list
	 */
	@BindSqlSelect(fields = "valueMapEnumByte")
	List<String> selectMapEnumByteOneString();
	
	
	
}
