package com.abubusoft.kripton.processor.kripton33;

import android.database.Cursor;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSelect;

@BindDao(Channel.class)
public interface DaoChannel02 {
	@BindSelect(where = "updateTime=${updateTimeA} and updateTime=${updateTimeB}")
	Cursor select(long updateTimeA);
	
	/*
	 * @BindSelect(where="updateTime=${channel.updateTimeB}") List<Channel> select(Channel channel);
	 */

	/*
	 * @BindInsert(value="updateTimeB") long insertBean(Channel bean);
	 */

	/*
	 * @SQLUpdateBean(where = "id=:id") long updateContact(ChannelMessage bean, long id);
	 * 
	 * @SQLSelectBean(where = "") ChannelMessage selectBean();
	 */

}
