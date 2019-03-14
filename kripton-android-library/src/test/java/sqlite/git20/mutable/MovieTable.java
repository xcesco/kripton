package sqlite.git20.mutable;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Movie</code> is associated to table <code>director</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Movie
 */
public class MovieTable implements SQLiteTable {
  /**
   * Costant represents typeName of table director
   */
  public static final String TABLE_NAME = "director";

  /**
   * <p>
   * DDL to create table director
   * </p>
   *
   * <pre>CREATE TABLE director (mid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT); CREATE INDEX idx_director_0 on director (title);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE director (mid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT); CREATE INDEX idx_director_0 on director (title);";

  /**
   * <p>
   * DDL to drop table director
   * </p>
   *
   * <pre> DROP INDEX IF EXISTS idx_director_1;DROP TABLE IF EXISTS director;</pre>
   */
  public static final String DROP_TABLE_SQL = " DROP INDEX IF EXISTS idx_director_1;DROP TABLE IF EXISTS director;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>mid</code>. This costant represents column name.
   *
   *  @see Movie#id
   */
  public static final String COLUMN_ID = "mid";

  /**
   * Entity's property <code>title</code> is associated to table column <code>title</code>. This costant represents column name.
   *
   *  @see Movie#title
   */
  public static final String COLUMN_TITLE = "title";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_TITLE};

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
