package sqlite.feature.livedata.paginated.case2;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Group</code> is associated to table <code>person_groups</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Group
 */
public class GroupTable implements SQLiteTable {
  /**
   * Costant represents typeName of table person_groups
   */
  public static final String TABLE_NAME = "person_groups";

  /**
   * <p>
   * DDL to create table person_groups
   * </p>
   *
   * <pre>CREATE TABLE person_groups (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person_groups (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT);";

  /**
   * <p>
   * DDL to drop table person_groups
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS person_groups;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS person_groups;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Group#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Group#name
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
