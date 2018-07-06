package sqlite.feature.pkstring.relations;

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
   * <pre>CREATE TABLE song (name TEXT PRIMARY KEY, album_id TEXT, FOREIGN KEY(album_id) REFERENCES album(id));</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE song (name TEXT PRIMARY KEY, album_id TEXT, FOREIGN KEY(album_id) REFERENCES album(id));";

  /**
   * <p>
   * DDL to drop table song
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS song;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS song;";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Song#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>albumId</code> is associated to table column <code>album_id</code>. This costant represents column name.
   *
   *  @see Song#albumId
   */
  public static final String COLUMN_ALBUM_ID = "album_id";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_NAME, COLUMN_ALBUM_ID};

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
