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
package com.abubusoft.kripton.processor.example01;

import java.util.List;

import android.database.Cursor;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

@BindDao(Channel.class)
public interface DaoChannel {
/*
	// raw insert
	@BindSqlInsert
	long insertRaw1(@BindSqlParam("ownerUid") String b, @BindSqlParam("id") long azz);
	
	@BindSqlInsert
	boolean insertRaw2(@BindSqlParam("ownerUid") String b, long id);
	
	@BindSqlInsert
	int insertRaw3(String ownerUid, long id);
	
	@BindSqlInsert
	int insertBean1(Channel bean);
	
	@BindSqlInsert
	boolean insertBean2(@BindSqlParam("arg") Channel bean);
*/
	/*
	@BindSqlUpdate(where = "id=${dummy}")
	long updateContactRaw1(@BindSqlParam("id") long glu,@BindSqlParam("dummy") long aid);
	
	@BindSqlUpdate(where = "id=${dummy}")
	long updateContactRaw2(long id,long dummy);
	
	@BindSqlUpdate(where="id=${test}")
	boolean updateContactRaw3(@BindSqlParam("ownerUid") String app, @BindSqlParam("test") long id);
	*/
	
	@BindSqlUpdate(where="id=${id}")
	int updateContactRaw4(String ownerUid, long id);
	
	@BindSqlUpdate(where="id=${bean.id}")
	int updateContactBean1(Channel bean);
	
	/*
	
	@BindSqlDelete(where="ownerUid=${ownerUid}")
	long deleteContactRaw1(@BindSqlParam("ownerUid") String b);
	
	@BindSqlDelete(where="ownerUid=${ownerUid}")
	boolean deleteContactRaw2(String ownerUid);*/
	
	@BindSqlDelete(where="ownerUid=${channel.id}")
	boolean deleteContactBean1(Channel channel);
	
	@BindSqlDelete(where="ownerUid=${channel.id}")
	boolean deleteContactBean2(@BindSqlParam("channel") Channel value);

//    @BindSqlSelect
//    List<Channel> selectAll();
//	
//    @BindSqlSelect(where="updateTime=${a}")
//    List<Channel> select(@BindSqlParam("a") long updateTimeA);
  
	
	/*
	@BindSqlSelect(value="count(*)",where="updateTime=${godo.updateTime}")
    long selectCount(@BindSqlParam("godo") Channel input);
	
	@BindSqlSelect(value={"id","ownerUid"}, where="updateTime=${input.updateTime}")
	List<Channel> selectId(Channel input);
	
	
    @BindSqlSelect(where="updateTime=${value.updateTime}")
    List<Channel> selectList(@BindSqlParam("value") Channel input);
    */
	
  /*  
    @BindSqlSelect(where="updateTime=${channel.updateTime}")
    Cursor selectCursor(Channel channel);
	*/
	/*
	*/
	/*
	@SQLSelectBean(where = "")
	ChannelMessage selectBean();*/
	
}
