package sqlite.feature.optional.model;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Article</code> is associated to table <code>articles</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Article
 */
public class ArticleTable implements SQLiteTable {
  /**
   * Costant represents typeName of table articles
   */
  public static final String TABLE_NAME = "articles";

  /**
   * <p>
   * DDL to create table articles
   * </p>
   *
   * <pre>CREATE TABLE articles (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE articles (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT);";

  /**
   * <p>
   * DDL to drop table articles
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS articles;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS articles;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Article#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>title</code> is associated to table column <code>title</code>. This costant represents column name.
   *
   *  @see Article#title
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
