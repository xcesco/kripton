package com.abubusoft.kripton.processor.example01;

import android.content.ContentValues;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtil;

/**
 * <p>
 * DAO implementation for entity <code>Channel</code>, based on interface <code>DaoChannel</code>
 * </p>
 *  @see Channel
 *  @see DaoChannel
 *  @see ChannelTable
 */
public class DaoChannelImpl extends AbstractDao implements DaoChannel {
  public DaoChannelImpl(BindDummy01DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Insert SQL:</p>
   * <pre>INSERT INTO channel (owner_uid, id) VALUES (${ownerUid}, ${id})</pre>
   *
   * <p>Insert's parameters are:</p>
   * <ul>
   * 	<li>Method's param <strong>b</strong> is binded to column <strong>owner_uid</strong></li>
   * 	<li>Method's param <strong>azz</strong> is binded to column <strong>id</strong></li>
   * </ul>
   *
   * @param b
   * 	used as inserted field
   * @param azz
   * 	used as inserted field
   * @return id of inserted record
   */
  @Override
  public long insertContact(String b, long azz) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (b!=null) {
      contentValues.put("owner_uid", b);
    } else {
      contentValues.putNull("owner_uid");
    }

    contentValues.put("id", azz);

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO channel (owner_uid, id) VALUES ('"+StringUtil.checkSize(contentValues.get("b"))+"', '"+StringUtil.checkSize(contentValues.get("azz"))+"')"));
    long result = database().insert("channel", null, contentValues);
    return result;
  }

  /**
   * <p>Update SQL:</p>
   * <pre>UPDATE channel SET app=${ownerUid} WHERE id=${test}</pre>
   *
   * @param app
   * 	used as updated field
   * @param id
   * 	used as updated field
   *
   * @return number of updated records
   */
  @Override
  public long updateContact(String app, long id) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (app!=null) {
      contentValues.put("owner_uid", app);
    } else {
      contentValues.putNull("owner_uid");
    }

    String[] whereConditions={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("UPDATE channel SET app='"+StringUtil.checkSize(contentValues.get("owner_uid"))+"' WHERE id=%s"), (Object[])whereConditions);
    int result = database().update("channel", contentValues, "id=?", whereConditions);
    return result;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE channel WHERE ownerUid=${pap}</pre>
   *
   * @param b
   * 	used as updated field
   *
   * @return number of deleted records
   */
  @Override
  public long deleteContact(String b) {
    String[] whereConditions={(b==null?null:b)};

    Logger.info(StringUtil.formatSQL("DELETE channel WHERE ownerUid=%s"), (Object[])whereConditions);
    int result = database().delete("channel", "owner_uid=?", whereConditions);
    return result;
  }
}
