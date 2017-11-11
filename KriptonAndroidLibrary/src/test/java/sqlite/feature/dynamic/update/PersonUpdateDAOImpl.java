package sqlite.feature.dynamic.update;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sqlite.feature.dynamic.Person;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>PersonUpdateDAO</code>
 * </p>
 *
 *  @see Person
 *  @see PersonUpdateDAO
 *  @see sqlite.feature.dynamic.PersonTable
 */
public class PersonUpdateDAOImpl extends AbstractDao implements PersonUpdateDAO {
  protected String SELECT_ONE_SQL1 = "SELECT id, name, surname, birth_city, birth_day FROM person WHERE name like ? || '%' ";

  protected String SELEC_ALL_SQL2 = "SELECT id, name, surname, birth_city, birth_day FROM person";

  private SQLiteStatement insertOnePreparedStatement0;

  public PersonUpdateDAOImpl(BindPersonUpdateDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, birth_city, birth_day FROM person WHERE name like ${nameTemp} || '%' </pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${nameTemp}</dt><dd>is binded to method's parameter <strong>nameValue</strong></dd>
   * </dl>
   *
   * @param nameValue
   * 	is binded to <code>${nameTemp}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectOne(String nameValue) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL1;
    // add where arguments
    _contentValues.addWhereArgs((nameValue==null?"":nameValue));
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

      ArrayList<Person> resultList=new ArrayList<Person>(cursor.getCount());
      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("surname");
        int index3=cursor.getColumnIndex("birth_city");
        int index4=cursor.getColumnIndex("birth_day");

        do
         {
          resultBean=new Person();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.surname=cursor.getString(index2); }
          if (!cursor.isNull(index3)) { resultBean.birthCity=cursor.getString(index3); }
          if (!cursor.isNull(index4)) { resultBean.birthDay=DateUtils.read(cursor.getString(index4)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person WHERE id = ${nameValue} AND #{DYNAMIC_WHERE}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${nameValue}</dt><dd>is mapped to method's parameter <strong>nameValue</strong></dd>
   * </dl>
   *
   * <dl>
   * <dt>where</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{DYNAMIC_WHERE}</dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>where</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{DYNAMIC_WHERE}</dd></dl>
   *
   * @param nameValue
   * 	is used as where parameter <strong>${nameValue}</strong>
   * @param where
   * 	is used as dynamic where conditions
   */
  @Override
  public void deleteRaw(String nameValue, String where) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.addWhereArgs((nameValue==null?"":nameValue));

    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=where;
    // generation CODE_001 -- END
    StringBuilder _sqlBuilder=getSQLStringBuilder();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id = ?"+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // generate sql
    String _sql=String.format("DELETE FROM person WHERE id = ?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));
    // log section BEGIN
    if (this.dataSource.logEnabled) {

      // display log
      Logger.info("DELETE FROM person WHERE id = ?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, _sql, _contentValues);
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM person WHERE id = ${bean.id} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>where</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{DYNAMIC_WHERE}</dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @param where
   * 	is used as dynamic where conditions
   */
  @Override
  public void deleteBean(Person bean, String where) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.addWhereArgs(String.valueOf(bean.id));

    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=where;
    // generation CODE_001 -- END
    StringBuilder _sqlBuilder=getSQLStringBuilder();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id = ?"+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // generate sql
    String _sql=String.format("DELETE FROM person WHERE id = ?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));
    // log section BEGIN
    if (this.dataSource.logEnabled) {

      // display log
      Logger.info("DELETE FROM person WHERE id = ?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, _sql, _contentValues);
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET name=:name WHERE id = ${nameValue} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>name</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${nameValue}</dt><dd>is mapped to method's parameter <strong>nameValue</strong></dd>
   * </dl>
   *
   * <dl>
   * <dt>where</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{DYNAMIC_WHERE}</dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>where</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{DYNAMIC_WHERE}</dd></dl>
   *
   * @param name
   * 	is used as updated field <strong>name</strong>
   * @param nameValue
   * 	is used as where parameter <strong>${nameValue}</strong>
   * @param where
   * 	is used as dynamic where conditions
   */
  @Override
  public void updateRaw(String name, String nameValue, String where) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    if (name!=null) {
      _contentValues.put("name", name);
    } else {
      _contentValues.putNull("name");
    }

    _contentValues.addWhereArgs((nameValue==null?"":nameValue));

    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=where;
    // generation CODE_001 -- END
    StringBuilder _sqlBuilder=getSQLStringBuilder();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id = ?"+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // generate sql
    String _sql=String.format("UPDATE person SET name=? WHERE id = ?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));
    // log section BEGIN
    if (this.dataSource.logEnabled) {

      // display log
      Logger.info("UPDATE person SET name=:name WHERE id = ?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, _sql, _contentValues);
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE person SET name=:name, surname=:surname, birth_city=:birthCity, birth_day=:birthDay WHERE id = ${bean.id} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
   * 	<dt>birth_city</dt><dd>is mapped to <strong>${bean.birthCity}</strong></dd>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>${bean.birthDay}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>where</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{DYNAMIC_WHERE}</dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @param where
   * 	is used as dynamic where conditions
   */
  @Override
  public void updateBean(Person bean, String where) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    if (bean.name!=null) {
      _contentValues.put("name", bean.name);
    } else {
      _contentValues.putNull("name");
    }
    if (bean.surname!=null) {
      _contentValues.put("surname", bean.surname);
    } else {
      _contentValues.putNull("surname");
    }
    if (bean.birthCity!=null) {
      _contentValues.put("birth_city", bean.birthCity);
    } else {
      _contentValues.putNull("birth_city");
    }
    if (bean.birthDay!=null) {
      _contentValues.put("birth_day", DateUtils.write(bean.birthDay));
    } else {
      _contentValues.putNull("birth_day");
    }

    _contentValues.addWhereArgs(String.valueOf(bean.id));

    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=where;
    // generation CODE_001 -- END
    StringBuilder _sqlBuilder=getSQLStringBuilder();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id = ?"+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // generate sql
    String _sql=String.format("UPDATE person SET name=?, surname=?, birth_city=?, birth_day=? WHERE id = ?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));
    // log section BEGIN
    if (this.dataSource.logEnabled) {

      // display log
      Logger.info("UPDATE person SET name=:name, surname=:surname, birth_city=:birthCity, birth_day=:birthDay WHERE id = ?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, _sql, _contentValues);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, birth_city, birth_day FROM person</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selecAll() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELEC_ALL_SQL2;
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

      ArrayList<Person> resultList=new ArrayList<Person>(cursor.getCount());
      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("surname");
        int index3=cursor.getColumnIndex("birth_city");
        int index4=cursor.getColumnIndex("birth_day");

        do
         {
          resultBean=new Person();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.surname=cursor.getString(index2); }
          if (!cursor.isNull(index3)) { resultBean.birthCity=cursor.getString(index3); }
          if (!cursor.isNull(index4)) { resultBean.birthDay=DateUtils.read(cursor.getString(index4)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT OR IGNORE INTO person (name, surname, birth_city, birth_day) VALUES (${name}, ${surname}, ${birthCity}, ${birthDay})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>${name}</strong> and method's parameter <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is binded to query's parameter <strong>${surname}</strong> and method's parameter <strong>surname</strong></dd>
   * 	<dt>birth_city</dt><dd>is binded to query's parameter <strong>${birthCity}</strong> and method's parameter <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is binded to query's parameter <strong>${birthDay}</strong> and method's parameter <strong>birthDay</strong></dd>
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
    KriptonContentValues _contentValues=contentValuesForUpdate();

    if (name!=null) {
      _contentValues.put("name", name);
    } else {
      _contentValues.putNull("name");
    }
    if (surname!=null) {
      _contentValues.put("surname", surname);
    } else {
      _contentValues.putNull("surname");
    }
    if (birthCity!=null) {
      _contentValues.put("birth_city", birthCity);
    } else {
      _contentValues.putNull("birth_city");
    }
    if (birthDay!=null) {
      _contentValues.put("birth_day", DateUtils.write(birthDay));
    } else {
      _contentValues.putNull("birth_day");
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
      Logger.info("INSERT OR IGNORE INTO person (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    if (insertOnePreparedStatement0==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT OR IGNORE INTO person (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertOnePreparedStatement0 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertOnePreparedStatement0, _contentValues);
  }

  public void clearCompiledStatements() {
    if (insertOnePreparedStatement0!=null) {
      insertOnePreparedStatement0.close();
      insertOnePreparedStatement0=null;
    }
  }
}
