package sqlite.feature.javadoc.update.raw;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;

/**
 * <p>This is the content provider generated for {@link UpdateRawPersonDataSource}</p>
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
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/${id}</pre></td><td>{@link UpdateRawPersonDaoImpl#updateBean2}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/${id}/more</pre></td><td>{@link UpdateRawPersonDaoImpl#updateBeanDynamic3}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/${id}/moreAndMore</pre></td><td>{@link UpdateRawPersonDaoImpl#updateBeanDynamicWithArgs4}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/jql/${surname}</pre></td><td>{@link UpdateRawPersonDaoImpl#updateAllBeansJQL0}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/jql/all/${surname}</pre></td><td>{@link UpdateRawPersonDaoImpl#updateFromSelectJQL1}</td></tr>
 * </table>
 */
public class BindUpdateRawPersonContentProvider extends ContentProvider {
  /**
   * <p>content provider's URI. Example:</p>
   * <pre>content://sqlite.contentprovider.kripton35</pre>
   */
  public static final String URI = "content://sqlite.feature.javadoc.bean";

  /**
   * <p>datasource singleton</p>
   */
  private static BindUpdateRawPersonDataSource dataSource;

  /**
   * <p>Content provider authority</p>
   */
  public static final String AUTHORITY = "sqlite.feature.javadoc.bean";

  /**
   * <p>URI matcher</p>
   */
  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

  public static final String PATH_PERSON_1 = "persons/#";

  public static final String PATH_PERSON_2 = "persons/#/more";

  public static final String PATH_PERSON_3 = "persons/#/moreAndMore";

  public static final String PATH_PERSON_4 = "persons/jql/*";

  public static final String PATH_PERSON_5 = "persons/jql/all/*";

  static final int PATH_PERSON_1_INDEX = 1;

  static final int PATH_PERSON_2_INDEX = 2;

  static final int PATH_PERSON_3_INDEX = 3;

  static final int PATH_PERSON_4_INDEX = 4;

  static final int PATH_PERSON_5_INDEX = 5;

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
    dataSource = BindUpdateRawPersonDataSource.instance();
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

  /**
   * method UpdateRawPersonDao.updateAllBeansJQL
   * method UpdateRawPersonDao.updateFromSelectJQL
   * method UpdateRawPersonDao.updateBean
   * method UpdateRawPersonDao.updateBeanDynamic
   * method UpdateRawPersonDao.updateBeanDynamicWithArgs
   */
  @Override
  public int update(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    int returnRowUpdated=1;
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_1_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/${id}
        returnRowUpdated=dataSource.getUpdateRawPersonDao().updateBean2(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_2_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/${id}/more
        returnRowUpdated=dataSource.getUpdateRawPersonDao().updateBeanDynamic3(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_3_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/${id}/moreAndMore
        returnRowUpdated=dataSource.getUpdateRawPersonDao().updateBeanDynamicWithArgs4(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_4_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/jql/${surname}
        returnRowUpdated=dataSource.getUpdateRawPersonDao().updateAllBeansJQL0(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_5_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/jql/all/${surname}
        returnRowUpdated=dataSource.getUpdateRawPersonDao().updateFromSelectJQL1(uri, contentValues, selection, selectionArgs);
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

  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    throw new IllegalArgumentException("Unknown URI for SELECT operation: " + uri);
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
    }
    throw new IllegalArgumentException("Unknown URI for getType operation: " + uri);
  }
}
