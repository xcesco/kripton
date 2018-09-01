package sqlite.feature.livedata.persistence3.repository;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;

/**
 * <p>This is the content provider generated for {@link AppDataSource}</p>
 *
 * <h2>Content provider authority:</h2>
 * <pre>com.abubsoft.kripton</pre>
 *
 * <h2>Supported query operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubsoft.kripton/persons</pre></td><td>{@link DaoPersonImpl#selectAll1ForContentProvider}</td></tr>
 * <tr><td><pre>content://com.abubsoft.kripton/persons/${name}</pre></td><td>{@link DaoPersonImpl#select0ForContentProvider}</td></tr>
 * </table>
 *
 * <h2>Supported insert operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubsoft.kripton/persons</pre></td><td>{@link DaoPersonImpl#insert2ForContentProvider}</td></tr>
 * </table>
 *
 * <h2>Supported update operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubsoft.kripton/persons/${bean.id}</pre></td><td>{@link DaoPersonImpl#update3ForContentProvider}</td></tr>
 * <tr><td><pre>content://com.abubsoft.kripton/persons/${id}/name</pre></td><td>{@link DaoPersonImpl#updateOnlyName4ForContentProvider}</td></tr>
 * </table>
 *
 * <h2>Supported delete operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubsoft.kripton/persons/${bean.id}</pre></td><td>{@link DaoPersonImpl#delete5ForContentProvider}</td></tr>
 * <tr><td><pre>content://com.abubsoft.kripton/persons/${name}/name</pre></td><td>{@link DaoPersonImpl#deleteByName6ForContentProvider}</td></tr>
 * </table>
 *
 *
 *
 */
public class BindAppContentProvider extends ContentProvider {
  /**
   * <p>content provider's URI.</p>
   * <pre>content://com.abubsoft.kripton</pre>
   */
  public static final String URI = "content://com.abubsoft.kripton";

  /**
   * <p>datasource singleton</p>
   */
  private static BindAppDataSource dataSource;

  /**
   * <p>Content provider authority</p>
   */
  public static final String AUTHORITY = "com.abubsoft.kripton";

  /**
   * <p>URI matcher</p>
   */
  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

  /**
   * <p>Uri</p>
   * <pre>content://com.abubsoft.kripton/persons</pre>
   */
  private static final Uri URI_PATH_PERSON_1 = Uri.parse(URI+"/persons");

  /**
   * <p>Uri</p>
   * <pre>content://com.abubsoft.kripton/persons/#</pre>
   */
  private static final Uri URI_PATH_PERSON_2 = Uri.parse(URI+"/persons/#");

  /**
   * <p>Uri</p>
   * <pre>content://com.abubsoft.kripton/persons/#/name</pre>
   */
  private static final Uri URI_PATH_PERSON_3 = Uri.parse(URI+"/persons/#/name");

  /**
   * <p>Uri</p>
   * <pre>content://com.abubsoft.kripton/persons/[*]</pre>
   */
  private static final Uri URI_PATH_PERSON_4 = Uri.parse(URI+"/persons/*");

  /**
   * <p>Uri</p>
   * <pre>content://com.abubsoft.kripton/persons/[*]/name</pre>
   */
  private static final Uri URI_PATH_PERSON_5 = Uri.parse(URI+"/persons/*/name");

  static final String PATH_PERSON_1 = "persons";

  static final String PATH_PERSON_2 = "persons/#";

  static final String PATH_PERSON_3 = "persons/#/name";

  static final String PATH_PERSON_4 = "persons/*";

  static final String PATH_PERSON_5 = "persons/*/name";

  static final int PATH_PERSON_1_INDEX = 1;

  static final int PATH_PERSON_2_INDEX = 2;

  static final int PATH_PERSON_3_INDEX = 3;

  static final int PATH_PERSON_4_INDEX = 4;

  static final int PATH_PERSON_5_INDEX = 5;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubsoft.kripton/persons</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubsoft.kripton/persons</pre>
   *
   * <p>Method associated to this URI is {@link DaoPersonImpl#insert2ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_INSERT = URI_PATH_PERSON_1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubsoft.kripton/persons</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubsoft.kripton/persons</pre>
   *
   * <p>Method associated to this URI is {@link DaoPersonImpl#selectAll1ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_SELECT_ALL = URI_PATH_PERSON_1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubsoft.kripton/persons/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubsoft.kripton/persons/${bean.id}</pre>
   *
   * <p>Method associated to this URI is {@link DaoPersonImpl#delete5ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_DELETE = URI_PATH_PERSON_2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubsoft.kripton/persons/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubsoft.kripton/persons/${bean.id}</pre>
   *
   * <p>Method associated to this URI is {@link DaoPersonImpl#update3ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_UPDATE = URI_PATH_PERSON_2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubsoft.kripton/persons/#/name</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubsoft.kripton/persons/${id}/name</pre>
   *
   * <p>Method associated to this URI is {@link DaoPersonImpl#updateOnlyName4ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_UPDATE_ONLY_NAME = URI_PATH_PERSON_3;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubsoft.kripton/persons/[*]</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubsoft.kripton/persons/${name}</pre>
   *
   * <p>Method associated to this URI is {@link DaoPersonImpl#select0ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_SELECT = URI_PATH_PERSON_4;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubsoft.kripton/persons/[*]/name</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubsoft.kripton/persons/${name}/name</pre>
   *
   * <p>Method associated to this URI is {@link DaoPersonImpl#deleteByName6ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_DELETE_BY_NAME = URI_PATH_PERSON_5;

  static {
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_1, PATH_PERSON_1_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_2, PATH_PERSON_2_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_3, PATH_PERSON_3_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_4, PATH_PERSON_4_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_5, PATH_PERSON_5_INDEX);
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
   * <tr><td><pre>content://com.abubsoft.kripton/persons</pre></td><td>{@link DaoPersonImpl#selectAll1ForContentProvider}</td></tr>
   * <tr><td><pre>content://com.abubsoft.kripton/persons/${name}</pre></td><td>{@link DaoPersonImpl#select0ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    Cursor returnCursor=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_1_INDEX: {
        // URI: content://com.abubsoft.kripton/persons
        returnCursor=dataSource.getDaoPerson().selectAll1ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_PERSON_4_INDEX: {
        // URI: content://com.abubsoft.kripton/persons/${name}
        returnCursor=dataSource.getDaoPerson().select0ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
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
   * <tr><td><pre>content://com.abubsoft.kripton/persons</pre></td><td>{@link DaoPersonImpl#insert2ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public Uri insert(Uri uri, ContentValues contentValues) {
    long _id=-1;
    Uri _returnURL=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_1_INDEX: {
        _id=dataSource.getDaoPerson().insert2ForContentProvider(uri, contentValues);
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
   * <tr><td><pre>content://com.abubsoft.kripton/persons/${bean.id}</pre></td><td>{@link DaoPersonImpl#update3ForContentProvider}</td></tr>
   * <tr><td><pre>content://com.abubsoft.kripton/persons/${id}/name</pre></td><td>{@link DaoPersonImpl#updateOnlyName4ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public int update(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    int returnRowUpdated=1;
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_2_INDEX: {
        // URI: content://com.abubsoft.kripton/persons/${bean.id}
        returnRowUpdated=dataSource.getDaoPerson().update3ForContentProvider(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_3_INDEX: {
        // URI: content://com.abubsoft.kripton/persons/${id}/name
        returnRowUpdated=dataSource.getDaoPerson().updateOnlyName4ForContentProvider(uri, contentValues, selection, selectionArgs);
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
   * <tr><td><pre>content://com.abubsoft.kripton/persons/${bean.id}</pre></td><td>{@link DaoPersonImpl#delete5ForContentProvider}</td></tr>
   * <tr><td><pre>content://com.abubsoft.kripton/persons/${name}/name</pre></td><td>{@link DaoPersonImpl#deleteByName6ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    int returnRowDeleted=-1;
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_2_INDEX: {
        // URI: content://com.abubsoft.kripton/persons/${bean.id}
        returnRowDeleted=dataSource.getDaoPerson().delete5ForContentProvider(uri, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_5_INDEX: {
        // URI: content://com.abubsoft.kripton/persons/${name}/name
        returnRowDeleted=dataSource.getDaoPerson().deleteByName6ForContentProvider(uri, selection, selectionArgs);
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
      case PATH_PERSON_1_INDEX: {
        return "vnd.android.cursor.dir/vnd.com.abubsoft.kripton.person";
      }
      case PATH_PERSON_2_INDEX: {
        return "vnd.android.cursor.item/vnd.com.abubsoft.kripton.person";
      }
      case PATH_PERSON_3_INDEX: {
        return "vnd.android.cursor.item/vnd.com.abubsoft.kripton.person";
      }
      case PATH_PERSON_4_INDEX: {
        return "vnd.android.cursor.dir/vnd.com.abubsoft.kripton.person";
      }
      case PATH_PERSON_5_INDEX: {
        return "vnd.android.cursor.item/vnd.com.abubsoft.kripton.person";
      }
    }
    throw new IllegalArgumentException("Unknown URI for getType operation: " + uri);
  }
}
