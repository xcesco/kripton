package sqlite.feature.custombean.case3;

import android.arch.lifecycle.LiveData;
import android.database.Cursor;
import com.abubusoft.kripton.android.LiveDataHandler;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.livedata.KriptonLiveDataHandlerImpl;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.common.StringUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
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
  /**
   * SQL definition for method findBooksNoIsbn
   */
  private static final String FIND_BOOKS_NO_ISBN_SQL1 = "SELECT * FROM book WHERE isbn = ?";

  static Collection<WeakReference<LiveDataHandler>> liveDatas = new CopyOnWriteArraySet<WeakReference<LiveDataHandler>>();

  public BookDaoImpl(BindAppDaoFactory daoFactory) {
    super(daoFactory.getContext());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT * FROM book WHERE isbn = :isbn</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link BookNoIsbn}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:isbn</dt><dd>is binded to method's parameter <strong>isbn</strong></dd>
   * </dl>
   *
   * @param isbn
   * 	is binded to <code>:isbn</code>
   * @return collection of bean or empty collection.
   */
  protected List<BookNoIsbn> findBooksNoIsbnForLiveData(String isbn) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_BOOKS_NO_ISBN_SQL1;
    // add where arguments
    _contentValues.addWhereArgs((isbn==null?"":isbn));
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

      ArrayList<BookNoIsbn> resultList=new ArrayList<BookNoIsbn>(_cursor.getCount());
      BookNoIsbn resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("title");

        do
         {
          resultBean=new BookNoIsbn();

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
   * <pre>SELECT * FROM book WHERE isbn = :isbn</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link BookNoIsbn}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:isbn</dt><dd>is binded to method's parameter <strong>isbn</strong></dd>
   * </dl>
   *
   * @param isbn
   * 	is binded to <code>:isbn</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public LiveData<List<BookNoIsbn>> findBooksNoIsbn(final String isbn) {
    // common part generation - BEGIN
    // common part generation - END
    final KriptonLiveDataHandlerImpl<List<BookNoIsbn>> builder=new KriptonLiveDataHandlerImpl<List<BookNoIsbn>>() {
      @Override
      protected List<BookNoIsbn> compute() {
        return BindAppDataSource.getInstance().executeBatch(new BindAppDataSource.Batch<List<BookNoIsbn>>() {
          @Override
          public List<BookNoIsbn> onExecute(BindAppDaoFactory daoFactory) {
            return daoFactory.getBookDao().findBooksNoIsbnForLiveData(isbn);
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
      _context.registrySQLEvent(BindAppDataSource.BOOK_DAO_UID);
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
  }
}
