package sqlite.feature.contentprovider.base;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import java.lang.IllegalArgumentException;
import java.lang.Override;
import java.lang.String;

/**
 * <p>This is the content provider generated for {@link ArtistsDataSource}</p>
 *
 * <h2>Content provider authority:</h2>
 * <pre>com.abubusoft.kripton.example</pre>
 *
 * <h2>Supported insert operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * </table>
 *
 * <h2>Supported query operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/artist/${id}</pre></td><td>{@link ArtistDaoImpl#selectById0}</td></tr>
 * </table>
 */
public class BindArtistsContentProvider extends ContentProvider {
  /**
   * <p>content provider's URI. Example:</p>
   * <pre>content://sqlite.contentprovider.kripton35</pre>
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

  public static final String PATH_ARTIST_1 = "artist/#";

  static final int PATH_ARTIST_1_INDEX = 1;

  static {
    sURIMatcher.addURI(AUTHORITY, PATH_ARTIST_1, PATH_ARTIST_1_INDEX);
  }

  /**
   * <p>Create datasource and open database in read mode.</p>
   *
   * @see android.content.ContentProvider#onCreate()
   */
  @Override
  public boolean onCreate() {
    dataSource = BindArtistsDataSource.instance();
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
  public Uri insert(Uri uri, ContentValues contentValues) {
    long _id=-1;
    Uri _returnURL=null;
    switch (sURIMatcher.match(uri)) {
      default: {
        throw new IllegalArgumentException("Unknown URI for INSERT operation: " + uri);
      }
    }
  }

  @Override
  public int update(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    throw new IllegalArgumentException("Unknown URI for UPDATE operation: " + uri);
  }

  /**
   * method ArtistDao.selectById
   * method ArtistDao.selectById
   * method ArtistDao.selectAll
   */
  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    Cursor returnCursor=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_ARTIST_1_INDEX: {
        // URI: content://com.abubusoft.kripton.example/artist/${id}
        returnCursor=dataSource.getArtistDao().selectById0(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for SELECT operation: " + uri);
      }
    }
    return returnCursor;
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
    }
    throw new IllegalArgumentException("Unknown URI for getType operation: " + uri);
  }
}
