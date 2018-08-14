package sqlite.feature.custombean.case1;

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
   * <pre>CREATE TABLE user (id TEXT PRIMARY KEY NOT NULL, age INTEGER, last_name TEXT, name TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE user (id TEXT PRIMARY KEY NOT NULL, age INTEGER, last_name TEXT, name TEXT);";

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
   * Entity's property <code>age</code> is associated to table column <code>age</code>. This costant represents column name.
   *
   *  @see User#age
   */
  public static final String COLUMN_AGE = "age";

  /**
   * Entity's property <code>lastName</code> is associated to table column <code>last_name</code>. This costant represents column name.
   *
   *  @see User#lastName
   */
  public static final String COLUMN_LAST_NAME = "last_name";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see User#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_AGE, COLUMN_LAST_NAME, COLUMN_NAME};

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
