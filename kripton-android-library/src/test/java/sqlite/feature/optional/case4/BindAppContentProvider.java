package sqlite.feature.optional.case4;

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
 * <pre>com.abubusoft.kripton.example</pre>
 *
 * <h2>Supported query operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/artists/title/:id</pre></td><td>{@link DaoArtistImpl#selectTitleById0ForContentProvider}</td></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/artists/title2/:id</pre></td><td>{@link DaoArtistImpl#selectTitle2ById1ForContentProvider}</td></tr>
 * </table>
 *
 *
 *
 */
public class BindAppContentProvider extends ContentProvider {
  /**
   * <p>content provider's URI.</p>
   * <pre>content://com.abubusoft.kripton.example</pre>
   */
  public static final String URI = "content://com.abubusoft.kripton.example";

  /**
   * <p>datasource singleton</p>
   */
  private static BindAppDataSource dataSource;

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
   * <pre>content://com.abubusoft.kripton.example/artists/title/#</pre>
   */
  private static final Uri URI_PATH_ARTIST_1 = Uri.parse(URI+"/artists/title/#");

  /**
   * <p>Uri</p>
   * <pre>content://com.abubusoft.kripton.example/artists/title2/#</pre>
   */
  private static final Uri URI_PATH_ARTIST_2 = Uri.parse(URI+"/artists/title2/#");

  static final String PATH_ARTIST_1 = "artists/title/#";

  static final String PATH_ARTIST_2 = "artists/title2/#";

  static final int PATH_ARTIST_1_INDEX = 1;

  static final int PATH_ARTIST_2_INDEX = 2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.kripton.example/artists/title/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.kripton.example/artists/title/:id</pre>
   *
   * <p>Method associated to this URI is {@link DaoArtistImpl#selectTitleById0ForContentProvider}</p>
   */
  public static final Uri URI_ARTIST_SELECT_TITLE_BY_ID = URI_PATH_ARTIST_1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.kripton.example/artists/title2/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.kripton.example/artists/title2/:id</pre>
   *
   * <p>Method associated to this URI is {@link DaoArtistImpl#selectTitle2ById1ForContentProvider}</p>
   */
  public static final Uri URI_ARTIST_SELECT_TITLE2_BY_ID = URI_PATH_ARTIST_2;

  static {
    sURIMatcher.addURI(AUTHORITY, PATH_ARTIST_1, PATH_ARTIST_1_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_ARTIST_2, PATH_ARTIST_2_INDEX);
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
   * <tr><td><pre>content://com.abubusoft.kripton.example/artists/title/:id</pre></td><td>{@link DaoArtistImpl#selectTitleById0ForContentProvider}</td></tr>
   * <tr><td><pre>content://com.abubusoft.kripton.example/artists/title2/:id</pre></td><td>{@link DaoArtistImpl#selectTitle2ById1ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    Cursor returnCursor=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_ARTIST_1_INDEX: {
        // URI: content://com.abubusoft.kripton.example/artists/title/:id
        returnCursor=dataSource.getDaoArtist().selectTitleById0ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_ARTIST_2_INDEX: {
        // URI: content://com.abubusoft.kripton.example/artists/title2/:id
        returnCursor=dataSource.getDaoArtist().selectTitle2ById1ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
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
      case PATH_ARTIST_1_INDEX: {
        return "vnd.android.cursor.dir/vnd.com.abubusoft.kripton.example.artist";
      }
      case PATH_ARTIST_2_INDEX: {
        return "vnd.android.cursor.dir/vnd.com.abubusoft.kripton.example.artist";
      }
    }
    throw new IllegalArgumentException("Unknown URI for getType operation: " + uri);
  }
}
