package sqlite.feature.pkstring.rx;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>User</code> is associated to table <code>users</code>
 * This class represents table associated to entity.
 * </p>
 *  @see User
 */
public class UserTable implements SQLiteTable {
  /**
   * Costant represents typeName of table users
   */
  public static final String TABLE_NAME = "users";

  /**
   * <p>
   * DDL to create table users
   * </p>
   *
   * <pre>CREATE TABLE users (userid TEXT PRIMARY KEY, username TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE users (userid TEXT PRIMARY KEY, username TEXT);";

  /**
   * <p>
   * DDL to drop table users
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS users;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS users;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>userid</code>. This costant represents column name.
   *
   *  @see User#id
   */
  public static final String COLUMN_ID = "userid";

  /**
   * Entity's property <code>userName</code> is associated to table column <code>username</code>. This costant represents column name.
   *
   *  @see User#userName
   */
  public static final String COLUMN_USER_NAME = "username";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_USER_NAME};

  /**
   * Columns array
   */
  @Override
  public String[] columns() {
    return COLUMNS;
  }

  /**
   * table name
   */
  @Override
  public String name() {
    return TABLE_NAME;
  }
}
