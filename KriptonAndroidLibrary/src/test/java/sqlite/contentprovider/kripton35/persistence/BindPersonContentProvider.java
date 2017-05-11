package sqlite.contentprovider.kripton35.persistence;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import java.lang.Override;
import java.lang.String;

public class BindPersonContentProvider extends ContentProvider {
  /**
   * <p>datasource singleton</p>
   */
  private static BindPersonDataSource dataSource;

  /**
   * <p>Content provider authority</p>
   */
  private static final String AUTHORITY = "sqlite.contentprovider.kripton35";

  /**
   * <p>URI matcher</p>
   */
  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

  /**
   * <p>URI matcher for "persons", supplied by "PersonDAO"</p>
   */
  private static final int URI_PERSON_0 = 0;

  /**
   * <p>URI matcher for "persons/#", supplied by "PersonDAO"</p>
   */
  private static final int URI_PERSON_1 = 1;

  static {
    sURIMatcher.addURI(AUTHORITY, "persons", URI_PERSON_0);
    sURIMatcher.addURI(AUTHORITY, "persons/#", URI_PERSON_1);
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

  @Override
  public Uri insert(Uri uri, ContentValues values) {
    int uriType=sURIMatcher.match(uri);
    switch(uriType) {
      case URI_PERSON_0:
        return Uri.parse("content://" + AUTHORITY +"/persons/" + "id");
      default:
        return null;
    }
  }

  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
    return null;
  }

  @Override
  public String getType(Uri uri) {
    int uriType=sURIMatcher.match(uri);
    switch(uriType) {
      case URI_PERSON_0:
        return ContentResolver.CURSOR_DIR_BASE_TYPE+"/vnd.sqlite.contentprovider.kripton35.person";
      case URI_PERSON_1:
        return ContentResolver.CURSOR_ITEM_BASE_TYPE+"/vnd.sqlite.contentprovider.kripton35.person";
      default:
        return null;
    }
  }

  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    return 0;
  }

  @Override
  public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
    return 0;
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
}
