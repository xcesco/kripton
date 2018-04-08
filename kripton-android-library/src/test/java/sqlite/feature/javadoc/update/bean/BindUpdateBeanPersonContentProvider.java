package sqlite.feature.javadoc.update.bean;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;

/**
 * <p>This is the content provider generated for {@link UpdateBeanPersonDataSource}</p>
 *
 * <h2>Content provider authority:</h2>
 * <pre>sqlite.feature.javadoc.bean</pre>
 *
 * <h2>Supported insert operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * </table>
 *
 * <h2>Supported update operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons</pre></td><td>{@link UpdateBeanPersonDaoImpl#updateAllBeans0}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/${bean.id}</pre></td><td>{@link UpdateBeanPersonDaoImpl#updateOneBean1}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/${bean.id}/more</pre></td><td>{@link UpdateBeanPersonDaoImpl#updateBeanDynamic6}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/${bean.id}/moreAndMore</pre></td><td>{@link UpdateBeanPersonDaoImpl#updateBeanDynamicWithArgs7}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/dynamic/${bean.id}</pre></td><td>{@link UpdateBeanPersonDaoImpl#updateOneBeanWithDynamic2}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/dynamicArgs/${bean.id}</pre></td><td>{@link UpdateBeanPersonDaoImpl#updateOneBeanWithDynamicAndArgs3}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/jql</pre></td><td>{@link UpdateBeanPersonDaoImpl#updateAllBeansJQL4}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/jql/one/b/${bean.id}</pre></td><td>{@link UpdateBeanPersonDaoImpl#updateFromSelectJQL5}</td></tr>
 * </table>
 *
 */
public class BindUpdateBeanPersonContentProvider extends ContentProvider {
  /**
   * <p>content provider's URI.</p>
   * <pre>content://sqlite.feature.javadoc.bean</pre>
   */
  public static final String URI = "content://sqlite.feature.javadoc.bean";

  /**
   * <p>datasource singleton</p>
   */
  private static BindUpdateBeanPersonDataSource dataSource;

  /**
   * <p>Content provider authority</p>
   */
  public static final String AUTHORITY = "sqlite.feature.javadoc.bean";

  /**
   * <p>URI matcher</p>
   */
  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons</pre>
   */
  private static final Uri URI_PATH_PERSON_1 = Uri.parse(URI+"/persons");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#</pre>
   */
  private static final Uri URI_PATH_PERSON_2 = Uri.parse(URI+"/persons/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#/more</pre>
   */
  private static final Uri URI_PATH_PERSON_3 = Uri.parse(URI+"/persons/#/more");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#/moreAndMore</pre>
   */
  private static final Uri URI_PATH_PERSON_4 = Uri.parse(URI+"/persons/#/moreAndMore");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamic/#</pre>
   */
  private static final Uri URI_PATH_PERSON_5 = Uri.parse(URI+"/persons/dynamic/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamicArgs/#</pre>
   */
  private static final Uri URI_PATH_PERSON_6 = Uri.parse(URI+"/persons/dynamicArgs/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/jql</pre>
   */
  private static final Uri URI_PATH_PERSON_7 = Uri.parse(URI+"/persons/jql");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/jql/one/b/#</pre>
   */
  private static final Uri URI_PATH_PERSON_8 = Uri.parse(URI+"/persons/jql/one/b/#");

  static final String PATH_PERSON_1 = "persons";

  static final String PATH_PERSON_2 = "persons/#";

  static final String PATH_PERSON_3 = "persons/#/more";

  static final String PATH_PERSON_4 = "persons/#/moreAndMore";

  static final String PATH_PERSON_5 = "persons/dynamic/#";

  static final String PATH_PERSON_6 = "persons/dynamicArgs/#";

  static final String PATH_PERSON_7 = "persons/jql";

  static final String PATH_PERSON_8 = "persons/jql/one/b/#";

  static final int PATH_PERSON_1_INDEX = 1;

  static final int PATH_PERSON_2_INDEX = 2;

  static final int PATH_PERSON_3_INDEX = 3;

  static final int PATH_PERSON_4_INDEX = 4;

  static final int PATH_PERSON_5_INDEX = 5;

  static final int PATH_PERSON_6_INDEX = 6;

  static final int PATH_PERSON_7_INDEX = 7;

  static final int PATH_PERSON_8_INDEX = 8;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons</pre>
   *
   * <p>Method associated to this URI is {@link UpdateBeanPersonDaoImpl#updateAllBeans0}</p>
   */
  public static final Uri URI_PERSON_UPDATE_ALL_BEANS = URI_PATH_PERSON_1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/${bean.id}</pre>
   *
   * <p>Method associated to this URI is {@link UpdateBeanPersonDaoImpl#updateOneBean1}</p>
   */
  public static final Uri URI_PERSON_UPDATE_ONE_BEAN = URI_PATH_PERSON_2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#/more</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/${bean.id}/more</pre>
   *
   * <p>Method associated to this URI is {@link UpdateBeanPersonDaoImpl#updateBeanDynamic6}</p>
   */
  public static final Uri URI_PERSON_UPDATE_BEAN_DYNAMIC = URI_PATH_PERSON_3;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#/moreAndMore</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/${bean.id}/moreAndMore</pre>
   *
   * <p>Method associated to this URI is {@link UpdateBeanPersonDaoImpl#updateBeanDynamicWithArgs7}</p>
   */
  public static final Uri URI_PERSON_UPDATE_BEAN_DYNAMIC_WITH_ARGS = URI_PATH_PERSON_4;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamic/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamic/${bean.id}</pre>
   *
   * <p>Method associated to this URI is {@link UpdateBeanPersonDaoImpl#updateOneBeanWithDynamic2}</p>
   */
  public static final Uri URI_PERSON_UPDATE_ONE_BEAN_WITH_DYNAMIC = URI_PATH_PERSON_5;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamicArgs/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamicArgs/${bean.id}</pre>
   *
   * <p>Method associated to this URI is {@link UpdateBeanPersonDaoImpl#updateOneBeanWithDynamicAndArgs3}</p>
   */
  public static final Uri URI_PERSON_UPDATE_ONE_BEAN_WITH_DYNAMIC_AND_ARGS = URI_PATH_PERSON_6;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/jql</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/jql</pre>
   *
   * <p>Method associated to this URI is {@link UpdateBeanPersonDaoImpl#updateAllBeansJQL4}</p>
   */
  public static final Uri URI_PERSON_UPDATE_ALL_BEANS_J_Q_L = URI_PATH_PERSON_7;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/jql/one/b/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/jql/one/b/${bean.id}</pre>
   *
   * <p>Method associated to this URI is {@link UpdateBeanPersonDaoImpl#updateFromSelectJQL5}</p>
   */
  public static final Uri URI_PERSON_UPDATE_FROM_SELECT_J_Q_L = URI_PATH_PERSON_8;

  static {
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_1, PATH_PERSON_1_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_2, PATH_PERSON_2_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_3, PATH_PERSON_3_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_4, PATH_PERSON_4_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_5, PATH_PERSON_5_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_6, PATH_PERSON_6_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_7, PATH_PERSON_7_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_8, PATH_PERSON_8_INDEX);
  }

  /**
   * <p>Create datasource and open database in read mode.</p>
   *
   * @see android.content.ContentProvider#onCreate()
   */
  @Override
  public boolean onCreate() {
    if (KriptonLibrary.context()==null) {
      KriptonLibrary.init(getContext());
    }
    dataSource = BindUpdateBeanPersonDataSource.instance();
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
   * </table>
   *
   */
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

  /**
   *
   * <h2>Supported update operations</h2>
   * <table>
   * <tr><th>URI</th><th>DAO.METHOD</th></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons</pre></td><td>{@link UpdateBeanPersonDaoImpl#updateAllBeans0}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/${bean.id}</pre></td><td>{@link UpdateBeanPersonDaoImpl#updateOneBean1}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/${bean.id}/more</pre></td><td>{@link UpdateBeanPersonDaoImpl#updateBeanDynamic6}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/${bean.id}/moreAndMore</pre></td><td>{@link UpdateBeanPersonDaoImpl#updateBeanDynamicWithArgs7}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/dynamic/${bean.id}</pre></td><td>{@link UpdateBeanPersonDaoImpl#updateOneBeanWithDynamic2}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/dynamicArgs/${bean.id}</pre></td><td>{@link UpdateBeanPersonDaoImpl#updateOneBeanWithDynamicAndArgs3}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/jql</pre></td><td>{@link UpdateBeanPersonDaoImpl#updateAllBeansJQL4}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/jql/one/b/${bean.id}</pre></td><td>{@link UpdateBeanPersonDaoImpl#updateFromSelectJQL5}</td></tr>
   * </table>
   *
   */
  @Override
  public int update(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    int returnRowUpdated=1;
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_1_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons
        returnRowUpdated=dataSource.getUpdateBeanPersonDao().updateAllBeans0(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_2_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/${bean.id}
        returnRowUpdated=dataSource.getUpdateBeanPersonDao().updateOneBean1(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_3_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/${bean.id}/more
        returnRowUpdated=dataSource.getUpdateBeanPersonDao().updateBeanDynamic6(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_4_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/${bean.id}/moreAndMore
        returnRowUpdated=dataSource.getUpdateBeanPersonDao().updateBeanDynamicWithArgs7(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_5_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/dynamic/${bean.id}
        returnRowUpdated=dataSource.getUpdateBeanPersonDao().updateOneBeanWithDynamic2(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_6_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/dynamicArgs/${bean.id}
        returnRowUpdated=dataSource.getUpdateBeanPersonDao().updateOneBeanWithDynamicAndArgs3(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_7_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/jql
        returnRowUpdated=dataSource.getUpdateBeanPersonDao().updateAllBeansJQL4(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_8_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/jql/one/b/${bean.id}
        returnRowUpdated=dataSource.getUpdateBeanPersonDao().updateFromSelectJQL5(uri, contentValues, selection, selectionArgs);
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
      case PATH_PERSON_1_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_2_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_3_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_4_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_5_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_6_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_7_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_8_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.javadoc.bean.person";
      }
    }
    throw new IllegalArgumentException("Unknown URI for getType operation: " + uri);
  }
}
