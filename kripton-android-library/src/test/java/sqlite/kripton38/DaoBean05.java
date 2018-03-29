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
import com.abubusoft.kripton.android.orm.OnReadBeanListener;
import com.abubusoft.kripton.android.orm.OnReadCursorListener;

@BindDao(Bean05.class)
public interface DaoBean05 {

	@BindSqlSelect(where="pk=${id}")
	Bean05 selectOne(Long id);
	
	
	@BindSqlSelect(where="pk=${bean.pk} and text=${bean.text}")
	Bean05 selectOne(Bean05 bean);
	
	@BindSqlSelect(where="pk=${id}")
	List<Bean05> selectAll(long id);

	@BindSqlSelect(fields="pk",where=" text = ${text}")
	List<Long> selectPK(String text);
	
	@BindSqlSelect(fields="count(*)",where=" text = ${text}")
	Long selectCount(String text);
	
	@BindSqlSelect(where="pk=${id}")
	void selectCursorListener(Long id, OnReadCursorListener listener);
	
	@BindSqlSelect(where="pk=${id}")
	void selectBeanListener(Long id, OnReadBeanListener<Bean05> listener);

	@BindSqlSelect(where="pk=${id}")
	void selectOne(Long id, OnReadCursorListener listener);

	@BindSqlSelect(where="pk=${id}")
	void selectOne(long id, OnReadBeanListener<Bean05> listener);

	@BindSqlInsert
	long insertRaw(String text, byte[] content, Date creationTime);
	
	@BindSqlInsert
	void insert(Bean05 bean);
	
	@BindSqlUpdate(where="pk=${bean.pk} and text=${bean.text} and creationTime=${bean.creationTime}")
	long updateOne(Bean05 bean);
	
	@BindSqlUpdate(where="pk=${uid} and creationTime=${valido} and creationTime=${validoIn}")
	long updateOne(byte[] content, String text, long uid, Date validoIn, Date valido );

	@BindSqlDelete(where="pk=${bean.pk} and text=${bean.text} and creationTime=${bean.creationTime}")
	long deleteOne(Bean05 bean);
	
	@BindSqlDelete(where="pk=${uid} and creationTime=${valido} and creationTime=${validoIn}")
	long deleteOne(long uid, Date validoIn, Date valido );
	
	@BindSqlDelete(where="pk=${id}")
	long deleteOne(long id);
	
	@BindSqlDelete(where="pk=${va.pk}")
	long deleteBean(Bean05 va);
	
	@BindSqlSelect(fields="content",where="pk=${id}")
	byte[] getOne(long id);
	
}