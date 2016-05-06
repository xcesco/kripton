package com.abubusoft.kritpon.example01;

import android.database.Cursor;

import com.abubusoft.kripton.android.annotation.SQLDao;
import com.abubusoft.kripton.android.annotation.SQLDelete;
import com.abubusoft.kripton.android.annotation.SQLDeleteBean;
import com.abubusoft.kripton.android.annotation.SQLInsert;
import com.abubusoft.kripton.android.annotation.SQLInsertBean;
import com.abubusoft.kripton.android.annotation.SQLSelect;
import com.abubusoft.kripton.android.annotation.SQLSelectBean;
import com.abubusoft.kripton.android.annotation.SQLUpdate;
import com.abubusoft.kripton.android.annotation.SQLUpdateBean;

@SQLDao(ChannelMessage.class)
public interface DaoChannelMessage {

	/*
	@SQLInsertBean
	long insertBean(ChannelMessage bean);

	@SQLInsertBean
	long insertBeanAll(ChannelMessage bean);
	
	@SQLUpdateBean(value="uid", where = "id = ${id} and ownerUid = ${bean.ownerUid}")
	long updateBean(ChannelMessage bean, long id);
	
	
	@SQLUpdateBean(where = "saluto = ${salutoA} and ownerUid = ${bean.ownerUid}")
	long updatBeanAll(ChannelMessage bean, long id, String salutoA);
	*/
	
	/*
	@SQLDeleteBean(where="id=${bean.id}")
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
	
  /**
   * <p>This SQLSelect <pre>[valid, id, text, type, owner_uid, update_time]</pre> is based on query:</p>
   *
   * <pre>select distinct valid, id, text, type, owner_uid, update_time from channel_message where id = ${bean.valid} having uid = ${uid} </pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[bean.valid, uid]</pre>
   *
   * <p>Extracted column are:</p>
   *
   * 
   *
   * @param bean
   * @param uid
   */
	@SQLSelect(distinct=true, excludedFields="uid", where="id = ${bean.valid}", having="uid = ${uid} ")
	Cursor selectAll(ChannelMessage bean, long uid);
	
	@SQLDeleteBean(where="id=${id} and uid=${bean.uid}")
	long deleteBeanAll(ChannelMessage bean, long id);

	
	/*@SQLSelect(distinct=true, where = " id = ${id}")
	Cursor selecAll(long id);*/

/*
	
	
	@SQLInsert
	double insertContactA(String ownerUid, String text, long updateTime);
	
	
	*/
	
	
	
	
}
