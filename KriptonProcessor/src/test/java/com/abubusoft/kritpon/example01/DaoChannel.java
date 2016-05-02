package com.abubusoft.kritpon.example01;

import com.abubusoft.kripton.android.annotation.SQLDao;
import com.abubusoft.kripton.android.annotation.SQLInsert;
import com.abubusoft.kripton.android.annotation.SQLInsertBean;

@SQLDao(ChannelMessage.class)
public interface DaoChannel {

	@SQLInsert
	long insertContact(String ownerUid);
	
	
	@SQLInsertBean(excludedFields={"updateTime"})
	long insertBean(ChannelMessage bean);
	
	/*
	@SQLUpdateBean(where = "id=:id")
	long updateContact(ChannelMessage bean, long id);
	
	@SQLSelectBean(where = "")
	ChannelMessage selectBean();*/
	
}
