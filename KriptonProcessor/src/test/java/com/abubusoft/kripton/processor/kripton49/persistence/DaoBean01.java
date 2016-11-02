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
package com.abubusoft.kripton.processor.kripton49.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.processor.kripton49.entities.Bean01Entity;

@BindDao(Bean01Entity.class)
public interface DaoBean01 {
	@BindSelect(where="id=${id}")
	Bean01Entity selectOne(Long id);
	
	@BindSelect(where="id=${id}")
	List<Bean01Entity> selectById(Long id);
	
	@BindUpdate(where="id=${id}")
	long updateOne(String text, Long id);
	
	@BindDelete(where="id=${id}")
	long deleteOne(Long id);
	
	@BindInsert()
	long insertOne(Long id);
	
	@BindInsert
	long insertOne(Bean01Entity bean);
	
}