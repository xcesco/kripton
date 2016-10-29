package com.abubusoft.kripton.processor.kripton51.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtil;
import com.abubusoft.kripton.processor.kripton51.entities.MessageEntity;
import com.abubusoft.kripton.processor.kripton51.entities.OwnerType;
import com.abubusoft.kripton.processor.kripton51.internal.MessageType;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>MessageEntity</code>, based on interface <code>DaoMessage</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton51.entities.MessageEntity
 *  @see com.abubusoft.kripton.processor.kripton51.persistence.DaoMessage
 *  @see com.abubusoft.kripton.processor.kripton51.entities.MessageEntityTable
 */
public class BindDaoMessage extends AbstractDao implements DaoMessage {
  public BindDaoMessage(BindWhisperDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, channel_id, owner_type, face_uid, text, owner_uid, channel_uid, update_time, type FROM message WHERE channelId = ${channelId}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[channelId]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, channel_id, owner_type, face_uid, text, owner_uid, channel_uid, update_time, type]</pre>
   *
   * @param channelId
   *
   * @return list of bean or empty list.
   */
  @Override
  public List<MessageEntity> selectByChannel(long channelId) {
    // build where condition
    String[] args={String.valueOf(channelId)};

    Logger.info(StringUtil.formatSQL("SELECT id, channel_id, owner_type, face_uid, text, owner_uid, channel_uid, update_time, type FROM message WHERE channel_id = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, channel_id, owner_type, face_uid, text, owner_uid, channel_uid, update_time, type FROM message WHERE channel_id = ?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<MessageEntity> resultList=new LinkedList<MessageEntity>();
    MessageEntity resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("channel_id");
      int index2=cursor.getColumnIndex("owner_type");
      int index3=cursor.getColumnIndex("face_uid");
      int index4=cursor.getColumnIndex("text");
      int index5=cursor.getColumnIndex("owner_uid");
      int index6=cursor.getColumnIndex("channel_uid");
      int index7=cursor.getColumnIndex("update_time");
      int index8=cursor.getColumnIndex("type");

      do
       {
        resultBean=new MessageEntity();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.channelId=cursor.getLong(index1); }
        if (!cursor.isNull(index2)) { resultBean.ownerType=OwnerType.valueOf(cursor.getString(index2)); }
        if (!cursor.isNull(index3)) { resultBean.faceUid=cursor.getString(index3); }
        if (!cursor.isNull(index4)) { resultBean.text=cursor.getString(index4); }
        if (!cursor.isNull(index5)) { resultBean.ownerUid=cursor.getString(index5); }
        if (!cursor.isNull(index6)) { resultBean.channelUid=cursor.getString(index6); }
        if (!cursor.isNull(index7)) { resultBean.updateTime=cursor.getLong(index7); }
        if (!cursor.isNull(index8)) { resultBean.type=MessageType.valueOf(cursor.getString(index8)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE message SET channel_id=${bean.channelId}, owner_type=${bean.ownerType}, face_uid=${bean.faceUid}, text=${bean.text}, owner_uid=${bean.ownerUid}, channel_uid=${bean.channelUid}, update_time=${bean.updateTime}, type=${bean.type} WHERE id = ${bean.id}</pre>
   *
   * @param bean
   * 	used as updated field and in where condition
   *
   * @return true if record is updated
   */
  @Override
  public boolean updateById(MessageEntity bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("channel_id", bean.channelId);

    if (bean.ownerType!=null) {
      contentValues.put("owner_type", bean.ownerType.toString());
    } else {
      contentValues.putNull("owner_type");
    }

    if (bean.faceUid!=null) {
      contentValues.put("face_uid", bean.faceUid);
    } else {
      contentValues.putNull("face_uid");
    }

    if (bean.text!=null) {
      contentValues.put("text", bean.text);
    } else {
      contentValues.putNull("text");
    }

    if (bean.ownerUid!=null) {
      contentValues.put("owner_uid", bean.ownerUid);
    } else {
      contentValues.putNull("owner_uid");
    }

    if (bean.channelUid!=null) {
      contentValues.put("channel_uid", bean.channelUid);
    } else {
      contentValues.putNull("channel_uid");
    }

    contentValues.put("update_time", bean.updateTime);

    if (bean.type!=null) {
      contentValues.put("type", bean.type.toString());
    } else {
      contentValues.putNull("type");
    }

    String[] whereConditions={String.valueOf(bean.id)};

    Logger.info(StringUtil.formatSQL("UPDATE message SET channel_id='"+StringUtil.checkSize(contentValues.get("channel_id"))+"', owner_type='"+StringUtil.checkSize(contentValues.get("owner_type"))+"', face_uid='"+StringUtil.checkSize(contentValues.get("face_uid"))+"', text='"+StringUtil.checkSize(contentValues.get("text"))+"', owner_uid='"+StringUtil.checkSize(contentValues.get("owner_uid"))+"', channel_uid='"+StringUtil.checkSize(contentValues.get("channel_uid"))+"', update_time='"+StringUtil.checkSize(contentValues.get("update_time"))+"', type='"+StringUtil.checkSize(contentValues.get("type"))+"' WHERE id = %s"), (Object[])whereConditions);
    int result = database().update("message", contentValues, "id = ?", whereConditions);
    return result!=0;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO message (channel_id, owner_type, face_uid, text, owner_uid, channel_uid, update_time, type) VALUES (${bean.channelId}, ${bean.ownerType}, ${bean.faceUid}, ${bean.text}, ${bean.ownerUid}, ${bean.channelUid}, ${bean.updateTime}, ${bean.type})</pre>
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * @param bean
   * 	used as updated field and in where condition
   */
  @Override
  public void insert(MessageEntity bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("channel_id", bean.channelId);

    if (bean.ownerType!=null) {
      contentValues.put("owner_type", bean.ownerType.toString());
    } else {
      contentValues.putNull("owner_type");
    }

    if (bean.faceUid!=null) {
      contentValues.put("face_uid", bean.faceUid);
    } else {
      contentValues.putNull("face_uid");
    }

    if (bean.text!=null) {
      contentValues.put("text", bean.text);
    } else {
      contentValues.putNull("text");
    }

    if (bean.ownerUid!=null) {
      contentValues.put("owner_uid", bean.ownerUid);
    } else {
      contentValues.putNull("owner_uid");
    }

    if (bean.channelUid!=null) {
      contentValues.put("channel_uid", bean.channelUid);
    } else {
      contentValues.putNull("channel_uid");
    }

    contentValues.put("update_time", bean.updateTime);

    if (bean.type!=null) {
      contentValues.put("type", bean.type.toString());
    } else {
      contentValues.putNull("type");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO message (channel_id, owner_type, face_uid, text, owner_uid, channel_uid, update_time, type) VALUES ('"+StringUtil.checkSize(contentValues.get("channel_id"))+"', '"+StringUtil.checkSize(contentValues.get("owner_type"))+"', '"+StringUtil.checkSize(contentValues.get("face_uid"))+"', '"+StringUtil.checkSize(contentValues.get("text"))+"', '"+StringUtil.checkSize(contentValues.get("owner_uid"))+"', '"+StringUtil.checkSize(contentValues.get("channel_uid"))+"', '"+StringUtil.checkSize(contentValues.get("update_time"))+"', '"+StringUtil.checkSize(contentValues.get("type"))+"')"));
    long result = database().insert("message", null, contentValues);
    bean.id=result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, channel_id, owner_type, face_uid, text, owner_uid, channel_uid, update_time, type FROM message WHERE uid = ${uid}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[uid]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, channel_id, owner_type, face_uid, text, owner_uid, channel_uid, update_time, type]</pre>
   *
   * @param uid
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public MessageEntity selectByUid(String uid) {
    // build where condition
    String[] args={(uid==null?null:uid)};

    Logger.info(StringUtil.formatSQL("SELECT id, channel_id, owner_type, face_uid, text, owner_uid, channel_uid, update_time, type FROM message WHERE uid = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, channel_id, owner_type, face_uid, text, owner_uid, channel_uid, update_time, type FROM message WHERE uid = ?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    MessageEntity resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("channel_id");
      int index2=cursor.getColumnIndex("owner_type");
      int index3=cursor.getColumnIndex("face_uid");
      int index4=cursor.getColumnIndex("text");
      int index5=cursor.getColumnIndex("owner_uid");
      int index6=cursor.getColumnIndex("channel_uid");
      int index7=cursor.getColumnIndex("update_time");
      int index8=cursor.getColumnIndex("type");

      resultBean=new MessageEntity();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.channelId=cursor.getLong(index1); }
      if (!cursor.isNull(index2)) { resultBean.ownerType=OwnerType.valueOf(cursor.getString(index2)); }
      if (!cursor.isNull(index3)) { resultBean.faceUid=cursor.getString(index3); }
      if (!cursor.isNull(index4)) { resultBean.text=cursor.getString(index4); }
      if (!cursor.isNull(index5)) { resultBean.ownerUid=cursor.getString(index5); }
      if (!cursor.isNull(index6)) { resultBean.channelUid=cursor.getString(index6); }
      if (!cursor.isNull(index7)) { resultBean.updateTime=cursor.getLong(index7); }
      if (!cursor.isNull(index8)) { resultBean.type=MessageType.valueOf(cursor.getString(index8)); }

    }
    cursor.close();

    return resultBean;
  }
}
