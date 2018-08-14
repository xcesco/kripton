package sqlite.feature.custombean.case1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.KriptonLibrary;

/**
 * <p>This is the content provider generated for {@link AppDataSource}</p>
 *
 * <h2>Content provider authority:</h2>
 * <pre>sqlite.feature.custombean.case1</pre>
 *
 * <h2>Supported query operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://sqlite.feature.custombean.case1/loan/loadAll</pre></td><td>{@link LoanDaoImpl#findAllLoans0ForContentProvider}</td></tr>
 * <tr><td><pre>content://sqlite.feature.custombean.case1/loan/loadLoanAndBook</pre></td><td>{@link LoanDaoImpl#findAllWithUserAndBook1ForContentProvider}</td></tr>
 * </table>
 *
 *
 *
 */
public class BindAppContentProvider extends ContentProvider {
  /**
   * <p>content provider's URI.</p>
   * <pre>content://sqlite.feature.custombean.case1</pre>
   */
  public static final String URI = "content://sqlite.feature.custombean.case1";

  /**
   * <p>datasource singleton</p>
   */
  private static BindAppDataSource dataSource;

  /**
   * <p>Content provider authority</p>
   */
  public static final String AUTHORITY = "sqlite.feature.custombean.case1";

  /**
   * <p>URI matcher</p>
   */
  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.custombean.case1/loan/loadAll</pre>
   */
  private static final Uri URI_PATH_LOAN_1 = Uri.parse(URI+"/loan/loadAll");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.custombean.case1/loan/loadLoanAndBook</pre>
   */
  private static final Uri URI_PATH_LOAN_2 = Uri.parse(URI+"/loan/loadLoanAndBook");

  static final String PATH_LOAN_1 = "loan/loadAll";

  static final String PATH_LOAN_2 = "loan/loadLoanAndBook";

  static final int PATH_LOAN_1_INDEX = 1;

  static final int PATH_LOAN_2_INDEX = 2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.custombean.case1/loan/loadAll</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.custombean.case1/loan/loadAll</pre>
   *
   * <p>Method associated to this URI is {@link LoanDaoImpl#findAllLoans0ForContentProvider}</p>
   */
  public static final Uri URI_LOAN_FIND_ALL_LOANS = URI_PATH_LOAN_1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.custombean.case1/loan/loadLoanAndBook</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.custombean.case1/loan/loadLoanAndBook</pre>
   *
   * <p>Method associated to this URI is {@link LoanDaoImpl#findAllWithUserAndBook1ForContentProvider}</p>
   */
  public static final Uri URI_LOAN_FIND_ALL_WITH_USER_AND_BOOK = URI_PATH_LOAN_2;

  static {
    sURIMatcher.addURI(AUTHORITY, PATH_LOAN_1, PATH_LOAN_1_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_LOAN_2, PATH_LOAN_2_INDEX);
  }

  /**
   * <p>Create datasource and open database in read mode.</p>
   *
   * @see android.content.ContentProvider#onCreate()
   */
  @Override
  public boolean onCreate() {
    if (KriptonLibrary.getContext()==null) {
      KriptonLibrary.init(getContext());
    }
    dataSource = BindAppDataSource.getInstance();
    dataSource.openWritableDatabase();
    return true;
  }

  /**
   * <p>Close database.</p>
   *
   * @see android.content.ContentProvider#shutdown()
   */
  @Override
  public void shutdown() {
    super.shutdown();
    dataSource.close();
  }

  /**
   *
   * <h2>Supported query operations</h2>
   * <table>
   * <tr><th>URI</th><th>DAO.METHOD</th></tr>
   * <tr><td><pre>content://sqlite.feature.custombean.case1/loan/loadAll</pre></td><td>{@link LoanDaoImpl#findAllLoans0ForContentProvider}</td></tr>
   * <tr><td><pre>content://sqlite.feature.custombean.case1/loan/loadLoanAndBook</pre></td><td>{@link LoanDaoImpl#findAllWithUserAndBook1ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    Cursor returnCursor=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_LOAN_1_INDEX: {
        // URI: content://sqlite.feature.custombean.case1/loan/loadAll
        returnCursor=dataSource.getLoanDao().findAllLoans0ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_LOAN_2_INDEX: {
        // URI: content://sqlite.feature.custombean.case1/loan/loadLoanAndBook
        returnCursor=dataSource.getLoanDao().findAllWithUserAndBook1ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for SELECT operation: " + uri);
      }
    }
    return returnCursor;
  }

  @Override
  public Uri insert(Uri uri, ContentValues contentValues) {
    throw new IllegalArgumentException("Unknown URI for DELETE operation: " + uri);
  }

  @Override
  public int update(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    throw new IllegalArgumentException("Unknown URI for UPDATE operation: " + uri);
  }

  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    throw new IllegalArgumentException("Unknown URI for DELETE operation: " + uri);
  }

  @Override
  public String getType(Uri uri) {
    switch (sURIMatcher.match(uri)) {
      case PATH_LOAN_1_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.custombean.case1.loan";
      }
      case PATH_LOAN_2_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.custombean.case1.loan";
      }
    }
    throw new IllegalArgumentException("Unknown URI for getType operation: " + uri);
  }
}
