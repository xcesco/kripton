package sqlite.feature.in.case3;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SpreadUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>City</code>, based on interface <code>DaoCity</code>
 * </p>
 *
 *  @see City
 *  @see DaoCity
 *  @see CityTable
 */
public class DaoCityImpl extends Dao implements DaoCity {
  private static SQLiteStatement insertPreparedStatement0;

  public DaoCityImpl(BindAppDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO city (name) VALUES (:bean.name)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(City bean) {
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO city (name) VALUES (?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("name", bean.name);

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
      Logger.info("INSERT INTO city (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    bean.id=result;

    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name FROM city WHERE id in (:{args})</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:args</dt><dd>is binded to method's parameter <strong>args</strong></dd>
   * </dl>
   *
   * @param args
   * 	is binded to <code>:args</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<City> selectAll(Long[] args) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT id, name FROM city");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN
    // need to use SpreadUtils operations

    // manage WHERE statement
    String _sqlWhereStatement=String.format(" WHERE id in (%s)",SpreadUtils.generateQuestion(args));
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    String _sql=_sqlBuilder.toString();
    // add where arguments
    if (args!=null) {
      // args is managed as spread param
      for (int _i=0; _i<args.length;_i++) {
         _contentValues.addWhereArgs((args[_i]==null?"":String.valueOf(args[_i])));
      }
    }
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

      ArrayList<City> resultList=new ArrayList<City>(_cursor.getCount());
      City resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("name");

        do
         {
          resultBean=new City();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.name=_cursor.getString(index1); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
  }
}
