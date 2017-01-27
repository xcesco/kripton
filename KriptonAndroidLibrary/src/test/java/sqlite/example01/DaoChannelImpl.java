package sqlite.example01;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
   * <p>SQL delete:</p>
   * <pre>DELETE channel WHERE ownerUid=${value.id}</pre>
   *
   * <p><strong>Parameters used in where conditions:</strong></p>
   * <dl>
   * 	<dt>${value.id}</dt><dd>is mapped to method's parameter <strong>channel.id</strong></dd>
   * </dl>
   *
   * @param channel
   * 	is used as ${value}
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteContactBean1(Channel channel) {
    String[] whereConditionsArray={String.valueOf(channel.getId())};

    Logger.info(StringUtils.formatSQL("DELETE channel WHERE ownerUid=%s ", (Object[]) whereConditionsArray));
    int result = database().delete("channel", "owner_uid=?", whereConditionsArray);
    return result!=0;
  }

  /**
   * <p>SQL delete:</p>
   * <pre>DELETE channel WHERE ownerUid=${value.id}</pre>
   *
   * <p><strong>Parameters used in where conditions:</strong></p>
   * <dl>
   * 	<dt>${value.id}</dt><dd>is mapped to method's parameter <strong>value.id</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as ${value}
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteContactBean2(Channel value) {
    String[] whereConditionsArray={String.valueOf(value.getId())};

    Logger.info(StringUtils.formatSQL("DELETE channel WHERE ownerUid=%s ", (Object[]) whereConditionsArray));
    int result = database().delete("channel", "owner_uid=?", whereConditionsArray);
    return result!=0;
  }

  /**
   * <p>SQL delete:</p>
   * <pre>DELETE channel WHERE ownerUid=${ownerUid} and id=${id}</pre>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${ownerUid}</dt><dd>is mapped to method's parameter <strong>b</strong></dd>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>dummy</strong></dd>
   * </dl>
   *
   * @param b
   * 	is used as where parameter <strong>${ownerUid}</strong>
   * @param dummy
   * 	is used as where parameter <strong>${id}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteContactRaw1(String b, long dummy) {
    String[] whereConditionsArray={(b==null?"":b), String.valueOf(dummy)};

    Logger.info(StringUtils.formatSQL("DELETE channel WHERE ownerUid=%s and id=%s", (Object[])whereConditionsArray));
    int result = database().delete("channel", "owner_uid=? and id=?", whereConditionsArray);
    return result;
  }

  /**
   * <p>SQL delete:</p>
   * <pre>DELETE channel WHERE ownerUid=${ownerUid} and id=${id}</pre>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${ownerUid}</dt><dd>is mapped to method's parameter <strong>ownerUid</strong></dd>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param ownerUid
   * 	is used as where parameter <strong>${ownerUid}</strong>
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteContactRaw2(String ownerUid, long id) {
    String[] whereConditionsArray={(ownerUid==null?"":ownerUid), String.valueOf(id)};

    Logger.info(StringUtils.formatSQL("DELETE channel WHERE ownerUid=%s and id=%s", (Object[])whereConditionsArray));
    int result = database().delete("channel", "owner_uid=? and id=?", whereConditionsArray);
    return result!=0;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO channel (owner_uid, id) VALUES (${ownerUid}, ${id})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>owner_uid</dt><dd>is binded to query's parameter <strong>${ownerUid}</strong> and method's parameter <strong>b</strong></dd>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>azz</strong></dd>
   * </dl>
   *
   * @param b
   * 	is binded to column <strong>owner_uid</strong>
   * @param azz
   * 	is binded to column <strong>id</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertRaw1(String b, long azz) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (b!=null) {
      contentValues.put("owner_uid", b);
    } else {
      contentValues.putNull("owner_uid");
    }

    contentValues.put("id", azz);

    // log
    Logger.info(StringUtils.formatSQL("INSERT INTO channel (owner_uid, id) VALUES ('"+StringUtils.checkSize(contentValues.get("owner_uid"))+"', '"+StringUtils.checkSize(contentValues.get("id"))+"')"));
    long result = database().insert("channel", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO channel (owner_uid, id) VALUES (${ownerUid}, ${id})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>owner_uid</dt><dd>is binded to query's parameter <strong>${ownerUid}</strong> and method's parameter <strong>b</strong></dd>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param b
   * 	is binded to column <strong>owner_uid</strong>
   * @param id
   * 	is binded to column <strong>id</strong>
   *
   * @return <code>true</code> if record is inserted, <code>false</code> otherwise
   */
  @Override
  public boolean insertRaw2(String b, long id) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (b!=null) {
      contentValues.put("owner_uid", b);
    } else {
      contentValues.putNull("owner_uid");
    }

    contentValues.put("id", id);

    // log
    Logger.info(StringUtils.formatSQL("INSERT INTO channel (owner_uid, id) VALUES ('"+StringUtils.checkSize(contentValues.get("owner_uid"))+"', '"+StringUtils.checkSize(contentValues.get("id"))+"')"));
    long result = database().insert("channel", null, contentValues);
    return result!=-1;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO channel (owner_uid, id) VALUES (${ownerUid}, ${id})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>owner_uid</dt><dd>is binded to query's parameter <strong>${ownerUid}</strong> and method's parameter <strong>ownerUid</strong></dd>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param ownerUid
   * 	is binded to column <strong>owner_uid</strong>
   * @param id
   * 	is binded to column <strong>id</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insertRaw3(String ownerUid, long id) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (ownerUid!=null) {
      contentValues.put("owner_uid", ownerUid);
    } else {
      contentValues.putNull("owner_uid");
    }

    contentValues.put("id", id);

    // log
    Logger.info(StringUtils.formatSQL("INSERT INTO channel (owner_uid, id) VALUES ('"+StringUtils.checkSize(contentValues.get("owner_uid"))+"', '"+StringUtils.checkSize(contentValues.get("id"))+"')"));
    int result = (int)database().insert("channel", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO channel (uid, owner_uid, update_time, name) VALUES (${bean.uid}, ${bean.ownerUid}, ${bean.updateTime}, ${bean.name})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>uid</dt><dd>is mapped to <strong>${bean.uid}</strong></dd>
   * 	<dt>owner_uid</dt><dd>is mapped to <strong>${bean.ownerUid}</strong></dd>
   * 	<dt>update_time</dt><dd>is mapped to <strong>${bean.updateTime}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insertBean1(Channel bean) {
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

    // log
    Logger.info(StringUtils.formatSQL("INSERT INTO channel (uid, owner_uid, update_time, name) VALUES ('"+StringUtils.checkSize(contentValues.get("uid"))+"', '"+StringUtils.checkSize(contentValues.get("owner_uid"))+"', '"+StringUtils.checkSize(contentValues.get("update_time"))+"', '"+StringUtils.checkSize(contentValues.get("name"))+"')"));
    long result = database().insert("channel", null, contentValues);
    bean.setId(result);

    return (int)result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO channel (uid, owner_uid, update_time, name) VALUES (${arg.uid}, ${arg.ownerUid}, ${arg.updateTime}, ${arg.name})</pre>
   *
   * <p><code>arg.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>uid</dt><dd>is mapped to <strong>${arg.uid}</strong></dd>
   * 	<dt>owner_uid</dt><dd>is mapped to <strong>${arg.ownerUid}</strong></dd>
   * 	<dt>update_time</dt><dd>is mapped to <strong>${arg.updateTime}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${arg.name}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>arg</strong>
   *
   * @return <code>true</code> if record is inserted, <code>false</code> otherwise
   */
  @Override
  public boolean insertBean2(Channel bean) {
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

    // log
    Logger.info(StringUtils.formatSQL("INSERT INTO channel (uid, owner_uid, update_time, name) VALUES ('"+StringUtils.checkSize(contentValues.get("uid"))+"', '"+StringUtils.checkSize(contentValues.get("owner_uid"))+"', '"+StringUtils.checkSize(contentValues.get("update_time"))+"', '"+StringUtils.checkSize(contentValues.get("name"))+"')"));
    long result = database().insert("channel", null, contentValues);
    bean.setId(result);

    return result!=-1;
  }

  /**
   * <p>SQL update:</p>
   * <pre>UPDATE channel SET id=${id} WHERE id=${dummy}</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>glu</strong></dd>
   * </dl>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${dummy}</dt><dd>is mapped to method's parameter <strong>aid</strong></dd>
   * </dl>
   *
   * @param glu
   * 	is used as updated field <strong>id</strong>
   * @param aid
   * 	is used as where parameter <strong>${dummy}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateContactRaw1(long glu, long aid) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", glu);

    String[] whereConditionsArray={String.valueOf(aid)};

    Logger.info(StringUtils.formatSQL("UPDATE channel SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE id=%s", (Object[])whereConditionsArray));
    int result = database().update("channel", contentValues, "id=?", whereConditionsArray);
    return result;
  }

  /**
   * <p>SQL update:</p>
   * <pre>UPDATE channel SET id=${id} WHERE id=${dummy}</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${dummy}</dt><dd>is mapped to method's parameter <strong>dummy</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param dummy
   * 	is used as where parameter <strong>${dummy}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateContactRaw2(long id, long dummy) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={String.valueOf(dummy)};

    Logger.info(StringUtils.formatSQL("UPDATE channel SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE id=%s", (Object[])whereConditionsArray));
    int result = database().update("channel", contentValues, "id=?", whereConditionsArray);
    return result;
  }

  /**
   * <p>SQL update:</p>
   * <pre>UPDATE channel SET ownerUid=${ownerUid} WHERE id=${test}</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>owner_uid</dt><dd>is binded to query's parameter <strong>${ownerUid}</strong> and method's parameter <strong>app</strong></dd>
   * </dl>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${test}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param app
   * 	is used as updated field <strong>ownerUid</strong>
   * @param id
   * 	is used as where parameter <strong>${test}</strong>
   *
   * @return <code>true</code> if record is updated, <code>false</code> otherwise
   */
  @Override
  public boolean updateContactRaw3(String app, long id) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (app!=null) {
      contentValues.put("owner_uid", app);
    } else {
      contentValues.putNull("owner_uid");
    }

    String[] whereConditionsArray={String.valueOf(id)};

    Logger.info(StringUtils.formatSQL("UPDATE channel SET ownerUid='"+StringUtils.checkSize(contentValues.get("owner_uid"))+"' WHERE id=%s", (Object[])whereConditionsArray));
    int result = database().update("channel", contentValues, "id=?", whereConditionsArray);
    return result!=0;
  }

  /**
   * <p>SQL update:</p>
   * <pre>UPDATE channel SET ownerUid=${ownerUid} WHERE id=${id}</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>owner_uid</dt><dd>is binded to query's parameter <strong>${ownerUid}</strong> and method's parameter <strong>ownerUid</strong></dd>
   * </dl>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
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

    String[] whereConditionsArray={String.valueOf(id)};

    Logger.info(StringUtils.formatSQL("UPDATE channel SET ownerUid='"+StringUtils.checkSize(contentValues.get("owner_uid"))+"' WHERE id=%s", (Object[])whereConditionsArray));
    int result = database().update("channel", contentValues, "id=?", whereConditionsArray);
    return result;
  }

  /**
   * <p>SQL Update:</p>
   * <pre>UPDATE channel SET uid=${bean.uid}, owner_uid=${bean.ownerUid}, update_time=${bean.updateTime}, name=${bean.name} WHERE id=${bean.id}</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>uid</dt><dd>is mapped to <strong>${bean.uid}</strong></dd>
   * 	<dt>owner_uid</dt><dd>is mapped to <strong>${bean.ownerUid}</strong></dd>
   * 	<dt>update_time</dt><dd>is mapped to <strong>${bean.updateTime}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * </dl>
   *
   * <p><strong>Parameters used in where conditions:</strong></p>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>value.id</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as ${bean}
   *
   * @return number of updated records
   */
  @Override
  public int updateContactBean1(Channel value) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (value.getUid()!=null) {
      contentValues.put("uid", value.getUid());
    } else {
      contentValues.putNull("uid");
    }

    if (value.getOwnerUid()!=null) {
      contentValues.put("owner_uid", value.getOwnerUid());
    } else {
      contentValues.putNull("owner_uid");
    }

    contentValues.put("update_time", value.getUpdateTime());

    if (value.getName()!=null) {
      contentValues.put("name", value.getName());
    } else {
      contentValues.putNull("name");
    }

    String[] whereConditionsArray={String.valueOf(value.getId())};

    Logger.info(StringUtils.formatSQL("UPDATE channel SET uid='"+StringUtils.checkSize(contentValues.get("uid"))+"', owner_uid='"+StringUtils.checkSize(contentValues.get("owner_uid"))+"', update_time='"+StringUtils.checkSize(contentValues.get("update_time"))+"', name='"+StringUtils.checkSize(contentValues.get("name"))+"' WHERE id='%s'", (Object[]) whereConditionsArray));
    int result = database().update("channel", contentValues, "UPDATE channel SET uid='"+StringUtils.checkSize(contentValues.get("uid"))+"', owner_uid='"+StringUtils.checkSize(contentValues.get("owner_uid"))+"', update_time='"+StringUtils.checkSize(contentValues.get("update_time"))+"', name='"+StringUtils.checkSize(contentValues.get("name"))+"' WHERE id='%s'", whereConditionsArray);
    return result;
  }

  /**
   * <p>SQL Update:</p>
   * <pre>UPDATE channel SET uid=${bean.uid}, owner_uid=${bean.ownerUid}, update_time=${bean.updateTime}, name=${bean.name} WHERE id=${bean.id}</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>uid</dt><dd>is mapped to <strong>${bean.uid}</strong></dd>
   * 	<dt>owner_uid</dt><dd>is mapped to <strong>${bean.ownerUid}</strong></dd>
   * 	<dt>update_time</dt><dd>is mapped to <strong>${bean.updateTime}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * </dl>
   *
   * <p><strong>Parameters used in where conditions:</strong></p>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of updated records
   */
  @Override
  public long updateContactBean2(Channel bean) {
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

    String[] whereConditionsArray={String.valueOf(bean.getId())};

    Logger.info(StringUtils.formatSQL("UPDATE channel SET uid='"+StringUtils.checkSize(contentValues.get("uid"))+"', owner_uid='"+StringUtils.checkSize(contentValues.get("owner_uid"))+"', update_time='"+StringUtils.checkSize(contentValues.get("update_time"))+"', name='"+StringUtils.checkSize(contentValues.get("name"))+"' WHERE id='%s'", (Object[]) whereConditionsArray));
    int result = database().update("channel", contentValues, "UPDATE channel SET uid='"+StringUtils.checkSize(contentValues.get("uid"))+"', owner_uid='"+StringUtils.checkSize(contentValues.get("owner_uid"))+"', update_time='"+StringUtils.checkSize(contentValues.get("update_time"))+"', name='"+StringUtils.checkSize(contentValues.get("name"))+"' WHERE id='%s'", whereConditionsArray);
    return result;
  }

  /**
   * <p>SQL Update:</p>
   * <pre>UPDATE channel SET uid=${bean.uid}, owner_uid=${bean.ownerUid}, update_time=${bean.updateTime}, name=${bean.name} WHERE id=${bean.id}</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>uid</dt><dd>is mapped to <strong>${bean.uid}</strong></dd>
   * 	<dt>owner_uid</dt><dd>is mapped to <strong>${bean.ownerUid}</strong></dd>
   * 	<dt>update_time</dt><dd>is mapped to <strong>${bean.updateTime}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * </dl>
   *
   * <p><strong>Parameters used in where conditions:</strong></p>
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
  public boolean updateContactBean3(Channel bean) {
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

    String[] whereConditionsArray={String.valueOf(bean.getId())};

    Logger.info(StringUtils.formatSQL("UPDATE channel SET uid='"+StringUtils.checkSize(contentValues.get("uid"))+"', owner_uid='"+StringUtils.checkSize(contentValues.get("owner_uid"))+"', update_time='"+StringUtils.checkSize(contentValues.get("update_time"))+"', name='"+StringUtils.checkSize(contentValues.get("name"))+"' WHERE id='%s'", (Object[]) whereConditionsArray));
    int result = database().update("channel", contentValues, "UPDATE channel SET uid='"+StringUtils.checkSize(contentValues.get("uid"))+"', owner_uid='"+StringUtils.checkSize(contentValues.get("owner_uid"))+"', update_time='"+StringUtils.checkSize(contentValues.get("update_time"))+"', name='"+StringUtils.checkSize(contentValues.get("name"))+"' WHERE id='%s'", whereConditionsArray);
    return result!=0;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT uid, owner_uid, update_time, name, id FROM channel</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>owner_uid</dt><dd>is associated to bean's property <strong>ownerUid</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Channel> selectAll() {
    // build where condition
    String[] args={};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT uid, owner_uid, update_time, name, id FROM channel",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT uid, owner_uid, update_time, name, id FROM channel", args);
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
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT uid, owner_uid, update_time, name, id FROM channel WHERE updateTime=${a}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>owner_uid</dt><dd>is associated to bean's property <strong>ownerUid</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${a}</dt><dd>is binded to method's parameter <strong>updateTimeA</strong></dd>
   * </dl>
   *
   * @param updateTimeA
   * 	is binded to <code>${a}</code>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Channel> selectRaw1(long updateTimeA) {
    // build where condition
    String[] args={String.valueOf(updateTimeA)};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time='%s'",(Object[])args));
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
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT uid, owner_uid, update_time, name, id FROM channel WHERE updateTime=${a}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>uid</dt><dd>no bean's property is associated</dd>
   * 	<dt>owner_uid</dt><dd>no bean's property is associated</dd>
   * 	<dt>update_time</dt><dd>no bean's property is associated</dd>
   * 	<dt>name</dt><dd>no bean's property is associated</dd>
   * 	<dt>id</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${a}</dt><dd>is binded to method's parameter <strong>updateTimeA</strong></dd>
   * </dl>
   *
   * @param updateTimeA
   * 	is binded to <code>${a}</code>
   *
   * @return cursor. Closing the cursor is delegated to the calling code.
   */
  @Override
  public Cursor selectRaw2(long updateTimeA) {
    // build where condition
    String[] args={String.valueOf(updateTimeA)};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time='%s'",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    return cursor;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT uid, owner_uid, update_time, name, id FROM channel WHERE updateTime=${a}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>owner_uid</dt><dd>is associated to bean's property <strong>ownerUid</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${a}</dt><dd>is binded to method's parameter <strong>updateTimeA</strong></dd>
   * </dl>
   *
   * @param updateTimeA
   * 	is binded to <code>${a}</code>
   * @param listener
   * 	is the Channel listener
   */
  @Override
  public void selectRaw3(long updateTimeA, OnReadBeanListener<Channel> listener) {
    // build where condition
    String[] args={String.valueOf(updateTimeA)};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time='%s'",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Channel resultBean=new Channel();
    try {
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("uid");
        int index1=cursor.getColumnIndex("owner_uid");
        int index2=cursor.getColumnIndex("update_time");
        int index3=cursor.getColumnIndex("name");
        int index4=cursor.getColumnIndex("id");

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
          if (!cursor.isNull(index0)) { resultBean.setUid(cursor.getString(index0)); }
          if (!cursor.isNull(index1)) { resultBean.setOwnerUid(cursor.getString(index1)); }
          if (!cursor.isNull(index2)) { resultBean.setUpdateTime(cursor.getLong(index2)); }
          if (!cursor.isNull(index3)) { resultBean.setName(cursor.getString(index3)); }
          if (!cursor.isNull(index4)) { resultBean.setId(cursor.getLong(index4)); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    } finally {
      if (!cursor.isClosed()) {
        cursor.close();
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT uid, owner_uid, update_time, name, id FROM channel WHERE updateTime=${a}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>uid</dt><dd>no bean's property is associated</dd>
   * 	<dt>owner_uid</dt><dd>no bean's property is associated</dd>
   * 	<dt>update_time</dt><dd>no bean's property is associated</dd>
   * 	<dt>name</dt><dd>no bean's property is associated</dd>
   * 	<dt>id</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${a}</dt><dd>is binded to method's parameter <strong>updateTimeA</strong></dd>
   * </dl>
   *
   * @param updateTimeA
   * 	is binded to <code>${a}</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectRaw4(long updateTimeA, OnReadCursorListener listener) {
    // build where condition
    String[] args={String.valueOf(updateTimeA)};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time='%s'",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    try {
      if (cursor.moveToFirst()) {

        do
         {
          listener.onRead(cursor);
        } while (cursor.moveToNext());
      }
    } finally {
      if (!cursor.isClosed()) {
        cursor.close();
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT uid, owner_uid, update_time, name, id FROM channel WHERE updateTime=${a}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>owner_uid</dt><dd>is associated to bean's property <strong>ownerUid</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${a}</dt><dd>is binded to method's parameter <strong>updateTimeA</strong></dd>
   * </dl>
   *
   * @param updateTimeA
   * 	is binded to <code>${a}</code>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public Set<Channel> selectRaw5(long updateTimeA) {
    // build where condition
    String[] args={String.valueOf(updateTimeA)};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time='%s'",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    HashSet<Channel> resultList=new HashSet<Channel>();
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

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT count(*) FROM channel WHERE update_time='%s'",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT count(*) FROM channel WHERE update_time=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    long result=0L;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0L; }
        result=cursor.getLong(0);
      }
    } finally {
      if (!cursor.isClosed()) {
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
   * 	is the Channel listener
   */
  @Override
  public void selectBean2(Channel value, OnReadBeanListener<Channel> listener) {
    // build where condition
    String[] args={String.valueOf(value.getUpdateTime())};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT update_time FROM channel WHERE update_time='%s'",(Object[])args));
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
      if (!cursor.isClosed()) {
        cursor.close();
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT update_time FROM channel WHERE updateTime=${bean.updateTime}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>update_time</dt><dd>no bean's property is associated</dd>
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
   * 	is the cursor listener
   */
  @Override
  public void selectBean3(Channel value, OnReadCursorListener listener) {
    // build where condition
    String[] args={String.valueOf(value.getUpdateTime())};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT update_time FROM channel WHERE update_time='%s'",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT update_time FROM channel WHERE update_time=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    try {
      if (cursor.moveToFirst()) {

        do
         {
          listener.onRead(cursor);
        } while (cursor.moveToNext());
      }
    } finally {
      if (!cursor.isClosed()) {
        cursor.close();
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT update_time FROM channel WHERE updateTime=${bean.updateTime}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>update_time</dt><dd>no bean's property is associated</dd>
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
   * @return cursor. Closing the cursor is delegated to the calling code.
   */
  @Override
  public Cursor selectBean4(Channel value) {
    // build where condition
    String[] args={String.valueOf(value.getUpdateTime())};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT update_time FROM channel WHERE update_time='%s'",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT update_time FROM channel WHERE update_time=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    return cursor;
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
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Channel selectBean5(Channel value) {
    // build where condition
    String[] args={String.valueOf(value.getUpdateTime())};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT update_time FROM channel WHERE update_time='%s'",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT update_time FROM channel WHERE update_time=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Channel resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("update_time");

      resultBean=new Channel();

      if (!cursor.isNull(index0)) { resultBean.setUpdateTime(cursor.getLong(index0)); }

    }
    cursor.close();

    return resultBean;
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
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public ArrayList<Channel> selectBean6(Channel value) {
    // build where condition
    String[] args={String.valueOf(value.getUpdateTime())};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT update_time FROM channel WHERE update_time='%s'",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT update_time FROM channel WHERE update_time=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    ArrayList<Channel> resultList=new ArrayList<Channel>();
    Channel resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("update_time");

      do
       {
        resultBean=new Channel();

        if (!cursor.isNull(index0)) { resultBean.setUpdateTime(cursor.getLong(index0)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
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
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public Set<Channel> selectBean7(Channel value) {
    // build where condition
    String[] args={String.valueOf(value.getUpdateTime())};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT update_time FROM channel WHERE update_time='%s'",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT update_time FROM channel WHERE update_time=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    HashSet<Channel> resultList=new HashSet<Channel>();
    Channel resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("update_time");

      do
       {
        resultBean=new Channel();

        if (!cursor.isNull(index0)) { resultBean.setUpdateTime(cursor.getLong(index0)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT update_time FROM channel WHERE updateTime=${bean.updateTime}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>update_time</dt><dd>no bean's property is associated</dd>
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
   * @return collection of single value extracted with query.
   */
  @Override
  public List<Long> selectBean8(Channel value) {
    // build where condition
    String[] args={String.valueOf(value.getUpdateTime())};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT update_time FROM channel WHERE update_time='%s'",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT update_time FROM channel WHERE update_time=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Long> resultList=new LinkedList<Long>();


    try {
      if (cursor.moveToFirst()) {

        do
         {
          if (!cursor.isNull(0)) {
            resultList.add(cursor.getLong(0));
          } else {
            resultList.add(null);
          }
        } while (cursor.moveToNext());
      }
    } finally {
      if (!cursor.isClosed()) {
        cursor.close();
      }
    }
    return resultList;
  }
}
