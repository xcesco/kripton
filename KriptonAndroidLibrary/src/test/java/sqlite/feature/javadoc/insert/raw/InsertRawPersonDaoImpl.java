package sqlite.feature.javadoc.insert.raw;

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

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>InsertRawPersonDao</code>
 * </p>
 *
 *  @see sqlite.feature.javadoc.Person
 *  @see InsertRawPersonDao
 *  @see sqlite.feature.javadoc.PersonTable
 */
public class InsertRawPersonDaoImpl extends AbstractDao implements InsertRawPersonDao {
  private static final Set<String> insertOneRaw0ColumnSet = CollectionUtils.asSet(String.class, "name", "surname");

  private static final Set<String> insertOneRawFieldName1ColumnSet = CollectionUtils.asSet(String.class, "name");

  private static final Set<String> insertOne2RawFieldName2ColumnSet = CollectionUtils.asSet(String.class, "name");

  private SQLiteStatement insertOneRawPreparedStatement0;

  private SQLiteStatement insertOneRawFieldNamePreparedStatement1;

  private SQLiteStatement insertOne2RawFieldNamePreparedStatement2;

  public InsertRawPersonDaoImpl(BindInsertRawPersonDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO person (name, surname) VALUES (${name}, ${surname})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>${name}</strong> and method's parameter <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is binded to query's parameter <strong>${surname}</strong> and method's parameter <strong>surname</strong></dd>
   * </dl>
   *
   * @param name
   * 	is binded to column value <strong>name</strong>
   * @param surname
   * 	is binded to column value <strong>surname</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insertOneRaw(String name, String surname) {
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

    }
    // log section END
    // insert operation
    if (insertOneRawPreparedStatement0==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO person (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertOneRawPreparedStatement0 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertOneRawPreparedStatement0, _contentValues);
    return (int)result;
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT INTO Person (name, surname) VALUES (${name}, ${surname})</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT INTO person (name, surname) VALUES (${name}, ${surname})</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons"
   * @param contentValues content values
   * @return new row's id
   */
  long insertOneRaw0(Uri uri, ContentValues contentValues) {
    Logger.info("Execute INSERT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValues(contentValues);
    for (String columnName:_contentValues.values().keySet()) {
      if (!insertOneRaw0ColumnSet.contains(columnName)) {
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
   * <h2>SQL insert</h2>
   * <pre>INSERT OR REPLACE INTO person (name) VALUES (${name})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>${name}</strong> and method's parameter <strong>name</strong></dd>
   * </dl>
   *
   * @param name
   * 	is binded to column value <strong>name</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insertOneRawFieldName(String name) {
    KriptonContentValues _contentValues=contentValuesForUpdate();

    if (name!=null) {
      _contentValues.put("name", name);
    } else {
      _contentValues.putNull("name");
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

    }
    // log section END
    // insert operation
    if (insertOneRawFieldNamePreparedStatement1==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT OR REPLACE INTO person (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertOneRawFieldNamePreparedStatement1 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertOneRawFieldNamePreparedStatement1, _contentValues);
    return (int)result;
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/name</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO Person (name) VALUES (${name})</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO person (name) VALUES (${name})</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/name"
   * @param contentValues content values
   * @return new row's id
   */
  long insertOneRawFieldName1(Uri uri, ContentValues contentValues) {
    Logger.info("Execute INSERT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValues(contentValues);
    for (String columnName:_contentValues.values().keySet()) {
      if (!insertOneRawFieldName1ColumnSet.contains(columnName)) {
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
   * <h2>SQL insert</h2>
   * <pre>INSERT OR REPLACE INTO person (name) VALUES (${name})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>${name}</strong> and method's parameter <strong>name</strong></dd>
   * </dl>
   *
   * @param name
   * 	is binded to column value <strong>name</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insertOne2RawFieldName(String name) {
    KriptonContentValues _contentValues=contentValuesForUpdate();

    if (name!=null) {
      _contentValues.put("name", name);
    } else {
      _contentValues.putNull("name");
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

    }
    // log section END
    // insert operation
    if (insertOne2RawFieldNamePreparedStatement2==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT OR REPLACE INTO person (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertOne2RawFieldNamePreparedStatement2 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertOne2RawFieldNamePreparedStatement2, _contentValues);
    return (int)result;
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/surname</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO Person (name) VALUES (${name})</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO person (name) VALUES (${name})</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/surname"
   * @param contentValues content values
   * @return new row's id
   */
  long insertOne2RawFieldName2(Uri uri, ContentValues contentValues) {
    Logger.info("Execute INSERT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValues(contentValues);
    for (String columnName:_contentValues.values().keySet()) {
      if (!insertOne2RawFieldName2ColumnSet.contains(columnName)) {
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
    // insert operation
    long result = database().insert("person", null, _contentValues.values());
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT OR REPLACE INTO person (name) SELECT name FROM person WHERE name=${name}</pre>
   *
   * <h2>Method parameters used as sql parameters</h2>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>${name}</strong></dd>
   * </dl>
   *
   * @param name
   * 	is used as parameter
   *
   */
  @Override
  public void insertRawFromSelect(String name) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    // build where condition
    _contentValues.addWhereArgs((name==null?"":name));

    Logger.info("INSERT OR REPLACE INTO person (name) SELECT name FROM person WHERE name=${param0}");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    database().execSQL("INSERT OR REPLACE INTO person (name) SELECT name FROM person WHERE name=?", _contentValues.whereArgsAsArray());
  }

  public void clearCompiledStatements() {
    if (insertOneRawPreparedStatement0!=null) {
      insertOneRawPreparedStatement0.close();
      insertOneRawPreparedStatement0=null;
    }
    if (insertOneRawFieldNamePreparedStatement1!=null) {
      insertOneRawFieldNamePreparedStatement1.close();
      insertOneRawFieldNamePreparedStatement1=null;
    }
    if (insertOne2RawFieldNamePreparedStatement2!=null) {
      insertOne2RawFieldNamePreparedStatement2.close();
      insertOne2RawFieldNamePreparedStatement2=null;
    }
  }
}
