package com.abubusoft.kripton.processor.kripton33;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindUpdate;

@BindDao(Channel.class)
public interface DaoChannel05 {
	@BindUpdate(where = "updateTime=${bean.updateTimeA} and updateTime=${bean.updateTimeB}")
	int update(Channel bean);
	
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
