package sqlite.feature.javadoc.update.raw;

import android.content.ContentValues;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.Set;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>UpdateRawPersonDao</code>
 * </p>
 *
 *  @see sqlite.feature.javadoc.Person
 *  @see UpdateRawPersonDao
 *  @see sqlite.feature.javadoc.PersonTable
 */
public class UpdateRawPersonDaoImpl extends AbstractDao implements UpdateRawPersonDao {
  private static SQLiteStatement updateAllBeansPreparedStatement0;

  private static SQLiteStatement updateAllBeansJQLPreparedStatement1;

  private static final Set<String> updateAllBeansJQL0ColumnSet = CollectionUtils.asSet(String.class, "student", "person_name");

  private static SQLiteStatement updateFromSelectJQLPreparedStatement2;

  private static final Set<String> updateFromSelectJQL1ColumnSet = CollectionUtils.asSet(String.class, "person_name");

  private static SQLiteStatement updateBeanPreparedStatement3;

  private static final Set<String> updateBean2ColumnSet = CollectionUtils.asSet(String.class, "person_name");

  private static final Set<String> updateBeanDynamic3ColumnSet = CollectionUtils.asSet(String.class, "person_name");

  private static final Set<String> updateBeanDynamicWithArgs4ColumnSet = CollectionUtils.asSet(String.class, "person_name");

  public UpdateRawPersonDaoImpl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET person_name=:personName</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>person_name</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * </dl>
   *
   * @param personName
   * 	is used as updated field <strong>personName</strong>
   *
   * @return number of updated records
   */
  @Override
  public int updateAllBeans(String personName) {
    if (updateAllBeansPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="UPDATE person SET person_name=?";
      updateAllBeansPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateAllBeansPreparedStatement0);
    if (personName!=null) {
      _contentValues.put("person_name", personName);
    } else {
      _contentValues.putNull("person_name");
    }


    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name");

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
    int result = KriptonDatabaseWrapper.updateDelete(updateAllBeansPreparedStatement0, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET student = :student, person_name=:personName  where person_surname=${personSurname}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>student</li>
   * 	<li>person_name</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${personSurname}</dt><dd>is mapped to method's parameter <strong>surname</strong></dd>
   * </dl>
   *
   * @param name
   * 	is used as updated field <strong>personName</strong>
   * @param surname
   * 	is used as where parameter <strong>${personSurname}</strong>
   * @param student
   * 	is used as updated field <strong>student</strong>
   */
  @Override
  public void updateAllBeansJQL(String name, String surname, boolean student) {
    if (updateAllBeansJQLPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="UPDATE person SET student = ?, person_name=?  where person_surname=?";
      updateAllBeansJQLPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateAllBeansJQLPreparedStatement1);
    _contentValues.put("student", student);
    if (name!=null) {
      _contentValues.put("person_name", name);
    } else {
      _contentValues.putNull("person_name");
    }

    _contentValues.addWhereArgs((surname==null?"":surname));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET student = :student, person_name=:person_name  where person_surname=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(updateAllBeansJQLPreparedStatement1, _contentValues);
  }

  /**
   * <h1>Content provider URI (UPDATE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/jql/[*]</pre>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE perSon SET student = ${student}, PersonnAme=${personName}  where personSurname=${personSurname}</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE person SET student = ${student}, PersonnAme=${personName}  where person_surname=${personSurname}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${personSurname}</strong> at path segment 2</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/jql/[*]"
   * @param contentValues content values
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  int updateAllBeansJQL0(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    Logger.info("Execute UPDATE for URI %s", uri.toString());
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where person_surname=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // Add parameter personSurname at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    for (String columnName:_contentValues.values().keySet()) {
      if (!updateAllBeansJQL0ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/jql/*', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "person" ));
      }
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET student = :student, person_name=:person_name  where person_surname=?");

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
    int result = database().update("person", _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET person_name=${personName}, student = (select student from person where person_surname=${surname})</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>person_name</li>
   * 	<li>student</li>
   * </ul>
   *
   * <h2>Parameters:</h2>
   * <dl>
   * 	<dt>${name}</dt><dd>is mapped to method's parameter <strong>name</strong></dd>
   * 	<dt>${surname}</dt><dd>is mapped to method's parameter <strong>surname</strong></dd>
   * </dl>
   *
   * @param surname
   * 	is used as for parameter <strong>surname</strong>
   * @param name
   * 	is used as for parameter <strong>personName</strong>
   */
  @Override
  public void updateFromSelectAllBeansJQL(String surname, String name) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    if (name!=null) {
      _contentValues.put("person_name", name);
    } else {
      _contentValues.putNull("person_name");
    }
    // build where condition
    _contentValues.addWhereArgs((surname==null?"":surname));
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 

      Logger.info("UPDATE person SET Personname=${personName}, student = (select student from person where person_surname=?)");

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

    database().execSQL("UPDATE person SET Personname=?, student = (select student from person where person_surname=?)", _contentValues.whereArgsAsArray());
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET person_name=:personName where student= (select student from person where person_surname=${surname})</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>person_name</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${surname}</dt><dd>is mapped to method's parameter <strong>surname</strong></dd>
   * </dl>
   *
   * @param personName
   * 	is used as updated field <strong>personName</strong>
   * @param surname
   * 	is used as where parameter <strong>${surname}</strong>
   */
  @Override
  public void updateFromSelectJQL(String personName, String surname) {
    if (updateFromSelectJQLPreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="UPDATE person SET person_name=? where student= (select student from person where person_surname=?)";
      updateFromSelectJQLPreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateFromSelectJQLPreparedStatement2);
    if (personName!=null) {
      _contentValues.put("person_name", personName);
    } else {
      _contentValues.putNull("person_name");
    }

    _contentValues.addWhereArgs((surname==null?"":surname));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name where student= (select student from person where person_surname=?)");

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
    int result = KriptonDatabaseWrapper.updateDelete(updateFromSelectJQLPreparedStatement2, _contentValues);
  }

  /**
   * <h1>Content provider URI (UPDATE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/jql/all/[*]</pre>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE Person SET personname=${personName} where student= (select student from Person where personsurname=${surname})</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE person SET personname=${personName} where student= (select student from person where person_surname=${surname})</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${surname}</strong> at path segment 3</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/jql/all/[*]"
   * @param contentValues content values
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  int updateFromSelectJQL1(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    Logger.info("Execute UPDATE for URI %s", uri.toString());
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where student= (select student from person where person_surname=?)";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    for (String columnName:_contentValues.values().keySet()) {
      if (!updateFromSelectJQL1ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/jql/all/*', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "person" ));
      }
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name where student= (select student from person where person_surname=?)");

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
    int result = database().update("person", _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET person_name=:personName WHERE id=${id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>person_name</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param personName
   * 	is used as updated field <strong>personName</strong>
   *
   * @return number of updated records
   */
  @Override
  public int updateBean(long id, String personName) {
    if (updateBeanPreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="UPDATE person SET person_name=? WHERE id=?";
      updateBeanPreparedStatement3 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateBeanPreparedStatement3);
    if (personName!=null) {
      _contentValues.put("person_name", personName);
    } else {
      _contentValues.putNull("person_name");
    }

    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name WHERE id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(updateBeanPreparedStatement3, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (UPDATE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#</pre>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE Person SET personName=${personName} WHERE id=${id}</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE person SET personName=${personName} WHERE id=${id}</pre>
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
   * @param uri "content://sqlite.feature.javadoc.bean/persons/#"
   * @param contentValues content values
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  int updateBean2(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
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
    // Add parameter id at path segment 1
    _contentValues.addWhereArgs(uri.getPathSegments().get(1));
    for (String columnName:_contentValues.values().keySet()) {
      if (!updateBean2ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/#', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "person" ));
      }
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name WHERE id=?");

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
    int result = database().update("person", _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET person_name=:personName WHERE id=${id} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>person_name</li>
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
   * @param where
   * 	is used as dynamic where conditions
   * @param personName
   * 	is used as updated field <strong>personName</strong>
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   *
   * @return number of updated records
   */
  @Override
  public int updateBeanDynamic(String where, String personName, long id) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    if (personName!=null) {
      _contentValues.put("person_name", personName);
    } else {
      _contentValues.putNull("person_name");
    }

    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=where;
    // generation CODE_001 -- END
    StringBuilder _sqlBuilder=sqlBuilder();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?"+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // generate sql
    String _sql=String.format("UPDATE person SET person_name=? WHERE id=?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name WHERE id=?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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
    int result = KriptonDatabaseWrapper.updateDelete(_context, _sql, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (UPDATE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#/more</pre>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE Person SET personName=${personName} WHERE id=${id} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE person SET personName=${personName} WHERE id=${id} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${id}</strong> at path segment 1</li>
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
  int updateBeanDynamic3(Uri uri, ContentValues contentValues, String selection,
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
    String _sqlWhereStatement=" id=?"+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // Add parameter id at path segment 1
    _contentValues.addWhereArgs(uri.getPathSegments().get(1));
    for (String columnName:_contentValues.values().keySet()) {
      if (!updateBeanDynamic3ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/#/more', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "person" ));
      }
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name WHERE id=?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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
    int result = database().update("person", _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET person_name=:personName WHERE id=${id} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>person_name</li>
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
   * @param personName
   * 	is used as updated field <strong>personName</strong>
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
  public int updateBeanDynamicWithArgs(String personName, long id, String where, String[] args) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    if (personName!=null) {
      _contentValues.put("person_name", personName);
    } else {
      _contentValues.putNull("person_name");
    }

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
    String _sqlWhereStatement=" id=?"+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    if (StringUtils.hasText(_sqlDynamicWhere) && _sqlDynamicWhereArgs!=null) {
      for (String _arg: _sqlDynamicWhereArgs) {
        _contentValues.addWhereArgs(_arg);
      }
    }

    // generate sql
    String _sql=String.format("UPDATE person SET person_name=? WHERE id=?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name WHERE id=?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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
    int result = KriptonDatabaseWrapper.updateDelete(_context, _sql, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (UPDATE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#/moreAndMore</pre>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE Person SET personName=${personName} WHERE id=${id} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE person SET personName=${personName} WHERE id=${id} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${id}</strong> at path segment 1</li>
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
  int updateBeanDynamicWithArgs4(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
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
    String _sqlWhereStatement=" id=?"+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    if (StringUtils.hasText(_sqlDynamicWhere) && _sqlDynamicWhereArgs!=null) {
      for (String _arg: _sqlDynamicWhereArgs) {
        _contentValues.addWhereArgs(_arg);
      }
    }
    // Add parameter id at path segment 1
    _contentValues.addWhereArgs(uri.getPathSegments().get(1));
    if (StringUtils.hasText(_sqlDynamicWhere) && _sqlDynamicWhereArgs!=null) {
      for (String _arg: _sqlDynamicWhereArgs) {
        _contentValues.addWhereArgs(_arg);
      }
    }
    for (String columnName:_contentValues.values().keySet()) {
      if (!updateBeanDynamicWithArgs4ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/#/moreAndMore', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "person" ));
      }
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET person_name=:person_name WHERE id=?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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
    int result = database().update("person", _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  public static void clearCompiledStatements() {
    if (updateAllBeansPreparedStatement0!=null) {
      updateAllBeansPreparedStatement0.close();
      updateAllBeansPreparedStatement0=null;
    }
    if (updateAllBeansJQLPreparedStatement1!=null) {
      updateAllBeansJQLPreparedStatement1.close();
      updateAllBeansJQLPreparedStatement1=null;
    }
    if (updateFromSelectJQLPreparedStatement2!=null) {
      updateFromSelectJQLPreparedStatement2.close();
      updateFromSelectJQLPreparedStatement2=null;
    }
    if (updateBeanPreparedStatement3!=null) {
      updateBeanPreparedStatement3.close();
      updateBeanPreparedStatement3=null;
    }
  }
}
