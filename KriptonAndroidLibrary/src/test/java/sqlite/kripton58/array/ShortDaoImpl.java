package sqlite.kripton58.array;

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
import com.abubusoft.kripton.common.CollectionUtils;
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
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>ShortBean</code>, based on interface <code>ShortDao</code>
 * </p>
 *
 *  @see ShortBean
 *  @see ShortDao
 *  @see ShortBeanTable
 */
public class ShortDaoImpl extends AbstractDao implements ShortDao {
  protected String SELECT_ONE_SQL1 = "SELECT id, value, value2 FROM short_bean";

  protected String SELECT_ONE_SQL2 = "SELECT id, value, value2 FROM short_bean WHERE value=? and value2=?";

  protected String SELECT_ONE_SQL3 = "SELECT id, value, value2 FROM short_bean WHERE value=? and value2=?";

  protected String SELECT_ONE_SQL4 = "SELECT id, value, value2 FROM short_bean WHERE value=? and value2=?";

  protected String SELECT_LIST_SQL5 = "SELECT id, value, value2 FROM short_bean WHERE value=? and value2=?";

  private SQLiteStatement updateOnePreparedStatement0;

  private SQLiteStatement insertPreparedStatement1;

  private SQLiteStatement insertPreparedStatement2;

  private SQLiteStatement deletePreparedStatement3;

  public ShortDaoImpl(BindShortDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value2 FROM short_bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public ShortBean selectOne() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL1;
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (this.dataSource.logEnabled) {
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
      if (this.dataSource.logEnabled) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ShortBean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value2");

        resultBean=new ShortBean();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.value=ShortBeanTable.parseValue(cursor.getBlob(index1)); }
        if (!cursor.isNull(index2)) { resultBean.value2=ShortBeanTable.parseValue2(cursor.getBlob(index2)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value2 FROM short_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is binded to method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>${value}</code>
   * @param value2
   * 	is binded to <code>${value2}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public ShortBean selectOne(short[] value, Short[] value2) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL2;
    // add where arguments
    _contentValues.addWhereArgs((value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)));
    _contentValues.addWhereArgs((value2==null?"":new String(serializer2(value2),StandardCharsets.UTF_8)));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (this.dataSource.logEnabled) {
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
      if (this.dataSource.logEnabled) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ShortBean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value2");

        resultBean=new ShortBean();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.value=ShortBeanTable.parseValue(cursor.getBlob(index1)); }
        if (!cursor.isNull(index2)) { resultBean.value2=ShortBeanTable.parseValue2(cursor.getBlob(index2)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value2 FROM short_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is binded to method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>${value}</code>
   * @param value2
   * 	is binded to <code>${value2}</code>
   * @param listener
   * 	is the ShortBean listener
   */
  @Override
  public void selectOne(short[] value, Short[] value2, OnReadBeanListener<ShortBean> listener) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL3;
    // add where arguments
    _contentValues.addWhereArgs((value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)));
    _contentValues.addWhereArgs((value2==null?"":new String(serializer2(value2),StandardCharsets.UTF_8)));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (this.dataSource.logEnabled) {
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
      if (this.dataSource.logEnabled) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END
      ShortBean resultBean=new ShortBean();
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value2");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          // id does not need reset
          resultBean.value=null;
          resultBean.value2=null;

          // generate mapping
          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.value=ShortBeanTable.parseValue(cursor.getBlob(index1)); }
          if (!cursor.isNull(index2)) { resultBean.value2=ShortBeanTable.parseValue2(cursor.getBlob(index2)); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value2 FROM short_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is binded to method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>${value}</code>
   * @param value2
   * 	is binded to <code>${value2}</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectOne(short[] value, Short[] value2, OnReadCursorListener listener) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL4;
    // add where arguments
    _contentValues.addWhereArgs((value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)));
    _contentValues.addWhereArgs((value2==null?"":new String(serializer2(value2),StandardCharsets.UTF_8)));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (this.dataSource.logEnabled) {
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
      if (this.dataSource.logEnabled) {
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
   * <pre>SELECT id, value, value2 FROM short_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is binded to method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>${value}</code>
   * @param value2
   * 	is binded to <code>${value2}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<ShortBean> selectList(short[] value, Short[] value2) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_LIST_SQL5;
    // add where arguments
    _contentValues.addWhereArgs((value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)));
    _contentValues.addWhereArgs((value2==null?"":new String(serializer2(value2),StandardCharsets.UTF_8)));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (this.dataSource.logEnabled) {
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
      if (this.dataSource.logEnabled) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ArrayList<ShortBean> resultList=new ArrayList<ShortBean>(cursor.getCount());
      ShortBean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value2");

        do
         {
          resultBean=new ShortBean();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.value=ShortBeanTable.parseValue(cursor.getBlob(index1)); }
          if (!cursor.isNull(index2)) { resultBean.value2=ShortBeanTable.parseValue2(cursor.getBlob(index2)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE short_bean SET id=:id WHERE value=${value} and value2=${value2}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>id</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is mapped to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is mapped to method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param value
   * 	is used as where parameter <strong>${value}</strong>
   * @param value2
   * 	is used as where parameter <strong>${value2}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, short[] value, Short[] value2) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.put("id", id);

    _contentValues.addWhereArgs((value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)));
    _contentValues.addWhereArgs((value2==null?"":new String(serializer2(value2),StandardCharsets.UTF_8)));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (updateOnePreparedStatement0==null) {
      StringBuilder _sqlBuilder=getSQLStringBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" value=? and value2=?";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="UPDATE short_bean SET id=? WHERE value=? and value2=?";
      updateOnePreparedStatement0 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    // log section BEGIN
    if (this.dataSource.logEnabled) {

      // display log
      Logger.info("UPDATE short_bean SET id=:id WHERE value=? and value2=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, updateOnePreparedStatement0, _contentValues);
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO short_bean (id, value, value2) VALUES (${id}, ${value}, ${value2})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is binded to query's parameter <strong>${value}</strong> and method's parameter <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is binded to query's parameter <strong>${value2}</strong> and method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to column value <strong>id</strong>
   * @param value
   * 	is binded to column value <strong>value</strong>
   * @param value2
   * 	is binded to column value <strong>value2</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(long id, short[] value, Short[] value2) {
    KriptonContentValues _contentValues=contentValuesForUpdate();

    _contentValues.put("id", id);
    if (value!=null) {
      _contentValues.put("value", serializer1(value));
    } else {
      _contentValues.putNull("value");
    }
    if (value2!=null) {
      _contentValues.put("value2", serializer2(value2));
    } else {
      _contentValues.putNull("value2");
    }

    // log section BEGIN
    if (this.dataSource.logEnabled) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO short_bean (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    }
    // log section END
    // insert operation
    if (insertPreparedStatement1==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO short_bean (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertPreparedStatement1 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertPreparedStatement1, _contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO short_bean (value, value2) VALUES (${bean.value}, ${bean.value2})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>${bean.value}</strong></dd>
   * 	<dt>value2</dt><dd>is mapped to <strong>${bean.value2}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(ShortBean bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    if (bean.value!=null) {
      _contentValues.put("value", ShortBeanTable.serializeValue(bean.value));
    } else {
      _contentValues.putNull("value");
    }
    if (bean.value2!=null) {
      _contentValues.put("value2", ShortBeanTable.serializeValue2(bean.value2));
    } else {
      _contentValues.putNull("value2");
    }

    // log section BEGIN
    if (this.dataSource.logEnabled) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO short_bean (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    }
    // log section END
    // insert operation
    if (insertPreparedStatement2==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO short_bean (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertPreparedStatement2 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertPreparedStatement2, _contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM short_bean WHERE value=${value} and value2=${value2}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is mapped to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is mapped to method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as where parameter <strong>${value}</strong>
   * @param value2
   * 	is used as where parameter <strong>${value2}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(short[] value, Short[] value2) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.addWhereArgs((value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)));
    _contentValues.addWhereArgs((value2==null?"":new String(serializer2(value2),StandardCharsets.UTF_8)));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (deletePreparedStatement3==null) {
      StringBuilder _sqlBuilder=getSQLStringBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" value=? and value2=?";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="DELETE FROM short_bean WHERE value=? and value2=?";
      deletePreparedStatement3 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    // log section BEGIN
    if (this.dataSource.logEnabled) {

      // display log
      Logger.info("DELETE FROM short_bean WHERE value=? and value2=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, deletePreparedStatement3, _contentValues);
    return result;
  }

  /**
   * for param serializer1 serialization
   */
  private byte[] serializer1(short[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        int n=value.length;
        short item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          jacksonSerializer.writeNumber(item);
        }
        jacksonSerializer.writeEndArray();
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
  private short[] parser1(byte[] input) {
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
      short[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Short> collection=new ArrayList<>();
        Short item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getShortValue();
          }
          collection.add(item);
        }
        result=CollectionUtils.asShortTypeArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param serializer2 serialization
   */
  private byte[] serializer2(Short[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        int n=value.length;
        Short item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
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
  private Short[] parser2(byte[] input) {
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
      Short[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Short> collection=new ArrayList<>();
        Short item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getShortValue();
          }
          collection.add(item);
        }
        result=CollectionUtils.asShortArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  public void clearCompiledStatements() {
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
  }
}
