package sqlite.kripton58.list;

import android.database.Cursor;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseHelper;
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
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>LongBean</code>, based on interface <code>LongDao</code>
 * </p>
 *
 *  @see LongBean
 *  @see LongDao
 *  @see LongBeanTable
 */
public class LongDaoImpl extends Dao implements LongDao {
  /**
   * SQL definition for method selectOne
   */
  private static final String SELECT_ONE_SQL1 = "SELECT id, value, value2 FROM long_bean";

  /**
   * SQL definition for method selectOne
   */
  private static final String SELECT_ONE_SQL2 = "SELECT id, value, value2 FROM long_bean WHERE CAST(value AS TEXT)=?";

  /**
   * SQL definition for method selectOne
   */
  private static final String SELECT_ONE_SQL3 = "SELECT id, value, value2 FROM long_bean WHERE CAST(value AS TEXT)=?";

  /**
   * SQL definition for method selectOne
   */
  private static final String SELECT_ONE_SQL4 = "SELECT id, value, value2 FROM long_bean WHERE CAST(value AS TEXT)=?";

  /**
   * SQL definition for method selectList
   */
  private static final String SELECT_LIST_SQL5 = "SELECT id, value, value2 FROM long_bean WHERE CAST(value AS TEXT)=?";

  private static SupportSQLiteStatement updateOnePreparedStatement0;

  private static SupportSQLiteStatement insertPreparedStatement1;

  private static SupportSQLiteStatement insertPreparedStatement2;

  private static SupportSQLiteStatement deletePreparedStatement3;

  public LongDaoImpl(BindLongDaoFactory daoFactory) {
    super(daoFactory.getContext());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value2 FROM long_bean</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link LongBean}
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
  public LongBean selectOne() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL1;
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
      // Specialized part - SelectBeanHelper - BEGIN

      LongBean resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value");
        int index2=_cursor.getColumnIndex("value2");

        resultBean=new LongBean();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.value=LongBeanTable.parseValue(_cursor.getBlob(index1)); }
        if (!_cursor.isNull(index2)) { resultBean.value2=LongBeanTable.parseValue2(_cursor.getBlob(index2)); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value2 FROM long_bean WHERE CAST(value AS TEXT)=${value}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link LongBean}
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
   * 	<dt>:value</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>:value</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public LongBean selectOne(List<Long> value) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL2;
    // add where arguments
    _contentValues.addWhereArgs((value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)));
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

      LongBean resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value");
        int index2=_cursor.getColumnIndex("value2");

        resultBean=new LongBean();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.value=LongBeanTable.parseValue(_cursor.getBlob(index1)); }
        if (!_cursor.isNull(index2)) { resultBean.value2=LongBeanTable.parseValue2(_cursor.getBlob(index2)); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value2 FROM long_bean WHERE CAST(value AS TEXT)=${value}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link LongBean}
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
   * 	<dt>:value</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>:value</code>
   * @param listener
   * 	is the LongBean listener
   */
  @Override
  public void selectOne(List<Long> value, OnReadBeanListener<LongBean> listener) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL3;
    // add where arguments
    _contentValues.addWhereArgs((value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)));
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
      LongBean resultBean=new LongBean();
      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value");
        int index2=_cursor.getColumnIndex("value2");

        int rowCount=_cursor.getCount();
        do
         {
          // reset mapping
          // id does not need reset (it will be taken from db)
          resultBean.value=null;
          resultBean.value2=null;

          // generate mapping
          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.value=LongBeanTable.parseValue(_cursor.getBlob(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.value2=LongBeanTable.parseValue2(_cursor.getBlob(index2)); }

          listener.onRead(resultBean, _cursor.getPosition(), rowCount);
        } while (_cursor.moveToNext());
      }
    }
    // Specialized part - SelectBeanListenerHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value2 FROM long_bean WHERE CAST(value AS TEXT)=${value}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link LongBean}
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
   * 	<dt>:value</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>:value</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectOne(List<Long> value, OnReadCursorListener listener) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL4;
    // add where arguments
    _contentValues.addWhereArgs((value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)));
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
   * <pre>SELECT id, value, value2 FROM long_bean WHERE CAST(value AS TEXT)=${value}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link LongBean}
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
   * 	<dt>:value</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>:value</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<LongBean> selectList(List<Long> value) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_LIST_SQL5;
    // add where arguments
    _contentValues.addWhereArgs((value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)));
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

      ArrayList<LongBean> resultList=new ArrayList<LongBean>(_cursor.getCount());
      LongBean resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value");
        int index2=_cursor.getColumnIndex("value2");

        do
         {
          resultBean=new LongBean();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.value=LongBeanTable.parseValue(_cursor.getBlob(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.value2=LongBeanTable.parseValue2(_cursor.getBlob(index2)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE long_bean SET value=:value WHERE id=:id and CAST(value AS TEXT)=:paramValue</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>value</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * 	<dt>:paramValue</dt><dd>is mapped to method's parameter <strong>paramValue</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as updated field <strong>value</strong>
   * @param id
   * 	is used as where parameter <strong>:id</strong>
   * @param paramValue
   * 	is used as where parameter <strong>:paramValue</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(List<Long> value, long id, List<Long> paramValue) {
    if (updateOnePreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="UPDATE long_bean SET value=? WHERE id=? and CAST(value AS TEXT)=?";
      updateOnePreparedStatement0 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateOnePreparedStatement0);
    _contentValues.put("value", serializer1(value));

    _contentValues.addWhereArgs(String.valueOf(id));
    _contentValues.addWhereArgs((paramValue==null?"":new String(serializer1(paramValue),StandardCharsets.UTF_8)));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE long_bean SET value=:value WHERE id=? and CAST(value AS TEXT)=?");

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
    int result = KriptonDatabaseHelper.updateDelete(updateOnePreparedStatement0, _contentValues);
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO long_bean (id, value) VALUES (:id, :value)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>:id</strong> and method's parameter <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is binded to query's parameter <strong>:value</strong> and method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to column value <strong>id</strong>
   * @param value
   * 	is binded to column value <strong>value</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(long id, List<Long> value) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO long_bean (id, value) VALUES (?, ?)";
      insertPreparedStatement1 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement1);

    _contentValues.put("id", id);
    _contentValues.put("value", serializer1(value));

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
      Logger.info("INSERT INTO long_bean (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insertPreparedStatement1, _contentValues);
    return result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO long_bean (value, value2) VALUES (:bean.value, :bean.value2)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>:bean.value</strong></dd>
   * 	<dt>value2</dt><dd>is mapped to <strong>:bean.value2</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(LongBean bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO long_bean (value, value2) VALUES (?, ?)";
      insertPreparedStatement2 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement2);
    _contentValues.put("value", LongBeanTable.serializeValue(bean.value));
    _contentValues.put("value2", LongBeanTable.serializeValue2(bean.value2));

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
      Logger.info("INSERT INTO long_bean (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insertPreparedStatement2, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.id=result;

    return result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM long_bean WHERE CAST(value AS TEXT)=:paramValue</pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:paramValue</dt><dd>is mapped to method's parameter <strong>paramValue</strong></dd>
   * </dl>
   *
   * @param paramValue
   * 	is used as where parameter <strong>:paramValue</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(List<Long> paramValue) {
    if (deletePreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM long_bean WHERE CAST(value AS TEXT)=?";
      deletePreparedStatement3 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deletePreparedStatement3);
    _contentValues.addWhereArgs((paramValue==null?"":new String(serializer1(paramValue),StandardCharsets.UTF_8)));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM long_bean WHERE CAST(value AS TEXT)=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(deletePreparedStatement3, _contentValues);
    return result;
  }

  /**
   * for param serializer1 serialization
   */
  private byte[] serializer1(List<Long> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        int n=value.size();
        Long item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param parser1 parsing
   */
  private List<Long> parser1(byte[] input) {
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
      List<Long> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Long> collection=new ArrayList<>();
        Long item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getLongValue();
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  public static void clearCompiledStatements() {
    try {
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
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
