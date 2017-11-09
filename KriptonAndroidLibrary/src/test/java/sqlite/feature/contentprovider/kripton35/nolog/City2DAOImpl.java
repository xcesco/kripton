package sqlite.feature.contentprovider.kripton35.nolog;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.Set;
import sqlite.feature.contentprovider.kripton35.entities.City;

/**
 * <p>
 * DAO implementation for entity <code>City</code>, based on interface <code>City2DAO</code>
 * </p>
 *
 *  @see City
 *  @see City2DAO
 *  @see sqlite.feature.contentprovider.kripton35.entities.CityTable
 */
public class City2DAOImpl extends AbstractDao implements City2DAO {
  private static final Set<String> insertBean0ColumnSet = CollectionUtils.asSet(String.class, "name");

  private static final Set<String> selectCityFromPerson1ColumnSet = CollectionUtils.asSet(String.class, "id", "name");

  private SQLiteStatement insertBeanPreparedStatement0;

  public City2DAOImpl(BindPerson2DataSource dataSet) {
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
    KriptonContentValues _contentValues=contentValuesForUpdate();
    if (bean.name!=null) {
      _contentValues.put("name", bean.name);
    } else {
      _contentValues.putNull("name");
    }

    // insert operation
    if (insertBeanPreparedStatement0==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO city (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertBeanPreparedStatement0 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertBeanPreparedStatement0, _contentValues);
    bean.id=result;
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/cities</pre>
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
   * @param uri "content://sqlite.feature.contentprovider.kripton35.nolog/cities"
   * @param contentValues content values
   * @return new row's id
   */
  long insertBean0(Uri uri, ContentValues contentValues) {
    KriptonContentValues _contentValues=contentValues(contentValues);
    for (String columnName:_contentValues.values().keySet()) {
      if (!insertBean0ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.contentprovider.kripton35.nolog/cities', column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "city" ));
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
    long result = database().insert("city", null, _contentValues.values());
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
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("select * from city");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where id = (select id from person where id=? )";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs(String.valueOf(personId));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {

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
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/cities/person/#</pre>
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
   * @param uri "content://sqlite.feature.contentprovider.kripton35.nolog/cities/person/#"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor selectCityFromPerson1(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("select %s from city ");

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
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.contentprovider.kripton35.nolog/cities/person/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "city" ));
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
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());

    // execute query
    Cursor _result = database().rawQuery(_sql, _contentValues.whereArgsAsArray());
    return _result;
  }

  public void clearCompiledStatements() {
    if (insertBeanPreparedStatement0!=null) {
      insertBeanPreparedStatement0.close();
      insertBeanPreparedStatement0=null;
    }
  }
}
