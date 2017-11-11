package sqlite.kripton64;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Bean64A</code>, based on interface <code>Bean64ADao</code>
 * </p>
 *
 *  @see Bean64A
 *  @see Bean64ADao
 *  @see Bean64ATable
 */
public class Bean64ADaoImpl extends AbstractDao implements Bean64ADao {
  protected String SELECT_ALL_SQL1 = "SELECT value_map_string_bean, value_set_string, value_string, id FROM bean64_a";

  protected String SELECT_LIST_SQL2 = "SELECT value_map_string_bean, value_set_string, value_string, id FROM bean64_a WHERE id=?";

  private SQLiteStatement insertPreparedStatement0;

  public Bean64ADaoImpl(BindBean64ADataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_map_string_bean, value_set_string, value_string, id FROM bean64_a</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean64A> selectAll() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL1;
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

      ArrayList<Bean64A> resultList=new ArrayList<Bean64A>(cursor.getCount());
      Bean64A resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_map_string_bean");
        int index1=cursor.getColumnIndex("value_set_string");
        int index2=cursor.getColumnIndex("value_string");
        int index3=cursor.getColumnIndex("id");

        do
         {
          resultBean=new Bean64A();

          if (!cursor.isNull(index0)) { resultBean.valueMapStringBean=Bean64ATable.parseValueMapStringBean(cursor.getBlob(index0)); }
          if (!cursor.isNull(index1)) { resultBean.valueSetString=Bean64ATable.parseValueSetString(cursor.getBlob(index1)); }
          if (!cursor.isNull(index2)) { resultBean.valueString=cursor.getString(index2); }
          resultBean.id=cursor.getLong(index3);

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_map_string_bean, value_set_string, value_string, id FROM bean64_a WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
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
  public List<Bean64A> selectList(long id) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_LIST_SQL2;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
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

      ArrayList<Bean64A> resultList=new ArrayList<Bean64A>(cursor.getCount());
      Bean64A resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_map_string_bean");
        int index1=cursor.getColumnIndex("value_set_string");
        int index2=cursor.getColumnIndex("value_string");
        int index3=cursor.getColumnIndex("id");

        do
         {
          resultBean=new Bean64A();

          if (!cursor.isNull(index0)) { resultBean.valueMapStringBean=Bean64ATable.parseValueMapStringBean(cursor.getBlob(index0)); }
          if (!cursor.isNull(index1)) { resultBean.valueSetString=Bean64ATable.parseValueSetString(cursor.getBlob(index1)); }
          if (!cursor.isNull(index2)) { resultBean.valueString=cursor.getString(index2); }
          resultBean.id=cursor.getLong(index3);

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean64_a (value_map_string_bean, value_set_string, value_string) VALUES (${bean.valueMapStringBean}, ${bean.valueSetString}, ${bean.valueString})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_map_string_bean</dt><dd>is mapped to <strong>${bean.valueMapStringBean}</strong></dd>
   * 	<dt>value_set_string</dt><dd>is mapped to <strong>${bean.valueSetString}</strong></dd>
   * 	<dt>value_string</dt><dd>is mapped to <strong>${bean.valueString}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(Bean64A bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    if (bean.valueMapStringBean!=null) {
      _contentValues.put("value_map_string_bean", Bean64ATable.serializeValueMapStringBean(bean.valueMapStringBean));
    } else {
      _contentValues.putNull("value_map_string_bean");
    }
    if (bean.valueSetString!=null) {
      _contentValues.put("value_set_string", Bean64ATable.serializeValueSetString(bean.valueSetString));
    } else {
      _contentValues.putNull("value_set_string");
    }
    if (bean.valueString!=null) {
      _contentValues.put("value_string", bean.valueString);
    } else {
      _contentValues.putNull("value_string");
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
      Logger.info("INSERT INTO bean64_a (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    if (insertPreparedStatement0==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO bean64_a (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertPreparedStatement0, _contentValues);
    bean.id=result;

    return result;
  }

  public void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
  }
}
