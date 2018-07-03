package sqlite.kripton111.model;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Country</code> is associated to table <code>country</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Country
 */
public class CountryTable implements SQLiteTable {
  /**
   * Costant represents typeName of table country
   */
  public static final String TABLE_NAME = "country";

  /**
   * <p>
   * DDL to create table country
   * </p>
   *
   * <pre>CREATE TABLE country (id INTEGER PRIMARY KEY AUTOINCREMENT, area INTEGER, calling_code TEXT NOT NULL, code TEXT UNIQUE NOT NULL, name TEXT NOT NULL, region TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE country (id INTEGER PRIMARY KEY AUTOINCREMENT, area INTEGER, calling_code TEXT NOT NULL, code TEXT UNIQUE NOT NULL, name TEXT NOT NULL, region TEXT);";

  /**
   * <p>
   * DDL to drop table country
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS country;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS country;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Country#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>area</code> is associated to table column <code>area</code>. This costant represents column name.
   *
   *  @see Country#area
   */
  public static final String COLUMN_AREA = "area";

  /**
   * Entity's property <code>callingCode</code> is associated to table column <code>calling_code</code>. This costant represents column name.
   *
   *  @see Country#callingCode
   */
  public static final String COLUMN_CALLING_CODE = "calling_code";

  /**
   * Entity's property <code>code</code> is associated to table column <code>code</code>. This costant represents column name.
   *
   *  @see Country#code
   */
  public static final String COLUMN_CODE = "code";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Country#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>region</code> is associated to table column <code>region</code>. This costant represents column name.
   *
   *  @see Country#region
   */
  public static final String COLUMN_REGION = "region";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_AREA, COLUMN_CALLING_CODE, COLUMN_CODE, COLUMN_NAME, COLUMN_REGION};

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
