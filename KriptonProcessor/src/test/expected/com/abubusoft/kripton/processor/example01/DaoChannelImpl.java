package com.abubusoft.kripton.processor.example01;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtil;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Channel</code>, based on interface <code>DaoChannel</code>
 * </p>
 *  @see Channel
 *  @see DaoChannel
 *  @see Channel$Table
 */
public class DaoChannelImpl extends AbstractDao implements DaoChannel {
  public DaoChannelImpl(BindDummy01DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO channel (owner_uid) VALUES (${ownerUid})</pre>
   *
   * @param ownerUid
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertContact(String ownerUid) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (ownerUid!=null) {
      contentValues.put("owner_uid", ownerUid);
    } else {
      contentValues.putNull("owner_uid");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO channel (owner_uid) VALUES ('"+StringUtil.checkSize(contentValues.get("owner_uid"))+"')"));
    long result = database().insert("channel", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT uid, owner_uid, update_time, name, id FROM channel WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[uid, owner_uid, update_time, name, id]</pre>
   *
   *
   * @return list of bean or empty list.
   */
  @Override
  public List<Channel> selectAll() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT uid, owner_uid, update_time, name, id FROM channel WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT uid, owner_uid, update_time, name, id FROM channel WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Channel> resultList=new LinkedList<Channel>();
    Channel resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("uid");
      int index1=cursor.getColumnIndex("owner_uid");
      int index2=cursor.getColumnIndex("update_time");
      int index3=cursor.getColumnIndex("name");
      int index4=cursor.getColumnIndex("id");

      do
       {
        resultBean=new Channel();

        if (!cursor.isNull(index0)) { resultBean.setUid(cursor.getString(index0)); }
        if (!cursor.isNull(index1)) { resultBean.setOwnerUid(cursor.getString(index1)); }
        if (!cursor.isNull(index2)) { resultBean.setUpdateTime(cursor.getLong(index2)); }
        if (!cursor.isNull(index3)) { resultBean.setName(cursor.getString(index3)); }
        if (!cursor.isNull(index4)) { resultBean.setId(cursor.getLong(index4)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT uid, owner_uid, update_time, name, id FROM channel WHERE updateTime=${updateTimeA}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[updateTimeA]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[uid, owner_uid, update_time, name, id]</pre>
   *
   * @param updateTimeA
   *
   * @return list of bean or empty list.
   */
  @Override
  public List<Channel> select(long updateTimeA) {
    // build where condition
    String[] args={String.valueOf(updateTimeA)};

    Logger.info(StringUtil.formatSQL("SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Channel> resultList=new LinkedList<Channel>();
    Channel resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("uid");
      int index1=cursor.getColumnIndex("owner_uid");
      int index2=cursor.getColumnIndex("update_time");
      int index3=cursor.getColumnIndex("name");
      int index4=cursor.getColumnIndex("id");

      do
       {
        resultBean=new Channel();

        if (!cursor.isNull(index0)) { resultBean.setUid(cursor.getString(index0)); }
        if (!cursor.isNull(index1)) { resultBean.setOwnerUid(cursor.getString(index1)); }
        if (!cursor.isNull(index2)) { resultBean.setUpdateTime(cursor.getLong(index2)); }
        if (!cursor.isNull(index3)) { resultBean.setName(cursor.getString(index3)); }
        if (!cursor.isNull(index4)) { resultBean.setId(cursor.getLong(index4)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT uid, owner_uid, update_time, name, id FROM channel WHERE updateTime=${channel.updateTime}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[channel.updateTime]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[uid, owner_uid, update_time, name, id]</pre>
   *
   * @param channel
   *
   * @return list of bean or empty list.
   */
  @Override
  public List<Channel> select(Channel channel) {
    // build where condition
    String[] args={String.valueOf(channel.getUpdateTime())};

    Logger.info(StringUtil.formatSQL("SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Channel> resultList=new LinkedList<Channel>();
    Channel resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("uid");
      int index1=cursor.getColumnIndex("owner_uid");
      int index2=cursor.getColumnIndex("update_time");
      int index3=cursor.getColumnIndex("name");
      int index4=cursor.getColumnIndex("id");

      do
       {
        resultBean=new Channel();

        if (!cursor.isNull(index0)) { resultBean.setUid(cursor.getString(index0)); }
        if (!cursor.isNull(index1)) { resultBean.setOwnerUid(cursor.getString(index1)); }
        if (!cursor.isNull(index2)) { resultBean.setUpdateTime(cursor.getLong(index2)); }
        if (!cursor.isNull(index3)) { resultBean.setName(cursor.getString(index3)); }
        if (!cursor.isNull(index4)) { resultBean.setId(cursor.getLong(index4)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT uid, owner_uid, update_time, name, id FROM channel WHERE updateTime=${channel.updateTime}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[channel.updateTime]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[uid, owner_uid, update_time, name, id]</pre>
   *
   * @param channel
   *
   * @return cursor. Closing the cursor is delegated to the calling code.
   */
  @Override
  public Cursor selectCursor(Channel channel) {
    // build where condition
    String[] args={String.valueOf(channel.getUpdateTime())};

    Logger.info(StringUtil.formatSQL("SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    return cursor;
  }
}
