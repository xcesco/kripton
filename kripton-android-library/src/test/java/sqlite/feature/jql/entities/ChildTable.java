package sqlite.feature.jql.entities;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Child</code> is associated to table <code>child</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Child
 */
public class ChildTable implements SQLiteTable {
  /**
   * Costant represents typeName of table child
   */
  public static final String TABLE_NAME = "child";

  /**
   * <p>
   * DDL to create table child
   * </p>
   *
   * <pre>CREATE TABLE child (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, parent_id INTEGER, FOREIGN KEY(parent_id) REFERENCES person(_id));</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE child (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, parent_id INTEGER, FOREIGN KEY(parent_id) REFERENCES person(_id));";

  /**
   * <p>
   * DDL to drop table child
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS child;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS child;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>_id</code>. This costant represents column name.
   *
   *  @see Child#id
   */
  public static final String COLUMN_ID = "_id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Child#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>parentId</code> is associated to table column <code>parent_id</code>. This costant represents column name.
   *
   *  @see Child#parentId
   */
  public static final String COLUMN_PARENT_ID = "parent_id";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_PARENT_ID};

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
