package sqlite.feature.join.model;

import com.abubusoft.kripton.android.orm.SQLiteTable;

/**
 * <p>
 * Entity <code>Book</code> is associated to table <code>book</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Book
 */
public class BookTable implements SQLiteTable {
  /**
   * Costant represents typeName of table book
   */
  public static final String TABLE_NAME = "book";

  /**
   * <p>
   * DDL to create table book
   * </p>
   *
   * <pre>CREATE TABLE book (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE book (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT);";

  /**
   * <p>
   * DDL to drop table book
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS book;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS book;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Book#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>title</code> is associated to table column <code>title</code>. This costant represents column name.
   *
   *  @see Book#title
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
