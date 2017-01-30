package sqlite.kripton51.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.LinkedList;
import java.util.List;
import sqlite.kripton51.entities.MessageEntity;
import sqlite.kripton51.entities.OwnerType;
import sqlite.kripton51.internal.MessageType;

/**
 * <p>
 * DAO implementation for entity <code>MessageEntity</code>, based on interface <code>DaoMessage</code>
 * </p>
 *
 *  @see MessageEntity
 *  @see DaoMessage
 *  @see sqlite.kripton51.entities.MessageEntityTable
 */
public class DaoMessageImpl extends AbstractDao implements DaoMessage {
  public DaoMessageImpl(BindWhisperDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, channel_id, owner_type, face_uid, text, owner_uid, channel_uid, update_time, type FROM message WHERE channelId = ${channelId}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>channel_id</dt><dd>is associated to bean's property <strong>channelId</strong></dd>
   * 	<dt>owner_type</dt><dd>is associated to bean's property <strong>ownerType</strong></dd>
   * 	<dt>face_uid</dt><dd>is associated to bean's property <strong>faceUid</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
   * 	<dt>owner_uid</dt><dd>is associated to bean's property <strong>ownerUid</strong></dd>
   * 	<dt>channel_uid</dt><dd>is associated to bean's property <strong>channelUid</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${channelId}</dt><dd>is binded to method's parameter <strong>channelId</strong></dd>
   * </dl>
   *
   * @param channelId
   * 	is binded to <code>${channelId}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<MessageEntity> selectByChannel(long channelId) {
    // build where condition
    String[] args={String.valueOf(channelId)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, channel_id, owner_type, face_uid, text, owner_uid, channel_uid, update_time, type FROM message WHERE channel_id = '%s'",(Object[])args));
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
   * <h2>SQL update:</h2>
   * <pre>UPDATE message SET channel_id=${bean.channelId}, owner_type=${bean.ownerType}, face_uid=${bean.faceUid}, text=${bean.text}, owner_uid=${bean.ownerUid}, channel_uid=${bean.channelUid}, update_time=${bean.updateTime}, type=${bean.type} WHERE id = ${bean.id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>channel_id</dt><dd>is mapped to <strong>${bean.channelId}</strong></dd>
   * 	<dt>owner_type</dt><dd>is mapped to <strong>${bean.ownerType}</strong></dd>
   * 	<dt>face_uid</dt><dd>is mapped to <strong>${bean.faceUid}</strong></dd>
   * 	<dt>text</dt><dd>is mapped to <strong>${bean.text}</strong></dd>
   * 	<dt>owner_uid</dt><dd>is mapped to <strong>${bean.ownerUid}</strong></dd>
   * 	<dt>channel_uid</dt><dd>is mapped to <strong>${bean.channelUid}</strong></dd>
   * 	<dt>update_time</dt><dd>is mapped to <strong>${bean.updateTime}</strong></dd>
   * 	<dt>type</dt><dd>is mapped to <strong>${bean.type}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return <code>true</code> if record is updated, <code>false</code> otherwise
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

    String[] whereConditionsArray={String.valueOf(bean.id)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE message SET channel_id='"+StringUtils.checkSize(contentValues.get("channel_id"))+"', owner_type='"+StringUtils.checkSize(contentValues.get("owner_type"))+"', face_uid='"+StringUtils.checkSize(contentValues.get("face_uid"))+"', text='"+StringUtils.checkSize(contentValues.get("text"))+"', owner_uid='"+StringUtils.checkSize(contentValues.get("owner_uid"))+"', channel_uid='"+StringUtils.checkSize(contentValues.get("channel_uid"))+"', update_time='"+StringUtils.checkSize(contentValues.get("update_time"))+"', type='"+StringUtils.checkSize(contentValues.get("type"))+"' WHERE id = '%s'", (Object[]) whereConditionsArray));
    int result = database().update("message", contentValues, "UPDATE message SET channel_id='"+StringUtils.checkSize(contentValues.get("channel_id"))+"', owner_type='"+StringUtils.checkSize(contentValues.get("owner_type"))+"', face_uid='"+StringUtils.checkSize(contentValues.get("face_uid"))+"', text='"+StringUtils.checkSize(contentValues.get("text"))+"', owner_uid='"+StringUtils.checkSize(contentValues.get("owner_uid"))+"', channel_uid='"+StringUtils.checkSize(contentValues.get("channel_uid"))+"', update_time='"+StringUtils.checkSize(contentValues.get("update_time"))+"', type='"+StringUtils.checkSize(contentValues.get("type"))+"' WHERE id = '%s'", whereConditionsArray);
    return result!=0;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO message (channel_id, owner_type, face_uid, text, owner_uid, channel_uid, update_time, type) VALUES (${bean.channelId}, ${bean.ownerType}, ${bean.faceUid}, ${bean.text}, ${bean.ownerUid}, ${bean.channelUid}, ${bean.updateTime}, ${bean.type})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>channel_id</dt><dd>is mapped to <strong>${bean.channelId}</strong></dd>
   * 	<dt>owner_type</dt><dd>is mapped to <strong>${bean.ownerType}</strong></dd>
   * 	<dt>face_uid</dt><dd>is mapped to <strong>${bean.faceUid}</strong></dd>
   * 	<dt>text</dt><dd>is mapped to <strong>${bean.text}</strong></dd>
   * 	<dt>owner_uid</dt><dd>is mapped to <strong>${bean.ownerUid}</strong></dd>
   * 	<dt>channel_uid</dt><dd>is mapped to <strong>${bean.channelUid}</strong></dd>
   * 	<dt>update_time</dt><dd>is mapped to <strong>${bean.updateTime}</strong></dd>
   * 	<dt>type</dt><dd>is mapped to <strong>${bean.type}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   *
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

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO message (channel_id, owner_type, face_uid, text, owner_uid, channel_uid, update_time, type) VALUES ('"+StringUtils.checkSize(contentValues.get("channel_id"))+"', '"+StringUtils.checkSize(contentValues.get("owner_type"))+"', '"+StringUtils.checkSize(contentValues.get("face_uid"))+"', '"+StringUtils.checkSize(contentValues.get("text"))+"', '"+StringUtils.checkSize(contentValues.get("owner_uid"))+"', '"+StringUtils.checkSize(contentValues.get("channel_uid"))+"', '"+StringUtils.checkSize(contentValues.get("update_time"))+"', '"+StringUtils.checkSize(contentValues.get("type"))+"')"));
    long result = database().insert("message", null, contentValues);
    bean.id=result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, channel_id, owner_type, face_uid, text, owner_uid, channel_uid, update_time, type FROM message WHERE uid = ${uid}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>channel_id</dt><dd>is associated to bean's property <strong>channelId</strong></dd>
   * 	<dt>owner_type</dt><dd>is associated to bean's property <strong>ownerType</strong></dd>
   * 	<dt>face_uid</dt><dd>is associated to bean's property <strong>faceUid</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
   * 	<dt>owner_uid</dt><dd>is associated to bean's property <strong>ownerUid</strong></dd>
   * 	<dt>channel_uid</dt><dd>is associated to bean's property <strong>channelUid</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${uid}</dt><dd>is binded to method's parameter <strong>uid</strong></dd>
   * </dl>
   *
   * @param uid
   * 	is binded to <code>${uid}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public MessageEntity selectByUid(String uid) {
    // build where condition
    String[] args={(uid==null?"":uid)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, channel_id, owner_type, face_uid, text, owner_uid, channel_uid, update_time, type FROM message WHERE uid = '%s'",(Object[])args));
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
