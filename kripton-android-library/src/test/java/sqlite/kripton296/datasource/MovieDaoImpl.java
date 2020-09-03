package sqlite.kripton296.datasource;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.abubusoft.kripton.android.LiveDataHandler;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseHelper;
import com.abubusoft.kripton.androidx.livedata.KriptonXLiveDataHandlerImpl;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import sqlite.kripton296.model.Movie;
import sqlite.kripton296.model.MovieWithDirector;

/**
 * <p>
 * DAO implementation for entity <code>Movie</code>, based on interface <code>MovieDao</code>
 * </p>
 *
 *  @see Movie
 *  @see MovieDao
 *  @see sqlite.kripton296.model.MovieTable
 */
public class MovieDaoImpl extends Dao implements MovieDao {
  /**
   * SQL definition for method findMovieByTitle
   */
  private static final String FIND_MOVIE_BY_TITLE_SQL4 = "SELECT id, director_id, title FROM movie WHERE title = ? LIMIT 1";

  private static SupportSQLiteStatement insertPreparedStatement0;

  private static SupportSQLiteStatement insertPreparedStatement1;

  private static SupportSQLiteStatement updatePreparedStatement2;

  private static SupportSQLiteStatement deleteAllPreparedStatement3;

  /**
   * SQL definition for method getAllMovies
   */
  private static final String GET_ALL_MOVIES_SQL5 = "select movie.title title, director.full_name full_name from movie, director where director.did=movie.director_id order By title ASC";

  static Collection<WeakReference<LiveDataHandler>> liveDatas = new CopyOnWriteArraySet<WeakReference<LiveDataHandler>>();

  public MovieDaoImpl(BindMovieDaoFactory daoFactory) {
    super(daoFactory.getContext());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, director_id, title FROM movie WHERE title = :title LIMIT 1</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Movie}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>director_id</dt><dd>is associated to bean's property <strong>directorId</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:title</dt><dd>is binded to method's parameter <strong>title</strong></dd>
   * </dl>
   *
   * @param title
   * 	is binded to <code>:title</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Movie findMovieByTitle(String title) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_MOVIE_BY_TITLE_SQL4;
    // add where arguments
    _contentValues.addWhereArgs((title==null?"":title));
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
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Movie resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("director_id");
        int index2=_cursor.getColumnIndex("title");

        resultBean=new Movie();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.directorId=_cursor.getLong(index1); }
        if (!_cursor.isNull(index2)) { resultBean.title=_cursor.getString(index2); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT OR IGNORE INTO movie (director_id, title) VALUES (:directorId, :title)</pre>
   *
   * <p><code>movies.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>director_id</dt><dd>is mapped to <strong>:movies.directorId</strong></dd>
   * 	<dt>title</dt><dd>is mapped to <strong>:movies.title</strong></dd>
   * </dl>
   *
   * @param movies
   * 	is mapped to parameter <strong>movies</strong>
   *
   */
  @Override
  public void insert(List<Movie> movies) {
    // Specialized Insert - InsertType - BEGIN
    for (Movie __bean: movies) {
      if (insertPreparedStatement0==null) {
        // generate static SQL for statement
        String _sql="INSERT OR IGNORE INTO movie (director_id, title) VALUES (?, ?)";
        insertPreparedStatement0 = KriptonDatabaseHelper.compile(_context, _sql);
      }
      KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
      _contentValues.put("director_id", __bean.directorId);
      _contentValues.put("title", __bean.title);

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
        Logger.info("INSERT OR IGNORE INTO movie (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
      long result = KriptonDatabaseHelper.insert(insertPreparedStatement0, _contentValues);
      // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
      __bean.id=result;
      // support for livedata
      registryEvent(result>0?1:0);
    }
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT OR IGNORE INTO movie (title, director_id) VALUES (:title, :directorId)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>title</dt><dd>is binded to query's parameter <strong>:title</strong> and method's parameter <strong>title</strong></dd>
   * 	<dt>directorId</dt><dd>is binded to query's parameter <strong>:directorId</strong> and method's parameter <strong>directorId</strong></dd>
   * </dl>
   *
   * @param title
   * 	is binded to column value <strong>title</strong>
   * @param directorId
   * 	is binded to column value <strong>director_id</strong>
   *
   */
  @Override
  public void insert(String title, long directorId) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="INSERT OR IGNORE INTO movie (title, director_id) VALUES (?, ?)";
      insertPreparedStatement1 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement1);

    _contentValues.put("title", title);
    _contentValues.put("director_id", directorId);

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
      Logger.info("INSERT OR IGNORE INTO movie (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insertPreparedStatement1, _contentValues);
    // support for livedata
    registryEvent(result>0?1:0);
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE OR IGNORE movie SET director_id=:directorId, title=:title</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>director_id</dt><dd>is mapped to <strong>:director.directorId</strong></dd>
   * 	<dt>title</dt><dd>is mapped to <strong>:director.title</strong></dd>
   * </dl>
   *
   * @param director
   * 	is used as <code>:director</code>
   */
  @Override
  public void update(Movie director) {
    if (updatePreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="UPDATE OR IGNORE movie SET director_id=?, title=?";
      updatePreparedStatement2 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updatePreparedStatement2);
    _contentValues.put("director_id", director.directorId);
    _contentValues.put("title", director.title);


    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE OR IGNORE movie SET director_id=:director_id, title=:title");

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
    int result = KriptonDatabaseHelper.updateDelete(updatePreparedStatement2, _contentValues);
    // support for livedata
    registryEvent(result);
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM movie</pre>
   *
   * <p>No where parameters were found.</p>
   *
   */
  @Override
  public void deleteAll() {
    if (deleteAllPreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM movie";
      deleteAllPreparedStatement3 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteAllPreparedStatement3);

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM movie");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(deleteAllPreparedStatement3, _contentValues);
    // support for livedata
    registryEvent(result);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>select movie.title title, director.full_name full_name from movie, director where director.did=movie.director_id order By title ASC</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link MovieWithDirector}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * 	<dt>full_name</dt><dd>is associated to bean's property <strong>fullName</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  protected List<MovieWithDirector> getAllMoviesForLiveData() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=GET_ALL_MOVIES_SQL5;
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
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<MovieWithDirector> resultList=new ArrayList<MovieWithDirector>(_cursor.getCount());
      MovieWithDirector resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("title");
        int index1=_cursor.getColumnIndex("full_name");

        do
         {
          resultBean=new MovieWithDirector();

          if (!_cursor.isNull(index0)) { resultBean.title=_cursor.getString(index0); }
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
   * <pre>select movie.title title, director.full_name full_name from movie, director where director.did=movie.director_id order By title ASC</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link MovieWithDirector}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * 	<dt>full_name</dt><dd>is associated to bean's property <strong>fullName</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public LiveData<List<MovieWithDirector>> getAllMovies() {
    // common part generation - BEGIN
    // common part generation - END
    final KriptonXLiveDataHandlerImpl<List<MovieWithDirector>> builder=new KriptonXLiveDataHandlerImpl<List<MovieWithDirector>>() {
      @Override
      protected List<MovieWithDirector> compute() {
        return BindMovieDataSource.getInstance().executeBatch(new BindMovieDataSource.Batch<List<MovieWithDirector>>() {
          @Override
          public List<MovieWithDirector> onExecute(BindMovieDaoFactory daoFactory) {
            return daoFactory.getMovieDao().getAllMoviesForLiveData();
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
      _context.registrySQLEvent(BindMovieDataSource.MOVIE_DAO_UID);
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
    try {
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
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
