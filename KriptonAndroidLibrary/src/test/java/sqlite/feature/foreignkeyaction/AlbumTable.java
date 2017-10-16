package sqlite.feature.foreignkeyaction;

/**
 * <p>
 * Entity <code>Album</code> is associated to table <code>album</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Album
 */
public class AlbumTable {
  /**
   * Costant represents typeName of table album
   */
  public static final String TABLE_NAME = "album";

  /**
   * <p>
   * DDL to create table album
   * </p>
   *
   * <pre>CREATE TABLE album (id INTEGER PRIMARY KEY AUTOINCREMENT, artist_id INTEGER, name TEXT, FOREIGN KEY(artist_id) REFERENCES artist(id) ON DELETE CASCADE ON UPDATE CASCADE);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE album (id INTEGER PRIMARY KEY AUTOINCREMENT, artist_id INTEGER, name TEXT, FOREIGN KEY(artist_id) REFERENCES artist(id) ON DELETE CASCADE ON UPDATE CASCADE);";

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
   * Entity's property <code>artistId</code> is associated to table column <code>artist_id</code>. This costant represents column name.
   *
   *  @see Album#artistId
   */
  public static final String COLUMN_ARTIST_ID = "artist_id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Album#name
   */
  public static final String COLUMN_NAME = "name";
}
