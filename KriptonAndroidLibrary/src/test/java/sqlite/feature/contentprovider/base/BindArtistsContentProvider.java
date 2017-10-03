package sqlite.feature.contentprovider.base;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;
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
 * <tr><td><pre>content://com.abubusoft.kripton.example/albums</pre></td><td>{@link AlbumDaoImpl#insert2}</td></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/artists</pre></td><td>{@link ArtistDaoImpl#insert2}</td></tr>
 * </table>
 *
 * <h2>Supported update operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/albums/${bean.id}</pre></td><td>{@link AlbumDaoImpl#update3}</td></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/artists/${bean.id}</pre></td><td>{@link ArtistDaoImpl#update3}</td></tr>
 * </table>
 *
 * <h2>Supported query operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/albums</pre></td><td>{@link AlbumDaoImpl#selectAll1}</td></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/albums/${id}</pre></td><td>{@link AlbumDaoImpl#selectById0}</td></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/artists</pre></td><td>{@link ArtistDaoImpl#selectAll1}</td></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/artists/${id}</pre></td><td>{@link ArtistDaoImpl#selectById0}</td></tr>
 * </table>
 *
 * <h2>Supported delete operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/albums/${bean.id}</pre></td><td>{@link AlbumDaoImpl#delete4}</td></tr>
 * <tr><td><pre>content://com.abubusoft.kripton.example/artists/${bean.id}</pre></td><td>{@link ArtistDaoImpl#delete4}</td></tr>
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

  public static final String PATH_ALBUM_1 = "albums";

  public static final String PATH_ALBUM_2 = "albums/#";

  public static final String PATH_ARTIST_3 = "artists";

  public static final String PATH_ARTIST_4 = "artists/#";

  static final int PATH_ALBUM_1_INDEX = 1;

  static final int PATH_ALBUM_2_INDEX = 2;

  static final int PATH_ARTIST_3_INDEX = 3;

  static final int PATH_ARTIST_4_INDEX = 4;

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

  /**
   * method ArtistDao.insert
   * method AlbumDao.insert
   */
  @Override
  public Uri insert(Uri uri, ContentValues contentValues) {
    long _id=-1;
    Uri _returnURL=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_ALBUM_1_INDEX: {
        _id=dataSource.getAlbumDao().insert2(uri, contentValues);
        _returnURL=Uri.withAppendedPath(uri, String.valueOf(_id));
        break;
      }
      case PATH_ARTIST_3_INDEX: {
        _id=dataSource.getArtistDao().insert2(uri, contentValues);
        _returnURL=Uri.withAppendedPath(uri, String.valueOf(_id));
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for INSERT operation: " + uri);
      }
    }
    Logger.info("Element is created with URI '%s'", _returnURL);
    Logger.info("Changes are notified for URI '%s'", uri);
    getContext().getContentResolver().notifyChange(uri, null);
    return _returnURL;
  }

  /**
   * method ArtistDao.update
   * method AlbumDao.update
   */
  @Override
  public int update(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    int returnRowUpdated=1;
    switch (sURIMatcher.match(uri)) {
      case PATH_ALBUM_2_INDEX: {
        // URI: content://com.abubusoft.kripton.example/albums/${bean.id}
        returnRowUpdated=dataSource.getAlbumDao().update3(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_ARTIST_4_INDEX: {
        // URI: content://com.abubusoft.kripton.example/artists/${bean.id}
        returnRowUpdated=dataSource.getArtistDao().update3(uri, contentValues, selection, selectionArgs);
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for UPDATE operation: " + uri);
      }
    }
    Logger.info("Changes are notified for URI %s", uri);
    getContext().getContentResolver().notifyChange(uri, null);
    return returnRowUpdated;
  }

  /**
   * method ArtistDao.selectById
   * method ArtistDao.selectAll
   * method AlbumDao.selectById
   * method AlbumDao.selectAll
   * method ArtistDao.selectById
   * method ArtistDao.selectAll
   * method AlbumDao.selectById
   * method AlbumDao.selectAll
   */
  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    Cursor returnCursor=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_ALBUM_1_INDEX: {
        // URI: content://com.abubusoft.kripton.example/albums
        returnCursor=dataSource.getAlbumDao().selectAll1(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_ALBUM_2_INDEX: {
        // URI: content://com.abubusoft.kripton.example/albums/${id}
        returnCursor=dataSource.getAlbumDao().selectById0(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_ARTIST_3_INDEX: {
        // URI: content://com.abubusoft.kripton.example/artists
        returnCursor=dataSource.getArtistDao().selectAll1(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_ARTIST_4_INDEX: {
        // URI: content://com.abubusoft.kripton.example/artists/${id}
        returnCursor=dataSource.getArtistDao().selectById0(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for SELECT operation: " + uri);
      }
    }
    return returnCursor;
  }

  /**
   * method ArtistDao.delete
   * method AlbumDao.delete
   */
  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    int returnRowDeleted=-1;
    switch (sURIMatcher.match(uri)) {
      case PATH_ALBUM_2_INDEX: {
        // URI: content://com.abubusoft.kripton.example/albums/${bean.id}
        returnRowDeleted=dataSource.getAlbumDao().delete4(uri, selection, selectionArgs);
        break;
      }
      case PATH_ARTIST_4_INDEX: {
        // URI: content://com.abubusoft.kripton.example/artists/${bean.id}
        returnRowDeleted=dataSource.getArtistDao().delete4(uri, selection, selectionArgs);
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for DELETE operation: " + uri);
      }
    }
    Logger.info("Changes are notified for URI %s", uri);
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
