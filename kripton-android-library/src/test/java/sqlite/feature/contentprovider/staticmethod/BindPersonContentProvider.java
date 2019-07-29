package sqlite.feature.contentprovider.staticmethod;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;

/**
 * <p>This is the content provider generated for {@link PersonDataSource}</p>
 *
 * <h2>Content provider authority:</h2>
 * <pre>sqlite.feature.contentprovider.kripton35</pre>
 *
 * <h2>Supported query operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/${name}</pre></td><td>{@link PersonDAOImpl#customSelect0ForContentProvider}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/test3</pre></td><td>{@link PersonDAOImpl#selectBean4ForContentProvider}</td></tr>
 * </table>
 *
 * <h2>Supported insert operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/${name}</pre></td><td>{@link PersonDAOImpl#insertName1ForContentProvider}</td></tr>
 * </table>
 *
 * <h2>Supported update operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/${id}</pre></td><td>{@link PersonDAOImpl#updateRaw3ForContentProvider}</td></tr>
 * </table>
 *
 * <h2>Supported delete operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/${id}</pre></td><td>{@link PersonDAOImpl#deleteRaw2ForContentProvider}</td></tr>
 * </table>
 *
 *
 *
 */
public class BindPersonContentProvider extends ContentProvider {
  /**
   * <p>content provider's URI.</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35</pre>
   */
  public static final String URI = "content://sqlite.feature.contentprovider.kripton35";

  /**
   * <p>datasource singleton</p>
   */
  private static BindPersonDataSource dataSource;

  /**
   * <p>Content provider authority</p>
   */
  public static final String AUTHORITY = "sqlite.feature.contentprovider.kripton35";

  /**
   * <p>URI matcher</p>
   */
  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/#</pre>
   */
  private static final Uri URI_PATH_PERSON_1 = Uri.parse(URI+"/persons/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/[*]</pre>
   */
  private static final Uri URI_PATH_PERSON_2 = Uri.parse(URI+"/persons/*");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/test3</pre>
   */
  private static final Uri URI_PATH_PERSON_3 = Uri.parse(URI+"/persons/test3");

  static final String PATH_PERSON_1 = "persons/#";

  static final String PATH_PERSON_2 = "persons/*";

  static final String PATH_PERSON_3 = "persons/test3";

  static final int PATH_PERSON_1_INDEX = 1;

  static final int PATH_PERSON_2_INDEX = 2;

  static final int PATH_PERSON_3_INDEX = 3;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/${id}</pre>
   *
   * <p>Method associated to this URI is {@link PersonDAOImpl#deleteRaw2ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_DELETE_RAW = URI_PATH_PERSON_1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/${id}</pre>
   *
   * <p>Method associated to this URI is {@link PersonDAOImpl#updateRaw3ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_UPDATE_RAW = URI_PATH_PERSON_1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/[*]</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/${name}</pre>
   *
   * <p>Method associated to this URI is {@link PersonDAOImpl#insertName1ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_INSERT_NAME = URI_PATH_PERSON_2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/[*]</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/${name}</pre>
   *
   * <p>Method associated to this URI is {@link PersonDAOImpl#customSelect0ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_CUSTOM_SELECT = URI_PATH_PERSON_2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/test3</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/test3</pre>
   *
   * <p>Method associated to this URI is {@link PersonDAOImpl#selectBean4ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_SELECT_BEAN = URI_PATH_PERSON_3;

  static {
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_1, PATH_PERSON_1_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_2, PATH_PERSON_2_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_3, PATH_PERSON_3_INDEX);
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
    dataSource = BindPersonDataSource.getInstance();
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
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/${name}</pre></td><td>{@link PersonDAOImpl#customSelect0ForContentProvider}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/test3</pre></td><td>{@link PersonDAOImpl#selectBean4ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    Cursor returnCursor=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_2_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35/persons/${name}
        returnCursor=dataSource.getPersonDAO().customSelect0ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_PERSON_3_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35/persons/test3
        returnCursor=dataSource.getPersonDAO().selectBean4ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
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
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/${name}</pre></td><td>{@link PersonDAOImpl#insertName1ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public Uri insert(Uri uri, ContentValues contentValues) {
    long _id=-1;
    Uri _returnURL=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_2_INDEX: {
        _id=dataSource.getPersonDAO().insertName1ForContentProvider(uri, contentValues);
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
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/${id}</pre></td><td>{@link PersonDAOImpl#updateRaw3ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public int update(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    int returnRowUpdated=1;
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_1_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35/persons/${id}
        returnRowUpdated=dataSource.getPersonDAO().updateRaw3ForContentProvider(uri, contentValues, selection, selectionArgs);
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
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/${id}</pre></td><td>{@link PersonDAOImpl#deleteRaw2ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    int returnRowDeleted=-1;
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_1_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35/persons/${id}
        returnRowDeleted=dataSource.getPersonDAO().deleteRaw2ForContentProvider(uri, selection, selectionArgs);
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
        return "vnd.android.cursor.item/vnd.sqlite.feature.contentprovider.kripton35.person";
      }
      case PATH_PERSON_2_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.contentprovider.kripton35.person";
      }
      case PATH_PERSON_3_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.contentprovider.kripton35.person";
      }
    }
    throw new IllegalArgumentException("Unknown URI for getType operation: " + uri);
  }
}
