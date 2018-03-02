package sqlite.feature.contentprovider.kripton213.case1;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Cheese</code> is associated to table <code>cheeses</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Cheese
 */
public class CheeseTable implements SQLiteTable {
  /**
   * Costant represents typeName of table cheeses
   */
  public static final String TABLE_NAME = "cheeses";

  /**
   * <p>
   * DDL to create table cheeses
   * </p>
   *
   * <pre>CREATE TABLE cheeses (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE cheeses (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);";

  /**
   * <p>
   * DDL to drop table cheeses
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS cheeses;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS cheeses;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Cheese#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Cheese#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME};

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
