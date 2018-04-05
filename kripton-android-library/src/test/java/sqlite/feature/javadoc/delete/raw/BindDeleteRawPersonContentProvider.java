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
package sqlite.feature.javadoc.delete.raw;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;

// TODO: Auto-generated Javadoc
/**
 * <p>This is the content provider generated for {@link DeleteRawPersonDataSource}</p>
 *
 * <h2>Content provider authority:</h2>
 * <pre>sqlite.feature.javadoc.bean</pre>
 *
 * <h2>Supported insert operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * </table>
 *
 * <h2>Supported delete operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/${id}</pre></td><td>{@link DeleteRawPersonDaoImpl#deleteOneBean0}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/${id}/moreAndMore</pre></td><td>{@link DeleteRawPersonDaoImpl#deleteBeanDynamicWithArgs5}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/${surname}</pre></td><td>{@link DeleteRawPersonDaoImpl#deleteOneBean1}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/a/${surname}/${name}</pre></td><td>{@link DeleteRawPersonDaoImpl#deleteFromSelectAllBeansJQL2}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/single/${id}</pre></td><td>{@link DeleteRawPersonDaoImpl#deleteRaw3}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/single2/${id}</pre></td><td>{@link DeleteRawPersonDaoImpl#deleteRawDynamic4}</td></tr>
 * </table>
 *
 */
public class BindDeleteRawPersonContentProvider extends ContentProvider {
  /**
   * <p>content provider's URI.</p>
   * <pre>content://sqlite.feature.javadoc.bean</pre>
   */
  public static final String URI = "content://sqlite.feature.javadoc.bean";

  /** <p>datasource singleton</p>. */
  private static BindDeleteRawPersonDataSource dataSource;

  /** <p>Content provider authority</p>. */
  public static final String AUTHORITY = "sqlite.feature.javadoc.bean";

  /** <p>URI matcher</p>. */
  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#</pre>
   */
  private static final Uri URI_PATH_PERSON_1 = Uri.parse(URI+"/persons/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#/moreAndMore</pre>
   */
  private static final Uri URI_PATH_PERSON_2 = Uri.parse(URI+"/persons/#/moreAndMore");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/[*]</pre>
   */
  private static final Uri URI_PATH_PERSON_3 = Uri.parse(URI+"/persons/*");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/a/[*]/[*]</pre>
   */
  private static final Uri URI_PATH_PERSON_4 = Uri.parse(URI+"/persons/a/*/*");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/single/#</pre>
   */
  private static final Uri URI_PATH_PERSON_5 = Uri.parse(URI+"/persons/single/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/single2/#</pre>
   */
  private static final Uri URI_PATH_PERSON_6 = Uri.parse(URI+"/persons/single2/#");

  /** The Constant PATH_PERSON_1. */
  static final String PATH_PERSON_1 = "persons/#";

  /** The Constant PATH_PERSON_2. */
  static final String PATH_PERSON_2 = "persons/#/moreAndMore";

  /** The Constant PATH_PERSON_3. */
  static final String PATH_PERSON_3 = "persons/*";

  /** The Constant PATH_PERSON_4. */
  static final String PATH_PERSON_4 = "persons/a/*/*";

  /** The Constant PATH_PERSON_5. */
  static final String PATH_PERSON_5 = "persons/single/#";

  /** The Constant PATH_PERSON_6. */
  static final String PATH_PERSON_6 = "persons/single2/#";

  /** The Constant PATH_PERSON_1_INDEX. */
  static final int PATH_PERSON_1_INDEX = 1;

  /** The Constant PATH_PERSON_2_INDEX. */
  static final int PATH_PERSON_2_INDEX = 2;

  /** The Constant PATH_PERSON_3_INDEX. */
  static final int PATH_PERSON_3_INDEX = 3;

  /** The Constant PATH_PERSON_4_INDEX. */
  static final int PATH_PERSON_4_INDEX = 4;

  /** The Constant PATH_PERSON_5_INDEX. */
  static final int PATH_PERSON_5_INDEX = 5;

  /** The Constant PATH_PERSON_6_INDEX. */
  static final int PATH_PERSON_6_INDEX = 6;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/${id}</pre>
   *
   * <p>Method associated to this URI is {@link DeleteRawPersonDaoImpl#deleteOneBean0}</p>
   */
  public static final Uri URI_PERSON_DELETE_ONE_BEAN = URI_PATH_PERSON_1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#/moreAndMore</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/${id}/moreAndMore</pre>
   *
   * <p>Method associated to this URI is {@link DeleteRawPersonDaoImpl#deleteBeanDynamicWithArgs5}</p>
   */
  public static final Uri URI_PERSON_DELETE_BEAN_DYNAMIC_WITH_ARGS = URI_PATH_PERSON_2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/a/[*]/[*]</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/a/${surname}/${name}</pre>
   *
   * <p>Method associated to this URI is {@link DeleteRawPersonDaoImpl#deleteFromSelectAllBeansJQL2}</p>
   */
  public static final Uri URI_PERSON_DELETE_FROM_SELECT_ALL_BEANS_J_Q_L = URI_PATH_PERSON_4;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/single/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/single/${id}</pre>
   *
   * <p>Method associated to this URI is {@link DeleteRawPersonDaoImpl#deleteRaw3}</p>
   */
  public static final Uri URI_PERSON_DELETE_RAW = URI_PATH_PERSON_5;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/single2/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/single2/${id}</pre>
   *
   * <p>Method associated to this URI is {@link DeleteRawPersonDaoImpl#deleteRawDynamic4}</p>
   */
  public static final Uri URI_PERSON_DELETE_RAW_DYNAMIC = URI_PATH_PERSON_6;

  static {
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_1, PATH_PERSON_1_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_2, PATH_PERSON_2_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_3, PATH_PERSON_3_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_4, PATH_PERSON_4_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_5, PATH_PERSON_5_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_6, PATH_PERSON_6_INDEX);
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
    dataSource = BindDeleteRawPersonDataSource.instance();
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

  /* (non-Javadoc)
   * @see android.content.ContentProvider#query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String)
   */
  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    throw new IllegalArgumentException("Unknown URI for SELECT operation: " + uri);
  }

  /**
   * <h2>Supported insert operations</h2>
   * <table>
   * <tr><th>URI</th><th>DAO.METHOD</th></tr>
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
      default: {
        throw new IllegalArgumentException("Unknown URI for INSERT operation: " + uri);
      }
    }
  }

  /* (non-Javadoc)
   * @see android.content.ContentProvider#update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[])
   */
  @Override
  public int update(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    throw new IllegalArgumentException("Unknown URI for UPDATE operation: " + uri);
  }

  /**
   * <h2>Supported delete operations</h2>
   * <table>
   * <tr><th>URI</th><th>DAO.METHOD</th></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/${id}</pre></td><td>{@link DeleteRawPersonDaoImpl#deleteOneBean0}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/${id}/moreAndMore</pre></td><td>{@link DeleteRawPersonDaoImpl#deleteBeanDynamicWithArgs5}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/${surname}</pre></td><td>{@link DeleteRawPersonDaoImpl#deleteOneBean1}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/a/${surname}/${name}</pre></td><td>{@link DeleteRawPersonDaoImpl#deleteFromSelectAllBeansJQL2}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/single/${id}</pre></td><td>{@link DeleteRawPersonDaoImpl#deleteRaw3}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/single2/${id}</pre></td><td>{@link DeleteRawPersonDaoImpl#deleteRawDynamic4}</td></tr>
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
      case PATH_PERSON_1_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/${id}
        returnRowDeleted=dataSource.getDeleteRawPersonDao().deleteOneBean0(uri, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_2_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/${id}/moreAndMore
        returnRowDeleted=dataSource.getDeleteRawPersonDao().deleteBeanDynamicWithArgs5(uri, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_3_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/${surname}
        returnRowDeleted=dataSource.getDeleteRawPersonDao().deleteOneBean1(uri, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_4_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/a/${surname}/${name}
        returnRowDeleted=dataSource.getDeleteRawPersonDao().deleteFromSelectAllBeansJQL2(uri, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_5_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/single/${id}
        returnRowDeleted=dataSource.getDeleteRawPersonDao().deleteRaw3(uri, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_6_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/single2/${id}
        returnRowDeleted=dataSource.getDeleteRawPersonDao().deleteRawDynamic4(uri, selection, selectionArgs);
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
    }
    throw new IllegalArgumentException("Unknown URI for getType operation: " + uri);
  }
}
