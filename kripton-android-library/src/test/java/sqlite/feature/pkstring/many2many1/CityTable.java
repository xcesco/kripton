package sqlite.feature.pkstring.many2many1;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>City</code> is associated to table <code>cities</code>
 * This class represents table associated to entity.
 * </p>
 *  @see City
 */
public class CityTable implements SQLiteTable {
  /**
   * Costant represents typeName of table cities
   */
  public static final String TABLE_NAME = "cities";

  /**
   * <p>
   * DDL to create table cities
   * </p>
   *
   * <pre>CREATE TABLE cities (id TEXT PRIMARY KEY NOT NULL, name TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE cities (id TEXT PRIMARY KEY NOT NULL, name TEXT);";

  /**
   * <p>
   * DDL to drop table cities
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS cities;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS cities;";

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
