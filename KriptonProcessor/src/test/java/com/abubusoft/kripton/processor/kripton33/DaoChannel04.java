package com.abubusoft.kripton.processor.kripton33;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;

@BindDao(Channel.class)
public interface DaoChannel04 {
	@BindDelete(where = "updateTime=${updateTimeA} and updateTime=${updateTimeB}")
	int delete(long updateTime, long updateTimeA);
	
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
