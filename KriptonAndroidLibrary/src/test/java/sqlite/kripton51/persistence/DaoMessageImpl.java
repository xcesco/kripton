package sqlite.kripton51.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
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
   * <pre>SELECT id, channel_id, owner_type, uid, face_uid, text, owner_uid, channel_uid, update_time, type FROM message WHERE channel_id = ${channelId}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>channel_id</dt><dd>is associated to bean's property <strong>channelId</strong></dd>
   * 	<dt>owner_type</dt><dd>is associated to bean's property <strong>ownerType</strong></dd>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
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
    StringBuilder _sqlBuilder=new StringBuilder();
    StringBuilder _projectionBuffer=new StringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=new ArrayList<>();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE channelId = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(channelId));
    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql,(Object[])_sqlArgs);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("param (%s): '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<MessageEntity> resultList=new LinkedList<MessageEntity>();
      MessageEntity resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("channel_id");
        int index2=cursor.getColumnIndex("owner_type");
        int index3=cursor.getColumnIndex("uid");
        int index4=cursor.getColumnIndex("face_uid");
        int index5=cursor.getColumnIndex("text");
        int index6=cursor.getColumnIndex("owner_uid");
        int index7=cursor.getColumnIndex("channel_uid");
        int index8=cursor.getColumnIndex("update_time");
        int index9=cursor.getColumnIndex("type");

        do
         {
          resultBean=new MessageEntity();

          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.channelId=cursor.getLong(index1); }
          if (!cursor.isNull(index2)) { resultBean.ownerType=OwnerType.valueOf(cursor.getString(index2)); }
          if (!cursor.isNull(index3)) { resultBean.uid=cursor.getString(index3); }
          if (!cursor.isNull(index4)) { resultBean.faceUid=cursor.getString(index4); }
          if (!cursor.isNull(index5)) { resultBean.text=cursor.getString(index5); }
          if (!cursor.isNull(index6)) { resultBean.ownerUid=cursor.getString(index6); }
          if (!cursor.isNull(index7)) { resultBean.channelUid=cursor.getString(index7); }
          if (!cursor.isNull(index8)) { resultBean.updateTime=cursor.getLong(index8); }
          if (!cursor.isNull(index9)) { resultBean.type=MessageType.valueOf(cursor.getString(index9)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE message SET channel_id=${bean.channelId}, owner_type=${bean.ownerType}, uid=${bean.uid}, face_uid=${bean.faceUid}, text=${bean.text}, owner_uid=${bean.ownerUid}, channel_uid=${bean.channelUid}, update_time=${bean.updateTime}, type=${bean.type} WHERE id = ${bean.id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>channel_id</dt><dd>is mapped to <strong>${bean.channelId}</strong></dd>
   * 	<dt>owner_type</dt><dd>is mapped to <strong>${bean.ownerType}</strong></dd>
   * 	<dt>uid</dt><dd>is mapped to <strong>${bean.uid}</strong></dd>
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

    if (bean.uid!=null) {
      contentValues.put("uid", bean.uid);
    } else {
      contentValues.putNull("uid");
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

    ArrayList<String> _sqlWhereParams=new ArrayList<String>();
    _sqlWhereParams.add(String.valueOf(bean.id));

    StringBuilder _sqlBuilder=new StringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    //StringUtils and SqlUtils will be used to format SQL

    // display log
    Logger.info("UPDATE message SET channelId=:channel_id, ownerType=:owner_type, uid=:uid, faceUid=:face_uid, text=:text, ownerUid=:owner_uid, channelUid=:channel_uid, updateTime=:update_time, type=:type WHERE id = ?");

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("value :%s = <null>", _contentKey);
      } else {
        Logger.info("value :%s = '%s' of type %s", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getName());
      }
    }
    // log for content values -- END

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("param (%s): '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    int result = database().update("message", contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return result!=0;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO message (channel_id, owner_type, uid, face_uid, text, owner_uid, channel_uid, update_time, type) VALUES (${bean.channelId}, ${bean.ownerType}, ${bean.uid}, ${bean.faceUid}, ${bean.text}, ${bean.ownerUid}, ${bean.channelUid}, ${bean.updateTime}, ${bean.type})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>channel_id</dt><dd>is mapped to <strong>${bean.channelId}</strong></dd>
   * 	<dt>owner_type</dt><dd>is mapped to <strong>${bean.ownerType}</strong></dd>
   * 	<dt>uid</dt><dd>is mapped to <strong>${bean.uid}</strong></dd>
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

    if (bean.uid!=null) {
      contentValues.put("uid", bean.uid);
    } else {
      contentValues.putNull("uid");
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
    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info(SqlUtils.formatSQL("INSERT INTO message (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString()));

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("value :%s = <null>", _contentKey);
      } else {
        Logger.info("value :%s = '%s' of type %s", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    long result = database().insert("message", null, contentValues);
    bean.id=result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, channel_id, owner_type, uid, face_uid, text, owner_uid, channel_uid, update_time, type FROM message WHERE uid = ${uid}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>channel_id</dt><dd>is associated to bean's property <strong>channelId</strong></dd>
   * 	<dt>owner_type</dt><dd>is associated to bean's property <strong>ownerType</strong></dd>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
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
    StringBuilder _sqlBuilder=new StringBuilder();
    StringBuilder _projectionBuffer=new StringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=new ArrayList<>();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE uid = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add((uid==null?"":uid));
    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql,(Object[])_sqlArgs);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("param (%s): '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      MessageEntity resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("channel_id");
        int index2=cursor.getColumnIndex("owner_type");
        int index3=cursor.getColumnIndex("uid");
        int index4=cursor.getColumnIndex("face_uid");
        int index5=cursor.getColumnIndex("text");
        int index6=cursor.getColumnIndex("owner_uid");
        int index7=cursor.getColumnIndex("channel_uid");
        int index8=cursor.getColumnIndex("update_time");
        int index9=cursor.getColumnIndex("type");

        resultBean=new MessageEntity();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.channelId=cursor.getLong(index1); }
        if (!cursor.isNull(index2)) { resultBean.ownerType=OwnerType.valueOf(cursor.getString(index2)); }
        if (!cursor.isNull(index3)) { resultBean.uid=cursor.getString(index3); }
        if (!cursor.isNull(index4)) { resultBean.faceUid=cursor.getString(index4); }
        if (!cursor.isNull(index5)) { resultBean.text=cursor.getString(index5); }
        if (!cursor.isNull(index6)) { resultBean.ownerUid=cursor.getString(index6); }
        if (!cursor.isNull(index7)) { resultBean.channelUid=cursor.getString(index7); }
        if (!cursor.isNull(index8)) { resultBean.updateTime=cursor.getLong(index8); }
        if (!cursor.isNull(index9)) { resultBean.type=MessageType.valueOf(cursor.getString(index9)); }

      }
      return resultBean;
    }
  }
}
