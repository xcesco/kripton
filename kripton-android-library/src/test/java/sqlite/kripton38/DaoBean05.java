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
package sqlite.kripton38;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;

// TODO: Auto-generated Javadoc
/**
 * The Interface DaoBean05.
 */
@BindDao(Bean05.class)
public interface DaoBean05 {

	/**
	 * Select one.
	 *
	 * @param id the id
	 * @return the bean 05
	 */
	@BindSqlSelect(where="pk=${id}")
	Bean05 selectOne(Long id);
	
	
	/**
	 * Select one.
	 *
	 * @param bean the bean
	 * @return the bean 05
	 */
	@BindSqlSelect(where="pk=${bean.pk} and text=${bean.text}")
	Bean05 selectOne(Bean05 bean);
	
	/**
	 * Select all.
	 *
	 * @param id the id
	 * @return the list
	 */
	@BindSqlSelect(where="pk=${id}")
	List<Bean05> selectAll(long id);

	/**
	 * Select PK.
	 *
	 * @param text the text
	 * @return the list
	 */
	@BindSqlSelect(fields="pk",where=" text = ${text}")
	List<Long> selectPK(String text);
	
	/**
	 * Select count.
	 *
	 * @param text the text
	 * @return the long
	 */
	@BindSqlSelect(fields="count(*)",where=" text = ${text}")
	Long selectCount(String text);
	
	/**
	 * Select cursor listener.
	 *
	 * @param id the id
	 * @param listener the listener
	 */
	@BindSqlSelect(where="pk=${id}")
	void selectCursorListener(Long id, OnReadCursorListener listener);
	
	/**
	 * Select bean listener.
	 *
	 * @param id the id
	 * @param listener the listener
	 */
	@BindSqlSelect(where="pk=${id}")
	void selectBeanListener(Long id, OnReadBeanListener<Bean05> listener);

	/**
	 * Select one.
	 *
	 * @param id the id
	 * @param listener the listener
	 */
	@BindSqlSelect(where="pk=${id}")
	void selectOne(Long id, OnReadCursorListener listener);

	/**
	 * Select one.
	 *
	 * @param id the id
	 * @param listener the listener
	 */
	@BindSqlSelect(where="pk=${id}")
	void selectOne(long id, OnReadBeanListener<Bean05> listener);

	/**
	 * Insert raw.
	 *
	 * @param text the text
	 * @param content the content
	 * @param creationTime the creation time
	 * @return the long
	 */
	@BindSqlInsert
	long insertRaw(String text, byte[] content, Date creationTime);
	
	/**
	 * Insert.
	 *
	 * @param bean the bean
	 */
	@BindSqlInsert
	void insert(Bean05 bean);
	
	/**
	 * Update one.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlUpdate(where="pk=${bean.pk} and text=${bean.text} and creationTime=${bean.creationTime}")
	long updateOne(Bean05 bean);
	
	/**
	 * Update one.
	 *
	 * @param content the content
	 * @param text the text
	 * @param uid the uid
	 * @param validoIn the valido in
	 * @param valido the valido
	 * @return the long
	 */
	@BindSqlUpdate(where="pk=${uid} and creationTime=${valido} and creationTime=${validoIn}")
	long updateOne(byte[] content, String text, long uid, Date validoIn, Date valido );

	/**
	 * Delete one.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlDelete(where="pk=${bean.pk} and text=${bean.text} and creationTime=${bean.creationTime}")
	long deleteOne(Bean05 bean);
	
	/**
	 * Delete one.
	 *
	 * @param uid the uid
	 * @param validoIn the valido in
	 * @param valido the valido
	 * @return the long
	 */
	@BindSqlDelete(where="pk=${uid} and creationTime=${valido} and creationTime=${validoIn}")
	long deleteOne(long uid, Date validoIn, Date valido );
	
	/**
	 * Delete one.
	 *
	 * @param id the id
	 * @return the long
	 */
	@BindSqlDelete(where="pk=${id}")
	long deleteOne(long id);
	
	/**
	 * Delete bean.
	 *
	 * @param va the va
	 * @return the long
	 */
	@BindSqlDelete(where="pk=${va.pk}")
	long deleteBean(Bean05 va);
	
	/**
	 * Gets the one.
	 *
	 * @param id the id
	 * @return the one
	 */
	@BindSqlSelect(fields="content",where="pk=${id}")
	byte[] getOne(long id);
	
}