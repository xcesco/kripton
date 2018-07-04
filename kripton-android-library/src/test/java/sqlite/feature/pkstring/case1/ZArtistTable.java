package sqlite.feature.pkstring.case1;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>ZArtist</code> is associated to table <code>z_artist</code>
 * This class represents table associated to entity.
 * </p>
 *  @see ZArtist
 */
public class ZArtistTable implements SQLiteTable {
  /**
   * Costant represents typeName of table z_artist
   */
  public static final String TABLE_NAME = "z_artist";

  /**
   * <p>
   * DDL to create table z_artist
   * </p>
   *
   * <pre>CREATE TABLE z_artist (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, year TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE z_artist (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, year TEXT);";

  /**
   * <p>
   * DDL to drop table z_artist
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS z_artist;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS z_artist;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see ZArtist#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see ZArtist#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>year</code> is associated to table column <code>year</code>. This costant represents column name.
   *
   *  @see ZArtist#year
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
