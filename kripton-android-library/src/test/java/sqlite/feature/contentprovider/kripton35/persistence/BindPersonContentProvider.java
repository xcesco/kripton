/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.feature.contentprovider.kripton35.persistence;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;

// TODO: Auto-generated Javadoc
/**
 * <p>This is the content provider generated for {@link PersonDataSource}</p>
 *
 * <h2>Content provider authority:</h2>
 * <pre>sqlite.feature.contentprovider.kripton35</pre>
 *
 * <h2>Supported query operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/cities/person/${personId}</pre></td><td>{@link CityDAOImpl#selectCityFromPerson1}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons</pre></td><td>{@link PersonDAOImpl#selectAll10}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/${nameTemp}/test0</pre></td><td>{@link PersonDAOImpl#selectOne9}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/${data.name}/test1</pre></td><td>{@link PersonDAOImpl#selectOne11}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/test3</pre></td><td>{@link PersonDAOImpl#selectBean12}</td></tr>
 * </table>
 *
 * <h2>Supported insert operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/cities</pre></td><td>{@link CityDAOImpl#insertBean0}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons</pre></td><td>{@link PersonDAOImpl#insertBean0}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/${name}</pre></td><td>{@link PersonDAOImpl#insertName1}</td></tr>
 * </table>
 *
 * <h2>Supported update operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/${id}</pre></td><td>{@link PersonDAOImpl#updateRaw5}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/test1/${id}</pre></td><td>{@link PersonDAOImpl#updateRaw6}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/test2/${id}</pre></td><td>{@link PersonDAOImpl#updateRaw7}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/test3/${person.id}</pre></td><td>{@link PersonDAOImpl#updateBean8}</td></tr>
 * </table>
 *
 * <h2>Supported delete operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/${id}</pre></td><td>{@link PersonDAOImpl#deleteRaw2}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/test0/${id}</pre></td><td>{@link PersonDAOImpl#deleteRaw3}</td></tr>
 * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/test1/${bean.id}</pre></td><td>{@link PersonDAOImpl#deleteBean4}</td></tr>
 * </table>
 *
 */
public class BindPersonContentProvider extends ContentProvider {
  /**
   * <p>content provider's URI.</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35</pre>
   */
  public static final String URI = "content://sqlite.feature.contentprovider.kripton35";

  /** <p>datasource singleton</p>. */
  private static BindPersonDataSource dataSource;

  /** <p>Content provider authority</p>. */
  public static final String AUTHORITY = "sqlite.feature.contentprovider.kripton35";

  /** <p>URI matcher</p>. */
  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35/cities</pre>
   */
  private static final Uri URI_PATH_CITY_1 = Uri.parse(URI+"/cities");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35/cities/person/#</pre>
   */
  private static final Uri URI_PATH_CITY_2 = Uri.parse(URI+"/cities/person/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons</pre>
   */
  private static final Uri URI_PATH_PERSON_3 = Uri.parse(URI+"/persons");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/#</pre>
   */
  private static final Uri URI_PATH_PERSON_4 = Uri.parse(URI+"/persons/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/[*]</pre>
   */
  private static final Uri URI_PATH_PERSON_5 = Uri.parse(URI+"/persons/*");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/[*]/test0</pre>
   */
  private static final Uri URI_PATH_PERSON_6 = Uri.parse(URI+"/persons/*/test0");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/[*]/test1</pre>
   */
  private static final Uri URI_PATH_PERSON_7 = Uri.parse(URI+"/persons/*/test1");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/test0/#</pre>
   */
  private static final Uri URI_PATH_PERSON_8 = Uri.parse(URI+"/persons/test0/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/test1/#</pre>
   */
  private static final Uri URI_PATH_PERSON_9 = Uri.parse(URI+"/persons/test1/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/test2/#</pre>
   */
  private static final Uri URI_PATH_PERSON_10 = Uri.parse(URI+"/persons/test2/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/test3</pre>
   */
  private static final Uri URI_PATH_PERSON_11 = Uri.parse(URI+"/persons/test3");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/test3/#</pre>
   */
  private static final Uri URI_PATH_PERSON_12 = Uri.parse(URI+"/persons/test3/#");

  /** The Constant PATH_CITY_1. */
  static final String PATH_CITY_1 = "cities";

  /** The Constant PATH_CITY_2. */
  static final String PATH_CITY_2 = "cities/person/#";

  /** The Constant PATH_PERSON_3. */
  static final String PATH_PERSON_3 = "persons";

  /** The Constant PATH_PERSON_4. */
  static final String PATH_PERSON_4 = "persons/#";

  /** The Constant PATH_PERSON_5. */
  static final String PATH_PERSON_5 = "persons/*";

  /** The Constant PATH_PERSON_6. */
  static final String PATH_PERSON_6 = "persons/*/test0";

  /** The Constant PATH_PERSON_7. */
  static final String PATH_PERSON_7 = "persons/*/test1";

  /** The Constant PATH_PERSON_8. */
  static final String PATH_PERSON_8 = "persons/test0/#";

  /** The Constant PATH_PERSON_9. */
  static final String PATH_PERSON_9 = "persons/test1/#";

  /** The Constant PATH_PERSON_10. */
  static final String PATH_PERSON_10 = "persons/test2/#";

  /** The Constant PATH_PERSON_11. */
  static final String PATH_PERSON_11 = "persons/test3";

  /** The Constant PATH_PERSON_12. */
  static final String PATH_PERSON_12 = "persons/test3/#";

  /** The Constant PATH_CITY_1_INDEX. */
  static final int PATH_CITY_1_INDEX = 1;

  /** The Constant PATH_CITY_2_INDEX. */
  static final int PATH_CITY_2_INDEX = 2;

  /** The Constant PATH_PERSON_3_INDEX. */
  static final int PATH_PERSON_3_INDEX = 3;

  /** The Constant PATH_PERSON_4_INDEX. */
  static final int PATH_PERSON_4_INDEX = 4;

  /** The Constant PATH_PERSON_5_INDEX. */
  static final int PATH_PERSON_5_INDEX = 5;

  /** The Constant PATH_PERSON_6_INDEX. */
  static final int PATH_PERSON_6_INDEX = 6;

  /** The Constant PATH_PERSON_7_INDEX. */
  static final int PATH_PERSON_7_INDEX = 7;

  /** The Constant PATH_PERSON_8_INDEX. */
  static final int PATH_PERSON_8_INDEX = 8;

  /** The Constant PATH_PERSON_9_INDEX. */
  static final int PATH_PERSON_9_INDEX = 9;

  /** The Constant PATH_PERSON_10_INDEX. */
  static final int PATH_PERSON_10_INDEX = 10;

  /** The Constant PATH_PERSON_11_INDEX. */
  static final int PATH_PERSON_11_INDEX = 11;

  /** The Constant PATH_PERSON_12_INDEX. */
  static final int PATH_PERSON_12_INDEX = 12;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/cities</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/cities</pre>
   *
   * <p>Method associated to this URI is {@link CityDAOImpl#insertBean0}</p>
   */
  public static final Uri URI_CITY_INSERT_BEAN = URI_PATH_CITY_1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/cities/person/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/cities/person/${personId}</pre>
   *
   * <p>Method associated to this URI is {@link CityDAOImpl#selectCityFromPerson1}</p>
   */
  public static final Uri URI_CITY_SELECT_CITY_FROM_PERSON = URI_PATH_CITY_2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons</pre>
   *
   * <p>Method associated to this URI is {@link PersonDAOImpl#insertBean0}</p>
   */
  public static final Uri URI_PERSON_INSERT_BEAN = URI_PATH_PERSON_3;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons</pre>
   *
   * <p>Method associated to this URI is {@link PersonDAOImpl#selectAll10}</p>
   */
  public static final Uri URI_PERSON_SELECT_ALL = URI_PATH_PERSON_3;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/${id}</pre>
   *
   * <p>Method associated to this URI is {@link PersonDAOImpl#deleteRaw2}</p>
   */
  public static final Uri URI_PERSON_DELETE_RAW = URI_PATH_PERSON_4;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/${id}</pre>
   *
   * <p>Method associated to this URI is {@link PersonDAOImpl#updateRaw5}</p>
   */
  public static final Uri URI_PERSON_UPDATE_RAW = URI_PATH_PERSON_4;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/[*]</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/${name}</pre>
   *
   * <p>Method associated to this URI is {@link PersonDAOImpl#insertName1}</p>
   */
  public static final Uri URI_PERSON_INSERT_NAME = URI_PATH_PERSON_5;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/[*]/test0</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/${nameTemp}/test0</pre>
   *
   * <p>Method associated to this URI is {@link PersonDAOImpl#selectOne9}</p>
   */
  public static final Uri URI_PERSON_SELECT_ONE = URI_PATH_PERSON_6;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/test1/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/test1/${bean.id}</pre>
   *
   * <p>Method associated to this URI is {@link PersonDAOImpl#deleteBean4}</p>
   */
  public static final Uri URI_PERSON_DELETE_BEAN = URI_PATH_PERSON_9;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/test3</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/test3</pre>
   *
   * <p>Method associated to this URI is {@link PersonDAOImpl#selectBean12}</p>
   */
  public static final Uri URI_PERSON_SELECT_BEAN = URI_PATH_PERSON_11;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/test3/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.contentprovider.kripton35/persons/test3/${person.id}</pre>
   *
   * <p>Method associated to this URI is {@link PersonDAOImpl#updateBean8}</p>
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
   * @return true, if successful
   * @see android.content.ContentProvider#onCreate()
   */
  @Override
  public boolean onCreate() {
    if (KriptonLibrary.context()==null) {
      KriptonLibrary.init(getContext());
    }
    dataSource = BindPersonDataSource.instance();
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
   * <h2>Supported query operations</h2>
   * <table>
   * <tr><th>URI</th><th>DAO.METHOD</th></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/cities/person/${personId}</pre></td><td>{@link CityDAOImpl#selectCityFromPerson1}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons</pre></td><td>{@link PersonDAOImpl#selectAll10}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/${nameTemp}/test0</pre></td><td>{@link PersonDAOImpl#selectOne9}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/${data.name}/test1</pre></td><td>{@link PersonDAOImpl#selectOne11}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/test3</pre></td><td>{@link PersonDAOImpl#selectBean12}</td></tr>
   * </table>
   *
   * @param uri the uri
   * @param projection the projection
   * @param selection the selection
   * @param selectionArgs the selection args
   * @param sortOrder the sort order
   * @return the cursor
   */
  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    Cursor returnCursor=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_CITY_2_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35/cities/person/${personId}
        returnCursor=dataSource.getCityDAO().selectCityFromPerson1(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_PERSON_3_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35/persons
        returnCursor=dataSource.getPersonDAO().selectAll10(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_PERSON_6_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35/persons/${nameTemp}/test0
        returnCursor=dataSource.getPersonDAO().selectOne9(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_PERSON_7_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35/persons/${data.name}/test1
        returnCursor=dataSource.getPersonDAO().selectOne11(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_PERSON_11_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35/persons/test3
        returnCursor=dataSource.getPersonDAO().selectBean12(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for SELECT operation: " + uri);
      }
    }
    return returnCursor;
  }

  /**
   * <h2>Supported insert operations</h2>
   * <table>
   * <tr><th>URI</th><th>DAO.METHOD</th></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/cities</pre></td><td>{@link CityDAOImpl#insertBean0}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons</pre></td><td>{@link PersonDAOImpl#insertBean0}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/${name}</pre></td><td>{@link PersonDAOImpl#insertName1}</td></tr>
   * </table>
   *
   * @param uri the uri
   * @param contentValues the content values
   * @return the uri
   */
  @Override
  public Uri insert(Uri uri, ContentValues contentValues) {
    long _id=-1;
    Uri _returnURL=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_CITY_1_INDEX: {
        _id=dataSource.getCityDAO().insertBean0(uri, contentValues);
        _returnURL=Uri.withAppendedPath(uri, String.valueOf(_id));
        break;
      }
      case PATH_PERSON_3_INDEX: {
        _id=dataSource.getPersonDAO().insertBean0(uri, contentValues);
        _returnURL=Uri.withAppendedPath(uri, String.valueOf(_id));
        break;
      }
      case PATH_PERSON_5_INDEX: {
        _id=dataSource.getPersonDAO().insertName1(uri, contentValues);
        _returnURL=Uri.withAppendedPath(uri, String.valueOf(_id));
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for INSERT operation: " + uri);
      }
    }
    // log section BEGIN
    if (dataSource.isLogEnabled()) {
      Logger.info("Element is created with URI '%s'", _returnURL);
      Logger.info("Changes are notified for URI '%s'", uri);
    }
    // log section END
    getContext().getContentResolver().notifyChange(uri, null);
    return _returnURL;
  }

  /**
   * <h2>Supported update operations</h2>
   * <table>
   * <tr><th>URI</th><th>DAO.METHOD</th></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/${id}</pre></td><td>{@link PersonDAOImpl#updateRaw5}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/test1/${id}</pre></td><td>{@link PersonDAOImpl#updateRaw6}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/test2/${id}</pre></td><td>{@link PersonDAOImpl#updateRaw7}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/test3/${person.id}</pre></td><td>{@link PersonDAOImpl#updateBean8}</td></tr>
   * </table>
   *
   * @param uri the uri
   * @param contentValues the content values
   * @param selection the selection
   * @param selectionArgs the selection args
   * @return the int
   */
  @Override
  public int update(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    int returnRowUpdated=1;
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_4_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35/persons/${id}
        returnRowUpdated=dataSource.getPersonDAO().updateRaw5(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_9_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35/persons/test1/${id}
        returnRowUpdated=dataSource.getPersonDAO().updateRaw6(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_10_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35/persons/test2/${id}
        returnRowUpdated=dataSource.getPersonDAO().updateRaw7(uri, contentValues, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_12_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35/persons/test3/${person.id}
        returnRowUpdated=dataSource.getPersonDAO().updateBean8(uri, contentValues, selection, selectionArgs);
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
   * <h2>Supported delete operations</h2>
   * <table>
   * <tr><th>URI</th><th>DAO.METHOD</th></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/${id}</pre></td><td>{@link PersonDAOImpl#deleteRaw2}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/test0/${id}</pre></td><td>{@link PersonDAOImpl#deleteRaw3}</td></tr>
   * <tr><td><pre>content://sqlite.feature.contentprovider.kripton35/persons/test1/${bean.id}</pre></td><td>{@link PersonDAOImpl#deleteBean4}</td></tr>
   * </table>
   *
   * @param uri the uri
   * @param selection the selection
   * @param selectionArgs the selection args
   * @return the int
   */
  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    int returnRowDeleted=-1;
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_4_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35/persons/${id}
        returnRowDeleted=dataSource.getPersonDAO().deleteRaw2(uri, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_8_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35/persons/test0/${id}
        returnRowDeleted=dataSource.getPersonDAO().deleteRaw3(uri, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_9_INDEX: {
        // URI: content://sqlite.feature.contentprovider.kripton35/persons/test1/${bean.id}
        returnRowDeleted=dataSource.getPersonDAO().deleteBean4(uri, selection, selectionArgs);
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for DELETE operation: " + uri);
      }
    }
    // log section BEGIN
    if (dataSource.isLogEnabled()) {
      Logger.info("Changes are notified for URI %s", uri);
    }
    // log section END
    getContext().getContentResolver().notifyChange(uri, null);
    return returnRowDeleted;
  }

  /* (non-Javadoc)
   * @see android.content.ContentProvider#getType(android.net.Uri)
   */
  @Override
  public String getType(Uri uri) {
    switch (sURIMatcher.match(uri)) {
      case PATH_CITY_1_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.contentprovider.kripton35.city";
      }
      case PATH_CITY_2_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.contentprovider.kripton35.city";
      }
      case PATH_PERSON_3_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.contentprovider.kripton35.person";
      }
      case PATH_PERSON_4_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.contentprovider.kripton35.person";
      }
      case PATH_PERSON_5_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.contentprovider.kripton35.person";
      }
      case PATH_PERSON_6_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.contentprovider.kripton35.person";
      }
      case PATH_PERSON_7_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.contentprovider.kripton35.person";
      }
      case PATH_PERSON_8_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.contentprovider.kripton35.person";
      }
      case PATH_PERSON_9_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.contentprovider.kripton35.person";
      }
      case PATH_PERSON_10_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.contentprovider.kripton35.person";
      }
      case PATH_PERSON_11_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.contentprovider.kripton35.person";
      }
      case PATH_PERSON_12_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.contentprovider.kripton35.person";
      }
    }
    throw new IllegalArgumentException("Unknown URI for getType operation: " + uri);
  }
}
