package com.abubusoft.kripton.example01;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDaoDefinition;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindInsertBean;
import com.abubusoft.kripton.android.annotation.BindSelect;

@BindDaoDefinition(Channel.class)
public interface DaoChannel {

	@BindInsert
	long insertContact(String ownerUid);
	

    @BindSelect
    List<Channel> selectAll();
	
	
	@BindInsertBean
	long insertBean(Channel bean);
	
	/*
	@SQLUpdateBean(where = "id=:id")
	long updateContact(ChannelMessage bean, long id);
	
	@SQLSelectBean(where = "")
	ChannelMessage selectBean();*/
	
}
