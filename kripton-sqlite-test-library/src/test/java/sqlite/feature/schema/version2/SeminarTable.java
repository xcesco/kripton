package sqlite.feature.schema.version2;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Seminar</code> is associated to table <code>seminar</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Seminar
 */
public class SeminarTable implements SQLiteTable {
  /**
   * Costant represents typeName of table seminar
   */
  public static final String TABLE_NAME = "seminar";

  /**
   * <p>
   * DDL to create table seminar
   * </p>
   *
   * <pre>CREATE TABLE seminar (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, location TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE seminar (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, location TEXT);";

  /**
   * <p>
   * DDL to drop table seminar
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS seminar;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS seminar;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Seminar#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Seminar#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>location</code> is associated to table column <code>location</code>. This costant represents column name.
   *
   *  @see Seminar#location
   */
  public static final String COLUMN_LOCATION = "location";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_LOCATION};

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
