package sqlite.feature.javadoc.delete.raw;

import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>DeleteRawPersonDao</code>
 * </p>
 *
 *  @see sqlite.feature.javadoc.Person
 *  @see DeleteRawPersonDao
 *  @see sqlite.feature.javadoc.PersonTable
 */
public class DeleteRawPersonDaoImpl extends Dao implements DeleteRawPersonDao {
  private static SQLiteStatement deleteOneBeanPreparedStatement0;

  private static SQLiteStatement deleteOneBeanPreparedStatement1;

  private static SQLiteStatement deleteAllBeansJQLPreparedStatement2;

  private static SQLiteStatement deleteFromSelectAllBeansJQLPreparedStatement3;

  private static SQLiteStatement deleteRawPreparedStatement4;

  public DeleteRawPersonDaoImpl(BindDeleteRawPersonDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person WHERE id=${id}</pre>
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
  public int deleteOneBean(long id) {
    if (deleteOneBeanPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person WHERE id=?";
      deleteOneBeanPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteOneBeanPreparedStatement0);
    _contentValues.addWhereArgs(String.valueOf(id));

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
   * <pre>DELETE FROM Person WHERE id=${id}</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM person WHERE id=${id}</pre>
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
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  int deleteOneBean0(Uri uri, String selection, String[] selectionArgs) {
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
    // Add parameter id at path segment 1
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
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person WHERE person_surname=${surname}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${surname}</dt><dd>is mapped to method's parameter <strong>surname</strong></dd>
   * </dl>
   *
   * @param surname
   * 	is used as where parameter <strong>${surname}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public int deleteOneBean(String surname) {
    if (deleteOneBeanPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person WHERE person_surname=?";
      deleteOneBeanPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteOneBeanPreparedStatement1);
    _contentValues.addWhereArgs((surname==null?"":surname));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person WHERE person_surname=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteOneBeanPreparedStatement1, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (DELETE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/[*]</pre>
   *
   * <h2>JQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM Person WHERE personSurname=${surname}</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM person WHERE person_surname=${surname}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${surname}</strong> at path segment 1</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/[*]"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  int deleteOneBean1(Uri uri, String selection, String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValues();
    Logger.info("Execute DELETE for URI %s", uri.toString());
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" person_surname=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person WHERE person_surname=?");

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
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person WHERE person_name=${name} and person_surname=${surname} AND student = 0</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${name}</dt><dd>is mapped to method's parameter <strong>name</strong></dd>
   * 	<dt>${surname}</dt><dd>is mapped to method's parameter <strong>surname</strong></dd>
   * </dl>
   *
   * @param name
   * 	is used as where parameter <strong>${name}</strong>
   * @param surname
   * 	is used as where parameter <strong>${surname}</strong>
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteAllBeansJQL(String name, String surname) {
    if (deleteAllBeansJQLPreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person WHERE person_name=? and person_surname=? AND student = 0";
      deleteAllBeansJQLPreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteAllBeansJQLPreparedStatement2);
    _contentValues.addWhereArgs((name==null?"":name));
    _contentValues.addWhereArgs((surname==null?"":surname));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person WHERE person_name=? and person_surname=? AND student = 0");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteAllBeansJQLPreparedStatement2, _contentValues);
    return result!=0;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person WHERE person_surname=${surname} and student = (select student from person where person_name=${name})</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${surname}</dt><dd>is mapped to method's parameter <strong>surname</strong></dd>
   * 	<dt>${name}</dt><dd>is mapped to method's parameter <strong>name</strong></dd>
   * </dl>
   *
   * @param name
   * 	is used as where parameter <strong>${name}</strong>
   * @param surname
   * 	is used as where parameter <strong>${surname}</strong>
   */
  @Override
  public void deleteFromSelectAllBeansJQL(String name, String surname) {
    if (deleteFromSelectAllBeansJQLPreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person WHERE person_surname=? and student = (select student from person where person_name=?)";
      deleteFromSelectAllBeansJQLPreparedStatement3 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteFromSelectAllBeansJQLPreparedStatement3);
    _contentValues.addWhereArgs((surname==null?"":surname));
    _contentValues.addWhereArgs((name==null?"":name));

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
    int result = KriptonDatabaseWrapper.updateDelete(deleteFromSelectAllBeansJQLPreparedStatement3, _contentValues);
  }

  /**
   * <h1>Content provider URI (DELETE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/a/[*]/[*]</pre>
   *
   * <h2>JQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM Person WHERE personSurname=${surname} and student = (select student from Person where personName=${name})</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM person WHERE person_surname=${surname} and student = (select student from person where person_name=${name})</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${surname}</strong> at path segment 2</li>
   * <li><strong>${name}</strong> at path segment 3</li>
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
  int deleteFromSelectAllBeansJQL2(Uri uri, String selection, String[] selectionArgs) {
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
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person WHERE id=${id}</pre>
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
  public long deleteRaw(long id) {
    if (deleteRawPreparedStatement4==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person WHERE id=?";
      deleteRawPreparedStatement4 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteRawPreparedStatement4);
    _contentValues.addWhereArgs(String.valueOf(id));

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
    int result = KriptonDatabaseWrapper.updateDelete(deleteRawPreparedStatement4, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (DELETE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/single/#</pre>
   *
   * <h2>JQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM Person WHERE id=${id}</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM person WHERE id=${id}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${id}</strong> at path segment 2</li>
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
  int deleteRaw3(Uri uri, String selection, String[] selectionArgs) {
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
    // Add parameter id at path segment 2
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
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person WHERE id=${id} #{DYNAMIC_WHERE}</pre>
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
   *
   * @return number of deleted records
   */
  @Override
  public int deleteRawDynamic(long id, String where) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
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
   * <pre>DELETE FROM Person WHERE id=${id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM person WHERE id=${id} #{DYNAMIC_WHERE}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${id}</strong> at path segment 2</li>
   * </ul>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/single2/#"
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  int deleteRawDynamic4(Uri uri, String selection, String[] selectionArgs) {
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
    // Add parameter id at path segment 2
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
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person WHERE id=${id} #{DYNAMIC_WHERE}</pre>
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
   * @return number of deleted records
   */
  @Override
  public int deleteBeanDynamicWithArgs(long id, String where, String[] args) {
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
   * <pre>DELETE FROM Person WHERE id=${id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM person WHERE id=${id} #{DYNAMIC_WHERE}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${id}</strong> at path segment 1</li>
   * </ul>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/#/moreAndMore"
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  int deleteBeanDynamicWithArgs5(Uri uri, String selection, String[] selectionArgs) {
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
    // Add parameter id at path segment 1
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
    if (deleteOneBeanPreparedStatement1!=null) {
      deleteOneBeanPreparedStatement1.close();
      deleteOneBeanPreparedStatement1=null;
    }
    if (deleteAllBeansJQLPreparedStatement2!=null) {
      deleteAllBeansJQLPreparedStatement2.close();
      deleteAllBeansJQLPreparedStatement2=null;
    }
    if (deleteFromSelectAllBeansJQLPreparedStatement3!=null) {
      deleteFromSelectAllBeansJQLPreparedStatement3.close();
      deleteFromSelectAllBeansJQLPreparedStatement3=null;
    }
    if (deleteRawPreparedStatement4!=null) {
      deleteRawPreparedStatement4.close();
      deleteRawPreparedStatement4=null;
    }
  }
}
