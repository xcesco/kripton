package com.abubusoft.kripton.example01;

import java.util.List;

import android.database.Cursor;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;

@BindDao(Channel.class)
public interface DaoChannel {

	@BindInsert
	long insertContact(String ownerUid);

    @BindSelect
    List<Channel> selectAll();
	
    @BindSelect(where="updateTime=${updateTimeA}")
    List<Channel> select(long updateTimeA);
    
    @BindSelect(where="updateTime=${channel.updateTime}")
    List<Channel> select(Channel channel);
    
    @BindSelect(where="updateTime=${channel.updateTime}")
    Cursor selectCursor(Channel channel);
	
	/*
	@SQLUpdateBean(where = "id=:id")
	long updateContact(ChannelMessage bean, long id);
	
	@SQLSelectBean(where = "")
	ChannelMessage selectBean();*/
	
}
