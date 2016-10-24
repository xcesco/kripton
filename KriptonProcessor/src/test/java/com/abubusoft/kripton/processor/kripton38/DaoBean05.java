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
package com.abubusoft.kripton.processor.kripton38;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.android.sqlite.ReadBeanListener;
import com.abubusoft.kripton.android.sqlite.ReadCursorListener;

@BindDao(Bean05.class)
public interface DaoBean05 {

	@BindSelect(where="pk=${id}")
	Bean05 selectOne(Long id);
	
	
	@BindSelect(where="pk=${bean.pk} and prova=${bean.text}")
	Bean05 selectOne(Bean05 bean);
	
	@BindSelect(where="pk=${id}")
	List<Bean05> selectAll(long id);

	@BindSelect(value="pk",where=" text = ${text}")
	List<Long> selectPK(String text);
	
	@BindSelect(value="count(*)",where=" text = ${text}")
	Long selectCount(String text);
	
	@BindSelect(where="pk=${id}")
	void selectCursorListener(Long id, ReadCursorListener listener);
	
	@BindSelect(where="pk=${id}")
	void selectBeanListener(Long id, ReadBeanListener<Bean05> listener);

	@BindSelect(where="pk=${id}")
	void selectOne(Long id, ReadCursorListener listener);

	@BindSelect(where="pk=${id}")
	void selectOne(long id, ReadBeanListener<Bean05> listener);

	@BindInsert
	long insertRaw(String text, byte[] content, Date creationTime);
	
	@BindInsert
	void insert(Bean05 bean);
	
	@BindUpdate(where="pk=${bean.pk} and text=${bean.text} and creationTime=${bean.creationTime}")
	long updateOne(Bean05 bean);
	
	@BindUpdate(where="pk=${uid} and creationTime=${valido} and creationTime=${validoIn}")
	long updateOne(byte[] content, String text, long uid, Date validoIn, Date valido );

	@BindDelete(where="pk=${bean.pk} and text=${bean.text} and creationTime=${bean.creationTime}")
	long deleteOne(Bean05 bean);
	
	@BindDelete(where="pk=${uid} and creationTime=${valido} and creationTime=${validoIn}")
	long deleteOne(long uid, Date validoIn, Date valido );
	
	@BindDelete(where="pk=${id}")
	long deleteOne(long id);
	
	@BindDelete(where="pk=${va.pk}")
	long deleteBean(Bean05 va);
	
	@BindSelect(value="content",where="pk=${id}")
	byte[] getOne(long id);
	
}