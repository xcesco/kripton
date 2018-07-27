package sqlite.kripton63;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * DAO implementation for entity <code>Bean63</code>, based on interface <code>BeanDao</code>
 * </p>
 *
 *  @see Bean63
 *  @see BeanDao
 *  @see Bean63Table
 */
public class BeanDaoImpl extends Dao implements BeanDao {
  private static final String SELECT_ONE_SQL1 = "SELECT id, value, value_map_enum_byte, value_map_string_byte FROM bean63";

  private static final String SELECT_ONE_SQL2 = "SELECT id, value, value_map_enum_byte, value_map_string_byte FROM bean63 WHERE id = ?";

  private static final String SELECT_ONE_SQL3 = "SELECT id, value, value_map_enum_byte, value_map_string_byte FROM bean63 WHERE id = ?";

  private static final String SELECT_LIST_SQL4 = "SELECT id, value, value_map_enum_byte, value_map_string_byte FROM bean63 WHERE id = ?";

  private static SQLiteStatement updateOnePreparedStatement0;

  private static SQLiteStatement insertPreparedStatement1;

  private static SQLiteStatement insertPreparedStatement2;

  private static final String SELECT_ONE_SQL5 = "SELECT id, value, value_map_enum_byte, value_map_string_byte FROM bean63 WHERE value=?";

  private static SQLiteStatement deletePreparedStatement3;

  private static SQLiteStatement updateOnePreparedStatement4;

  private static SQLiteStatement insertPreparedStatement5;

  private static final String SELECT_ONE_SQL6 = "SELECT id, value, value_map_enum_byte, value_map_string_byte FROM bean63 WHERE value=?";

  private static final String SELECT_CURSOR_ONE_SQL7 = "SELECT id, value, value_map_enum_byte, value_map_string_byte FROM bean63 WHERE value=?";

  private static final String SELECT_LISTENER_ONE_SQL8 = "SELECT id, value, value_map_enum_byte, value_map_string_byte FROM bean63 WHERE value=?";

  private static final String SELECT_CURSOR_LISTENER_ONE_SQL9 = "SELECT id, value, value_map_enum_byte, value_map_string_byte FROM bean63 WHERE value=?";

  private static SQLiteStatement deletePreparedStatement6;

  private static SQLiteStatement updateOnePreparedStatement7;

  private static final String SELECT_MAP_ENUM_BYTE_ONE_SQL10 = "SELECT value_map_enum_byte FROM bean63";

  private static final String SELECT_MAP_ENUM_BYTE_ONE_STRING_SQL11 = "SELECT value_map_enum_byte FROM bean63";

  public BeanDaoImpl(BindBeanDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_enum_byte, value_map_string_byte FROM bean63</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean63 selectOne() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL1;
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
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Bean63 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value");
        int index2=_cursor.getColumnIndex("value_map_enum_byte");
        int index3=_cursor.getColumnIndex("value_map_string_byte");

        resultBean=new Bean63();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.value=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(_cursor.getBlob(index2)); }
        if (!_cursor.isNull(index3)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(_cursor.getBlob(index3)); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_enum_byte, value_map_string_byte FROM bean63 WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @param listener
   * 	is the Bean63 listener
   */
  @Override
  public void selectOne(int id, OnReadBeanListener<Bean63> listener) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL2;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
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
      // common part generation - END
      // Specialized part - SelectBeanListenerHelper - BEGIN
      Bean63 resultBean=new Bean63();
      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value");
        int index2=_cursor.getColumnIndex("value_map_enum_byte");
        int index3=_cursor.getColumnIndex("value_map_string_byte");

        int rowCount=_cursor.getCount();
        do
         {
          // reset mapping
          // id does not need reset (it will be taken from db)
          resultBean.value=null;
          resultBean.valueMapEnumByte=null;
          resultBean.valueMapStringByte=null;

          // generate mapping
          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.value=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(_cursor.getBlob(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(_cursor.getBlob(index3)); }

          listener.onRead(resultBean, _cursor.getPosition(), rowCount);
        } while (_cursor.moveToNext());
      }
    }
    // Specialized part - SelectBeanListenerHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_enum_byte, value_map_string_byte FROM bean63 WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectOne(long id, OnReadCursorListener listener) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL3;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
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
   * <pre>SELECT id, value, value_map_enum_byte, value_map_string_byte FROM bean63 WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean63> selectList(long id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_LIST_SQL4;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
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
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Bean63> resultList=new ArrayList<Bean63>(_cursor.getCount());
      Bean63 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value");
        int index2=_cursor.getColumnIndex("value_map_enum_byte");
        int index3=_cursor.getColumnIndex("value_map_string_byte");

        do
         {
          resultBean=new Bean63();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.value=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(_cursor.getBlob(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(_cursor.getBlob(index3)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean63 SET value=:value, value_map_enum_byte=:valueMapEnumByte, value_map_string_byte=:valueMapStringByte WHERE id=${value.id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>:value.value</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is mapped to <strong>:value.valueMapEnumByte</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is mapped to <strong>:value.valueMapStringByte</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:value.id</dt><dd>is mapped to method's parameter <strong>value.id</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as <code>:value</code>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(Bean63 value) {
    if (updateOnePreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="UPDATE bean63 SET value=?, value_map_enum_byte=?, value_map_string_byte=? WHERE id=?";
      updateOnePreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateOnePreparedStatement0);
    _contentValues.put("value", value.value);
    _contentValues.put("value_map_enum_byte", Bean63Table.serializeValueMapEnumByte(value.valueMapEnumByte));
    _contentValues.put("value_map_string_byte", Bean63Table.serializeValueMapStringByte(value.valueMapStringByte));

    _contentValues.addWhereArgs(String.valueOf(value.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE bean63 SET value=:value, value_map_enum_byte=:value_map_enum_byte, value_map_string_byte=:value_map_string_byte WHERE id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(updateOnePreparedStatement0, _contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean63 (value, value_map_enum_byte, value_map_string_byte) VALUES (:bean.value, :bean.valueMapEnumByte, :bean.valueMapStringByte)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>:bean.value</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is mapped to <strong>:bean.valueMapEnumByte</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is mapped to <strong>:bean.valueMapStringByte</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(Bean63 bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO bean63 (value, value_map_enum_byte, value_map_string_byte) VALUES (?, ?, ?)";
      insertPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement1);
    _contentValues.put("value", bean.value);
    _contentValues.put("value_map_enum_byte", Bean63Table.serializeValueMapEnumByte(bean.valueMapEnumByte));
    _contentValues.put("value_map_string_byte", Bean63Table.serializeValueMapStringByte(bean.valueMapStringByte));

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
      Logger.info("INSERT INTO bean63 (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.id=result;

    return result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO bean63 (value_map_string_byte) VALUES (:valueMapStringByte)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>valueMapStringByte</dt><dd>is binded to query's parameter <strong>:valueMapStringByte</strong> and method's parameter <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * @param valueMapStringByte
   * 	is binded to column value <strong>value_map_string_byte</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(Map<String, Byte> valueMapStringByte) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO bean63 (value_map_string_byte) VALUES (?)";
      insertPreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement2);

    _contentValues.put("value_map_string_byte", serializer1(valueMapStringByte));

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
      Logger.info("INSERT INTO bean63 (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement2, _contentValues);
    return result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_enum_byte, value_map_string_byte FROM bean63 WHERE value=${valueMapStringByte}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:valueMapStringByte</dt><dd>is binded to method's parameter <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * @param valueMapStringByte
   * 	is binded to <code>:valueMapStringByte</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean63 selectOne(Map<String, Byte> valueMapStringByte) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL5;
    // add where arguments
    _contentValues.addWhereArgs((valueMapStringByte==null?"":new String(serializer1(valueMapStringByte),StandardCharsets.UTF_8)));
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
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Bean63 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value");
        int index2=_cursor.getColumnIndex("value_map_enum_byte");
        int index3=_cursor.getColumnIndex("value_map_string_byte");

        resultBean=new Bean63();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.value=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(_cursor.getBlob(index2)); }
        if (!_cursor.isNull(index3)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(_cursor.getBlob(index3)); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM bean63 WHERE value=:valueMapStringByte</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:valueMapStringByte</dt><dd>is mapped to method's parameter <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * @param valueMapStringByte
   * 	is used as where parameter <strong>:valueMapStringByte</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(Map<String, Byte> valueMapStringByte) {
    if (deletePreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM bean63 WHERE value=?";
      deletePreparedStatement3 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deletePreparedStatement3);
    _contentValues.addWhereArgs((valueMapStringByte==null?"":new String(serializer1(valueMapStringByte),StandardCharsets.UTF_8)));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM bean63 WHERE value=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deletePreparedStatement3, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE bean63 SET id=:id WHERE value=:valueMapStringByte</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>id</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:valueMapStringByte</dt><dd>is mapped to method's parameter <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueMapStringByte
   * 	is used as where parameter <strong>:valueMapStringByte</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, Map<String, Byte> valueMapStringByte) {
    if (updateOnePreparedStatement4==null) {
      // generate static SQL for statement
      String _sql="UPDATE bean63 SET id=? WHERE value=?";
      updateOnePreparedStatement4 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateOnePreparedStatement4);
    _contentValues.put("id", id);

    _contentValues.addWhereArgs((valueMapStringByte==null?"":new String(serializer1(valueMapStringByte),StandardCharsets.UTF_8)));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE bean63 SET id=:id WHERE value=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(updateOnePreparedStatement4, _contentValues);
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO bean63 (value_map_enum_byte) VALUES (:valueMapEnumByte)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>valueMapEnumByte</dt><dd>is binded to query's parameter <strong>:valueMapEnumByte</strong> and method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is binded to column value <strong>value_map_enum_byte</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(HashMap<EnumType, Byte> valueMapEnumByte) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement5==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO bean63 (value_map_enum_byte) VALUES (?)";
      insertPreparedStatement5 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement5);

    _contentValues.put("value_map_enum_byte", serializer2(valueMapEnumByte));

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
      Logger.info("INSERT INTO bean63 (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement5, _contentValues);
    return result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_enum_byte, value_map_string_byte FROM bean63 WHERE value=${valueMapEnumByte}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:valueMapEnumByte</dt><dd>is binded to method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is binded to <code>:valueMapEnumByte</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean63 selectOne(HashMap<EnumType, Byte> valueMapEnumByte) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL6;
    // add where arguments
    _contentValues.addWhereArgs((valueMapEnumByte==null?"":new String(serializer2(valueMapEnumByte),StandardCharsets.UTF_8)));
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
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Bean63 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value");
        int index2=_cursor.getColumnIndex("value_map_enum_byte");
        int index3=_cursor.getColumnIndex("value_map_string_byte");

        resultBean=new Bean63();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.value=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(_cursor.getBlob(index2)); }
        if (!_cursor.isNull(index3)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(_cursor.getBlob(index3)); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_enum_byte, value_map_string_byte FROM bean63 WHERE value=${valueMapEnumByte}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:valueMapEnumByte</dt><dd>is binded to method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is binded to <code>:valueMapEnumByte</code>
   * @return cursor. Closing the cursor is delegated to the calling code.
   */
  @Override
  public Cursor selectCursorOne(HashMap<EnumType, Byte> valueMapEnumByte) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_CURSOR_ONE_SQL7;
    // add where arguments
    _contentValues.addWhereArgs((valueMapEnumByte==null?"":new String(serializer2(valueMapEnumByte),StandardCharsets.UTF_8)));
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
    Cursor _cursor = database().rawQuery(_sql, _sqlArgs);
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
   * <pre>SELECT id, value, value_map_enum_byte, value_map_string_byte FROM bean63 WHERE value=${valueMapEnumByte}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:valueMapEnumByte</dt><dd>is binded to method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is binded to <code>:valueMapEnumByte</code>
   * @param listener
   * 	is the Bean63 listener
   */
  @Override
  public void selectListenerOne(HashMap<EnumType, Byte> valueMapEnumByte,
      OnReadBeanListener<Bean63> listener) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_LISTENER_ONE_SQL8;
    // add where arguments
    _contentValues.addWhereArgs((valueMapEnumByte==null?"":new String(serializer2(valueMapEnumByte),StandardCharsets.UTF_8)));
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
      // common part generation - END
      // Specialized part - SelectBeanListenerHelper - BEGIN
      Bean63 resultBean=new Bean63();
      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value");
        int index2=_cursor.getColumnIndex("value_map_enum_byte");
        int index3=_cursor.getColumnIndex("value_map_string_byte");

        int rowCount=_cursor.getCount();
        do
         {
          // reset mapping
          // id does not need reset (it will be taken from db)
          resultBean.value=null;
          resultBean.valueMapEnumByte=null;
          resultBean.valueMapStringByte=null;

          // generate mapping
          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.value=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(_cursor.getBlob(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(_cursor.getBlob(index3)); }

          listener.onRead(resultBean, _cursor.getPosition(), rowCount);
        } while (_cursor.moveToNext());
      }
    }
    // Specialized part - SelectBeanListenerHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_enum_byte, value_map_string_byte FROM bean63 WHERE value=${valueMapEnumByte}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:valueMapEnumByte</dt><dd>is binded to method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is binded to <code>:valueMapEnumByte</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectCursorListenerOne(HashMap<EnumType, Byte> valueMapEnumByte,
      OnReadCursorListener listener) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_CURSOR_LISTENER_ONE_SQL9;
    // add where arguments
    _contentValues.addWhereArgs((valueMapEnumByte==null?"":new String(serializer2(valueMapEnumByte),StandardCharsets.UTF_8)));
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
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM bean63 WHERE value=:valueMapEnumByte</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:valueMapEnumByte</dt><dd>is mapped to method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is used as where parameter <strong>:valueMapEnumByte</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(HashMap<EnumType, Byte> valueMapEnumByte) {
    if (deletePreparedStatement6==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM bean63 WHERE value=?";
      deletePreparedStatement6 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deletePreparedStatement6);
    _contentValues.addWhereArgs((valueMapEnumByte==null?"":new String(serializer2(valueMapEnumByte),StandardCharsets.UTF_8)));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM bean63 WHERE value=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deletePreparedStatement6, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE bean63 SET id=:id WHERE value=:valueMapEnumByte</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>id</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:valueMapEnumByte</dt><dd>is mapped to method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueMapEnumByte
   * 	is used as where parameter <strong>:valueMapEnumByte</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, HashMap<EnumType, Byte> valueMapEnumByte) {
    if (updateOnePreparedStatement7==null) {
      // generate static SQL for statement
      String _sql="UPDATE bean63 SET id=? WHERE value=?";
      updateOnePreparedStatement7 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateOnePreparedStatement7);
    _contentValues.put("id", id);

    _contentValues.addWhereArgs((valueMapEnumByte==null?"":new String(serializer2(valueMapEnumByte),StandardCharsets.UTF_8)));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE bean63 SET id=:id WHERE value=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(updateOnePreparedStatement7, _contentValues);
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_map_enum_byte FROM bean63</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean63> selectMapEnumByteOne() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_MAP_ENUM_BYTE_ONE_SQL10;
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
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Bean63> resultList=new ArrayList<Bean63>(_cursor.getCount());
      Bean63 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("value_map_enum_byte");

        do
         {
          resultBean=new Bean63();

          if (!_cursor.isNull(index0)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(_cursor.getBlob(index0)); }

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
   * <pre>SELECT value_map_enum_byte FROM bean63</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @return collection of single value extracted by query.
   */
  @Override
  public List<String> selectMapEnumByteOneString() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_MAP_ENUM_BYTE_ONE_STRING_SQL11;
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
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectScalarListHelper - BEGIN

      ArrayList<String> resultList=new ArrayList<String>(_cursor.getCount());


      if (_cursor.moveToFirst()) {

        do
         {
          if (!_cursor.isNull(0)) {
            resultList.add(parser3(_cursor.getBlob(0)));
          } else {
            resultList.add(null);
          }
        } while (_cursor.moveToNext());
      }
      return resultList;
    }
    // Specialized part - SelectScalarListHelper - END
  }

  /**
   * for param serializer1 serialization
   */
  private byte[] serializer1(Map<String, Byte> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        // write wrapper tag
        if (value.size()>0) {
          jacksonSerializer.writeFieldName("element");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<String, Byte> item: value.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField(null, item.getKey());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField(null);
            } else {
              jacksonSerializer.writeNumberField(null, item.getValue());
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("element");
        }
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
  private Map<String, Byte> parser1(byte[] input) {
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
      Map<String, Byte> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashMap<String, Byte> collection=new HashMap<>();
        String key=null;
        Byte value=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          jacksonParser.nextValue();
          key=jacksonParser.getText();
          jacksonParser.nextValue();
          if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
            value=jacksonParser.getByteValue();
          }
          collection.put(key, value);
          key=null;
          value=null;
          jacksonParser.nextToken();
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param serializer2 serialization
   */
  private byte[] serializer2(HashMap<EnumType, Byte> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        // write wrapper tag
        if (value.size()>0) {
          jacksonSerializer.writeFieldName("element");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<EnumType, Byte> item: value.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField(null, item.getKey().toString());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField(null);
            } else {
              jacksonSerializer.writeNumberField(null, item.getValue());
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("element");
        }
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param parser2 parsing
   */
  private HashMap<EnumType, Byte> parser2(byte[] input) {
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
      HashMap<EnumType, Byte> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashMap<EnumType, Byte> collection=new HashMap<>();
        EnumType key=null;
        Byte value=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          jacksonParser.nextValue();
           {
            String tempEnum=jacksonParser.getText();
            key=StringUtils.hasText(tempEnum)?EnumType.valueOf(tempEnum):null;
          }
          jacksonParser.nextValue();
          if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
            value=jacksonParser.getByteValue();
          }
          collection.put(key, value);
          key=null;
          value=null;
          jacksonParser.nextToken();
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param serializer3 serialization
   */
  private byte[] serializer3(String value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        jacksonSerializer.writeStringField("element", value);
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param parser3 parsing
   */
  private String parser3(byte[] input) {
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
      String result=null;
      if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
        result=jacksonParser.getText();
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  public static void clearCompiledStatements() {
    if (updateOnePreparedStatement0!=null) {
      updateOnePreparedStatement0.close();
      updateOnePreparedStatement0=null;
    }
    if (insertPreparedStatement1!=null) {
      insertPreparedStatement1.close();
      insertPreparedStatement1=null;
    }
    if (insertPreparedStatement2!=null) {
      insertPreparedStatement2.close();
      insertPreparedStatement2=null;
    }
    if (deletePreparedStatement3!=null) {
      deletePreparedStatement3.close();
      deletePreparedStatement3=null;
    }
    if (updateOnePreparedStatement4!=null) {
      updateOnePreparedStatement4.close();
      updateOnePreparedStatement4=null;
    }
    if (insertPreparedStatement5!=null) {
      insertPreparedStatement5.close();
      insertPreparedStatement5=null;
    }
    if (deletePreparedStatement6!=null) {
      deletePreparedStatement6.close();
      deletePreparedStatement6=null;
    }
    if (updateOnePreparedStatement7!=null) {
      updateOnePreparedStatement7.close();
      updateOnePreparedStatement7=null;
    }
  }
}
