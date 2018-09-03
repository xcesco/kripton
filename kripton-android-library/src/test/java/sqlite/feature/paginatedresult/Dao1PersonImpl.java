package sqlite.feature.paginatedresult;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.PagedResult;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sqlite.feature.paginatedresult.model.Person;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>Dao1Person</code>
 * </p>
 *
 *  @see Person
 *  @see Dao1Person
 *  @see sqlite.feature.paginatedresult.model.PersonTable
 */
public class Dao1PersonImpl extends Dao implements Dao1Person {
  private static SQLiteStatement insertOnePreparedStatement0;

  private static final String SELECT_ALL_SQL1 = "SELECT id, birth_city, birth_day, name, surname FROM person ORDER BY name";

  private static SQLiteStatement deleteAllPreparedStatement1;

  public Dao1PersonImpl(BindPerson1DaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, birth_city, birth_day, name, surname FROM person ORDER BY name LIMIT 10 OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
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
   * @return paginated result.
   */
  @Override
  public PagedResult<Person> select() {
    final PaginatedResult2 paginatedResult=new PaginatedResult2();
    // common part generation - BEGIN
    // common part generation - END
    return paginatedResult;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, birth_city, birth_day, name, surname FROM person ORDER BY name LIMIT 10 OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
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
   * @param paginatedResult
   * 	handler of paginated result
   * @return result list
   */
  private List<Person> select(PaginatedResult2 paginatedResult) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT id, birth_city, birth_day, name, surname FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sortOrder=null;
    String _sqlWhereStatement="";
    // generation order - BEGIN
    String _sqlOrderByStatement=" ORDER BY name";
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

    // generation limit - BEGIN
    String _sqlLimitStatement=" LIMIT 10";
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

  public class PaginatedResult2 extends PagedResult<Person> {
    PaginatedResult2() {
      this.pageSize=10;
    }

    public List<Person> execute() {
      list=Dao1PersonImpl.this.select(this);
      return list;
    }

    public List<Person> execute(BindPerson1DaoFactory daoFactory) {
      return daoFactory.getDao1Person().select(this);
    }
  }
}
