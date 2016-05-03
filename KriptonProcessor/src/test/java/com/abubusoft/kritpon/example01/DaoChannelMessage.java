package com.abubusoft.kritpon.example01;

import com.abubusoft.kripton.android.annotation.SQLDao;
import com.abubusoft.kripton.android.annotation.SQLDelete;
import com.abubusoft.kripton.android.annotation.SQLInsert;
import com.abubusoft.kripton.android.annotation.SQLInsertBean;
import com.abubusoft.kripton.android.annotation.SQLUpdate;

@SQLDao(ChannelMessage.class)
public interface DaoChannelMessage {

	@SQLInsert
	String insertContact(String ownerUid, String text, long updateTime);
	
	@SQLInsert
	double insertContactA(String ownerUid, String text, long updateTime);
	
	@SQLInsertBean(excludedFields="uid")
	long insertContact(ChannelMessage bean);
	
	@SQLUpdate(where = "id=${id} and ownerUid=${ownerUid}")
	long updateContact(String ownerUid, String text, long id);
	
	@SQLDelete(where ="id=${id}")
	long deleteContact(long id);
/*	
	
	@SQLUpdateBean(where = "id=:id")
	long updateContact(ChannelMessage bean, long id);
	
	@SQLSelectBean(where = "")
	ChannelMessage selectBean();
	*/
	
}
