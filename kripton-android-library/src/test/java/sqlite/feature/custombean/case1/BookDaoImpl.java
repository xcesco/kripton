package sqlite.feature.custombean.case1;

import android.arch.lifecycle.LiveData;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.livedata.KriptonComputableLiveData;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.adapters.DateTimeMillisecondsTypeAdapter;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.SQLTypeAdapterUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <p>
 * DAO implementation for entity <code>Book</code>, based on interface <code>BookDao</code>
 * </p>
 *
 *  @see Book
 *  @see BookDao
 *  @see BookTable
 */
public class BookDaoImpl extends Dao implements BookDao {
  private static final String LOAD_BOOK_BY_ID_SQL1 = "SELECT id, title FROM book WHERE id = ?";

  private static final String FIND_BOOKS_BORROWED_BY_NAME_SQL2 = "SELECT * FROM book INNER JOIN loan ON loan.book_id = book.id INNER JOIN user on user.id = loan.user_id WHERE user.name LIKE ?";

  private static final String FIND_BOOKS_BORROWED_BY_NAME_AFTER_SQL3 = "SELECT * FROM book INNER JOIN loan ON loan.book_id = book.id INNER JOIN user on user.id = loan.user_id WHERE user.name LIKE ? AND loan.end_time > ? ";

  private static final String FIND_BOOKS_BORROWED_BY_NAME_SYNC_SQL4 = "SELECT * FROM book INNER JOIN loan ON loan.book_id = book.id INNER JOIN user on user.id = loan.user_id WHERE user.name LIKE ?";

  private static final String FIND_BOOKS_BORROWED_BY_USER_SQL5 = "SELECT * FROM book INNER JOIN loan ON loan.book_id LIKE book.id WHERE loan.user_id LIKE ? ";

  private static final String FIND_BOOKS_BORROWED_BY_USER_AFTER_SQL6 = "SELECT * FROM book INNER JOIN loan ON loan.book_id LIKE book.id WHERE loan.user_id LIKE ? AND loan.end_time > ? ";

  private static final String FIND_BOOKS_BORROWED_BY_USER_SYNC_SQL7 = "SELECT * FROM book INNER JOIN loan ON loan.book_id LIKE book.id WHERE loan.user_id = ? ";

  private static final String FIND_ALL_BOOKS_SQL8 = "SELECT id, title FROM book";

  private static final String FIND_ALL_BOOKS_SYNC_SQL9 = "SELECT id, title FROM book";

  private static SQLiteStatement insertBookPreparedStatement0;

  private static SQLiteStatement updateBookPreparedStatement1;

  private static SQLiteStatement deleteAllPreparedStatement2;

  static Collection<WeakReference<KriptonComputableLiveData<?>>> liveDatas = new CopyOnWriteArraySet<WeakReference<KriptonComputableLiveData<?>>>();

  public BookDaoImpl(BindAppDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, title FROM book WHERE id = :id</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
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
  public Book loadBookById(int id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=LOAD_BOOK_BY_ID_SQL1;
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

      Book resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("title");

        resultBean=new Book();

        resultBean.id=_cursor.getString(index0);
        if (!_cursor.isNull(index1)) { resultBean.title=_cursor.getString(index1); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT * FROM book INNER JOIN loan ON loan.book_id = book.id INNER JOIN user on user.id = loan.user_id WHERE user.name LIKE :userName</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:userName</dt><dd>is binded to method's parameter <strong>userName</strong></dd>
   * </dl>
   *
   * @param userName
   * 	is binded to <code>:userName</code>
   * @return collection of bean or empty collection.
   */
  protected List<Book> findBooksBorrowedByNameForLiveData(String userName) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_BOOKS_BORROWED_BY_NAME_SQL2;
    // add where arguments
    _contentValues.addWhereArgs((userName==null?"":userName));
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

      ArrayList<Book> resultList=new ArrayList<Book>(_cursor.getCount());
      Book resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("title");

        do
         {
          resultBean=new Book();

          resultBean.id=_cursor.getString(index0);
          if (!_cursor.isNull(index1)) { resultBean.title=_cursor.getString(index1); }

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
   * <pre>SELECT * FROM book INNER JOIN loan ON loan.book_id = book.id INNER JOIN user on user.id = loan.user_id WHERE user.name LIKE :userName</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:userName</dt><dd>is binded to method's parameter <strong>userName</strong></dd>
   * </dl>
   *
   * @param userName
   * 	is binded to <code>:userName</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public LiveData<List<Book>> findBooksBorrowedByName(final String userName) {
    // common part generation - BEGIN
    // common part generation - END
    final KriptonComputableLiveData<List<Book>> builder=new KriptonComputableLiveData<List<Book>>() {
      @Override
      protected List<Book> compute() {
        return BindAppDataSource.getInstance().executeBatch(new BindAppDataSource.Batch<List<Book>>() {
          @Override
          public List<Book> onExecute(BindAppDaoFactory daoFactory) {
            return daoFactory.getBookDao().findBooksBorrowedByNameForLiveData(userName);
          }
        });
      }
    };
    registryLiveData(builder);
    return builder.getLiveData();
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT * FROM book INNER JOIN loan ON loan.book_id = book.id INNER JOIN user on user.id = loan.user_id WHERE user.name LIKE :userName AND loan.end_time > :after </pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:userName</dt><dd>is binded to method's parameter <strong>userName</strong></dd>
   * 	<dt>:after</dt><dd>is binded to method's parameter <strong>after</strong></dd>
   * </dl>
   *
   * @param userName
   * 	is binded to <code>:userName</code>
   * @param after
   * 	is binded to <code>:after</code>
   * @return collection of bean or empty collection.
   */
  protected List<Book> findBooksBorrowedByNameAfterForLiveData(String userName, Date after) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_BOOKS_BORROWED_BY_NAME_AFTER_SQL3;
    // add where arguments
    _contentValues.addWhereArgs((userName==null?"":userName));
    _contentValues.addWhereArgs((after==null?"":DateUtils.write(after)));
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

      ArrayList<Book> resultList=new ArrayList<Book>(_cursor.getCount());
      Book resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("title");

        do
         {
          resultBean=new Book();

          resultBean.id=_cursor.getString(index0);
          if (!_cursor.isNull(index1)) { resultBean.title=_cursor.getString(index1); }

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
   * <pre>SELECT * FROM book INNER JOIN loan ON loan.book_id = book.id INNER JOIN user on user.id = loan.user_id WHERE user.name LIKE :userName AND loan.end_time > :after </pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:userName</dt><dd>is binded to method's parameter <strong>userName</strong></dd>
   * 	<dt>:after</dt><dd>is binded to method's parameter <strong>after</strong></dd>
   * </dl>
   *
   * @param userName
   * 	is binded to <code>:userName</code>
   * @param after
   * 	is binded to <code>:after</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public LiveData<List<Book>> findBooksBorrowedByNameAfter(final String userName,
      final Date after) {
    // common part generation - BEGIN
    // common part generation - END
    final KriptonComputableLiveData<List<Book>> builder=new KriptonComputableLiveData<List<Book>>() {
      @Override
      protected List<Book> compute() {
        return BindAppDataSource.getInstance().executeBatch(new BindAppDataSource.Batch<List<Book>>() {
          @Override
          public List<Book> onExecute(BindAppDaoFactory daoFactory) {
            return daoFactory.getBookDao().findBooksBorrowedByNameAfterForLiveData(userName, after);
          }
        });
      }
    };
    registryLiveData(builder);
    return builder.getLiveData();
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT * FROM book INNER JOIN loan ON loan.book_id = book.id INNER JOIN user on user.id = loan.user_id WHERE user.name LIKE :userName</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:userName</dt><dd>is binded to method's parameter <strong>userName</strong></dd>
   * </dl>
   *
   * @param userName
   * 	is binded to <code>:userName</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Book> findBooksBorrowedByNameSync(String userName) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_BOOKS_BORROWED_BY_NAME_SYNC_SQL4;
    // add where arguments
    _contentValues.addWhereArgs((userName==null?"":userName));
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

      ArrayList<Book> resultList=new ArrayList<Book>(_cursor.getCount());
      Book resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("title");

        do
         {
          resultBean=new Book();

          resultBean.id=_cursor.getString(index0);
          if (!_cursor.isNull(index1)) { resultBean.title=_cursor.getString(index1); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT * FROM book INNER JOIN loan ON loan.book_id LIKE book.id WHERE loan.user_id LIKE :userId </pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:userId</dt><dd>is binded to method's parameter <strong>userId</strong></dd>
   * </dl>
   *
   * @param userId
   * 	is binded to <code>:userId</code>
   * @return collection of bean or empty collection.
   */
  protected List<Book> findBooksBorrowedByUserForLiveData(String userId) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_BOOKS_BORROWED_BY_USER_SQL5;
    // add where arguments
    _contentValues.addWhereArgs((userId==null?"":userId));
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

      ArrayList<Book> resultList=new ArrayList<Book>(_cursor.getCount());
      Book resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("title");

        do
         {
          resultBean=new Book();

          resultBean.id=_cursor.getString(index0);
          if (!_cursor.isNull(index1)) { resultBean.title=_cursor.getString(index1); }

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
   * <pre>SELECT * FROM book INNER JOIN loan ON loan.book_id LIKE book.id WHERE loan.user_id LIKE :userId </pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:userId</dt><dd>is binded to method's parameter <strong>userId</strong></dd>
   * </dl>
   *
   * @param userId
   * 	is binded to <code>:userId</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public LiveData<List<Book>> findBooksBorrowedByUser(final String userId) {
    // common part generation - BEGIN
    // common part generation - END
    final KriptonComputableLiveData<List<Book>> builder=new KriptonComputableLiveData<List<Book>>() {
      @Override
      protected List<Book> compute() {
        return BindAppDataSource.getInstance().executeBatch(new BindAppDataSource.Batch<List<Book>>() {
          @Override
          public List<Book> onExecute(BindAppDaoFactory daoFactory) {
            return daoFactory.getBookDao().findBooksBorrowedByUserForLiveData(userId);
          }
        });
      }
    };
    registryLiveData(builder);
    return builder.getLiveData();
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT * FROM book INNER JOIN loan ON loan.book_id LIKE book.id WHERE loan.user_id LIKE :userId AND loan.end_time > :after </pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:userId</dt><dd>is binded to method's parameter <strong>userId</strong></dd>
   * 	<dt>:after</dt><dd>is binded to method's parameter <strong>after</strong></dd>
   * </dl>
   *
   * @param userId
   * 	is binded to <code>:userId</code>
   * @param after
   * 	is binded to <code>:after</code>
   * @return collection of bean or empty collection.
   */
  protected List<Book> findBooksBorrowedByUserAfterForLiveData(String userId, Date after) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_BOOKS_BORROWED_BY_USER_AFTER_SQL6;
    // add where arguments
    _contentValues.addWhereArgs((userId==null?"":userId));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(DateTimeMillisecondsTypeAdapter.class, after));
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

      ArrayList<Book> resultList=new ArrayList<Book>(_cursor.getCount());
      Book resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("title");

        do
         {
          resultBean=new Book();

          resultBean.id=_cursor.getString(index0);
          if (!_cursor.isNull(index1)) { resultBean.title=_cursor.getString(index1); }

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
   * <pre>SELECT * FROM book INNER JOIN loan ON loan.book_id LIKE book.id WHERE loan.user_id LIKE :userId AND loan.end_time > :after </pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:userId</dt><dd>is binded to method's parameter <strong>userId</strong></dd>
   * 	<dt>:after</dt><dd>is binded to method's parameter <strong>after</strong></dd>
   * </dl>
   *
   * @param userId
   * 	is binded to <code>:userId</code>
   * @param after
   * 	is binded to <code>:after</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public LiveData<List<Book>> findBooksBorrowedByUserAfter(final String userId, final Date after) {
    // common part generation - BEGIN
    // common part generation - END
    final KriptonComputableLiveData<List<Book>> builder=new KriptonComputableLiveData<List<Book>>() {
      @Override
      protected List<Book> compute() {
        return BindAppDataSource.getInstance().executeBatch(new BindAppDataSource.Batch<List<Book>>() {
          @Override
          public List<Book> onExecute(BindAppDaoFactory daoFactory) {
            return daoFactory.getBookDao().findBooksBorrowedByUserAfterForLiveData(userId, after);
          }
        });
      }
    };
    registryLiveData(builder);
    return builder.getLiveData();
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT * FROM book INNER JOIN loan ON loan.book_id LIKE book.id WHERE loan.user_id = :userId </pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:userId</dt><dd>is binded to method's parameter <strong>userId</strong></dd>
   * </dl>
   *
   * @param userId
   * 	is binded to <code>:userId</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Book> findBooksBorrowedByUserSync(String userId) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_BOOKS_BORROWED_BY_USER_SYNC_SQL7;
    // add where arguments
    _contentValues.addWhereArgs((userId==null?"":userId));
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

      ArrayList<Book> resultList=new ArrayList<Book>(_cursor.getCount());
      Book resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("title");

        do
         {
          resultBean=new Book();

          resultBean.id=_cursor.getString(index0);
          if (!_cursor.isNull(index1)) { resultBean.title=_cursor.getString(index1); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, title FROM book</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  protected List<Book> findAllBooksForLiveData() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_ALL_BOOKS_SQL8;
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

      ArrayList<Book> resultList=new ArrayList<Book>(_cursor.getCount());
      Book resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("title");

        do
         {
          resultBean=new Book();

          resultBean.id=_cursor.getString(index0);
          if (!_cursor.isNull(index1)) { resultBean.title=_cursor.getString(index1); }

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
   * <pre>SELECT id, title FROM book</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public LiveData<List<Book>> findAllBooks() {
    // common part generation - BEGIN
    // common part generation - END
    final KriptonComputableLiveData<List<Book>> builder=new KriptonComputableLiveData<List<Book>>() {
      @Override
      protected List<Book> compute() {
        return BindAppDataSource.getInstance().executeBatch(new BindAppDataSource.Batch<List<Book>>() {
          @Override
          public List<Book> onExecute(BindAppDaoFactory daoFactory) {
            return daoFactory.getBookDao().findAllBooksForLiveData();
          }
        });
      }
    };
    registryLiveData(builder);
    return builder.getLiveData();
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, title FROM book</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Book> findAllBooksSync() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_ALL_BOOKS_SYNC_SQL9;
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

      ArrayList<Book> resultList=new ArrayList<Book>(_cursor.getCount());
      Book resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("title");

        do
         {
          resultBean=new Book();

          resultBean.id=_cursor.getString(index0);
          if (!_cursor.isNull(index1)) { resultBean.title=_cursor.getString(index1); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR IGNORE INTO book (id, title) VALUES (:book.id, :book.title)</pre>
   *
   * <p><code>book.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>:book.id</strong></dd>
   * 	<dt>title</dt><dd>is mapped to <strong>:book.title</strong></dd>
   * </dl>
   *
   * @param book
   * 	is mapped to parameter <strong>book</strong>
   *
   */
  @Override
  public void insertBook(Book book) {
    // Specialized Insert - InsertType - BEGIN
    if (insertBookPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT OR IGNORE INTO book (id, title) VALUES (?, ?)";
      insertBookPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertBookPreparedStatement0);
    _contentValues.put("id", book.id);
    _contentValues.put("title", book.title);

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
      Logger.info("INSERT OR IGNORE INTO book (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertBookPreparedStatement0, _contentValues);
    // support for livedata
    registryEvent(result>0?1:0);
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE OR REPLACE book SET title=:title</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>title</dt><dd>is mapped to <strong>:book.title</strong></dd>
   * </dl>
   *
   * @param book
   * 	is used as <code>:book</code>
   */
  @Override
  public void updateBook(Book book) {
    if (updateBookPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="UPDATE OR REPLACE book SET title=?";
      updateBookPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateBookPreparedStatement1);
    _contentValues.put("title", book.title);


    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE OR REPLACE book SET title=:title");

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
    int result = KriptonDatabaseWrapper.updateDelete(updateBookPreparedStatement1, _contentValues);
    // support for livedata
    registryEvent(result);
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM book</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * </dl>
   *
   */
  @Override
  public void deleteAll() {
    if (deleteAllPreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM book";
      deleteAllPreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteAllPreparedStatement2);

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM book");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteAllPreparedStatement2, _contentValues);
    // support for livedata
    registryEvent(result);
  }

  protected void registryEvent(int affectedRows) {
    if (affectedRows==0) {
      return;
    }
    if (_context.isInSession()) {
      _context.registrySQLEvent(BindAppDataSource.BOOK_DAO_UID);
    } else {
      invalidateLiveData();
    }
  }

  protected void registryLiveData(KriptonComputableLiveData<?> value) {
    liveDatas.add(new WeakReference<KriptonComputableLiveData<?>>(value));
  }

  /**
   * <p>Invalidate livedata.</p>
   *
   */
  public void invalidateLiveData() {
    for (WeakReference<KriptonComputableLiveData<?>> item: liveDatas) {
      if (item.get()!=null) {
        item.get().invalidate();
      }
    }
  }

  public static void clearCompiledStatements() {
    if (insertBookPreparedStatement0!=null) {
      insertBookPreparedStatement0.close();
      insertBookPreparedStatement0=null;
    }
    if (updateBookPreparedStatement1!=null) {
      updateBookPreparedStatement1.close();
      updateBookPreparedStatement1=null;
    }
    if (deleteAllPreparedStatement2!=null) {
      deleteAllPreparedStatement2.close();
      deleteAllPreparedStatement2=null;
    }
  }
}
