package sqlite.example01;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.LinkedHashSet;
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
  private static SQLiteStatement deleteContactBean1PreparedStatement0;

  private static SQLiteStatement deleteContactBean2PreparedStatement1;

  private static SQLiteStatement deleteContactRaw1PreparedStatement2;

  private static SQLiteStatement deleteContactRaw2PreparedStatement3;

  private static SQLiteStatement insertRaw1PreparedStatement4;

  private static SQLiteStatement insertRaw2PreparedStatement5;

  private static SQLiteStatement insertRaw3PreparedStatement6;

  private static SQLiteStatement insertBean1PreparedStatement7;

  private static SQLiteStatement insertBean2PreparedStatement8;

  private static SQLiteStatement updateContactRaw1PreparedStatement9;

  private static SQLiteStatement updateContactRaw2PreparedStatement10;

  private static SQLiteStatement updateContactRaw3PreparedStatement11;

  private static SQLiteStatement updateContactRaw4PreparedStatement12;

  private static SQLiteStatement updateContactBean1PreparedStatement13;

  private static SQLiteStatement updateContactBean2PreparedStatement14;

  private static SQLiteStatement updateContactBean3PreparedStatement15;

  private static final String SELECT_ALL_SQL1 = "SELECT uid, owner_uid, update_time, name, id FROM channel";

  private static final String SELECT_RAW1_SQL2 = "SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time=?";

  private static final String SELECT_RAW2_SQL3 = "SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time=?";

  private static final String SELECT_RAW3_SQL4 = "SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time=?";

  private static final String SELECT_RAW4_SQL5 = "SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time=?";

  private static final String SELECT_RAW5_SQL6 = "SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time=?";

  private static final String SELECT_BEAN1_SQL7 = "SELECT count(*) FROM channel WHERE update_time=?";

  private static final String SELECT_BEAN2_SQL8 = "SELECT update_time FROM channel WHERE update_time=?";

  private static final String SELECT_BEAN3_SQL9 = "SELECT update_time FROM channel WHERE update_time=?";

  private static final String SELECT_BEAN4_SQL10 = "SELECT update_time FROM channel WHERE update_time=?";

  private static final String SELECT_BEAN5_SQL11 = "SELECT update_time FROM channel WHERE update_time=?";

  private static final String SELECT_BEAN6_SQL12 = "SELECT update_time FROM channel WHERE update_time=?";

  private static final String SELECT_BEAN7_SQL13 = "SELECT update_time FROM channel WHERE update_time=?";

  private static final String SELECT_BEAN8_SQL14 = "SELECT update_time FROM channel WHERE update_time=?";

  public DaoChannelImpl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM channel WHERE owner_uid=${value.id}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
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
    if (deleteContactBean1PreparedStatement0==null) {
      // generate static SQL for insert
      String _sql="DELETE FROM channel WHERE owner_uid=?";
      deleteContactBean1PreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteContactBean1PreparedStatement0);
    _contentValues.addWhereArgs(String.valueOf(channel.getId()));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM channel WHERE owner_uid=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(_context, deleteContactBean1PreparedStatement0, _contentValues);
    return result!=0;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM channel WHERE owner_uid=${value.id}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
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
    if (deleteContactBean2PreparedStatement1==null) {
      // generate static SQL for insert
      String _sql="DELETE FROM channel WHERE owner_uid=?";
      deleteContactBean2PreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteContactBean2PreparedStatement1);
    _contentValues.addWhereArgs(String.valueOf(value.getId()));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM channel WHERE owner_uid=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(_context, deleteContactBean2PreparedStatement1, _contentValues);
    return result!=0;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM channel WHERE owner_uid=${ownerUid} and id=${id}</pre>
   *
   *
   * <h2>Where parameters:</h2>
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
    if (deleteContactRaw1PreparedStatement2==null) {
      // generate static SQL for insert
      String _sql="DELETE FROM channel WHERE owner_uid=? and id=?";
      deleteContactRaw1PreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteContactRaw1PreparedStatement2);
    _contentValues.addWhereArgs((b==null?"":b));
    _contentValues.addWhereArgs(String.valueOf(dummy));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM channel WHERE owner_uid=? and id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(_context, deleteContactRaw1PreparedStatement2, _contentValues);
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM channel WHERE owner_uid=${ownerUid} and id=${id}</pre>
   *
   *
   * <h2>Where parameters:</h2>
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
    if (deleteContactRaw2PreparedStatement3==null) {
      // generate static SQL for insert
      String _sql="DELETE FROM channel WHERE owner_uid=? and id=?";
      deleteContactRaw2PreparedStatement3 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteContactRaw2PreparedStatement3);
    _contentValues.addWhereArgs((ownerUid==null?"":ownerUid));
    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM channel WHERE owner_uid=? and id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(_context, deleteContactRaw2PreparedStatement3, _contentValues);
    return result!=0;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO channel (owner_uid, id) VALUES (${b}, ${azz})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>b</dt><dd>is binded to query's parameter <strong>${b}</strong> and method's parameter <strong>b</strong></dd>
   * 	<dt>azz</dt><dd>is binded to query's parameter <strong>${azz}</strong> and method's parameter <strong>azz</strong></dd>
   * </dl>
   *
   * @param b
   * 	is binded to column value <strong>owner_uid</strong>
   * @param azz
   * 	is binded to column value <strong>id</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertRaw1(String b, long azz) {
    if (insertRaw1PreparedStatement4==null) {
      // generate static SQL for insert
      String _sql="INSERT INTO channel (owner_uid, id) VALUES (?, ?)";
      insertRaw1PreparedStatement4 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertRaw1PreparedStatement4);

    if (b!=null) {
      _contentValues.put("owner_uid", b);
    } else {
      _contentValues.putNull("owner_uid");
    }
    _contentValues.put("id", azz);

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
      Logger.info("INSERT INTO channel (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(_context, insertRaw1PreparedStatement4, _contentValues);
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO channel (owner_uid, id) VALUES (${b}, ${id})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>b</dt><dd>is binded to query's parameter <strong>${b}</strong> and method's parameter <strong>b</strong></dd>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param b
   * 	is binded to column value <strong>owner_uid</strong>
   * @param id
   * 	is binded to column value <strong>id</strong>
   *
   * @return <code>true</code> if record is inserted, <code>false</code> otherwise
   */
  @Override
  public boolean insertRaw2(String b, long id) {
    if (insertRaw2PreparedStatement5==null) {
      // generate static SQL for insert
      String _sql="INSERT INTO channel (owner_uid, id) VALUES (?, ?)";
      insertRaw2PreparedStatement5 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertRaw2PreparedStatement5);

    if (b!=null) {
      _contentValues.put("owner_uid", b);
    } else {
      _contentValues.putNull("owner_uid");
    }
    _contentValues.put("id", id);

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
      Logger.info("INSERT INTO channel (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(_context, insertRaw2PreparedStatement5, _contentValues);
    return result!=-1;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO channel (owner_uid, id) VALUES (${ownerUid}, ${id})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>ownerUid</dt><dd>is binded to query's parameter <strong>${ownerUid}</strong> and method's parameter <strong>ownerUid</strong></dd>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param ownerUid
   * 	is binded to column value <strong>owner_uid</strong>
   * @param id
   * 	is binded to column value <strong>id</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insertRaw3(String ownerUid, long id) {
    if (insertRaw3PreparedStatement6==null) {
      // generate static SQL for insert
      String _sql="INSERT INTO channel (owner_uid, id) VALUES (?, ?)";
      insertRaw3PreparedStatement6 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertRaw3PreparedStatement6);

    if (ownerUid!=null) {
      _contentValues.put("owner_uid", ownerUid);
    } else {
      _contentValues.putNull("owner_uid");
    }
    _contentValues.put("id", id);

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
      Logger.info("INSERT INTO channel (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(_context, insertRaw3PreparedStatement6, _contentValues);
    return (int)result;
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
    if (insertBean1PreparedStatement7==null) {
      // generate static SQL for insert
      String _sql="INSERT INTO channel (uid, owner_uid, update_time, name) VALUES (?, ?, ?, ?)";
      insertBean1PreparedStatement7 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertBean1PreparedStatement7);
    if (bean.getUid()!=null) {
      _contentValues.put("uid", bean.getUid());
    } else {
      _contentValues.putNull("uid");
    }
    if (bean.getOwnerUid()!=null) {
      _contentValues.put("owner_uid", bean.getOwnerUid());
    } else {
      _contentValues.putNull("owner_uid");
    }
    _contentValues.put("update_time", bean.getUpdateTime());
    if (bean.getName()!=null) {
      _contentValues.put("name", bean.getName());
    } else {
      _contentValues.putNull("name");
    }

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
      Logger.info("INSERT INTO channel (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(_context, insertBean1PreparedStatement7, _contentValues);
    bean.setId(result);

    return (int)result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO channel (uid, owner_uid, update_time, name) VALUES (${bean.uid}, ${bean.ownerUid}, ${bean.updateTime}, ${bean.name})</pre>
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
    if (insertBean2PreparedStatement8==null) {
      // generate static SQL for insert
      String _sql="INSERT INTO channel (uid, owner_uid, update_time, name) VALUES (?, ?, ?, ?)";
      insertBean2PreparedStatement8 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertBean2PreparedStatement8);
    if (bean.getUid()!=null) {
      _contentValues.put("uid", bean.getUid());
    } else {
      _contentValues.putNull("uid");
    }
    if (bean.getOwnerUid()!=null) {
      _contentValues.put("owner_uid", bean.getOwnerUid());
    } else {
      _contentValues.putNull("owner_uid");
    }
    _contentValues.put("update_time", bean.getUpdateTime());
    if (bean.getName()!=null) {
      _contentValues.put("name", bean.getName());
    } else {
      _contentValues.putNull("name");
    }

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
      Logger.info("INSERT INTO channel (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(_context, insertBean2PreparedStatement8, _contentValues);
    bean.setId(result);

    return result!=-1;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE channel SET id=:id WHERE id=${dummy}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>id</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
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
    if (updateContactRaw1PreparedStatement9==null) {
      // generate static SQL for insert
      String _sql="UPDATE channel SET id=? WHERE id=?";
      updateContactRaw1PreparedStatement9 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateContactRaw1PreparedStatement9);
    _contentValues.put("id", glu);

    _contentValues.addWhereArgs(String.valueOf(aid));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE channel SET id=:id WHERE id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(_context, updateContactRaw1PreparedStatement9, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE channel SET id=:id WHERE id=${dummy}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>id</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
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
    if (updateContactRaw2PreparedStatement10==null) {
      // generate static SQL for insert
      String _sql="UPDATE channel SET id=? WHERE id=?";
      updateContactRaw2PreparedStatement10 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateContactRaw2PreparedStatement10);
    _contentValues.put("id", id);

    _contentValues.addWhereArgs(String.valueOf(dummy));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE channel SET id=:id WHERE id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(_context, updateContactRaw2PreparedStatement10, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE channel SET owner_uid=:ownerUid WHERE id=${test}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>owner_uid</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
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
    if (updateContactRaw3PreparedStatement11==null) {
      // generate static SQL for insert
      String _sql="UPDATE channel SET ownerUid=? WHERE id=?";
      updateContactRaw3PreparedStatement11 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateContactRaw3PreparedStatement11);
    if (app!=null) {
      _contentValues.put("owner_uid", app);
    } else {
      _contentValues.putNull("owner_uid");
    }

    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE channel SET owner_uid=:ownerUid WHERE id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(_context, updateContactRaw3PreparedStatement11, _contentValues);
    return result!=0;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE channel SET owner_uid=:ownerUid WHERE id=${id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>owner_uid</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
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
    if (updateContactRaw4PreparedStatement12==null) {
      // generate static SQL for insert
      String _sql="UPDATE channel SET ownerUid=? WHERE id=?";
      updateContactRaw4PreparedStatement12 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateContactRaw4PreparedStatement12);
    if (ownerUid!=null) {
      _contentValues.put("owner_uid", ownerUid);
    } else {
      _contentValues.putNull("owner_uid");
    }

    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE channel SET owner_uid=:ownerUid WHERE id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(_context, updateContactRaw4PreparedStatement12, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE channel SET uid=:uid, owner_uid=:ownerUid, update_time=:updateTime, name=:name WHERE id=${bean.id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>uid</dt><dd>is mapped to <strong>${bean.uid}</strong></dd>
   * 	<dt>owner_uid</dt><dd>is mapped to <strong>${bean.ownerUid}</strong></dd>
   * 	<dt>update_time</dt><dd>is mapped to <strong>${bean.updateTime}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
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
    if (updateContactBean1PreparedStatement13==null) {
      // generate static SQL for insert
      String _sql="UPDATE channel SET uid=?, ownerUid=?, updateTime=?, name=? WHERE id=?";
      updateContactBean1PreparedStatement13 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateContactBean1PreparedStatement13);
    if (value.getUid()!=null) {
      _contentValues.put("uid", value.getUid());
    } else {
      _contentValues.putNull("uid");
    }
    if (value.getOwnerUid()!=null) {
      _contentValues.put("owner_uid", value.getOwnerUid());
    } else {
      _contentValues.putNull("owner_uid");
    }
    _contentValues.put("update_time", value.getUpdateTime());
    if (value.getName()!=null) {
      _contentValues.put("name", value.getName());
    } else {
      _contentValues.putNull("name");
    }

    _contentValues.addWhereArgs(String.valueOf(value.getId()));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE channel SET uid=:uid, owner_uid=:ownerUid, update_time=:updateTime, name=:name WHERE id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(_context, updateContactBean1PreparedStatement13, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE channel SET uid=:uid, owner_uid=:ownerUid, update_time=:updateTime, name=:name WHERE id=${bean.id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>uid</dt><dd>is mapped to <strong>${bean.uid}</strong></dd>
   * 	<dt>owner_uid</dt><dd>is mapped to <strong>${bean.ownerUid}</strong></dd>
   * 	<dt>update_time</dt><dd>is mapped to <strong>${bean.updateTime}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
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
   * @return number of updated records
   */
  @Override
  public long updateContactBean2(Channel bean) {
    if (updateContactBean2PreparedStatement14==null) {
      // generate static SQL for insert
      String _sql="UPDATE channel SET uid=?, ownerUid=?, updateTime=?, name=? WHERE id=?";
      updateContactBean2PreparedStatement14 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateContactBean2PreparedStatement14);
    if (bean.getUid()!=null) {
      _contentValues.put("uid", bean.getUid());
    } else {
      _contentValues.putNull("uid");
    }
    if (bean.getOwnerUid()!=null) {
      _contentValues.put("owner_uid", bean.getOwnerUid());
    } else {
      _contentValues.putNull("owner_uid");
    }
    _contentValues.put("update_time", bean.getUpdateTime());
    if (bean.getName()!=null) {
      _contentValues.put("name", bean.getName());
    } else {
      _contentValues.putNull("name");
    }

    _contentValues.addWhereArgs(String.valueOf(bean.getId()));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE channel SET uid=:uid, owner_uid=:ownerUid, update_time=:updateTime, name=:name WHERE id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(_context, updateContactBean2PreparedStatement14, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE channel SET uid=:uid, owner_uid=:ownerUid, update_time=:updateTime, name=:name WHERE id=${bean.id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>uid</dt><dd>is mapped to <strong>${bean.uid}</strong></dd>
   * 	<dt>owner_uid</dt><dd>is mapped to <strong>${bean.ownerUid}</strong></dd>
   * 	<dt>update_time</dt><dd>is mapped to <strong>${bean.updateTime}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
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
  public boolean updateContactBean3(Channel bean) {
    if (updateContactBean3PreparedStatement15==null) {
      // generate static SQL for insert
      String _sql="UPDATE channel SET uid=?, ownerUid=?, updateTime=?, name=? WHERE id=?";
      updateContactBean3PreparedStatement15 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateContactBean3PreparedStatement15);
    if (bean.getUid()!=null) {
      _contentValues.put("uid", bean.getUid());
    } else {
      _contentValues.putNull("uid");
    }
    if (bean.getOwnerUid()!=null) {
      _contentValues.put("owner_uid", bean.getOwnerUid());
    } else {
      _contentValues.putNull("owner_uid");
    }
    _contentValues.put("update_time", bean.getUpdateTime());
    if (bean.getName()!=null) {
      _contentValues.put("name", bean.getName());
    } else {
      _contentValues.putNull("name");
    }

    _contentValues.addWhereArgs(String.valueOf(bean.getId()));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE channel SET uid=:uid, owner_uid=:ownerUid, update_time=:updateTime, name=:name WHERE id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(_context, updateContactBean3PreparedStatement15, _contentValues);
    return result!=0;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT uid, owner_uid, update_time, name, id FROM channel</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>owner_uid</dt><dd>is associated to bean's property <strong>ownerUid</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Channel> selectAll() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL1;
    // add where arguments
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ArrayList<Channel> resultList=new ArrayList<Channel>(cursor.getCount());
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
          resultBean.setId(cursor.getLong(index4));

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time=${a}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>owner_uid</dt><dd>is associated to bean's property <strong>ownerUid</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${a}</dt><dd>is binded to method's parameter <strong>updateTimeA</strong></dd>
   * </dl>
   *
   * @param updateTimeA
   * 	is binded to <code>${a}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Channel> selectRaw1(long updateTimeA) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_RAW1_SQL2;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(updateTimeA));
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ArrayList<Channel> resultList=new ArrayList<Channel>(cursor.getCount());
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
          resultBean.setId(cursor.getLong(index4));

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time=${a}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>owner_uid</dt><dd>is associated to bean's property <strong>ownerUid</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${a}</dt><dd>is binded to method's parameter <strong>updateTimeA</strong></dd>
   * </dl>
   *
   * @param updateTimeA
   * 	is binded to <code>${a}</code>
   * @return cursor. Closing the cursor is delegated to the calling code.
   */
  @Override
  public Cursor selectRaw2(long updateTimeA) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_RAW2_SQL3;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(updateTimeA));
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
    Cursor cursor = database().rawQuery(_sql, _sqlArgs);
    // log section BEGIN
    if (_context.isLogEnabled()) {
      Logger.info("Rows found: %s",cursor.getCount());
    }
    // log section END
    return cursor;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time=${a}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>owner_uid</dt><dd>is associated to bean's property <strong>ownerUid</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
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
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_RAW3_SQL4;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(updateTimeA));
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END
      Channel resultBean=new Channel();
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
          // id does not need reset

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.setUid(cursor.getString(index0)); }
          if (!cursor.isNull(index1)) { resultBean.setOwnerUid(cursor.getString(index1)); }
          if (!cursor.isNull(index2)) { resultBean.setUpdateTime(cursor.getLong(index2)); }
          if (!cursor.isNull(index3)) { resultBean.setName(cursor.getString(index3)); }
          resultBean.setId(cursor.getLong(index4));

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time=${a}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>owner_uid</dt><dd>is associated to bean's property <strong>ownerUid</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
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
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_RAW4_SQL5;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(updateTimeA));
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      if (cursor.moveToFirst()) {

        do
         {
          listener.onRead(cursor);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT uid, owner_uid, update_time, name, id FROM channel WHERE update_time=${a}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>owner_uid</dt><dd>is associated to bean's property <strong>ownerUid</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${a}</dt><dd>is binded to method's parameter <strong>updateTimeA</strong></dd>
   * </dl>
   *
   * @param updateTimeA
   * 	is binded to <code>${a}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public Set<Channel> selectRaw5(long updateTimeA) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_RAW5_SQL6;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(updateTimeA));
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      LinkedHashSet<Channel> resultList=new LinkedHashSet<Channel>();
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
          resultBean.setId(cursor.getLong(index4));

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT count(*) FROM channel WHERE update_time=${bean.updateTime}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>count(*)</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.updateTime}</dt><dd>is binded to method's parameter <strong>value.updateTime</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as ${bean}
   * @return single value extracted by query.
   */
  @Override
  public long selectBean1(Channel value) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BEAN1_SQL7;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(value.getUpdateTime()));
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END
      long result=0L;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0L; }
        result=cursor.getLong(0);
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT update_time FROM channel WHERE update_time=${bean.updateTime}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
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
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BEAN2_SQL8;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(value.getUpdateTime()));
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END
      Channel resultBean=new Channel();
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
          // id does not need reset

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.setUpdateTime(cursor.getLong(index0)); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT update_time FROM channel WHERE update_time=${bean.updateTime}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
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
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BEAN3_SQL9;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(value.getUpdateTime()));
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      if (cursor.moveToFirst()) {

        do
         {
          listener.onRead(cursor);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT update_time FROM channel WHERE update_time=${bean.updateTime}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.updateTime}</dt><dd>is binded to method's parameter <strong>value.updateTime</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as ${bean}
   * @return cursor. Closing the cursor is delegated to the calling code.
   */
  @Override
  public Cursor selectBean4(Channel value) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BEAN4_SQL10;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(value.getUpdateTime()));
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
    Cursor cursor = database().rawQuery(_sql, _sqlArgs);
    // log section BEGIN
    if (_context.isLogEnabled()) {
      Logger.info("Rows found: %s",cursor.getCount());
    }
    // log section END
    return cursor;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT update_time FROM channel WHERE update_time=${bean.updateTime}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.updateTime}</dt><dd>is binded to method's parameter <strong>value.updateTime</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as ${bean}
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Channel selectBean5(Channel value) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BEAN5_SQL11;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(value.getUpdateTime()));
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      Channel resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("update_time");

        resultBean=new Channel();

        if (!cursor.isNull(index0)) { resultBean.setUpdateTime(cursor.getLong(index0)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT update_time FROM channel WHERE update_time=${bean.updateTime}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.updateTime}</dt><dd>is binded to method's parameter <strong>value.updateTime</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as ${bean}
   * @return collection of bean or empty collection.
   */
  @Override
  public ArrayList<Channel> selectBean6(Channel value) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BEAN6_SQL12;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(value.getUpdateTime()));
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ArrayList<Channel> resultList=new ArrayList<Channel>(cursor.getCount());
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

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT update_time FROM channel WHERE update_time=${bean.updateTime}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.updateTime}</dt><dd>is binded to method's parameter <strong>value.updateTime</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as ${bean}
   * @return collection of bean or empty collection.
   */
  @Override
  public Set<Channel> selectBean7(Channel value) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BEAN7_SQL13;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(value.getUpdateTime()));
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      LinkedHashSet<Channel> resultList=new LinkedHashSet<Channel>();
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

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT update_time FROM channel WHERE update_time=${bean.updateTime}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.updateTime}</dt><dd>is binded to method's parameter <strong>value.updateTime</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as ${bean}
   * @return collection of single value extracted by query.
   */
  @Override
  public List<Long> selectBean8(Channel value) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BEAN8_SQL14;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(value.getUpdateTime()));
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ArrayList<Long> resultList=new ArrayList<Long>(cursor.getCount());


      if (cursor.moveToFirst()) {

        do
         {
          if (!cursor.isNull(0)) {
            resultList.add(parser1(cursor.getBlob(0)));
          } else {
            resultList.add(null);
          }
        } while (cursor.moveToNext());
      }
      return resultList;
    }
  }

  /**
   * for param serializer1 serialization
   */
  private byte[] serializer1(Long value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        jacksonSerializer.writeNumberField("element", value);
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param parser1 parsing
   */
  private Long parser1(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      Long result=null;
      if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
        result=jacksonParser.getLongValue();
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  public static void clearCompiledStatements() {
    if (deleteContactBean1PreparedStatement0!=null) {
      deleteContactBean1PreparedStatement0.close();
      deleteContactBean1PreparedStatement0=null;
    }
    if (deleteContactBean2PreparedStatement1!=null) {
      deleteContactBean2PreparedStatement1.close();
      deleteContactBean2PreparedStatement1=null;
    }
    if (deleteContactRaw1PreparedStatement2!=null) {
      deleteContactRaw1PreparedStatement2.close();
      deleteContactRaw1PreparedStatement2=null;
    }
    if (deleteContactRaw2PreparedStatement3!=null) {
      deleteContactRaw2PreparedStatement3.close();
      deleteContactRaw2PreparedStatement3=null;
    }
    if (insertRaw1PreparedStatement4!=null) {
      insertRaw1PreparedStatement4.close();
      insertRaw1PreparedStatement4=null;
    }
    if (insertRaw2PreparedStatement5!=null) {
      insertRaw2PreparedStatement5.close();
      insertRaw2PreparedStatement5=null;
    }
    if (insertRaw3PreparedStatement6!=null) {
      insertRaw3PreparedStatement6.close();
      insertRaw3PreparedStatement6=null;
    }
    if (insertBean1PreparedStatement7!=null) {
      insertBean1PreparedStatement7.close();
      insertBean1PreparedStatement7=null;
    }
    if (insertBean2PreparedStatement8!=null) {
      insertBean2PreparedStatement8.close();
      insertBean2PreparedStatement8=null;
    }
    if (updateContactRaw1PreparedStatement9!=null) {
      updateContactRaw1PreparedStatement9.close();
      updateContactRaw1PreparedStatement9=null;
    }
    if (updateContactRaw2PreparedStatement10!=null) {
      updateContactRaw2PreparedStatement10.close();
      updateContactRaw2PreparedStatement10=null;
    }
    if (updateContactRaw3PreparedStatement11!=null) {
      updateContactRaw3PreparedStatement11.close();
      updateContactRaw3PreparedStatement11=null;
    }
    if (updateContactRaw4PreparedStatement12!=null) {
      updateContactRaw4PreparedStatement12.close();
      updateContactRaw4PreparedStatement12=null;
    }
    if (updateContactBean1PreparedStatement13!=null) {
      updateContactBean1PreparedStatement13.close();
      updateContactBean1PreparedStatement13=null;
    }
    if (updateContactBean2PreparedStatement14!=null) {
      updateContactBean2PreparedStatement14.close();
      updateContactBean2PreparedStatement14=null;
    }
    if (updateContactBean3PreparedStatement15!=null) {
      updateContactBean3PreparedStatement15.close();
      updateContactBean3PreparedStatement15=null;
    }
  }
}
