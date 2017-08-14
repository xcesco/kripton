package sqlite.feature.contentprovider.kripton35.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.ArrayList;
import java.util.Set;
import sqlite.feature.contentprovider.kripton35.entities.City;

/**
 * <p>
 * DAO implementation for entity <code>City</code>, based on interface <code>CityDAO</code>
 * </p>
 *
 *  @see City
 *  @see CityDAO
 *  @see sqlite.feature.contentprovider.kripton35.entities.CityTable
 */
public class CityDAOImpl extends AbstractDao implements CityDAO {
  private static final Set<String> insertBean0ColumnSet = CollectionUtils.asSet(String.class, "name");

  private static final Set<String> selectCityFromPerson1ColumnSet = CollectionUtils.asSet(String.class, "id", "name");

  public CityDAOImpl(BindPersonDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO city (name) VALUES (${bean.name})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insertBean(City bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.name!=null) {
      contentValues.put("name", bean.name);
    } else {
      contentValues.putNull("name");
    }

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO city (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    long result = database().insert("city", null, contentValues);
    bean.id=result;
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.feature.contentprovider.kripton35/cities</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT INTO City (name) VALUES (${bean.name})</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT INTO city (name) VALUES (${bean.name})</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.contentprovider.kripton35/cities"
   * @param contentValues content values
   * @return new row's id
   */
  long insertBean0(Uri uri, ContentValues contentValues) {
    Logger.info("Execute INSERT for URI %s", uri.toString());
    for (String columnName:contentValues.keySet()) {
      if (!insertBean0ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.contentprovider.kripton35/cities', column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "city" ));
      }
    }

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO city (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    long result = database().insert("city", null, contentValues);
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>select * from city where id = (select id from person where id=${personId} )</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${personId}</dt><dd>is binded to method's parameter <strong>personId</strong></dd>
   * </dl>
   *
   * @param personId
   * 	is binded to <code>${personId}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public City selectCityFromPerson(long personId) {
    StringBuilder _sqlBuilder=new StringBuilder();
    _sqlBuilder.append("select * from city");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where id = (select id from person where id=? )";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(personId));
    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
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

      City resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");

        resultBean=new City();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }

      }
      return resultBean;
    }
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.contentprovider.kripton35/cities/person/#</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>select * from City where id = (select id from Person where id=${personId} )</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>select * from city where id = (select id from person where id=${personId} )</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${personId}</strong> at path segment 2</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.contentprovider.kripton35/cities/person/#"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor selectCityFromPerson1(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    StringBuilder _sqlBuilder=new StringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("select %s from city ");
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where id = (select id from person where id=? )";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectCityFromPerson1ColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.contentprovider.kripton35/cities/person/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "city" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectCityFromPerson1ColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter personId at path segment 2
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
