package sqlite.feature.javadoc.delete.raw;

import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
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
public class DeleteRawPersonDaoImpl extends AbstractDao implements DeleteRawPersonDao {
  private SQLiteStatement deleteOneBeanPreparedStatement0;

  private SQLiteStatement deleteAllBeansJQLPreparedStatement1;

  private SQLiteStatement deleteFromSelectAllBeansJQLPreparedStatement2;

  private SQLiteStatement deleteRawPreparedStatement3;

  public DeleteRawPersonDaoImpl(BindDeleteRawPersonDataSource dataSet) {
    super(dataSet);
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
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (deleteOneBeanPreparedStatement0==null) {
      StringBuilder _sqlBuilder=getSQLStringBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" id=?";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="DELETE FROM person WHERE id=?";
      deleteOneBeanPreparedStatement0 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    // log section BEGIN
    if (this.dataSource.logEnabled) {

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
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, deleteOneBeanPreparedStatement0, _contentValues);
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
    StringBuilder _sqlBuilder=getSQLStringBuilder();
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
    if (this.dataSource.logEnabled) {

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
   * <pre>DELETE FROM person WHERE name=${name} and surname=${surname} AND student = 0</pre>
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
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.addWhereArgs((name==null?"":name));
    _contentValues.addWhereArgs((surname==null?"":surname));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (deleteAllBeansJQLPreparedStatement1==null) {
      StringBuilder _sqlBuilder=getSQLStringBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" name=? and surname=? AND student = 0";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="DELETE FROM person WHERE name=? and surname=? AND student = 0";
      deleteAllBeansJQLPreparedStatement1 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    // log section BEGIN
    if (this.dataSource.logEnabled) {

      // display log
      Logger.info("DELETE FROM person WHERE name=? and surname=? AND student = 0");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, deleteAllBeansJQLPreparedStatement1, _contentValues);
    return result!=0;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person WHERE surname=${surname} and student = (select student from person where name=${name})</pre>
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
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.addWhereArgs((surname==null?"":surname));
    _contentValues.addWhereArgs((name==null?"":name));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (deleteFromSelectAllBeansJQLPreparedStatement2==null) {
      StringBuilder _sqlBuilder=getSQLStringBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" surname=? and student = (select student from person where name=?)";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="DELETE FROM person WHERE surname=? and student = (select student from person where name=?)";
      deleteFromSelectAllBeansJQLPreparedStatement2 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    // log section BEGIN
    if (this.dataSource.logEnabled) {

      // display log
      Logger.info("DELETE FROM person WHERE surname=? and student = (select student from person where name=?)");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, deleteFromSelectAllBeansJQLPreparedStatement2, _contentValues);
  }

  /**
   * <h1>Content provider URI (DELETE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/a/[*]/[*]</pre>
   *
   * <h2>JQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM Person WHERE surname=${surname} and student = (select student from Person where name=${name})</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM person WHERE surname=${surname} and student = (select student from person where name=${name})</pre>
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
  int deleteFromSelectAllBeansJQL1(Uri uri, String selection, String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValues();
    Logger.info("Execute DELETE for URI %s", uri.toString());
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" surname=? and student = (select student from person where name=?)";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // Add parameter surname at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    // Add parameter name at path segment 3
    _contentValues.addWhereArgs(uri.getPathSegments().get(3));
    // log section BEGIN
    if (this.dataSource.logEnabled) {

      // display log
      Logger.info("DELETE FROM person WHERE surname=? and student = (select student from person where name=?)");

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
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (deleteRawPreparedStatement3==null) {
      StringBuilder _sqlBuilder=getSQLStringBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" id=?";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="DELETE FROM person WHERE id=?";
      deleteRawPreparedStatement3 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    // log section BEGIN
    if (this.dataSource.logEnabled) {

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
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, deleteRawPreparedStatement3, _contentValues);
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
  int deleteRaw2(Uri uri, String selection, String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValues();
    Logger.info("Execute DELETE for URI %s", uri.toString());
    StringBuilder _sqlBuilder=getSQLStringBuilder();
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
    if (this.dataSource.logEnabled) {

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
   * <pre>DELETE FROM person WHERE id=${id} AND #{DYNAMIC_WHERE}</pre>
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
    StringBuilder _sqlBuilder=getSQLStringBuilder();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?"+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // generate sql
    String _sql=String.format("DELETE FROM person WHERE id=?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));
    // log section BEGIN
    if (this.dataSource.logEnabled) {

      // display log
      Logger.info("DELETE FROM person WHERE id=?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, _sql, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (DELETE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/single2/#</pre>
   *
   * <h2>JQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM Person WHERE id=${id} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM person WHERE id=${id} AND #{DYNAMIC_WHERE}</pre>
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
  int deleteRawDynamic3(Uri uri, String selection, String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValues();
    Logger.info("Execute DELETE for URI %s", uri.toString());
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=selection;
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?"+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // Add parameter id at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    // log section BEGIN
    if (this.dataSource.logEnabled) {

      // display log
      Logger.info("DELETE FROM person WHERE id=?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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
   * <pre>DELETE FROM person WHERE id=${id} AND #{DYNAMIC_WHERE}</pre>
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
    StringBuilder _sqlBuilder=getSQLStringBuilder();

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
    String _sql=String.format("DELETE FROM person WHERE id=?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));
    // log section BEGIN
    if (this.dataSource.logEnabled) {

      // display log
      Logger.info("DELETE FROM person WHERE id=?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, _sql, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (DELETE operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#/moreAndMore</pre>
   *
   * <h2>JQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM Person WHERE id=${id} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM person WHERE id=${id} AND #{DYNAMIC_WHERE}</pre>
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
  int deleteBeanDynamicWithArgs4(Uri uri, String selection, String[] selectionArgs) {
    KriptonContentValues _contentValues=contentValues();
    Logger.info("Execute DELETE for URI %s", uri.toString());
    StringBuilder _sqlBuilder=getSQLStringBuilder();
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
    // log section BEGIN
    if (this.dataSource.logEnabled) {

      // display log
      Logger.info("DELETE FROM person WHERE id=?%s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

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

  public void clearCompiledStatements() {
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
    if (deleteRawPreparedStatement3!=null) {
      deleteRawPreparedStatement3.close();
      deleteRawPreparedStatement3=null;
    }
  }
}
