package sqlite.feature.pkstring.rx;

import android.arch.lifecycle.LiveData;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import com.abubusoft.kripton.android.LiveDataHandler;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.livedata.KriptonLiveDataHandlerImpl;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.PagedResult;
import com.abubusoft.kripton.android.sqlite.SQLiteEvent;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import io.reactivex.subjects.PublishSubject;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <p>
 * DAO implementation for entity <code>User</code>, based on interface <code>UserDao</code>
 * </p>
 *
 *  @see User
 *  @see UserDao
 *  @see UserTable
 */
public class UserDaoImpl extends Dao implements UserDao {
  private static SQLiteStatement insertUserPreparedStatement0;

  private static final Set<String> insertUser0ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "userid", "username");

  private static SQLiteStatement insertUserPreparedStatement1;

  private static final Set<String> insertUser1ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "userid");

  private static SQLiteStatement deleteAllUsersPreparedStatement2;

  static Collection<WeakReference<LiveDataHandler>> liveDatas = new CopyOnWriteArraySet<WeakReference<LiveDataHandler>>();

  private static final PublishSubject<SQLiteEvent> subject = PublishSubject.create();

  public UserDaoImpl(BindUserDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT userid, username FROM users WHERE userid=:id LIMIT 1 OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link User}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>userid</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>username</dt><dd>is associated to bean's property <strong>userName</strong></dd>
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
  public User getUser(String id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT userid, username FROM users");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE userid=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // generation limit - BEGIN
    String _sqlLimitStatement=" LIMIT 1";
    _sqlBuilder.append(_sqlLimitStatement);
    // generation limit - END

    String _sql=_sqlBuilder.toString();
    // add where arguments
    _contentValues.addWhereArgs((id==null?"":id));
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

      User resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      String __id=null;
      String __userName=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("userid");
        int index1=_cursor.getColumnIndex("username");

        // reset temporary variable for immutable POJO
        // immutable object: initialize temporary variables for properties
        __id=null;
        __userName=null;
        __id=_cursor.getString(index0);
        if (!_cursor.isNull(index1)) { __userName=_cursor.getString(index1); }

        // define immutable POJO
        // immutable object: inizialize object
        resultBean=new User(__id,__userName);
      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT userid, username FROM users LIMIT 20 OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link User}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>userid</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>username</dt><dd>is associated to bean's property <strong>userName</strong></dd>
   * </dl>
   *
   * @return paginated result.
   */
  protected PagedResult<User> selectPagedForLiveData() {
    final PaginatedResult7 paginatedResult=new PaginatedResult7();
    // common part generation - BEGIN
    // common part generation - END
    return paginatedResult;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT userid, username FROM users LIMIT 20 OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link User}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>userid</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>username</dt><dd>is associated to bean's property <strong>userName</strong></dd>
   * </dl>
   *
   * @param paginatedResult
   * 	handler of paginated result
   * @return result list
   */
  private List<User> selectPaged(PaginatedResult7 paginatedResult) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT userid, username FROM users");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sqlWhereStatement="";
    // generation limit - BEGIN
    String _sqlLimitStatement=" LIMIT "+paginatedResult.getPageSize();
    _sqlBuilder.append(_sqlLimitStatement);
    // generation limit - END

    // generation offset - BEGIN
    String _sqlOffsetStatement=" OFFSET "+paginatedResult.getOffset();
    _sqlBuilder.append(_sqlOffsetStatement);
    // generation offset - END

    String _sql=_sqlBuilder.toString();
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
      // Specialized part II - SelectPaginatedResultHelper - BEGIN

      List<User> resultList=new ArrayList<User>(_cursor.getCount());
      User resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      String __id=null;
      String __userName=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("userid");
        int index1=_cursor.getColumnIndex("username");

        do
         {
          // reset temporary variable for immutable POJO
          // immutable object: initialize temporary variables for properties
          __id=null;
          __userName=null;
          __id=_cursor.getString(index0);
          if (!_cursor.isNull(index1)) { __userName=_cursor.getString(index1); }

          // define immutable POJO
          // immutable object: inizialize object
          resultBean=new User(__id,__userName);
          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return (resultList==null ? null : Collections.unmodifiableList(resultList));
    }
    // Specialized part II - SelectPaginatedResultHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT userid, username FROM users LIMIT 20 OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link User}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>count(*)</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @param paginatedResult
   * 	handler of paginated result
   * @return total row count
   */
  private int selectPagedTotalCount(PaginatedResult7 paginatedResult) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT count(*) FROM users");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sqlWhereStatement="";
    String _sql=_sqlBuilder.toString();
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
      // Specialized part II - SelectPaginatedResultHelper - BEGIN
      int _result=-1;

      if (_cursor.moveToFirst()) {
        _result=_cursor.getInt(0);
      }
      // log section for select BEGIN
      if (_context.isLogEnabled()) {
        // manage log
        Logger.info("Total elements found: ", _result);

        // log for where parameters -- BEGIN
        int _whereParamCounter=0;
        for (String _whereParamItem: _contentValues.whereArgs()) {
          Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
        }
        // log for where parameters -- END
        // log section for select END
      }
      return _result;
    }
    // Specialized part II - SelectPaginatedResultHelper - END
  }

  /**
   * <h2>Live data</h2>
   * <p>This method open a connection internally.</p>
   *
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT userid, username FROM users LIMIT 20 OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link User}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>userid</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>username</dt><dd>is associated to bean's property <strong>userName</strong></dd>
   * </dl>
   *
   * @return paginated result.
   */
  @Override
  public LiveData<PagedResult<User>> selectPaged() {
    // common part generation - BEGIN
    // common part generation - END
    final KriptonLiveDataHandlerImpl<PagedResult<User>> builder=new KriptonLiveDataHandlerImpl<PagedResult<User>>() {
      @Override
      protected PagedResult<User> compute() {
        return BindUserDataSource.getInstance().executeBatch(new BindUserDataSource.Batch<PagedResult<User>>() {
          @Override
          public PagedResult<User> onExecute(BindUserDaoFactory daoFactory) {
            return daoFactory.getUserDao().selectPagedForLiveData();
          }
        });
      }
    };
    registryLiveData(builder);
    return builder.getLiveData();
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT OR REPLACE INTO users (userid, username) VALUES (:user.id, :user.userName)</pre>
   *
   * <p><code>user.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>userid</dt><dd>is mapped to <strong>:user.id</strong></dd>
   * 	<dt>username</dt><dd>is mapped to <strong>:user.userName</strong></dd>
   * </dl>
   *
   * @param user
   * 	is mapped to parameter <strong>user</strong>
   *
   */
  @Override
  public void insertUser(User user) {
    // Specialized Insert - InsertType - BEGIN
    if (insertUserPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT OR REPLACE INTO users (userid, username) VALUES (?, ?)";
      insertUserPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertUserPreparedStatement0);
    _contentValues.put("userid", user.getId());
    _contentValues.put("username", user.getUserName());

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
      Logger.info("INSERT OR REPLACE INTO users (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertUserPreparedStatement0, _contentValues);
    if (result>0) {
      // rx management 
      subject.onNext(SQLiteEvent.createInsertWithUid(user.getId()));
    }
    // support for livedata
    registryEvent(result>0?1:0);
    // Specialized Insert - InsertType - END
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.feature.pkstring.rx/user/insert1</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO User (id, userName) VALUES (:user.id, :user.userName)</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO users (userid, username) VALUES (:user.id, :user.userName)</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.pkstring.rx/user/insert1"
   * @param contentValues content values
   * @return new row's id
   */
  long insertUser0ForContentProvider(Uri uri, ContentValues contentValues) {
    Logger.info("Execute INSERT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    for (String columnName:_contentValues.values().keySet()) {
      if (!insertUser0ForContentProviderColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.pkstring.rx/user/insert1', column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "users" ));
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
    // conflict algorithm REPLACE
    // insert operation
    long result = database().insertWithOnConflict("users", null, _contentValues.values(), 5);
    if (result>0) {
      // rx management 
      subject.onNext(SQLiteEvent.createInsertWithUid(contentValues.getAsString("userid")));
    }
    // support for livedata
    registryEvent(result>0?1:0);
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT OR REPLACE INTO users (userid) VALUES (:id)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>:id</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to column value <strong>userid</strong>
   *
   */
  @Override
  public void insertUser(String id) {
    // Specialized Insert - InsertType - BEGIN
    if (insertUserPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="INSERT OR REPLACE INTO users (userid) VALUES (?)";
      insertUserPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertUserPreparedStatement1);

    _contentValues.put("userid", id);

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
      Logger.info("INSERT OR REPLACE INTO users (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertUserPreparedStatement1, _contentValues);
    if (result>0) {
      // rx management 
      subject.onNext(SQLiteEvent.createInsertWithUid(id));
    }
    // support for livedata
    registryEvent(result>0?1:0);
    // Specialized Insert - InsertType - END
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.feature.pkstring.rx/user/insert2</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO User (id) VALUES (:id)</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO users (userid) VALUES (:id)</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.pkstring.rx/user/insert2"
   * @param contentValues content values
   * @return new row's id
   */
  long insertUser1ForContentProvider(Uri uri, ContentValues contentValues) {
    Logger.info("Execute INSERT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    for (String columnName:_contentValues.values().keySet()) {
      if (!insertUser1ForContentProviderColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.pkstring.rx/user/insert2', column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "users" ));
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
    // conflict algorithm REPLACE
    // insert operation
    long result = database().insertWithOnConflict("users", null, _contentValues.values(), 5);
    if (result>0) {
      // rx management 
      subject.onNext(SQLiteEvent.createInsertWithUid(contentValues.getAsString("userid")));
    }
    // support for livedata
    registryEvent(result>0?1:0);
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM users</pre>
   *
   * <p>No where parameters were found.</p>
   *
   */
  @Override
  public void deleteAllUsers() {
    if (deleteAllUsersPreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM users";
      deleteAllUsersPreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteAllUsersPreparedStatement2);

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM users");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteAllUsersPreparedStatement2, _contentValues);
    if (result>0) {
      // rx management 
      subject.onNext(SQLiteEvent.createDelete(result));
    }
    // support for livedata
    registryEvent(result);
  }

  protected void registryEvent(int affectedRows) {
    if (affectedRows==0) {
      return;
    }
    if (_context.isInSession()) {
      _context.registrySQLEvent(BindUserDataSource.USER_DAO_UID);
    } else {
      invalidateLiveData();
    }
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

  public PublishSubject<SQLiteEvent> getSubject() {
    return subject;
  }

  public static void clearCompiledStatements() {
    if (insertUserPreparedStatement0!=null) {
      insertUserPreparedStatement0.close();
      insertUserPreparedStatement0=null;
    }
    if (insertUserPreparedStatement1!=null) {
      insertUserPreparedStatement1.close();
      insertUserPreparedStatement1=null;
    }
    if (deleteAllUsersPreparedStatement2!=null) {
      deleteAllUsersPreparedStatement2.close();
      deleteAllUsersPreparedStatement2=null;
    }
  }

  public class PaginatedResult7 extends PagedResult<User> {
    PaginatedResult7() {
      this.pageSize=20;
    }

    public List<User> execute() {
      list=UserDaoImpl.this.selectPaged(this);
      return list;
    }

    public List<User> execute(BindUserDaoFactory daoFactory) {
      return daoFactory.getUserDao().selectPaged(this);
    }
  }
}
