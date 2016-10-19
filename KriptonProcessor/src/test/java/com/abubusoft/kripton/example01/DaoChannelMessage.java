package com.abubusoft.kripton.example01;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindInsert;

@BindDao(ChannelMessage.class)
public interface DaoChannelMessage {	
	
	@BindInsert
	long insertBean(ChannelMessage bean);
/*
	@BindInsert
	long insertBeanAll(ChannelMessage bean);
	
	@BindUpdate(value="uid", where = "id = ${bean.id}")
	long updateBean(ChannelMessage bean);
	
	@BindUpdate(where = "ownerUid = ${bean.ownerUid}")
	long updatBeanAll(ChannelMessage bean);
	
	@BindDelete(where="id=${bean.id}")
	long deleteBean(ChannelMessage bean);
	
	
	
	
	@SQLInsert
	String insert(String ownerUid, String text, long updateTime);
	
	@SQLUpdate(where = "id = ${id} and ownerUid = ${ownerUid}")
	long update(String ownerUid, String text, long id);
	
	@SQLDelete(where ="id = ${id}")
	long delete(long id);
	
	

	@SQLSelect(distinct=true, where="id = ${id}", having=" uid = ${uid} ")
	Cursor selectAll(long id, long uid);
	
	*/
	

	/*@SQLSelect(distinct=true, excludedFields="uid", where="id = ${bean.valid}", having="uid = ${uid} ")
	Cursor selectAll(ChannelMessage bean, long uid);*/
/*	
	@BindSelect(distinct=true, excludedFields="uid", where="id = ${bean.valid}", having="uid = ${uid} ")
	List<ChannelMessage> selectList(ChannelMessage bean, long uid);
	
	@BindDelete(where="uid=${bean.uid}")
	long deleteBeanAll(ChannelMessage bean);

	*/
	/*@SQLSelect(distinct=true, where = " id = ${id}")
	Cursor selecAll(long id);*/

/*
	
	
	@SQLInsert
	double insertContactA(String ownerUid, String text, long updateTime);
	
	
	*/
	
	
	
	
}
