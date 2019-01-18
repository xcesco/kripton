package sqlite.kripton296.model;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Movie</code> is associated to table <code>movie</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Movie
 */
public class MovieTable implements SQLiteTable {
  /**
   * Costant represents typeName of table movie
   */
  public static final String TABLE_NAME = "movie";

  /**
   * <p>
   * DDL to create table movie
   * </p>
   *
   * <pre>CREATE TABLE movie (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, director_id INTEGER, title TEXT, FOREIGN KEY(director_id) REFERENCES director(did) ON DELETE CASCADE); CREATE INDEX idx_movie_0 on movie (title); CREATE INDEX idx_movie_1 on movie (director_id);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE movie (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, director_id INTEGER, title TEXT, FOREIGN KEY(director_id) REFERENCES director(did) ON DELETE CASCADE); CREATE INDEX idx_movie_0 on movie (title); CREATE INDEX idx_movie_1 on movie (director_id);";

  /**
   * <p>
   * DDL to drop table movie
   * </p>
   *
   * <pre> DROP INDEX IF EXISTS idx_movie_1; DROP INDEX IF EXISTS idx_movie_2;DROP TABLE IF EXISTS movie;</pre>
   */
  public static final String DROP_TABLE_SQL = " DROP INDEX IF EXISTS idx_movie_1; DROP INDEX IF EXISTS idx_movie_2;DROP TABLE IF EXISTS movie;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Movie#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>directorId</code> is associated to table column <code>director_id</code>. This costant represents column name.
   *
   *  @see Movie#directorId
   */
  public static final String COLUMN_DIRECTOR_ID = "director_id";

  /**
   * Entity's property <code>title</code> is associated to table column <code>title</code>. This costant represents column name.
   *
   *  @see Movie#title
   */
  public static final String COLUMN_TITLE = "title";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_DIRECTOR_ID, COLUMN_TITLE};

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
