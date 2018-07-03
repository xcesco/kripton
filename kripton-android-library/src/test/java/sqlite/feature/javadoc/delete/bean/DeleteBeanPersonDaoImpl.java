package sqlite.feature.javadoc.delete.bean;

import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import sqlite.feature.javadoc.Person;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>DeleteBeanPersonDao</code>
 * </p>
 *
 *  @see Person
 *  @see DeleteBeanPersonDao
 *  @see sqlite.feature.javadoc.PersonTable
 */
public class DeleteBeanPersonDaoImpl extends Dao implements DeleteBeanPersonDao {
  private static SQLiteStatement deleteOneBeanPreparedStatement0;

  private static SQLiteStatement deleteAllBeansJQLPreparedStatement1;

  private static SQLiteStatement deleteFromSelectAllBeansJQLPreparedStatement2;

  private static SQLiteStatement deleteBeanPreparedStatement3;

  public DeleteBeanPersonDaoImpl(BindDeleteBeanPersonDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM person WHERE id=${bean.id}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   *
   * @return number of deleted records
   */
  @Override
  public int deleteOneBean(Person bean) {
    if (deleteOneBeanPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person WHERE id=?";
      deleteOneBeanPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteOneBeanPreparedStatement0);
    _contentValues.addWhereArgs(String.valueOf(bean.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person WHERE id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteOneBeanPreparedStatement0, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (DELETE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#</pre>
   *
   * <h2>JQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM Person WHERE id=${bean.id}</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM person WHERE id=${bean.id}</pre>
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
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  int deleteOneBean0ForContentProvider(Uri uri, String selection, String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValues();
    Logger.info("Execute DELETE for URI %s", uri.toString());
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
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person WHERE id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END

    // execute SQL
    int result = database().delete("person", _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM person WHERE person_name=${bean.personName} AND person_surname=${bean.personSurname} AND student = 0</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.personName</dt><dd>is mapped to method's parameter <strong>bean.personName</strong></dd>
   * 	<dt>:bean.personSurname</dt><dd>is mapped to method's parameter <strong>bean.personSurname</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   */
  @Override
  public void deleteAllBeansJQL(Person bean) {
    if (deleteAllBeansJQLPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person WHERE person_name=? AND person_surname=? AND student = 0";
      deleteAllBeansJQLPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteAllBeansJQLPreparedStatement1);
    _contentValues.addWhereArgs((bean.getPersonName()==null?"":bean.getPersonName()));
    _contentValues.addWhereArgs((bean.getPersonSurname()==null?"":bean.getPersonSurname()));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person WHERE person_name=? AND person_surname=? AND student = 0");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteAllBeansJQLPreparedStatement1, _contentValues);
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM person WHERE person_surname=${bean.personSurname} and student = (select student from person where person_name=${bean.personName})</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.personSurname</dt><dd>is mapped to method's parameter <strong>bean.personSurname</strong></dd>
   * 	<dt>:bean.personName</dt><dd>is mapped to method's parameter <strong>bean.personName</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   *
   * @return number of deleted records
   */
  @Override
  public int deleteFromSelectAllBeansJQL(Person bean) {
    if (deleteFromSelectAllBeansJQLPreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person WHERE person_surname=? and student = (select student from person where person_name=?)";
      deleteFromSelectAllBeansJQLPreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteFromSelectAllBeansJQLPreparedStatement2);
    _contentValues.addWhereArgs((bean.getPersonSurname()==null?"":bean.getPersonSurname()));
    _contentValues.addWhereArgs((bean.getPersonName()==null?"":bean.getPersonName()));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person WHERE person_surname=? and student = (select student from person where person_name=?)");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteFromSelectAllBeansJQLPreparedStatement2, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (DELETE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/a/[*]/[*]</pre>
   *
   * <h2>JQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM Person WHERE personSurname=${bean.personSurname} and student = (select student from Person where personName=${bean.personName})</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM person WHERE person_surname=${bean.personSurname} and student = (select student from person where person_name=${bean.personName})</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>:bean.personSurname</strong> at path segment 2</li>
   * <li><strong>:bean.personName</strong> at path segment 3</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/a/[*]/[*]"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  int deleteFromSelectAllBeansJQL1ForContentProvider(Uri uri, String selection,
      String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValues();
    Logger.info("Execute DELETE for URI %s", uri.toString());
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" person_surname=? and student = (select student from person where person_name=?)";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // Add parameter bean.personSurname at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    // Add parameter bean.personName at path segment 3
    _contentValues.addWhereArgs(uri.getPathSegments().get(3));
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person WHERE person_surname=? and student = (select student from person where person_name=?)");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END

    // execute SQL
    int result = database().delete("person", _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM person WHERE id=${bean.id}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   *
   * @return number of deleted records
   */
  @Override
  public int deleteBean(Person bean) {
    if (deleteBeanPreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person WHERE id=?";
      deleteBeanPreparedStatement3 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteBeanPreparedStatement3);
    _contentValues.addWhereArgs(String.valueOf(bean.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person WHERE id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteBeanPreparedStatement3, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (DELETE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/single/#</pre>
   *
   * <h2>JQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM Person WHERE id=${bean.id}</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM person WHERE id=${bean.id}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>:bean.id</strong> at path segment 2</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/single/#"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  int deleteBean2ForContentProvider(Uri uri, String selection, String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValues();
    Logger.info("Execute DELETE for URI %s", uri.toString());
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // Add parameter bean.id at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person WHERE id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END

    // execute SQL
    int result = database().delete("person", _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM person WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
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
   * @return number of deleted records
   */
  @Override
  public int deleteBeanDynamic(Person bean, String where) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
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
    String _sql=String.format("DELETE FROM person WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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
   * <h1>Content provider URI (DELETE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/single2/#</pre>
   *
   * <h2>JQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM Person WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM person WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>:bean.id</strong> at path segment 2</li>
   * </ul>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/single2/#"
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  int deleteBeanDynamic3ForContentProvider(Uri uri, String selection, String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValues();
    Logger.info("Execute DELETE for URI %s", uri.toString());
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
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END

    // execute SQL
    int result = database().delete("person", _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM person WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
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
   * @return number of deleted records
   */
  @Override
  public int deleteBeanDynamicWithArgs(Person bean, String where, String[] args) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
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
    String _sql=String.format("DELETE FROM person WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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
   * <h1>Content provider URI (DELETE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#/moreAndMore</pre>
   *
   * <h2>JQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM Person WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM person WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>:bean.id</strong> at path segment 1</li>
   * </ul>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/#/moreAndMore"
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  int deleteBeanDynamicWithArgs4ForContentProvider(Uri uri, String selection,
      String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValues();
    Logger.info("Execute DELETE for URI %s", uri.toString());
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
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person WHERE id=? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END

    // execute SQL
    int result = database().delete("person", _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  public static void clearCompiledStatements() {
    if (deleteOneBeanPreparedStatement0!=null) {
      deleteOneBeanPreparedStatement0.close();
      deleteOneBeanPreparedStatement0=null;
    }
    if (deleteAllBeansJQLPreparedStatement1!=null) {
      deleteAllBeansJQLPreparedStatement1.close();
      deleteAllBeansJQLPreparedStatement1=null;
    }
    if (deleteFromSelectAllBeansJQLPreparedStatement2!=null) {
      deleteFromSelectAllBeansJQLPreparedStatement2.close();
      deleteFromSelectAllBeansJQLPreparedStatement2=null;
    }
    if (deleteBeanPreparedStatement3!=null) {
      deleteBeanPreparedStatement3.close();
      deleteBeanPreparedStatement3=null;
    }
  }
}
