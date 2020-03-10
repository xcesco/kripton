package sqlite.feature.async;

import android.database.Cursor;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseHelper;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.io.IOException;
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
public class DaoChannelImpl extends Dao implements DaoChannel {
  private static SupportSQLiteStatement deleteContactBean1PreparedStatement0;

  private static SupportSQLiteStatement deleteContactBean2PreparedStatement1;

  private static SupportSQLiteStatement deleteContactRaw1PreparedStatement2;

  private static SupportSQLiteStatement deleteContactRaw2PreparedStatement3;

  private static SupportSQLiteStatement insertRaw1PreparedStatement4;

  private static SupportSQLiteStatement insertRaw2PreparedStatement5;

  private static SupportSQLiteStatement insertRaw3PreparedStatement6;

  private static SupportSQLiteStatement insertBean1PreparedStatement7;

  private static SupportSQLiteStatement insertBean2PreparedStatement8;

  private static SupportSQLiteStatement updateContactRaw1PreparedStatement9;

  private static SupportSQLiteStatement updateContactRaw2PreparedStatement10;

  private static SupportSQLiteStatement updateContactRaw3PreparedStatement11;

  private static SupportSQLiteStatement updateContactRaw4PreparedStatement12;

  private static SupportSQLiteStatement updateContactBean1PreparedStatement13;

  private static SupportSQLiteStatement updateContactBean2PreparedStatement14;

  private static SupportSQLiteStatement updateContactBean3PreparedStatement15;

  /**
   * SQL definition for method selectAll
   */
  private static final String SELECT_ALL_SQL1 = "SELECT id, name, owner_uid, uid, update_time FROM channel";

  /**
   * SQL definition for method selectRaw1
   */
  private static final String SELECT_RAW1_SQL2 = "SELECT id, name, owner_uid, uid, update_time FROM channel WHERE update_time=?";

  /**
   * SQL definition for method selectRaw2
   */
  private static final String SELECT_RAW2_SQL3 = "SELECT id, name, owner_uid, uid, update_time FROM channel WHERE update_time=?";

  /**
   * SQL definition for method selectRaw3
   */
  private static final String SELECT_RAW3_SQL4 = "SELECT id, name, owner_uid, uid, update_time FROM channel WHERE update_time=?";

  /**
   * SQL definition for method selectRaw4
   */
  private static final String SELECT_RAW4_SQL5 = "SELECT id, name, owner_uid, uid, update_time FROM channel WHERE update_time=?";

  /**
   * SQL definition for method selectRaw5
   */
  private static final String SELECT_RAW5_SQL6 = "SELECT id, name, owner_uid, uid, update_time FROM channel WHERE update_time=?";

  /**
   * SQL definition for method selectBean1
   */
  private static final String SELECT_BEAN1_SQL7 = "SELECT count(*) FROM channel WHERE update_time=?";

  /**
   * SQL definition for method selectBean2
   */
  private static final String SELECT_BEAN2_SQL8 = "SELECT update_time FROM channel WHERE update_time=?";

  /**
   * SQL definition for method selectBean3
   */
  private static final String SELECT_BEAN3_SQL9 = "SELECT update_time FROM channel WHERE update_time=?";

  /**
   * SQL definition for method selectBean4
   */
  private static final String SELECT_BEAN4_SQL10 = "SELECT update_time FROM channel WHERE update_time=?";

  /**
   * SQL definition for method selectBean5
   */
  private static final String SELECT_BEAN5_SQL11 = "SELECT update_time FROM channel WHERE update_time=?";

  /**
   * SQL definition for method selectBean6
   */
  private static final String SELECT_BEAN6_SQL12 = "SELECT update_time FROM channel WHERE update_time=?";

  /**
   * SQL definition for method selectBean7
   */
  private static final String SELECT_BEAN7_SQL13 = "SELECT update_time FROM channel WHERE update_time=?";

  /**
   * SQL definition for method selectBean8
   */
  private static final String SELECT_BEAN8_SQL14 = "SELECT update_time FROM channel WHERE update_time=?";

  public DaoChannelImpl(BindDummy01DaoFactory daoFactory) {
    super(daoFactory.getContext());
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM channel WHERE owner_uid=:value.id</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:value.id</dt><dd>is mapped to method's parameter <strong>channel.id</strong></dd>
   * </dl>
   *
   * @param channel
   * 	is used as <code>:value</code>
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteContactBean1(Channel channel) {
    if (deleteContactBean1PreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM channel WHERE owner_uid=?";
      deleteContactBean1PreparedStatement0 = KriptonDatabaseHelper.compile(_context, _sql);
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
    int result = KriptonDatabaseHelper.updateDelete(deleteContactBean1PreparedStatement0, _contentValues);
    return result!=0;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM channel WHERE owner_uid=:{value.id}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:value.id</dt><dd>is mapped to method's parameter <strong>value.id</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as <code>:value</code>
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteContactBean2(Channel value) {
    if (deleteContactBean2PreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM channel WHERE owner_uid=?";
      deleteContactBean2PreparedStatement1 = KriptonDatabaseHelper.compile(_context, _sql);
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
    int result = KriptonDatabaseHelper.updateDelete(deleteContactBean2PreparedStatement1, _contentValues);
    return result!=0;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM channel WHERE owner_uid=:ownerUid and id=:id</pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:ownerUid</dt><dd>is mapped to method's parameter <strong>b</strong></dd>
   * 	<dt>:id</dt><dd>is mapped to method's parameter <strong>dummy</strong></dd>
   * </dl>
   *
   * @param b
   * 	is used as where parameter <strong>:ownerUid</strong>
   * @param dummy
   * 	is used as where parameter <strong>:id</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteContactRaw1(String b, long dummy) {
    if (deleteContactRaw1PreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM channel WHERE owner_uid=? and id=?";
      deleteContactRaw1PreparedStatement2 = KriptonDatabaseHelper.compile(_context, _sql);
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
    int result = KriptonDatabaseHelper.updateDelete(deleteContactRaw1PreparedStatement2, _contentValues);
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM channel WHERE owner_uid=:ownerUid and id=:id</pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:ownerUid</dt><dd>is mapped to method's parameter <strong>ownerUid</strong></dd>
   * 	<dt>:id</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param ownerUid
   * 	is used as where parameter <strong>:ownerUid</strong>
   * @param id
   * 	is used as where parameter <strong>:id</strong>
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteContactRaw2(String ownerUid, long id) {
    if (deleteContactRaw2PreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM channel WHERE owner_uid=? and id=?";
      deleteContactRaw2PreparedStatement3 = KriptonDatabaseHelper.compile(_context, _sql);
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
    int result = KriptonDatabaseHelper.updateDelete(deleteContactRaw2PreparedStatement3, _contentValues);
    return result!=0;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO channel (owner_uid, id) VALUES (:b, :azz)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>b</dt><dd>is binded to query's parameter <strong>:b</strong> and method's parameter <strong>b</strong></dd>
   * 	<dt>azz</dt><dd>is binded to query's parameter <strong>:azz</strong> and method's parameter <strong>azz</strong></dd>
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
    // Specialized Insert - InsertType - BEGIN
    if (insertRaw1PreparedStatement4==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO channel (owner_uid, id) VALUES (?, ?)";
      insertRaw1PreparedStatement4 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertRaw1PreparedStatement4);

    _contentValues.put("owner_uid", b);
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
    long result = KriptonDatabaseHelper.insert(insertRaw1PreparedStatement4, _contentValues);
    return result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO channel (owner_uid, id) VALUES (:b, :id)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>b</dt><dd>is binded to query's parameter <strong>:b</strong> and method's parameter <strong>b</strong></dd>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>:id</strong> and method's parameter <strong>id</strong></dd>
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
    // Specialized Insert - InsertType - BEGIN
    if (insertRaw2PreparedStatement5==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO channel (owner_uid, id) VALUES (?, ?)";
      insertRaw2PreparedStatement5 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertRaw2PreparedStatement5);

    _contentValues.put("owner_uid", b);
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
    long result = KriptonDatabaseHelper.insert(insertRaw2PreparedStatement5, _contentValues);
    return result!=-1;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO channel (owner_uid, id) VALUES (:ownerUid, :id)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>ownerUid</dt><dd>is binded to query's parameter <strong>:ownerUid</strong> and method's parameter <strong>ownerUid</strong></dd>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>:id</strong> and method's parameter <strong>id</strong></dd>
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
    // Specialized Insert - InsertType - BEGIN
    if (insertRaw3PreparedStatement6==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO channel (owner_uid, id) VALUES (?, ?)";
      insertRaw3PreparedStatement6 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertRaw3PreparedStatement6);

    _contentValues.put("owner_uid", ownerUid);
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
    long result = KriptonDatabaseHelper.insert(insertRaw3PreparedStatement6, _contentValues);
    return (int)result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO channel (name, owner_uid, uid, update_time) VALUES (:bean.name, :bean.ownerUid, :bean.uid, :bean.updateTime)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>owner_uid</dt><dd>is mapped to <strong>:bean.ownerUid</strong></dd>
   * 	<dt>uid</dt><dd>is mapped to <strong>:bean.uid</strong></dd>
   * 	<dt>update_time</dt><dd>is mapped to <strong>:bean.updateTime</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insertBean1(Channel bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertBean1PreparedStatement7==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO channel (name, owner_uid, uid, update_time) VALUES (?, ?, ?, ?)";
      insertBean1PreparedStatement7 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertBean1PreparedStatement7);
    _contentValues.put("name", bean.getName());
    _contentValues.put("owner_uid", bean.getOwnerUid());
    _contentValues.put("uid", bean.getUid());
    _contentValues.put("update_time", bean.getUpdateTime());

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
    long result = KriptonDatabaseHelper.insert(insertBean1PreparedStatement7, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.setId(result);

    return (int)result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO channel (name, owner_uid, uid, update_time) VALUES (:bean.name, :bean.ownerUid, :bean.uid, :bean.updateTime)</pre>
   *
   * <p><code>arg.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>:arg.name</strong></dd>
   * 	<dt>owner_uid</dt><dd>is mapped to <strong>:arg.ownerUid</strong></dd>
   * 	<dt>uid</dt><dd>is mapped to <strong>:arg.uid</strong></dd>
   * 	<dt>update_time</dt><dd>is mapped to <strong>:arg.updateTime</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>arg</strong>
   *
   * @return <code>true</code> if record is inserted, <code>false</code> otherwise
   */
  @Override
  public boolean insertBean2(Channel bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertBean2PreparedStatement8==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO channel (name, owner_uid, uid, update_time) VALUES (?, ?, ?, ?)";
      insertBean2PreparedStatement8 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertBean2PreparedStatement8);
    _contentValues.put("name", bean.getName());
    _contentValues.put("owner_uid", bean.getOwnerUid());
    _contentValues.put("uid", bean.getUid());
    _contentValues.put("update_time", bean.getUpdateTime());

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
    long result = KriptonDatabaseHelper.insert(insertBean2PreparedStatement8, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.setId(result);

    return result!=-1;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE channel SET id=:id WHERE id=:dummy</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>id</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:dummy</dt><dd>is mapped to method's parameter <strong>aid</strong></dd>
   * </dl>
   *
   * @param glu
   * 	is used as updated field <strong>id</strong>
   * @param aid
   * 	is used as where parameter <strong>:dummy</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateContactRaw1(long glu, long aid) {
    if (updateContactRaw1PreparedStatement9==null) {
      // generate static SQL for statement
      String _sql="UPDATE channel SET id=? WHERE id=?";
      updateContactRaw1PreparedStatement9 = KriptonDatabaseHelper.compile(_context, _sql);
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
    int result = KriptonDatabaseHelper.updateDelete(updateContactRaw1PreparedStatement9, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE channel SET id=:id WHERE id=:dummy</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>id</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:dummy</dt><dd>is mapped to method's parameter <strong>dummy</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param dummy
   * 	is used as where parameter <strong>:dummy</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateContactRaw2(long id, long dummy) {
    if (updateContactRaw2PreparedStatement10==null) {
      // generate static SQL for statement
      String _sql="UPDATE channel SET id=? WHERE id=?";
      updateContactRaw2PreparedStatement10 = KriptonDatabaseHelper.compile(_context, _sql);
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
    int result = KriptonDatabaseHelper.updateDelete(updateContactRaw2PreparedStatement10, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE channel SET owner_uid=:ownerUid WHERE id=:test</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>owner_uid</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:test</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param app
   * 	is used as updated field <strong>ownerUid</strong>
   * @param id
   * 	is used as where parameter <strong>:test</strong>
   *
   * @return <code>true</code> if record is updated, <code>false</code> otherwise
   */
  @Override
  public boolean updateContactRaw3(String app, long id) {
    if (updateContactRaw3PreparedStatement11==null) {
      // generate static SQL for statement
      String _sql="UPDATE channel SET owner_uid=? WHERE id=?";
      updateContactRaw3PreparedStatement11 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateContactRaw3PreparedStatement11);
    _contentValues.put("owner_uid", app);

    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE channel SET owner_uid=:owner_uid WHERE id=?");

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
    int result = KriptonDatabaseHelper.updateDelete(updateContactRaw3PreparedStatement11, _contentValues);
    return result!=0;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE channel SET owner_uid=:ownerUid WHERE id=:id</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>owner_uid</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param ownerUid
   * 	is used as updated field <strong>ownerUid</strong>
   * @param id
   * 	is used as where parameter <strong>:id</strong>
   *
   * @return number of updated records
   */
  @Override
  public int updateContactRaw4(String ownerUid, long id) {
    if (updateContactRaw4PreparedStatement12==null) {
      // generate static SQL for statement
      String _sql="UPDATE channel SET owner_uid=? WHERE id=?";
      updateContactRaw4PreparedStatement12 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateContactRaw4PreparedStatement12);
    _contentValues.put("owner_uid", ownerUid);

    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE channel SET owner_uid=:owner_uid WHERE id=?");

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
    int result = KriptonDatabaseHelper.updateDelete(updateContactRaw4PreparedStatement12, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE channel SET name=:name, owner_uid=:ownerUid, uid=:uid, update_time=:updateTime WHERE id=${bean.id}</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>owner_uid</dt><dd>is mapped to <strong>:bean.ownerUid</strong></dd>
   * 	<dt>uid</dt><dd>is mapped to <strong>:bean.uid</strong></dd>
   * 	<dt>update_time</dt><dd>is mapped to <strong>:bean.updateTime</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is mapped to method's parameter <strong>value.id</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as <code>:bean</code>
   *
   * @return number of updated records
   */
  @Override
  public int updateContactBean1(Channel value) {
    if (updateContactBean1PreparedStatement13==null) {
      // generate static SQL for statement
      String _sql="UPDATE channel SET name=?, owner_uid=?, uid=?, update_time=? WHERE id=?";
      updateContactBean1PreparedStatement13 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateContactBean1PreparedStatement13);
    _contentValues.put("name", value.getName());
    _contentValues.put("owner_uid", value.getOwnerUid());
    _contentValues.put("uid", value.getUid());
    _contentValues.put("update_time", value.getUpdateTime());

    _contentValues.addWhereArgs(String.valueOf(value.getId()));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE channel SET name=:name, owner_uid=:owner_uid, uid=:uid, update_time=:update_time WHERE id=?");

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
    int result = KriptonDatabaseHelper.updateDelete(updateContactBean1PreparedStatement13, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE channel SET name=:name, owner_uid=:ownerUid, uid=:uid, update_time=:updateTime WHERE id=${bean.id}</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>owner_uid</dt><dd>is mapped to <strong>:bean.ownerUid</strong></dd>
   * 	<dt>uid</dt><dd>is mapped to <strong>:bean.uid</strong></dd>
   * 	<dt>update_time</dt><dd>is mapped to <strong>:bean.updateTime</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   *
   * @return number of updated records
   */
  @Override
  public long updateContactBean2(Channel bean) {
    if (updateContactBean2PreparedStatement14==null) {
      // generate static SQL for statement
      String _sql="UPDATE channel SET name=?, owner_uid=?, uid=?, update_time=? WHERE id=?";
      updateContactBean2PreparedStatement14 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateContactBean2PreparedStatement14);
    _contentValues.put("name", bean.getName());
    _contentValues.put("owner_uid", bean.getOwnerUid());
    _contentValues.put("uid", bean.getUid());
    _contentValues.put("update_time", bean.getUpdateTime());

    _contentValues.addWhereArgs(String.valueOf(bean.getId()));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE channel SET name=:name, owner_uid=:owner_uid, uid=:uid, update_time=:update_time WHERE id=?");

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
    int result = KriptonDatabaseHelper.updateDelete(updateContactBean2PreparedStatement14, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE channel SET name=:name, owner_uid=:ownerUid, uid=:uid, update_time=:updateTime WHERE id=${bean.id}</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>owner_uid</dt><dd>is mapped to <strong>:bean.ownerUid</strong></dd>
   * 	<dt>uid</dt><dd>is mapped to <strong>:bean.uid</strong></dd>
   * 	<dt>update_time</dt><dd>is mapped to <strong>:bean.updateTime</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   *
   * @return <code>true</code> if record is updated, <code>false</code> otherwise
   */
  @Override
  public boolean updateContactBean3(Channel bean) {
    if (updateContactBean3PreparedStatement15==null) {
      // generate static SQL for statement
      String _sql="UPDATE channel SET name=?, owner_uid=?, uid=?, update_time=? WHERE id=?";
      updateContactBean3PreparedStatement15 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateContactBean3PreparedStatement15);
    _contentValues.put("name", bean.getName());
    _contentValues.put("owner_uid", bean.getOwnerUid());
    _contentValues.put("uid", bean.getUid());
    _contentValues.put("update_time", bean.getUpdateTime());

    _contentValues.addWhereArgs(String.valueOf(bean.getId()));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE channel SET name=:name, owner_uid=:owner_uid, uid=:uid, update_time=:update_time WHERE id=?");

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
    int result = KriptonDatabaseHelper.updateDelete(updateContactBean3PreparedStatement15, _contentValues);
    return result!=0;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, owner_uid, uid, update_time FROM channel</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Channel}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>owner_uid</dt><dd>is associated to bean's property <strong>ownerUid</strong></dd>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Channel> selectAll() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL1;
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Channel> resultList=new ArrayList<Channel>(_cursor.getCount());
      Channel resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("name");
        int index2=_cursor.getColumnIndex("owner_uid");
        int index3=_cursor.getColumnIndex("uid");
        int index4=_cursor.getColumnIndex("update_time");

        do
         {
          resultBean=new Channel();

          resultBean.setId(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.setName(_cursor.getString(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.setOwnerUid(_cursor.getString(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.setUid(_cursor.getString(index3)); }
          if (!_cursor.isNull(index4)) { resultBean.setUpdateTime(_cursor.getLong(index4)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, owner_uid, uid, update_time FROM channel WHERE update_time=${a}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Channel}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>owner_uid</dt><dd>is associated to bean's property <strong>ownerUid</strong></dd>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:a</dt><dd>is binded to method's parameter <strong>updateTimeA</strong></dd>
   * </dl>
   *
   * @param updateTimeA
   * 	is binded to <code>:a</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Channel> selectRaw1(long updateTimeA) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_RAW1_SQL2;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(updateTimeA));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Channel> resultList=new ArrayList<Channel>(_cursor.getCount());
      Channel resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("name");
        int index2=_cursor.getColumnIndex("owner_uid");
        int index3=_cursor.getColumnIndex("uid");
        int index4=_cursor.getColumnIndex("update_time");

        do
         {
          resultBean=new Channel();

          resultBean.setId(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.setName(_cursor.getString(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.setOwnerUid(_cursor.getString(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.setUid(_cursor.getString(index3)); }
          if (!_cursor.isNull(index4)) { resultBean.setUpdateTime(_cursor.getLong(index4)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, owner_uid, uid, update_time FROM channel WHERE update_time=${a}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Channel}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>owner_uid</dt><dd>is associated to bean's property <strong>ownerUid</strong></dd>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:a</dt><dd>is binded to method's parameter <strong>updateTimeA</strong></dd>
   * </dl>
   *
   * @param updateTimeA
   * 	is binded to <code>:a</code>
   * @return cursor. Closing the cursor is delegated to the calling code.
   */
  @Override
  public Cursor selectRaw2(long updateTimeA) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_RAW2_SQL3;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(updateTimeA));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    Cursor _cursor = getDatabase().query(_sql, _sqlArgs);
    // log section BEGIN
    if (_context.isLogEnabled()) {
      Logger.info("Rows found: %s",_cursor.getCount());
    }
    // log section END
    // common part generation - END
    // Specialized part - SelectRawHelper - BEGIN
    return _cursor;
    // Specialized part - SelectRawHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, owner_uid, uid, update_time FROM channel WHERE update_time=${a}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Channel}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>owner_uid</dt><dd>is associated to bean's property <strong>ownerUid</strong></dd>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:a</dt><dd>is binded to method's parameter <strong>updateTimeA</strong></dd>
   * </dl>
   *
   * @param updateTimeA
   * 	is binded to <code>:a</code>
   * @param listener
   * 	is the Channel listener
   */
  @Override
  public void selectRaw3(long updateTimeA, OnReadBeanListener<Channel> listener) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_RAW3_SQL4;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(updateTimeA));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListenerHelper - BEGIN
      Channel resultBean=new Channel();
      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("name");
        int index2=_cursor.getColumnIndex("owner_uid");
        int index3=_cursor.getColumnIndex("uid");
        int index4=_cursor.getColumnIndex("update_time");

        int rowCount=_cursor.getCount();
        do
         {
          // reset mapping
          // id does not need reset (it will be taken from db)
          resultBean.setName(null);
          resultBean.setOwnerUid(null);
          resultBean.setUid(null);
          resultBean.setUpdateTime(0L);

          // generate mapping
          resultBean.setId(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.setName(_cursor.getString(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.setOwnerUid(_cursor.getString(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.setUid(_cursor.getString(index3)); }
          if (!_cursor.isNull(index4)) { resultBean.setUpdateTime(_cursor.getLong(index4)); }

          listener.onRead(resultBean, _cursor.getPosition(), rowCount);
        } while (_cursor.moveToNext());
      }
    }
    // Specialized part - SelectBeanListenerHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, owner_uid, uid, update_time FROM channel WHERE update_time=${a}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Channel}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>owner_uid</dt><dd>is associated to bean's property <strong>ownerUid</strong></dd>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:a</dt><dd>is binded to method's parameter <strong>updateTimeA</strong></dd>
   * </dl>
   *
   * @param updateTimeA
   * 	is binded to <code>:a</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectRaw4(long updateTimeA, OnReadCursorListener listener) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_RAW4_SQL5;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(updateTimeA));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectRawListenerHelper - BEGIN

      if (_cursor.moveToFirst()) {

        do
         {
          listener.onRead(_cursor);
        } while (_cursor.moveToNext());
      }
    }
    // Specialized part - SelectRawListenerHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, owner_uid, uid, update_time FROM channel WHERE update_time=${a}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Channel}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>owner_uid</dt><dd>is associated to bean's property <strong>ownerUid</strong></dd>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:a</dt><dd>is binded to method's parameter <strong>updateTimeA</strong></dd>
   * </dl>
   *
   * @param updateTimeA
   * 	is binded to <code>:a</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public Set<Channel> selectRaw5(long updateTimeA) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_RAW5_SQL6;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(updateTimeA));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      LinkedHashSet<Channel> resultList=new LinkedHashSet<Channel>();
      Channel resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("name");
        int index2=_cursor.getColumnIndex("owner_uid");
        int index3=_cursor.getColumnIndex("uid");
        int index4=_cursor.getColumnIndex("update_time");

        do
         {
          resultBean=new Channel();

          resultBean.setId(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.setName(_cursor.getString(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.setOwnerUid(_cursor.getString(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.setUid(_cursor.getString(index3)); }
          if (!_cursor.isNull(index4)) { resultBean.setUpdateTime(_cursor.getLong(index4)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT count(*) FROM channel WHERE update_time=${bean.updateTime}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Channel}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>count(*)</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:bean.updateTime</dt><dd>is binded to method's parameter <strong>value.updateTime</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as <code>:bean</code>
   * @return single value extracted by query.
   */
  @Override
  public long selectBean1(Channel value) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BEAN1_SQL7;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(value.getUpdateTime()));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectScalarHelper - BEGIN
      long result=0L;

      if (_cursor.moveToFirst()) {

        if (_cursor.isNull(0)) { return 0L; }
        result=_cursor.getLong(0);
      }
      return result;
    }
    // Specialized part - SelectScalarHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT update_time FROM channel WHERE update_time=${bean.updateTime}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Channel}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:bean.updateTime</dt><dd>is binded to method's parameter <strong>value.updateTime</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as <code>:bean</code>
   * @param listener
   * 	is the Channel listener
   */
  @Override
  public void selectBean2(Channel value, OnReadBeanListener<Channel> listener) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BEAN2_SQL8;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(value.getUpdateTime()));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListenerHelper - BEGIN
      Channel resultBean=new Channel();
      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("update_time");

        int rowCount=_cursor.getCount();
        do
         {
          // reset mapping
          // id does not need reset (it will be taken from db)
          resultBean.setName(null);
          resultBean.setOwnerUid(null);
          resultBean.setUid(null);
          resultBean.setUpdateTime(0L);

          // generate mapping
          if (!_cursor.isNull(index0)) { resultBean.setUpdateTime(_cursor.getLong(index0)); }

          listener.onRead(resultBean, _cursor.getPosition(), rowCount);
        } while (_cursor.moveToNext());
      }
    }
    // Specialized part - SelectBeanListenerHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT update_time FROM channel WHERE update_time=${bean.updateTime}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Channel}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:bean.updateTime</dt><dd>is binded to method's parameter <strong>value.updateTime</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as <code>:bean</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectBean3(Channel value, OnReadCursorListener listener) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BEAN3_SQL9;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(value.getUpdateTime()));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectRawListenerHelper - BEGIN

      if (_cursor.moveToFirst()) {

        do
         {
          listener.onRead(_cursor);
        } while (_cursor.moveToNext());
      }
    }
    // Specialized part - SelectRawListenerHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT update_time FROM channel WHERE update_time=${bean.updateTime}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Channel}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:bean.updateTime</dt><dd>is binded to method's parameter <strong>value.updateTime</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as <code>:bean</code>
   * @return cursor. Closing the cursor is delegated to the calling code.
   */
  @Override
  public Cursor selectBean4(Channel value) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BEAN4_SQL10;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(value.getUpdateTime()));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    Cursor _cursor = getDatabase().query(_sql, _sqlArgs);
    // log section BEGIN
    if (_context.isLogEnabled()) {
      Logger.info("Rows found: %s",_cursor.getCount());
    }
    // log section END
    // common part generation - END
    // Specialized part - SelectRawHelper - BEGIN
    return _cursor;
    // Specialized part - SelectRawHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT update_time FROM channel WHERE update_time=${bean.updateTime}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Channel}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:bean.updateTime</dt><dd>is binded to method's parameter <strong>value.updateTime</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as <code>:bean</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Channel selectBean5(Channel value) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BEAN5_SQL11;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(value.getUpdateTime()));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Channel resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("update_time");

        resultBean=new Channel();

        if (!_cursor.isNull(index0)) { resultBean.setUpdateTime(_cursor.getLong(index0)); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT update_time FROM channel WHERE update_time=${bean.updateTime}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Channel}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:bean.updateTime</dt><dd>is binded to method's parameter <strong>value.updateTime</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as <code>:bean</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public ArrayList<Channel> selectBean6(Channel value) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BEAN6_SQL12;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(value.getUpdateTime()));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Channel> resultList=new ArrayList<Channel>(_cursor.getCount());
      Channel resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("update_time");

        do
         {
          resultBean=new Channel();

          if (!_cursor.isNull(index0)) { resultBean.setUpdateTime(_cursor.getLong(index0)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT update_time FROM channel WHERE update_time=${bean.updateTime}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Channel}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:bean.updateTime</dt><dd>is binded to method's parameter <strong>value.updateTime</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as <code>:bean</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public Set<Channel> selectBean7(Channel value) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BEAN7_SQL13;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(value.getUpdateTime()));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      LinkedHashSet<Channel> resultList=new LinkedHashSet<Channel>();
      Channel resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("update_time");

        do
         {
          resultBean=new Channel();

          if (!_cursor.isNull(index0)) { resultBean.setUpdateTime(_cursor.getLong(index0)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT update_time FROM channel WHERE update_time=${bean.updateTime}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Channel}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:bean.updateTime</dt><dd>is binded to method's parameter <strong>value.updateTime</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as <code>:bean</code>
   * @return collection of single value extracted by query.
   */
  @Override
  public List<Long> selectBean8(Channel value) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BEAN8_SQL14;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(value.getUpdateTime()));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectScalarListHelper - BEGIN

      ArrayList<Long> resultList=new ArrayList<Long>(_cursor.getCount());


      if (_cursor.moveToFirst()) {

        do
         {
          if (!_cursor.isNull(0)) {
            resultList.add(_cursor.getLong(0));
          } else {
            resultList.add(null);
          }
        } while (_cursor.moveToNext());
      }
      return resultList;
    }
    // Specialized part - SelectScalarListHelper - END
  }

  public static void clearCompiledStatements() {
    try {
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
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
