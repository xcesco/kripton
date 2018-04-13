package sqlite.kripton51.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.EnumUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
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
public class DaoMessageImpl extends Dao implements DaoMessage {
  private static final String SELECT_BY_CHANNEL_SQL1 = "SELECT id, channel_id, owner_type, uid, face_uid, text, owner_uid, channel_uid, update_time, type FROM message WHERE channel_id = ?";

  private static SQLiteStatement updateByIdPreparedStatement0;

  private static SQLiteStatement insertPreparedStatement1;

  private static final String SELECT_BY_UID_SQL2 = "SELECT id, channel_id, owner_type, uid, face_uid, text, owner_uid, channel_uid, update_time, type FROM message WHERE uid = ?";

  public DaoMessageImpl(BindWhisperDaoFactory daoFactory) {
    super(daoFactory.context());
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
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_CHANNEL_SQL1;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(channelId));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END

      ArrayList<MessageEntity> resultList=new ArrayList<MessageEntity>(_cursor.getCount());
      MessageEntity resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("channel_id");
        int index2=_cursor.getColumnIndex("owner_type");
        int index3=_cursor.getColumnIndex("uid");
        int index4=_cursor.getColumnIndex("face_uid");
        int index5=_cursor.getColumnIndex("text");
        int index6=_cursor.getColumnIndex("owner_uid");
        int index7=_cursor.getColumnIndex("channel_uid");
        int index8=_cursor.getColumnIndex("update_time");
        int index9=_cursor.getColumnIndex("type");

        do
         {
          resultBean=new MessageEntity();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.channelId=_cursor.getLong(index1); }
          if (!_cursor.isNull(index2)) { resultBean.ownerType=OwnerType.valueOf(_cursor.getString(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.uid=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { resultBean.faceUid=_cursor.getString(index4); }
          if (!_cursor.isNull(index5)) { resultBean.text=_cursor.getString(index5); }
          if (!_cursor.isNull(index6)) { resultBean.ownerUid=_cursor.getString(index6); }
          if (!_cursor.isNull(index7)) { resultBean.channelUid=_cursor.getString(index7); }
          if (!_cursor.isNull(index8)) { resultBean.updateTime=_cursor.getLong(index8); }
          if (!_cursor.isNull(index9)) { resultBean.type=MessageType.valueOf(_cursor.getString(index9)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE message SET channel_id=:channelId, owner_type=:ownerType, uid=:uid, face_uid=:faceUid, text=:text, owner_uid=:ownerUid, channel_uid=:channelUid, update_time=:updateTime, type=:type WHERE id = ${bean.id}</pre>
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
    if (updateByIdPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="UPDATE message SET channel_id=?, owner_type=?, uid=?, face_uid=?, text=?, owner_uid=?, channel_uid=?, update_time=?, type=? WHERE id = ?";
      updateByIdPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateByIdPreparedStatement0);
    _contentValues.put("channel_id", bean.channelId);
    _contentValues.put("owner_type", EnumUtils.write(bean.ownerType));
    _contentValues.put("uid", bean.uid);
    _contentValues.put("face_uid", bean.faceUid);
    _contentValues.put("text", bean.text);
    _contentValues.put("owner_uid", bean.ownerUid);
    _contentValues.put("channel_uid", bean.channelUid);
    _contentValues.put("update_time", bean.updateTime);
    _contentValues.put("type", EnumUtils.write(bean.type));

    _contentValues.addWhereArgs(String.valueOf(bean.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE message SET channel_id=:channel_id, owner_type=:owner_type, uid=:uid, face_uid=:face_uid, text=:text, owner_uid=:owner_uid, channel_uid=:channel_uid, update_time=:update_time, type=:type WHERE id = ?");

      // log for content values -- BEGIN
      Triple<String, Object, KriptonContentValues.ParamType> _contentValue;
      for (int i = 0; i < _contentValues.size(); i++) {
        _contentValue = _contentValues.get(i);
        if (_contentValue.value1==null) {
          Logger.info("==> :%s = <null>", _contentValue.value0);
        } else {
          Logger.info("==> :%s = '%s' (%s)", _contentValue.value0, StringUtils.checkSize(_contentValue.value1), _contentValue.value1.getClass().getCanonicalName());
        }
      }
      // log for content values -- END

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(updateByIdPreparedStatement0, _contentValues);
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
   */
  @Override
  public void insert(MessageEntity bean) {
    if (insertPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO message (channel_id, owner_type, uid, face_uid, text, owner_uid, channel_uid, update_time, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
      insertPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement1);
    _contentValues.put("channel_id", bean.channelId);
    _contentValues.put("owner_type", EnumUtils.write(bean.ownerType));
    _contentValues.put("uid", bean.uid);
    _contentValues.put("face_uid", bean.faceUid);
    _contentValues.put("text", bean.text);
    _contentValues.put("owner_uid", bean.ownerUid);
    _contentValues.put("channel_uid", bean.channelUid);
    _contentValues.put("update_time", bean.updateTime);
    _contentValues.put("type", EnumUtils.write(bean.type));

    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO message (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

      // log for content values -- BEGIN
      Triple<String, Object, KriptonContentValues.ParamType> _contentValue;
      for (int i = 0; i < _contentValues.size(); i++) {
        _contentValue = _contentValues.get(i);
        if (_contentValue.value1==null) {
          Logger.info("==> :%s = <null>", _contentValue.value0);
        } else {
          Logger.info("==> :%s = '%s' (%s)", _contentValue.value0, StringUtils.checkSize(_contentValue.value1), _contentValue.value1.getClass().getCanonicalName());
        }
      }
      // log for content values -- END
      // log for insert -- END 


      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    // insert operation
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement1, _contentValues);
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
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_UID_SQL2;
    // add where arguments
    _contentValues.addWhereArgs((uid==null?"":uid));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END

      MessageEntity resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("channel_id");
        int index2=_cursor.getColumnIndex("owner_type");
        int index3=_cursor.getColumnIndex("uid");
        int index4=_cursor.getColumnIndex("face_uid");
        int index5=_cursor.getColumnIndex("text");
        int index6=_cursor.getColumnIndex("owner_uid");
        int index7=_cursor.getColumnIndex("channel_uid");
        int index8=_cursor.getColumnIndex("update_time");
        int index9=_cursor.getColumnIndex("type");

        resultBean=new MessageEntity();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.channelId=_cursor.getLong(index1); }
        if (!_cursor.isNull(index2)) { resultBean.ownerType=OwnerType.valueOf(_cursor.getString(index2)); }
        if (!_cursor.isNull(index3)) { resultBean.uid=_cursor.getString(index3); }
        if (!_cursor.isNull(index4)) { resultBean.faceUid=_cursor.getString(index4); }
        if (!_cursor.isNull(index5)) { resultBean.text=_cursor.getString(index5); }
        if (!_cursor.isNull(index6)) { resultBean.ownerUid=_cursor.getString(index6); }
        if (!_cursor.isNull(index7)) { resultBean.channelUid=_cursor.getString(index7); }
        if (!_cursor.isNull(index8)) { resultBean.updateTime=_cursor.getLong(index8); }
        if (!_cursor.isNull(index9)) { resultBean.type=MessageType.valueOf(_cursor.getString(index9)); }

      }
      return resultBean;
    }
  }

  public static void clearCompiledStatements() {
    if (updateByIdPreparedStatement0!=null) {
      updateByIdPreparedStatement0.close();
      updateByIdPreparedStatement0=null;
    }
    if (insertPreparedStatement1!=null) {
      insertPreparedStatement1.close();
      insertPreparedStatement1=null;
    }
  }
}
