package sqlite.feature.contentprovider.base;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * DAO implementation for entity <code>Artist</code>, based on interface <code>ArtistDao</code>
 * </p>
 *
 *  @see Artist
 *  @see ArtistDao
 *  @see ArtistTable
 */
public class ArtistDaoImpl extends AbstractDao implements ArtistDao {
  private static final Set<String> selectById0ColumnSet = CollectionUtils.asSet(String.class, "id", "name");

  public ArtistDaoImpl(BindArtistsDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name FROM artist WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Artist selectById(long id) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, name FROM artist");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(id));
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

      Artist resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");

        resultBean=new Artist();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }

      }
      return resultBean;
    }
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://com.abubusoft.kripton.example/artist/#</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, name FROM Artist WHERE id=${id}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, name FROM artist WHERE id=${id}</pre>
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
   * @param uri "content://com.abubusoft.kripton.example/artist/#"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor selectById0(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("SELECT %s FROM artist ");
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
        if (!selectById0ColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://com.abubusoft.kripton.example/artist/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "artist" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectById0ColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter id at path segment 1
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
   * <pre>SELECT id, name FROM artist</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Artist> selectAll() {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, name FROM artist");
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

      LinkedList<Artist> resultList=new LinkedList<Artist>();
      Artist resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");

        do
         {
          resultBean=new Artist();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO artist (name) VALUES (${name})</pre>
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
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(Artist bean) {
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
    Logger.info("INSERT INTO artist (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    long result = database().insert("artist", null, contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE artist SET name=:name WHERE id=${bean.id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of updated records
   */
  @Override
  public long update(Artist bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.name!=null) {
      contentValues.put("name", bean.name);
    } else {
      contentValues.putNull("name");
    }

    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(bean.id));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("UPDATE artist SET name=:name WHERE id=?");

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

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().update("artist", contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));;
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM artist WHERE id=${bean.id}</pre>
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
  public long delete(Artist bean) {
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(bean.id));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("DELETE FROM artist WHERE id=?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().delete("artist", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return result;
  }
}
