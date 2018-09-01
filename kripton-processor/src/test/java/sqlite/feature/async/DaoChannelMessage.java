/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
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
package sqlite.feature.async;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;

// TODO: Auto-generated Javadoc
/**
 * The Interface DaoChannelMessage.
 */
@BindDao(ChannelMessage.class)
public interface DaoChannelMessage {	
	
	/**
	 * Insert bean.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlInsert
	long insertBean(ChannelMessage bean);
/*
	@BindInsert
	long insertBeanAll(ChannelMessage bean);
	
	@BindUpdate(value="uid", where = "id = ${bean.id}")
	long updateBean(ChannelMessage bean);
	
	@BindUpdate(where = "ownerUid = ${bean.ownerUid}")
	long updatBeanAll(ChannelMessage bean);
	
	@BindDelete(where="id=${bean.id}")
	long deleteBean(ChannelMessage bean);
	
	
	
	
	@SQLInsert
	String insert(String ownerUid, String text, long updateTime);
	
	@SQLUpdate(where = "id = ${id} and ownerUid = ${ownerUid}")
	long update(String ownerUid, String text, long id);
	
	@SQLDelete(where ="id = ${id}")
	long delete(long id);
	
	

	@SQLSelect(distinct=true, where="id = ${id}", having=" uid = ${uid} ")
	Cursor selectAll(long id, long uid);
	
	*/
	

	/*@SQLSelect(distinct=true, excludedFields="uid", where="id = ${bean.valid}", having="uid = ${uid} ")
	Cursor selectAll(ChannelMessage bean, long uid);*/
/*	
	@BindSelect(distinct=true, excludedFields="uid", where="id = ${bean.valid}", having="uid = ${uid} ")
	List<ChannelMessage> selectList(ChannelMessage bean, long uid);
	
	@BindDelete(where="uid=${bean.uid}")
	long deleteBeanAll(ChannelMessage bean);

	*/
	/*@SQLSelect(distinct=true, where = " id = ${id}")
	Cursor selecAll(long id);*/

/*
	
	
	@SQLInsert
	double insertContactA(String ownerUid, String text, long updateTime);
	
	
	*/
	
	
	
	
}
