/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.feature.javadoc.insert.raw;

import android.content.ContentValues;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>InsertRawPersonDao</code>
 * </p>.
 *
 * @see sqlite.feature.javadoc.Person
 * @see InsertRawPersonDao
 * @see sqlite.feature.javadoc.PersonTable
 */
public class InsertRawPersonDaoImpl extends Dao implements InsertRawPersonDao {
  
  /** The insert one raw prepared statement 0. */
  private static SQLiteStatement insertOneRawPreparedStatement0;

  /** The Constant insertOneRaw0ColumnSet. */
  private static final Set<String> insertOneRaw0ColumnSet = CollectionUtils.asSet(String.class, "person_name", "person_surname");

  /** The insert one raw field name prepared statement 1. */
  private static SQLiteStatement insertOneRawFieldNamePreparedStatement1;

  /** The Constant insertOneRawFieldName1ColumnSet. */
  private static final Set<String> insertOneRawFieldName1ColumnSet = CollectionUtils.asSet(String.class, "person_name");

  /** The insert one 2 raw field name prepared statement 2. */
  private static SQLiteStatement insertOne2RawFieldNamePreparedStatement2;

  /** The Constant insertOne2RawFieldName2ColumnSet. */
  private static final Set<String> insertOne2RawFieldName2ColumnSet = CollectionUtils.asSet(String.class, "person_name", "person_surname");

  /**
   * Instantiates a new insert raw person dao impl.
   *
   * @param context the context
   */
  public InsertRawPersonDaoImpl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO person (person_name, person_surname) VALUES (${name}, ${personSurname})</pre>
   * 
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>${name}</strong> and method's parameter <strong>name</strong></dd>
   * 	<dt>personSurname</dt><dd>is binded to query's parameter <strong>${personSurname}</strong> and method's parameter <strong>personSurname</strong></dd>
   * </dl>.
   *
   * @param name 	is binded to column value <strong>person_name</strong>
   * @param personSurname 	is binded to column value <strong>person_surname</strong>
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insertOneRaw(String name, String personSurname) {
    if (insertOneRawPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO person (person_name, person_surname) VALUES (?, ?)";
      insertOneRawPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertOneRawPreparedStatement0);

    _contentValues.put("person_name", name);
    _contentValues.put("person_surname", personSurname);

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
    long result = KriptonDatabaseWrapper.insert(insertOneRawPreparedStatement0, _contentValues);
    return (int)result;
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT INTO Person (personName, personSurname) VALUES (${personName}, ${personSurname})</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT INTO person (person_name, person_surname) VALUES (${personName}, ${personSurname})</pre>
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
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
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
   * <pre>INSERT OR REPLACE INTO person (person_name) VALUES (${name})</pre>
   * 
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>${name}</strong> and method's parameter <strong>name</strong></dd>
   * </dl>.
   *
   * @param name 	is binded to column value <strong>person_name</strong>
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insertOneRawFieldName(String name) {
    if (insertOneRawFieldNamePreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="INSERT OR REPLACE INTO person (person_name) VALUES (?)";
      insertOneRawFieldNamePreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertOneRawFieldNamePreparedStatement1);

    _contentValues.put("person_name", name);

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


      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    // insert operation
    long result = KriptonDatabaseWrapper.insert(insertOneRawFieldNamePreparedStatement1, _contentValues);
    return (int)result;
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/name</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO Person (personName) VALUES (${personName})</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO person (person_name) VALUES (${personName})</pre>
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
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
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
   * <pre>INSERT OR REPLACE INTO person (person_name, person_surname) VALUES (${name}, ${surnname})</pre>
   * 
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>${name}</strong> and method's parameter <strong>name</strong></dd>
   * 	<dt>surnname</dt><dd>is binded to query's parameter <strong>${surnname}</strong> and method's parameter <strong>surnname</strong></dd>
   * </dl>.
   *
   * @param surnname 	is binded to column value <strong>person_surname</strong>
   * @param name 	is binded to column value <strong>person_name</strong>
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insertOne2RawFieldName(String surnname, String name) {
    if (insertOne2RawFieldNamePreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="INSERT OR REPLACE INTO person (person_name, person_surname) VALUES (?, ?)";
      insertOne2RawFieldNamePreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertOne2RawFieldNamePreparedStatement2);

    _contentValues.put("person_name", name);
    _contentValues.put("person_surname", surnname);

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


      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    // insert operation
    long result = KriptonDatabaseWrapper.insert(insertOne2RawFieldNamePreparedStatement2, _contentValues);
    return (int)result;
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/surname</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO Person (personName, personSurname) VALUES (${personName}, ${personSurname})</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO person (person_name, person_surname) VALUES (${personName}, ${personSurname})</pre>
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
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
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
   * <pre>INSERT OR REPLACE INTO person (person_name) SELECT person_name FROM person WHERE person_name=${name}</pre>
   * 
   * <h2>Method parameters used as sql parameters</h2>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>${name}</strong></dd>
   * </dl>.
   *
   * @param name 	is used as parameter
   */
  @Override
  public void insertRawFromSelect(String name) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    // build where condition
    _contentValues.addWhereArgs((name==null?"":name));
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 

      Logger.info("INSERT OR REPLACE INTO person (person_name) SELECT person_name FROM person WHERE person_name=?");

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

    database().execSQL("INSERT OR REPLACE INTO person (person_name) SELECT person_name FROM person WHERE person_name=?", _contentValues.whereArgsAsArray());
  }

  /**
   * Clear compiled statements.
   */
  public static void clearCompiledStatements() {
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
