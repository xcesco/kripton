package sqlite.feature.many2many.case7;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>City</code> is associated to table <code>city</code>
 * This class represents table associated to entity.
 * </p>
 *  @see City
 */
public class CityTable implements SQLiteTable {
  /**
   * Costant represents typeName of table city
   */
  public static final String TABLE_NAME = "city";

  /**
   * <p>
   * DDL to create table city
   * </p>
   *
   * <pre>CREATE TABLE city (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE city (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT);";

  /**
   * <p>
   * DDL to drop table city
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS city;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS city;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see City#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see City#name
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
