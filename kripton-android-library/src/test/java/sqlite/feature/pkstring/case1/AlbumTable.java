package sqlite.feature.pkstring.case1;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Album</code> is associated to table <code>album</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Album
 */
public class AlbumTable implements SQLiteTable {
  /**
   * Costant represents typeName of table album
   */
  public static final String TABLE_NAME = "album";

  /**
   * <p>
   * DDL to create table album
   * </p>
   *
   * <pre>CREATE TABLE album (name TEXT PRIMARY KEY, year TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE album (name TEXT PRIMARY KEY, year TEXT);";

  /**
   * <p>
   * DDL to drop table album
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS album;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS album;";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Album#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>year</code> is associated to table column <code>year</code>. This costant represents column name.
   *
   *  @see Album#year
   */
  public static final String COLUMN_YEAR = "year";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_NAME, COLUMN_YEAR};

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
