package sqlite.kripton296.datasource;

import android.arch.lifecycle.LiveData;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.LiveDataHandler;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.livedata.KriptonLiveDataHandlerImpl;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import sqlite.kripton296.model.Director;

/**
 * <p>
 * DAO implementation for entity <code>Director</code>, based on interface <code>DirectorDao</code>
 * </p>
 *
 *  @see Director
 *  @see DirectorDao
 *  @see sqlite.kripton296.model.DirectorTable
 */
public class DirectorDaoImpl extends Dao implements DirectorDao {
  /**
   * SQL definition for method findDirectorById
   */
  private static final String FIND_DIRECTOR_BY_ID_SQL1 = "SELECT did, full_name FROM director WHERE did = ? LIMIT 1";

  /**
   * SQL definition for method findDirectorByName
   */
  private static final String FIND_DIRECTOR_BY_NAME_SQL2 = "SELECT did, full_name FROM director WHERE full_name = ? LIMIT 1";

  private static SQLiteStatement insertPreparedStatement0;

  private static SQLiteStatement insertPreparedStatement1;

  private static SQLiteStatement updatePreparedStatement2;

  private static SQLiteStatement deleteAllPreparedStatement3;

  /**
   * SQL definition for method getAllDirectors
   */
  private static final String GET_ALL_DIRECTORS_SQL3 = "SELECT did, full_name FROM director ORDER BY full_name ASC";

  static Collection<WeakReference<LiveDataHandler>> liveDatas = new CopyOnWriteArraySet<WeakReference<LiveDataHandler>>();

  public DirectorDaoImpl(BindMovieDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT did, full_name FROM director WHERE did = :id LIMIT 1</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Director}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>did</dt><dd>is associated to bean's property <strong>did</strong></dd>
   * 	<dt>full_name</dt><dd>is associated to bean's property <strong>fullName</strong></dd>
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
  public Director findDirectorById(long id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_DIRECTOR_BY_ID_SQL1;
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

      Director resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("did");
        int index1=_cursor.getColumnIndex("full_name");

        resultBean=new Director();

        resultBean.did=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.fullName=_cursor.getString(index1); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT did, full_name FROM director WHERE full_name = :fullName LIMIT 1</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Director}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>did</dt><dd>is associated to bean's property <strong>did</strong></dd>
   * 	<dt>full_name</dt><dd>is associated to bean's property <strong>fullName</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:fullName</dt><dd>is binded to method's parameter <strong>fullName</strong></dd>
   * </dl>
   *
   * @param fullName
   * 	is binded to <code>:fullName</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Director findDirectorByName(String fullName) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_DIRECTOR_BY_NAME_SQL2;
    // add where arguments
    _contentValues.addWhereArgs((fullName==null?"":fullName));
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

      Director resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("did");
        int index1=_cursor.getColumnIndex("full_name");

        resultBean=new Director();

        resultBean.did=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.fullName=_cursor.getString(index1); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT OR IGNORE INTO director (full_name) VALUES (:fullName)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>fullName</dt><dd>is binded to query's parameter <strong>:fullName</strong> and method's parameter <strong>fullName</strong></dd>
   * </dl>
   *
   * @param fullName
   * 	is binded to column value <strong>full_name</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(String fullName) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT OR IGNORE INTO director (full_name) VALUES (?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);

    _contentValues.put("full_name", fullName);

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
      Logger.info("INSERT OR IGNORE INTO director (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    // support for livedata
    registryEvent(result>0?1:0);
    return result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT OR IGNORE INTO director (full_name) VALUES (:fullName)</pre>
   *
   * <p><code>directors.did</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>full_name</dt><dd>is mapped to <strong>:directors.fullName</strong></dd>
   * </dl>
   *
   * @param directors
   * 	is mapped to parameter <strong>directors</strong>
   *
   */
  @Override
  public void insert(List<Director> directors) {
    // Specialized Insert - InsertType - BEGIN
    for (Director __bean: directors) {
      if (insertPreparedStatement1==null) {
        // generate static SQL for statement
        String _sql="INSERT OR IGNORE INTO director (full_name) VALUES (?)";
        insertPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
      }
      KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement1);
      _contentValues.put("full_name", __bean.fullName);

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
        Logger.info("INSERT OR IGNORE INTO director (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
      long result = KriptonDatabaseWrapper.insert(insertPreparedStatement1, _contentValues);
      // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
      __bean.did=result;
      // support for livedata
      registryEvent(result>0?1:0);
    }
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE OR IGNORE director SET full_name=:fullName</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>full_name</dt><dd>is mapped to <strong>:director.fullName</strong></dd>
   * </dl>
   *
   * @param director
   * 	is used as <code>:director</code>
   */
  @Override
  public void update(Director director) {
    if (updatePreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="UPDATE OR IGNORE director SET full_name=?";
      updatePreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updatePreparedStatement2);
    _contentValues.put("full_name", director.fullName);


    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE OR IGNORE director SET full_name=:full_name");

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
    int result = KriptonDatabaseWrapper.updateDelete(updatePreparedStatement2, _contentValues);
    // support for livedata
    registryEvent(result);
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM director</pre>
   *
   * <p>No where parameters were found.</p>
   *
   */
  @Override
  public void deleteAll() {
    if (deleteAllPreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM director";
      deleteAllPreparedStatement3 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteAllPreparedStatement3);

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM director");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteAllPreparedStatement3, _contentValues);
    // support for livedata
    registryEvent(result);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT did, full_name FROM director ORDER BY full_name ASC</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Director}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>did</dt><dd>is associated to bean's property <strong>did</strong></dd>
   * 	<dt>full_name</dt><dd>is associated to bean's property <strong>fullName</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  protected List<Director> getAllDirectorsForLiveData() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=GET_ALL_DIRECTORS_SQL3;
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

      ArrayList<Director> resultList=new ArrayList<Director>(_cursor.getCount());
      Director resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("did");
        int index1=_cursor.getColumnIndex("full_name");

        do
         {
          resultBean=new Director();

          resultBean.did=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.fullName=_cursor.getString(index1); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Live data</h2>
   * <p>This method open a connection internally.</p>
   *
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT did, full_name FROM director ORDER BY full_name ASC</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Director}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>did</dt><dd>is associated to bean's property <strong>did</strong></dd>
   * 	<dt>full_name</dt><dd>is associated to bean's property <strong>fullName</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public LiveData<List<Director>> getAllDirectors() {
    // common part generation - BEGIN
    // common part generation - END
    final KriptonLiveDataHandlerImpl<List<Director>> builder=new KriptonLiveDataHandlerImpl<List<Director>>() {
      @Override
      protected List<Director> compute() {
        return BindMovieDataSource.getInstance().executeBatch(new BindMovieDataSource.Batch<List<Director>>() {
          @Override
          public List<Director> onExecute(BindMovieDaoFactory daoFactory) {
            return daoFactory.getDirectorDao().getAllDirectorsForLiveData();
          }
        });
      }
    };
    registryLiveData(builder);
    return builder.getLiveData();
  }

  protected void registryEvent(int affectedRows) {
    if (affectedRows==0) {
      return;
    }
    if (_context.isInSession()) {
      _context.registrySQLEvent(BindMovieDataSource.DIRECTOR_DAO_UID);
    } else {
      invalidateLiveData();
    }
  }

  /**
   * <p>Allows to registry change on this DAO in a transaction, in an batch operation or in a standalone operation.</p>
   *
   */
  public void registryChange() {
    registryEvent(1);
  }

  protected void registryLiveData(LiveDataHandler value) {
    liveDatas.add(new WeakReference<LiveDataHandler>(value));
  }

  /**
   * <p>Invalidate livedata.</p>
   *
   */
  public void invalidateLiveData() {
    for (WeakReference<LiveDataHandler> item: liveDatas) {
      if (item.get()!=null) {
        item.get().invalidate();
      }
    }
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
    if (insertPreparedStatement1!=null) {
      insertPreparedStatement1.close();
      insertPreparedStatement1=null;
    }
    if (updatePreparedStatement2!=null) {
      updatePreparedStatement2.close();
      updatePreparedStatement2=null;
    }
    if (deleteAllPreparedStatement3!=null) {
      deleteAllPreparedStatement3.close();
      deleteAllPreparedStatement3=null;
    }
  }
}
