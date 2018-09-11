package sqlite.feature.multiple;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
  /**
   * SQL definition for method select
   */
  private static final String SELECT_SQL2 = "SELECT id, name, surname FROM person WHERE name=?";

  private static SQLiteStatement insertPreparedStatement0;

  private static SQLiteStatement insertAllPreparedStatement1;

  private static SQLiteStatement updatePreparedStatement2;

  public DaoPersonImpl(BindAppDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname FROM person WHERE name=${name}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
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
   * @return collection of bean or empty collection. If result type is List, it will be generated as <strong>immutable list</strong>.
   */
  @Override
  public List<Person> select(String name) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_SQL2;
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
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Person> resultList=new ArrayList<Person>(_cursor.getCount());
      Person resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      String __name=null;
      String __surname=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("name");
        int index2=_cursor.getColumnIndex("surname");

        do
         {
          // reset temporary variable for immutable POJO
          // immutable object: initialize temporary variables for properties
          __id=0;
          __name=null;
          __surname=null;
          __id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { __name=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { __surname=_cursor.getString(index2); }

          // define immutable POJO
          // immutable object: inizialize object
          resultBean=new Person(__id,__name,__surname);
          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return (resultList==null ? null : Collections.unmodifiableList(resultList));
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO person (name, surname) VALUES (:name, :surname)</pre>
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
   */
  @Override
  public void insert(List<Person> bean) {
    // Specialized Insert - InsertType - BEGIN
    for (Person __bean: bean) {
      if (insertPreparedStatement0==null) {
        // generate static SQL for statement
        String _sql="INSERT INTO person (name, surname) VALUES (?, ?)";
        insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
      }
      KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
      _contentValues.put("name", __bean.getName());
      _contentValues.put("surname", __bean.getSurname());

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
      long result = KriptonDatabaseWrapper.insert(insertPreparedStatement0, _contentValues);
    }
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO person (name, surname) VALUES (:name, :surname)</pre>
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
   */
  @Override
  public Set<Person> insertAll(List<Person> bean) {
    // Specialized Insert - InsertType - BEGIN
    Set<Person> __result=new LinkedHashSet<>();
    for (Person __bean: bean) {
      if (insertAllPreparedStatement1==null) {
        // generate static SQL for statement
        String _sql="INSERT INTO person (name, surname) VALUES (?, ?)";
        insertAllPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
      }
      KriptonContentValues _contentValues=contentValuesForUpdate(insertAllPreparedStatement1);
      _contentValues.put("name", __bean.getName());
      _contentValues.put("surname", __bean.getSurname());

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
      long result = KriptonDatabaseWrapper.insert(insertAllPreparedStatement1, _contentValues);
    }
    return __result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET name=:name, surname=:surname WHERE id=${bean.id}</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>:bean.surname</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   */
  @Override
  public void update(Person bean) {
    if (updatePreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="UPDATE person SET name=?, surname=? WHERE id=?";
      updatePreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updatePreparedStatement2);
    _contentValues.put("name", bean.getName());
    _contentValues.put("surname", bean.getSurname());

    _contentValues.addWhereArgs(String.valueOf(bean.getId()));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET name=:name, surname=:surname WHERE id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(updatePreparedStatement2, _contentValues);
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
    if (insertAllPreparedStatement1!=null) {
      insertAllPreparedStatement1.close();
      insertAllPreparedStatement1=null;
    }
    if (updatePreparedStatement2!=null) {
      updatePreparedStatement2.close();
      updatePreparedStatement2=null;
    }
  }
}
