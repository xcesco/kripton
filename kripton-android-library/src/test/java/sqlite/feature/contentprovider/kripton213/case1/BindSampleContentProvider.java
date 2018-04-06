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
package sqlite.feature.contentprovider.kripton213.case1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;

// TODO: Auto-generated Javadoc
/**
 * <p>This is the content provider generated for {@link SampleDataSource}</p>
 *
 * <h2>Content provider authority:</h2>
 * <pre>com.abubusoft.contentprovidersample.provider</pre>
 *
 * <h2>Supported query operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese</pre></td><td>{@link CheeseDaoImpl#selectAll1}</td></tr>
 * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese/${id}</pre></td><td>{@link CheeseDaoImpl#selectById2}</td></tr>
 * </table>
 *
 * <h2>Supported insert operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese</pre></td><td>{@link CheeseDaoImpl#insert0}</td></tr>
 * </table>
 *
 * <h2>Supported update operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese/${cheese.id}</pre></td><td>{@link CheeseDaoImpl#update4}</td></tr>
 * </table>
 *
 * <h2>Supported delete operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese/${id}</pre></td><td>{@link CheeseDaoImpl#deleteById3}</td></tr>
 * </table>
 *
 */
public class BindSampleContentProvider extends ContentProvider {
  /**
   * <p>content provider's URI.</p>
   * <pre>content://com.abubusoft.contentprovidersample.provider</pre>
   */
  public static final String URI = "content://com.abubusoft.contentprovidersample.provider";

  /** <p>datasource singleton</p>. */
  private static BindSampleDataSource dataSource;

  /** <p>Content provider authority</p>. */
  public static final String AUTHORITY = "com.abubusoft.contentprovidersample.provider";

  /** <p>URI matcher</p>. */
  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

  /**
   * <p>Uri</p>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese</pre>
   */
  private static final Uri URI_PATH_CHEESE_1 = Uri.parse(URI+"/cheese");

  /**
   * <p>Uri</p>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese/#</pre>
   */
  private static final Uri URI_PATH_CHEESE_2 = Uri.parse(URI+"/cheese/#");

  /** The Constant PATH_CHEESE_1. */
  static final String PATH_CHEESE_1 = "cheese";

  /** The Constant PATH_CHEESE_2. */
  static final String PATH_CHEESE_2 = "cheese/#";

  /** The Constant PATH_CHEESE_1_INDEX. */
  static final int PATH_CHEESE_1_INDEX = 1;

  /** The Constant PATH_CHEESE_2_INDEX. */
  static final int PATH_CHEESE_2_INDEX = 2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese</pre>
   *
   * <p>Method associated to this URI is {@link CheeseDaoImpl#insert0}</p>
   */
  public static final Uri URI_CHEESE_INSERT = URI_PATH_CHEESE_1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese</pre>
   *
   * <p>Method associated to this URI is {@link CheeseDaoImpl#selectAll1}</p>
   */
  public static final Uri URI_CHEESE_SELECT_ALL = URI_PATH_CHEESE_1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese/${id}</pre>
   *
   * <p>Method associated to this URI is {@link CheeseDaoImpl#deleteById3}</p>
   */
  public static final Uri URI_CHEESE_DELETE_BY_ID = URI_PATH_CHEESE_2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese/${id}</pre>
   *
   * <p>Method associated to this URI is {@link CheeseDaoImpl#selectById2}</p>
   */
  public static final Uri URI_CHEESE_SELECT_BY_ID = URI_PATH_CHEESE_2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://com.abubusoft.contentprovidersample.provider/cheese/${cheese.id}</pre>
   *
   * <p>Method associated to this URI is {@link CheeseDaoImpl#update4}</p>
   */
  public static final Uri URI_CHEESE_UPDATE = URI_PATH_CHEESE_2;

  static {
    sURIMatcher.addURI(AUTHORITY, PATH_CHEESE_1, PATH_CHEESE_1_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_CHEESE_2, PATH_CHEESE_2_INDEX);
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
    dataSource = BindSampleDataSource.instance();
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
   * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese</pre></td><td>{@link CheeseDaoImpl#selectAll1}</td></tr>
   * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese/${id}</pre></td><td>{@link CheeseDaoImpl#selectById2}</td></tr>
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
      case PATH_CHEESE_1_INDEX: {
        // URI: content://com.abubusoft.contentprovidersample.provider/cheese
        returnCursor=dataSource.getCheeseDao().selectAll1(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_CHEESE_2_INDEX: {
        // URI: content://com.abubusoft.contentprovidersample.provider/cheese/${id}
        returnCursor=dataSource.getCheeseDao().selectById2(uri, projection, selection, selectionArgs, sortOrder);
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
   * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese</pre></td><td>{@link CheeseDaoImpl#insert0}</td></tr>
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
      case PATH_CHEESE_1_INDEX: {
        _id=dataSource.getCheeseDao().insert0(uri, contentValues);
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
   * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese/${cheese.id}</pre></td><td>{@link CheeseDaoImpl#update4}</td></tr>
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
      case PATH_CHEESE_2_INDEX: {
        // URI: content://com.abubusoft.contentprovidersample.provider/cheese/${cheese.id}
        returnRowUpdated=dataSource.getCheeseDao().update4(uri, contentValues, selection, selectionArgs);
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
   * <tr><td><pre>content://com.abubusoft.contentprovidersample.provider/cheese/${id}</pre></td><td>{@link CheeseDaoImpl#deleteById3}</td></tr>
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
      case PATH_CHEESE_2_INDEX: {
        // URI: content://com.abubusoft.contentprovidersample.provider/cheese/${id}
        returnRowDeleted=dataSource.getCheeseDao().deleteById3(uri, selection, selectionArgs);
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
      case PATH_CHEESE_1_INDEX: {
        return "vnd.android.cursor.dir/vnd.com.abubusoft.contentprovidersample.provider.cheese";
      }
      case PATH_CHEESE_2_INDEX: {
        return "vnd.android.cursor.dir/vnd.com.abubusoft.contentprovidersample.provider.cheese";
      }
    }
    throw new IllegalArgumentException("Unknown URI for getType operation: " + uri);
  }
}
