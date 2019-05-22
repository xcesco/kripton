package sqlite.git23.bug;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;

/**
 * <p>This is the content provider generated for {@link AlbumDataSource}</p>
 *
 * <h2>Content provider authority:</h2>
 * <pre>com.abubusoft.kripton.example</pre>
 *
 * <h2>Supported query operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/albums/${id}</pre></td><td>{@link AlbumDaoImpl#selectById0ForContentProvider}</td></tr>
 * </table>
 *
 * <h2>Supported update operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/albums/${pref.id}</pre></td><td>{@link AlbumDaoImpl#update1ForContentProvider}</td></tr>
 * </table>
 *
 *
 *
 */
public class BindAlbumContentProvider extends ContentProvider {
  /**
   * <p>content provider's URI.</p>
   * <pre>content://com.abubusoft.kripton.example</pre>
   */
  public static final String URI = "content://com.abubusoft.kripton.example";

  /**
   * <p>datasource singleton</p>
   */
  private static BindAlbumDataSource dataSource;

  /**
   * <p>Content provider authority</p>
   */
  public static final String AUTHORITY = "com.abubusoft.kripton.example";

  /**
   * <p>URI matcher</p>
   */
  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

  /**
   * <p>Uri</p>
   * <pre>content://com.abubusoft.kripton.example/albums/#</pre>
   */
  private static final Uri URI_PATH_ALBUM_1 = Uri.parse(URI+"/albums/#");

  static final String PATH_ALBUM_1 = "albums/#";

  static final int PATH_ALBUM_1_INDEX = 1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.kripton.example/albums/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.kripton.example/albums/${id}</pre>
   *
   * <p>Method associated to this URI is {@link AlbumDaoImpl#selectById0ForContentProvider}</p>
   */
  public static final Uri URI_ALBUM_SELECT_BY_ID = URI_PATH_ALBUM_1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.kripton.example/albums/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.kripton.example/albums/${pref.id}</pre>
   *
   * <p>Method associated to this URI is {@link AlbumDaoImpl#update1ForContentProvider}</p>
   */
  public static final Uri URI_ALBUM_UPDATE = URI_PATH_ALBUM_1;

  static {
    sURIMatcher.addURI(AUTHORITY, PATH_ALBUM_1, PATH_ALBUM_1_INDEX);
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
    dataSource = BindAlbumDataSource.getInstance();
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
   * <tr><td><pre>content://com.abubusoft.kripton.example/albums/${id}</pre></td><td>{@link AlbumDaoImpl#selectById0ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    Cursor returnCursor=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_ALBUM_1_INDEX: {
        // URI: content://com.abubusoft.kripton.example/albums/${id}
        returnCursor=dataSource.getAlbumDao().selectById0ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
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

  /**
   *
   * <h2>Supported update operations</h2>
   * <table>
   * <tr><th>URI</th><th>DAO.METHOD</th></tr>
   * <tr><td><pre>content://com.abubusoft.kripton.example/albums/${pref.id}</pre></td><td>{@link AlbumDaoImpl#update1ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public int update(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    int returnRowUpdated=1;
    switch (sURIMatcher.match(uri)) {
      case PATH_ALBUM_1_INDEX: {
        // URI: content://com.abubusoft.kripton.example/albums/${pref.id}
        returnRowUpdated=dataSource.getAlbumDao().update1ForContentProvider(uri, contentValues, selection, selectionArgs);
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

  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    throw new IllegalArgumentException("Unknown URI for DELETE operation: " + uri);
  }

  @Override
  public String getType(Uri uri) {
    switch (sURIMatcher.match(uri)) {
      case PATH_ALBUM_1_INDEX: {
        return "vnd.android.cursor.dir/vnd.com.abubusoft.kripton.example.album";
      }
    }
    throw new IllegalArgumentException("Unknown URI for getType operation: " + uri);
  }
}
