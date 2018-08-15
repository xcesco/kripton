package sqlite.kripton205;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;

/**
 * <p>
 * DAO implementation for entity <code>Bean205</code>, based on interface <code>Bean205Dao</code>
 * </p>
 *
 *  @see Bean205
 *  @see Bean205Dao
 *  @see Bean205Table
 */
public class Bean205DaoImpl extends Dao implements Bean205Dao {
  private static final String SELECT_BY_BEAN_SQL1 = "SELECT id, name, surname FROM bean205 WHERE name like ? || '%'";

  private static SQLiteStatement insertPreparedStatement0;

  public Bean205DaoImpl(BindBean96DaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname FROM bean205 WHERE name like ${name} || '%'</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean205}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:name</dt><dd>is binded to method's parameter <strong>name</strong></dd>
   * </dl>
   *
   * @param name
   * 	is binded to <code>:name</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean205 selectByBean(String name) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_BEAN_SQL1;
    // add where arguments
    _contentValues.addWhereArgs((name==null?"":name));
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
      // Specialized part - SelectBeanHelper - BEGIN

      Bean205 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("name");
        int index2=_cursor.getColumnIndex("surname");

        resultBean=new Bean205();

        resultBean.setId(_cursor.getLong(index0));
        if (!_cursor.isNull(index1)) { resultBean.name=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { resultBean.surname=_cursor.getString(index2); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO bean205 (name, surname) VALUES (:bean.name, :bean.surname)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>:bean.surname</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <code>true</code> if record is inserted, <code>false</code> otherwise
   */
  @Override
  public boolean insert(Bean205 bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO bean205 (name, surname) VALUES (?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("name", bean.name);
    _contentValues.put("surname", bean.surname);

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
      Logger.info("INSERT INTO bean205 (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    bean.setId(result);

    return result!=-1;
    // Specialized Insert - InsertType - END
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
  }
}
