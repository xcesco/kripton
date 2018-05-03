package sqlite.feature.contentprovider.kripton35.nolog;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import sqlite.feature.contentprovider.kripton35.entities.Person;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>Person2DAO</code>
 * </p>
 *
 *  @see Person
 *  @see Person2DAO
 *  @see sqlite.feature.contentprovider.kripton35.entities.PersonTable
 */
public class Person2DAOImpl extends Dao implements Person2DAO {
  private static SQLiteStatement insertBeanPreparedStatement0;

  private static final Set<String> insertBean0ColumnSet = CollectionUtils.asSet(String.class, "city", "birth_city", "birth_day", "value", "name", "surname");

  private static SQLiteStatement insertNamePreparedStatement1;

  private static final Set<String> insertName1ColumnSet = CollectionUtils.asSet(String.class, "name");

  private static SQLiteStatement deleteRawPreparedStatement2;

  private static SQLiteStatement deleteBeanPreparedStatement3;

  private static SQLiteStatement updateRawPreparedStatement4;

  private static final Set<String> updateRaw5ColumnSet = CollectionUtils.asSet(String.class, "name");

  private static final Set<String> updateRaw6ColumnSet = CollectionUtils.asSet(String.class, "name");

  private static final Set<String> updateRaw7ColumnSet = CollectionUtils.asSet(String.class, "name");

  private static SQLiteStatement updateBeanPreparedStatement5;

  private static final Set<String> updateBean8ColumnSet = CollectionUtils.asSet(String.class, "alias_parent_id", "city", "birth_city", "birth_day", "value", "name", "surname");

  private static final Set<String> selectOne9ColumnSet = CollectionUtils.asSet(String.class, "id", "alias_parent_id", "city", "birth_city", "birth_day", "value", "name", "surname");

  private static final Set<String> selectAll10ColumnSet = CollectionUtils.asSet(String.class, "id", "alias_parent_id", "city", "birth_city", "birth_day", "value", "name", "surname");

  private static final Set<String> selectOne11ColumnSet = CollectionUtils.asSet(String.class, "id", "alias_parent_id", "city", "birth_city", "birth_day", "value", "name", "surname");

  private static final String SELECT_BEAN_SQL1 = "SELECT id, alias_parent_id, city, birth_city, birth_day, value, name, surname FROM person";

  private static final Set<String> selectBean12ColumnSet = CollectionUtils.asSet(String.class, "id", "alias_parent_id", "city", "birth_city", "birth_day", "value", "name", "surname");

  public Person2DAOImpl(BindPerson2DaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR FAIL INTO person (city, birth_city, birth_day, value, name, surname) VALUES (${bean.city}, ${bean.birthCity}, ${bean.birthDay}, ${bean.value}, ${bean.name}, ${bean.surname})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>city</dt><dd>is mapped to <strong>${bean.city}</strong></dd>
   * 	<dt>birth_city</dt><dd>is mapped to <strong>${bean.birthCity}</strong></dd>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>${bean.birthDay}</strong></dd>
   * 	<dt>value</dt><dd>is mapped to <strong>${bean.value}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insertBean(Person bean) {
    if (insertBeanPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT OR FAIL INTO person (city, birth_city, birth_day, value, name, surname) VALUES (?, ?, ?, ?, ?, ?)";
      insertBeanPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertBeanPreparedStatement0);
    _contentValues.put(bean.city);
    _contentValues.put(bean.birthCity);
    _contentValues.put(DateUtils.write(bean.birthDay));
    _contentValues.put(bean.value);
    _contentValues.put(bean.getName());
    _contentValues.put(bean.getSurname());

    // insert operation
    long result = KriptonDatabaseWrapper.insert(insertBeanPreparedStatement0, _contentValues);
    bean.id=result;
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT OR FAIL INTO Person (city, birthCity, birthDay, value, name, surname) VALUES (${bean.city}, ${bean.birthCity}, ${bean.birthDay}, ${bean.value}, ${bean.name}, ${bean.surname})</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT OR FAIL INTO person (city, birth_city, birth_day, value, name, surname) VALUES (${bean.city}, ${bean.birthCity}, ${bean.birthDay}, ${bean.value}, ${bean.name}, ${bean.surname})</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.contentprovider.kripton35.nolog/persons"
   * @param contentValues content values
   * @return new row's id
   */
  long insertBean0(Uri uri, ContentValues contentValues) {
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    for (String columnName:_contentValues.values().keySet()) {
      if (!insertBean0ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.contentprovider.kripton35.nolog/persons', column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "person" ));
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
    // conflict algorithm FAIL
    // insert operation
    long result = database().insertWithOnConflict("person", null, _contentValues.values(), 3);
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO person (name) VALUES (${tempName})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>tempName</dt><dd>is binded to query's parameter <strong>${tempName}</strong> and method's parameter <strong>tempName</strong></dd>
   * </dl>
   *
   * @param tempName
   * 	is binded to column value <strong>name</strong>
   *
   */
  @Override
  public void insertName(String tempName) {
    if (insertNamePreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO person (name) VALUES (?)";
      insertNamePreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertNamePreparedStatement1);

    _contentValues.put(tempName);

    // insert operation
    long result = KriptonDatabaseWrapper.insert(insertNamePreparedStatement1, _contentValues);
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/[*]</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT INTO Person (name) VALUES (${name})</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT INTO person (name) VALUES (${name})</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${name}</strong> at path segment 1</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.contentprovider.kripton35.nolog/persons/[*]"
   * @param contentValues content values
   * @return new row's id
   */
  long insertName1(Uri uri, ContentValues contentValues) {
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    for (String columnName:_contentValues.values().keySet()) {
      if (!insertName1ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.contentprovider.kripton35.nolog/persons/*', column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "person" ));
      }
    }

    // Add parameter name at path segment 1
    contentValues.put("name", uri.getPathSegments().get(1));

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
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person WHERE id = ${id}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public int deleteRaw(long id) {
    if (deleteRawPreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person WHERE id = ?";
      deleteRawPreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteRawPreparedStatement2);
    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    int result = KriptonDatabaseWrapper.updateDelete(deleteRawPreparedStatement2, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (DELETE operation):</h1>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/#</pre>
   *
   * <h2>JQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM Person WHERE id = ${id}</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM person WHERE id = ${id}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${id}</strong> at path segment 1</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.contentprovider.kripton35.nolog/persons/#"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  int deleteRaw2(Uri uri, String selection, String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // Add parameter id at path segment 1
    _contentValues.addWhereArgs(uri.getPathSegments().get(1));
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person WHERE id = ?");
    }
    // log section END

    // execute SQL
    int result = database().delete("person", _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person WHERE id = ${id} #{DYNAMIC_WHERE}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
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
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param where
   * 	is used as dynamic where conditions
   * @param args
   * 	is used as updated field <strong>args</strong>
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteRaw(long id, String where, String[] args) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=where;
    // initialize dynamic where args
    String[] _sqlDynamicWhereArgs=args;
    // generation CODE_001 -- END
    StringBuilder _sqlBuilder=sqlBuilder();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id = ? "+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    if (StringUtils.hasText(_sqlDynamicWhere) && _sqlDynamicWhereArgs!=null) {
      for (String _arg: _sqlDynamicWhereArgs) {
        _contentValues.addWhereArgs(_arg);
      }
    }

    // generate sql
    String _sql=String.format("DELETE FROM person WHERE id = ? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));
    int result = KriptonDatabaseWrapper.updateDelete(_context, _sql, _contentValues);
    return result!=0;
  }

  /**
   * <h1>Content provider URI (DELETE operation):</h1>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test0/#</pre>
   *
   * <h2>JQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM Person WHERE id = ${id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM person WHERE id = ${id} #{DYNAMIC_WHERE}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${id}</strong> at path segment 2</li>
   * </ul>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.contentprovider.kripton35.nolog/persons/test0/#"
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  int deleteRaw3(Uri uri, String selection, String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=selection;
    // initialize dynamic where args
    String[] _sqlDynamicWhereArgs=selectionArgs;
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id = ? "+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    if (StringUtils.hasText(_sqlDynamicWhere) && _sqlDynamicWhereArgs!=null) {
      for (String _arg: _sqlDynamicWhereArgs) {
        _contentValues.addWhereArgs(_arg);
      }
    }
    // Add parameter id at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    if (StringUtils.hasText(_sqlDynamicWhere) && _sqlDynamicWhereArgs!=null) {
      for (String _arg: _sqlDynamicWhereArgs) {
        _contentValues.addWhereArgs(_arg);
      }
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person WHERE id = ? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));
    }
    // log section END

    // execute SQL
    int result = database().delete("person", _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM person WHERE id = ${bean.id}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of deleted records
   */
  @Override
  public long deleteBean(Person bean) {
    if (deleteBeanPreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person WHERE id = ?";
      deleteBeanPreparedStatement3 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteBeanPreparedStatement3);
    _contentValues.addWhereArgs(String.valueOf(bean.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    int result = KriptonDatabaseWrapper.updateDelete(deleteBeanPreparedStatement3, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (DELETE operation):</h1>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test1/#</pre>
   *
   * <h2>JQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM Person WHERE id = ${bean.id}</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM person WHERE id = ${bean.id}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${bean.id}</strong> at path segment 2</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.contentprovider.kripton35.nolog/persons/test1/#"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  int deleteBean4(Uri uri, String selection, String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // Add parameter bean.id at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person WHERE id = ?");
    }
    // log section END

    // execute SQL
    int result = database().delete("person", _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET name=:name WHERE id=${id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>name</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param name
   * 	is used as updated field <strong>name</strong>
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   *
   * @return number of updated records
   */
  @Override
  public int updateRaw(String name, long id) {
    if (updateRawPreparedStatement4==null) {
      // generate static SQL for statement
      String _sql="UPDATE person SET name=? WHERE id=?";
      updateRawPreparedStatement4 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateRawPreparedStatement4);
    _contentValues.put(name);

    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    int result = KriptonDatabaseWrapper.updateDelete(updateRawPreparedStatement4, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (UPDATE operation):</h1>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/#</pre>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE Person SET name=${name} WHERE id=${id}</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE person SET name=${name} WHERE id=${id}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${id}</strong> at path segment 1</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.contentprovider.kripton35.nolog/persons/#"
   * @param contentValues content values
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  int updateRaw5(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // Add parameter id at path segment 1
    _contentValues.addWhereArgs(uri.getPathSegments().get(1));
    for (String columnName:_contentValues.values().keySet()) {
      if (!updateRaw5ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.contentprovider.kripton35.nolog/persons/#', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "person" ));
      }
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET name=:name WHERE id=?");

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
    }
    // log section END

    // execute SQL
    int result = database().update("person", _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET name=:name WHERE id=${id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>name</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
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
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param where
   * 	is used as dynamic where conditions
   *
   * @return number of updated records
   */
  @Override
  public int updateRaw(String name, long id, String where) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.put(name);

    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=where;
    // generation CODE_001 -- END
    StringBuilder _sqlBuilder=sqlBuilder();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=? "+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // generate sql
    String _sql=String.format("UPDATE person SET name=? WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));
    int result = KriptonDatabaseWrapper.updateDelete(_context, _sql, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (UPDATE operation):</h1>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test1/#</pre>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE Person SET name=${name} WHERE id=${id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE person SET name=${name} WHERE id=${id} #{DYNAMIC_WHERE}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${id}</strong> at path segment 2</li>
   * </ul>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.contentprovider.kripton35.nolog/persons/test1/#"
   * @param contentValues content values
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  int updateRaw6(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=selection;
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=? "+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // Add parameter id at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    for (String columnName:_contentValues.values().keySet()) {
      if (!updateRaw6ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.contentprovider.kripton35.nolog/persons/test1/#', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "person" ));
      }
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET name=:name WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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
    }
    // log section END

    // execute SQL
    int result = database().update("person", _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET name=:name WHERE id=${id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>name</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
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
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param where
   * 	is used as dynamic where conditions
   * @param args
   * 	is used as updated field <strong>args</strong>
   *
   * @return number of updated records
   */
  @Override
  public int updateRaw(String name, long id, String where, String[] args) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.put(name);

    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=where;
    // initialize dynamic where args
    String[] _sqlDynamicWhereArgs=args;
    // generation CODE_001 -- END
    StringBuilder _sqlBuilder=sqlBuilder();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=? "+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    if (StringUtils.hasText(_sqlDynamicWhere) && _sqlDynamicWhereArgs!=null) {
      for (String _arg: _sqlDynamicWhereArgs) {
        _contentValues.addWhereArgs(_arg);
      }
    }

    // generate sql
    String _sql=String.format("UPDATE person SET name=? WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));
    int result = KriptonDatabaseWrapper.updateDelete(_context, _sql, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (UPDATE operation):</h1>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test2/#</pre>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE Person SET name=${name} WHERE id=${id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE person SET name=${name} WHERE id=${id} #{DYNAMIC_WHERE}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${id}</strong> at path segment 2</li>
   * </ul>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.contentprovider.kripton35.nolog/persons/test2/#"
   * @param contentValues content values
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  int updateRaw7(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=selection;
    // initialize dynamic where args
    String[] _sqlDynamicWhereArgs=selectionArgs;
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=? "+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    if (StringUtils.hasText(_sqlDynamicWhere) && _sqlDynamicWhereArgs!=null) {
      for (String _arg: _sqlDynamicWhereArgs) {
        _contentValues.addWhereArgs(_arg);
      }
    }
    // Add parameter id at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    if (StringUtils.hasText(_sqlDynamicWhere) && _sqlDynamicWhereArgs!=null) {
      for (String _arg: _sqlDynamicWhereArgs) {
        _contentValues.addWhereArgs(_arg);
      }
    }
    for (String columnName:_contentValues.values().keySet()) {
      if (!updateRaw7ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.contentprovider.kripton35.nolog/persons/test2/#', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "person" ));
      }
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET name=:name WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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
    }
    // log section END

    // execute SQL
    int result = database().update("person", _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE person SET alias_parent_id=:parentId, city=:city, birth_city=:birthCity, birth_day=:birthDay, value=:value, name=:name, surname=:surname WHERE id=${person.id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>alias_parent_id</dt><dd>is mapped to <strong>${person.parentId}</strong></dd>
   * 	<dt>city</dt><dd>is mapped to <strong>${person.city}</strong></dd>
   * 	<dt>birth_city</dt><dd>is mapped to <strong>${person.birthCity}</strong></dd>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>${person.birthDay}</strong></dd>
   * 	<dt>value</dt><dd>is mapped to <strong>${person.value}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${person.name}</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>${person.surname}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${person.id}</dt><dd>is mapped to method's parameter <strong>person.id</strong></dd>
   * </dl>
   *
   * @param person
   * 	is used as ${person}
   *
   * @return number of updated records
   */
  @Override
  public int updateBean(Person person) {
    if (updateBeanPreparedStatement5==null) {
      // generate static SQL for statement
      String _sql="UPDATE person SET alias_parent_id=?, city=?, birth_city=?, birth_day=?, value=?, name=?, surname=? WHERE id=?";
      updateBeanPreparedStatement5 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateBeanPreparedStatement5);
    _contentValues.put(person.parentId);
    _contentValues.put(person.city);
    _contentValues.put(person.birthCity);
    _contentValues.put(DateUtils.write(person.birthDay));
    _contentValues.put(person.value);
    _contentValues.put(person.getName());
    _contentValues.put(person.getSurname());

    _contentValues.addWhereArgs(String.valueOf(person.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    int result = KriptonDatabaseWrapper.updateDelete(updateBeanPreparedStatement5, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (UPDATE operation):</h1>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test3/#</pre>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE Person SET parentId=${person.parentId}, city=${person.city}, birthCity=${person.birthCity}, birthDay=${person.birthDay}, value=${person.value}, name=${person.name}, surname=${person.surname} WHERE id=${person.id}</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE person SET alias_parent_id=${person.parentId}, city=${person.city}, birth_city=${person.birthCity}, birth_day=${person.birthDay}, value=${person.value}, name=${person.name}, surname=${person.surname} WHERE id=${person.id}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${person.id}</strong> at path segment 2</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.contentprovider.kripton35.nolog/persons/test3/#"
   * @param contentValues content values
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  int updateBean8(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // Add parameter person.id at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    for (String columnName:_contentValues.values().keySet()) {
      if (!updateBean8ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.contentprovider.kripton35.nolog/persons/test3/#', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "person" ));
      }
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET alias_parent_id=:alias_parent_id, city=:city, birth_city=:birth_city, birth_day=:birth_day, value=:value, name=:name, surname=:surname WHERE id=?");

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
    }
    // log section END

    // execute SQL
    int result = database().update("person", _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, alias_parent_id, city, birth_city, birth_day, value, name, surname FROM person WHERE name like ${nameTemp} || '%' GROUP BY id HAVING id=2 ORDER BY id, #{DYNAMIC_ORDER_BY}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>alias_parent_id</dt><dd>is associated to bean's property <strong>parentId</strong></dd>
   * 	<dt>city</dt><dd>is associated to bean's property <strong>city</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>orderBy</dt>is part of order statement resolved at runtime. In above SQL it is displayed as #{DYNAMIC_ORDER_BY}</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${nameTemp}</dt><dd>is binded to method's parameter <strong>nameValue</strong></dd>
   * </dl>
   *
   * @param nameValue
   * 	is binded to <code>${nameTemp}</code>
   * @param orderBy
   * 	is used as <strong>dynamic ORDER BY statement</strong> and it is formatted by ({@link StringUtils#format})
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectOne(String nameValue, String orderBy) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT id, alias_parent_id, city, birth_city, birth_day, value, name, surname FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sortOrder=orderBy;

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE name like ? || '%'";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // manage GROUP BY statement
    String _sqlGroupByStatement=" GROUP BY id";
    _sqlBuilder.append(_sqlGroupByStatement);

    // manage HAVING statement
    String _sqlHavingStatement=" HAVING id=2";
    _sqlBuilder.append(_sqlHavingStatement);
    // generation order - BEGIN
    String _sqlOrderByStatement=" ORDER BY id"+StringUtils.ifNotEmptyAppend(_sortOrder, ", ");
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

    String _sql=_sqlBuilder.toString();
    // add where arguments
    _contentValues.addWhereArgs((nameValue==null?"":nameValue));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {

      ArrayList<Person> resultList=new ArrayList<Person>(_cursor.getCount());
      Person resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("alias_parent_id");
        int index2=_cursor.getColumnIndex("city");
        int index3=_cursor.getColumnIndex("birth_city");
        int index4=_cursor.getColumnIndex("birth_day");
        int index5=_cursor.getColumnIndex("value");
        int index6=_cursor.getColumnIndex("name");
        int index7=_cursor.getColumnIndex("surname");

        do
         {
          resultBean=new Person();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.parentId=_cursor.getLong(index1); }
          if (!_cursor.isNull(index2)) { resultBean.city=_cursor.getLong(index2); }
          if (!_cursor.isNull(index3)) { resultBean.birthCity=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { resultBean.birthDay=DateUtils.read(_cursor.getString(index4)); }
          if (!_cursor.isNull(index5)) { resultBean.value=_cursor.getLong(index5); }
          if (!_cursor.isNull(index6)) { resultBean.setName(_cursor.getString(index6)); }
          if (!_cursor.isNull(index7)) { resultBean.setSurname(_cursor.getString(index7)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/[*]/test0</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, parentId, city, birthCity, birthDay, value, name, surname FROM Person WHERE name like ${nameTemp} || '%' GROUP BY id HAVING id=2 ORDER BY id, #{DYNAMIC_ORDER_BY}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, alias_parent_id, city, birth_city, birth_day, value, name, surname FROM person WHERE name like ${nameTemp} || '%' GROUP BY id HAVING id=2 ORDER BY id, #{DYNAMIC_ORDER_BY}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${nameTemp}</strong> at path segment 1</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.contentprovider.kripton35.nolog/persons/[*]/test0"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor selectOne9(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    String _sortOrder=sortOrder;
    _sqlBuilder.append("SELECT %s FROM person    ");

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE name like ? || '%'";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // manage GROUP BY statement
    String _sqlGroupByStatement=" GROUP BY id";
    _sqlBuilder.append(_sqlGroupByStatement);

    // manage HAVING statement
    String _sqlHavingStatement=" HAVING id=2";
    _sqlBuilder.append(_sqlHavingStatement);
    // generation order - BEGIN
    String _sqlOrderByStatement=" ORDER BY id"+StringUtils.ifNotEmptyAppend(_sortOrder, ", ");
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END


    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectOne9ColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.contentprovider.kripton35.nolog/persons/*/test0', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectOne9ColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter nameTemp at path segment 1
    _contentValues.addWhereArgs(uri.getPathSegments().get(1));
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());

    // execute query
    Cursor _result = database().rawQuery(_sql, _contentValues.whereArgsAsArray());
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, alias_parent_id, city, birth_city, birth_day, value, name, surname FROM person WHERE #{DYNAMIC_WHERE} ORDER BY name asc, #{DYNAMIC_ORDER_BY}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>alias_parent_id</dt><dd>is associated to bean's property <strong>parentId</strong></dd>
   * 	<dt>city</dt><dd>is associated to bean's property <strong>city</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>where</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{DYNAMIC_WHERE}</dd>
   * <dt>order</dt>is part of order statement resolved at runtime. In above SQL it is displayed as #{DYNAMIC_ORDER_BY}</dd>
   * </dl>
   *
   * @param where
   * 	is used as <strong>dynamic WHERE statement</strong> and it is formatted by ({@link StringUtils#format})
   * @param args
   * 	is binded to <code>${args}</code>
   * @param order
   * 	is used as <strong>dynamic ORDER BY statement</strong> and it is formatted by ({@link StringUtils#format})
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectAll(String where, String[] args, String order) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT id, alias_parent_id, city, birth_city, birth_day, value, name, surname FROM person");
    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=where;
    // initialize dynamic where args
    String[] _sqlDynamicWhereArgs=args;
    // generation CODE_001 -- END
    String _sortOrder=order;

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=StringUtils.ifNotEmptyAppend(_sqlDynamicWhere, " WHERE ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    if (StringUtils.hasText(_sqlDynamicWhere) && _sqlDynamicWhereArgs!=null) {
      for (String _arg: _sqlDynamicWhereArgs) {
        _contentValues.addWhereArgs(_arg);
      }
    }
    // generation order - BEGIN
    String _sqlOrderByStatement=" ORDER BY name asc"+StringUtils.ifNotEmptyAppend(_sortOrder, ", ");
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

    String _sql=_sqlBuilder.toString();
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {

      ArrayList<Person> resultList=new ArrayList<Person>(_cursor.getCount());
      Person resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("alias_parent_id");
        int index2=_cursor.getColumnIndex("city");
        int index3=_cursor.getColumnIndex("birth_city");
        int index4=_cursor.getColumnIndex("birth_day");
        int index5=_cursor.getColumnIndex("value");
        int index6=_cursor.getColumnIndex("name");
        int index7=_cursor.getColumnIndex("surname");

        do
         {
          resultBean=new Person();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.parentId=_cursor.getLong(index1); }
          if (!_cursor.isNull(index2)) { resultBean.city=_cursor.getLong(index2); }
          if (!_cursor.isNull(index3)) { resultBean.birthCity=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { resultBean.birthDay=DateUtils.read(_cursor.getString(index4)); }
          if (!_cursor.isNull(index5)) { resultBean.value=_cursor.getLong(index5); }
          if (!_cursor.isNull(index6)) { resultBean.setName(_cursor.getString(index6)); }
          if (!_cursor.isNull(index7)) { resultBean.setSurname(_cursor.getString(index7)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, parentId, city, birthCity, birthDay, value, name, surname FROM Person WHERE #{DYNAMIC_WHERE} ORDER BY name asc, #{DYNAMIC_ORDER_BY}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, alias_parent_id, city, birth_city, birth_day, value, name, surname FROM person WHERE #{DYNAMIC_WHERE} ORDER BY name asc, #{DYNAMIC_ORDER_BY}</pre>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.contentprovider.kripton35.nolog/persons"
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  Cursor selectAll10(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=selection;
    // initialize dynamic where args
    String[] _sqlDynamicWhereArgs=selectionArgs;
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    String _sortOrder=sortOrder;
    _sqlBuilder.append("SELECT %s FROM person  ");

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=StringUtils.ifNotEmptyAppend(_sqlDynamicWhere, " WHERE ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    if (StringUtils.hasText(_sqlDynamicWhere) && _sqlDynamicWhereArgs!=null) {
      for (String _arg: _sqlDynamicWhereArgs) {
        _contentValues.addWhereArgs(_arg);
      }
    }
    // generation order - BEGIN
    String _sqlOrderByStatement=" ORDER BY name asc"+StringUtils.ifNotEmptyAppend(_sortOrder, ", ");
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END


    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectAll10ColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.contentprovider.kripton35.nolog/persons', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectAll10ColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());

    // execute query
    Cursor _result = database().rawQuery(_sql, _contentValues.whereArgsAsArray());
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, alias_parent_id, city, birth_city, birth_day, value, name, surname FROM person WHERE name like ${data.name} || '%' ORDER BY #{DYNAMIC_ORDER_BY}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>alias_parent_id</dt><dd>is associated to bean's property <strong>parentId</strong></dd>
   * 	<dt>city</dt><dd>is associated to bean's property <strong>city</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>orderBy</dt>is part of order statement resolved at runtime. In above SQL it is displayed as #{DYNAMIC_ORDER_BY}</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${data.name}</dt><dd>is binded to method's parameter <strong>bean.name</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${data}
   * @param orderBy
   * 	is used as <strong>dynamic ORDER BY statement</strong> and it is formatted by ({@link StringUtils#format})
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectOne(Person bean, String orderBy) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT id, alias_parent_id, city, birth_city, birth_day, value, name, surname FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sortOrder=orderBy;

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE name like ? || '%'";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // generation order - BEGIN
    String _sqlOrderByStatement=StringUtils.ifNotEmptyAppend(_sortOrder," ORDER BY ");
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

    String _sql=_sqlBuilder.toString();
    // add where arguments
    _contentValues.addWhereArgs(bean.getName());
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {

      ArrayList<Person> resultList=new ArrayList<Person>(_cursor.getCount());
      Person resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("alias_parent_id");
        int index2=_cursor.getColumnIndex("city");
        int index3=_cursor.getColumnIndex("birth_city");
        int index4=_cursor.getColumnIndex("birth_day");
        int index5=_cursor.getColumnIndex("value");
        int index6=_cursor.getColumnIndex("name");
        int index7=_cursor.getColumnIndex("surname");

        do
         {
          resultBean=new Person();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.parentId=_cursor.getLong(index1); }
          if (!_cursor.isNull(index2)) { resultBean.city=_cursor.getLong(index2); }
          if (!_cursor.isNull(index3)) { resultBean.birthCity=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { resultBean.birthDay=DateUtils.read(_cursor.getString(index4)); }
          if (!_cursor.isNull(index5)) { resultBean.value=_cursor.getLong(index5); }
          if (!_cursor.isNull(index6)) { resultBean.setName(_cursor.getString(index6)); }
          if (!_cursor.isNull(index7)) { resultBean.setSurname(_cursor.getString(index7)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/[*]/test1</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, parentId, city, birthCity, birthDay, value, name, surname FROM Person WHERE name like ${data.name} || '%' ORDER BY #{DYNAMIC_ORDER_BY}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, alias_parent_id, city, birth_city, birth_day, value, name, surname FROM person WHERE name like ${data.name} || '%' ORDER BY #{DYNAMIC_ORDER_BY}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${data.name}</strong> at path segment 1</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.contentprovider.kripton35.nolog/persons/[*]/test1"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor selectOne11(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    String _sortOrder=sortOrder;
    _sqlBuilder.append("SELECT %s FROM person  ");

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE name like ? || '%'";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // generation order - BEGIN
    String _sqlOrderByStatement=StringUtils.ifNotEmptyAppend(_sortOrder," ORDER BY ");
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END


    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectOne11ColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.contentprovider.kripton35.nolog/persons/*/test1', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectOne11ColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter data.name at path segment 1
    _contentValues.addWhereArgs(uri.getPathSegments().get(1));
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());

    // execute query
    Cursor _result = database().rawQuery(_sql, _contentValues.whereArgsAsArray());
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, alias_parent_id, city, birth_city, birth_day, value, name, surname FROM person</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>alias_parent_id</dt><dd>is associated to bean's property <strong>parentId</strong></dd>
   * 	<dt>city</dt><dd>is associated to bean's property <strong>city</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectBean() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BEAN_SQL1;
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {

      ArrayList<Person> resultList=new ArrayList<Person>(_cursor.getCount());
      Person resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("alias_parent_id");
        int index2=_cursor.getColumnIndex("city");
        int index3=_cursor.getColumnIndex("birth_city");
        int index4=_cursor.getColumnIndex("birth_day");
        int index5=_cursor.getColumnIndex("value");
        int index6=_cursor.getColumnIndex("name");
        int index7=_cursor.getColumnIndex("surname");

        do
         {
          resultBean=new Person();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.parentId=_cursor.getLong(index1); }
          if (!_cursor.isNull(index2)) { resultBean.city=_cursor.getLong(index2); }
          if (!_cursor.isNull(index3)) { resultBean.birthCity=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { resultBean.birthDay=DateUtils.read(_cursor.getString(index4)); }
          if (!_cursor.isNull(index5)) { resultBean.value=_cursor.getLong(index5); }
          if (!_cursor.isNull(index6)) { resultBean.setName(_cursor.getString(index6)); }
          if (!_cursor.isNull(index7)) { resultBean.setSurname(_cursor.getString(index7)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test3</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, parentId, city, birthCity, birthDay, value, name, surname FROM Person</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, alias_parent_id, city, birth_city, birth_day, value, name, surname FROM person</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.contentprovider.kripton35.nolog/persons/test3"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor selectBean12(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("SELECT %s FROM person");
    String _sqlWhereStatement="";

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectBean12ColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.contentprovider.kripton35.nolog/persons/test3', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectBean12ColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());

    // execute query
    Cursor _result = database().rawQuery(_sql, _contentValues.whereArgsAsArray());
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, alias_parent_id, city, birth_city, birth_day, value, name, surname FROM person WHERE #{DYNAMIC_WHERE} ORDER BY name</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>alias_parent_id</dt><dd>is associated to bean's property <strong>parentId</strong></dd>
   * 	<dt>city</dt><dd>is associated to bean's property <strong>city</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>where</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{DYNAMIC_WHERE}</dd>
   * </dl>
   *
   * @param cursorListener
   * 	is the cursor listener
   * @param where
   * 	is used as <strong>dynamic WHERE statement</strong> and it is formatted by ({@link StringUtils#format})
   */
  @Override
  public void selectCursorListener(OnReadCursorListener cursorListener, String where) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT id, alias_parent_id, city, birth_city, birth_day, value, name, surname FROM person");
    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=where;
    // generation CODE_001 -- END
    String _sortOrder=null;

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=StringUtils.ifNotEmptyAppend(_sqlDynamicWhere, " WHERE ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // generation order - BEGIN
    String _sqlOrderByStatement=" ORDER BY name";
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

    String _sql=_sqlBuilder.toString();
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {

      if (_cursor.moveToFirst()) {

        do
         {
          cursorListener.onRead(_cursor);
        } while (_cursor.moveToNext());
      }
    }
  }

  public static void clearCompiledStatements() {
    if (insertBeanPreparedStatement0!=null) {
      insertBeanPreparedStatement0.close();
      insertBeanPreparedStatement0=null;
    }
    if (insertNamePreparedStatement1!=null) {
      insertNamePreparedStatement1.close();
      insertNamePreparedStatement1=null;
    }
    if (deleteRawPreparedStatement2!=null) {
      deleteRawPreparedStatement2.close();
      deleteRawPreparedStatement2=null;
    }
    if (deleteBeanPreparedStatement3!=null) {
      deleteBeanPreparedStatement3.close();
      deleteBeanPreparedStatement3=null;
    }
    if (updateRawPreparedStatement4!=null) {
      updateRawPreparedStatement4.close();
      updateRawPreparedStatement4=null;
    }
    if (updateBeanPreparedStatement5!=null) {
      updateBeanPreparedStatement5.close();
      updateBeanPreparedStatement5=null;
    }
  }
}
