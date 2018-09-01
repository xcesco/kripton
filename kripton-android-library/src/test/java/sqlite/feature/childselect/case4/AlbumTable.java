package sqlite.feature.childselect.case4;

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
   * <pre>CREATE TABLE album (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE album (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT);";

  /**
   * <p>
   * DDL to drop table album
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS album;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS album;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Album#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Album#name
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
