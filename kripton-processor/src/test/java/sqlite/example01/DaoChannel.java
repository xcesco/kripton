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
package sqlite.example01;

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

// TODO: Auto-generated Javadoc
/**
 * The Interface DaoChannel.
 */
@BindDao(Channel.class)
public interface DaoChannel {
	
	/**
	 * Delete contact bean 1.
	 *
	 * @param channel the channel
	 * @return true, if successful
	 */
	// delete
	@BindSqlDelete(where = "ownerUid=${value.id}")
	boolean deleteContactBean1(@BindSqlParam("value") Channel channel);

	/**
	 * Delete contact bean 2.
	 *
	 * @param value the value
	 * @return true, if successful
	 */
	@BindSqlDelete(where = "ownerUid=${value.id}")
	boolean deleteContactBean2(Channel value);

	/**
	 * Delete contact raw 1.
	 *
	 * @param b the b
	 * @param dummy the dummy
	 * @return the long
	 */
	@BindSqlDelete(where = "ownerUid=${ownerUid} and id=${id}")
	long deleteContactRaw1(@BindSqlParam("ownerUid") String b, @BindSqlParam("id") long dummy);

	/**
	 * Delete contact raw 2.
	 *
	 * @param ownerUid the owner uid
	 * @param id the id
	 * @return true, if successful
	 */
	@BindSqlDelete(where = "ownerUid=${ownerUid} and id=${id}")
	boolean deleteContactRaw2(String ownerUid, long id);

	/**
	 * Insert raw 1.
	 *
	 * @param b the b
	 * @param azz the azz
	 * @return the long
	 */
	// insert
	@BindSqlInsert
	long insertRaw1(@BindSqlParam("ownerUid") String b, @BindSqlParam("id") long azz);

	/**
	 * Insert raw 2.
	 *
	 * @param b the b
	 * @param id the id
	 * @return true, if successful
	 */
	@BindSqlInsert
	boolean insertRaw2(@BindSqlParam("ownerUid") String b, long id);

	/**
	 * Insert raw 3.
	 *
	 * @param ownerUid the owner uid
	 * @param id the id
	 * @return the int
	 */
	@BindSqlInsert
	int insertRaw3(String ownerUid, long id);

	/**
	 * Insert bean 1.
	 *
	 * @param bean the bean
	 * @return the int
	 */
	@BindSqlInsert
	int insertBean1(Channel bean);

	/**
	 * Insert bean 2.
	 *
	 * @param bean the bean
	 * @return true, if successful
	 */
	@BindSqlInsert
	boolean insertBean2(@BindSqlParam("arg") Channel bean);

	/**
	 * Update contact raw 1.
	 *
	 * @param glu the glu
	 * @param aid the aid
	 * @return the long
	 */
	// update
	@BindSqlUpdate(where = "id=${dummy}")
	long updateContactRaw1(@BindSqlParam("id") long glu, @BindSqlParam("dummy") long aid);

	/**
	 * Update contact raw 2.
	 *
	 * @param id the id
	 * @param dummy the dummy
	 * @return the long
	 */
	@BindSqlUpdate(where = "id=${dummy}")
	long updateContactRaw2(long id, long dummy);

	/**
	 * Update contact raw 3.
	 *
	 * @param app the app
	 * @param id the id
	 * @return true, if successful
	 */
	@BindSqlUpdate(where = "id=${test}")
	boolean updateContactRaw3(@BindSqlParam("ownerUid") String app, @BindSqlParam("test") long id);

	/**
	 * Update contact raw 4.
	 *
	 * @param ownerUid the owner uid
	 * @param id the id
	 * @return the int
	 */
	@BindSqlUpdate(where = "id=${id}")
	int updateContactRaw4(String ownerUid, long id);

	/**
	 * Update contact bean 1.
	 *
	 * @param value the value
	 * @return the int
	 */
	@BindSqlUpdate(where = "id=${bean.id}")
	int updateContactBean1(@BindSqlParam("bean") Channel value);

	/**
	 * Update contact bean 2.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlUpdate(where = "id=${bean.id}")
	long updateContactBean2(Channel bean);

	/**
	 * Update contact bean 3.
	 *
	 * @param bean the bean
	 * @return true, if successful
	 */
	@BindSqlUpdate(where = "id=${bean.id}")
	boolean updateContactBean3(Channel bean);

	/**
	 * Select all.
	 *
	 * @return the list
	 */
	// select
	@BindSqlSelect
	List<Channel> selectAll();

	/**
	 * Select raw 1.
	 *
	 * @param updateTimeA the update time A
	 * @return the list
	 */
	@BindSqlSelect(where = "updateTime=${a}")
	List<Channel> selectRaw1(@BindSqlParam("a") long updateTimeA);

	/**
	 * Select raw 2.
	 *
	 * @param updateTimeA the update time A
	 * @return the cursor
	 */
	@BindSqlSelect(where = "updateTime=${a}")
	Cursor selectRaw2(@BindSqlParam("a") long updateTimeA);

	/**
	 * Select raw 3.
	 *
	 * @param updateTimeA the update time A
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "updateTime=${a}")
	void selectRaw3(@BindSqlParam("a") long updateTimeA, OnReadBeanListener<Channel> listener);

	/**
	 * Select raw 4.
	 *
	 * @param updateTimeA the update time A
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "updateTime=${a}")
	void selectRaw4(@BindSqlParam("a") long updateTimeA, OnReadCursorListener listener);

	/**
	 * Select raw 5.
	 *
	 * @param updateTimeA the update time A
	 * @return the sets the
	 */
	@BindSqlSelect(where = "updateTime=${a}")
	Set<Channel> selectRaw5(@BindSqlParam("a") long updateTimeA);

	/**
	 * Select bean 1.
	 *
	 * @param value the value
	 * @return the long
	 */
	@BindSqlSelect(fields = "count(*)", where = "updateTime=${bean.updateTime}")
	long selectBean1(@BindSqlParam("bean") Channel value);

	/**
	 * Select bean 2.
	 *
	 * @param value the value
	 * @param listener the listener
	 */
	@BindSqlSelect(fields = "updateTime", where = "updateTime=${bean.updateTime}")
	void selectBean2(@BindSqlParam("bean") Channel value, OnReadBeanListener<Channel> listener);

	/**
	 * Select bean 3.
	 *
	 * @param value the value
	 * @param listener the listener
	 */
	@BindSqlSelect(fields = "updateTime", where = "updateTime=${bean.updateTime}")
	void selectBean3(@BindSqlParam("bean") Channel value, OnReadCursorListener listener);

	/**
	 * Select bean 4.
	 *
	 * @param value the value
	 * @return the cursor
	 */
	@BindSqlSelect(fields = "updateTime", where = "updateTime=${bean.updateTime}")
	Cursor selectBean4(@BindSqlParam("bean") Channel value);

	/**
	 * Select bean 5.
	 *
	 * @param value the value
	 * @return the channel
	 */
	@BindSqlSelect(fields = "updateTime", where = "updateTime=${bean.updateTime}")
	Channel selectBean5(@BindSqlParam("bean") Channel value);

	/**
	 * Select bean 6.
	 *
	 * @param value the value
	 * @return the array list
	 */
	@BindSqlSelect(fields = "updateTime", where = "updateTime=${bean.updateTime}")
	ArrayList<Channel> selectBean6(@BindSqlParam("bean") Channel value);

	/**
	 * Select bean 7.
	 *
	 * @param value the value
	 * @return the sets the
	 */
	@BindSqlSelect(fields = "updateTime", where = "updateTime=${bean.updateTime}")
	Set<Channel> selectBean7(@BindSqlParam("bean") Channel value);

	/**
	 * Select bean 8.
	 *
	 * @param value the value
	 * @return the list
	 */
	@BindSqlSelect(fields="updateTime",where="updateTime=${bean.updateTime}")
    List<Long> selectBean8(@BindSqlParam("bean") Channel value);

	
}
