package sqlite.feat.includeFields;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.LinkedList;
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
public class PersonDAOImpl extends AbstractDao implements PersonDAO {
  public PersonDAOImpl(BindPersonDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT name, id FROM person WHERE type_name=${bean.name} ORDER BY name</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.name}</dt><dd>is binded to method's parameter <strong>bean.name</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectIncludeOne(Person bean) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT name, id FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sortOrder=null;

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE type_name=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs((bean.name==null?"":bean.name));
    // generation order - BEGIN
    String _sqlOrderByStatement=" ORDER BY name";
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Person> resultList=new LinkedList<Person>();
      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("name");
        int index1=cursor.getColumnIndex("id");

        do
         {
          resultBean=new Person();

          if (!cursor.isNull(index0)) { resultBean.name=cursor.getString(index0); }
          resultBean.id=cursor.getLong(index1);

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT surname, birth_city, birth_day, type_name FROM person ORDER BY name</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>type_name</dt><dd>is associated to bean's property <strong>typeName</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectExcludeOne() {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT surname, birth_city, birth_day, type_name FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sortOrder=null;
    String _sqlWhereStatement="";

    // build where condition
    // generation order - BEGIN
    String _sqlOrderByStatement=" ORDER BY name";
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Person> resultList=new LinkedList<Person>();
      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("surname");
        int index1=cursor.getColumnIndex("birth_city");
        int index2=cursor.getColumnIndex("birth_day");
        int index3=cursor.getColumnIndex("type_name");

        do
         {
          resultBean=new Person();

          if (!cursor.isNull(index0)) { resultBean.surname=cursor.getString(index0); }
          if (!cursor.isNull(index1)) { resultBean.birthCity=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.birthDay=DateUtils.read(cursor.getString(index2)); }
          if (!cursor.isNull(index3)) { resultBean.typeName=cursor.getString(index3); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO person (name, id) VALUES (${bean.name}, ${bean.id})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>${bean.id}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insertIncludeOne(Person bean) {
    KriptonContentValues _contentValues=contentValues();
    if (bean.name!=null) {
      _contentValues.put("name", bean.name);
    } else {
      _contentValues.putNull("name");
    }
    _contentValues.put("id", bean.id);

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:_contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO person (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:_contentValues.keySet()) {
      _contentValue=_contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    // generate SQL for insert
    String _sql=String.format("INSERT INTO person (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
    // insert operation
    long result = KriptonDatabaseWrapper.insert(dataSource, _sql, _contentValues);
    bean.id=result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO person (surname, birth_city, birth_day, type_name) VALUES (${bean.surname}, ${bean.birthCity}, ${bean.birthDay}, ${bean.typeName})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
   * 	<dt>birth_city</dt><dd>is mapped to <strong>${bean.birthCity}</strong></dd>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>${bean.birthDay}</strong></dd>
   * 	<dt>type_name</dt><dd>is mapped to <strong>${bean.typeName}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insertExcludeOne(Person bean) {
    KriptonContentValues _contentValues=contentValues();
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
    if (bean.typeName!=null) {
      _contentValues.put("type_name", bean.typeName);
    } else {
      _contentValues.putNull("type_name");
    }

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:_contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO person (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:_contentValues.keySet()) {
      _contentValue=_contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    // generate SQL for insert
    String _sql=String.format("INSERT INTO person (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
    // insert operation
    long result = KriptonDatabaseWrapper.insert(dataSource, _sql, _contentValues);
    bean.id=result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE person SET name=:name, id=:id</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>${bean.id}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   */
  @Override
  public void updateIncludeOne(Person bean) {
    KriptonContentValues _contentValues=contentValues();
    if (bean.name!=null) {
      _contentValues.put("name", bean.name);
    } else {
      _contentValues.putNull("name");
    }
    _contentValues.put("id", bean.id);


    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sqlWhereStatement="";

    // generate sql
    String _sql="UPDATE person SET name=?, id=?";

    // display log
    Logger.info("UPDATE person SET name=:name, id=:id");

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:_contentValues.keySet()) {
      _contentValue=_contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = KriptonDatabaseWrapper.update(dataSource, _sql, _contentValues);
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE person SET surname=:surname, birth_city=:birthCity, birth_day=:birthDay, type_name=:typeName</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
   * 	<dt>birth_city</dt><dd>is mapped to <strong>${bean.birthCity}</strong></dd>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>${bean.birthDay}</strong></dd>
   * 	<dt>type_name</dt><dd>is mapped to <strong>${bean.typeName}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   */
  @Override
  public void updateExcludeOne(Person bean) {
    KriptonContentValues _contentValues=contentValues();
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
    if (bean.typeName!=null) {
      _contentValues.put("type_name", bean.typeName);
    } else {
      _contentValues.putNull("type_name");
    }


    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sqlWhereStatement="";

    // generate sql
    String _sql="UPDATE person SET surname=?, birth_city=?, birth_day=?, type_name=?";

    // display log
    Logger.info("UPDATE person SET surname=:surname, birth_city=:birthCity, birth_day=:birthDay, type_name=:typeName");

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:_contentValues.keySet()) {
      _contentValue=_contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = KriptonDatabaseWrapper.update(dataSource, _sql, _contentValues);
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM person</pre>
   *
   * @param bean
   * 	is used as ${bean}
   */
  @Override
  public void deleteIncludeOne(Person bean) {
    KriptonContentValues _contentValues=contentValues();

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sqlWhereStatement="";

    // generate sql
    String _sql="DELETE FROM person";

    // display log
    Logger.info("DELETE FROM person");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = KriptonDatabaseWrapper.delete(dataSource, _sql, _contentValues);
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM person</pre>
   *
   * @param bean
   * 	is used as ${bean}
   */
  @Override
  public void deleteExcludeOne(Person bean) {
    KriptonContentValues _contentValues=contentValues();

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sqlWhereStatement="";

    // generate sql
    String _sql="DELETE FROM person";

    // display log
    Logger.info("DELETE FROM person");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = KriptonDatabaseWrapper.delete(dataSource, _sql, _contentValues);
  }
}
