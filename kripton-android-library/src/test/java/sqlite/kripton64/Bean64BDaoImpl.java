package sqlite.kripton64;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Bean64B</code>, based on interface <code>Bean64BDao</code>
 * </p>
 *
 *  @see Bean64B
 *  @see Bean64BDao
 *  @see Bean64BTable
 */
public class Bean64BDaoImpl extends Dao implements Bean64BDao {
  /**
   * SQL definition for method selectAll
   */
  private static final String SELECT_ALL_SQL2 = "SELECT id, value_map_string_bean, value_set_string, value_string FROM bean64_b";

  /**
   * SQL definition for method selectList
   */
  private static final String SELECT_LIST_SQL4 = "SELECT id, value_map_string_bean, value_set_string, value_string FROM bean64_b WHERE id=?";

  private static SQLiteStatement insertPreparedStatement0;

  public Bean64BDaoImpl(BindBean64BDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value_map_string_bean, value_set_string, value_string FROM bean64_b</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean64B}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean64B> selectAll() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL2;
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
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Bean64B> resultList=new ArrayList<Bean64B>(_cursor.getCount());
      Bean64B resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value_map_string_bean");
        int index2=_cursor.getColumnIndex("value_set_string");
        int index3=_cursor.getColumnIndex("value_string");

        do
         {
          resultBean=new Bean64B();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.valueMapStringBean=Bean64BTable.parseValueMapStringBean(_cursor.getBlob(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.valueSetString=Bean64BTable.parseValueSetString(_cursor.getBlob(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.valueString=_cursor.getString(index3); }

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
   * <pre>SELECT id, value_map_string_bean, value_set_string, value_string FROM bean64_b WHERE id=${id}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean64B}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
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
  public List<Bean64B> selectList(long id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_LIST_SQL4;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
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
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Bean64B> resultList=new ArrayList<Bean64B>(_cursor.getCount());
      Bean64B resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value_map_string_bean");
        int index2=_cursor.getColumnIndex("value_set_string");
        int index3=_cursor.getColumnIndex("value_string");

        do
         {
          resultBean=new Bean64B();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.valueMapStringBean=Bean64BTable.parseValueMapStringBean(_cursor.getBlob(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.valueSetString=Bean64BTable.parseValueSetString(_cursor.getBlob(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.valueString=_cursor.getString(index3); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO bean64_b (value_map_string_bean, value_set_string, value_string) VALUES (:bean.valueMapStringBean, :bean.valueSetString, :bean.valueString)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_map_string_bean</dt><dd>is mapped to <strong>:bean.valueMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is mapped to <strong>:bean.valueSetString</strong></dd>
   * 	<dt>value_string</dt><dd>is mapped to <strong>:bean.valueString</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(Bean64B bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO bean64_b (value_map_string_bean, value_set_string, value_string) VALUES (?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("value_map_string_bean", Bean64BTable.serializeValueMapStringBean(bean.valueMapStringBean));
    _contentValues.put("value_set_string", Bean64BTable.serializeValueSetString(bean.valueSetString));
    _contentValues.put("value_string", bean.valueString);

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
      Logger.info("INSERT INTO bean64_b (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement0, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.id=result;

    return result;
    // Specialized Insert - InsertType - END
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
  }
}
