package com.abubusoft.kripton.example01;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;

@BindDao(ChannelMessage.class)
public interface DaoChannelMessage {
	
	@BindInsert
	long insertBean(ChannelMessage bean);

	@BindInsert
	long insertBeanAll(ChannelMessage bean);
	
	@BindUpdate(value="uid", where = "id = ${bean.id}")
	long updateBean(ChannelMessage bean);
	
	@BindUpdate(where = "ownerUid = ${bean.ownerUid}")
	long updatBeanAll(ChannelMessage bean);
	
	@BindDelete(where="id=${bean.id}")
	long deleteBean(ChannelMessage bean);
	
	/*
	
	
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
	/*@SQLSelect(distinct=true, excludedFields="uid", where="id = ${bean.valid}", having="uid = ${uid} ")
	Cursor selectAll(ChannelMessage bean, long uid);*/
	
	@BindSelect(distinct=true, excludedFields="uid", where="id = ${bean.valid}", having="uid = ${uid} ")
	List<ChannelMessage> selectList(ChannelMessage bean, long uid);
	
	@BindDelete(where="uid=${bean.uid}")
	long deleteBeanAll(ChannelMessage bean);

	
	/*@SQLSelect(distinct=true, where = " id = ${id}")
	Cursor selecAll(long id);*/

/*
	
	
	@SQLInsert
	double insertContactA(String ownerUid, String text, long updateTime);
	
	
	*/
	
	
	
	
}
