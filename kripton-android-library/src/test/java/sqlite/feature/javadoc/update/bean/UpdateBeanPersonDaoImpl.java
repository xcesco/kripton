package sqlite.feature.javadoc.update.bean;

import android.content.ContentValues;
import android.net.Uri;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseHelper;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.io.IOException;
import java.util.Set;
import sqlite.feature.javadoc.Person;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>UpdateBeanPersonDao</code>
 * </p>
 *
 *  @see Person
 *  @see UpdateBeanPersonDao
 *  @see sqlite.feature.javadoc.PersonTable
 */
public class UpdateBeanPersonDaoImpl extends Dao implements UpdateBeanPersonDao {
  private static SupportSQLiteStatement updateAllBeansPreparedStatement0;

  private static final Set<String> updateAllBeans0ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "person_name", "person_surname", "student");

  private static SupportSQLiteStatement updateOneBeanPreparedStatement1;

  private static final Set<String> updateOneBean1ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "person_name", "person_surname", "student");

  private static final Set<String> updateOneBeanWithDynamic2ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "person_name", "person_surname", "student");

  private static final Set<String> updateOneBeanWithDynamicAndArgs3ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "person_name", "person_surname", "student");

  private static SupportSQLiteStatement updateAllBeansJQLPreparedStatement2;

  private static final Set<String> updateAllBeansJQL4ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "person_surname", "student");

  private static SupportSQLiteStatement updateFromSelectJQLPreparedStatement3;

  private static final Set<String> updateFromSelectJQL5ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "person_name");

  private static final Set<String> updateBeanDynamic6ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "person_name", "person_surname", "student");

  private static final Set<String> updateBeanDynamicWithArgs7ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "person_name", "person_surname", "student");

  public UpdateBeanPersonDaoImpl(BindUpdateBeanPersonDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET person_name=:personName, person_surname=:personSurname, student=:student</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>person_name</dt><dd>is mapped to <strong>:bean.personName</strong></dd>
   * 	<dt>person_surname</dt><dd>is mapped to <strong>:bean.personSurname</strong></dd>
   * 	<dt>student</dt><dd>is mapped to <strong>:bean.student</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   *
   * @return number of updated records
   */
  @Override
  public int updateAllBeans(Person bean) {
    if (updateAllBeansPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="UPDATE person SET person_name=?, person_surname=?, student=?";
      updateAllBeansPreparedStatement0 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateAllBeansPreparedStatement0);
    _contentValues.put("person_name", bean.getPersonName());
    _contentValues.put("person_surname", bean.getPersonSurname());
    _contentValues.put("student", bean.isStudent());


    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name, person_surname=:person_surname, student=:student");

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
    int result = KriptonDatabaseHelper.updateDelete(updateAllBeansPreparedStatement0, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (UPDATE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons</pre>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE Person SET personName=:bean.personName, personSurname=:bean.personSurname, student=:bean.student</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE person SET person_name=:bean.personName, person_surname=:bean.personSurname, student=:bean.student</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons"
   * @param contentValues content values
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  int updateAllBeans0ForContentProvider(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    Logger.info("Execute UPDATE for URI %s", uri.toString());
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sqlWhereStatement="";
    for (String columnName:_contentValues.values().keySet()) {
      if (!updateAllBeans0ForContentProviderColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "person" ));
      }
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name, person_surname=:person_surname, student=:student");

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

    // execute SQL
    // conflict algorithm NONE
    int result = database().update("person", 0, _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET person_name=:personName, person_surname=:personSurname, student=:student WHERE id=${bean.id}</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>person_name</dt><dd>is mapped to <strong>:bean.personName</strong></dd>
   * 	<dt>person_surname</dt><dd>is mapped to <strong>:bean.personSurname</strong></dd>
   * 	<dt>student</dt><dd>is mapped to <strong>:bean.student</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   *
   * @return number of updated records
   */
  @Override
  public int updateOneBean(Person bean) {
    if (updateOneBeanPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="UPDATE person SET person_name=?, person_surname=?, student=? WHERE id=?";
      updateOneBeanPreparedStatement1 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateOneBeanPreparedStatement1);
    _contentValues.put("person_name", bean.getPersonName());
    _contentValues.put("person_surname", bean.getPersonSurname());
    _contentValues.put("student", bean.isStudent());

    _contentValues.addWhereArgs(String.valueOf(bean.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name, person_surname=:person_surname, student=:student WHERE id=?");

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
    int result = KriptonDatabaseHelper.updateDelete(updateOneBeanPreparedStatement1, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (UPDATE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#</pre>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE Person SET personName=:bean.personName, personSurname=:bean.personSurname, student=:bean.student WHERE id=${bean.id}</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE person SET person_name=:bean.personName, person_surname=:bean.personSurname, student=:bean.student WHERE id=${bean.id}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>:bean.id</strong> at path segment 1</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/#"
   * @param contentValues content values
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  int updateOneBean1ForContentProvider(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    Logger.info("Execute UPDATE for URI %s", uri.toString());
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // Add parameter bean.id at path segment 1
    _contentValues.addWhereArgs(uri.getPathSegments().get(1));
    for (String columnName:_contentValues.values().keySet()) {
      if (!updateOneBean1ForContentProviderColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/#', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "person" ));
      }
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name, person_surname=:person_surname, student=:student WHERE id=?");

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

    // execute SQL
    // conflict algorithm NONE
    int result = database().update("person", 0, _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET person_name=:personName, person_surname=:personSurname, student=:student WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>person_name</dt><dd>is mapped to <strong>:bean.personName</strong></dd>
   * 	<dt>person_surname</dt><dd>is mapped to <strong>:bean.personSurname</strong></dd>
   * 	<dt>student</dt><dd>is mapped to <strong>:bean.student</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>where</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{DYNAMIC_WHERE}</dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   * @param where
   * 	is used as dynamic where conditions
   *
   * @return number of updated records
   */
  @Override
  public int updateOneBeanWithDynamic(Person bean, String where) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.put("person_name", bean.getPersonName());
    _contentValues.put("person_surname", bean.getPersonSurname());
    _contentValues.put("student", bean.isStudent());

    _contentValues.addWhereArgs(String.valueOf(bean.id));

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
    String _sql=String.format("UPDATE person SET person_name=?, person_surname=?, student=? WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name, person_surname=:person_surname, student=:student WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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
    int result = KriptonDatabaseHelper.updateDelete(_context, _sql, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (UPDATE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamic/#</pre>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE Person SET personName=:bean.personName, personSurname=:bean.personSurname, student=:bean.student WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE person SET person_name=:bean.personName, person_surname=:bean.personSurname, student=:bean.student WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>:bean.id</strong> at path segment 2</li>
   * </ul>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/dynamic/#"
   * @param contentValues content values
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  int updateOneBeanWithDynamic2ForContentProvider(Uri uri, ContentValues contentValues,
      String selection, String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    Logger.info("Execute UPDATE for URI %s", uri.toString());
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
    // Add parameter bean.id at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    for (String columnName:_contentValues.values().keySet()) {
      if (!updateOneBeanWithDynamic2ForContentProviderColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/dynamic/#', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "person" ));
      }
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name, person_surname=:person_surname, student=:student WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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

    // execute SQL
    // conflict algorithm NONE
    int result = database().update("person", 0, _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET person_name=:personName, person_surname=:personSurname, student=:student WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>person_name</dt><dd>is mapped to <strong>:bean.personName</strong></dd>
   * 	<dt>person_surname</dt><dd>is mapped to <strong>:bean.personSurname</strong></dd>
   * 	<dt>student</dt><dd>is mapped to <strong>:bean.student</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>where</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{DYNAMIC_WHERE}</dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   * @param where
   * 	is used as dynamic where conditions
   * @param args
   * 	is used as <code>:args</code>
   *
   * @return number of updated records
   */
  @Override
  public int updateOneBeanWithDynamicAndArgs(Person bean, String where, String[] args) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.put("person_name", bean.getPersonName());
    _contentValues.put("person_surname", bean.getPersonSurname());
    _contentValues.put("student", bean.isStudent());

    _contentValues.addWhereArgs(String.valueOf(bean.id));

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
    String _sql=String.format("UPDATE person SET person_name=?, person_surname=?, student=? WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name, person_surname=:person_surname, student=:student WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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
    int result = KriptonDatabaseHelper.updateDelete(_context, _sql, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (UPDATE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamicArgs/#</pre>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE Person SET personName=:bean.personName, personSurname=:bean.personSurname, student=:bean.student WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE person SET person_name=:bean.personName, person_surname=:bean.personSurname, student=:bean.student WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>:bean.id</strong> at path segment 2</li>
   * </ul>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/dynamicArgs/#"
   * @param contentValues content values
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  int updateOneBeanWithDynamicAndArgs3ForContentProvider(Uri uri, ContentValues contentValues,
      String selection, String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    Logger.info("Execute UPDATE for URI %s", uri.toString());
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
    // Add parameter bean.id at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    if (StringUtils.hasText(_sqlDynamicWhere) && _sqlDynamicWhereArgs!=null) {
      for (String _arg: _sqlDynamicWhereArgs) {
        _contentValues.addWhereArgs(_arg);
      }
    }
    for (String columnName:_contentValues.values().keySet()) {
      if (!updateOneBeanWithDynamicAndArgs3ForContentProviderColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/dynamicArgs/#', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "person" ));
      }
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name, person_surname=:person_surname, student=:student WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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

    // execute SQL
    // conflict algorithm NONE
    int result = database().update("person", 0, _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET person_surname=:personSurname, student = :student</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>person_surname</dt><dd>is mapped to <strong>:bean.personSurname</strong></dd>
   * 	<dt>student</dt><dd>is mapped to <strong>:bean.student</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   */
  @Override
  public void updateAllBeansJQL(Person bean) {
    if (updateAllBeansJQLPreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="UPDATE person SET person_surname=?, student = ?";
      updateAllBeansJQLPreparedStatement2 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateAllBeansJQLPreparedStatement2);
    _contentValues.put("person_surname", bean.getPersonSurname());
    _contentValues.put("student", bean.isStudent());


    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_surname=:person_surname, student = :student");

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
    int result = KriptonDatabaseHelper.updateDelete(updateAllBeansJQLPreparedStatement2, _contentValues);
  }

  /**
   * <h1>Content provider URI (UPDATE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/jql</pre>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE Person SET personSurname=${bean.personSurname}, student = ${bean.student}</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE person SET person_surname=${bean.personSurname}, student = ${bean.student}</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/jql"
   * @param contentValues content values
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  int updateAllBeansJQL4ForContentProvider(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    Logger.info("Execute UPDATE for URI %s", uri.toString());
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sqlWhereStatement="";
    for (String columnName:_contentValues.values().keySet()) {
      if (!updateAllBeansJQL4ForContentProviderColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/jql', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "person" ));
      }
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_surname=:person_surname, student = :student");

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

    // execute SQL
    // conflict algorithm NONE
    int result = database().update("person", 0, _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET person_name=:personName, student = (select student from person where id=0)</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>student</dt><dd>is mapped to <strong>:bean.student</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   */
  @Override
  public void updateFromSelectAllBeansJQL(Person bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.put("person_name", bean.getPersonName());
    _contentValues.put("student", bean.isStudent());


    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _sqlBuilder=sqlBuilder();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where id=0";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // generate sql
    String _sql="UPDATE person SET person_name=?, student = (select student from person where id=0)";
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name, student = (select student from person where id=0)");

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
    int result = KriptonDatabaseHelper.updateDelete(_context, _sql, _contentValues);
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET person_name=:personname where student = (select student from person where id=${bean.id})</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>person_name</dt><dd>is mapped to <strong>:bean.personName</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   *
   * @return number of updated records
   */
  @Override
  public int updateFromSelectJQL(Person bean) {
    if (updateFromSelectJQLPreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="UPDATE person SET person_name=? where student = (select student from person where id=?)";
      updateFromSelectJQLPreparedStatement3 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateFromSelectJQLPreparedStatement3);
    _contentValues.put("person_name", bean.getPersonName());

    _contentValues.addWhereArgs(String.valueOf(bean.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name where student = (select student from person where id=?)");

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
    int result = KriptonDatabaseHelper.updateDelete(updateFromSelectJQLPreparedStatement3, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (UPDATE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/jql/one/b/#</pre>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE Person SET personName=${bean.personname} where student = (select student from Person where id=${bean.id})</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE person SET person_name=${bean.personname} where student = (select student from person where id=${bean.id})</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>:bean.id</strong> at path segment 4</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/jql/one/b/#"
   * @param contentValues content values
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  int updateFromSelectJQL5ForContentProvider(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    Logger.info("Execute UPDATE for URI %s", uri.toString());
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where student = (select student from person where id=?)";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // Add parameter bean.id at path segment 4
    _contentValues.addWhereArgs(uri.getPathSegments().get(4));
    for (String columnName:_contentValues.values().keySet()) {
      if (!updateFromSelectJQL5ForContentProviderColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/jql/one/b/#', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "person" ));
      }
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name where student = (select student from person where id=?)");

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

    // execute SQL
    // conflict algorithm NONE
    int result = database().update("person", 0, _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET person_name=:personName, person_surname=:personSurname, student=:student WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>person_name</dt><dd>is mapped to <strong>:bean.personName</strong></dd>
   * 	<dt>person_surname</dt><dd>is mapped to <strong>:bean.personSurname</strong></dd>
   * 	<dt>student</dt><dd>is mapped to <strong>:bean.student</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>where</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{DYNAMIC_WHERE}</dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   * @param where
   * 	is used as dynamic where conditions
   *
   * @return number of updated records
   */
  @Override
  public int updateBeanDynamic(Person bean, String where) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.put("person_name", bean.getPersonName());
    _contentValues.put("person_surname", bean.getPersonSurname());
    _contentValues.put("student", bean.isStudent());

    _contentValues.addWhereArgs(String.valueOf(bean.id));

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
    String _sql=String.format("UPDATE person SET person_name=?, person_surname=?, student=? WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name, person_surname=:person_surname, student=:student WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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
    int result = KriptonDatabaseHelper.updateDelete(_context, _sql, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (UPDATE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#/more</pre>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE Person SET personName=:bean.personName, personSurname=:bean.personSurname, student=:bean.student WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE person SET person_name=:bean.personName, person_surname=:bean.personSurname, student=:bean.student WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>:bean.id</strong> at path segment 1</li>
   * </ul>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/#/more"
   * @param contentValues content values
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  int updateBeanDynamic6ForContentProvider(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    Logger.info("Execute UPDATE for URI %s", uri.toString());
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
    // Add parameter bean.id at path segment 1
    _contentValues.addWhereArgs(uri.getPathSegments().get(1));
    for (String columnName:_contentValues.values().keySet()) {
      if (!updateBeanDynamic6ForContentProviderColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/#/more', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "person" ));
      }
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name, person_surname=:person_surname, student=:student WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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

    // execute SQL
    // conflict algorithm NONE
    int result = database().update("person", 0, _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET person_name=:personName, person_surname=:personSurname, student=:student WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>person_name</dt><dd>is mapped to <strong>:bean.personName</strong></dd>
   * 	<dt>person_surname</dt><dd>is mapped to <strong>:bean.personSurname</strong></dd>
   * 	<dt>student</dt><dd>is mapped to <strong>:bean.student</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>where</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{DYNAMIC_WHERE}</dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   * @param where
   * 	is used as dynamic where conditions
   * @param args
   * 	is used as <code>:args</code>
   *
   * @return number of updated records
   */
  @Override
  public int updateBeanDynamicWithArgs(Person bean, String where, String[] args) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.put("person_name", bean.getPersonName());
    _contentValues.put("person_surname", bean.getPersonSurname());
    _contentValues.put("student", bean.isStudent());

    _contentValues.addWhereArgs(String.valueOf(bean.id));

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
    String _sql=String.format("UPDATE person SET person_name=?, person_surname=?, student=? WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name, person_surname=:person_surname, student=:student WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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
    int result = KriptonDatabaseHelper.updateDelete(_context, _sql, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (UPDATE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#/moreAndMore</pre>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE Person SET personName=:bean.personName, personSurname=:bean.personSurname, student=:bean.student WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE person SET person_name=:bean.personName, person_surname=:bean.personSurname, student=:bean.student WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>:bean.id</strong> at path segment 1</li>
   * </ul>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/#/moreAndMore"
   * @param contentValues content values
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  int updateBeanDynamicWithArgs7ForContentProvider(Uri uri, ContentValues contentValues,
      String selection, String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    Logger.info("Execute UPDATE for URI %s", uri.toString());
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
    // Add parameter bean.id at path segment 1
    _contentValues.addWhereArgs(uri.getPathSegments().get(1));
    if (StringUtils.hasText(_sqlDynamicWhere) && _sqlDynamicWhereArgs!=null) {
      for (String _arg: _sqlDynamicWhereArgs) {
        _contentValues.addWhereArgs(_arg);
      }
    }
    for (String columnName:_contentValues.values().keySet()) {
      if (!updateBeanDynamicWithArgs7ForContentProviderColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/#/moreAndMore', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "person" ));
      }
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name, person_surname=:person_surname, student=:student WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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

    // execute SQL
    // conflict algorithm NONE
    int result = database().update("person", 0, _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  public static void clearCompiledStatements() {
    try {
      if (updateAllBeansPreparedStatement0!=null) {
        updateAllBeansPreparedStatement0.close();
        updateAllBeansPreparedStatement0=null;
      }
      if (updateOneBeanPreparedStatement1!=null) {
        updateOneBeanPreparedStatement1.close();
        updateOneBeanPreparedStatement1=null;
      }
      if (updateAllBeansJQLPreparedStatement2!=null) {
        updateAllBeansJQLPreparedStatement2.close();
        updateAllBeansJQLPreparedStatement2=null;
      }
      if (updateFromSelectJQLPreparedStatement3!=null) {
        updateFromSelectJQLPreparedStatement3.close();
        updateFromSelectJQLPreparedStatement3=null;
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
