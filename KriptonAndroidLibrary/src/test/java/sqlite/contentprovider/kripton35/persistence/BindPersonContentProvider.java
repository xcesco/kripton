package sqlite.contentprovider.kripton35.persistence;

import android.content.ContentProvider;
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

  static final String PATH_PERSON_1 = "persons";

  static final String PATH_PERSON_2 = "persons/#";

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
   * uri persons
   */
  @Override
  public Uri insert(Uri uri, ContentValues values) {
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_1_INDEX: {
        break;
      }
    }
    return null;
  }

  /**
   * method PersonDAO.selectOne
   * method PersonDAO.selectBeanListener
   * uri persons
   */
  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_1_INDEX: {
        break;
      }
    }
    return null;
  }

  /**
   * method PersonDAO.deleteBean
   * uri persons/#
   */
  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_2_INDEX: {
        break;
      }
    }
    return 0;
  }

  /**
   * method PersonDAO.updateWhereStaticAndDynamic
   * uri persons/#
   */
  @Override
  public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_2_INDEX: {
        break;
      }
    }
    return 0;
  }

  /**
   * uri persons
   * uri persons/#
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
