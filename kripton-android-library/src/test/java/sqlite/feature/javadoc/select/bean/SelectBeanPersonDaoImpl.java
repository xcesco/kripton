package sqlite.feature.javadoc.select.bean;

import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.ArrayList;
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
public class SelectBeanPersonDaoImpl extends Dao implements SelectBeanPersonDao {
  private static final String SELECT_ALL_BEANS_SQL1 = "SELECT id, person_name, person_surname, student FROM person";

  private static final Set<String> selectAllBeans0ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "id", "person_name", "person_surname", "student");

  private static final String SELECT_ALL_BEANS_COUNT_SQL2 = "SELECT count(*) FROM person WHERE id=?";

  private static final Set<String> selectAllBeansCount1ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "count(*)");

  private static final String SELECT_ONE_BEAN_SQL3 = "SELECT id, person_name, person_surname, student FROM person WHERE id=?";

  private static final Set<String> selectOneBean2ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "id", "person_name", "person_surname", "student");

  private static final Set<String> selectOneBeanWithDynamic3ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "person_name");

  private static final Set<String> selectOneBeanWithDynamicAndArgs4ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "id", "person_name", "person_surname", "student");

  private static final Set<String> selectOneBeanWithDynamicOrder5ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "id", "person_name", "person_surname", "student");

  private static final Set<String> selectOneBeanWithDynamicOrderAndListener6ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "id", "person_name", "person_surname", "student");

  private static final String SELECT_WITH_J_Q_L_SQL4 = "select * from person where id=?";

  private static final Set<String> selectWithJQL7ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "id", "person_name", "person_surname", "student");

  private static final String SELECT_WITH_J_Q_L_AND_INNER_S_Q_L_SQL5 = "select * from person where id=? and id in (select id from person)";

  private static final Set<String> selectWithJQLAndInnerSQL8ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "id", "person_name", "person_surname", "student");

  public SelectBeanPersonDaoImpl(BindSelectBeanPersonDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, person_name, person_surname, student FROM person</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>person_name</dt><dd>is associated to bean's property <strong>personName</strong></dd>
   * 	<dt>person_surname</dt><dd>is associated to bean's property <strong>personSurname</strong></dd>
   * 	<dt>student</dt><dd>is associated to bean's property <strong>student</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectAllBeans() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_BEANS_SQL1;
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section for select END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Person> resultList=new ArrayList<Person>(_cursor.getCount());
      Person resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("person_name");
        int index2=_cursor.getColumnIndex("person_surname");
        int index3=_cursor.getColumnIndex("student");

        do
         {
          resultBean=new Person();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.setPersonName(_cursor.getString(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.setPersonSurname(_cursor.getString(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.setStudent(_cursor.getInt(index3)==0?false:true); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, personName, personSurname, student FROM Person</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, person_name, person_surname, student FROM person</pre>
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
  Cursor selectAllBeans0ForContentProvider(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
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
        if (!selectAllBeans0ForContentProviderColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectAllBeans0ForContentProviderColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());

    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().rawQuery(_sql, _contentValues.whereArgsAsArray());
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT count(*) FROM person WHERE id=${love.id}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>count(*)</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:love.id</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:love</code>
   * @return single value extracted by query.
   */
  @Override
  public int selectAllBeansCount(Person bean) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_BEANS_COUNT_SQL2;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(bean.id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section for select END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectScalarHelper - BEGIN
      int result=0;

      if (_cursor.moveToFirst()) {

        if (_cursor.isNull(0)) { return 0; }
        result=_cursor.getInt(0);
      }
      return result;
    }
    // Specialized part - SelectScalarHelper - END
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
   * <li><strong>:love.id</strong> at path segment 2</li>
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
  Cursor selectAllBeansCount1ForContentProvider(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("SELECT %s FROM person ");

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectAllBeansCount1ForContentProviderColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/a/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectAllBeansCount1ForContentProviderColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter love.id at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());

    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().rawQuery(_sql, _contentValues.whereArgsAsArray());
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, person_name, person_surname, student FROM person WHERE id=${bean.id}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>person_name</dt><dd>is associated to bean's property <strong>personName</strong></dd>
   * 	<dt>person_surname</dt><dd>is associated to bean's property <strong>personSurname</strong></dd>
   * 	<dt>student</dt><dd>is associated to bean's property <strong>student</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is binded to method's parameter <strong>benza.id</strong></dd>
   * </dl>
   *
   * @param benza
   * 	is used as <code>:bean</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Person selectOneBean(Person benza) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_BEAN_SQL3;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(benza.id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section for select END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Person resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("person_name");
        int index2=_cursor.getColumnIndex("person_surname");
        int index3=_cursor.getColumnIndex("student");

        resultBean=new Person();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.setPersonName(_cursor.getString(index1)); }
        if (!_cursor.isNull(index2)) { resultBean.setPersonSurname(_cursor.getString(index2)); }
        if (!_cursor.isNull(index3)) { resultBean.setStudent(_cursor.getInt(index3)==0?false:true); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, personName, personSurname, student FROM Person WHERE id=${bean.id}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, person_name, person_surname, student FROM person WHERE id=${bean.id}</pre>
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
  Cursor selectOneBean2ForContentProvider(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("SELECT %s FROM person ");

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectOneBean2ForContentProviderColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectOneBean2ForContentProviderColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter bean.id at path segment 1
    _contentValues.addWhereArgs(uri.getPathSegments().get(1));
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());

    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().rawQuery(_sql, _contentValues.whereArgsAsArray());
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT person_name FROM person WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>person_name</dt><dd>is associated to bean's property <strong>personname</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>where</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{DYNAMIC_WHERE}</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   * @param where
   * 	is used as <strong>dynamic WHERE statement</strong> and it is formatted by ({@link StringUtils#format})
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Person selectOneBeanWithDynamic(Person bean, String where) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT person_name FROM person");
    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=where;
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=? "+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    String _sql=_sqlBuilder.toString();
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(bean.id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section for select END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Person resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("person_name");

        resultBean=new Person();

        if (!_cursor.isNull(index0)) { resultBean.setPersonName(_cursor.getString(index0)); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamic/#</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT personname FROM Person WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT person_name FROM person WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>:bean.id</strong> at path segment 2</li>
   * </ul>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/dynamic/#"
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  Cursor selectOneBeanWithDynamic3ForContentProvider(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=selection;
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("SELECT %s FROM person ");

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=? "+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectOneBeanWithDynamic3ForContentProviderColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/dynamic/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectOneBeanWithDynamic3ForContentProviderColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter bean.id at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());

    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().rawQuery(_sql, _contentValues.whereArgsAsArray());
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, person_name, person_surname, student FROM person WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>person_name</dt><dd>is associated to bean's property <strong>personName</strong></dd>
   * 	<dt>person_surname</dt><dd>is associated to bean's property <strong>personSurname</strong></dd>
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
   * 	<dt>:bean.id</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   * @param where
   * 	is used as <strong>dynamic WHERE statement</strong> and it is formatted by ({@link StringUtils#format})
   * @param args
   * 	is binded to <code>:args</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Person selectOneBeanWithDynamicAndArgs(Person bean, String where, String[] args) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT id, person_name, person_surname, student FROM person");
    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=where;
    // initialize dynamic where args
    String[] _sqlDynamicWhereArgs=args;
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=? "+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    if (StringUtils.hasText(_sqlDynamicWhere) && _sqlDynamicWhereArgs!=null) {
      for (String _arg: _sqlDynamicWhereArgs) {
        _contentValues.addWhereArgs(_arg);
      }
    }
    String _sql=_sqlBuilder.toString();
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(bean.id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section for select END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Person resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("person_name");
        int index2=_cursor.getColumnIndex("person_surname");
        int index3=_cursor.getColumnIndex("student");

        resultBean=new Person();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.setPersonName(_cursor.getString(index1)); }
        if (!_cursor.isNull(index2)) { resultBean.setPersonSurname(_cursor.getString(index2)); }
        if (!_cursor.isNull(index3)) { resultBean.setStudent(_cursor.getInt(index3)==0?false:true); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamicandArgs/#</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, personName, personSurname, student FROM Person WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, person_name, person_surname, student FROM person WHERE id=${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>:bean.id</strong> at path segment 2</li>
   * </ul>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/dynamicandArgs/#"
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  Cursor selectOneBeanWithDynamicAndArgs4ForContentProvider(Uri uri, String[] projection,
      String selection, String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=selection;
    // initialize dynamic where args
    String[] _sqlDynamicWhereArgs=selectionArgs;
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("SELECT %s FROM person ");

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=? "+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    if (StringUtils.hasText(_sqlDynamicWhere) && _sqlDynamicWhereArgs!=null) {
      for (String _arg: _sqlDynamicWhereArgs) {
        _contentValues.addWhereArgs(_arg);
      }
    }

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectOneBeanWithDynamicAndArgs4ForContentProviderColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/dynamicandArgs/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectOneBeanWithDynamicAndArgs4ForContentProviderColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter bean.id at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());

    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().rawQuery(_sql, _contentValues.whereArgsAsArray());
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, person_name, person_surname, student FROM person WHERE id=${bean.id} ORDER BY #{DYNAMIC_ORDER_BY}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>person_name</dt><dd>is associated to bean's property <strong>personName</strong></dd>
   * 	<dt>person_surname</dt><dd>is associated to bean's property <strong>personSurname</strong></dd>
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
   * 	<dt>:bean.id</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   * @param order
   * 	is used as <strong>dynamic ORDER BY statement</strong> and it is formatted by ({@link StringUtils#format})
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Person selectOneBeanWithDynamicOrder(Person bean, String order) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT id, person_name, person_surname, student FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sortOrder=order;

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // generation order - BEGIN
    String _sqlOrderByStatement=StringUtils.ifNotEmptyAppend(_sortOrder," ORDER BY ");
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

    String _sql=_sqlBuilder.toString();
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(bean.id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section for select END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Person resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("person_name");
        int index2=_cursor.getColumnIndex("person_surname");
        int index3=_cursor.getColumnIndex("student");

        resultBean=new Person();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.setPersonName(_cursor.getString(index1)); }
        if (!_cursor.isNull(index2)) { resultBean.setPersonSurname(_cursor.getString(index2)); }
        if (!_cursor.isNull(index3)) { resultBean.setStudent(_cursor.getInt(index3)==0?false:true); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamicOrder/#</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, personName, personSurname, student FROM Person WHERE id=${bean.id} ORDER BY #{DYNAMIC_ORDER_BY}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, person_name, person_surname, student FROM person WHERE id=${bean.id} ORDER BY #{DYNAMIC_ORDER_BY}</pre>
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
   * @param uri "content://sqlite.feature.javadoc.bean/persons/dynamicOrder/#"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor selectOneBeanWithDynamicOrder5ForContentProvider(Uri uri, String[] projection,
      String selection, String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    String _sortOrder=sortOrder;
    _sqlBuilder.append("SELECT %s FROM person  ");

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
        if (!selectOneBeanWithDynamicOrder5ForContentProviderColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/dynamicOrder/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectOneBeanWithDynamicOrder5ForContentProviderColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter bean.id at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());

    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().rawQuery(_sql, _contentValues.whereArgsAsArray());
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, person_name, person_surname, student FROM person WHERE id=${bean.id} ORDER BY #{DYNAMIC_ORDER_BY}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>person_name</dt><dd>is associated to bean's property <strong>personName</strong></dd>
   * 	<dt>person_surname</dt><dd>is associated to bean's property <strong>personSurname</strong></dd>
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
   * 	<dt>:bean.id</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   * @param order
   * 	is used as <strong>dynamic ORDER BY statement</strong> and it is formatted by ({@link StringUtils#format})
   * @param listener
   * 	is the Person listener
   */
  @Override
  public void selectOneBeanWithDynamicOrderAndListener(Person bean, String order,
      OnReadBeanListener<Person> listener) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT id, person_name, person_surname, student FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sortOrder=order;

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // generation order - BEGIN
    String _sqlOrderByStatement=StringUtils.ifNotEmptyAppend(_sortOrder," ORDER BY ");
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

    String _sql=_sqlBuilder.toString();
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(bean.id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section for select END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListenerHelper - BEGIN
      Person resultBean=new Person();
      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("person_name");
        int index2=_cursor.getColumnIndex("person_surname");
        int index3=_cursor.getColumnIndex("student");

        int rowCount=_cursor.getCount();
        do
         {
          // reset mapping
          // id does not need reset (it will be taken from db)
          resultBean.setPersonName(null);
          resultBean.setPersonSurname(null);
          resultBean.setStudent(false);

          // generate mapping
          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.setPersonName(_cursor.getString(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.setPersonSurname(_cursor.getString(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.setStudent(_cursor.getInt(index3)==0?false:true); }

          listener.onRead(resultBean, _cursor.getPosition(), rowCount);
        } while (_cursor.moveToNext());
      }
    }
    // Specialized part - SelectBeanListenerHelper - END
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamicOrderAndLis/#</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, personName, personSurname, student FROM Person WHERE id=${bean.id} ORDER BY #{DYNAMIC_ORDER_BY}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, person_name, person_surname, student FROM person WHERE id=${bean.id} ORDER BY #{DYNAMIC_ORDER_BY}</pre>
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
   * @param uri "content://sqlite.feature.javadoc.bean/persons/dynamicOrderAndLis/#"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor selectOneBeanWithDynamicOrderAndListener6ForContentProvider(Uri uri, String[] projection,
      String selection, String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    String _sortOrder=sortOrder;
    _sqlBuilder.append("SELECT %s FROM person  ");

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
        if (!selectOneBeanWithDynamicOrderAndListener6ForContentProviderColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/dynamicOrderAndLis/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectOneBeanWithDynamicOrderAndListener6ForContentProviderColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter bean.id at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());

    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().rawQuery(_sql, _contentValues.whereArgsAsArray());
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>select * from person where id=${bean.id}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>person_name</dt><dd>is associated to bean's property <strong>personName</strong></dd>
   * 	<dt>person_surname</dt><dd>is associated to bean's property <strong>personSurname</strong></dd>
   * 	<dt>student</dt><dd>is associated to bean's property <strong>student</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Person selectWithJQL(Person bean) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_WITH_J_Q_L_SQL4;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(bean.id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section for select END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Person resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("person_name");
        int index2=_cursor.getColumnIndex("person_surname");
        int index3=_cursor.getColumnIndex("student");

        resultBean=new Person();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.setPersonName(_cursor.getString(index1)); }
        if (!_cursor.isNull(index2)) { resultBean.setPersonSurname(_cursor.getString(index2)); }
        if (!_cursor.isNull(index3)) { resultBean.setStudent(_cursor.getInt(index3)==0?false:true); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
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
   * <li><strong>:bean.id</strong> at path segment 2</li>
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
  Cursor selectWithJQL7ForContentProvider(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("select %s from person ");

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectWithJQL7ForContentProviderColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/jql/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectWithJQL7ForContentProviderColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter bean.id at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());

    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().rawQuery(_sql, _contentValues.whereArgsAsArray());
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>select * from person where id=${bean.id} and id in (select id from person)</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>person_name</dt><dd>is associated to bean's property <strong>personName</strong></dd>
   * 	<dt>person_surname</dt><dd>is associated to bean's property <strong>personSurname</strong></dd>
   * 	<dt>student</dt><dd>is associated to bean's property <strong>student</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Person selectWithJQLAndInnerSQL(Person bean) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_WITH_J_Q_L_AND_INNER_S_Q_L_SQL5;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(bean.id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section for select END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Person resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("person_name");
        int index2=_cursor.getColumnIndex("person_surname");
        int index3=_cursor.getColumnIndex("student");

        resultBean=new Person();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.setPersonName(_cursor.getString(index1)); }
        if (!_cursor.isNull(index2)) { resultBean.setPersonSurname(_cursor.getString(index2)); }
        if (!_cursor.isNull(index3)) { resultBean.setStudent(_cursor.getInt(index3)==0?false:true); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
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
   * <li><strong>:bean.id</strong> at path segment 2</li>
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
  Cursor selectWithJQLAndInnerSQL8ForContentProvider(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("select %s from person ");

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where id=? and id in (select id from person)";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectWithJQLAndInnerSQL8ForContentProviderColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/jqlAndInnserSQL/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectWithJQLAndInnerSQL8ForContentProviderColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter bean.id at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());

    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().rawQuery(_sql, _contentValues.whereArgsAsArray());
    return _result;
  }

  public static void clearCompiledStatements() {
  }
}
