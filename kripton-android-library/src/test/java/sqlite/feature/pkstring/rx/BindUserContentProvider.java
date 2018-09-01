package sqlite.feature.pkstring.rx;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;

/**
 * <p>This is the content provider generated for {@link UserDataSource}</p>
 *
 * <h2>Content provider authority:</h2>
 * <pre>sqlite.feature.pkstring.rx</pre>
 *
 * <h2>Supported insert operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://sqlite.feature.pkstring.rx/user/insert1</pre></td><td>{@link UserDaoImpl#insertUser0ForContentProvider}</td></tr>
 * <tr><td><pre>content://sqlite.feature.pkstring.rx/user/insert2</pre></td><td>{@link UserDaoImpl#insertUser1ForContentProvider}</td></tr>
 * </table>
 *
 *
 *
 */
public class BindUserContentProvider extends ContentProvider {
  /**
   * <p>content provider's URI.</p>
   * <pre>content://sqlite.feature.pkstring.rx</pre>
   */
  public static final String URI = "content://sqlite.feature.pkstring.rx";

  /**
   * <p>datasource singleton</p>
   */
  private static BindUserDataSource dataSource;

  /**
   * <p>Content provider authority</p>
   */
  public static final String AUTHORITY = "sqlite.feature.pkstring.rx";

  /**
   * <p>URI matcher</p>
   */
  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.pkstring.rx/user/insert1</pre>
   */
  private static final Uri URI_PATH_USER_1 = Uri.parse(URI+"/user/insert1");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.pkstring.rx/user/insert2</pre>
   */
  private static final Uri URI_PATH_USER_2 = Uri.parse(URI+"/user/insert2");

  static final String PATH_USER_1 = "user/insert1";

  static final String PATH_USER_2 = "user/insert2";

  static final int PATH_USER_1_INDEX = 1;

  static final int PATH_USER_2_INDEX = 2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.pkstring.rx/user/insert1</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.pkstring.rx/user/insert1</pre>
   *
   * <p>Method associated to this URI is {@link UserDaoImpl#insertUser0ForContentProvider}</p>
   */
  public static final Uri URI_USER_INSERT_USER = URI_PATH_USER_1;

  static {
    sURIMatcher.addURI(AUTHORITY, PATH_USER_1, PATH_USER_1_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_USER_2, PATH_USER_2_INDEX);
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
    dataSource = BindUserDataSource.getInstance();
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

  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    throw new IllegalArgumentException("Unknown URI for SELECT operation: " + uri);
  }

  /**
   *
   * <h2>Supported insert operations</h2>
   * <table>
   * <tr><th>URI</th><th>DAO.METHOD</th></tr>
   * <tr><td><pre>content://sqlite.feature.pkstring.rx/user/insert1</pre></td><td>{@link UserDaoImpl#insertUser0ForContentProvider}</td></tr>
   * <tr><td><pre>content://sqlite.feature.pkstring.rx/user/insert2</pre></td><td>{@link UserDaoImpl#insertUser1ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public Uri insert(Uri uri, ContentValues contentValues) {
    long _id=-1;
    Uri _returnURL=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_USER_1_INDEX: {
        _id=dataSource.getUserDao().insertUser0ForContentProvider(uri, contentValues);
        _returnURL=Uri.withAppendedPath(uri, String.valueOf(_id));
        break;
      }
      case PATH_USER_2_INDEX: {
        _id=dataSource.getUserDao().insertUser1ForContentProvider(uri, contentValues);
        _returnURL=Uri.withAppendedPath(uri, String.valueOf(_id));
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for INSERT operation: " + uri);
      }
    }
    // log section for content provider insert BEGIN
    if (dataSource.isLogEnabled()) {
      Logger.info("Element is created with URI '%s'", _returnURL);
      Logger.info("Changes are notified for URI '%s'", uri);
    }
    // log section for content provider insert END
    getContext().getContentResolver().notifyChange(uri, null);
    return _returnURL;
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
      case PATH_USER_1_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.pkstring.rx.user";
      }
      case PATH_USER_2_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.pkstring.rx.user";
      }
    }
    throw new IllegalArgumentException("Unknown URI for getType operation: " + uri);
  }
}
