package sqlite.feature.pkstring.case1;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Song</code> is associated to table <code>song</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Song
 */
public class SongTable implements SQLiteTable {
  /**
   * Costant represents typeName of table song
   */
  public static final String TABLE_NAME = "song";

  /**
   * <p>
   * DDL to create table song
   * </p>
   *
   * <pre>CREATE TABLE song (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, year TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE song (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, year TEXT);";

  /**
   * <p>
   * DDL to drop table song
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS song;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS song;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Song#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Song#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>year</code> is associated to table column <code>year</code>. This costant represents column name.
   *
   *  @see Song#year
   */
  public static final String COLUMN_YEAR = "year";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_YEAR};

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
