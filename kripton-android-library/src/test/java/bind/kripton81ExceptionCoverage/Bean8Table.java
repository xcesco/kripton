package bind.kripton81ExceptionCoverage;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Bean8</code> is associated to table <code>bean8</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Bean8
 */
public class Bean8Table implements SQLiteTable {
  /**
   * Costant represents typeName of table bean8
   */
  public static final String TABLE_NAME = "bean8";

  /**
   * <p>
   * DDL to create table bean8
   * </p>
   *
   * <pre>CREATE TABLE bean8 (id INTEGER PRIMARY KEY AUTOINCREMENT, ignore2 TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean8 (id INTEGER PRIMARY KEY AUTOINCREMENT, ignore2 TEXT);";

  /**
   * <p>
   * DDL to drop table bean8
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean8;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean8;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Bean8#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>ignore2</code> is associated to table column <code>ignore2</code>. This costant represents column name.
   *
   *  @see Bean8#ignore2
   */
  public static final String COLUMN_IGNORE2 = "ignore2";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_IGNORE2};

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
