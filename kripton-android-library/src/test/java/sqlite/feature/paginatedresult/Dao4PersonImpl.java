package sqlite.feature.paginatedresult;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.PagedResult;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sqlite.feature.paginatedresult.model.Person;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>Dao4Person</code>
 * </p>
 *
 *  @see Person
 *  @see Dao4Person
 *  @see sqlite.feature.paginatedresult.model.PersonTable
 */
public class Dao4PersonImpl extends Dao implements Dao4Person {
  private static SQLiteStatement insertOnePreparedStatement0;

  /**
   * SQL definition for method selectAll
   */
  private static final String SELECT_ALL_SQL1 = "SELECT id, birth_city, birth_day, name, surname FROM person ORDER BY name";

  private static SQLiteStatement deleteAllPreparedStatement1;

  public Dao4PersonImpl(BindPerson4DaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, birth_city, birth_day, name, surname FROM person WHERE id>${value} ORDER BY name LIMIT #{DYNAMIC_PAGE_SIZE} OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>pageSize</dt>is part of limit statement resolved at runtime. In above SQL it is displayed as #{DYNAMIC_PAGE_SIZE}</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:value</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>:value</code>
   * @param pageSize
   * 	is used as <strong>dynamic LIMIT statement</strong> and it is formatted by ({@link StringUtils#format})
   * @return paginated result.
   */
  @Override
  public PagedResult<Person> select(long value, int pageSize) {
    final PaginatedResult5 paginatedResult=new PaginatedResult5(value, pageSize);
    // common part generation - BEGIN
    // common part generation - END
    return paginatedResult;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, birth_city, birth_day, name, surname FROM person WHERE id>${value} ORDER BY name LIMIT #{DYNAMIC_PAGE_SIZE} OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>pageSize</dt>is part of limit statement resolved at runtime. In above SQL it is displayed as #{DYNAMIC_PAGE_SIZE}</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:value</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>:value</code>
   * @param pageSize
   * 	is used as <strong>dynamic LIMIT statement</strong> and it is formatted by ({@link StringUtils#format})
   * @param paginatedResult
   * 	handler of paginated result
   * @return result list
   */
  private List<Person> select(long value, int pageSize, PaginatedResult5 paginatedResult) {
    // total count - BEGIN
    paginatedResult.setTotalCount(this.selectTotalCount(value, pageSize, paginatedResult));
    // total count - END
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT id, birth_city, birth_day, name, surname FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sortOrder=null;

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id>?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // generation order - BEGIN
    String _sqlOrderByStatement=" ORDER BY name";
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

    // generation limit - BEGIN
    String _sqlLimitStatement=SqlUtils.printIf(paginatedResult.getPageSize()>0, " LIMIT "+paginatedResult.getPageSize());
    _sqlBuilder.append(_sqlLimitStatement);
    // generation limit - END

    // generation offset - BEGIN
    String _sqlOffsetStatement=SqlUtils.printIf(pageSize>0 && paginatedResult.getOffset()>0, " OFFSET "+paginatedResult.getOffset());
    _sqlBuilder.append(_sqlOffsetStatement);
    // generation offset - END

    String _sql=_sqlBuilder.toString();
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(value));
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

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("birth_city");
        int index2=_cursor.getColumnIndex("birth_day");
        int index3=_cursor.getColumnIndex("name");
        int index4=_cursor.getColumnIndex("surname");

        do
         {
          resultBean=new Person();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.birthCity=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.birthDay=DateUtils.read(_cursor.getString(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.name=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { resultBean.surname=_cursor.getString(index4); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part II - SelectPaginatedResultHelper - END
  }

  private int selectTotalCount(long value, int pageSize, PaginatedResult5 paginatedResult) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT count(*) FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sortOrder=null;

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id>?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    String _sql=_sqlBuilder.toString();
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(value));
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
   * <pre>INSERT INTO person (name, surname, birth_city, birth_day) VALUES (:name, :surname, :birthCity, :birthDay)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>:name</strong> and method's parameter <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is binded to query's parameter <strong>:surname</strong> and method's parameter <strong>surname</strong></dd>
   * 	<dt>birthCity</dt><dd>is binded to query's parameter <strong>:birthCity</strong> and method's parameter <strong>birthCity</strong></dd>
   * 	<dt>birthDay</dt><dd>is binded to query's parameter <strong>:birthDay</strong> and method's parameter <strong>birthDay</strong></dd>
   * </dl>
   *
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
  public void insertOne(String name, String surname, String birthCity, Date birthDay) {
    // Specialized Insert - InsertType - BEGIN
    if (insertOnePreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO person (name, surname, birth_city, birth_day) VALUES (?, ?, ?, ?)";
      insertOnePreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertOnePreparedStatement0);

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
   * <pre>SELECT id, birth_city, birth_day, name, surname FROM person ORDER BY name</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
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

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("birth_city");
        int index2=_cursor.getColumnIndex("birth_day");
        int index3=_cursor.getColumnIndex("name");
        int index4=_cursor.getColumnIndex("surname");

        do
         {
          resultBean=new Person();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.birthCity=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.birthDay=DateUtils.read(_cursor.getString(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.name=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { resultBean.surname=_cursor.getString(index4); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
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

  public class PaginatedResult5 extends PagedResult<Person> {
    long value;

    PaginatedResult5(long value, int pageSize) {
      this.value=value;
      this.pageSize=pageSize;
    }

    public List<Person> execute() {
      // Executor builder - BEGIN
      list=Dao4PersonImpl.this.select(value, this.pageSize, this);
      return list;
      // Executor builder - END
    }

    public List<Person> execute(BindPerson4DaoFactory daoFactory) {
      return daoFactory.getDao4Person().select(value, this.pageSize, this);
    }
  }
}
