package sqlite.kripton63;

import android.database.Cursor;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.android.sqlite.database.KriptonContentValues;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
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
public class BeanDaoImpl extends AbstractDao implements BeanDao {
  public BeanDaoImpl(BindBeanDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean63 selectOne() {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sqlWhereStatement="";

    // build where condition
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean63 resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value_map_string_byte");
        int index3=cursor.getColumnIndex("value_map_enum_byte");

        resultBean=new Bean63();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.value=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(cursor.getBlob(index2)); }
        if (!cursor.isNull(index3)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(cursor.getBlob(index3)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @param listener
   * 	is the Bean63 listener
   */
  @Override
  public void selectOne(int id, OnReadBeanListener<Bean63> listener) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs(String.valueOf(id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Bean63 resultBean=new Bean63();
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value_map_string_byte");
        int index3=cursor.getColumnIndex("value_map_enum_byte");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          // id does not need reset
          resultBean.value=null;
          resultBean.valueMapStringByte=null;
          resultBean.valueMapEnumByte=null;

          // generate mapping
          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.value=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(cursor.getBlob(index2)); }
          if (!cursor.isNull(index3)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(cursor.getBlob(index3)); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectOne(long id, OnReadCursorListener listener) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs(String.valueOf(id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

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
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean63> selectList(long id) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs(String.valueOf(id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Bean63> resultList=new LinkedList<Bean63>();
      Bean63 resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value_map_string_byte");
        int index3=cursor.getColumnIndex("value_map_enum_byte");

        do
         {
          resultBean=new Bean63();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.value=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(cursor.getBlob(index2)); }
          if (!cursor.isNull(index3)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(cursor.getBlob(index3)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean63 SET value=:value, value_map_string_byte=:valueMapStringByte, value_map_enum_byte=:valueMapEnumByte WHERE id=${value.id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>${value.value}</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is mapped to <strong>${value.valueMapStringByte}</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is mapped to <strong>${value.valueMapEnumByte}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${value.id}</dt><dd>is mapped to method's parameter <strong>value.id</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as ${value}
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(Bean63 value) {
    KriptonContentValues _contentValues=contentValues();
    if (value.value!=null) {
      _contentValues.put("value", value.value);
    } else {
      _contentValues.putNull("value");
    }
    if (value.valueMapStringByte!=null) {
      _contentValues.put("value_map_string_byte", Bean63Table.serializeValueMapStringByte(value.valueMapStringByte));
    } else {
      _contentValues.putNull("value_map_string_byte");
    }
    if (value.valueMapEnumByte!=null) {
      _contentValues.put("value_map_enum_byte", Bean63Table.serializeValueMapEnumByte(value.valueMapEnumByte));
    } else {
      _contentValues.putNull("value_map_enum_byte");
    }

    _contentValues.addWhereArgs(String.valueOf(value.id));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("UPDATE bean63 SET value=:value, value_map_string_byte=:valueMapStringByte, value_map_enum_byte=:valueMapEnumByte WHERE id=?");

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:_contentValues.keySet()) {
      _contentValue=_contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().update("bean63", _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());;
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean63 (value, value_map_string_byte, value_map_enum_byte) VALUES (${bean.value}, ${bean.valueMapStringByte}, ${bean.valueMapEnumByte})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>${bean.value}</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is mapped to <strong>${bean.valueMapStringByte}</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is mapped to <strong>${bean.valueMapEnumByte}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(Bean63 bean) {
    KriptonContentValues _contentValues=contentValues();
    if (bean.value!=null) {
      _contentValues.put("value", bean.value);
    } else {
      _contentValues.putNull("value");
    }
    if (bean.valueMapStringByte!=null) {
      _contentValues.put("value_map_string_byte", Bean63Table.serializeValueMapStringByte(bean.valueMapStringByte));
    } else {
      _contentValues.putNull("value_map_string_byte");
    }
    if (bean.valueMapEnumByte!=null) {
      _contentValues.put("value_map_enum_byte", Bean63Table.serializeValueMapEnumByte(bean.valueMapEnumByte));
    } else {
      _contentValues.putNull("value_map_enum_byte");
    }

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:_contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO bean63 (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:_contentValues.keySet()) {
      _contentValue=_contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    long result = database().insert("bean63", null, _contentValues.values());
    bean.id=result;

    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO bean63 (value_map_string_byte) VALUES (${valueMapStringByte})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>value_map_string_byte</dt><dd>is binded to query's parameter <strong>${valueMapStringByte}</strong> and method's parameter <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * @param valueMapStringByte
   * 	is binded to column value <strong>value_map_string_byte</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(Map<String, Byte> valueMapStringByte) {
    KriptonContentValues _contentValues=contentValues();

    if (valueMapStringByte!=null) {
      _contentValues.put("value_map_string_byte", serializer1(valueMapStringByte));
    } else {
      _contentValues.putNull("value_map_string_byte");
    }

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:_contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO bean63 (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:_contentValues.keySet()) {
      _contentValue=_contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    long result = database().insert("bean63", null, _contentValues.values());
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=${valueMapStringByte}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueMapStringByte}</dt><dd>is binded to method's parameter <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * @param valueMapStringByte
   * 	is binded to <code>${valueMapStringByte}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean63 selectOne(Map<String, Byte> valueMapStringByte) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE value=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs((valueMapStringByte==null?"":new String(serializer1(valueMapStringByte),StandardCharsets.UTF_8)));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean63 resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value_map_string_byte");
        int index3=cursor.getColumnIndex("value_map_enum_byte");

        resultBean=new Bean63();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.value=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(cursor.getBlob(index2)); }
        if (!cursor.isNull(index3)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(cursor.getBlob(index3)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM bean63 WHERE value=${valueMapStringByte}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueMapStringByte}</dt><dd>is mapped to method's parameter <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * @param valueMapStringByte
   * 	is used as where parameter <strong>${valueMapStringByte}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(Map<String, Byte> valueMapStringByte) {
    KriptonContentValues _contentValues=contentValues();
    _contentValues.addWhereArgs((valueMapStringByte==null?"":new String(serializer1(valueMapStringByte),StandardCharsets.UTF_8)));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" value=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("DELETE FROM bean63 WHERE value=?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().delete("bean63", _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE bean63 SET id=:id WHERE value=${valueMapStringByte}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>id</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueMapStringByte}</dt><dd>is mapped to method's parameter <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueMapStringByte
   * 	is used as where parameter <strong>${valueMapStringByte}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, Map<String, Byte> valueMapStringByte) {
    KriptonContentValues _contentValues=contentValues();
    _contentValues.put("id", id);

    _contentValues.addWhereArgs((valueMapStringByte==null?"":new String(serializer1(valueMapStringByte),StandardCharsets.UTF_8)));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" value=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("UPDATE bean63 SET id=:id WHERE value=?");

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:_contentValues.keySet()) {
      _contentValue=_contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().update("bean63", _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());;
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO bean63 (value_map_enum_byte) VALUES (${valueMapEnumByte})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>value_map_enum_byte</dt><dd>is binded to query's parameter <strong>${valueMapEnumByte}</strong> and method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is binded to column value <strong>value_map_enum_byte</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(HashMap<EnumType, Byte> valueMapEnumByte) {
    KriptonContentValues _contentValues=contentValues();

    if (valueMapEnumByte!=null) {
      _contentValues.put("value_map_enum_byte", serializer2(valueMapEnumByte));
    } else {
      _contentValues.putNull("value_map_enum_byte");
    }

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:_contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO bean63 (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:_contentValues.keySet()) {
      _contentValue=_contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    long result = database().insert("bean63", null, _contentValues.values());
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=${valueMapEnumByte}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueMapEnumByte}</dt><dd>is binded to method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is binded to <code>${valueMapEnumByte}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean63 selectOne(HashMap<EnumType, Byte> valueMapEnumByte) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE value=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs((valueMapEnumByte==null?"":new String(serializer2(valueMapEnumByte),StandardCharsets.UTF_8)));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean63 resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value_map_string_byte");
        int index3=cursor.getColumnIndex("value_map_enum_byte");

        resultBean=new Bean63();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.value=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(cursor.getBlob(index2)); }
        if (!cursor.isNull(index3)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(cursor.getBlob(index3)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=${valueMapEnumByte}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueMapEnumByte}</dt><dd>is binded to method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is binded to <code>${valueMapEnumByte}</code>
   * @return cursor. Closing the cursor is delegated to the calling code.
   */
  @Override
  public Cursor selectCursorOne(HashMap<EnumType, Byte> valueMapEnumByte) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE value=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs((valueMapEnumByte==null?"":new String(serializer2(valueMapEnumByte),StandardCharsets.UTF_8)));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    Cursor cursor = database().rawQuery(_sql, _sqlArgs);
    Logger.info("Rows found: %s",cursor.getCount());
    return cursor;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=${valueMapEnumByte}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueMapEnumByte}</dt><dd>is binded to method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is binded to <code>${valueMapEnumByte}</code>
   * @param listener
   * 	is the Bean63 listener
   */
  @Override
  public void selectListenerOne(HashMap<EnumType, Byte> valueMapEnumByte,
      OnReadBeanListener<Bean63> listener) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE value=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs((valueMapEnumByte==null?"":new String(serializer2(valueMapEnumByte),StandardCharsets.UTF_8)));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Bean63 resultBean=new Bean63();
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value_map_string_byte");
        int index3=cursor.getColumnIndex("value_map_enum_byte");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          // id does not need reset
          resultBean.value=null;
          resultBean.valueMapStringByte=null;
          resultBean.valueMapEnumByte=null;

          // generate mapping
          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.value=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(cursor.getBlob(index2)); }
          if (!cursor.isNull(index3)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(cursor.getBlob(index3)); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=${valueMapEnumByte}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueMapEnumByte}</dt><dd>is binded to method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is binded to <code>${valueMapEnumByte}</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectCursorListenerOne(HashMap<EnumType, Byte> valueMapEnumByte,
      OnReadCursorListener listener) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE value=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs((valueMapEnumByte==null?"":new String(serializer2(valueMapEnumByte),StandardCharsets.UTF_8)));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      if (cursor.moveToFirst()) {

        do
         {
          listener.onRead(cursor);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM bean63 WHERE value=${valueMapEnumByte}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueMapEnumByte}</dt><dd>is mapped to method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is used as where parameter <strong>${valueMapEnumByte}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(HashMap<EnumType, Byte> valueMapEnumByte) {
    KriptonContentValues _contentValues=contentValues();
    _contentValues.addWhereArgs((valueMapEnumByte==null?"":new String(serializer2(valueMapEnumByte),StandardCharsets.UTF_8)));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" value=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("DELETE FROM bean63 WHERE value=?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().delete("bean63", _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE bean63 SET id=:id WHERE value=${valueMapEnumByte}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>id</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueMapEnumByte}</dt><dd>is mapped to method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueMapEnumByte
   * 	is used as where parameter <strong>${valueMapEnumByte}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, HashMap<EnumType, Byte> valueMapEnumByte) {
    KriptonContentValues _contentValues=contentValues();
    _contentValues.put("id", id);

    _contentValues.addWhereArgs((valueMapEnumByte==null?"":new String(serializer2(valueMapEnumByte),StandardCharsets.UTF_8)));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" value=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("UPDATE bean63 SET id=:id WHERE value=?");

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:_contentValues.keySet()) {
      _contentValue=_contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().update("bean63", _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());;
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
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT value_map_enum_byte FROM bean63");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sqlWhereStatement="";

    // build where condition
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Bean63> resultList=new LinkedList<Bean63>();
      Bean63 resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_map_enum_byte");

        do
         {
          resultBean=new Bean63();

          if (!cursor.isNull(index0)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(cursor.getBlob(index0)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
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
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT value_map_enum_byte FROM bean63");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sqlWhereStatement="";

    // build where condition
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<String> resultList=new LinkedList<String>();


      if (cursor.moveToFirst()) {

        do
         {
          if (!cursor.isNull(0)) {
            resultList.add(parser3(cursor.getBlob(0)));
          } else {
            resultList.add(null);
          }
        } while (cursor.moveToNext());
      }
      return resultList;
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
}
