package com.abubusoft.kripton.processor.example01;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.common.StringUtil;

/**
 * <p>
 * DAO implementation for entity <code>Channel</code>, based on interface <code>DaoChannel</code>
 * </p>
 *
 *  @see Channel
 *  @see DaoChannel
 *  @see ChannelTable
 */
public class DaoChannelImpl extends AbstractDao implements DaoChannel {
  public DaoChannelImpl(BindDummy01DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL Update:</p>
   * <pre>UPDATE channel SET uid=${value.uid}, owner_uid=${value.ownerUid}, update_time=${value.updateTime}, name=${value.name} WHERE id=${value.id} and id=${value.id}</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>uid</dt><dd>is mapped to <strong>${value.uid}</strong></dd>
   * 	<dt>owner_uid</dt><dd>is mapped to <strong>${value.ownerUid}</strong></dd>
   * 	<dt>update_time</dt><dd>is mapped to <strong>${value.updateTime}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${value.name}</strong></dd>
   * </dl>
   *
   * <p><strong>Parameters used in where conditions:</strong></p>
   * <dl>
   * 	<dt>${value.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>${value.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${value}
   *
   * @return number of updated records
   */
  @Override
  public int updateContactBean1(Channel bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.getUid()!=null) {
      contentValues.put("uid", bean.getUid());
    } else {
      contentValues.putNull("uid");
    }

    if (bean.getOwnerUid()!=null) {
      contentValues.put("owner_uid", bean.getOwnerUid());
    } else {
      contentValues.putNull("owner_uid");
    }

    contentValues.put("update_time", bean.getUpdateTime());

    if (bean.getName()!=null) {
      contentValues.put("name", bean.getName());
    } else {
      contentValues.putNull("name");
    }

    String[] whereConditions={String.valueOf(bean.getId()), String.valueOf(bean.getId())};

    Logger.info(StringUtil.formatSQL("UPDATE channel SET uid='"+StringUtil.checkSize(contentValues.get("uid"))+"', owner_uid='"+StringUtil.checkSize(contentValues.get("owner_uid"))+"', update_time='"+StringUtil.checkSize(contentValues.get("update_time"))+"', name='"+StringUtil.checkSize(contentValues.get("name"))+"' WHERE id=%s and id=%s"), (Object[])whereConditions);
    int result = database().update("channel", contentValues, "id=? and id=?", whereConditions);
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT count(*) FROM channel WHERE updateTime=${bean.updateTime}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>count(*)</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${bean.updateTime}</dt><dd>is binded to method's parameter <strong>value.updateTime</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as ${bean}
   *
   * @return single value extracted with query.
   */
  @Override
  public long selectBean1(Channel value) {
    // build where condition
    String[] args={String.valueOf(value.getUpdateTime())};

    Logger.info(StringUtil.formatSQL("SELECT count(*) FROM channel WHERE update_time='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT count(*) FROM channel WHERE update_time=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    long result=0L;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0L; }
        result=cursor.getLong(0);
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT update_time FROM channel WHERE updateTime=${bean.updateTime}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${bean.updateTime}</dt><dd>is binded to method's parameter <strong>value.updateTime</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as ${bean}
   * @param listener
   * 	is -----
   */
  @Override
  public void selectBean2(Channel value, OnReadBeanListener<Channel> listener) {
    // build where condition
    String[] args={String.valueOf(value.getUpdateTime())};

    Logger.info(StringUtil.formatSQL("SELECT update_time FROM channel WHERE update_time='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT update_time FROM channel WHERE update_time=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Channel resultBean=new Channel();

    try {
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("update_time");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          resultBean.setUid(null);
          resultBean.setOwnerUid(null);
          resultBean.setUpdateTime(0L);
          resultBean.setName(null);
          resultBean.setId(0L);

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.setUpdateTime(cursor.getLong(index0)); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
  }
}
