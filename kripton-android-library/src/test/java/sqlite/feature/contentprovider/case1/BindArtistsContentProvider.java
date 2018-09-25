package sqlite.feature.contentprovider.case1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;

/**
 * <p>This is the content provider generated for {@link ArtistsDataSource}</p>
 *
 * <h2>Content provider authority:</h2>
 * <pre>com.abubusoft.kripton.example</pre>
 *
 * <h2>Supported query operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/albums</pre></td><td>{@link AlbumDaoImpl#selectAll1ForContentProvider}</td></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/albums/${id}</pre></td><td>{@link AlbumDaoImpl#selectById0ForContentProvider}</td></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/artists</pre></td><td>{@link ArtistDaoImpl#selectAll1ForContentProvider}</td></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/artists/${id}</pre></td><td>{@link ArtistDaoImpl#selectById0ForContentProvider}</td></tr>
 * </table>
 *
 * <h2>Supported insert operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/albums</pre></td><td>{@link AlbumDaoImpl#insert2ForContentProvider}</td></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/artists</pre></td><td>{@link ArtistDaoImpl#insert2ForContentProvider}</td></tr>
 * </table>
 *
 * <h2>Supported update operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/albums/${bean.id}</pre></td><td>{@link AlbumDaoImpl#update3ForContentProvider}</td></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/artists/${bean.id}</pre></td><td>{@link ArtistDaoImpl#update3ForContentProvider}</td></tr>
 * </table>
 *
 * <h2>Supported delete operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/albums/${bean.id}</pre></td><td>{@link AlbumDaoImpl#delete4ForContentProvider}</td></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/artists/${bean.id}</pre></td><td>{@link ArtistDaoImpl#delete4ForContentProvider}</td></tr>
 * </table>
 *
 *
 *
 */
public class BindArtistsContentProvider extends ContentProvider {
  /**
   * <p>content provider's URI.</p>
   * <pre>content://com.abubusoft.kripton.example</pre>
   */
  public static final String URI = "content://com.abubusoft.kripton.example";

  /**
   * <p>datasource singleton</p>
   */
  private static BindArtistsDataSource dataSource;

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
   * <pre>content://com.abubusoft.kripton.example/albums</pre>
   */
  private static final Uri URI_PATH_ALBUM_1 = Uri.parse(URI+"/albums");

  /**
   * <p>Uri</p>
   * <pre>content://com.abubusoft.kripton.example/albums/#</pre>
   */
  private static final Uri URI_PATH_ALBUM_2 = Uri.parse(URI+"/albums/#");

  /**
   * <p>Uri</p>
   * <pre>content://com.abubusoft.kripton.example/artists</pre>
   */
  private static final Uri URI_PATH_ARTIST_3 = Uri.parse(URI+"/artists");

  /**
   * <p>Uri</p>
   * <pre>content://com.abubusoft.kripton.example/artists/#</pre>
   */
  private static final Uri URI_PATH_ARTIST_4 = Uri.parse(URI+"/artists/#");

  static final String PATH_ALBUM_1 = "albums";

  static final String PATH_ALBUM_2 = "albums/#";

  static final String PATH_ARTIST_3 = "artists";

  static final String PATH_ARTIST_4 = "artists/#";

  static final int PATH_ALBUM_1_INDEX = 1;

  static final int PATH_ALBUM_2_INDEX = 2;

  static final int PATH_ARTIST_3_INDEX = 3;

  static final int PATH_ARTIST_4_INDEX = 4;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.kripton.example/albums</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.kripton.example/albums</pre>
   *
   * <p>Method associated to this URI is {@link AlbumDaoImpl#insert2ForContentProvider}</p>
   */
  public static final Uri URI_ALBUM_INSERT = URI_PATH_ALBUM_1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.kripton.example/albums</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.kripton.example/albums</pre>
   *
   * <p>Method associated to this URI is {@link AlbumDaoImpl#selectAll1ForContentProvider}</p>
   */
  public static final Uri URI_ALBUM_SELECT_ALL = URI_PATH_ALBUM_1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.kripton.example/albums/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.kripton.example/albums/${bean.id}</pre>
   *
   * <p>Method associated to this URI is {@link AlbumDaoImpl#delete4ForContentProvider}</p>
   */
  public static final Uri URI_ALBUM_DELETE = URI_PATH_ALBUM_2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.kripton.example/albums/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.kripton.example/albums/${id}</pre>
   *
   * <p>Method associated to this URI is {@link AlbumDaoImpl#selectById0ForContentProvider}</p>
   */
  public static final Uri URI_ALBUM_SELECT_BY_ID = URI_PATH_ALBUM_2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.kripton.example/albums/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.kripton.example/albums/${bean.id}</pre>
   *
   * <p>Method associated to this URI is {@link AlbumDaoImpl#update3ForContentProvider}</p>
   */
  public static final Uri URI_ALBUM_UPDATE = URI_PATH_ALBUM_2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.kripton.example/artists</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.kripton.example/artists</pre>
   *
   * <p>Method associated to this URI is {@link ArtistDaoImpl#insert2ForContentProvider}</p>
   */
  public static final Uri URI_ARTIST_INSERT = URI_PATH_ARTIST_3;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.kripton.example/artists</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.kripton.example/artists</pre>
   *
   * <p>Method associated to this URI is {@link ArtistDaoImpl#selectAll1ForContentProvider}</p>
   */
  public static final Uri URI_ARTIST_SELECT_ALL = URI_PATH_ARTIST_3;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.kripton.example/artists/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.kripton.example/artists/${bean.id}</pre>
   *
   * <p>Method associated to this URI is {@link ArtistDaoImpl#delete4ForContentProvider}</p>
   */
  public static final Uri URI_ARTIST_DELETE = URI_PATH_ARTIST_4;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.kripton.example/artists/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.kripton.example/artists/${id}</pre>
   *
   * <p>Method associated to this URI is {@link ArtistDaoImpl#selectById0ForContentProvider}</p>
   */
  public static final Uri URI_ARTIST_SELECT_BY_ID = URI_PATH_ARTIST_4;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.kripton.example/artists/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.kripton.example/artists/${bean.id}</pre>
   *
   * <p>Method associated to this URI is {@link ArtistDaoImpl#update3ForContentProvider}</p>
   */
  public static final Uri URI_ARTIST_UPDATE = URI_PATH_ARTIST_4;

  static {
    sURIMatcher.addURI(AUTHORITY, PATH_ALBUM_1, PATH_ALBUM_1_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_ALBUM_2, PATH_ALBUM_2_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_ARTIST_3, PATH_ARTIST_3_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_ARTIST_4, PATH_ARTIST_4_INDEX);
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
    dataSource = BindArtistsDataSource.getInstance();
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
   * <tr><td><pre>content://com.abubusoft.kripton.example/albums</pre></td><td>{@link AlbumDaoImpl#selectAll1ForContentProvider}</td></tr>
   * <tr><td><pre>content://com.abubusoft.kripton.example/albums/${id}</pre></td><td>{@link AlbumDaoImpl#selectById0ForContentProvider}</td></tr>
   * <tr><td><pre>content://com.abubusoft.kripton.example/artists</pre></td><td>{@link ArtistDaoImpl#selectAll1ForContentProvider}</td></tr>
   * <tr><td><pre>content://com.abubusoft.kripton.example/artists/${id}</pre></td><td>{@link ArtistDaoImpl#selectById0ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    Cursor returnCursor=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_ALBUM_1_INDEX: {
        // URI: content://com.abubusoft.kripton.example/albums
        returnCursor=dataSource.getAlbumDao().selectAll1ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_ALBUM_2_INDEX: {
        // URI: content://com.abubusoft.kripton.example/albums/${id}
        returnCursor=dataSource.getAlbumDao().selectById0ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_ARTIST_3_INDEX: {
        // URI: content://com.abubusoft.kripton.example/artists
        returnCursor=dataSource.getArtistDao().selectAll1ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_ARTIST_4_INDEX: {
        // URI: content://com.abubusoft.kripton.example/artists/${id}
        returnCursor=dataSource.getArtistDao().selectById0ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
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
   * <tr><td><pre>content://com.abubusoft.kripton.example/albums</pre></td><td>{@link AlbumDaoImpl#insert2ForContentProvider}</td></tr>
   * <tr><td><pre>content://com.abubusoft.kripton.example/artists</pre></td><td>{@link ArtistDaoImpl#insert2ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public Uri insert(Uri uri, ContentValues contentValues) {
    long _id=-1;
    Uri _returnURL=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_ALBUM_1_INDEX: {
        _id=dataSource.getAlbumDao().insert2ForContentProvider(uri, contentValues);
        _returnURL=Uri.withAppendedPath(uri, String.valueOf(_id));
        break;
      }
      case PATH_ARTIST_3_INDEX: {
        _id=dataSource.getArtistDao().insert2ForContentProvider(uri, contentValues);
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

  /**
   *
   * <h2>Supported update operations</h2>
   * <table>
   * <tr><th>URI</th><th>DAO.METHOD</th></tr>
   * <tr><td><pre>content://com.abubusoft.kripton.example/albums/${bean.id}</pre></td><td>{@link AlbumDaoImpl#update3ForContentProvider}</td></tr>
   * <tr><td><pre>content://com.abubusoft.kripton.example/artists/${bean.id}</pre></td><td>{@link ArtistDaoImpl#update3ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public int update(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    int returnRowUpdated=1;
    switch (sURIMatcher.match(uri)) {
      case PATH_ALBUM_2_INDEX: {
        // URI: content://com.abubusoft.kripton.example/albums/${bean.id}
        returnRowUpdated=dataSource.getAlbumDao().update3ForContentProvider(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_ARTIST_4_INDEX: {
        // URI: content://com.abubusoft.kripton.example/artists/${bean.id}
        returnRowUpdated=dataSource.getArtistDao().update3ForContentProvider(uri, contentValues, selection, selectionArgs);
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
   * <tr><td><pre>content://com.abubusoft.kripton.example/albums/${bean.id}</pre></td><td>{@link AlbumDaoImpl#delete4ForContentProvider}</td></tr>
   * <tr><td><pre>content://com.abubusoft.kripton.example/artists/${bean.id}</pre></td><td>{@link ArtistDaoImpl#delete4ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    int returnRowDeleted=-1;
    switch (sURIMatcher.match(uri)) {
      case PATH_ALBUM_2_INDEX: {
        // URI: content://com.abubusoft.kripton.example/albums/${bean.id}
        returnRowDeleted=dataSource.getAlbumDao().delete4ForContentProvider(uri, selection, selectionArgs);
        break;
      }
      case PATH_ARTIST_4_INDEX: {
        // URI: content://com.abubusoft.kripton.example/artists/${bean.id}
        returnRowDeleted=dataSource.getArtistDao().delete4ForContentProvider(uri, selection, selectionArgs);
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for DELETE operation: " + uri);
      }
    }
    // log section for content provider delete BEGIN
    if (dataSource.isLogEnabled()) {
      Logger.info("Changes are notified for URI %s", uri);
    }
    // log section for content provider delete END
    getContext().getContentResolver().notifyChange(uri, null);
    return returnRowDeleted;
  }

  @Override
  public String getType(Uri uri) {
    switch (sURIMatcher.match(uri)) {
      case PATH_ALBUM_1_INDEX: {
        return "vnd.android.cursor.dir/vnd.com.abubusoft.kripton.example.album";
      }
      case PATH_ALBUM_2_INDEX: {
        return "vnd.android.cursor.dir/vnd.com.abubusoft.kripton.example.album";
      }
      case PATH_ARTIST_3_INDEX: {
        return "vnd.android.cursor.dir/vnd.com.abubusoft.kripton.example.artist";
      }
      case PATH_ARTIST_4_INDEX: {
        return "vnd.android.cursor.dir/vnd.com.abubusoft.kripton.example.artist";
      }
    }
    throw new IllegalArgumentException("Unknown URI for getType operation: " + uri);
  }
}
