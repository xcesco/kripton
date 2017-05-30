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

  static final String PATH_PERSON_2 = "persons/test";

  static final String PATH_PERSON_3 = "persons/test1";

  static final String PATH_PERSON_4 = "persons/${id}/dummy/${birthCity}";

  static final int PATH_PERSON_1_INDEX = 1;

  static final int PATH_PERSON_2_INDEX = 2;

  static final int PATH_PERSON_3_INDEX = 3;

  static final int PATH_PERSON_4_INDEX = 4;

  static {
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_1, PATH_PERSON_1_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_2, PATH_PERSON_2_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_3, PATH_PERSON_3_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_4, PATH_PERSON_4_INDEX);
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
   * method PersonDAO.insertTwo
   * method PersonDAO.insertTwo
   * uri persons
   * uri persons/test
   * uri persons/test1
   */
  @Override
  public Uri insert(Uri uri, ContentValues contentValues) {
    long id=-1;
    String notifyURL=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_1_INDEX: {
        id=dataSource.getPersonDAO().insertOne0(contentValues);
        notifyURL=PATH_PERSON_1;
        break;
      }
      case PATH_PERSON_2_INDEX: {
        id=dataSource.getPersonDAO().insertTwo1(contentValues);
        notifyURL=PATH_PERSON_2;
        break;
      }
      case PATH_PERSON_3_INDEX: {
        id=dataSource.getPersonDAO().insertTwo2(contentValues);
        notifyURL=PATH_PERSON_3;
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI: " + uri);
      }
    }
    getContext().getContentResolver().notifyChange(uri, null);
    return Uri.parse(notifyURL+"/"+id);
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

  /**
   * method PersonDAO.delete
   * uri persons/${id}/dummy/${birthCity}
   */
  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_4_INDEX: {
        break;
      }
    }
    return 0;
  }

  /**
   * uri persons
   * uri persons/test
   * uri persons/test1
   * uri persons/${id}/dummy/${birthCity}
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
      case PATH_PERSON_3_INDEX: {
        break;
      }
      case PATH_PERSON_4_INDEX: {
        break;
      }
    }
    return null;
  }
}
