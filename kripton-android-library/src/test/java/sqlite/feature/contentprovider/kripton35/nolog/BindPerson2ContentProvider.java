package sqlite.feature.contentprovider.kripton35.nolog;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.KriptonLibrary;

/**
 * <p>This is the content provider generated for {@link Person2DataSource}</p>
 *
 * <h2>Content provider authority:</h2>
 * <pre>sqlite.feature.contentprovider.kripton35.nolog</pre>
 *
 * <h2>Supported query operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/cities/person/${personId}</pre></td><td>{@link City2DAOImpl#selectCityFromPerson1ForContentProvider}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons</pre></td><td>{@link Person2DAOImpl#selectAll10ForContentProvider}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/${nameTemp}/test0</pre></td><td>{@link Person2DAOImpl#selectOne9ForContentProvider}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/${data.name}/test1</pre></td><td>{@link Person2DAOImpl#selectOne11ForContentProvider}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test3</pre></td><td>{@link Person2DAOImpl#selectBean12ForContentProvider}</td></tr>
 * </table>
 *
 * <h2>Supported insert operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/cities</pre></td><td>{@link City2DAOImpl#insertBean0ForContentProvider}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons</pre></td><td>{@link Person2DAOImpl#insertBean0ForContentProvider}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/${name}</pre></td><td>{@link Person2DAOImpl#insertName1ForContentProvider}</td></tr>
 * </table>
 *
 * <h2>Supported update operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/${id}</pre></td><td>{@link Person2DAOImpl#updateRaw5ForContentProvider}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test1/${id}</pre></td><td>{@link Person2DAOImpl#updateRaw6ForContentProvider}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test2/${id}</pre></td><td>{@link Person2DAOImpl#updateRaw7ForContentProvider}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test3/${person.id}</pre></td><td>{@link Person2DAOImpl#updateBean8ForContentProvider}</td></tr>
 * </table>
 *
 * <h2>Supported delete operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/${id}</pre></td><td>{@link Person2DAOImpl#deleteRaw2ForContentProvider}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test0/${id}</pre></td><td>{@link Person2DAOImpl#deleteRaw3ForContentProvider}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test1/${bean.id}</pre></td><td>{@link Person2DAOImpl#deleteBean4ForContentProvider}</td></tr>
 * </table>
 *
 */
public class BindPerson2ContentProvider extends ContentProvider {
  /**
   * <p>content provider's URI.</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog</pre>
   */
  public static final String URI = "content://sqlite.feature.contentprovider.kripton35.nolog";

  /**
   * <p>datasource singleton</p>
   */
  private static BindPerson2DataSource dataSource;

  /**
   * <p>Content provider authority</p>
   */
  public static final String AUTHORITY = "sqlite.feature.contentprovider.kripton35.nolog";

  /**
   * <p>URI matcher</p>
   */
  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/cities</pre>
   */
  private static final Uri URI_PATH_CITY_1 = Uri.parse(URI+"/cities");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/cities/person/#</pre>
   */
  private static final Uri URI_PATH_CITY_2 = Uri.parse(URI+"/cities/person/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons</pre>
   */
  private static final Uri URI_PATH_PERSON_3 = Uri.parse(URI+"/persons");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/#</pre>
   */
  private static final Uri URI_PATH_PERSON_4 = Uri.parse(URI+"/persons/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/[*]</pre>
   */
  private static final Uri URI_PATH_PERSON_5 = Uri.parse(URI+"/persons/*");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/[*]/test0</pre>
   */
  private static final Uri URI_PATH_PERSON_6 = Uri.parse(URI+"/persons/*/test0");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/[*]/test1</pre>
   */
  private static final Uri URI_PATH_PERSON_7 = Uri.parse(URI+"/persons/*/test1");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test0/#</pre>
   */
  private static final Uri URI_PATH_PERSON_8 = Uri.parse(URI+"/persons/test0/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test1/#</pre>
   */
  private static final Uri URI_PATH_PERSON_9 = Uri.parse(URI+"/persons/test1/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test2/#</pre>
   */
  private static final Uri URI_PATH_PERSON_10 = Uri.parse(URI+"/persons/test2/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test3</pre>
   */
  private static final Uri URI_PATH_PERSON_11 = Uri.parse(URI+"/persons/test3");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test3/#</pre>
   */
  private static final Uri URI_PATH_PERSON_12 = Uri.parse(URI+"/persons/test3/#");

  static final String PATH_CITY_1 = "cities";

  static final String PATH_CITY_2 = "cities/person/#";

  static final String PATH_PERSON_3 = "persons";

  static final String PATH_PERSON_4 = "persons/#";

  static final String PATH_PERSON_5 = "persons/*";

  static final String PATH_PERSON_6 = "persons/*/test0";

  static final String PATH_PERSON_7 = "persons/*/test1";

  static final String PATH_PERSON_8 = "persons/test0/#";

  static final String PATH_PERSON_9 = "persons/test1/#";

  static final String PATH_PERSON_10 = "persons/test2/#";

  static final String PATH_PERSON_11 = "persons/test3";

  static final String PATH_PERSON_12 = "persons/test3/#";

  static final int PATH_CITY_1_INDEX = 1;

  static final int PATH_CITY_2_INDEX = 2;

  static final int PATH_PERSON_3_INDEX = 3;

  static final int PATH_PERSON_4_INDEX = 4;

  static final int PATH_PERSON_5_INDEX = 5;

  static final int PATH_PERSON_6_INDEX = 6;

  static final int PATH_PERSON_7_INDEX = 7;

  static final int PATH_PERSON_8_INDEX = 8;

  static final int PATH_PERSON_9_INDEX = 9;

  static final int PATH_PERSON_10_INDEX = 10;

  static final int PATH_PERSON_11_INDEX = 11;

  static final int PATH_PERSON_12_INDEX = 12;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/cities</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/cities</pre>
   *
   * <p>Method associated to this URI is {@link City2DAOImpl#insertBean0ForContentProvider}</p>
   */
  public static final Uri URI_CITY_INSERT_BEAN = URI_PATH_CITY_1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/cities/person/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/cities/person/${personId}</pre>
   *
   * <p>Method associated to this URI is {@link City2DAOImpl#selectCityFromPerson1ForContentProvider}</p>
   */
  public static final Uri URI_CITY_SELECT_CITY_FROM_PERSON = URI_PATH_CITY_2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons</pre>
   *
   * <p>Method associated to this URI is {@link Person2DAOImpl#insertBean0ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_INSERT_BEAN = URI_PATH_PERSON_3;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons</pre>
   *
   * <p>Method associated to this URI is {@link Person2DAOImpl#selectAll10ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_SELECT_ALL = URI_PATH_PERSON_3;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/${id}</pre>
   *
   * <p>Method associated to this URI is {@link Person2DAOImpl#deleteRaw2ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_DELETE_RAW = URI_PATH_PERSON_4;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/${id}</pre>
   *
   * <p>Method associated to this URI is {@link Person2DAOImpl#updateRaw5ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_UPDATE_RAW = URI_PATH_PERSON_4;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/[*]</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/${name}</pre>
   *
   * <p>Method associated to this URI is {@link Person2DAOImpl#insertName1ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_INSERT_NAME = URI_PATH_PERSON_5;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/[*]/test0</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/${nameTemp}/test0</pre>
   *
   * <p>Method associated to this URI is {@link Person2DAOImpl#selectOne9ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_SELECT_ONE = URI_PATH_PERSON_6;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test1/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test1/${bean.id}</pre>
   *
   * <p>Method associated to this URI is {@link Person2DAOImpl#deleteBean4ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_DELETE_BEAN = URI_PATH_PERSON_9;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test3</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test3</pre>
   *
   * <p>Method associated to this URI is {@link Person2DAOImpl#selectBean12ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_SELECT_BEAN = URI_PATH_PERSON_11;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test3/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test3/${person.id}</pre>
   *
   * <p>Method associated to this URI is {@link Person2DAOImpl#updateBean8ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_UPDATE_BEAN = URI_PATH_PERSON_12;

  static {
    sURIMatcher.addURI(AUTHORITY, PATH_CITY_1, PATH_CITY_1_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_CITY_2, PATH_CITY_2_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_3, PATH_PERSON_3_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_4, PATH_PERSON_4_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_5, PATH_PERSON_5_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_6, PATH_PERSON_6_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_7, PATH_PERSON_7_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_8, PATH_PERSON_8_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_9, PATH_PERSON_9_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_10, PATH_PERSON_10_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_11, PATH_PERSON_11_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_12, PATH_PERSON_12_INDEX);
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
    dataSource = BindPerson2DataSource.getInstance();
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
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/cities/person/${personId}</pre></td><td>{@link City2DAOImpl#selectCityFromPerson1ForContentProvider}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons</pre></td><td>{@link Person2DAOImpl#selectAll10ForContentProvider}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/${nameTemp}/test0</pre></td><td>{@link Person2DAOImpl#selectOne9ForContentProvider}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/${data.name}/test1</pre></td><td>{@link Person2DAOImpl#selectOne11ForContentProvider}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test3</pre></td><td>{@link Person2DAOImpl#selectBean12ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    Cursor returnCursor=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_CITY_2_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35.nolog/cities/person/${personId}
        returnCursor=dataSource.getCity2DAO().selectCityFromPerson1ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_PERSON_3_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35.nolog/persons
        returnCursor=dataSource.getPerson2DAO().selectAll10ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_PERSON_6_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35.nolog/persons/${nameTemp}/test0
        returnCursor=dataSource.getPerson2DAO().selectOne9ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_PERSON_7_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35.nolog/persons/${data.name}/test1
        returnCursor=dataSource.getPerson2DAO().selectOne11ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_PERSON_11_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35.nolog/persons/test3
        returnCursor=dataSource.getPerson2DAO().selectBean12ForContentProvider(uri, projection, selection, selectionArgs, sortOrder);
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
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/cities</pre></td><td>{@link City2DAOImpl#insertBean0ForContentProvider}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons</pre></td><td>{@link Person2DAOImpl#insertBean0ForContentProvider}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/${name}</pre></td><td>{@link Person2DAOImpl#insertName1ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public Uri insert(Uri uri, ContentValues contentValues) {
    long _id=-1;
    Uri _returnURL=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_CITY_1_INDEX: {
        _id=dataSource.getCity2DAO().insertBean0ForContentProvider(uri, contentValues);
        _returnURL=Uri.withAppendedPath(uri, String.valueOf(_id));
        break;
      }
      case PATH_PERSON_3_INDEX: {
        _id=dataSource.getPerson2DAO().insertBean0ForContentProvider(uri, contentValues);
        _returnURL=Uri.withAppendedPath(uri, String.valueOf(_id));
        break;
      }
      case PATH_PERSON_5_INDEX: {
        _id=dataSource.getPerson2DAO().insertName1ForContentProvider(uri, contentValues);
        _returnURL=Uri.withAppendedPath(uri, String.valueOf(_id));
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for INSERT operation: " + uri);
      }
    }
    getContext().getContentResolver().notifyChange(uri, null);
    return _returnURL;
  }

  /**
   *
   * <h2>Supported update operations</h2>
   * <table>
   * <tr><th>URI</th><th>DAO.METHOD</th></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/${id}</pre></td><td>{@link Person2DAOImpl#updateRaw5ForContentProvider}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test1/${id}</pre></td><td>{@link Person2DAOImpl#updateRaw6ForContentProvider}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test2/${id}</pre></td><td>{@link Person2DAOImpl#updateRaw7ForContentProvider}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test3/${person.id}</pre></td><td>{@link Person2DAOImpl#updateBean8ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public int update(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    int returnRowUpdated=1;
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_4_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35.nolog/persons/${id}
        returnRowUpdated=dataSource.getPerson2DAO().updateRaw5ForContentProvider(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_9_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35.nolog/persons/test1/${id}
        returnRowUpdated=dataSource.getPerson2DAO().updateRaw6ForContentProvider(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_10_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35.nolog/persons/test2/${id}
        returnRowUpdated=dataSource.getPerson2DAO().updateRaw7ForContentProvider(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_12_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35.nolog/persons/test3/${person.id}
        returnRowUpdated=dataSource.getPerson2DAO().updateBean8ForContentProvider(uri, contentValues, selection, selectionArgs);
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for UPDATE operation: " + uri);
      }
    }
    getContext().getContentResolver().notifyChange(uri, null);
    return returnRowUpdated;
  }

  /**
   *
   * <h2>Supported delete operations</h2>
   * <table>
   * <tr><th>URI</th><th>DAO.METHOD</th></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/${id}</pre></td><td>{@link Person2DAOImpl#deleteRaw2ForContentProvider}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test0/${id}</pre></td><td>{@link Person2DAOImpl#deleteRaw3ForContentProvider}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35.nolog/persons/test1/${bean.id}</pre></td><td>{@link Person2DAOImpl#deleteBean4ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    int returnRowDeleted=-1;
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_4_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35.nolog/persons/${id}
        returnRowDeleted=dataSource.getPerson2DAO().deleteRaw2ForContentProvider(uri, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_8_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35.nolog/persons/test0/${id}
        returnRowDeleted=dataSource.getPerson2DAO().deleteRaw3ForContentProvider(uri, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_9_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35.nolog/persons/test1/${bean.id}
        returnRowDeleted=dataSource.getPerson2DAO().deleteBean4ForContentProvider(uri, selection, selectionArgs);
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for DELETE operation: " + uri);
      }
    }
    getContext().getContentResolver().notifyChange(uri, null);
    return returnRowDeleted;
  }

  @Override
  public String getType(Uri uri) {
    switch (sURIMatcher.match(uri)) {
      case PATH_CITY_1_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.contentprovider.kripton35.nolog.city";
      }
      case PATH_CITY_2_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.contentprovider.kripton35.nolog.city";
      }
      case PATH_PERSON_3_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.contentprovider.kripton35.nolog.person";
      }
      case PATH_PERSON_4_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.contentprovider.kripton35.nolog.person";
      }
      case PATH_PERSON_5_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.contentprovider.kripton35.nolog.person";
      }
      case PATH_PERSON_6_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.contentprovider.kripton35.nolog.person";
      }
      case PATH_PERSON_7_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.contentprovider.kripton35.nolog.person";
      }
      case PATH_PERSON_8_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.contentprovider.kripton35.nolog.person";
      }
      case PATH_PERSON_9_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.contentprovider.kripton35.nolog.person";
      }
      case PATH_PERSON_10_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.contentprovider.kripton35.nolog.person";
      }
      case PATH_PERSON_11_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.contentprovider.kripton35.nolog.person";
      }
      case PATH_PERSON_12_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.contentprovider.kripton35.nolog.person";
      }
    }
    throw new IllegalArgumentException("Unknown URI for getType operation: " + uri);
  }
}
