package commons.kripton86.test5;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Bean5</code> is associated to table <code>bean5</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Bean5
 */
public class Bean5Table implements SQLiteTable {
  /**
   * Costant represents typeName of table bean5
   */
  public static final String TABLE_NAME = "bean5";

  /**
   * <p>
   * DDL to create table bean5
   * </p>
   *
   * <pre>CREATE TABLE bean5 (id TEXT PRIMARY KEY AUTOINCREMENT, test TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean5 (id TEXT PRIMARY KEY AUTOINCREMENT, test TEXT);";

  /**
   * <p>
   * DDL to drop table bean5
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean5;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean5;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Bean5#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>test</code> is associated to table column <code>test</code>. This costant represents column name.
   *
   *  @see Bean5#test
   */
  public static final String COLUMN_TEST = "test";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_TEST};

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
