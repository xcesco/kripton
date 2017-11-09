package sqlite.feature.javadoc.insert.bean;

import android.content.ContentValues;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.Set;
import sqlite.feature.javadoc.Person;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>InsertBeanPersonDao</code>
 * </p>
 *
 *  @see Person
 *  @see InsertBeanPersonDao
 *  @see sqlite.feature.javadoc.PersonTable
 */
public class InsertBeanPersonDaoImpl extends AbstractDao implements InsertBeanPersonDao {
  private static final Set<String> insertOneBean0ColumnSet = CollectionUtils.asSet(String.class, "name", "surname", "student");

  private static final Set<String> insertOneBeanFieldName1ColumnSet = CollectionUtils.asSet(String.class, "name");

  private static final Set<String> insertOneBeanFieldSurname2ColumnSet = CollectionUtils.asSet(String.class, "surname", "student");

  private SQLiteStatement insertOneBeanPreparedStatement0;

  private SQLiteStatement insertOneBeanFieldNamePreparedStatement1;

  private SQLiteStatement insertOneBeanFieldSurnamePreparedStatement2;

  private SQLiteStatement insertBeanFromSelectPreparedStatement3;

  public InsertBeanPersonDaoImpl(BindInsertBeanPersonDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO person (name, surname, student) VALUES (${bean.name}, ${bean.surname}, ${bean.student})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
   * 	<dt>student</dt><dd>is mapped to <strong>${bean.student}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insertOneBean(Person bean) {
    KriptonContentValues _contentValues=contentValues();
    if (bean.getName()!=null) {
      _contentValues.put("name", bean.getName());
    } else {
      _contentValues.putNull("name");
    }
    if (bean.getSurname()!=null) {
      _contentValues.put("surname", bean.getSurname());
    } else {
      _contentValues.putNull("surname");
    }
    _contentValues.put("student", bean.isStudent());

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

    // insert operation
    if (insertOneBeanPreparedStatement0==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO person (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertOneBeanPreparedStatement0 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertOneBeanPreparedStatement0, _contentValues);
    bean.id=result;

    return (int)result;
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT INTO Person (name, surname, student) VALUES (${bean.name}, ${bean.surname}, ${bean.student})</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT INTO person (name, surname, student) VALUES (${bean.name}, ${bean.surname}, ${bean.student})</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons"
   * @param contentValues content values
   * @return new row's id
   */
  long insertOneBean0(Uri uri, ContentValues contentValues) {
    Logger.info("Execute INSERT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValues(contentValues);
    for (String columnName:_contentValues.values().keySet()) {
      if (!insertOneBean0ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons', column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "person" ));
      }
    }


    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:_contentValues.values().keySet()) {
      _contentValue=_contentValues.values().get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // insert operation
    long result = database().insert("person", null, _contentValues.values());
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR REPLACE INTO person (name) VALUES (${bean.name})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insertOneBeanFieldName(Person bean) {
    KriptonContentValues _contentValues=contentValues();
    if (bean.getName()!=null) {
      _contentValues.put("name", bean.getName());
    } else {
      _contentValues.putNull("name");
    }

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:_contentValues.keys()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT OR REPLACE INTO person (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    // insert operation
    if (insertOneBeanFieldNamePreparedStatement1==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT OR REPLACE INTO person (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertOneBeanFieldNamePreparedStatement1 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertOneBeanFieldNamePreparedStatement1, _contentValues);
    bean.id=result;

    return (int)result;
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/name</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO Person (name) VALUES (${bean.name})</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO person (name) VALUES (${bean.name})</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/name"
   * @param contentValues content values
   * @return new row's id
   */
  long insertOneBeanFieldName1(Uri uri, ContentValues contentValues) {
    Logger.info("Execute INSERT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValues(contentValues);
    for (String columnName:_contentValues.values().keySet()) {
      if (!insertOneBeanFieldName1ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/name', column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "person" ));
      }
    }


    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:_contentValues.values().keySet()) {
      _contentValue=_contentValues.values().get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // conflict algorithm REPLACE
    // insert operation
    long result = database().insertWithOnConflict("person", null, _contentValues.values(), 5);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR REPLACE INTO person (surname, student) VALUES (${bean.surname}, ${bean.student})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
   * 	<dt>student</dt><dd>is mapped to <strong>${bean.student}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insertOneBeanFieldSurname(Person bean) {
    KriptonContentValues _contentValues=contentValues();
    if (bean.getSurname()!=null) {
      _contentValues.put("surname", bean.getSurname());
    } else {
      _contentValues.putNull("surname");
    }
    _contentValues.put("student", bean.isStudent());

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:_contentValues.keys()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT OR REPLACE INTO person (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    // insert operation
    if (insertOneBeanFieldSurnamePreparedStatement2==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT OR REPLACE INTO person (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertOneBeanFieldSurnamePreparedStatement2 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertOneBeanFieldSurnamePreparedStatement2, _contentValues);
    bean.id=result;

    return (int)result;
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/surname</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO Person (surname, student) VALUES (${bean.surname}, ${bean.student})</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO person (surname, student) VALUES (${bean.surname}, ${bean.student})</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/surname"
   * @param contentValues content values
   * @return new row's id
   */
  long insertOneBeanFieldSurname2(Uri uri, ContentValues contentValues) {
    Logger.info("Execute INSERT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValues(contentValues);
    for (String columnName:_contentValues.values().keySet()) {
      if (!insertOneBeanFieldSurname2ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/surname', column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "person" ));
      }
    }


    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:_contentValues.values().keySet()) {
      _contentValue=_contentValues.values().get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // conflict algorithm REPLACE
    // insert operation
    long result = database().insertWithOnConflict("person", null, _contentValues.values(), 5);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR REPLACE INTO person (name) SELECT name FROM person WHERE name=${bean.name}</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insertBeanFromSelect(Person bean) {
    KriptonContentValues _contentValues=contentValues();
    if (bean.getName()!=null) {
      _contentValues.put("name", bean.getName());
    } else {
      _contentValues.putNull("name");
    }

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:_contentValues.keys()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT OR REPLACE INTO person (%s) SELECT name FROM person WHERE name=${bean.name}", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    // insert operation
    if (insertBeanFromSelectPreparedStatement3==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT OR REPLACE INTO person (%s) SELECT name FROM person WHERE name=${bean.name}", _contentValues.keyList(), _contentValues.keyValueList());
      insertBeanFromSelectPreparedStatement3 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertBeanFromSelectPreparedStatement3, _contentValues);
    bean.id=result;
  }

  public void clearCompiledStatements() {
    if (insertOneBeanPreparedStatement0!=null) {
      insertOneBeanPreparedStatement0.close();
      insertOneBeanPreparedStatement0=null;
    }
    if (insertOneBeanFieldNamePreparedStatement1!=null) {
      insertOneBeanFieldNamePreparedStatement1.close();
      insertOneBeanFieldNamePreparedStatement1=null;
    }
    if (insertOneBeanFieldSurnamePreparedStatement2!=null) {
      insertOneBeanFieldSurnamePreparedStatement2.close();
      insertOneBeanFieldSurnamePreparedStatement2=null;
    }
    if (insertBeanFromSelectPreparedStatement3!=null) {
      insertBeanFromSelectPreparedStatement3.close();
      insertBeanFromSelectPreparedStatement3=null;
    }
  }
}
