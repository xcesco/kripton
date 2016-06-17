package com.abubusoft.kripton.processor.kripton33;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindUpdate;

@BindDao(Channel.class)
public interface DaoChannel03 {
	@BindUpdate(where = "updateTime=${updateTimeA} and updateTime=${updateTimeB}")
	int update(long updateTime, long updateTimeA);
	
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
