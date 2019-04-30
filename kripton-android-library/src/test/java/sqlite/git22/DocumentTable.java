package sqlite.git22;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Document</code> is associated to table <code>document</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Document
 */
public class DocumentTable implements SQLiteTable {
  /**
   * Costant represents typeName of table document
   */
  public static final String TABLE_NAME = "document";

  /**
   * <p>
   * DDL to create table document
   * </p>
   *
   * <pre>CREATE TABLE document (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, file_name TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE document (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, file_name TEXT);";

  /**
   * <p>
   * DDL to drop table document
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS document;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS document;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Document#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>fileName</code> is associated to table column <code>file_name</code>. This costant represents column name.
   *
   *  @see Document#fileName
   */
  public static final String COLUMN_FILE_NAME = "file_name";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_FILE_NAME};

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
