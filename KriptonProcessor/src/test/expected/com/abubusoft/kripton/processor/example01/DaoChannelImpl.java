package com.abubusoft.kripton.processor.example01;

import android.content.ContentValues;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
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
   * <p>SQL Update used:</p>
   * <pre>UPDATE channel SET ownerUid=${ownerUid} WHERE id=${id}</pre>
   *
   * <p><strong>Updated fields:</strong></p>
   * <dl>
   * 	<dt>ownerUid</dt><dd>is mapped to parameter <strong>ownerUid</strong></dd>
   * </dl>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param ownerUid
   * 	is used as updated field <strong>ownerUid</strong>
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   *
   * @return number of updated records
   */
  @Override
  public int updateContactRaw4(String ownerUid, long id) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (ownerUid!=null) {
      contentValues.put("owner_uid", ownerUid);
    } else {
      contentValues.putNull("owner_uid");
    }

    String[] whereConditions={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("UPDATE channel SET ownerUid='"+StringUtil.checkSize(contentValues.get("owner_uid"))+"' WHERE id=%s"), (Object[])whereConditions);
    int result = database().update("channel", contentValues, "id=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE channel SET uid=${bean.uid}, owner_uid=${bean.ownerUid}, update_time=${bean.updateTime}, name=${bean.name} WHERE id=${bean.id}</pre>
   *
   * @param bean
   * 	is used as where parameter ${bean}
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

    String[] whereConditions={String.valueOf(bean.getId())};

    Logger.info(StringUtil.formatSQL("UPDATE channel SET uid='"+StringUtil.checkSize(contentValues.get("uid"))+"', owner_uid='"+StringUtil.checkSize(contentValues.get("owner_uid"))+"', update_time='"+StringUtil.checkSize(contentValues.get("update_time"))+"', name='"+StringUtil.checkSize(contentValues.get("name"))+"' WHERE id=%s"), (Object[])whereConditions);
    int result = database().update("channel", contentValues, "id=?", whereConditions);
    return result;
  }

  /**
   * <p>SQL Delete used:</p>
   * <pre>DELETE channel WHERE ownerUid=${channel.id}</pre>
   *
   * @param channel
   * 	is used as where parameter ${channel}
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteContactBean1(Channel channel) {
    String[] whereConditions={String.valueOf(channel.getId())};

    Logger.info(StringUtil.formatSQL("owner_uid=%s"), (Object[])whereConditions);
    int result = database().delete("channel", "owner_uid=?", whereConditions);
    return result!=0;
  }

  /**
   * <p>SQL Delete used:</p>
   * <pre>DELETE channel WHERE ownerUid=${channel.id}</pre>
   *
   * @param value
   * 	is used as where parameter ${channel}
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteContactBean2(Channel value) {
    String[] whereConditions={String.valueOf(value.getId())};

    Logger.info(StringUtil.formatSQL("owner_uid=%s"), (Object[])whereConditions);
    int result = database().delete("channel", "owner_uid=?", whereConditions);
    return result!=0;
  }
}
