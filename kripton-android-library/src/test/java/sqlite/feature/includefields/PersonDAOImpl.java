package sqlite.feature.includefields;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>PersonDAO</code>
 * </p>
 *
 *  @see Person
 *  @see PersonDAO
 *  @see PersonTable
 */
public class PersonDAOImpl extends Dao implements PersonDAO {
  /**
   * SQL definition for method selectIncludeOne
   */
  private static final String SELECT_INCLUDE_ONE_SQL2 = "SELECT name, id FROM person WHERE type_name=? ORDER BY name";

  /**
   * SQL definition for method selectExcludeOne
   */
  private static final String SELECT_EXCLUDE_ONE_SQL4 = "SELECT birth_city, birth_day, surname, type_name FROM person ORDER BY name";

  private static SQLiteStatement insertIncludeOnePreparedStatement0;

  private static SQLiteStatement insertExcludeOnePreparedStatement1;

  private static SQLiteStatement updateIncludeOnePreparedStatement2;

  private static SQLiteStatement updateExcludeOnePreparedStatement3;

  private static SQLiteStatement deleteIncludeOnePreparedStatement4;

  private static SQLiteStatement deleteExcludeOnePreparedStatement5;

  public PersonDAOImpl(BindPersonDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT name, id FROM person WHERE type_name=${bean.name} ORDER BY name</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:bean.name</dt><dd>is binded to method's parameter <strong>bean.name</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectIncludeOne(Person bean) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_INCLUDE_ONE_SQL2;
    // add where arguments
    _contentValues.addWhereArgs(bean.name);
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

        int index0=_cursor.getColumnIndex("name");
        int index1=_cursor.getColumnIndex("id");

        do
         {
          resultBean=new Person();

          if (!_cursor.isNull(index0)) { resultBean.name=_cursor.getString(index0); }
          resultBean.id=_cursor.getLong(index1);

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
   * <pre>SELECT birth_city, birth_day, surname, type_name FROM person ORDER BY name</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>type_name</dt><dd>is associated to bean's property <strong>typeName</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectExcludeOne() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_EXCLUDE_ONE_SQL4;
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

        int index0=_cursor.getColumnIndex("birth_city");
        int index1=_cursor.getColumnIndex("birth_day");
        int index2=_cursor.getColumnIndex("surname");
        int index3=_cursor.getColumnIndex("type_name");

        do
         {
          resultBean=new Person();

          if (!_cursor.isNull(index0)) { resultBean.birthCity=_cursor.getString(index0); }
          if (!_cursor.isNull(index1)) { resultBean.birthDay=DateUtils.read(_cursor.getString(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.surname=_cursor.getString(index2); }
          if (!_cursor.isNull(index3)) { resultBean.typeName=_cursor.getString(index3); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO person (name, id) VALUES (:bean.name, :bean.id)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>:bean.id</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insertIncludeOne(Person bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertIncludeOnePreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO person (name, id) VALUES (?, ?)";
      insertIncludeOnePreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertIncludeOnePreparedStatement0);
    _contentValues.put("name", bean.name);
    _contentValues.put("id", bean.id);

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
    long result = KriptonDatabaseWrapper.insert(insertIncludeOnePreparedStatement0, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.id=result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO person (birth_city, birth_day, surname, type_name) VALUES (:bean.birthCity, :bean.birthDay, :bean.surname, :bean.typeName)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>birth_city</dt><dd>is mapped to <strong>:bean.birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>:bean.birthDay</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>:bean.surname</strong></dd>
   * 	<dt>type_name</dt><dd>is mapped to <strong>:bean.typeName</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insertExcludeOne(Person bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertExcludeOnePreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO person (birth_city, birth_day, surname, type_name) VALUES (?, ?, ?, ?)";
      insertExcludeOnePreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertExcludeOnePreparedStatement1);
    _contentValues.put("birth_city", bean.birthCity);
    _contentValues.put("birth_day", DateUtils.write(bean.birthDay));
    _contentValues.put("surname", bean.surname);
    _contentValues.put("type_name", bean.typeName);

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
    long result = KriptonDatabaseWrapper.insert(insertExcludeOnePreparedStatement1, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.id=result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET name=:name, id=:id</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>:bean.id</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   */
  @Override
  public void updateIncludeOne(Person bean) {
    if (updateIncludeOnePreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="UPDATE person SET name=?, id=?";
      updateIncludeOnePreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateIncludeOnePreparedStatement2);
    _contentValues.put("name", bean.name);
    _contentValues.put("id", bean.id);


    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET name=:name, id=:id");

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
    int result = KriptonDatabaseWrapper.updateDelete(updateIncludeOnePreparedStatement2, _contentValues);
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET birth_city=:birthCity, birth_day=:birthDay, surname=:surname, type_name=:typeName</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>birth_city</dt><dd>is mapped to <strong>:bean.birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>:bean.birthDay</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>:bean.surname</strong></dd>
   * 	<dt>type_name</dt><dd>is mapped to <strong>:bean.typeName</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   */
  @Override
  public void updateExcludeOne(Person bean) {
    if (updateExcludeOnePreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="UPDATE person SET birth_city=?, birth_day=?, surname=?, type_name=?";
      updateExcludeOnePreparedStatement3 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateExcludeOnePreparedStatement3);
    _contentValues.put("birth_city", bean.birthCity);
    _contentValues.put("birth_day", DateUtils.write(bean.birthDay));
    _contentValues.put("surname", bean.surname);
    _contentValues.put("type_name", bean.typeName);


    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET birth_city=:birth_city, birth_day=:birth_day, surname=:surname, type_name=:type_name");

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
    int result = KriptonDatabaseWrapper.updateDelete(updateExcludeOnePreparedStatement3, _contentValues);
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM person</pre>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   */
  @Override
  public void deleteIncludeOne(Person bean) {
    if (deleteIncludeOnePreparedStatement4==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person";
      deleteIncludeOnePreparedStatement4 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteIncludeOnePreparedStatement4);

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
    int result = KriptonDatabaseWrapper.updateDelete(deleteIncludeOnePreparedStatement4, _contentValues);
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM person</pre>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   */
  @Override
  public void deleteExcludeOne(Person bean) {
    if (deleteExcludeOnePreparedStatement5==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person";
      deleteExcludeOnePreparedStatement5 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteExcludeOnePreparedStatement5);

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
    int result = KriptonDatabaseWrapper.updateDelete(deleteExcludeOnePreparedStatement5, _contentValues);
  }

  public static void clearCompiledStatements() {
    if (insertIncludeOnePreparedStatement0!=null) {
      insertIncludeOnePreparedStatement0.close();
      insertIncludeOnePreparedStatement0=null;
    }
    if (insertExcludeOnePreparedStatement1!=null) {
      insertExcludeOnePreparedStatement1.close();
      insertExcludeOnePreparedStatement1=null;
    }
    if (updateIncludeOnePreparedStatement2!=null) {
      updateIncludeOnePreparedStatement2.close();
      updateIncludeOnePreparedStatement2=null;
    }
    if (updateExcludeOnePreparedStatement3!=null) {
      updateExcludeOnePreparedStatement3.close();
      updateExcludeOnePreparedStatement3=null;
    }
    if (deleteIncludeOnePreparedStatement4!=null) {
      deleteIncludeOnePreparedStatement4.close();
      deleteIncludeOnePreparedStatement4=null;
    }
    if (deleteExcludeOnePreparedStatement5!=null) {
      deleteExcludeOnePreparedStatement5.close();
      deleteExcludeOnePreparedStatement5=null;
    }
  }
}
