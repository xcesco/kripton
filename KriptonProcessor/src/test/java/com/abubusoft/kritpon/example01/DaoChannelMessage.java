package com.abubusoft.kritpon.example01;

import com.abubusoft.kripton.android.annotation.SQLDao;
import com.abubusoft.kripton.android.annotation.SQLInsert;

@SQLDao(ChannelMessage.class)
public interface DaoChannelMessage {

	@SQLInsert
	String insertContact(String ownerUid, String text, long updateTime);
	
	@SQLInsert
	double insertContactA(String ownerUid, String text, long updateTime);
/*	
	@SQLInsertBean
	long insertContact(ChannelMessage bean);
	
	@SQLUpdateBean(where = "id=:id")
	long updateContact(ChannelMessage bean, long id);
	
	@SQLSelectBean(where = "")
	ChannelMessage selectBean();
	*/
	
}
