package sqlite.feature.immutable.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * DAO implementation for entity <code>Album</code>, based on interface <code>AlbumDao</code>
 * </p>
 *
 *  @see Album
 *  @see AlbumDao
 *  @see AlbumTable
 */
public class AlbumDaoImpl extends Dao implements AlbumDao {
  private static final String SELECT_BY_ID_SQL3 = "SELECT id, artist_id, name FROM album WHERE id=?";

  private static final Set<String> selectById0ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "id", "artist_id", "name");

  private static final String SELECT_ALL_SQL4 = "SELECT id, artist_id, name FROM album";

  private static final Set<String> selectAll1ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "id", "artist_id", "name");

  private static SQLiteStatement insertPreparedStatement0;

  private static final Set<String> insert2ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "artist_id", "name");

  private static SQLiteStatement updatePreparedStatement1;

  private static final Set<String> update3ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "artist_id", "name");

  private static SQLiteStatement deletePreparedStatement2;

  public AlbumDaoImpl(BindArtistsDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, artist_id, name FROM album WHERE id=${id}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Album}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>artist_id</dt><dd>is associated to bean's property <strong>artistId</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Album selectById(long id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ID_SQL3;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
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

      Album resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      String __name=null;
      long __artistId=0;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("artist_id");
        int index2=_cursor.getColumnIndex("name");

        // reset temporary variable for immutable POJO
        // immutable object: initialize temporary variables for properties
        __id=0;
        __name=null;
        __artistId=0;
        __id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { __artistId=_cursor.getLong(index1); }
        if (!_cursor.isNull(index2)) { __name=_cursor.getString(index2); }

        // define immutable POJO
        // immutable object: inizialize object
        resultBean=new Album(__id,__name,__artistId);
      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://com.abubusoft.kripton.example/albums/#</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, artistId, name FROM Album WHERE id=${id}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, artist_id, name FROM album WHERE id=${id}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>:id</strong> at path segment 1</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://com.abubusoft.kripton.example/albums/#"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor selectById0ForContentProvider(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("SELECT %s FROM album ");

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectById0ForContentProviderColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://com.abubusoft.kripton.example/albums/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "album" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectById0ForContentProviderColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter id at path segment 1
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
   * <pre>SELECT id, artist_id, name FROM album</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Album}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>artist_id</dt><dd>is associated to bean's property <strong>artistId</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection. If result type is List, it will be generated as <strong>immutable list</strong>.
   */
  @Override
  public List<Album> selectAll() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL4;
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

      ArrayList<Album> resultList=new ArrayList<Album>(_cursor.getCount());
      Album resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      String __name=null;
      long __artistId=0;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("artist_id");
        int index2=_cursor.getColumnIndex("name");

        do
         {
          // reset temporary variable for immutable POJO
          // immutable object: initialize temporary variables for properties
          __id=0;
          __name=null;
          __artistId=0;
          __id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { __artistId=_cursor.getLong(index1); }
          if (!_cursor.isNull(index2)) { __name=_cursor.getString(index2); }

          // define immutable POJO
          // immutable object: inizialize object
          resultBean=new Album(__id,__name,__artistId);
          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return (resultList==null ? null : Collections.unmodifiableList(resultList));
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://com.abubusoft.kripton.example/albums</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, artistId, name FROM Album</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, artist_id, name FROM album</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://com.abubusoft.kripton.example/albums"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor selectAll1ForContentProvider(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("SELECT %s FROM album");
    String _sqlWhereStatement="";

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectAll1ForContentProviderColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://com.abubusoft.kripton.example/albums', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "album" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectAll1ForContentProviderColumnSet) {
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
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO album (artist_id, name) VALUES (:artistId, :name)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>artist_id</dt><dd>is mapped to <strong>:bean.artistId</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public Album insert(Album bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO album (artist_id, name) VALUES (?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("artist_id", bean.getArtistId());
    _contentValues.put("name", bean.getName());

    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO album (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    // insert operation
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement0, _contentValues);
    // immutable POJO - create a copy with new id
    // immutable object: initialize temporary variables for properties
    long __id=0;
    String __name=null;
    long __artistId=0;
    // immutable object: initialize temporary variables for properties with entity propertiy values
    __id=bean.getId();
    __name=bean.getName();
    __artistId=bean.getArtistId();
    __id=result;
    // immutable object: inizialize object
    bean=new Album(__id,__name,__artistId);

    return bean;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://com.abubusoft.kripton.example/albums</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT INTO Album (artistId, name) VALUES (:artistId, :name)</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT INTO album (artist_id, name) VALUES (:artistId, :name)</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://com.abubusoft.kripton.example/albums"
   * @param contentValues content values
   * @return new row's id
   */
  long insert2ForContentProvider(Uri uri, ContentValues contentValues) {
    Logger.info("Execute INSERT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    for (String columnName:_contentValues.values().keySet()) {
      if (!insert2ForContentProviderColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://com.abubusoft.kripton.example/albums', column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "album" ));
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
    long result = database().insert("album", null, _contentValues.values());
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE album SET artist_id=:artistId, name=:name WHERE id=${bean.id}</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>artist_id</dt><dd>is mapped to <strong>:bean.artistId</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
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
  public long update(Album bean) {
    if (updatePreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="UPDATE album SET artist_id=?, name=? WHERE id=?";
      updatePreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updatePreparedStatement1);
    _contentValues.put("artist_id", bean.getArtistId());
    _contentValues.put("name", bean.getName());

    _contentValues.addWhereArgs(String.valueOf(bean.getId()));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE album SET artist_id=:artist_id, name=:name WHERE id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(updatePreparedStatement1, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (UPDATE operation):</h1>
   * <pre>content://com.abubusoft.kripton.example/albums/#</pre>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE Album SET artistId=:artistId, name=:name WHERE id=${bean.id}</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE album SET artist_id=:artistId, name=:name WHERE id=${bean.id}</pre>
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
   * @param uri "content://com.abubusoft.kripton.example/albums/#"
   * @param contentValues content values
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  int update3ForContentProvider(Uri uri, ContentValues contentValues, String selection,
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
      if (!update3ForContentProviderColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://com.abubusoft.kripton.example/albums/#', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "album" ));
      }
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE album SET artist_id=:artist_id, name=:name WHERE id=?");

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
    int result = database().update("album", _contentValues.values(), _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM album WHERE id=${bean.id}</pre>
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
  public long delete(Album bean) {
    if (deletePreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM album WHERE id=?";
      deletePreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deletePreparedStatement2);
    _contentValues.addWhereArgs(String.valueOf(bean.getId()));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM album WHERE id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deletePreparedStatement2, _contentValues);
    return result;
  }

  /**
   * <h1>Content provider URI (DELETE operation):</h1>
   * <pre>content://com.abubusoft.kripton.example/albums/#</pre>
   *
   * <h2>JQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM Album WHERE id=${bean.id}</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM album WHERE id=${bean.id}</pre>
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
   * @param uri "content://com.abubusoft.kripton.example/albums/#"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  int delete4ForContentProvider(Uri uri, String selection, String[] selectionArgs) {
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
      Logger.info("DELETE FROM album WHERE id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END

    // execute SQL
    int result = database().delete("album", _sqlWhereStatement, _contentValues.whereArgsAsArray());
    return result;
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
    if (updatePreparedStatement1!=null) {
      updatePreparedStatement1.close();
      updatePreparedStatement1=null;
    }
    if (deletePreparedStatement2!=null) {
      deletePreparedStatement2.close();
      deletePreparedStatement2=null;
    }
  }
}
