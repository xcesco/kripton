package sqlite.feature.immutable.pagedresult;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.PagedResultImpl;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>DaoPerson</code>
 * </p>
 *
 *  @see Person
 *  @see DaoPerson
 *  @see PersonTable
 */
public class DaoPersonImpl extends Dao implements DaoPerson {
  private static SQLiteStatement insertOnePreparedStatement0;

  /**
   * SQL definition for method selectAll
   */
  private static final String SELECT_ALL_SQL1 = "SELECT pk, birth_city, birth_day, name, surname FROM person ORDER BY name";

  private static SQLiteStatement deleteAllPreparedStatement1;

  public DaoPersonImpl(BindAppDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, birth_city, birth_day, name, surname FROM person ORDER BY name LIMIT 10 OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>pk</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * @return paginated result.
   */
  @Override
  public PagedResultImpl<Person> select() {
    final PaginatedResult9 paginatedResult=new PaginatedResult9();
    // common part generation - BEGIN
    // common part generation - END
    return paginatedResult;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, birth_city, birth_day, name, surname FROM person ORDER BY name LIMIT 10 OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>pk</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * @param paginatedResult
   * 	handler of paginated result
   * @return result list
   */
  private List<Person> select(PaginatedResult9 paginatedResult) {
    // total count - BEGIN
    paginatedResult.setTotalCount(this.selectTotalCount(paginatedResult));
    // total count - END
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT pk, birth_city, birth_day, name, surname FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sortOrder=null;
    String _sqlWhereStatement="";
    // generation order - BEGIN
    String _sqlOrderByStatement=" ORDER BY name";
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

    // generation limit - BEGIN
    String _sqlLimitStatement=" LIMIT "+paginatedResult.getPageSize();
    _sqlBuilder.append(_sqlLimitStatement);
    // generation limit - END

    // generation offset - BEGIN
    String _sqlOffsetStatement=" OFFSET "+paginatedResult.getOffset();
    _sqlBuilder.append(_sqlOffsetStatement);
    // generation offset - END

    String _sql=_sqlBuilder.toString();
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
      // Specialized part II - SelectPaginatedResultHelper - BEGIN

      List<Person> resultList=new ArrayList<Person>(_cursor.getCount());
      Person resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      String __pk=null;
      String __name=null;
      String __surname=null;
      String __birthCity=null;
      Date __birthDay=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("pk");
        int index1=_cursor.getColumnIndex("birth_city");
        int index2=_cursor.getColumnIndex("birth_day");
        int index3=_cursor.getColumnIndex("name");
        int index4=_cursor.getColumnIndex("surname");

        do
         {
          // reset temporary variable for immutable POJO
          // immutable object: initialize temporary variables for properties
          __pk=null;
          __name=null;
          __surname=null;
          __birthCity=null;
          __birthDay=null;
          __pk=_cursor.getString(index0);
          if (!_cursor.isNull(index1)) { __birthCity=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { __birthDay=DateUtils.read(_cursor.getString(index2)); }
          if (!_cursor.isNull(index3)) { __name=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { __surname=_cursor.getString(index4); }

          // define immutable POJO
          // immutable object: inizialize object
          resultBean=new Person(__pk,__name,__surname,__birthCity,__birthDay);
          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return (resultList==null ? null : Collections.unmodifiableList(resultList));
    }
    // Specialized part II - SelectPaginatedResultHelper - END
  }

  private int selectTotalCount(PaginatedResult9 paginatedResult) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT count(*) FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sortOrder=null;
    String _sqlWhereStatement="";
    String _sql=_sqlBuilder.toString();
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
      // Specialized part II - SelectPaginatedResultHelper - BEGIN
      // manage query for total count eements
      int _result=-1;

      if (_cursor.moveToFirst()) {
        _result=_cursor.getInt(0);
      }
      // log section for select BEGIN
      if (_context.isLogEnabled()) {
        // manage log

        // log for where parameters -- BEGIN
        int _whereParamCounter=0;
        for (String _whereParamItem: _contentValues.whereArgs()) {
          Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
        }
        // log for where parameters -- END
        Logger.info("Total elements found: %s", _result);
        // log section for select END
      }
      return _result;
    }
    // Specialized part II - SelectPaginatedResultHelper - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO person (pk, name, surname, birth_city, birth_day) VALUES (:pk, :name, :surname, :birthCity, :birthDay)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>pk</dt><dd>is binded to query's parameter <strong>:pk</strong> and method's parameter <strong>pk</strong></dd>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>:name</strong> and method's parameter <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is binded to query's parameter <strong>:surname</strong> and method's parameter <strong>surname</strong></dd>
   * 	<dt>birthCity</dt><dd>is binded to query's parameter <strong>:birthCity</strong> and method's parameter <strong>birthCity</strong></dd>
   * 	<dt>birthDay</dt><dd>is binded to query's parameter <strong>:birthDay</strong> and method's parameter <strong>birthDay</strong></dd>
   * </dl>
   *
   * @param pk
   * 	is binded to column value <strong>pk</strong>
   * @param name
   * 	is binded to column value <strong>name</strong>
   * @param surname
   * 	is binded to column value <strong>surname</strong>
   * @param birthCity
   * 	is binded to column value <strong>birth_city</strong>
   * @param birthDay
   * 	is binded to column value <strong>birth_day</strong>
   *
   */
  @Override
  public void insertOne(String pk, String name, String surname, String birthCity, Date birthDay) {
    // Specialized Insert - InsertType - BEGIN
    if (insertOnePreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO person (pk, name, surname, birth_city, birth_day) VALUES (?, ?, ?, ?, ?)";
      insertOnePreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertOnePreparedStatement0);

    _contentValues.put("pk", pk);
    _contentValues.put("name", name);
    _contentValues.put("surname", surname);
    _contentValues.put("birth_city", birthCity);
    _contentValues.put("birth_day", DateUtils.write(birthDay));

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
      Logger.info("INSERT INTO person (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertOnePreparedStatement0, _contentValues);
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, birth_city, birth_day, name, surname FROM person ORDER BY name</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>pk</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection. If result type is List, it will be generated as <strong>immutable list</strong>.
   */
  @Override
  public List<Person> selectAll() {
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
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Person> resultList=new ArrayList<Person>(_cursor.getCount());
      Person resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      String __pk=null;
      String __name=null;
      String __surname=null;
      String __birthCity=null;
      Date __birthDay=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("pk");
        int index1=_cursor.getColumnIndex("birth_city");
        int index2=_cursor.getColumnIndex("birth_day");
        int index3=_cursor.getColumnIndex("name");
        int index4=_cursor.getColumnIndex("surname");

        do
         {
          // reset temporary variable for immutable POJO
          // immutable object: initialize temporary variables for properties
          __pk=null;
          __name=null;
          __surname=null;
          __birthCity=null;
          __birthDay=null;
          __pk=_cursor.getString(index0);
          if (!_cursor.isNull(index1)) { __birthCity=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { __birthDay=DateUtils.read(_cursor.getString(index2)); }
          if (!_cursor.isNull(index3)) { __name=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { __surname=_cursor.getString(index4); }

          // define immutable POJO
          // immutable object: inizialize object
          resultBean=new Person(__pk,__name,__surname,__birthCity,__birthDay);
          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return (resultList==null ? null : Collections.unmodifiableList(resultList));
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person</pre>
   *
   * <p>No where parameters were found.</p>
   *
   *
   * @return number of deleted records
   */
  @Override
  public int deleteAll() {
    if (deleteAllPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person";
      deleteAllPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteAllPreparedStatement1);

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteAllPreparedStatement1, _contentValues);
    return result;
  }

  public static void clearCompiledStatements() {
    if (insertOnePreparedStatement0!=null) {
      insertOnePreparedStatement0.close();
      insertOnePreparedStatement0=null;
    }
    if (deleteAllPreparedStatement1!=null) {
      deleteAllPreparedStatement1.close();
      deleteAllPreparedStatement1=null;
    }
  }

  public class PaginatedResult9 extends PagedResultImpl<Person> {
    PaginatedResult9() {
      this.pageSize=10;
    }

    public List<Person> execute() {
      // Executor builder - BEGIN
      list=DaoPersonImpl.this.select(this);
      return list;
      // Executor builder - END
    }

    public List<Person> execute(BindAppDaoFactory daoFactory) {
      return daoFactory.getDaoPerson().select(this);
    }
  }
}
