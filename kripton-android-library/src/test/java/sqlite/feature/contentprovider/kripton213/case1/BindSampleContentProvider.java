package sqlite.feature.contentprovider.kripton213.case1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;

/**
 * <p>This is the content provider generated for {@link SampleDataSource}</p>
 *
 * <h2>Content provider authority:</h2>
 * <pre>com.abubusoft.contentprovidersample.provider</pre>
 *
 * <h2>Supported query operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese</pre></td><td>{@link CheeseDaoImpl#selectAll1ForContentProvider}</td></tr>
 * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese/${id}</pre></td><td>{@link CheeseDaoImpl#selectById2ForContentProvider}</td></tr>
 * </table>
 *
 * <h2>Supported insert operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese</pre></td><td>{@link CheeseDaoImpl#insert0ForContentProvider}</td></tr>
 * </table>
 *
 * <h2>Supported update operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese/${cheese.id}</pre></td><td>{@link CheeseDaoImpl#update4ForContentProvider}</td></tr>
 * </table>
 *
 * <h2>Supported delete operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese/${id}</pre></td><td>{@link CheeseDaoImpl#deleteById3ForContentProvider}</td></tr>
 * </table>
 *
 */
public class BindSampleContentProvider extends ContentProvider {
  /**
   * <p>content provider's URI.</p>
   * <pre>content://com.abubusoft.contentprovidersample.provider</pre>
   */
  public static final String URI = "content://com.abubusoft.contentprovidersample.provider";

  /**
   * <p>datasource singleton</p>
   */
  private static BindSampleDataSource dataSource;

  /**
   * <p>Content provider authority</p>
   */
  public static final String AUTHORITY = "com.abubusoft.contentprovidersample.provider";

  /**
   * <p>URI matcher</p>
   */
  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

  /**
   * <p>Uri</p>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese</pre>
   */
  private static final Uri URI_PATH_CHEESE_1 = Uri.parse(URI+"/cheese");

  /**
   * <p>Uri</p>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese/#</pre>
   */
  private static final Uri URI_PATH_CHEESE_2 = Uri.parse(URI+"/cheese/#");

  static final String PATH_CHEESE_1 = "cheese";

  static final String PATH_CHEESE_2 = "cheese/#";

  static final int PATH_CHEESE_1_INDEX = 1;

  static final int PATH_CHEESE_2_INDEX = 2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese</pre>
   *
   * <p>Method associated to this URI is {@link CheeseDaoImpl#insert0ForContentProvider}</p>
   */
  public static final Uri URI_CHEESE_INSERT = URI_PATH_CHEESE_1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese</pre>
   *
   * <p>Method associated to this URI is {@link CheeseDaoImpl#selectAll1ForContentProvider}</p>
   */
  public static final Uri URI_CHEESE_SELECT_ALL = URI_PATH_CHEESE_1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese/${id}</pre>
   *
   * <p>Method associated to this URI is {@link CheeseDaoImpl#deleteById3ForContentProvider}</p>
   */
  public static final Uri URI_CHEESE_DELETE_BY_ID = URI_PATH_CHEESE_2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese/${id}</pre>
   *
   * <p>Method associated to this URI is {@link CheeseDaoImpl#selectById2ForContentProvider}</p>
   */
  public static final Uri URI_CHEESE_SELECT_BY_ID = URI_PATH_CHEESE_2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese/${cheese.id}</pre>
   *
   * <p>Method associated to this URI is {@link CheeseDaoImpl#update4ForContentProvider}</p>
   */
  public static final Uri URI_CHEESE_UPDATE = URI_PATH_CHEESE_2;

  static {
    sURIMatcher.addURI(AUTHORITY, PATH_CHEESE_1, PATH_CHEESE_1_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_CHEESE_2, PATH_CHEESE_2_INDEX);
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
    dataSource = BindSampleDataSource.getInstance();
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
   * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese</pre></td><td>{@link CheeseDaoImpl#selectAll1ForContentProvider}</td></tr>
   * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese/${id}</pre></td><td>{@link CheeseDaoImpl#selectById2ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    Cursor returnCursor=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_CHEESE_1_INDEX: {
        // URI: content://com.abubusoft.contentprovidersample.provider/cheese
        returnCursor=dataSource.getCheeseDao().selectAll1ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_CHEESE_2_INDEX: {
        // URI: content://com.abubusoft.contentprovidersample.provider/cheese/${id}
        returnCursor=dataSource.getCheeseDao().selectById2ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for SELECT operation: " + uri);
      }
    }
    return returnCursor;
  }

  /**
   *
   * <h2>Supported insert operations</h2>
   * <table>
   * <tr><th>URI</th><th>DAO.METHOD</th></tr>
   * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese</pre></td><td>{@link CheeseDaoImpl#insert0ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public Uri insert(Uri uri, ContentValues contentValues) {
    long _id=-1;
    Uri _returnURL=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_CHEESE_1_INDEX: {
        _id=dataSource.getCheeseDao().insert0ForContentProvider(uri, contentValues);
        _returnURL=Uri.withAppendedPath(uri, String.valueOf(_id));
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for INSERT operation: " + uri);
      }
    }
    // log section BEGIN
    if (dataSource.isLogEnabled()) {
      Logger.info("Element is created with URI '%s'", _returnURL);
      Logger.info("Changes are notified for URI '%s'", uri);
    }
    // log section END
    getContext().getContentResolver().notifyChange(uri, null);
    return _returnURL;
  }

  /**
   *
   * <h2>Supported update operations</h2>
   * <table>
   * <tr><th>URI</th><th>DAO.METHOD</th></tr>
   * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese/${cheese.id}</pre></td><td>{@link CheeseDaoImpl#update4ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public int update(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    int returnRowUpdated=1;
    switch (sURIMatcher.match(uri)) {
      case PATH_CHEESE_2_INDEX: {
        // URI: content://com.abubusoft.contentprovidersample.provider/cheese/${cheese.id}
        returnRowUpdated=dataSource.getCheeseDao().update4ForContentProvider(uri, contentValues, selection, selectionArgs);
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for UPDATE operation: " + uri);
      }
    }
    // log section BEGIN
    if (dataSource.isLogEnabled()) {
      Logger.info("Changes are notified for URI %s", uri);
    }
    // log section END
    getContext().getContentResolver().notifyChange(uri, null);
    return returnRowUpdated;
  }

  /**
   *
   * <h2>Supported delete operations</h2>
   * <table>
   * <tr><th>URI</th><th>DAO.METHOD</th></tr>
   * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese/${id}</pre></td><td>{@link CheeseDaoImpl#deleteById3ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    int returnRowDeleted=-1;
    switch (sURIMatcher.match(uri)) {
      case PATH_CHEESE_2_INDEX: {
        // URI: content://com.abubusoft.contentprovidersample.provider/cheese/${id}
        returnRowDeleted=dataSource.getCheeseDao().deleteById3ForContentProvider(uri, selection, selectionArgs);
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for DELETE operation: " + uri);
      }
    }
    // log section BEGIN
    if (dataSource.isLogEnabled()) {
      Logger.info("Changes are notified for URI %s", uri);
    }
    // log section END
    getContext().getContentResolver().notifyChange(uri, null);
    return returnRowDeleted;
  }

  @Override
  public String getType(Uri uri) {
    switch (sURIMatcher.match(uri)) {
      case PATH_CHEESE_1_INDEX: {
        return "vnd.android.cursor.dir/vnd.com.abubusoft.contentprovidersample.provider.cheese";
      }
      case PATH_CHEESE_2_INDEX: {
        return "vnd.android.cursor.dir/vnd.com.abubusoft.contentprovidersample.provider.cheese";
      }
    }
    throw new IllegalArgumentException("Unknown URI for getType operation: " + uri);
  }
}
