package com.abubusoft.kritpon.example01;

import com.abubusoft.kripton.android.annotation.SQLDao;
import com.abubusoft.kripton.android.annotation.SQLDelete;
import com.abubusoft.kripton.android.annotation.SQLDeleteBean;
import com.abubusoft.kripton.android.annotation.SQLInsert;
import com.abubusoft.kripton.android.annotation.SQLInsertBean;
import com.abubusoft.kripton.android.annotation.SQLUpdate;
import com.abubusoft.kripton.android.annotation.SQLUpdateBean;

@SQLDao(ChannelMessage.class)
public interface DaoChannelMessage {
	
	@SQLInsertBean(excludedFields="uid")
	long insertBean(ChannelMessage bean);
	
	@SQLInsertBean
	long insertBeanAll(ChannelMessage bean);
	
	@SQLUpdateBean(value="uid", where = "id = ${id} and ownerUid = ${bean.ownerUid}")
	long updateBean(ChannelMessage bean, long id);
	
	@SQLUpdateBean(where = "id = ${id} and ownerUid = ${bean.ownerUid}")
	long updatBeanAll(ChannelMessage bean, long id);
	
	
	@SQLDeleteBean(where="id=${bean.id}")
	long deleteBean(ChannelMessage bean);
	
	@SQLDeleteBean(where="id=${id} and uid=${bean.uid}")
	long deleteBeanAll(ChannelMessage bean, long id);
	
	
	@SQLInsert
	String insert(String ownerUid, String text, long updateTime);
	
	@SQLUpdate(where = "id = ${id} and ownerUid = ${ownerUid}")
	long update(String ownerUid, String text, long id);
	
	@SQLDelete(where ="id = ${id}")
	long delete(long id);

/*
	
	
	@SQLInsert
	double insertContactA(String ownerUid, String text, long updateTime);
	
	
	*/
	
	
	/*
	
	
	@SQLUpdateBean(where = "id=:id")
	long updateContact(ChannelMessage bean, long id);
	
	@SQLSelectBean(where = "")
	ChannelMessage selectBean();
	*/
	
}
