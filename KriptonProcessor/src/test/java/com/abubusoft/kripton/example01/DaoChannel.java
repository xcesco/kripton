package com.abubusoft.kripton.example01;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindInsertRaw;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;

@BindDao(Channel.class)
public interface DaoChannel {

	@BindInsertRaw
	long insertContact(String ownerUid);
	

    @BindSelect(where="1=1")
    List<Channel> selectAll();
	
	
	@BindInsert
	long insertBean(Channel bean);
	
	/*
	@SQLUpdateBean(where = "id=:id")
	long updateContact(ChannelMessage bean, long id);
	
	@SQLSelectBean(where = "")
	ChannelMessage selectBean();*/
	
}
