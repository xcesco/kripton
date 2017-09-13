package sqlite.feature.javadoc.select.bean;

import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import sqlite.feature.javadoc.Person;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>SelectBeanPersonDao</code>
 * </p>
 *
 *  @see Person
 *  @see SelectBeanPersonDao
 *  @see sqlite.feature.javadoc.PersonTable
 */
public class SelectBeanPersonDaoImpl extends AbstractDao implements SelectBeanPersonDao {
  private static final Set<String> selectAllBeans0ColumnSet = CollectionUtils.asSet(String.class, "id", "name", "surname", "student");

  private static final Set<String> selectAllBeansCount1ColumnSet = CollectionUtils.asSet(String.class, "count(*)");

  private static final Set<String> selectOneBean2ColumnSet = CollectionUtils.asSet(String.class, "id", "name", "surname", "student");

  private static final Set<String> selectOneBeanWithDynamic3ColumnSet = CollectionUtils.asSet(String.class, "name");

  private static final Set<String> selectOneBeanWithDynamicAndArgs4ColumnSet = CollectionUtils.asSet(String.class, "id", "name", "surname", "student");

  private static final Set<String> selectOneBeanWithDynamicOrder5ColumnSet = CollectionUtils.asSet(String.class, "id", "name", "surname", "student");

  private static final Set<String> selectOneBeanWithDynamicOrderAndListener6ColumnSet = CollectionUtils.asSet(String.class, "id", "name", "surname", "student");

  private static final Set<String> selectWithJQL7ColumnSet = CollectionUtils.asSet(String.class, "id", "name", "surname", "student");

  private static final Set<String> selectWithJQLAndInnerSQL8ColumnSet = CollectionUtils.asSet(String.class, "id", "name", "surname", "student");

  public SelectBeanPersonDaoImpl(BindSelectBeanPersonDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, student FROM person</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>student</dt><dd>is associated to bean's property <strong>student</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectAllBeans() {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, name, surname, student FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    String _sqlWhereStatement="";

    // build where condition
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Person> resultList=new LinkedList<Person>();
      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("surname");
        int index3=cursor.getColumnIndex("student");

        do
         {
          resultBean=new Person();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.setName(cursor.getString(index1)); }
          if (!cursor.isNull(index2)) { resultBean.setSurname(cursor.getString(index2)); }
          if (!cursor.isNull(index3)) { resultBean.setStudent(cursor.getInt(index3)==0?false:true); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, name, surname, student FROM Person</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, name, surname, student FROM person</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor selectAllBeans0(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("SELECT %s FROM person");
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    String _sqlWhereStatement="";

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectAllBeans0ColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectAllBeans0ColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }

    // manage log
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().rawQuery(_sql, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT count(*) FROM person WHERE id=${love.id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>count(*)</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${love.id}</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${love}
   * @return single value extracted by query.
   */
  @Override
  public int selectAllBeansCount(Person bean) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT count(*) FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(bean.id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());
      int result=0;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0; }
        result=cursor.getInt(0);
      }
      return result;
    }
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/a/#</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT count(*) FROM Person WHERE id=${love.id}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT count(*) FROM person WHERE id=${love.id}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${love.id}</strong> at path segment 2</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/a/#"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor selectAllBeansCount1(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("SELECT %s FROM person ");
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectAllBeansCount1ColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/a/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectAllBeansCount1ColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter love.id at path segment 2
    _sqlWhereParams.add(uri.getPathSegments().get(2));

    // manage log
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().rawQuery(_sql, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, student FROM person WHERE id=${bean.id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>student</dt><dd>is associated to bean's property <strong>student</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is binded to method's parameter <strong>benza.id</strong></dd>
   * </dl>
   *
   * @param benza
   * 	is used as ${bean}
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Person selectOneBean(Person benza) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, name, surname, student FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(benza.id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("surname");
        int index3=cursor.getColumnIndex("student");

        resultBean=new Person();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.setName(cursor.getString(index1)); }
        if (!cursor.isNull(index2)) { resultBean.setSurname(cursor.getString(index2)); }
        if (!cursor.isNull(index3)) { resultBean.setStudent(cursor.getInt(index3)==0?false:true); }

      }
      return resultBean;
    }
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, name, surname, student FROM Person WHERE id=${bean.id}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, name, surname, student FROM person WHERE id=${bean.id}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${bean.id}</strong> at path segment 1</li>
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
  Cursor selectOneBean2(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("SELECT %s FROM person ");
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectOneBean2ColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectOneBean2ColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter bean.id at path segment 1
    _sqlWhereParams.add(uri.getPathSegments().get(1));

    // manage log
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().rawQuery(_sql, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT name FROM person WHERE id=${bean.id} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>where</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{DYNAMIC_WHERE}</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @param where
   * 	is used as <strong>dynamic WHERE statement</strong> and it is formatted by ({@link StringUtils#format})
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Person selectOneBeanWithDynamic(Person bean, String where) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT name FROM person");
    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=where;
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?"+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(bean.id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("name");

        resultBean=new Person();

        if (!cursor.isNull(index0)) { resultBean.setName(cursor.getString(index0)); }

      }
      return resultBean;
    }
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamic/#</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT name FROM Person WHERE id=${bean.id} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT name FROM person WHERE id=${bean.id} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${bean.id}</strong> at path segment 2</li>
   * </ul>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/dynamic/#"
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  Cursor selectOneBeanWithDynamic3(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=selection;
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("SELECT %s FROM person ");
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?"+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectOneBeanWithDynamic3ColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/dynamic/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectOneBeanWithDynamic3ColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter bean.id at path segment 2
    _sqlWhereParams.add(uri.getPathSegments().get(2));

    // manage log
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().rawQuery(_sql, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, student FROM person WHERE id=${bean.id} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>student</dt><dd>is associated to bean's property <strong>student</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>where</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{DYNAMIC_WHERE}</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @param where
   * 	is used as <strong>dynamic WHERE statement</strong> and it is formatted by ({@link StringUtils#format})
   * @param args
   * 	is binded to <code>${args}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Person selectOneBeanWithDynamicAndArgs(Person bean, String where, String[] args) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, name, surname, student FROM person");
    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=where;
    // initialize dynamic where args
    String[] _sqlDynamicWhereArgs=args;
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?"+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    if (StringUtils.hasText(_sqlDynamicWhere) && _sqlDynamicWhereArgs!=null) {
      for (String _arg: _sqlDynamicWhereArgs) {
        _sqlWhereParams.add(_arg);
      }
    }

    // build where condition
    _sqlWhereParams.add(String.valueOf(bean.id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("surname");
        int index3=cursor.getColumnIndex("student");

        resultBean=new Person();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.setName(cursor.getString(index1)); }
        if (!cursor.isNull(index2)) { resultBean.setSurname(cursor.getString(index2)); }
        if (!cursor.isNull(index3)) { resultBean.setStudent(cursor.getInt(index3)==0?false:true); }

      }
      return resultBean;
    }
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamicandArgs/#</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, name, surname, student FROM Person WHERE id=${bean.id} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, name, surname, student FROM person WHERE id=${bean.id} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${bean.id}</strong> at path segment 2</li>
   * </ul>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/dynamicandArgs/#"
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  Cursor selectOneBeanWithDynamicAndArgs4(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=selection;
    // initialize dynamic where args
    String[] _sqlDynamicWhereArgs=selectionArgs;
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("SELECT %s FROM person ");
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?"+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    if (StringUtils.hasText(_sqlDynamicWhere) && _sqlDynamicWhereArgs!=null) {
      for (String _arg: _sqlDynamicWhereArgs) {
        _sqlWhereParams.add(_arg);
      }
    }

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectOneBeanWithDynamicAndArgs4ColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/dynamicandArgs/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectOneBeanWithDynamicAndArgs4ColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter bean.id at path segment 2
    _sqlWhereParams.add(uri.getPathSegments().get(2));

    // manage log
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().rawQuery(_sql, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, student FROM person WHERE id=${bean.id} ORDER BY #{DYNAMIC_ORDER_BY}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>student</dt><dd>is associated to bean's property <strong>student</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>order</dt>is part of order statement resolved at runtime. In above SQL it is displayed as #{DYNAMIC_ORDER_BY}</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @param order
   * 	is used as <strong>dynamic ORDER BY statement</strong> and it is formatted by ({@link StringUtils#format})
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Person selectOneBeanWithDynamicOrder(Person bean, String order) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, name, surname, student FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sortOrder=order;
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(bean.id));
    // generation order - BEGIN
    String _sqlOrderByStatement=StringUtils.ifNotEmptyAppend(_sortOrder," ORDER BY ");
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("surname");
        int index3=cursor.getColumnIndex("student");

        resultBean=new Person();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.setName(cursor.getString(index1)); }
        if (!cursor.isNull(index2)) { resultBean.setSurname(cursor.getString(index2)); }
        if (!cursor.isNull(index3)) { resultBean.setStudent(cursor.getInt(index3)==0?false:true); }

      }
      return resultBean;
    }
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamicOrder/#</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, name, surname, student FROM Person WHERE id=${bean.id} ORDER BY #{DYNAMIC_ORDER_BY}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, name, surname, student FROM person WHERE id=${bean.id} ORDER BY #{DYNAMIC_ORDER_BY}</pre>
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
   * @param uri "content://sqlite.feature.javadoc.bean/persons/dynamicOrder/#"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor selectOneBeanWithDynamicOrder5(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    String _sortOrder=sortOrder;
    _sqlBuilder.append("SELECT %s FROM person  ");
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
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
        if (!selectOneBeanWithDynamicOrder5ColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/dynamicOrder/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectOneBeanWithDynamicOrder5ColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter bean.id at path segment 2
    _sqlWhereParams.add(uri.getPathSegments().get(2));

    // manage log
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().rawQuery(_sql, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, student FROM person WHERE id=${bean.id} ORDER BY #{DYNAMIC_ORDER_BY}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>student</dt><dd>is associated to bean's property <strong>student</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>order</dt>is part of order statement resolved at runtime. In above SQL it is displayed as #{DYNAMIC_ORDER_BY}</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @param order
   * 	is used as <strong>dynamic ORDER BY statement</strong> and it is formatted by ({@link StringUtils#format})
   * @param listener
   * 	is the Person listener
   */
  @Override
  public void selectOneBeanWithDynamicOrderAndListener(Person bean, String order,
      OnReadBeanListener<Person> listener) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, name, surname, student FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sortOrder=order;
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(bean.id));
    // generation order - BEGIN
    String _sqlOrderByStatement=StringUtils.ifNotEmptyAppend(_sortOrder," ORDER BY ");
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Person resultBean=new Person();
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("surname");
        int index3=cursor.getColumnIndex("student");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          // id does not need reset
          resultBean.setName(null);
          resultBean.setSurname(null);
          resultBean.setStudent(false);

          // generate mapping
          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.setName(cursor.getString(index1)); }
          if (!cursor.isNull(index2)) { resultBean.setSurname(cursor.getString(index2)); }
          if (!cursor.isNull(index3)) { resultBean.setStudent(cursor.getInt(index3)==0?false:true); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamicOrderAndLis/#</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, name, surname, student FROM Person WHERE id=${bean.id} ORDER BY #{DYNAMIC_ORDER_BY}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, name, surname, student FROM person WHERE id=${bean.id} ORDER BY #{DYNAMIC_ORDER_BY}</pre>
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
   * @param uri "content://sqlite.feature.javadoc.bean/persons/dynamicOrderAndLis/#"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor selectOneBeanWithDynamicOrderAndListener6(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    String _sortOrder=sortOrder;
    _sqlBuilder.append("SELECT %s FROM person  ");
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
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
        if (!selectOneBeanWithDynamicOrderAndListener6ColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/dynamicOrderAndLis/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectOneBeanWithDynamicOrderAndListener6ColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter bean.id at path segment 2
    _sqlWhereParams.add(uri.getPathSegments().get(2));

    // manage log
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().rawQuery(_sql, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>select * from person where id=${bean.id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>student</dt><dd>is associated to bean's property <strong>student</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Person selectWithJQL(Person bean) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("select * from person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(bean.id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("surname");
        int index3=cursor.getColumnIndex("student");

        resultBean=new Person();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.setName(cursor.getString(index1)); }
        if (!cursor.isNull(index2)) { resultBean.setSurname(cursor.getString(index2)); }
        if (!cursor.isNull(index3)) { resultBean.setStudent(cursor.getInt(index3)==0?false:true); }

      }
      return resultBean;
    }
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/jql/#</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>select * from Person where id=${bean.id}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>select * from person where id=${bean.id}</pre>
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
   * @param uri "content://sqlite.feature.javadoc.bean/persons/jql/#"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor selectWithJQL7(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("select %s from person ");
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectWithJQL7ColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/jql/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectWithJQL7ColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter bean.id at path segment 2
    _sqlWhereParams.add(uri.getPathSegments().get(2));

    // manage log
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().rawQuery(_sql, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>select * from person where id=${bean.id} and id in (select id from person)</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>student</dt><dd>is associated to bean's property <strong>student</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Person selectWithJQLAndInnerSQL(Person bean) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("select * from person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where id=? and id in (select id from person)";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(bean.id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("surname");
        int index3=cursor.getColumnIndex("student");

        resultBean=new Person();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.setName(cursor.getString(index1)); }
        if (!cursor.isNull(index2)) { resultBean.setSurname(cursor.getString(index2)); }
        if (!cursor.isNull(index3)) { resultBean.setStudent(cursor.getInt(index3)==0?false:true); }

      }
      return resultBean;
    }
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/jqlAndInnserSQL/#</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>select * from Person where id=${bean.id} and id in (select id from Person)</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>select * from person where id=${bean.id} and id in (select id from person)</pre>
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
   * @param uri "content://sqlite.feature.javadoc.bean/persons/jqlAndInnserSQL/#"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor selectWithJQLAndInnerSQL8(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("select %s from person ");
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where id=? and id in (select id from person)";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectWithJQLAndInnerSQL8ColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/jqlAndInnserSQL/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectWithJQLAndInnerSQL8ColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter bean.id at path segment 2
    _sqlWhereParams.add(uri.getPathSegments().get(2));

    // manage log
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().rawQuery(_sql, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return _result;
  }
}
