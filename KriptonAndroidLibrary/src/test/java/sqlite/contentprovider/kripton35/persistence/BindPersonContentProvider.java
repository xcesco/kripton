package sqlite.contentprovider.kripton35.persistence;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import java.lang.IllegalArgumentException;
import java.lang.Override;
import java.lang.String;

public class BindPersonContentProvider extends ContentProvider {
  /**
   * <p>content provider's URI. Example:</p>
   * <pre>content://sqlite.contentprovider.kripton35</pre>
   */
  public static final String URI = "content://sqlite.contentprovider.kripton35";

  /**
   * <p>datasource singleton</p>
   */
  private static BindPersonDataSource dataSource;

  /**
   * <p>Content provider authority</p>
   */
  public static final String AUTHORITY = "sqlite.contentprovider.kripton35";

  /**
   * <p>URI matcher</p>
   */
  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

  public static final String PATH_PERSON_1 = "persons";

  public static final String PATH_PERSON_2 = "persons/#/children";

  static final int PATH_PERSON_1_INDEX = 1;

  static final int PATH_PERSON_2_INDEX = 2;

  static {
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_1, PATH_PERSON_1_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_2, PATH_PERSON_2_INDEX);
  }

  /**
   * <p>Create datasource and open database in read mode.</p>
   *
   * @see android.content.ContentProvider#onCreate()
   */
  @Override
  public boolean onCreate() {
    dataSource = BindPersonDataSource.instance();
    dataSource.openReadOnlyDatabase();
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
   * method PersonDAO.insertOne
   * method PersonDAO.insertChild
   * uri 
   * uri ${parentId}/children
   */
  @Override
  public Uri insert(Uri uri, ContentValues contentValues) {
    long id=-1;
    Uri returnURL=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_1_INDEX: {
        id=dataSource.getPersonDAO().insertOne0(uri, contentValues);
        returnURL=Uri.withAppendedPath(uri, String.valueOf(id));
        break;
      }
      case PATH_PERSON_2_INDEX: {
        id=dataSource.getPersonDAO().insertChild1(uri, contentValues);
        returnURL=Uri.withAppendedPath(uri, String.valueOf(id));
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI: " + uri);
      }
    }
    getContext().getContentResolver().notifyChange(uri, null);
    return returnURL;
  }

  @Override
  public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
    switch (sURIMatcher.match(uri)) {
    }
    return 0;
  }

  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
    switch (sURIMatcher.match(uri)) {
    }
    return null;
  }

  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    switch (sURIMatcher.match(uri)) {
    }
    return 0;
  }

  /**
   * uri 
   * uri ${parentId}/children
   */
  @Override
  public String getType(Uri uri) {
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_1_INDEX: {
        break;
      }
      case PATH_PERSON_2_INDEX: {
        break;
      }
    }
    return null;
  }
}
