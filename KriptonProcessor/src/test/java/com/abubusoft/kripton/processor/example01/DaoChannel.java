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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.database.Cursor;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;

@BindDao(Channel.class)
public interface DaoChannel {
	
	// delete
	@BindSqlDelete(where="ownerUid=${value.id}")
	boolean deleteContactBean1(@BindSqlParam("value") Channel channel);
	
	@BindSqlDelete(where="ownerUid=${value.id}")
	boolean deleteContactBean2(Channel value);		
	
	@BindSqlDelete(where="ownerUid=${ownerUid} and id=${id}")
	long deleteContactRaw1(@BindSqlParam("ownerUid") String b, @BindSqlParam("id") long dummy);
	
	@BindSqlDelete(where="ownerUid=${ownerUid} and id=${id}")
	boolean deleteContactRaw2(String ownerUid, long id);
	
	
	// insert	
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
	
	// update
	@BindSqlUpdate(where = "id=${dummy}")
	long updateContactRaw1(@BindSqlParam("id") long glu,@BindSqlParam("dummy") long aid);
	
	@BindSqlUpdate(where = "id=${dummy}")
	long updateContactRaw2(long id,long dummy);
	
	@BindSqlUpdate(where="id=${test}")
	boolean updateContactRaw3(@BindSqlParam("ownerUid") String app, @BindSqlParam("test") long id);
	
	@BindSqlUpdate(where="id=${id}")
	int updateContactRaw4(String ownerUid, long id);
		
	@BindSqlUpdate(where="id=${bean.id}")
	int updateContactBean1(@BindSqlParam("bean") Channel value);
	
	@BindSqlUpdate(where="id=${bean.id}")
	long updateContactBean2(Channel bean);
	
	@BindSqlUpdate(where="id=${bean.id}")
	boolean updateContactBean3(Channel bean);
	
	// select
    @BindSqlSelect
    List<Channel> selectAll();

    @BindSqlSelect(where="updateTime=${a}")
    List<Channel> selectRaw1(@BindSqlParam("a") long updateTimeA);  	        
	
	@BindSqlSelect(where="updateTime=${a}")
    Cursor selectRaw2(@BindSqlParam("a") long updateTimeA);
	
	@BindSqlSelect(where="updateTime=${a}")
    void selectRaw3(@BindSqlParam("a") long updateTimeA, OnReadBeanListener<Channel> listener);
	
	@BindSqlSelect(where="updateTime=${a}")
    void selectRaw4(@BindSqlParam("a") long updateTimeA, OnReadCursorListener listener);
	
	@BindSqlSelect(where="updateTime=${a}")
    Set<Channel> selectRaw5(@BindSqlParam("a") long updateTimeA);

	@BindSqlSelect(value="count(*)",where="updateTime=${bean.updateTime}")
    long selectBean1(@BindSqlParam("bean") Channel value);

	@BindSqlSelect(value="updateTime",where="updateTime=${bean.updateTime}")
    void selectBean2(@BindSqlParam("bean") Channel value, OnReadBeanListener<Channel> listener);
	
	@BindSqlSelect(value="updateTime",where="updateTime=${bean.updateTime}")
    void selectBean3(@BindSqlParam("bean") Channel value, OnReadCursorListener listener);	

	@BindSqlSelect(value="updateTime",where="updateTime=${bean.updateTime}")
    Cursor selectBean4(@BindSqlParam("bean") Channel value);
	
	@BindSqlSelect(value="updateTime",where="updateTime=${bean.updateTime}")
    Channel selectBean5(@BindSqlParam("bean") Channel value);
	
	@BindSqlSelect(value="updateTime",where="updateTime=${bean.updateTime}")
    ArrayList<Channel> selectBean6(@BindSqlParam("bean") Channel value);
	
	@BindSqlSelect(value="updateTime",where="updateTime=${bean.updateTime}")
    Set<Channel> selectBean7(@BindSqlParam("bean") Channel value);
	
	@BindSqlSelect(value="updateTime",where="updateTime=${bean.updateTime}")
    List<Long> selectBean8(@BindSqlParam("bean") Channel value);
	
		
}
