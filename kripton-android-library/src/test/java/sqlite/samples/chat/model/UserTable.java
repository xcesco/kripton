package sqlite.samples.chat.model;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>User</code> is associated to table <code>user</code>
 * This class represents table associated to entity.
 * </p>
 *  @see User
 */
public class UserTable implements SQLiteTable {
  /**
   * Costant represents typeName of table user
   */
  public static final String TABLE_NAME = "user";

  /**
   * <p>
   * DDL to create table user
   * </p>
   *
   * <pre>CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT);";

  /**
   * <p>
   * DDL to drop table user
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS user;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS user;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see User#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>username</code> is associated to table column <code>username</code>. This costant represents column name.
   *
   *  @see User#username
   */
  public static final String COLUMN_USERNAME = "username";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_USERNAME};

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
